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

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceRegionModel;
import com.liferay.commerce.model.CommerceRegionSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceRegion service. Represents a row in the &quot;CommerceRegion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceRegionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceRegionImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceRegionImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceRegionModelImpl
	extends BaseModelImpl<CommerceRegion> implements CommerceRegionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce region model instance should use the <code>CommerceRegion</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceRegion";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"commerceRegionId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceCountryId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"code_", Types.VARCHAR},
		{"priority", Types.DOUBLE}, {"active_", Types.BOOLEAN},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceRegionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceCountryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("code_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("priority", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceRegion (uuid_ VARCHAR(75) null,commerceRegionId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceCountryId LONG,name VARCHAR(75) null,code_ VARCHAR(75) null,priority DOUBLE,active_ BOOLEAN,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table CommerceRegion";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceRegion.priority ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceRegion.priority ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.model.CommerceRegion"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.model.CommerceRegion"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.model.CommerceRegion"),
		true);

	public static final long ACTIVE_COLUMN_BITMASK = 1L;

	public static final long CODE_COLUMN_BITMASK = 2L;

	public static final long COMMERCECOUNTRYID_COLUMN_BITMASK = 4L;

	public static final long COMPANYID_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

	public static final long PRIORITY_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceRegion toModel(CommerceRegionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceRegion model = new CommerceRegionImpl();

		model.setUuid(soapModel.getUuid());
		model.setCommerceRegionId(soapModel.getCommerceRegionId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceCountryId(soapModel.getCommerceCountryId());
		model.setName(soapModel.getName());
		model.setCode(soapModel.getCode());
		model.setPriority(soapModel.getPriority());
		model.setActive(soapModel.isActive());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceRegion> toModels(
		CommerceRegionSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceRegion> models = new ArrayList<CommerceRegion>(
			soapModels.length);

		for (CommerceRegionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.model.CommerceRegion"));

	public CommerceRegionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceRegionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceRegionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceRegionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceRegion.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceRegion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceRegion, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceRegion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceRegion, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceRegion)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceRegion, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceRegion, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceRegion)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceRegion, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceRegion, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceRegion>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceRegion.class.getClassLoader(), CommerceRegion.class,
			ModelWrapper.class);

		try {
			Constructor<CommerceRegion> constructor =
				(Constructor<CommerceRegion>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<CommerceRegion, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceRegion, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceRegion, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<CommerceRegion, Object>>();
		Map<String, BiConsumer<CommerceRegion, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CommerceRegion, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion, Object uuidObject) {

					commerceRegion.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"commerceRegionId",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getCommerceRegionId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceRegionId",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion,
					Object commerceRegionIdObject) {

					commerceRegion.setCommerceRegionId(
						(Long)commerceRegionIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion, Object companyIdObject) {

					commerceRegion.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion, Object userIdObject) {

					commerceRegion.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion, Object userNameObject) {

					commerceRegion.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion, Object createDateObject) {

					commerceRegion.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion, Object modifiedDateObject) {

					commerceRegion.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"commerceCountryId",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getCommerceCountryId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceCountryId",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion,
					Object commerceCountryIdObject) {

					commerceRegion.setCommerceCountryId(
						(Long)commerceCountryIdObject);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion, Object nameObject) {

					commerceRegion.setName((String)nameObject);
				}

			});
		attributeGetterFunctions.put(
			"code",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getCode();
				}

			});
		attributeSetterBiConsumers.put(
			"code",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion, Object codeObject) {

					commerceRegion.setCode((String)codeObject);
				}

			});
		attributeGetterFunctions.put(
			"priority",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getPriority();
				}

			});
		attributeSetterBiConsumers.put(
			"priority",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion, Object priorityObject) {

					commerceRegion.setPriority((Double)priorityObject);
				}

			});
		attributeGetterFunctions.put(
			"active",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getActive();
				}

			});
		attributeSetterBiConsumers.put(
			"active",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion, Object activeObject) {

					commerceRegion.setActive((Boolean)activeObject);
				}

			});
		attributeGetterFunctions.put(
			"lastPublishDate",
			new Function<CommerceRegion, Object>() {

				@Override
				public Object apply(CommerceRegion commerceRegion) {
					return commerceRegion.getLastPublishDate();
				}

			});
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			new BiConsumer<CommerceRegion, Object>() {

				@Override
				public void accept(
					CommerceRegion commerceRegion,
					Object lastPublishDateObject) {

					commerceRegion.setLastPublishDate(
						(Date)lastPublishDateObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getCommerceRegionId() {
		return _commerceRegionId;
	}

	@Override
	public void setCommerceRegionId(long commerceRegionId) {
		_commerceRegionId = commerceRegionId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getCommerceCountryId() {
		return _commerceCountryId;
	}

	@Override
	public void setCommerceCountryId(long commerceCountryId) {
		_columnBitmask |= COMMERCECOUNTRYID_COLUMN_BITMASK;

		if (!_setOriginalCommerceCountryId) {
			_setOriginalCommerceCountryId = true;

			_originalCommerceCountryId = _commerceCountryId;
		}

		_commerceCountryId = commerceCountryId;
	}

	public long getOriginalCommerceCountryId() {
		return _originalCommerceCountryId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public String getCode() {
		if (_code == null) {
			return "";
		}
		else {
			return _code;
		}
	}

	@Override
	public void setCode(String code) {
		_columnBitmask |= CODE_COLUMN_BITMASK;

		if (_originalCode == null) {
			_originalCode = _code;
		}

		_code = code;
	}

	public String getOriginalCode() {
		return GetterUtil.getString(_originalCode);
	}

	@JSON
	@Override
	public double getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(double priority) {
		_priority = priority;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public boolean getOriginalActive() {
		return _originalActive;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(CommerceRegion.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceRegion.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceRegion toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceRegion>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceRegionImpl commerceRegionImpl = new CommerceRegionImpl();

		commerceRegionImpl.setUuid(getUuid());
		commerceRegionImpl.setCommerceRegionId(getCommerceRegionId());
		commerceRegionImpl.setCompanyId(getCompanyId());
		commerceRegionImpl.setUserId(getUserId());
		commerceRegionImpl.setUserName(getUserName());
		commerceRegionImpl.setCreateDate(getCreateDate());
		commerceRegionImpl.setModifiedDate(getModifiedDate());
		commerceRegionImpl.setCommerceCountryId(getCommerceCountryId());
		commerceRegionImpl.setName(getName());
		commerceRegionImpl.setCode(getCode());
		commerceRegionImpl.setPriority(getPriority());
		commerceRegionImpl.setActive(isActive());
		commerceRegionImpl.setLastPublishDate(getLastPublishDate());

		commerceRegionImpl.resetOriginalValues();

		return commerceRegionImpl;
	}

	@Override
	public int compareTo(CommerceRegion commerceRegion) {
		int value = 0;

		if (getPriority() < commerceRegion.getPriority()) {
			value = -1;
		}
		else if (getPriority() > commerceRegion.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceRegion)) {
			return false;
		}

		CommerceRegion commerceRegion = (CommerceRegion)object;

		long primaryKey = commerceRegion.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_originalUuid = _uuid;

		_originalCompanyId = _companyId;

		_setOriginalCompanyId = false;

		_setModifiedDate = false;
		_originalCommerceCountryId = _commerceCountryId;

		_setOriginalCommerceCountryId = false;

		_originalCode = _code;

		_originalActive = _active;

		_setOriginalActive = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceRegion> toCacheModel() {
		CommerceRegionCacheModel commerceRegionCacheModel =
			new CommerceRegionCacheModel();

		commerceRegionCacheModel.uuid = getUuid();

		String uuid = commerceRegionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceRegionCacheModel.uuid = null;
		}

		commerceRegionCacheModel.commerceRegionId = getCommerceRegionId();

		commerceRegionCacheModel.companyId = getCompanyId();

		commerceRegionCacheModel.userId = getUserId();

		commerceRegionCacheModel.userName = getUserName();

		String userName = commerceRegionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceRegionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceRegionCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceRegionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceRegionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceRegionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceRegionCacheModel.commerceCountryId = getCommerceCountryId();

		commerceRegionCacheModel.name = getName();

		String name = commerceRegionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceRegionCacheModel.name = null;
		}

		commerceRegionCacheModel.code = getCode();

		String code = commerceRegionCacheModel.code;

		if ((code != null) && (code.length() == 0)) {
			commerceRegionCacheModel.code = null;
		}

		commerceRegionCacheModel.priority = getPriority();

		commerceRegionCacheModel.active = isActive();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			commerceRegionCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			commerceRegionCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return commerceRegionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceRegion, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceRegion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceRegion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((CommerceRegion)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<CommerceRegion, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceRegion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceRegion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CommerceRegion)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceRegion>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _commerceRegionId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceCountryId;
	private long _originalCommerceCountryId;
	private boolean _setOriginalCommerceCountryId;
	private String _name;
	private String _code;
	private String _originalCode;
	private double _priority;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private CommerceRegion _escapedModel;

}