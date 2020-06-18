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

package com.liferay.frontend.taglib.clay.data.set.view.table;

/**
 * @author Marco Leo
 */
public interface ClayTableSchemaBuilder {
// See, this is a real builder
	public void addField(ClayTableSchemaField clayTableSchemaField);

	public ClayTableSchemaField addField(String fieldName);

	public ClayTableSchemaField addField(String fieldName, String label);

	public ClayTableSchema build();

	public void removeField(String fieldName);

	public void setClayTableSchema(ClayTableSchema clayTableSchema);

}