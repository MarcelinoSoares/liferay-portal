/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.security.auth;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.url.pattern.mapper.URLPatternMapper;
import com.liferay.petra.url.pattern.mapper.URLPatternMapperFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierConfiguration;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.registry.Filter;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tomas Polesovsky
 * @author Peter Fellwock
 * @author Arthur Chan
 */
public class AuthVerifierPipeline {

	public static final String AUTH_TYPE = "auth.type";

	public static String getAuthVerifierPropertyName(String className) {
		String simpleClassName = StringUtil.extractLast(
			className, StringPool.PERIOD);

		return StringBundler.concat(
			PropsKeys.AUTH_VERIFIER, simpleClassName, StringPool.PERIOD);
	}

	public AuthVerifierPipeline(Map<String, Object> filterProperties) {
		_filterProperties = filterProperties;

		AuthVerifierTrackerCustomizer.addAuthVerifierPipeline(this);
	}

	public AuthVerifierResult verifyRequest(
			AccessControlContext accessControlContext)
		throws PortalException {

		if (accessControlContext == null) {
			throw new IllegalArgumentException(
				"Access control context is null");
		}

		HttpServletRequest httpServletRequest =
			accessControlContext.getRequest();

		String requestURI = httpServletRequest.getRequestURI();

		String contextPath = httpServletRequest.getContextPath();

		if (requestURI.equals(contextPath)) {
			requestURI += "/";
		}
		else {
			requestURI = requestURI.substring(contextPath.length());
		}

		AuthVerifierConfigurationConsumer authVerifierConfigurationConsumer =
			new AuthVerifierConfigurationConsumer(
				accessControlContext, _excludeURLPatternMapper, requestURI);

		_includeURLPatternMapper.consumeValues(
			authVerifierConfigurationConsumer, requestURI);

		if (authVerifierConfigurationConsumer.getAuthVerifierResult() != null) {
			return authVerifierConfigurationConsumer.getAuthVerifierResult();
		}

		return _createGuestVerificationResult(accessControlContext);
	}

	private AuthVerifierResult _createGuestVerificationResult(
			AccessControlContext accessControlContext)
		throws PortalException {

		AuthVerifierResult authVerifierResult = new AuthVerifierResult();

		authVerifierResult.setState(AuthVerifierResult.State.UNSUCCESSFUL);

		HttpServletRequest httpServletRequest =
			accessControlContext.getRequest();

		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(
			PortalUtil.getCompanyId(httpServletRequest));

		authVerifierResult.setUserId(defaultUserId);

		return authVerifierResult;
	}

	private String _fixLegacyURLPattern(String urlPattern) {
		if ((urlPattern == null) || (urlPattern.length() == 0)) {
			return urlPattern;
		}

		if (urlPattern.charAt(urlPattern.length() - 1) != '*') {
			return urlPattern;
		}

		if ((urlPattern.length() > 1) &&
			(urlPattern.charAt(urlPattern.length() - 2) == '/')) {

			return urlPattern;
		}

		return urlPattern.substring(0, urlPattern.length() - 1) + "/*";
	}

	private AuthVerifierConfiguration _mergeAuthVerifierConfiguration(
		AuthVerifierConfiguration authVerifierConfiguration,
		Map<String, Object> filterProperties) {

		String authVerifierPropertyName = getAuthVerifierPropertyName(
			authVerifierConfiguration.getAuthVerifierClassName());

		Properties mergedProperties = new Properties(
			authVerifierConfiguration.getProperties());

		for (Map.Entry<String, Object> filterPropertyEntry :
				filterProperties.entrySet()) {

			String propertyName = filterPropertyEntry.getKey();

			if (propertyName.startsWith(authVerifierPropertyName)) {
				mergedProperties.setProperty(
					propertyName.substring(authVerifierPropertyName.length()),
					String.valueOf(filterPropertyEntry.getValue()));
			}
		}

		if (mergedProperties.size() < 1) {
			return null;
		}

		AuthVerifierConfiguration mergedAuthVerifierConfiguration =
			new AuthVerifierConfiguration();

		mergedAuthVerifierConfiguration.setAuthVerifier(
			authVerifierConfiguration.getAuthVerifier());
		mergedAuthVerifierConfiguration.setAuthVerifierClassName(
			authVerifierConfiguration.getAuthVerifierClassName());
		mergedAuthVerifierConfiguration.setProperties(mergedProperties);

		return mergedAuthVerifierConfiguration;
	}

