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

package com.liferay.change.tracking.display;

import com.liferay.change.tracking.display.context.DisplayContext;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.InputStream;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Samuel Trong Tran
 */
public interface CTDisplayRenderer<T> {

	public default InputStream getDownloadInputStream(T model, String key)
		throws PortalException {

		return null;
	}

	public String getEditURL(HttpServletRequest httpServletRequest, T model)
		throws Exception;

	public Class<T> getModelClass();

	public String getTitle(Locale locale, T model) throws PortalException;

	public String getTypeName(Locale locale);

	public void render(DisplayContext<T> displayContext) throws Exception;

}