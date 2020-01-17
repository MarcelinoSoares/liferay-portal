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

package com.liferay.bean.portlet.spring.extension.internal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;

/**
 * @author Neil Griffin
 */
public class JSR330DependencyDescriptor extends DependencyDescriptor {

	public JSR330DependencyDescriptor(
		DependencyDescriptor original, String beanName, Class<?> requiredType) {

		super(original);

		_beanName = beanName;
		_requiredType = requiredType;
	}

	@Override
	public Object resolveShortcut(BeanFactory beanFactory) {
		return beanFactory.getBean(_beanName, _requiredType);
	}

	private static final long serialVersionUID = 1L;

	private final String _beanName;
	private final Class<?> _requiredType;

}