	private void _rebuildConfiguration() {
		Map<String, List<AuthVerifierConfiguration>>
			excludeAuthVerifierConfigurations = new HashMap<>();
		Map<String, List<AuthVerifierConfiguration>>
			includeAuthVerifierConfigurations = new HashMap<>();

		for (AuthVerifierConfiguration authVerifierConfiguration :
				AuthVerifierTrackerCustomizer._authVerifierConfigurations) {

			if (!_filterProperties.containsKey("portal_property_prefix")) {
				authVerifierConfiguration = _mergeAuthVerifierConfiguration(
					authVerifierConfiguration, _filterProperties);

				if (authVerifierConfiguration == null) {
					continue;
				}
			}

			Properties properties = authVerifierConfiguration.getProperties();

			String[] urlsExcludes = StringUtil.split(
				properties.getProperty("urls.excludes"));

			for (String urlsExclude : urlsExcludes) {
				urlsExclude = _fixLegacyURLPattern(urlsExclude);

				excludeAuthVerifierConfigurations.computeIfAbsent(
					urlsExclude, key -> new ArrayList<>());

				List<AuthVerifierConfiguration>
					excludeAuthVerifierConfigurationList =
						excludeAuthVerifierConfigurations.get(urlsExclude);

				excludeAuthVerifierConfigurationList.add(
					authVerifierConfiguration);
			}

			String[] urlsIncludes = StringUtil.split(
				properties.getProperty("urls.includes"));

			for (String urlsInclude : urlsIncludes) {
				urlsInclude = _fixLegacyURLPattern(urlsInclude);

				includeAuthVerifierConfigurations.computeIfAbsent(
					urlsInclude, key -> new ArrayList<>());

				List<AuthVerifierConfiguration>
					includeAuthVerifierConfigurationList =
						includeAuthVerifierConfigurations.get(urlsInclude);

				includeAuthVerifierConfigurationList.add(
					authVerifierConfiguration);
			}
		}

		_excludeURLPatternMapper = URLPatternMapperFactory.create(
			excludeAuthVerifierConfigurations);
		_includeURLPatternMapper = URLPatternMapperFactory.create(
			includeAuthVerifierConfigurations);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AuthVerifierPipeline.class);

	private static final ServiceTracker<AuthVerifier, AuthVerifierConfiguration>
		_serviceTracker;

	private URLPatternMapper<List<AuthVerifierConfiguration>>
		_excludeURLPatternMapper;
	private final Map<String, Object> _filterProperties;
	private URLPatternMapper<List<AuthVerifierConfiguration>>
		_includeURLPatternMapper;

	private static class AuthVerifierConfigurationConsumer
		implements Consumer<List<AuthVerifierConfiguration>> {

		public AuthVerifierConfigurationConsumer(
			AccessControlContext accessControlContext,
			URLPatternMapper<List<AuthVerifierConfiguration>>
				excludeURLPatternMapper,
			String requestURI) {

			_accessControlContext = accessControlContext;
			_excludeURLPatternMapper = excludeURLPatternMapper;
			_requestURI = requestURI;
		}

		@Override
		public void accept(
			List<AuthVerifierConfiguration> authVerifierConfigurations) {

			if (_authVerifierResult != null) {
				return;
			}

			if (_excludedAuthVerifierConfigurations == null) {
				_excludedAuthVerifierConfigurations = new HashSet<>();

				_excludeURLPatternMapper.consumeValues(
					_excludedAuthVerifierConfigurations::addAll, _requestURI);
			}

			for (AuthVerifierConfiguration authVerifierConfiguration :
					authVerifierConfigurations) {

				if (_excludedAuthVerifierConfigurations.contains(
						authVerifierConfiguration)) {

					continue;
				}

				_authVerifierResult = _verifyWithAuthVerifierConfiguration(
					_accessControlContext, authVerifierConfiguration);

				if (_authVerifierResult != null) {
					return;
				}
			}
		}

		public AuthVerifierResult getAuthVerifierResult() {
			return _authVerifierResult;
		}

		private Map<String, Object> _mergeSettings(
			Properties properties, Map<String, Object> settings) {

			Map<String, Object> mergedSettings = new HashMap<>(settings);

			if (properties != null) {
				for (Map.Entry<Object, Object> entry : properties.entrySet()) {
					mergedSettings.put(
						(String)entry.getKey(), entry.getValue());
				}
			}

			return mergedSettings;
		}

		private AuthVerifierResult _verifyWithAuthVerifierConfiguration(
			AccessControlContext accessControlContext,
			AuthVerifierConfiguration authVerifierConfiguration) {

			AuthVerifierResult authVerifierResult = null;

			AuthVerifier authVerifier =
				authVerifierConfiguration.getAuthVerifier();

			Properties properties = authVerifierConfiguration.getProperties();

			try {
				authVerifierResult = authVerifier.verify(
					accessControlContext, properties);
			}
			catch (Exception exception) {
				if (_log.isDebugEnabled()) {
					Class<?> authVerifierClass = authVerifier.getClass();

					_log.debug(
						"Skipping " + authVerifierClass.getName(), exception);
				}

				return null;
			}

			if (authVerifierResult == null) {
				Class<?> authVerifierClass = authVerifier.getClass();

				_log.error(
					"Auth verifier " + authVerifierClass.getName() +
						" did not return an auth verifier result");

				return null;
			}

			if (authVerifierResult.getState() ==
					AuthVerifierResult.State.NOT_APPLICABLE) {

				return null;
			}

			User user = UserLocalServiceUtil.fetchUser(
				authVerifierResult.getUserId());

			if ((user == null) || !user.isActive()) {
				if (_log.isDebugEnabled()) {
					Class<?> authVerifierClass = authVerifier.getClass();

					if (user == null) {
						_log.debug(
							StringBundler.concat(
								"Auth verifier ", authVerifierClass.getName(),
								" returned null user",
								authVerifierResult.getUserId()));
					}
					else {
						_log.debug(
							StringBundler.concat(
								"Auth verifier ", authVerifierClass.getName(),
								" returned inactive user",
								authVerifierResult.getUserId()));
					}
				}

				return null;
			}

			Map<String, Object> settings = _mergeSettings(
				properties, authVerifierResult.getSettings());

			settings.put(AUTH_TYPE, authVerifier.getAuthType());

			authVerifierResult.setSettings(settings);

			return authVerifierResult;
		}

		private final AccessControlContext _accessControlContext;
		private AuthVerifierResult _authVerifierResult;
		private Set<AuthVerifierConfiguration>
			_excludedAuthVerifierConfigurations;
		private final URLPatternMapper<List<AuthVerifierConfiguration>>
			_excludeURLPatternMapper;
		private final String _requestURI;

	}

