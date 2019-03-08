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

package com.liferay.portal.search.elasticsearch6.internal.synonym;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch6.internal.util.ResourceUtil;
import com.liferay.portal.search.synonym.SynonymManager;
import com.liferay.portal.search.test.util.AssertUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * @author Adam Brandizzi
 */
public class ElasticsearchSynonymManagerTest {

	@Before
	public void setUp() throws Exception {
		_elasticsearchFixture = new ElasticsearchFixture(
			ElasticsearchSynonymManagerTest.class.getSimpleName());

		_elasticsearchFixture.setUp();

		_elasticsearchSynonymManger = new ElasticsearchSynonymManager();
	}

	@After
	public void tearDown() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	@Test
	public void testGetCompanyIndexName() throws Exception {
		_companyIndexFactoryFixture.createIndices();

		long companyId = RandomTestUtil.randomLong();

		Assert.assertEquals(
			getIndexNameBuilder(companyId),
			_elasticsearchIndexInformation.getCompanyIndexName(companyId));
	}

	@Test
	public void testGetFieldMappings() throws Exception {
		_companyIndexFactoryFixture.createIndices();

		String fieldMappings = _elasticsearchIndexInformation.getFieldMappings(
			_companyIndexFactoryFixture.getIndexName());

		JSONObject expectedJSONObject = loadJSONObject(
			testName.getMethodName());
		JSONObject actualJSONObject = _jsonFactory.createJSONObject(
			fieldMappings);

		AssertUtils.assertEquals("", expectedJSONObject, actualJSONObject);
	}

	@Test
	public void testGetIndexNames() throws Exception {
		_companyIndexFactoryFixture.createIndices();

		String[] indexNames = _elasticsearchIndexInformation.getIndexNames();

		Assert.assertEquals(indexNames.toString(), 1, indexNames.length);
		Assert.assertEquals(
			_companyIndexFactoryFixture.getIndexName(), indexNames[0]);
	}

	@Rule
	public TestName testName = new TestName();

	protected static String getIndexNameBuilder(long companyId) {
		return "test-" + companyId;
	}

	protected JSONObject loadJSONObject(String suffix) throws Exception {
		String json = ResourceUtil.getResourceAsString(
			getClass(),
			"ElasticsearchIndexInformationTest-" + suffix + ".json");

		return _jsonFactory.createJSONObject(json);
	}

	private CompanyIndexFactoryFixture _companyIndexFactoryFixture;
	private ElasticsearchFixture _elasticsearchFixture;
	private ElasticsearchIndexInformation _elasticsearchIndexInformation;
	private SynonymManager _elasticsearchSynonymManger;
	private final JSONFactory _jsonFactory = new JSONFactoryImpl();

}