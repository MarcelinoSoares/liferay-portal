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

package com.liferay.portal.upgrade.util.test;

/**
 * @author Alicia García
 */
public class UpgradeMVCCVersionModuleTestTableClass {

	public static final String TABLE_NAME = "UpgradeMVCCVersionModuleTest";

	public static final String TABLE_SQL_CREATE =
		"create table UpgradeMVCCVersionModuleTest(_id LONG not null primary " +
			"key, _userId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table UpgradeMVCCVersionModuleTest";

}