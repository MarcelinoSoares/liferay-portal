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

import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.UriInfo;

import org.springframework.context.ApplicationEvent;

/**
 * @author  Neil Griffin
 */
public class BaseControllerEventImpl extends ApplicationEvent {

	public BaseControllerEventImpl(
		Object source, ResourceInfo resourceInfo, UriInfo uriInfo) {

		super(source);

		_resourceInfo = resourceInfo;
		_uriInfo = uriInfo;
	}

	public ResourceInfo getResourceInfo() {
		return _resourceInfo;
	}

	public UriInfo getUriInfo() {
		return _uriInfo;
	}

	private final ResourceInfo _resourceInfo;
	private final UriInfo _uriInfo;

}