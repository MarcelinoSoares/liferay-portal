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

package com.liferay.app.builder.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the AppBuilderAppDeployment service. Represents a row in the &quot;AppBuilderAppDeployment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.app.builder.model.impl.AppBuilderAppDeploymentModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.app.builder.model.impl.AppBuilderAppDeploymentImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppBuilderAppDeployment
 * @generated
 */
@ProviderType
public interface AppBuilderAppDeploymentModel
	extends BaseModel<AppBuilderAppDeployment> {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a app builder app deployment model instance should use the {@link AppBuilderAppDeployment} interface instead.
	 */

	/**
	 * Returns the primary key of this app builder app deployment.
	 *
	 * @return the primary key of this app builder app deployment
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this app builder app deployment.
	 *
	 * @param primaryKey the primary key of this app builder app deployment
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the app builder app deployment ID of this app builder app deployment.
	 *
	 * @return the app builder app deployment ID of this app builder app deployment
	 */
	public long getAppBuilderAppDeploymentId();

	/**
	 * Sets the app builder app deployment ID of this app builder app deployment.
	 *
	 * @param appBuilderAppDeploymentId the app builder app deployment ID of this app builder app deployment
	 */
	public void setAppBuilderAppDeploymentId(long appBuilderAppDeploymentId);

	/**
	 * Returns the app builder app ID of this app builder app deployment.
	 *
	 * @return the app builder app ID of this app builder app deployment
	 */
	public long getAppBuilderAppId();

	/**
	 * Sets the app builder app ID of this app builder app deployment.
	 *
	 * @param appBuilderAppId the app builder app ID of this app builder app deployment
	 */
	public void setAppBuilderAppId(long appBuilderAppId);

	/**
	 * Returns the settings of this app builder app deployment.
	 *
	 * @return the settings of this app builder app deployment
	 */
	@AutoEscape
	public String getSettings();

	/**
	 * Sets the settings of this app builder app deployment.
	 *
	 * @param settings the settings of this app builder app deployment
	 */
	public void setSettings(String settings);

	/**
	 * Returns the type of this app builder app deployment.
	 *
	 * @return the type of this app builder app deployment
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this app builder app deployment.
	 *
	 * @param type the type of this app builder app deployment
	 */
	public void setType(String type);

}