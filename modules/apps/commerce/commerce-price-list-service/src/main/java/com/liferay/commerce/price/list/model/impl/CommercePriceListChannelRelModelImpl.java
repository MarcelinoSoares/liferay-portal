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

package com.liferay.commerce.price.list.model.impl;

import com.liferay.commerce.price.list.model.CommercePriceListChannelRel;
import com.liferay.commerce.price.list.model.CommercePriceListChannelRelModel;
import com.liferay.commerce.price.list.model.CommercePriceListChannelRelSoap;
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
 * The base model implementation for the CommercePriceListChannelRel service. Represents a row in the &quot;CommercePriceListChannelRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommercePriceListChannelRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePriceListChannelRelImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListChannelRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommercePriceListChannelRelModelImpl
	extends BaseModelImpl<CommercePriceListChannelRel>
	implements CommercePriceListChannelRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce price list channel rel model instance should use the <code>CommercePriceListChannelRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommercePriceListChannelRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"CommercePriceListChannelRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceChannelId", Types.BIGINT},
		{"commercePriceListId", Types.BIGINT}, {"order_", Types.INTEGER},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CommercePriceListChannelRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceChannelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commercePriceListId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("order_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommercePriceListChannelRel (uuid_ VARCHAR(75) null,CommercePriceListChannelRelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceChannelId LONG,commercePriceListId LONG,order_ INTEGER,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table CommercePriceListChannelRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commercePriceListChannelRel.order ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommercePriceListChannelRel.order_ ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.price.list.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.price.list.model.CommercePriceListChannelRel"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.price.list.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.price.list.model.CommercePriceListChannelRel"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.price.list.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.price.list.model.CommercePriceListChannelRel"),
		true);

	public static final long COMMERCECHANNELID_COLUMN_BITMASK = 1L;

	public static final long COMMERCEPRICELISTID_COLUMN_BITMASK = 2L;

	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long ORDER_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommercePriceListChannelRel toModel(
		CommercePriceListChannelRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommercePriceListChannelRel model =
			new CommercePriceListChannelRelImpl();

		model.setUuid(soapModel.getUuid());
		model.setCommercePriceListChannelRelId(
			soapModel.getCommercePriceListChannelRelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceChannelId(soapModel.getCommerceChannelId());
		model.setCommercePriceListId(soapModel.getCommercePriceListId());
		model.setOrder(soapModel.getOrder());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommercePriceListChannelRel> toModels(
		CommercePriceListChannelRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommercePriceListChannelRel> models =
			new ArrayList<CommercePriceListChannelRel>(soapModels.length);

		for (CommercePriceListChannelRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.price.list.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.price.list.model.CommercePriceListChannelRel"));

	public CommercePriceListChannelRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CommercePriceListChannelRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommercePriceListChannelRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CommercePriceListChannelRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommercePriceListChannelRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommercePriceListChannelRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommercePriceListChannelRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommercePriceListChannelRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListChannelRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommercePriceListChannelRel)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommercePriceListChannelRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommercePriceListChannelRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommercePriceListChannelRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommercePriceListChannelRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommercePriceListChannelRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommercePriceListChannelRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommercePriceListChannelRel.class.getClassLoader(),
			CommercePriceListChannelRel.class, ModelWrapper.class);

		try {
			Constructor<CommercePriceListChannelRel> constructor =
				(Constructor<CommercePriceListChannelRel>)
					proxyClass.getConstructor(InvocationHandler.class);

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

	private static final Map
		<String, Function<CommercePriceListChannelRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommercePriceListChannelRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommercePriceListChannelRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommercePriceListChannelRel, Object>>();
		Map<String, BiConsumer<CommercePriceListChannelRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommercePriceListChannelRel, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<CommercePriceListChannelRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListChannelRel commercePriceListChannelRel) {

					return commercePriceListChannelRel.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<CommercePriceListChannelRel, Object>() {

				@Override
				public void accept(
					CommercePriceListChannelRel commercePriceListChannelRel,
					Object uuidObject) {

					commercePriceListChannelRel.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"CommercePriceListChannelRelId",
			new Function<CommercePriceListChannelRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListChannelRel commercePriceListChannelRel) {

					return commercePriceListChannelRel.
						getCommercePriceListChannelRelId();
				}

			});
		attributeSetterBiConsumers.put(
			"CommercePriceListChannelRelId",
			new BiConsumer<CommercePriceListChannelRel, Object>() {

				@Override
				public void accept(
					CommercePriceListChannelRel commercePriceListChannelRel,
					Object CommercePriceListChannelRelIdObject) {

					commercePriceListChannelRel.
						setCommercePriceListChannelRelId(
							(Long)CommercePriceListChannelRelIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommercePriceListChannelRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListChannelRel commercePriceListChannelRel) {

					return commercePriceListChannelRel.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommercePriceListChannelRel, Object>() {

				@Override
				public void accept(
					CommercePriceListChannelRel commercePriceListChannelRel,
					Object companyIdObject) {

					commercePriceListChannelRel.setCompanyId(
						(Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommercePriceListChannelRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListChannelRel commercePriceListChannelRel) {

					return commercePriceListChannelRel.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommercePriceListChannelRel, Object>() {

				@Override
				public void accept(
					CommercePriceListChannelRel commercePriceListChannelRel,
					Object userIdObject) {

					commercePriceListChannelRel.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommercePriceListChannelRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListChannelRel commercePriceListChannelRel) {

					return commercePriceListChannelRel.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommercePriceListChannelRel, Object>() {

				@Override
				public void accept(
					CommercePriceListChannelRel commercePriceListChannelRel,
					Object userNameObject) {

					commercePriceListChannelRel.setUserName(
						(String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommercePriceListChannelRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListChannelRel commercePriceListChannelRel) {

					return commercePriceListChannelRel.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommercePriceListChannelRel, Object>() {

				@Override
				public void accept(
					CommercePriceListChannelRel commercePriceListChannelRel,
					Object createDateObject) {

					commercePriceListChannelRel.setCreateDate(
						(Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommercePriceListChannelRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListChannelRel commercePriceListChannelRel) {

					return commercePriceListChannelRel.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommercePriceListChannelRel, Object>() {

				@Override
				public void accept(
					CommercePriceListChannelRel commercePriceListChannelRel,
					Object modifiedDateObject) {

					commercePriceListChannelRel.setModifiedDate(
						(Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"commerceChannelId",
			new Function<CommercePriceListChannelRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListChannelRel commercePriceListChannelRel) {

					return commercePriceListChannelRel.getCommerceChannelId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceChannelId",
			new BiConsumer<CommercePriceListChannelRel, Object>() {

				@Override
				public void accept(
					CommercePriceListChannelRel commercePriceListChannelRel,
					Object commerceChannelIdObject) {

					commercePriceListChannelRel.setCommerceChannelId(
						(Long)commerceChannelIdObject);
				}

			});
		attributeGetterFunctions.put(
			"commercePriceListId",
			new Function<CommercePriceListChannelRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListChannelRel commercePriceListChannelRel) {

					return commercePriceListChannelRel.getCommercePriceListId();
				}

			});
		attributeSetterBiConsumers.put(
			"commercePriceListId",
			new BiConsumer<CommercePriceListChannelRel, Object>() {

				@Override
				public void accept(
					CommercePriceListChannelRel commercePriceListChannelRel,
					Object commercePriceListIdObject) {

					commercePriceListChannelRel.setCommercePriceListId(
						(Long)commercePriceListIdObject);
				}

			});
		attributeGetterFunctions.put(
			"order",
			new Function<CommercePriceListChannelRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListChannelRel commercePriceListChannelRel) {

					return commercePriceListChannelRel.getOrder();
				}

			});
		attributeSetterBiConsumers.put(
			"order",
			new BiConsumer<CommercePriceListChannelRel, Object>() {

				@Override
				public void accept(
					CommercePriceListChannelRel commercePriceListChannelRel,
					Object orderObject) {

					commercePriceListChannelRel.setOrder((Integer)orderObject);
				}

			});
		attributeGetterFunctions.put(
			"lastPublishDate",
			new Function<CommercePriceListChannelRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListChannelRel commercePriceListChannelRel) {

					return commercePriceListChannelRel.getLastPublishDate();
				}

			});
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			new BiConsumer<CommercePriceListChannelRel, Object>() {

				@Override
				public void accept(
					CommercePriceListChannelRel commercePriceListChannelRel,
					Object lastPublishDateObject) {

					commercePriceListChannelRel.setLastPublishDate(
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
	public long getCommercePriceListChannelRelId() {
		return _CommercePriceListChannelRelId;
	}

	@Override
	public void setCommercePriceListChannelRelId(
		long CommercePriceListChannelRelId) {

		_CommercePriceListChannelRelId = CommercePriceListChannelRelId;
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
	public long getCommerceChannelId() {
		return _commerceChannelId;
	}

	@Override
	public void setCommerceChannelId(long commerceChannelId) {
		_columnBitmask |= COMMERCECHANNELID_COLUMN_BITMASK;

		if (!_setOriginalCommerceChannelId) {
			_setOriginalCommerceChannelId = true;

			_originalCommerceChannelId = _commerceChannelId;
		}

		_commerceChannelId = commerceChannelId;
	}

	public long getOriginalCommerceChannelId() {
		return _originalCommerceChannelId;
	}

	@JSON
	@Override
	public long getCommercePriceListId() {
		return _commercePriceListId;
	}

	@Override
	public void setCommercePriceListId(long commercePriceListId) {
		_columnBitmask |= COMMERCEPRICELISTID_COLUMN_BITMASK;

		if (!_setOriginalCommercePriceListId) {
			_setOriginalCommercePriceListId = true;

			_originalCommercePriceListId = _commercePriceListId;
		}

		_commercePriceListId = commercePriceListId;
	}

	public long getOriginalCommercePriceListId() {
		return _originalCommercePriceListId;
	}

	@JSON
	@Override
	public int getOrder() {
		return _order;
	}

	@Override
	public void setOrder(int order) {
		_order = order;
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
			PortalUtil.getClassNameId(
				CommercePriceListChannelRel.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommercePriceListChannelRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommercePriceListChannelRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommercePriceListChannelRel>
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
		CommercePriceListChannelRelImpl commercePriceListChannelRelImpl =
			new CommercePriceListChannelRelImpl();

		commercePriceListChannelRelImpl.setUuid(getUuid());
		commercePriceListChannelRelImpl.setCommercePriceListChannelRelId(
			getCommercePriceListChannelRelId());
		commercePriceListChannelRelImpl.setCompanyId(getCompanyId());
		commercePriceListChannelRelImpl.setUserId(getUserId());
		commercePriceListChannelRelImpl.setUserName(getUserName());
		commercePriceListChannelRelImpl.setCreateDate(getCreateDate());
		commercePriceListChannelRelImpl.setModifiedDate(getModifiedDate());
		commercePriceListChannelRelImpl.setCommerceChannelId(
			getCommerceChannelId());
		commercePriceListChannelRelImpl.setCommercePriceListId(
			getCommercePriceListId());
		commercePriceListChannelRelImpl.setOrder(getOrder());
		commercePriceListChannelRelImpl.setLastPublishDate(
			getLastPublishDate());

		commercePriceListChannelRelImpl.resetOriginalValues();

		return commercePriceListChannelRelImpl;
	}

	@Override
	public int compareTo(
		CommercePriceListChannelRel commercePriceListChannelRel) {

		int value = 0;

		if (getOrder() < commercePriceListChannelRel.getOrder()) {
			value = -1;
		}
		else if (getOrder() > commercePriceListChannelRel.getOrder()) {
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

		if (!(object instanceof CommercePriceListChannelRel)) {
			return false;
		}

		CommercePriceListChannelRel commercePriceListChannelRel =
			(CommercePriceListChannelRel)object;

		long primaryKey = commercePriceListChannelRel.getPrimaryKey();

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
		_originalCommerceChannelId = _commerceChannelId;

		_setOriginalCommerceChannelId = false;

		_originalCommercePriceListId = _commercePriceListId;

		_setOriginalCommercePriceListId = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CommercePriceListChannelRel> toCacheModel() {
		CommercePriceListChannelRelCacheModel
			commercePriceListChannelRelCacheModel =
				new CommercePriceListChannelRelCacheModel();

		commercePriceListChannelRelCacheModel.uuid = getUuid();

		String uuid = commercePriceListChannelRelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commercePriceListChannelRelCacheModel.uuid = null;
		}

		commercePriceListChannelRelCacheModel.CommercePriceListChannelRelId =
			getCommercePriceListChannelRelId();

		commercePriceListChannelRelCacheModel.companyId = getCompanyId();

		commercePriceListChannelRelCacheModel.userId = getUserId();

		commercePriceListChannelRelCacheModel.userName = getUserName();

		String userName = commercePriceListChannelRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commercePriceListChannelRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commercePriceListChannelRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commercePriceListChannelRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commercePriceListChannelRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commercePriceListChannelRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commercePriceListChannelRelCacheModel.commerceChannelId =
			getCommerceChannelId();

		commercePriceListChannelRelCacheModel.commercePriceListId =
			getCommercePriceListId();

		commercePriceListChannelRelCacheModel.order = getOrder();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			commercePriceListChannelRelCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			commercePriceListChannelRelCacheModel.lastPublishDate =
				Long.MIN_VALUE;
		}

		return commercePriceListChannelRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommercePriceListChannelRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommercePriceListChannelRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListChannelRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommercePriceListChannelRel)this));
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
		Map<String, Function<CommercePriceListChannelRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommercePriceListChannelRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListChannelRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommercePriceListChannelRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommercePriceListChannelRel>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _CommercePriceListChannelRelId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceChannelId;
	private long _originalCommerceChannelId;
	private boolean _setOriginalCommerceChannelId;
	private long _commercePriceListId;
	private long _originalCommercePriceListId;
	private boolean _setOriginalCommercePriceListId;
	private int _order;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private CommercePriceListChannelRel _escapedModel;

}