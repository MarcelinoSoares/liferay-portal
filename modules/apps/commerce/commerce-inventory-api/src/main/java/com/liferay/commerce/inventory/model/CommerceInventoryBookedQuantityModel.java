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

package com.liferay.commerce.inventory.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CommerceInventoryBookedQuantity service. Represents a row in the &quot;CIBookedQuantity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryBookedQuantityModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryBookedQuantityImpl</code>.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryBookedQuantity
 * @generated
 */
@ProviderType
public interface CommerceInventoryBookedQuantityModel
	extends AuditedModel, BaseModel<CommerceInventoryBookedQuantity>, MVCCModel,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce inventory booked quantity model instance should use the {@link CommerceInventoryBookedQuantity} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce inventory booked quantity.
	 *
	 * @return the primary key of this commerce inventory booked quantity
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce inventory booked quantity.
	 *
	 * @param primaryKey the primary key of this commerce inventory booked quantity
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce inventory booked quantity.
	 *
	 * @return the mvcc version of this commerce inventory booked quantity
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce inventory booked quantity.
	 *
	 * @param mvccVersion the mvcc version of this commerce inventory booked quantity
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the commerce inventory booked quantity ID of this commerce inventory booked quantity.
	 *
	 * @return the commerce inventory booked quantity ID of this commerce inventory booked quantity
	 */
	public long getCommerceInventoryBookedQuantityId();

	/**
	 * Sets the commerce inventory booked quantity ID of this commerce inventory booked quantity.
	 *
	 * @param commerceInventoryBookedQuantityId the commerce inventory booked quantity ID of this commerce inventory booked quantity
	 */
	public void setCommerceInventoryBookedQuantityId(
		long commerceInventoryBookedQuantityId);

	/**
	 * Returns the company ID of this commerce inventory booked quantity.
	 *
	 * @return the company ID of this commerce inventory booked quantity
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce inventory booked quantity.
	 *
	 * @param companyId the company ID of this commerce inventory booked quantity
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce inventory booked quantity.
	 *
	 * @return the user ID of this commerce inventory booked quantity
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce inventory booked quantity.
	 *
	 * @param userId the user ID of this commerce inventory booked quantity
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce inventory booked quantity.
	 *
	 * @return the user uuid of this commerce inventory booked quantity
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce inventory booked quantity.
	 *
	 * @param userUuid the user uuid of this commerce inventory booked quantity
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce inventory booked quantity.
	 *
	 * @return the user name of this commerce inventory booked quantity
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce inventory booked quantity.
	 *
	 * @param userName the user name of this commerce inventory booked quantity
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce inventory booked quantity.
	 *
	 * @return the create date of this commerce inventory booked quantity
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce inventory booked quantity.
	 *
	 * @param createDate the create date of this commerce inventory booked quantity
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce inventory booked quantity.
	 *
	 * @return the modified date of this commerce inventory booked quantity
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce inventory booked quantity.
	 *
	 * @param modifiedDate the modified date of this commerce inventory booked quantity
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the sku of this commerce inventory booked quantity.
	 *
	 * @return the sku of this commerce inventory booked quantity
	 */
	@AutoEscape
	public String getSku();

	/**
	 * Sets the sku of this commerce inventory booked quantity.
	 *
	 * @param sku the sku of this commerce inventory booked quantity
	 */
	public void setSku(String sku);

	/**
	 * Returns the quantity of this commerce inventory booked quantity.
	 *
	 * @return the quantity of this commerce inventory booked quantity
	 */
	public int getQuantity();

	/**
	 * Sets the quantity of this commerce inventory booked quantity.
	 *
	 * @param quantity the quantity of this commerce inventory booked quantity
	 */
	public void setQuantity(int quantity);

	/**
	 * Returns the expiration date of this commerce inventory booked quantity.
	 *
	 * @return the expiration date of this commerce inventory booked quantity
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this commerce inventory booked quantity.
	 *
	 * @param expirationDate the expiration date of this commerce inventory booked quantity
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the booked note of this commerce inventory booked quantity.
	 *
	 * @return the booked note of this commerce inventory booked quantity
	 */
	@AutoEscape
	public String getBookedNote();

	/**
	 * Sets the booked note of this commerce inventory booked quantity.
	 *
	 * @param bookedNote the booked note of this commerce inventory booked quantity
	 */
	public void setBookedNote(String bookedNote);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceInventoryBookedQuantity> toCacheModel();

	@Override
	public CommerceInventoryBookedQuantity toEscapedModel();

	@Override
	public CommerceInventoryBookedQuantity toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}