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

package com.liferay.sharing.document.library.internal.renderer;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.sharing.renderer.SharingEntryEditRenderer;

import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sergio González
 */
@Component(service = DLFileEntrySharingEntryEditRenderer.class)
public class DLFileEntrySharingEntryEditRenderer
	implements SharingEntryEditRenderer<FileEntry> {

	@Override
	public PortletURL getURLEdit(
			FileEntry fileEntry, LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException {

		AssetRendererFactory<DLFileEntry> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClass(
				DLFileEntry.class);

		AssetRenderer<DLFileEntry> assetRenderer =
			assetRendererFactory.getAssetRenderer(fileEntry.getFileEntryId());

		try {
			return assetRenderer.getURLEdit(
				liferayPortletRequest, liferayPortletResponse);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

}