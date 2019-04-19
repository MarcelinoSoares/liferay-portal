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

package com.liferay.batch.engine.model.impl;

import com.liferay.batch.engine.model.BatchJobExecution;
import com.liferay.batch.engine.model.BatchJobExecutionModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the BatchJobExecution service. Represents a row in the &quot;BatchJobExecution&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>BatchJobExecutionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BatchJobExecutionImpl}.
 * </p>
 *
 * @author Ivica Cardic
 * @see BatchJobExecutionImpl
 * @generated
 */
@ProviderType
public class BatchJobExecutionModelImpl
	extends BaseModelImpl<BatchJobExecution> implements BatchJobExecutionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a batch job execution model instance should use the <code>BatchJobExecution</code> interface instead.
	 */
	public static final String TABLE_NAME = "BatchJobExecution";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"batchJobExecutionId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"batchJobInstanceId", Types.BIGINT},
		{"status", Types.VARCHAR}, {"startTime", Types.TIMESTAMP},
		{"endTime", Types.TIMESTAMP}, {"jobSettings", Types.VARCHAR},
		{"error", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("batchJobExecutionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("batchJobInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("status", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("startTime", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endTime", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("jobSettings", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("error", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table BatchJobExecution (uuid_ VARCHAR(75) null,batchJobExecutionId LONG not null primary key,companyId LONG,createDate DATE null,modifiedDate DATE null,batchJobInstanceId LONG,status VARCHAR(75) null,startTime DATE null,endTime DATE null,jobSettings VARCHAR(75) null,error STRING null)";

	public static final String TABLE_SQL_DROP = "drop table BatchJobExecution";

	public static final String ORDER_BY_JPQL =
		" ORDER BY batchJobExecution.batchJobInstanceId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY BatchJobExecution.batchJobInstanceId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.batch.engine.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.batch.engine.model.BatchJobExecution"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.batch.engine.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.batch.engine.model.BatchJobExecution"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.batch.engine.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.batch.engine.model.BatchJobExecution"),
		true);

	public static final long BATCHJOBINSTANCEID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long STATUS_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.batch.engine.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.batch.engine.model.BatchJobExecution"));

	public BatchJobExecutionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _batchJobExecutionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBatchJobExecutionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _batchJobExecutionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return BatchJobExecution.class;
	}

	@Override
	public String getModelClassName() {
		return BatchJobExecution.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<BatchJobExecution, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<BatchJobExecution, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BatchJobExecution, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((BatchJobExecution)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<BatchJobExecution, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<BatchJobExecution, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(BatchJobExecution)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<BatchJobExecution, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<BatchJobExecution, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<BatchJobExecution, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<BatchJobExecution, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<BatchJobExecution, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<BatchJobExecution, Object>>();
		Map<String, BiConsumer<BatchJobExecution, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<BatchJobExecution, ?>>();

		attributeGetterFunctions.put("uuid", BatchJobExecution::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<BatchJobExecution, String>)BatchJobExecution::setUuid);
		attributeGetterFunctions.put(
			"batchJobExecutionId", BatchJobExecution::getBatchJobExecutionId);
		attributeSetterBiConsumers.put(
			"batchJobExecutionId",
			(BiConsumer<BatchJobExecution, Long>)
				BatchJobExecution::setBatchJobExecutionId);
		attributeGetterFunctions.put(
			"companyId", BatchJobExecution::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<BatchJobExecution, Long>)
				BatchJobExecution::setCompanyId);
		attributeGetterFunctions.put(
			"createDate", BatchJobExecution::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<BatchJobExecution, Date>)
				BatchJobExecution::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", BatchJobExecution::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<BatchJobExecution, Date>)
				BatchJobExecution::setModifiedDate);
		attributeGetterFunctions.put(
			"batchJobInstanceId", BatchJobExecution::getBatchJobInstanceId);
		attributeSetterBiConsumers.put(
			"batchJobInstanceId",
			(BiConsumer<BatchJobExecution, Long>)
				BatchJobExecution::setBatchJobInstanceId);
		attributeGetterFunctions.put("status", BatchJobExecution::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<BatchJobExecution, String>)
				BatchJobExecution::setStatus);
		attributeGetterFunctions.put(
			"startTime", BatchJobExecution::getStartTime);
		attributeSetterBiConsumers.put(
			"startTime",
			(BiConsumer<BatchJobExecution, Date>)
				BatchJobExecution::setStartTime);
		attributeGetterFunctions.put("endTime", BatchJobExecution::getEndTime);
		attributeSetterBiConsumers.put(
			"endTime",
			(BiConsumer<BatchJobExecution, Date>)BatchJobExecution::setEndTime);
		attributeGetterFunctions.put(
			"jobSettings", BatchJobExecution::getJobSettings);
		attributeSetterBiConsumers.put(
			"jobSettings",
			(BiConsumer<BatchJobExecution, String>)
				BatchJobExecution::setJobSettings);
		attributeGetterFunctions.put("error", BatchJobExecution::getError);
		attributeSetterBiConsumers.put(
			"error",
			(BiConsumer<BatchJobExecution, String>)BatchJobExecution::setError);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

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

	@Override
	public long getBatchJobExecutionId() {
		return _batchJobExecutionId;
	}

	@Override
	public void setBatchJobExecutionId(long batchJobExecutionId) {
		_batchJobExecutionId = batchJobExecutionId;
	}

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

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

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

	@Override
	public long getBatchJobInstanceId() {
		return _batchJobInstanceId;
	}

	@Override
	public void setBatchJobInstanceId(long batchJobInstanceId) {
		_columnBitmask = -1L;

		if (!_setOriginalBatchJobInstanceId) {
			_setOriginalBatchJobInstanceId = true;

			_originalBatchJobInstanceId = _batchJobInstanceId;
		}

		_batchJobInstanceId = batchJobInstanceId;
	}

	public long getOriginalBatchJobInstanceId() {
		return _originalBatchJobInstanceId;
	}

	@Override
	public String getStatus() {
		if (_status == null) {
			return "";
		}
		else {
			return _status;
		}
	}

	@Override
	public void setStatus(String status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (_originalStatus == null) {
			_originalStatus = _status;
		}

		_status = status;
	}

	public String getOriginalStatus() {
		return GetterUtil.getString(_originalStatus);
	}

	@Override
	public Date getStartTime() {
		return _startTime;
	}

	@Override
	public void setStartTime(Date startTime) {
		_startTime = startTime;
	}

	@Override
	public Date getEndTime() {
		return _endTime;
	}

	@Override
	public void setEndTime(Date endTime) {
		_endTime = endTime;
	}

	@Override
	public String getJobSettings() {
		if (_jobSettings == null) {
			return "";
		}
		else {
			return _jobSettings;
		}
	}

	@Override
	public void setJobSettings(String jobSettings) {
		_jobSettings = jobSettings;
	}

	@Override
	public String getError() {
		if (_error == null) {
			return "";
		}
		else {
			return _error;
		}
	}

	@Override
	public void setError(String error) {
		_error = error;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(BatchJobExecution.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), BatchJobExecution.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public BatchJobExecution toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (BatchJobExecution)ProxyUtil.newProxyInstance(
				_classLoader, _escapedModelInterfaces,
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		BatchJobExecutionImpl batchJobExecutionImpl =
			new BatchJobExecutionImpl();

		batchJobExecutionImpl.setUuid(getUuid());
		batchJobExecutionImpl.setBatchJobExecutionId(getBatchJobExecutionId());
		batchJobExecutionImpl.setCompanyId(getCompanyId());
		batchJobExecutionImpl.setCreateDate(getCreateDate());
		batchJobExecutionImpl.setModifiedDate(getModifiedDate());
		batchJobExecutionImpl.setBatchJobInstanceId(getBatchJobInstanceId());
		batchJobExecutionImpl.setStatus(getStatus());
		batchJobExecutionImpl.setStartTime(getStartTime());
		batchJobExecutionImpl.setEndTime(getEndTime());
		batchJobExecutionImpl.setJobSettings(getJobSettings());
		batchJobExecutionImpl.setError(getError());

		batchJobExecutionImpl.resetOriginalValues();

		return batchJobExecutionImpl;
	}

	@Override
	public int compareTo(BatchJobExecution batchJobExecution) {
		int value = 0;

		if (getBatchJobInstanceId() <
				batchJobExecution.getBatchJobInstanceId()) {

			value = -1;
		}
		else if (getBatchJobInstanceId() >
					batchJobExecution.getBatchJobInstanceId()) {

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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BatchJobExecution)) {
			return false;
		}

		BatchJobExecution batchJobExecution = (BatchJobExecution)obj;

		long primaryKey = batchJobExecution.getPrimaryKey();

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
		BatchJobExecutionModelImpl batchJobExecutionModelImpl = this;

		batchJobExecutionModelImpl._originalUuid =
			batchJobExecutionModelImpl._uuid;

		batchJobExecutionModelImpl._originalCompanyId =
			batchJobExecutionModelImpl._companyId;

		batchJobExecutionModelImpl._setOriginalCompanyId = false;

		batchJobExecutionModelImpl._setModifiedDate = false;

		batchJobExecutionModelImpl._originalBatchJobInstanceId =
			batchJobExecutionModelImpl._batchJobInstanceId;

		batchJobExecutionModelImpl._setOriginalBatchJobInstanceId = false;

		batchJobExecutionModelImpl._originalStatus =
			batchJobExecutionModelImpl._status;

		batchJobExecutionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<BatchJobExecution> toCacheModel() {
		BatchJobExecutionCacheModel batchJobExecutionCacheModel =
			new BatchJobExecutionCacheModel();

		batchJobExecutionCacheModel.uuid = getUuid();

		String uuid = batchJobExecutionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			batchJobExecutionCacheModel.uuid = null;
		}

		batchJobExecutionCacheModel.batchJobExecutionId =
			getBatchJobExecutionId();

		batchJobExecutionCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			batchJobExecutionCacheModel.createDate = createDate.getTime();
		}
		else {
			batchJobExecutionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			batchJobExecutionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			batchJobExecutionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		batchJobExecutionCacheModel.batchJobInstanceId =
			getBatchJobInstanceId();

		batchJobExecutionCacheModel.status = getStatus();

		String status = batchJobExecutionCacheModel.status;

		if ((status != null) && (status.length() == 0)) {
			batchJobExecutionCacheModel.status = null;
		}

		Date startTime = getStartTime();

		if (startTime != null) {
			batchJobExecutionCacheModel.startTime = startTime.getTime();
		}
		else {
			batchJobExecutionCacheModel.startTime = Long.MIN_VALUE;
		}

		Date endTime = getEndTime();

		if (endTime != null) {
			batchJobExecutionCacheModel.endTime = endTime.getTime();
		}
		else {
			batchJobExecutionCacheModel.endTime = Long.MIN_VALUE;
		}

		batchJobExecutionCacheModel.jobSettings = getJobSettings();

		String jobSettings = batchJobExecutionCacheModel.jobSettings;

		if ((jobSettings != null) && (jobSettings.length() == 0)) {
			batchJobExecutionCacheModel.jobSettings = null;
		}

		batchJobExecutionCacheModel.error = getError();

		String error = batchJobExecutionCacheModel.error;

		if ((error != null) && (error.length() == 0)) {
			batchJobExecutionCacheModel.error = null;
		}

		return batchJobExecutionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<BatchJobExecution, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<BatchJobExecution, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BatchJobExecution, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((BatchJobExecution)this));
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
		Map<String, Function<BatchJobExecution, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<BatchJobExecution, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BatchJobExecution, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((BatchJobExecution)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader =
		BatchJobExecution.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		BatchJobExecution.class, ModelWrapper.class
	};

	private String _uuid;
	private String _originalUuid;
	private long _batchJobExecutionId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _batchJobInstanceId;
	private long _originalBatchJobInstanceId;
	private boolean _setOriginalBatchJobInstanceId;
	private String _status;
	private String _originalStatus;
	private Date _startTime;
	private Date _endTime;
	private String _jobSettings;
	private String _error;
	private long _columnBitmask;
	private BatchJobExecution _escapedModel;

}