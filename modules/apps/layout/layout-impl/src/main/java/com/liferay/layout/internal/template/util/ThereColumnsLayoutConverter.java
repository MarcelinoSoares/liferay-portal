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

package com.liferay.layout.internal.template.util;

import com.liferay.layout.util.template.LayoutConverter;
import com.liferay.layout.util.template.LayoutData;
import com.liferay.layout.util.template.LayoutTypeSettingsInspectorUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortletConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true, property = "layout.template.id=3_columns",
	service = LayoutConverter.class
)
public class ThereColumnsLayoutConverter implements LayoutConverter {

	@Override
	public LayoutData convert(Layout layout) {
		return new LayoutData(layout) {
			{
				addLayoutRow(
					layoutRow -> {
						layoutRow.addLayoutColumn(
							layoutColumn -> {
								layoutColumn.addPortletsByColumnId(
									LayoutTypePortletConstants.COLUMN_PREFIX +
										1);
								layoutColumn.setSize(4);
							});

						layoutRow.addLayoutColumn(
							layoutColumn -> {
								layoutColumn.addPortletsByColumnId(
									LayoutTypePortletConstants.COLUMN_PREFIX +
										2);
								layoutColumn.setSize(4);
							});

						layoutRow.addLayoutColumn(
							layoutColumn -> {
								layoutColumn.addPortletsByColumnId(
									LayoutTypePortletConstants.COLUMN_PREFIX +
										3);
								layoutColumn.setSize(4);
							});
					});
			}
		};
	}

	@Override
	public boolean isConvertible(Layout layout) {
		if (LayoutTypeSettingsInspectorUtil.hasNestedPortletsPortlet(
				layout.getTypeSettingsProperties())) {

			return false;
		}

		if (LayoutTypeSettingsInspectorUtil.isCustomizableLayout(
				layout.getTypeSettingsProperties())) {

			return false;
		}

		return true;
	}

}