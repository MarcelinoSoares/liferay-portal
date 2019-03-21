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

package com.liferay.asset.list.service.impl;

import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.model.AssetListEntrySegmentsEntryRel;
import com.liferay.asset.list.service.base.AssetListEntrySegmentsEntryRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;

import java.util.Date;
import java.util.List;

/**
 * @author Eduardo García
 */
public class AssetListEntrySegmentsEntryRelLocalServiceImpl
	extends AssetListEntrySegmentsEntryRelLocalServiceBaseImpl {

	@Override
	public AssetListEntrySegmentsEntryRel addAssetListEntrySegmentsEntryRel(
			long assetListEntryId, long segmentsEntryId, String typeSettings,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		long assetListEntrySegmentsEntryRelId = counterLocalService.increment();

		AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel =
			assetListEntrySegmentsEntryRelPersistence.create(
				assetListEntrySegmentsEntryRelId);

		assetListEntrySegmentsEntryRel.setUuid(serviceContext.getUuid());
		assetListEntrySegmentsEntryRel.setGroupId(
			serviceContext.getScopeGroupId());
		assetListEntrySegmentsEntryRel.setCompanyId(
			serviceContext.getCompanyId());
		assetListEntrySegmentsEntryRel.setUserId(serviceContext.getUserId());
		assetListEntrySegmentsEntryRel.setUserName(user.getFullName());
		assetListEntrySegmentsEntryRel.setCreateDate(
			serviceContext.getCreateDate(new Date()));
		assetListEntrySegmentsEntryRel.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		assetListEntrySegmentsEntryRel.setAssetListEntryId(assetListEntryId);
		assetListEntrySegmentsEntryRel.setSegmentsEntryId(segmentsEntryId);
		assetListEntrySegmentsEntryRel.setTypeSettings(typeSettings);

		return assetListEntrySegmentsEntryRelPersistence.update(
			assetListEntrySegmentsEntryRel);
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public AssetListEntrySegmentsEntryRel deleteAssetListEntrySegmentsEntryRel(
			AssetListEntry assetListEntry)
		throws PortalException {

		return assetListEntrySegmentsEntryRelPersistence.remove(assetListEntry);
	}

	@Override
	public void deleteAssetListEntrySegmentsEntryRel(
			long assetListEntryId, long segmentsEntryId)
		throws PortalException {

		assetListEntrySegmentsEntryRelPersistence.removeByA_S(
			assetListEntryId, segmentsEntryId);
	}

	@Override
	public void deleteAssetListEntrySegmentsEntryRelByAssetListEntryId(
		long assetListEntryId) {

		assetListEntrySegmentsEntryRelPersistence.removeByAssetListEntryId(
			assetListEntryId);
	}

	@Override
	public AssetListEntrySegmentsEntryRel fetchAssetListEntrySegmentsEntryRel(
		long assetListEntryId, long segmentsEntryId) {

		return assetListEntrySegmentsEntryRelPersistence.fetchByA_S(
			assetListEntryId, segmentsEntryId);
	}

	@Override
	public AssetListEntrySegmentsEntryRel getAssetListEntrySegmentsEntryRel(
			long assetListEntryId, long segmentsEntryId)
		throws PortalException {

		return assetListEntrySegmentsEntryRelPersistence.findByA_S(
			assetListEntryId, segmentsEntryId);
	}

	@Override
	public List<AssetListEntrySegmentsEntryRel>
		getAssetListEntrySegmentsEntryRels(
			long assetListEntryId, int start, int end) {

		return assetListEntrySegmentsEntryRelPersistence.findByAssetListEntryId(
			assetListEntryId, start, end);
	}

	@Override
	public int getAssetListEntrySegmentsEntryRelsCount(long assetListEntryId) {
		return assetListEntrySegmentsEntryRelPersistence.
			countByAssetListEntryId(assetListEntryId);
	}

	@Override
	public AssetListEntrySegmentsEntryRel
		updateAssetListEntrySegmentsEntryRelTypeSettings(
			long assetListEntryId, long segmentsEntryId, String typeSettings) {

		AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel =
			assetListEntrySegmentsEntryRelPersistence.fetchByA_S(
				assetListEntryId, segmentsEntryId);

		assetListEntrySegmentsEntryRel.setModifiedDate(new Date());
		assetListEntrySegmentsEntryRel.setTypeSettings(typeSettings);

		return assetListEntrySegmentsEntryRelPersistence.update(
			assetListEntrySegmentsEntryRel);
	}

}