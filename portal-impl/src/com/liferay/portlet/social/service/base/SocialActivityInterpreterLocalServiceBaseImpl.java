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

package com.liferay.portlet.social.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.social.kernel.service.SocialActivityInterpreterLocalService;
import com.liferay.social.kernel.service.SocialActivityInterpreterLocalServiceUtil;
import com.liferay.social.kernel.service.persistence.SocialActivityFinder;
import com.liferay.social.kernel.service.persistence.SocialActivityPersistence;
import com.liferay.social.kernel.service.persistence.SocialActivitySetFinder;
import com.liferay.social.kernel.service.persistence.SocialActivitySetPersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the social activity interpreter local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.social.service.impl.SocialActivityInterpreterLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.social.service.impl.SocialActivityInterpreterLocalServiceImpl
 * @generated
 */
public abstract class SocialActivityInterpreterLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, SocialActivityInterpreterLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SocialActivityInterpreterLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>SocialActivityInterpreterLocalServiceUtil</code>.
	 */

	/**
	 * Returns the social activity interpreter local service.
	 *
	 * @return the social activity interpreter local service
	 */
	public SocialActivityInterpreterLocalService
		getSocialActivityInterpreterLocalService() {

		return socialActivityInterpreterLocalService;
	}

	/**
	 * Sets the social activity interpreter local service.
	 *
	 * @param socialActivityInterpreterLocalService the social activity interpreter local service
	 */
	public void setSocialActivityInterpreterLocalService(
		SocialActivityInterpreterLocalService
			socialActivityInterpreterLocalService) {

		this.socialActivityInterpreterLocalService =
			socialActivityInterpreterLocalService;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the social activity local service.
	 *
	 * @return the social activity local service
	 */
	public com.liferay.social.kernel.service.SocialActivityLocalService
		getSocialActivityLocalService() {

		return socialActivityLocalService;
	}

	/**
	 * Sets the social activity local service.
	 *
	 * @param socialActivityLocalService the social activity local service
	 */
	public void setSocialActivityLocalService(
		com.liferay.social.kernel.service.SocialActivityLocalService
			socialActivityLocalService) {

		this.socialActivityLocalService = socialActivityLocalService;
	}

	/**
	 * Returns the social activity persistence.
	 *
	 * @return the social activity persistence
	 */
	public SocialActivityPersistence getSocialActivityPersistence() {
		return socialActivityPersistence;
	}

	/**
	 * Sets the social activity persistence.
	 *
	 * @param socialActivityPersistence the social activity persistence
	 */
	public void setSocialActivityPersistence(
		SocialActivityPersistence socialActivityPersistence) {

		this.socialActivityPersistence = socialActivityPersistence;
	}

	/**
	 * Returns the social activity finder.
	 *
	 * @return the social activity finder
	 */
	public SocialActivityFinder getSocialActivityFinder() {
		return socialActivityFinder;
	}

	/**
	 * Sets the social activity finder.
	 *
	 * @param socialActivityFinder the social activity finder
	 */
	public void setSocialActivityFinder(
		SocialActivityFinder socialActivityFinder) {

		this.socialActivityFinder = socialActivityFinder;
	}

	/**
	 * Returns the social activity set local service.
	 *
	 * @return the social activity set local service
	 */
	public com.liferay.social.kernel.service.SocialActivitySetLocalService
		getSocialActivitySetLocalService() {

		return socialActivitySetLocalService;
	}

	/**
	 * Sets the social activity set local service.
	 *
	 * @param socialActivitySetLocalService the social activity set local service
	 */
	public void setSocialActivitySetLocalService(
		com.liferay.social.kernel.service.SocialActivitySetLocalService
			socialActivitySetLocalService) {

		this.socialActivitySetLocalService = socialActivitySetLocalService;
	}

	/**
	 * Returns the social activity set persistence.
	 *
	 * @return the social activity set persistence
	 */
	public SocialActivitySetPersistence getSocialActivitySetPersistence() {
		return socialActivitySetPersistence;
	}

	/**
	 * Sets the social activity set persistence.
	 *
	 * @param socialActivitySetPersistence the social activity set persistence
	 */
	public void setSocialActivitySetPersistence(
		SocialActivitySetPersistence socialActivitySetPersistence) {

		this.socialActivitySetPersistence = socialActivitySetPersistence;
	}

	/**
	 * Returns the social activity set finder.
	 *
	 * @return the social activity set finder
	 */
	public SocialActivitySetFinder getSocialActivitySetFinder() {
		return socialActivitySetFinder;
	}

	/**
	 * Sets the social activity set finder.
	 *
	 * @param socialActivitySetFinder the social activity set finder
	 */
	public void setSocialActivitySetFinder(
		SocialActivitySetFinder socialActivitySetFinder) {

		this.socialActivitySetFinder = socialActivitySetFinder;
	}

	public void afterPropertiesSet() {
		_setLocalServiceUtilService(socialActivityInterpreterLocalService);
	}

	public void destroy() {
		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SocialActivityInterpreterLocalService.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = InfrastructureUtil.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setLocalServiceUtilService(
		SocialActivityInterpreterLocalService
			socialActivityInterpreterLocalService) {

		try {
			Field field =
				SocialActivityInterpreterLocalServiceUtil.class.
					getDeclaredField("_service");

			field.setAccessible(true);

			field.set(null, socialActivityInterpreterLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = SocialActivityInterpreterLocalService.class)
	protected SocialActivityInterpreterLocalService
		socialActivityInterpreterLocalService;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(
		type = com.liferay.social.kernel.service.SocialActivityLocalService.class
	)
	protected com.liferay.social.kernel.service.SocialActivityLocalService
		socialActivityLocalService;

	@BeanReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;

	@BeanReference(type = SocialActivityFinder.class)
	protected SocialActivityFinder socialActivityFinder;

	@BeanReference(
		type = com.liferay.social.kernel.service.SocialActivitySetLocalService.class
	)
	protected com.liferay.social.kernel.service.SocialActivitySetLocalService
		socialActivitySetLocalService;

	@BeanReference(type = SocialActivitySetPersistence.class)
	protected SocialActivitySetPersistence socialActivitySetPersistence;

	@BeanReference(type = SocialActivitySetFinder.class)
	protected SocialActivitySetFinder socialActivitySetFinder;

}