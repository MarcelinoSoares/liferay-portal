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

package com.liferay.layout.content.page.editor.web.internal.portlet.action;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.layout.content.page.editor.web.internal.comment.CommentUtil;
import com.liferay.layout.content.page.editor.web.internal.workflow.WorkflowUtil;
import com.liferay.portal.kernel.comment.Comment;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
		"mvc.command.name=/content_layout/edit_fragment_entry_link_comment"
	},
	service = MVCActionCommand.class
)
public class EditFragmentEntryLinkCommentMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		LayoutPermissionUtil.check(
			themeDisplay.getPermissionChecker(), themeDisplay.getLayout(),
			ActionKeys.UPDATE);

		long commentId = ParamUtil.getLong(actionRequest, "commentId");

		Comment comment = _commentManager.fetchComment(commentId);

		if (comment.getUserId() != themeDisplay.getUserId()) {
			throw new PrincipalException();
		}

		WorkflowUtil.withoutWorkflow(
			() -> _commentManager.updateComment(
				themeDisplay.getUserId(), FragmentEntryLink.class.getName(),
				comment.getClassPK(), commentId, String.valueOf(Math.random()),
				ParamUtil.getString(actionRequest, "body"),
				WorkflowUtil.getServiceContextFunction(
					_getWorkflowAction(actionRequest), actionRequest)));

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			actionRequest);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse,
			CommentUtil.getCommentJSONObject(
				_commentManager.fetchComment(commentId), httpServletRequest));
	}

	private int _getWorkflowAction(ActionRequest actionRequest) {
		boolean resolved = ParamUtil.getBoolean(actionRequest, "resolved");

		if (resolved) {
			return WorkflowConstants.ACTION_PUBLISH;
		}

		return WorkflowConstants.ACTION_SAVE_DRAFT;
	}

	@Reference
	private CommentManager _commentManager;

	@Reference
	private Portal _portal;

}