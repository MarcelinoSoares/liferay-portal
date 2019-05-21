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
import com.liferay.bean.portlet.extension.BeanFilterMethodFactory;

import java.lang.reflect.Method;

import javax.enterprise.inject.spi.BeanManager;

/**
 * @author Neil Griffin
 */
public class CDIBeanFilterMethodFactory implements BeanFilterMethodFactory {

	public CDIBeanFilterMethodFactory(BeanManager beanManager) {
		_beanManager = beanManager;
	}

	@Override
	public BeanFilterMethod create(Method method, Class<?> beanType) {
		return new CDIBeanFilterMethod(_beanManager, method, beanType);
	}

	private final BeanManager _beanManager;

}