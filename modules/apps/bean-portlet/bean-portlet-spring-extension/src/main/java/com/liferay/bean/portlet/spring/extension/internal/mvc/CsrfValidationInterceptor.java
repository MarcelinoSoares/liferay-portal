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

package com.liferay.bean.portlet.spring.extension.internal.mvc;

import com.liferay.bean.portlet.extension.BeanPortletMethod;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.lang.reflect.Method;

import javax.mvc.security.Csrf;
import javax.mvc.security.CsrfProtected;

import javax.portlet.ClientDataRequest;

import javax.ws.rs.core.Configuration;

/**
 * @author Neil Griffin
 */
public class CsrfValidationInterceptor extends BeanPortletMethodInterceptor {

	public CsrfValidationInterceptor(
		BeanPortletMethod beanPortletMethod, boolean controller,
		Configuration configuration) {

		super(beanPortletMethod, controller);

		_configuration = configuration;
		_targetMethod = beanPortletMethod.getMethod();
	}

	@Override
	public Object invoke(Object... args) throws ReflectiveOperationException {
		if (!isController()) {
			return super.invoke(args);
		}

		Csrf.CsrfOptions csrfOptions = Csrf.CsrfOptions.EXPLICIT;

		Object csrfProtection = _configuration.getProperty(
			Csrf.CSRF_PROTECTION);

		if (csrfProtection != null) {
			if (csrfProtection instanceof Csrf.CsrfOptions) {
				csrfOptions = (Csrf.CsrfOptions)csrfProtection;
			}
			else {
				try {
					csrfOptions = Csrf.CsrfOptions.valueOf(
						csrfProtection.toString());
				}
				catch (IllegalArgumentException iae) {
					_log.error(iae.getMessage(), iae);
				}
			}
		}

		if (csrfOptions == Csrf.CsrfOptions.OFF) {
			return super.invoke(args);
		}

		if ((csrfOptions == Csrf.CsrfOptions.EXPLICIT) &&
			!_targetMethod.isAnnotationPresent(CsrfProtected.class)) {

			return super.invoke(args);
		}

		boolean proceed = false;

		if (args.length == 2) {
			if (args[0] instanceof ClientDataRequest) {
				ClientDataRequest clientDataRequest =
					(ClientDataRequest)args[0];

				ThemeDisplay themeDisplay =
					(ThemeDisplay)clientDataRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				String method = StringUtil.toLowerCase(
					clientDataRequest.getMethod());

				if (method.equals("post")) {
					try {
						AuthTokenUtil.checkCSRFToken(
							themeDisplay.getRequest(),
							CsrfValidationInterceptor.class.getName());

						proceed = true;
					}
					catch (PrincipalException pe) {
						_log.error("Invalid CSRF token");
					}
				}
				else {
					proceed = true;
				}
			}
			else {
				_log.error(
					"First parameter of method signature must be " +
						"ActionRequest or ResourceRequest");
			}
		}
		else {
			_log.error(
				"Method signature must include ActionRequest,ActionResponse " +
					"or ResourceRequest,ResourceResponse");
		}

		if (proceed) {
			return super.invoke(args);
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CsrfValidationInterceptor.class);

	private final Configuration _configuration;
	private final Method _targetMethod;

}