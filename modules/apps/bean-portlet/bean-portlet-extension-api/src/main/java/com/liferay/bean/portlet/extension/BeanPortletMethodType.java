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

package com.liferay.bean.portlet.extension;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.util.function.Function;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.HeaderRequest;
import javax.portlet.HeaderResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.annotations.ActionMethod;
import javax.portlet.annotations.DestroyMethod;
import javax.portlet.annotations.EventMethod;
import javax.portlet.annotations.HeaderMethod;
import javax.portlet.annotations.InitMethod;
import javax.portlet.annotations.RenderMethod;
import javax.portlet.annotations.ServeResourceMethod;

/**
 * @author Neil Griffin
 */
public enum BeanPortletMethodType {

	ACTION(
		ActionMethod.class,
		new Class<?>[] {ActionRequest.class, ActionResponse.class}, false, true,
		null, annotation -> null, annotation -> null,
		annotation -> {
			ActionMethod actionMethod = ActionMethod.class.cast(annotation);

			return new String[] {actionMethod.portletName()};
		},
		annotation -> 0, annotation -> null),
	DESTROY(
		DestroyMethod.class, new Class<?>[0], false, false, null,
		annotation -> null, annotation -> null,
		annotation -> {
			DestroyMethod destroyMethod = DestroyMethod.class.cast(annotation);

			return new String[] {destroyMethod.value()};
		},
		annotation -> 0, annotation -> null),
	EVENT(
		EventMethod.class,
		new Class<?>[] {EventRequest.class, EventResponse.class}, false, false,
		null, annotation -> null, annotation -> null,
		annotation -> {
			EventMethod eventMethod = EventMethod.class.cast(annotation);

			return new String[] {eventMethod.portletName()};
		},
		annotation -> 0, annotation -> null),
	HEADER(
		HeaderMethod.class,
		new Class<?>[] {HeaderRequest.class, HeaderResponse.class}, true, false,
		annotation -> null, annotation -> null,
		annotation -> {
			HeaderMethod headerMethod = HeaderMethod.class.cast(annotation);

			return new PortletMode(headerMethod.portletMode());
		},
		annotation -> {
			HeaderMethod headerMethod = HeaderMethod.class.cast(annotation);

			return headerMethod.portletNames();
		},
		annotation -> {
			HeaderMethod headerMethod = HeaderMethod.class.cast(annotation);

			return headerMethod.ordinal();
		},
		annotation -> {
			HeaderMethod headerMethod = HeaderMethod.class.cast(annotation);

			return headerMethod.include();
		}),
	INIT(
		InitMethod.class, new Class<?>[] {PortletConfig.class}, false, false,
		null, annotation -> null, annotation -> null,
		annotation -> {
			InitMethod initMethod = InitMethod.class.cast(annotation);

			return new String[] {initMethod.value()};
		},
		annotation -> 0, annotation -> null),
	RENDER(
		RenderMethod.class,
		new Class<?>[] {RenderRequest.class, RenderResponse.class}, true, true,
		annotation -> null, annotation -> null,
		annotation -> {
			RenderMethod renderMethod = RenderMethod.class.cast(annotation);

			return new PortletMode(renderMethod.portletMode());
		},
		annotation -> {
			RenderMethod renderMethod = RenderMethod.class.cast(annotation);

			return renderMethod.portletNames();
		},
		annotation -> {
			RenderMethod renderMethod = RenderMethod.class.cast(annotation);

			return renderMethod.ordinal();
		},
		annotation -> {
			RenderMethod renderMethod = RenderMethod.class.cast(annotation);

			return renderMethod.include();
		}),
	SERVE_RESOURCE(
		ServeResourceMethod.class,
		new Class<?>[] {ResourceRequest.class, ResourceResponse.class}, true,
		true,
		annotation -> {
			ServeResourceMethod serveResourceMethod =
				ServeResourceMethod.class.cast(annotation);

			return serveResourceMethod.characterEncoding();
		},
		annotation -> {
			ServeResourceMethod serveResourceMethod =
				ServeResourceMethod.class.cast(annotation);

			return serveResourceMethod.contentType();
		},
		annotation -> PortletMode.VIEW,
		annotation -> {
			ServeResourceMethod serveResourceMethod =
				ServeResourceMethod.class.cast(annotation);

			return serveResourceMethod.portletNames();
		},
		annotation -> {
			ServeResourceMethod serveResourceMethod =
				ServeResourceMethod.class.cast(annotation);

			return serveResourceMethod.ordinal();
		},
		annotation -> {
			ServeResourceMethod serveResourceMethod =
				ServeResourceMethod.class.cast(annotation);

			return serveResourceMethod.include();
		});

