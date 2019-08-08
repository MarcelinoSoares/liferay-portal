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

package com.liferay.layout.content.page.editor.web.internal.util;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.service.AssetEntryUsageLocalServiceUtil;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalServiceUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.asset.service.permission.AssetEntryPermission;
import com.liferay.taglib.security.PermissionsURLTag;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Víctor Galán
 */
public class MappedContentUtil {

	public static JSONArray getMappedContentsJSONArray(
			long groupId, long layoutClassNameId, long layoutClassPK,
			HttpServletRequest httpServletRequest)
		throws Exception {

		JSONArray mappedContentsJSONArray = JSONFactoryUtil.createJSONArray();

		Set<AssetEntry> assetEntries = _getMappedAssetEntries(
			groupId, layoutClassNameId, layoutClassPK);

		for (AssetEntry assetEntry : assetEntries) {
			mappedContentsJSONArray.put(
				_getMappedContentJSONObject(assetEntry, httpServletRequest));
		}

		return mappedContentsJSONArray;
	}

	private static JSONObject _getActionsJSONObject(
			AssetEntry assetEntry, ThemeDisplay themeDisplay,
			HttpServletRequest httpServletRequest)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				assetEntry.getClassName());

		AssetRenderer<?> assetRenderer = assetRendererFactory.getAssetRenderer(
			assetEntry.getClassPK());

		if (AssetEntryPermission.contains(
				themeDisplay.getPermissionChecker(), assetEntry,
				ActionKeys.UPDATE)) {

			PortletURL portletURL = assetRenderer.getURLEdit(
				httpServletRequest, LiferayWindowState.NORMAL,
				themeDisplay.getURLCurrent());

			jsonObject.put("editURL", portletURL.toString());
		}

		if (AssetEntryPermission.contains(
				themeDisplay.getPermissionChecker(), assetEntry,
				ActionKeys.PERMISSIONS)) {

			String permissionsURL = PermissionsURLTag.doTag(
				StringPool.BLANK, assetEntry.getClassName(),
				HtmlUtil.escape(assetEntry.getTitle(themeDisplay.getLocale())),
				null, String.valueOf(assetEntry.getClassPK()),
				LiferayWindowState.POP_UP.toString(), null, httpServletRequest);

			jsonObject.put("permissionsURL", permissionsURL);
		}

		if (AssetEntryPermission.contains(
				themeDisplay.getPermissionChecker(), assetEntry,
				ActionKeys.VIEW)) {

			String viewUsagesURL = assetRenderer.getURLViewUsages(
				httpServletRequest);

			viewUsagesURL = HttpUtil.setParameter(
				viewUsagesURL, "p_p_state",
				LiferayWindowState.POP_UP.toString());

			jsonObject.put("viewUsagesURL", viewUsagesURL);
		}

		return jsonObject;
	}

	private static JSONObject _getJournalArticleStatusJSONObject(long classPK)
		throws PortalException {

		JournalArticle journalArticle =
			JournalArticleServiceUtil.getLatestArticle(classPK);

		return JSONUtil.put(
			"label",
			WorkflowConstants.getStatusLabel(journalArticle.getStatus())
		).put(
			"style",
			LabelItem.getStyleFromWorkflowStatus(journalArticle.getStatus())
		);
	}

	private static Set<AssetEntry> _getMappedAssetEntries(
			long groupId, long layoutClassNameId, long layoutClassPK)
		throws PortalException {

		Set<AssetEntry> assetEntries = new HashSet<>();

		Set<Long> mappedClassPKs = new HashSet<>();

		List<FragmentEntryLink> fragmentEntryLinks =
			FragmentEntryLinkLocalServiceUtil.getFragmentEntryLinks(
				groupId, layoutClassNameId, layoutClassPK);

		for (FragmentEntryLink fragmentEntryLink : fragmentEntryLinks) {
			JSONObject editableValuesJSONObject =
				JSONFactoryUtil.createJSONObject(
					fragmentEntryLink.getEditableValues());

			Iterator<String> keysIterator = editableValuesJSONObject.keys();

			while (keysIterator.hasNext()) {
				String key = keysIterator.next();

				JSONObject editableProcessorJSONObject =
					editableValuesJSONObject.getJSONObject(key);

				if (editableProcessorJSONObject == null) {
					continue;
				}

				Iterator<String> editableKeysIterator =
					editableProcessorJSONObject.keys();

				while (editableKeysIterator.hasNext()) {
					String editableKey = editableKeysIterator.next();

					JSONObject editableJSONObject =
						editableProcessorJSONObject.getJSONObject(editableKey);

					if (!editableJSONObject.has("classNameId") ||
						!editableJSONObject.has("classPK") ||
						!editableJSONObject.has("fieldId")) {

						continue;
					}

					long classPK = editableJSONObject.getLong("classPK");

					if (mappedClassPKs.contains(classPK)) {
						continue;
					}

					mappedClassPKs.add(classPK);

					long classNameId = editableJSONObject.getLong(
						"classNameId");

					if (classNameId != PortalUtil.getClassNameId(
							JournalArticle.class)) {

						continue;
					}

					AssetEntry assetEntry = AssetEntryServiceUtil.getEntry(
						PortalUtil.getClassName(classNameId), classPK);

					if (assetEntry == null) {
						continue;
					}

					assetEntries.add(assetEntry);
				}
			}
		}

		return assetEntries;
	}

	private static JSONObject _getMappedContentJSONObject(
			AssetEntry assetEntry, HttpServletRequest httpServletRequest)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return JSONUtil.put(
			"className", assetEntry.getClassName()
		).put(
			"classNameId", assetEntry.getClassNameId()
		).put(
			"classPK", assetEntry.getClassPK()
		).put(
			"actions",
			_getActionsJSONObject(assetEntry, themeDisplay, httpServletRequest)
		).put(
			"name",
			ResourceActionsUtil.getModelResource(
				themeDisplay.getLocale(), assetEntry.getClassName())
		).put(
			"status",
			_getJournalArticleStatusJSONObject(assetEntry.getClassPK())
		).put(
			"title", assetEntry.getTitle(themeDisplay.getLocale())
		).put(
			"usagesCount",
			AssetEntryUsageLocalServiceUtil.getAssetEntryUsagesCount(
				assetEntry.getEntryId())
		);
	}

}