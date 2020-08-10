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
CommerceDiscountDisplayContext commerceDiscountDisplayContext = (CommerceDiscountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = commerceDiscountDisplayContext.getPortletURL();
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= PricingNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<div class="pt-4">
	<aui:form action="<%= portletURL.toString() %>" cssClass="container-fluid-1280" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
		<aui:input name="deleteDiscounts" type="hidden" />

		<commerce-ui:headless-dataset-display
			apiUrl="/o/headless-commerce-admin-pricing/v2.0/discounts"
			clayCreationMenu="<%= commerceDiscountDisplayContext.getClayCreationDiscountMenu() %>"
			clayHeadlessDataSetActionTemplates="<%= commerceDiscountDisplayContext.getClayHeadlessDataSetActionDiscountTemplates() %>"
			formId="fm"
			id="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_DISCOUNTS %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= portletURL %>"
			style="stacked"
		/>
	</aui:form>
</div>