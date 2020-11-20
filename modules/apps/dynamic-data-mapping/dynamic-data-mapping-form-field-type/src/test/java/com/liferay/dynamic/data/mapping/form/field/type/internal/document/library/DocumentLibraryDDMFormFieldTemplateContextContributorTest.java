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

package com.liferay.dynamic.data.mapping.form.field.type.internal.document.library;

import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldTypeSettingsTestCase;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletURL;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.util.HtmlImpl;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.hamcrest.CoreMatchers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.expectation.PowerMockitoStubber;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Pedro Queiroz
 */
@PrepareForTest(RequestBackedPortletURLFactoryUtil.class)
@RunWith(PowerMockRunner.class)
public class DocumentLibraryDDMFormFieldTemplateContextContributorTest
	extends BaseDDMFormFieldTypeSettingsTestCase {

	public HttpServletRequest createHttpServletRequest() {
		MockHttpServletRequest httpServletRequest =
			new MockHttpServletRequest();

		httpServletRequest.setParameter(
			"formInstanceId", String.valueOf(_FORM_INSTANCE_ID));

		return httpServletRequest;
	}

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setUpDLAppService();
		setUpFileEntry();
		setUpHtml();
		setUpJSONFactory();
		setUpJSONFactoryUtil();
		setUpParamUtil();
		setUpRequestBackedPortletURLFactoryUtil();
	}

	@Test
	public void testGetParametersShouldContainFileEntryURL() {
		DocumentLibraryDDMFormFieldTemplateContextContributor spy = createSpy(
			mockThemeDisplay());

		Map<String, Object> parameters = spy.getParameters(
			new DDMFormField("field", "document_library"),
			createDDMFormFieldRenderingContext());

		Assert.assertTrue(parameters.containsKey("fileEntryURL"));
	}

	@Test
	public void testGetParametersShouldContainItemSelectorAuthToken() {
		DocumentLibraryDDMFormFieldTemplateContextContributor spy = createSpy(
			mockThemeDisplay());

		Map<String, Object> parameters = spy.getParameters(
			new DDMFormField("field", "document_library"),
			createDDMFormFieldRenderingContext());

		Assert.assertEquals("token", parameters.get("itemSelectorAuthToken"));
	}

	@Test
	public void testGetParametersShouldContainUploadURL() {
		DocumentLibraryDDMFormFieldTemplateContextContributor spy = createSpy(
			mockThemeDisplay());

		Map<String, Object> parameters = spy.getParameters(
			new DDMFormField("field", "document_library"),
			createDDMFormFieldRenderingContext());

		String uploadURL = String.valueOf(parameters.get("uploadURL"));

		Assert.assertThat(
			uploadURL,
			CoreMatchers.containsString(
				"param_javax.portlet.action=/dynamic_data_mapping_form" +
					"/upload_file_entry"));
		Assert.assertThat(
			uploadURL,
			CoreMatchers.containsString(
				"param_formInstanceId=" + _FORM_INSTANCE_ID));
		Assert.assertThat(
			uploadURL,
			CoreMatchers.containsString("param_groupId=" + _GROUP_ID));
	}

	@Test
	public void testGetParametersShouldUseFileEntryTitle() {
		DocumentLibraryDDMFormFieldTemplateContextContributor spy = createSpy(
			mockThemeDisplay());

		Map<String, Object> parameters = spy.getParameters(
			new DDMFormField("field", "document_library"),
			createDDMFormFieldRenderingContext());

		Assert.assertEquals("New Title", parameters.get("fileEntryTitle"));
	}

	protected DDMFormFieldRenderingContext
		createDDMFormFieldRenderingContext() {

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext =
			new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setHttpServletRequest(
			createHttpServletRequest());
		ddmFormFieldRenderingContext.setProperty("groupId", _GROUP_ID);
		ddmFormFieldRenderingContext.setValue(
			JSONUtil.put(
				"groupId", _GROUP_ID
			).put(
				"title", "File Title"
			).put(
				"uuid", _FILE_ENTRY_UUID
			).toString());

		return ddmFormFieldRenderingContext;
	}

	protected DocumentLibraryDDMFormFieldTemplateContextContributor createSpy(
		ThemeDisplay themeDisplay) {

		DocumentLibraryDDMFormFieldTemplateContextContributor spy =
			PowerMockito.spy(
				_documentLibraryDDMFormFieldTemplateContextContributor);

		PowerMockitoStubber stubber = PowerMockito.doReturn(_resourceBundle);

		stubber.when(
			spy
		).getResourceBundle(
			Matchers.any(Locale.class)
		);

		stubber = PowerMockito.doReturn("token");

		stubber.when(
			spy
		).getItemSelectorAuthToken(
			Matchers.any(HttpServletRequest.class)
		);

		stubber = PowerMockito.doReturn(themeDisplay);

		stubber.when(
			spy
		).getThemeDisplay(
			Matchers.any(HttpServletRequest.class)
		);

		return spy;
	}

	protected RequestBackedPortletURLFactory
		mockRequestBackedPortletURLFactory() {

		RequestBackedPortletURLFactory requestBackedPortletURLFactory = mock(
			RequestBackedPortletURLFactory.class);

		when(
			requestBackedPortletURLFactory.createActionURL(
				DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM)
		).thenReturn(
			new MockLiferayPortletURL()
		);

		return requestBackedPortletURLFactory;
	}

	protected ThemeDisplay mockThemeDisplay() {
		ThemeDisplay themeDisplay = mock(ThemeDisplay.class);

		when(
			themeDisplay.getCompanyId()
		).thenReturn(
			_COMPANY_ID
		);

		when(
			themeDisplay.getPathContext()
		).thenReturn(
			"/my/path/context/"
		);

		when(
			themeDisplay.getPathThemeImages()
		).thenReturn(
			"/my/theme/images/"
		);

		User user = mockUser();

		when(
			themeDisplay.getUser()
		).thenReturn(
			user
		);

		return themeDisplay;
	}

	protected User mockUser() {
		User user = mock(User.class);

		when(
			user.getUserId()
		).thenReturn(
			0L
		);

		return user;
	}

	protected void setUpDLAppService() throws Exception {
		MemberMatcher.field(
			DocumentLibraryDDMFormFieldTemplateContextContributor.class,
			"dlAppService"
		).set(
			_documentLibraryDDMFormFieldTemplateContextContributor,
			_dlAppService
		);

		PowerMockito.when(
			_dlAppService.getFileEntryByUuidAndGroupId(
				_FILE_ENTRY_UUID, _GROUP_ID)
		).thenReturn(
			_fileEntry
		);
	}

	protected void setUpFileEntry() {
		_fileEntry.setUuid(_FILE_ENTRY_UUID);
		_fileEntry.setGroupId(_GROUP_ID);

		PowerMockito.when(
			_fileEntry.getTitle()
		).thenReturn(
			"New Title"
		);
	}

	protected void setUpHtml() throws Exception {
		MemberMatcher.field(
			DocumentLibraryDDMFormFieldTemplateContextContributor.class, "html"
		).set(
			_documentLibraryDDMFormFieldTemplateContextContributor, _html
		);
	}

	protected void setUpJSONFactory() throws Exception {
		MemberMatcher.field(
			DocumentLibraryDDMFormFieldTemplateContextContributor.class,
			"jsonFactory"
		).set(
			_documentLibraryDDMFormFieldTemplateContextContributor, _jsonFactory
		);
	}

	protected void setUpJSONFactoryUtil() {
		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());
	}

	protected void setUpParamUtil() {
		PropsUtil.setProps(Mockito.mock(Props.class));
	}

	protected void setUpRequestBackedPortletURLFactoryUtil() {
		PowerMockito.mockStatic(RequestBackedPortletURLFactoryUtil.class);

		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			mockRequestBackedPortletURLFactory();

		PowerMockito.when(
			RequestBackedPortletURLFactoryUtil.create(
				Matchers.any(HttpServletRequest.class))
		).thenReturn(
			requestBackedPortletURLFactory
		);
	}

	private static final long _COMPANY_ID = RandomTestUtil.randomLong();

	private static final String _FILE_ENTRY_UUID =
		RandomTestUtil.randomString();

	private static final long _FORM_INSTANCE_ID = RandomTestUtil.randomLong();

	private static final long _GROUP_ID = RandomTestUtil.randomLong();

	@Mock
	private DLAppService _dlAppService;

	private final DocumentLibraryDDMFormFieldTemplateContextContributor
		_documentLibraryDDMFormFieldTemplateContextContributor =
			new DocumentLibraryDDMFormFieldTemplateContextContributor();

	@Mock
	private FileEntry _fileEntry;

	private final Html _html = new HtmlImpl();
	private final JSONFactory _jsonFactory = new JSONFactoryImpl();

	@Mock
	private ResourceBundle _resourceBundle;

}