	public String getCharacterEncoding(Method method) {
		Annotation annotation = method.getAnnotation(_annotation);

		if (annotation == null) {
			return null;
		}

		return _characterEncodingFunction.apply(annotation);
	}

	public String getContentType(Method method) {
		Annotation annotation = method.getAnnotation(_annotation);

		if (annotation == null) {
			return null;
		}

		return _contentTypeFunction.apply(annotation);
	}

	public String getInclude(Method method) {
		Annotation annotation = method.getAnnotation(_annotation);

		if (annotation == null) {
			return null;
		}

		return _includeFunction.apply(annotation);
	}

	public int getOrdinal(Method method) {
		Annotation annotation = method.getAnnotation(_annotation);

		if (annotation == null) {
			return 0;
		}

		return _ordinalFunction.apply(annotation);
	}

	public PortletMode getPortletMode(Method method) {
		Annotation annotation = method.getAnnotation(_annotation);

		if ((annotation == null) || (_portletModeFunction == null)) {
			return null;
		}

		return _portletModeFunction.apply(annotation);
	}

	public String[] getPortletNames(Method method) {
		Annotation annotation = method.getAnnotation(_annotation);

		if (annotation == null) {
			return null;
		}

		return _portletNamesFunction.apply(annotation);
	}

	public boolean isMatch(Method method) {
		if (!method.isAnnotationPresent(_annotation)) {
			return false;
		}

		Class<?> returnType = method.getReturnType();

		Class<?>[] parameterTypes = method.getParameterTypes();

		// Exact match

		if (returnType.equals(Void.TYPE) && _isAssignableFrom(parameterTypes)) {
			return true;
		}

		// Variant match

		if (_variant && (parameterTypes.length == 0) &&
			(returnType.equals(Void.TYPE) || returnType.equals(String.class))) {

			return true;
		}

		// Controller match

		if (_controller &&
			((parameterTypes.length == 0) ||
			 _isAssignableFrom(parameterTypes))) {

			return true;
		}

		_log.error(
			StringBundler.concat(
				"Method ", method, " does not have a valid signature for @",
				_annotation.getSimpleName()));

		return false;
	}

	private BeanPortletMethodType(
		Class<? extends Annotation> annotation, Class<?>[] parameterTypes,
		boolean variant, boolean controller,
		Function<Annotation, String> characterEncodingFunction,
		Function<Annotation, String> contentTypeFunction,
		Function<Annotation, PortletMode> portletModeFunction,
		Function<Annotation, String[]> portletNamesFunction,
		Function<Annotation, Integer> ordinalFunction,
		Function<Annotation, String> includeFunction) {

		_annotation = annotation;
		_parameterTypes = parameterTypes;
		_variant = variant;
		_controller = controller;
		_characterEncodingFunction = characterEncodingFunction;
		_contentTypeFunction = contentTypeFunction;
		_portletModeFunction = portletModeFunction;
		_portletNamesFunction = portletNamesFunction;
		_ordinalFunction = ordinalFunction;
		_includeFunction = includeFunction;
	}

	private boolean _isAssignableFrom(Class<?>[] parameterTypes) {
		if (parameterTypes.length == _parameterTypes.length) {
			for (int i = 0; i < parameterTypes.length; i++) {
				if (!_parameterTypes[i].isAssignableFrom(parameterTypes[i])) {
					return false;
				}
			}

			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BeanPortletMethodType.class);

	private final Class<? extends Annotation> _annotation;
	private final Function<Annotation, String> _characterEncodingFunction;
	private final Function<Annotation, String> _contentTypeFunction;
	private final boolean _controller;
	private final Function<Annotation, String> _includeFunction;
	private final Function<Annotation, Integer> _ordinalFunction;
	private final Class<?>[] _parameterTypes;
	private final Function<Annotation, PortletMode> _portletModeFunction;
	private final Function<Annotation, String[]> _portletNamesFunction;
	private final boolean _variant;

}