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

import ClayButton from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import classNames from 'classnames';
import ClayLabel from '@clayui/label';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {
	EDITABLE_FRAGMENT_ENTRY_PROCESSOR,
	FRAGMENTS_EDITOR_ITEM_TYPES
} from '../../../utils/constants';
import {getItemPath} from '../../../utils/FragmentsEditorGetUtils.es';
import useSelector from '../../../store/hooks/useSelector.es';

const getEditableValues = (itemId, itemType, structure, fragmentEntryLinks) => {
	const itemPath = getItemPath(itemId, itemType, structure);

	const fragmentEntryLinkItem = itemPath.find(
		item => item.itemType === FRAGMENTS_EDITOR_ITEM_TYPES.fragment
	);

	const editableItem = itemPath.find(
		item => item.itemType === FRAGMENTS_EDITOR_ITEM_TYPES.editable
	);

	if (fragmentEntryLinkItem && editableItem) {
		return (
			fragmentEntryLinks[fragmentEntryLinkItem.itemId].editableValues[
				EDITABLE_FRAGMENT_ENTRY_PROCESSOR
			][editableItem.itemId] || {}
		);
	}

	return {};
};

const MappedContent = props => {
	const [active, setActive] = useState(false);
	const {editURL, permissionsURL, viewUsagesURL} = props.actions;
	const fragmentEntryLinks = useSelector(state => state.fragmentEntryLinks);
	const hoveredItemId = useSelector(state => state.hoveredItemId);
	const hoveredItemType = useSelector(state => state.hoveredItemType);

	const openWindow = (uri, title) => {
		Liferay.Util.openWindow({
			dialog: {
				destroyOnHide: true,
				modal: true
			},
			dialogIframe: {
				bodyCssClass: 'dialog-with-footer'
			},
			title,
			uri
		});
	};

	const {classNameId, classPK} = props;

	const itemId = `${classNameId}-${classPK}`;

	let isMappedContentHovered = false;

	if (hoveredItemType === FRAGMENTS_EDITOR_ITEM_TYPES.editable) {
		const editableValues = getEditableValues(
			hoveredItemId,
			fragmentEntryLinks
		);

		isMappedContentHovered =
			editableValues.classNameId === classNameId &&
			editableValues.classPK === classPK;
	} else if (hoveredItemType === FRAGMENTS_EDITOR_ITEM_TYPES.mappedItem) {
		isMappedContentHovered = itemId === hoveredItemId;
	}

	const className = classNames({
		'fragments-editor__mapped-content': true,
		'fragments-editor__mapped-content--mapped-item-hovered': isMappedContentHovered
	});

	return (
		<li
			className={className}
			data-fragments-editor-item-id={itemId}
			data-fragments-editor-item-type={
				FRAGMENTS_EDITOR_ITEM_TYPES.mappedItem
			}
		>
			<div className="d-flex p-3">
				<div className="autofit-col autofit-col-expand">
					<strong className="list-group-title truncate-text">
						{props.title}
					</strong>

					<span className="text-secondary small">{props.name}</span>

					<span className="text-secondary small">
						{props.usagesCount === 1
							? Liferay.Language.get('used-in-1-page')
							: Liferay.Util.sub(
									Liferay.Language.get('used-in-x-pages'),
									props.usagesCount
							  )}
					</span>

					<ClayLabel
						className="align-self-start mt-2"
						displayType={props.status.style}
					>
						{props.status.label}
					</ClayLabel>
				</div>

				<ClayDropDown
					active={active}
					onActiveChange={setActive}
					trigger={
						<ClayButton
							className="text-secondary btn-monospaced btn-sm"
							displayType="unstyled"
						>
							<ClayIcon symbol="ellipsis-v" />
						</ClayButton>
					}
				>
					<ClayDropDown.ItemList>
						{editURL && (
							<ClayDropDown.Item href={editURL} key="editURL">
								{Liferay.Language.get('edit')}
							</ClayDropDown.Item>
						)}

						{permissionsURL && (
							<ClayDropDown.Item
								href="javascript:;"
								key="permissionsURL"
								onClick={() =>
									openWindow(
										permissionsURL,
										Liferay.Language.get('permissions')
									)
								}
							>
								{Liferay.Language.get('permissions')}
							</ClayDropDown.Item>
						)}

						{viewUsagesURL && (
							<ClayDropDown.Item
								href="javascript:;"
								key="viewUsagesURL"
								onClick={() =>
									openWindow(
										viewUsagesURL,
										Liferay.Language.get('view-usages')
									)
								}
							>
								{Liferay.Language.get('view-usages')}
							</ClayDropDown.Item>
						)}
					</ClayDropDown.ItemList>
				</ClayDropDown>
			</div>
		</li>
	);
};

MappedContent.propTypes = {
	actions: PropTypes.object,
	name: PropTypes.string.isRequired,
	status: PropTypes.shape({
		label: PropTypes.string,
		style: PropTypes.string
	}),
	title: PropTypes.string.isRequired,
	usagesCount: PropTypes.number.isRequired
};

export {MappedContent};
export default MappedContent;
