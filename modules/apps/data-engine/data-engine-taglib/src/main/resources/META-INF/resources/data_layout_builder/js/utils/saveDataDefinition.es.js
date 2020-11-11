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

import {addItem, updateItem} from './client.es';

/**
 * Normalize field
 * @param {Array} availableLanguageIds
 * @param {Object} field
 * @param {String?} defaultLanguageId
 * @description some fields are translated with the language of
 * themeDisplay.getDefaultLanguageId() which is not necessarily the language of dataDefinition,
 * so we need to normalize all fields so that they receive themeDisplay.getDefaultLanguageId()
 */
const normalizeField = (
	availableLanguageIds,
	field,
	defaultLanguageId = themeDisplay.getDefaultLanguageId()
) => {
	const toArray = (value) =>
		availableLanguageIds.reduce((accumulator, currentValue) => {
			accumulator[currentValue] =
				value[currentValue] || value[defaultLanguageId] || '';

			return accumulator;
		}, {});

	const toString = (value) =>
		availableLanguageIds.reduce((accumulator, currentValue) => {
			if (value[currentValue] && value[currentValue].length) {
				accumulator[currentValue] = value[currentValue];
			}
			else {
				accumulator[currentValue] = value[defaultLanguageId];
			}

			return accumulator;
		}, {});

	const {defaultValue, label, tip} = field;
	const {options, placeholder, tooltip} = field.customProperties;

	return {
		...field,
		...(defaultValue && {
			defaultValue: Array.isArray(defaultValue[defaultLanguageId])
				? toString(defaultValue)
				: toArray(defaultValue),
		}),
		...(label && {label: toArray(label)}),
		...(tip && {tip: toArray(tip)}),
		customProperties: {
			...field.customProperties,
			...(options && {
				options: toString(options),
			}),
			...(placeholder && {
				placeholder: toArray(placeholder),
			}),
			...(tooltip && {
				tooltip: toArray(tooltip),
			}),
		},
		nestedDataDefinitionFields: field.nestedDataDefinitionFields.length
			? field.nestedDataDefinitionFields.map((nestedField) => ({
					...nestedField,
					...normalizeField(
						availableLanguageIds,
						nestedField,
						defaultLanguageId
					),
			  }))
			: [],
	};
};

/**
 * Normalize Data Definition
 * @param {Object} dataDefinition
 * @param {String?} defaultLanguageId
 */

export const normalizeDataDefinition = (
	dataDefinition,
	defaultLanguageId = themeDisplay.getDefaultLanguageId()
) => {
	return {
		...dataDefinition,
		dataDefinitionFields: dataDefinition.dataDefinitionFields.map((field) =>
			normalizeField(
				dataDefinition.availableLanguageIds,
				field,
				defaultLanguageId
			)
		),
		name: dataDefinition.availableLanguageIds.reduce(
			(accumulator, currentValue) => {
				accumulator[currentValue] =
					dataDefinition.name[currentValue] ||
					dataDefinition.name[defaultLanguageId] ||
					'';

				return accumulator;
			},
			{}
		),
	};
};

/**
 * Normalize Data Layout
 * @param {Object} dataLayout
 * @param {String?} defaultLanguageId
 */

export const normalizeDataLayout = (
	dataLayout,
	defaultLanguageId = themeDisplay.getDefaultLanguageId()
) => {
	return {
		...dataLayout,
		dataLayoutPages: dataLayout.dataLayoutPages.map((dataLayoutPage) => ({
			...dataLayoutPage,
			dataLayoutRows: (dataLayoutPage.dataLayoutRows || []).map(
				(dataLayoutRow) => ({
					...dataLayoutRow,
					dataLayoutColumns: (
						dataLayoutRow.dataLayoutColumns || []
					).map((dataLayoutColumn) => ({
						...dataLayoutColumn,
						fieldNames: dataLayoutColumn.fieldNames || [],
					})),
				})
			),
			description: {
				...dataLayout.description,
				[defaultLanguageId]:
					dataLayoutPage.description[defaultLanguageId] || '',
			},
			title: {
				...dataLayoutPage.title,
				[defaultLanguageId]:
					dataLayoutPage.title[defaultLanguageId] || '',
			},
		})),
		dataRules: dataLayout.dataRules.map((rule) => {
			delete rule.ruleEditedIndex;

			return rule;
		}),
	};
};

export default ({
	dataDefinition,
	dataDefinitionId,
	dataLayout,
	dataLayoutId,
}) => {
	const normalizedDataDefinition = normalizeDataDefinition(dataDefinition);
	const normalizedDataLayout = normalizeDataLayout(dataLayout);

	const updateDefinition = () =>
		updateItem(
			`/o/data-engine/v2.0/data-definitions/${dataDefinitionId}`,
			normalizedDataDefinition
		);

	if (dataLayoutId) {
		return updateDefinition().then(() =>
			updateItem(
				`/o/data-engine/v2.0/data-layouts/${dataLayoutId}`,
				normalizedDataLayout
			)
		);
	}

	return updateDefinition().then(() =>
		addItem(
			`/o/data-engine/v2.0/data-definitions/${dataDefinitionId}/data-layouts`,
			normalizedDataLayout
		)
	);
};