	private static class AuthVerifierTrackerCustomizer
		implements ServiceTrackerCustomizer
			<AuthVerifier, AuthVerifierConfiguration> {

		public static void addAuthVerifierPipeline(
			AuthVerifierPipeline authVerifierPipeline) {

			_authVerifierPipelines.add(authVerifierPipeline);

			authVerifierPipeline._rebuildConfiguration();
		}

		@Override
		public AuthVerifierConfiguration addingService(
			ServiceReference<AuthVerifier> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			AuthVerifier authVerifier = registry.getService(serviceReference);

			if (authVerifier == null) {
				return null;
			}

			Class<?> authVerifierClass = authVerifier.getClass();

			Properties properties = _loadProperties(
				serviceReference, authVerifierClass.getName());

			if (!_validate(properties, authVerifierClass.getName())) {
				return null;
			}

			AuthVerifierConfiguration authVerifierConfiguration =
				new AuthVerifierConfiguration();

			authVerifierConfiguration.setAuthVerifier(authVerifier);
			authVerifierConfiguration.setAuthVerifierClassName(
				authVerifierClass.getName());
			authVerifierConfiguration.setProperties(properties);

			_authVerifierConfigurations.add(authVerifierConfiguration);

			_rebuildAll();

			return authVerifierConfiguration;
		}

		@Override
		public void modifiedService(
			ServiceReference<AuthVerifier> serviceReference,
			AuthVerifierConfiguration authVerifierConfiguration) {

			Properties properties = _loadProperties(
				serviceReference,
				authVerifierConfiguration.getAuthVerifierClassName());

			if (_validate(
					properties,
					authVerifierConfiguration.getAuthVerifierClassName())) {

				authVerifierConfiguration.setProperties(properties);
			}
			else {
				_authVerifierConfigurations.remove(authVerifierConfiguration);
			}

			_rebuildAll();
		}

		@Override
		public void removedService(
			ServiceReference<AuthVerifier> serviceReference,
			AuthVerifierConfiguration authVerifierConfiguration) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_authVerifierConfigurations.remove(authVerifierConfiguration);

			_rebuildAll();
		}

		private Properties _loadProperties(
			ServiceReference<AuthVerifier> serviceReference,
			String authVerifierClassName) {

			Properties properties = new Properties();

			String authVerifierPropertyName = getAuthVerifierPropertyName(
				authVerifierClassName);

			Map<String, Object> serviceReferenceProperties =
				serviceReference.getProperties();

			for (Map.Entry<String, Object> entry :
					serviceReferenceProperties.entrySet()) {

				String key = entry.getKey();

				if (key.startsWith(authVerifierPropertyName)) {
					key = key.substring(authVerifierPropertyName.length());
				}

				properties.setProperty(key, String.valueOf(entry.getValue()));
			}

			return properties;
		}

		private void _rebuildAll() {
			_authVerifierPipelines.forEach(
				AuthVerifierPipeline::_rebuildConfiguration);
		}

		private boolean _validate(
			Properties properties, String authVerifierClassName) {

			String[] urlsIncludes = StringUtil.split(
				properties.getProperty("urls.includes"));

			if (urlsIncludes.length == 0) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Auth verifier " + authVerifierClassName +
							" does not have URLs configured");
				}

				return false;
			}

			return true;
		}

		private static final List<AuthVerifierConfiguration>
			_authVerifierConfigurations = new CopyOnWriteArrayList<>();
		private static final List<AuthVerifierPipeline> _authVerifierPipelines =
			new CopyOnWriteArrayList<>();

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		Filter filter = registry.getFilter(
			"(objectClass=" + AuthVerifier.class.getName() + ")");

		_serviceTracker = registry.trackServices(
			filter, new AuthVerifierTrackerCustomizer());

		_serviceTracker.open();
	}

}