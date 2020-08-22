<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
QuestionsConfiguration questionsConfiguration = portletDisplay.getPortletInstanceConfiguration(QuestionsConfiguration.class);

long categoryId = questionsConfiguration.rootTopicId();

MBCategory category = null;

String categoryName = StringPool.BLANK;

try {
	category = MBCategoryLocalServiceUtil.getCategory(categoryId);

	categoryName = category.getName();
}
catch (Exception exception) {
}
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="preferences--rootTopicId--" type="hidden" value="<%= categoryId %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset
				collapsible="<%= false %>"
				label="general-settings"
			>
				<aui:input name="preferences--showCardsForTopicNavigation--" type="checkbox" value="<%= questionsConfiguration.showCardsForTopicNavigation() %>" />

				<div class="form-group">
					<aui:input label="root-topic-id" name="categoryName" type="resource" value="<%= categoryName %>" />

					<aui:button name="selectCategoryButton" value="select" />

					<%
					String taglibRemoveFolder = "Liferay.Util.removeEntitySelection('rootTopicId', 'categoryName', this, '" + liferayPortletResponse.getNamespace() + "');";
					%>

					<aui:button disabled="<%= categoryId <= 0 %>" name="removeCategoryButton" onClick="<%= taglibRemoveFolder %>" value="remove" />
				</div>
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<script>
	var selectCategoryButton = document.getElementById(
		'<portlet:namespace />selectCategoryButton'
	);

	if (selectCategoryButton) {
		selectCategoryButton.addEventListener('click', function (event) {
			Liferay.Util.openSelectionModal({
				onSelect: function (event) {
					var form = document.<portlet:namespace />fm;

					Liferay.Util.setFormValues(form, {
						categoryName: Liferay.Util.unescape(event.name),
						rootTopicId: event.categoryid,
					});

					var removeCategoryButton = document.getElementById(
						'<portlet:namespace />removeCategoryButton'
					);

					if (removeCategoryButton) {
						Liferay.Util.toggleDisabled(removeCategoryButton, false);
					}
				},
				selectEventName: '<portlet:namespace />selectCategory',
				title: '<liferay-ui:message arguments="category" key="select-x" />',

				<%
				PortletURL selectCategoryURL = PortletProviderUtil.getPortletURL(request, MBCategory.class.getName(), PortletProvider.Action.EDIT);

				selectCategoryURL.setParameter("mvcRenderCommandName", "/message_boards/select_category");
				selectCategoryURL.setParameter("mbCategoryId", String.valueOf(categoryId));

				selectCategoryURL.setWindowState(LiferayWindowState.POP_UP);
				%>

				url: '<%= selectCategoryURL.toString() %>',
			});
		});
	}
</script>