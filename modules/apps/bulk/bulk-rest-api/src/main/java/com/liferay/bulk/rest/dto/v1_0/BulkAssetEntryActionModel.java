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

package com.liferay.bulk.rest.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@GraphQLName("BulkAssetEntryActionModel")
@XmlRootElement(name = "BulkAssetEntryActionModel")
public class BulkAssetEntryActionModel {

	public Long getFolderId() {
		return folderId;
	}

	public Long getRepositoryId() {
		return repositoryId;
	}

	public Boolean getSelectAll() {
		return selectAll;
	}

	public String[] getSelection() {
		return selection;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	@JsonIgnore
	public void setFolderId(
		UnsafeSupplier<Long, Exception> folderIdUnsafeSupplier) {

		try {
			folderId = folderIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setRepositoryId(Long repositoryId) {
		this.repositoryId = repositoryId;
	}

	@JsonIgnore
	public void setRepositoryId(
		UnsafeSupplier<Long, Exception> repositoryIdUnsafeSupplier) {

		try {
			repositoryId = repositoryIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setSelectAll(Boolean selectAll) {
		this.selectAll = selectAll;
	}

	@JsonIgnore
	public void setSelectAll(
		UnsafeSupplier<Boolean, Exception> selectAllUnsafeSupplier) {

		try {
			selectAll = selectAllUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setSelection(String[] selection) {
		this.selection = selection;
	}

	@JsonIgnore
	public void setSelection(
		UnsafeSupplier<String[], Exception> selectionUnsafeSupplier) {

		try {
			selection = selectionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{");

		sb.append("\"folderId\": ");

		sb.append(folderId);
		sb.append(", ");

		sb.append("\"repositoryId\": ");

		sb.append(repositoryId);
		sb.append(", ");

		sb.append("\"selectAll\": ");

		sb.append(selectAll);
		sb.append(", ");

		sb.append("\"selection\": ");

		sb.append("\"");
		sb.append(selection);
		sb.append("\"");

		sb.append("}");

		return sb.toString();
	}

	@GraphQLField
	@JsonProperty
	protected Long folderId;

	@GraphQLField
	@JsonProperty
	protected Long repositoryId;

	@GraphQLField
	@JsonProperty
	protected Boolean selectAll;

	@GraphQLField
	@JsonProperty
	protected String[] selection;

}