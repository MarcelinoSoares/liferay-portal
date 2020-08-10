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

package com.liferay.commerce.pricing.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CommercePricingClassCPDefinitionRel service. Represents a row in the &quot;CPricingClassCPDefinitionRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.pricing.model.impl.CommercePricingClassCPDefinitionRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.pricing.model.impl.CommercePricingClassCPDefinitionRelImpl</code>.
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommercePricingClassCPDefinitionRel
 * @generated
 */
@ProviderType
public interface CommercePricingClassCPDefinitionRelModel
	extends AuditedModel, BaseModel<CommercePricingClassCPDefinitionRel>,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce pricing class cp definition rel model instance should use the {@link CommercePricingClassCPDefinitionRel} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce pricing class cp definition rel.
	 *
	 * @return the primary key of this commerce pricing class cp definition rel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce pricing class cp definition rel.
	 *
	 * @param primaryKey the primary key of this commerce pricing class cp definition rel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the commerce pricing class cp definition rel ID of this commerce pricing class cp definition rel.
	 *
	 * @return the commerce pricing class cp definition rel ID of this commerce pricing class cp definition rel
	 */
	public long getCommercePricingClassCPDefinitionRelId();

	/**
	 * Sets the commerce pricing class cp definition rel ID of this commerce pricing class cp definition rel.
	 *
	 * @param CommercePricingClassCPDefinitionRelId the commerce pricing class cp definition rel ID of this commerce pricing class cp definition rel
	 */
	public void setCommercePricingClassCPDefinitionRelId(
		long CommercePricingClassCPDefinitionRelId);

	/**
	 * Returns the company ID of this commerce pricing class cp definition rel.
	 *
	 * @return the company ID of this commerce pricing class cp definition rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce pricing class cp definition rel.
	 *
	 * @param companyId the company ID of this commerce pricing class cp definition rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce pricing class cp definition rel.
	 *
	 * @return the user ID of this commerce pricing class cp definition rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce pricing class cp definition rel.
	 *
	 * @param userId the user ID of this commerce pricing class cp definition rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce pricing class cp definition rel.
	 *
	 * @return the user uuid of this commerce pricing class cp definition rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce pricing class cp definition rel.
	 *
	 * @param userUuid the user uuid of this commerce pricing class cp definition rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce pricing class cp definition rel.
	 *
	 * @return the user name of this commerce pricing class cp definition rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce pricing class cp definition rel.
	 *
	 * @param userName the user name of this commerce pricing class cp definition rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce pricing class cp definition rel.
	 *
	 * @return the create date of this commerce pricing class cp definition rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce pricing class cp definition rel.
	 *
	 * @param createDate the create date of this commerce pricing class cp definition rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce pricing class cp definition rel.
	 *
	 * @return the modified date of this commerce pricing class cp definition rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce pricing class cp definition rel.
	 *
	 * @param modifiedDate the modified date of this commerce pricing class cp definition rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce pricing class ID of this commerce pricing class cp definition rel.
	 *
	 * @return the commerce pricing class ID of this commerce pricing class cp definition rel
	 */
	public long getCommercePricingClassId();

	/**
	 * Sets the commerce pricing class ID of this commerce pricing class cp definition rel.
	 *
	 * @param commercePricingClassId the commerce pricing class ID of this commerce pricing class cp definition rel
	 */
	public void setCommercePricingClassId(long commercePricingClassId);

	/**
	 * Returns the cp definition ID of this commerce pricing class cp definition rel.
	 *
	 * @return the cp definition ID of this commerce pricing class cp definition rel
	 */
	public long getCPDefinitionId();

	/**
	 * Sets the cp definition ID of this commerce pricing class cp definition rel.
	 *
	 * @param CPDefinitionId the cp definition ID of this commerce pricing class cp definition rel
	 */
	public void setCPDefinitionId(long CPDefinitionId);

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
		CommercePricingClassCPDefinitionRel
			commercePricingClassCPDefinitionRel);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommercePricingClassCPDefinitionRel> toCacheModel();

	@Override
	public CommercePricingClassCPDefinitionRel toEscapedModel();

	@Override
	public CommercePricingClassCPDefinitionRel toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}