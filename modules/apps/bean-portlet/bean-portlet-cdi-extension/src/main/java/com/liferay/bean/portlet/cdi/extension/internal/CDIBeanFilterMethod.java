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

package com.liferay.bean.portlet.cdi.extension.internal;

import com.liferay.bean.portlet.extension.BeanFilterMethod;

import java.lang.reflect.Method;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

/**
 * @author Neil Griffin
 */
public class CDIBeanFilterMethod implements BeanFilterMethod {

	public CDIBeanFilterMethod(
		BeanManager beanManager, Method method, Class<?> beanType) {

		_beanManager = beanManager;
		_method = method;
		_beanType = beanType;
	}

	@Override
	public Object invoke(Object... args) throws ReflectiveOperationException {
		Set<Bean<?>> beans = _beanManager.getBeans(_beanType);

		Bean<?> resolvedBean = _beanManager.resolve(beans);

		CreationalContext<?> creationalContext =
			_beanManager.createCreationalContext(resolvedBean);

		Object beanInstance = _beanManager.getReference(
			resolvedBean, _beanType, creationalContext);

		return _method.invoke(beanInstance, args);
	}

	private final BeanManager _beanManager;
	private final Class<?> _beanType;
	private final Method _method;

}