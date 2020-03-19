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

package com.liferay.change.tracking.internal.reference;

import com.liferay.change.tracking.reference.TableReferenceDefinition;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.Table;

import java.util.List;
import java.util.Map;

/**
 * @author Preston Crary
 */
public class TableReferenceInfo<T extends Table<T>> {

	public TableReferenceInfo(
		TableReferenceDefinition<T> tableReferenceDefinition,
		Column<T, Long> primaryKeyColumn,
		Map<Table<?>, List<TableJoinHolder>> parentJoinMap,
		Map<Table<?>, List<TableJoinHolder>> childJoinMap) {

		_tableReferenceDefinition = tableReferenceDefinition;
		_primaryKeyColumn = primaryKeyColumn;
		_parentJoinMap = parentJoinMap;
		_childJoinMap = childJoinMap;
	}

	public Map<Table<?>, List<TableJoinHolder>> getChildJoinHoldersMap() {
		return _childJoinMap;
	}

	public Map<Table<?>, List<TableJoinHolder>> getParentJoinHoldersMap() {
		return _parentJoinMap;
	}

	public Column<T, Long> getPrimaryKeyColumn() {
		return _primaryKeyColumn;
	}

	public TableReferenceDefinition<T> getTableReferenceDefinition() {
		return _tableReferenceDefinition;
	}

	private final Map<Table<?>, List<TableJoinHolder>> _childJoinMap;
	private final Map<Table<?>, List<TableJoinHolder>> _parentJoinMap;
	private final Column<T, Long> _primaryKeyColumn;
	private final TableReferenceDefinition<T> _tableReferenceDefinition;

}