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

import com.liferay.commerce.price.list.model.CommercePriceListDiscountRel;
import com.liferay.commerce.price.list.model.CommercePriceListDiscountRelModel;
import com.liferay.commerce.price.list.model.CommercePriceListDiscountRelSoap;
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
 * The base model implementation for the CommercePriceListDiscountRel service. Represents a row in the &quot;CommercePriceListDiscountRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommercePriceListDiscountRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePriceListDiscountRelImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListDiscountRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommercePriceListDiscountRelModelImpl
	extends BaseModelImpl<CommercePriceListDiscountRel>
	implements CommercePriceListDiscountRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce price list discount rel model instance should use the <code>CommercePriceListDiscountRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommercePriceListDiscountRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"commercePriceListDiscountRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceDiscountId", Types.BIGINT},
		{"commercePriceListId", Types.BIGINT}, {"order_", Types.INTEGER},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commercePriceListDiscountRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceDiscountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commercePriceListId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("order_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommercePriceListDiscountRel (uuid_ VARCHAR(75) null,commercePriceListDiscountRelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceDiscountId LONG,commercePriceListId LONG,order_ INTEGER,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table CommercePriceListDiscountRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commercePriceListDiscountRel.order ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommercePriceListDiscountRel.order_ ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.price.list.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.price.list.model.CommercePriceListDiscountRel"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.price.list.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.price.list.model.CommercePriceListDiscountRel"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.price.list.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.price.list.model.CommercePriceListDiscountRel"),
		true);

	public static final long COMMERCEDISCOUNTID_COLUMN_BITMASK = 1L;

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
	public static CommercePriceListDiscountRel toModel(
		CommercePriceListDiscountRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommercePriceListDiscountRel model =
			new CommercePriceListDiscountRelImpl();

		model.setUuid(soapModel.getUuid());
		model.setCommercePriceListDiscountRelId(
			soapModel.getCommercePriceListDiscountRelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceDiscountId(soapModel.getCommerceDiscountId());
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
	public static List<CommercePriceListDiscountRel> toModels(
		CommercePriceListDiscountRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommercePriceListDiscountRel> models =
			new ArrayList<CommercePriceListDiscountRel>(soapModels.length);

		for (CommercePriceListDiscountRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.price.list.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.price.list.model.CommercePriceListDiscountRel"));

	public CommercePriceListDiscountRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commercePriceListDiscountRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommercePriceListDiscountRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commercePriceListDiscountRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommercePriceListDiscountRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommercePriceListDiscountRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommercePriceListDiscountRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommercePriceListDiscountRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListDiscountRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommercePriceListDiscountRel)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommercePriceListDiscountRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommercePriceListDiscountRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommercePriceListDiscountRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommercePriceListDiscountRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommercePriceListDiscountRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommercePriceListDiscountRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommercePriceListDiscountRel.class.getClassLoader(),
			CommercePriceListDiscountRel.class, ModelWrapper.class);

		try {
			Constructor<CommercePriceListDiscountRel> constructor =
				(Constructor<CommercePriceListDiscountRel>)
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
		<String, Function<CommercePriceListDiscountRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommercePriceListDiscountRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommercePriceListDiscountRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommercePriceListDiscountRel, Object>>();
		Map<String, BiConsumer<CommercePriceListDiscountRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommercePriceListDiscountRel, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<CommercePriceListDiscountRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListDiscountRel commercePriceListDiscountRel) {

					return commercePriceListDiscountRel.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<CommercePriceListDiscountRel, Object>() {

				@Override
				public void accept(
					CommercePriceListDiscountRel commercePriceListDiscountRel,
					Object uuidObject) {

					commercePriceListDiscountRel.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"commercePriceListDiscountRelId",
			new Function<CommercePriceListDiscountRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListDiscountRel commercePriceListDiscountRel) {

					return commercePriceListDiscountRel.
						getCommercePriceListDiscountRelId();
				}

			});
		attributeSetterBiConsumers.put(
			"commercePriceListDiscountRelId",
			new BiConsumer<CommercePriceListDiscountRel, Object>() {

				@Override
				public void accept(
					CommercePriceListDiscountRel commercePriceListDiscountRel,
					Object commercePriceListDiscountRelIdObject) {

					commercePriceListDiscountRel.
						setCommercePriceListDiscountRelId(
							(Long)commercePriceListDiscountRelIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommercePriceListDiscountRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListDiscountRel commercePriceListDiscountRel) {

					return commercePriceListDiscountRel.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommercePriceListDiscountRel, Object>() {

				@Override
				public void accept(
					CommercePriceListDiscountRel commercePriceListDiscountRel,
					Object companyIdObject) {

					commercePriceListDiscountRel.setCompanyId(
						(Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommercePriceListDiscountRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListDiscountRel commercePriceListDiscountRel) {

					return commercePriceListDiscountRel.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommercePriceListDiscountRel, Object>() {

				@Override
				public void accept(
					CommercePriceListDiscountRel commercePriceListDiscountRel,
					Object userIdObject) {

					commercePriceListDiscountRel.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommercePriceListDiscountRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListDiscountRel commercePriceListDiscountRel) {

					return commercePriceListDiscountRel.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommercePriceListDiscountRel, Object>() {

				@Override
				public void accept(
					CommercePriceListDiscountRel commercePriceListDiscountRel,
					Object userNameObject) {

					commercePriceListDiscountRel.setUserName(
						(String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommercePriceListDiscountRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListDiscountRel commercePriceListDiscountRel) {

					return commercePriceListDiscountRel.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommercePriceListDiscountRel, Object>() {

				@Override
				public void accept(
					CommercePriceListDiscountRel commercePriceListDiscountRel,
					Object createDateObject) {

					commercePriceListDiscountRel.setCreateDate(
						(Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommercePriceListDiscountRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListDiscountRel commercePriceListDiscountRel) {

					return commercePriceListDiscountRel.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommercePriceListDiscountRel, Object>() {

				@Override
				public void accept(
					CommercePriceListDiscountRel commercePriceListDiscountRel,
					Object modifiedDateObject) {

					commercePriceListDiscountRel.setModifiedDate(
						(Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"commerceDiscountId",
			new Function<CommercePriceListDiscountRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListDiscountRel commercePriceListDiscountRel) {

					return commercePriceListDiscountRel.getCommerceDiscountId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceDiscountId",
			new BiConsumer<CommercePriceListDiscountRel, Object>() {

				@Override
				public void accept(
					CommercePriceListDiscountRel commercePriceListDiscountRel,
					Object commerceDiscountIdObject) {

					commercePriceListDiscountRel.setCommerceDiscountId(
						(Long)commerceDiscountIdObject);
				}

			});
		attributeGetterFunctions.put(
			"commercePriceListId",
			new Function<CommercePriceListDiscountRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListDiscountRel commercePriceListDiscountRel) {

					return commercePriceListDiscountRel.
						getCommercePriceListId();
				}

			});
		attributeSetterBiConsumers.put(
			"commercePriceListId",
			new BiConsumer<CommercePriceListDiscountRel, Object>() {

				@Override
				public void accept(
					CommercePriceListDiscountRel commercePriceListDiscountRel,
					Object commercePriceListIdObject) {

					commercePriceListDiscountRel.setCommercePriceListId(
						(Long)commercePriceListIdObject);
				}

			});
		attributeGetterFunctions.put(
			"order",
			new Function<CommercePriceListDiscountRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListDiscountRel commercePriceListDiscountRel) {

					return commercePriceListDiscountRel.getOrder();
				}

			});
		attributeSetterBiConsumers.put(
			"order",
			new BiConsumer<CommercePriceListDiscountRel, Object>() {

				@Override
				public void accept(
					CommercePriceListDiscountRel commercePriceListDiscountRel,
					Object orderObject) {

					commercePriceListDiscountRel.setOrder((Integer)orderObject);
				}

			});
		attributeGetterFunctions.put(
			"lastPublishDate",
			new Function<CommercePriceListDiscountRel, Object>() {

				@Override
				public Object apply(
					CommercePriceListDiscountRel commercePriceListDiscountRel) {

					return commercePriceListDiscountRel.getLastPublishDate();
				}

			});
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			new BiConsumer<CommercePriceListDiscountRel, Object>() {

				@Override
				public void accept(
					CommercePriceListDiscountRel commercePriceListDiscountRel,
					Object lastPublishDateObject) {

					commercePriceListDiscountRel.setLastPublishDate(
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
	public long getCommercePriceListDiscountRelId() {
		return _commercePriceListDiscountRelId;
	}

	@Override
	public void setCommercePriceListDiscountRelId(
		long commercePriceListDiscountRelId) {

		_commercePriceListDiscountRelId = commercePriceListDiscountRelId;
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
	public long getCommerceDiscountId() {
		return _commerceDiscountId;
	}

	@Override
	public void setCommerceDiscountId(long commerceDiscountId) {
		_columnBitmask |= COMMERCEDISCOUNTID_COLUMN_BITMASK;

		if (!_setOriginalCommerceDiscountId) {
			_setOriginalCommerceDiscountId = true;

			_originalCommerceDiscountId = _commerceDiscountId;
		}

		_commerceDiscountId = commerceDiscountId;
	}

	public long getOriginalCommerceDiscountId() {
		return _originalCommerceDiscountId;
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
				CommercePriceListDiscountRel.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommercePriceListDiscountRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommercePriceListDiscountRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommercePriceListDiscountRel>
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
		CommercePriceListDiscountRelImpl commercePriceListDiscountRelImpl =
			new CommercePriceListDiscountRelImpl();

		commercePriceListDiscountRelImpl.setUuid(getUuid());
		commercePriceListDiscountRelImpl.setCommercePriceListDiscountRelId(
			getCommercePriceListDiscountRelId());
		commercePriceListDiscountRelImpl.setCompanyId(getCompanyId());
		commercePriceListDiscountRelImpl.setUserId(getUserId());
		commercePriceListDiscountRelImpl.setUserName(getUserName());
		commercePriceListDiscountRelImpl.setCreateDate(getCreateDate());
		commercePriceListDiscountRelImpl.setModifiedDate(getModifiedDate());
		commercePriceListDiscountRelImpl.setCommerceDiscountId(
			getCommerceDiscountId());
		commercePriceListDiscountRelImpl.setCommercePriceListId(
			getCommercePriceListId());
		commercePriceListDiscountRelImpl.setOrder(getOrder());
		commercePriceListDiscountRelImpl.setLastPublishDate(
			getLastPublishDate());

		commercePriceListDiscountRelImpl.resetOriginalValues();

		return commercePriceListDiscountRelImpl;
	}

	@Override
	public int compareTo(
		CommercePriceListDiscountRel commercePriceListDiscountRel) {

		int value = 0;

		if (getOrder() < commercePriceListDiscountRel.getOrder()) {
			value = -1;
		}
		else if (getOrder() > commercePriceListDiscountRel.getOrder()) {
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

		if (!(object instanceof CommercePriceListDiscountRel)) {
			return false;
		}

		CommercePriceListDiscountRel commercePriceListDiscountRel =
			(CommercePriceListDiscountRel)object;

		long primaryKey = commercePriceListDiscountRel.getPrimaryKey();

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
		_originalCommerceDiscountId = _commerceDiscountId;

		_setOriginalCommerceDiscountId = false;

		_originalCommercePriceListId = _commercePriceListId;

		_setOriginalCommercePriceListId = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CommercePriceListDiscountRel> toCacheModel() {
		CommercePriceListDiscountRelCacheModel
			commercePriceListDiscountRelCacheModel =
				new CommercePriceListDiscountRelCacheModel();

		commercePriceListDiscountRelCacheModel.uuid = getUuid();

		String uuid = commercePriceListDiscountRelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commercePriceListDiscountRelCacheModel.uuid = null;
		}

		commercePriceListDiscountRelCacheModel.commercePriceListDiscountRelId =
			getCommercePriceListDiscountRelId();

		commercePriceListDiscountRelCacheModel.companyId = getCompanyId();

		commercePriceListDiscountRelCacheModel.userId = getUserId();

		commercePriceListDiscountRelCacheModel.userName = getUserName();

		String userName = commercePriceListDiscountRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commercePriceListDiscountRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commercePriceListDiscountRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commercePriceListDiscountRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commercePriceListDiscountRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commercePriceListDiscountRelCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commercePriceListDiscountRelCacheModel.commerceDiscountId =
			getCommerceDiscountId();

		commercePriceListDiscountRelCacheModel.commercePriceListId =
			getCommercePriceListId();

		commercePriceListDiscountRelCacheModel.order = getOrder();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			commercePriceListDiscountRelCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			commercePriceListDiscountRelCacheModel.lastPublishDate =
				Long.MIN_VALUE;
		}

		return commercePriceListDiscountRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommercePriceListDiscountRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommercePriceListDiscountRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListDiscountRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommercePriceListDiscountRel)this));
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
		Map<String, Function<CommercePriceListDiscountRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommercePriceListDiscountRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListDiscountRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommercePriceListDiscountRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommercePriceListDiscountRel>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _commercePriceListDiscountRelId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceDiscountId;
	private long _originalCommerceDiscountId;
	private boolean _setOriginalCommerceDiscountId;
	private long _commercePriceListId;
	private long _originalCommercePriceListId;
	private boolean _setOriginalCommercePriceListId;
	private int _order;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private CommercePriceListDiscountRel _escapedModel;

}