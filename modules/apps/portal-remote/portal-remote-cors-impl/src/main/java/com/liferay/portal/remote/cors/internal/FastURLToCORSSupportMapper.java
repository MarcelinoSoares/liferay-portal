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

package com.liferay.portal.remote.cors.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Arthur Chan
 * @author Carlos Sierra Andrés
 */
public class FastURLToCORSSupportMapper extends URLToCORSSupportMapper {

	public FastURLToCORSSupportMapper(Map<String, CORSSupport> corsSupports) {
		super(corsSupports);
	}

	@Override
	public CORSSupport get(String urlPath) {
		CORSSupport corsSupport = _getWildcardCORSSupport(urlPath);

		if (corsSupport != null) {
			return corsSupport;
		}

		return _getExtensionCORSSupport(urlPath);
	}

	@Override
	protected void put(Map<String, CORSSupport> corsSupports) {
		int maxURLPatternLength = 0;

		for (Map.Entry<String, CORSSupport> entry : corsSupports.entrySet()) {
			String urlPattern = entry.getKey();

			if (urlPattern.length() > maxURLPatternLength) {
				maxURLPatternLength = urlPattern.length();
			}
		}

		if (maxURLPatternLength < 1) {
			_maxURLPatternLength = 64;
		}
		else {
			_maxURLPatternLength = maxURLPatternLength;
		}

		_trieMatrixExtension =
			new long[2][maxURLPatternLength][_ASCII_CHARACTER_RANGE];
		_trieMatrixWildcard =
			new long[2][maxURLPatternLength][_ASCII_CHARACTER_RANGE];

		_corsSupportsExtension = new ArrayList<>(Long.SIZE);
		_corsSupportsWildcard = new ArrayList<>(Long.SIZE);

		for (Map.Entry<String, CORSSupport> entry : corsSupports.entrySet()) {
			_put(entry.getKey(), entry.getValue());
		}
	}

	private static int _getFirstSetBitIndex(long bitMask) {
		int firstSetBitIndex = -1;

		if (bitMask == 0) {
			return firstSetBitIndex;
		}

		firstSetBitIndex = 63;

		long currentBitMask = bitMask << 32;

		if (currentBitMask != 0) {
			firstSetBitIndex -= 32;
			bitMask = currentBitMask;
		}

		currentBitMask = bitMask << 16;

		if (currentBitMask != 0) {
			firstSetBitIndex -= 16;
			bitMask = currentBitMask;
		}

		currentBitMask = bitMask << 8;

		if (currentBitMask != 0) {
			firstSetBitIndex -= 8;
			bitMask = currentBitMask;
		}

		currentBitMask = bitMask << 4;

		if (currentBitMask != 0) {
			firstSetBitIndex -= 4;
			bitMask = currentBitMask;
		}

		currentBitMask = bitMask << 2;

		if (currentBitMask != 0) {
			firstSetBitIndex -= 2;
			bitMask = currentBitMask;
		}

		currentBitMask = bitMask << 1;

		if (currentBitMask != 0) {
			firstSetBitIndex -= 1;
		}

		return firstSetBitIndex;
	}

	private int _getExactIndex(String urlPath, long[][][] trieMatrix) {
		int row = 0;
		long bitMask = _ALL_BITS_SET_BITMASK;
		int column = 0;

		for (; row < urlPath.length(); ++row) {
			if (row > (_maxURLPatternLength - 1)) {
				bitMask = 0;

				break;
			}

			char character = urlPath.charAt(row);

			column = character - _ASCII_PRINTABLE_OFFSET;

			bitMask &= trieMatrix[0][row][column];

			if (bitMask == 0) {
				break;
			}
		}

		if (bitMask != 0) {
			bitMask &= trieMatrix[1][row - 1][column];

			if (bitMask != 0) {
				return _getFirstSetBitIndex(bitMask);
			}
		}

		return -1;
	}

	private CORSSupport _getExtensionCORSSupport(String urlPath) {
		int urlPathLength = urlPath.length();
		long currentBitMask = _ALL_BITS_SET_BITMASK;

		for (int row = 0; row < urlPathLength; ++row) {
			if (row > (_maxURLPatternLength - 1)) {
				break;
			}

			char character = urlPath.charAt(urlPathLength - 1 - row);

			if (character == '/') {
				break;
			}

			int column = character - _ASCII_PRINTABLE_OFFSET;

			currentBitMask &= _trieMatrixExtension[0][row][column];

			if (currentBitMask == 0) {
				break;
			}

			if ((character == '.') && ((row + 1) < _maxURLPatternLength)) {
				long bitMask =
					currentBitMask &
					_trieMatrixExtension[1][row + 1][_INDEX_STAR];

				if (bitMask != 0) {
					return _corsSupportsExtension.get(
						_getFirstSetBitIndex(bitMask));
				}

				break;
			}
		}

		return null;
	}

	private CORSSupport _getWildcardCORSSupport(String urlPath) {
		boolean onlyExact = false;
		int urlPathLength = urlPath.length();
		boolean onlyWildcard = false;

		if (urlPath.charAt(0) != '/') {
			onlyExact = true;
		}
		else if ((urlPathLength > 1) &&
				 (urlPath.charAt(urlPathLength - 2) == '/') &&
				 (urlPath.charAt(urlPathLength - 1) == '*')) {

			onlyWildcard = true;
		}

		int row = 0;
		int col = 0;
		long currentBitMask = _ALL_BITS_SET_BITMASK;
		long bestMatchBitMask = 0;

		for (; row < urlPathLength; ++row) {
			if (row > (_maxURLPatternLength - 1)) {
				currentBitMask = 0;

				break;
			}

			char character = urlPath.charAt(row);

			col = character - _ASCII_PRINTABLE_OFFSET;

			currentBitMask &= _trieMatrixWildcard[0][row][col];

			if (currentBitMask == 0) {
				break;
			}

			if (!onlyExact && (character == '/') &&
				((row + 1) < _maxURLPatternLength)) {

				long bitMask =
					currentBitMask &
					_trieMatrixWildcard[1][row + 1][_INDEX_STAR];

				if (bitMask != 0) {
					bestMatchBitMask = bitMask;
				}
			}
		}

		if (currentBitMask == 0) {
			if (bestMatchBitMask == 0) {
				return null;
			}

			return _corsSupportsWildcard.get(
				_getFirstSetBitIndex(bestMatchBitMask));
		}

		if (onlyExact) {
			long bitMask =
				currentBitMask & _trieMatrixWildcard[1][row - 1][col];

			if (bitMask != 0) {
				return _corsSupportsWildcard.get(_getFirstSetBitIndex(bitMask));
			}

			return null;
		}

		if (!onlyWildcard) {
			long bitMask =
				currentBitMask & _trieMatrixWildcard[1][row - 1][col];

			if (bitMask != 0) {
				return _corsSupportsWildcard.get(_getFirstSetBitIndex(bitMask));
			}
		}

		long extraBitMask =
			currentBitMask & _trieMatrixWildcard[0][row][_INDEX_SLASH];

		extraBitMask &= _trieMatrixWildcard[0][row + 1][_INDEX_STAR];
		extraBitMask &= _trieMatrixWildcard[1][row + 1][_INDEX_STAR];

		if (extraBitMask != 0) {
			return _corsSupportsWildcard.get(
				_getFirstSetBitIndex(extraBitMask));
		}

		return _corsSupportsWildcard.get(
			_getFirstSetBitIndex(bestMatchBitMask));
	}

	private void _put(String urlPattern, CORSSupport corsSupport)
		throws IllegalArgumentException {

		if (corsSupport == null) {
			throw new IllegalArgumentException("Value can not be null");
		}

		if (isWildcardURLPattern(urlPattern)) {
			_put(urlPattern, corsSupport, true);

			return;
		}

		if (isExtensionURLPattern(urlPattern)) {
			_put(urlPattern, corsSupport, false);

			return;
		}

		_put(urlPattern, corsSupport, true);
	}

	private void _put(
		String urlPattern, CORSSupport corsSupport, boolean wildcard) {

		long[][][] trieMatrix = null;
		List<CORSSupport> corsSupports = null;

		if (wildcard) {
			trieMatrix = _trieMatrixWildcard;
			corsSupports = _corsSupportsWildcard;
		}
		else {
			trieMatrix = _trieMatrixExtension;
			corsSupports = _corsSupportsExtension;
		}

		if ((wildcard && (_storedURLPatternsWildcard > 63)) ||
			(!wildcard && (_storedURLPatternsExtension > 63))) {

			throw new IllegalArgumentException(
				"Exceeding maximum number of allowed URL patterns");
		}

		int index = _getExactIndex(urlPattern, trieMatrix);

		if (index > -1) {
			corsSupports.add(index, corsSupport);

			return;
		}

		if (wildcard) {
			index = _storedURLPatternsWildcard++;
		}
		else {
			index = _storedURLPatternsExtension++;
		}

		int row = 0;
		int urlPatternLength = urlPattern.length();
		int column = 0;
		long bitMask = 1L << index;

		for (; row < urlPatternLength; ++row) {
			char character;

			if (wildcard) {
				character = urlPattern.charAt(row);
			}
			else {
				character = urlPattern.charAt(urlPatternLength - 1 - row);
			}

			column = character - _ASCII_PRINTABLE_OFFSET;

			trieMatrix[0][row][column] |= bitMask;
		}

		trieMatrix[1][row - 1][column] |= bitMask;

		corsSupports.add(index, corsSupport);
	}

	private static final long _ALL_BITS_SET_BITMASK = ~0;

	private static final byte _ASCII_CHARACTER_RANGE = 96;

	private static final byte _ASCII_PRINTABLE_OFFSET = 32;

	private static final int _INDEX_SLASH = '/' - _ASCII_PRINTABLE_OFFSET;

	private static final int _INDEX_STAR = '*' - _ASCII_PRINTABLE_OFFSET;

	private List<CORSSupport> _corsSupportsExtension;
	private List<CORSSupport> _corsSupportsWildcard;
	private int _maxURLPatternLength;
	private int _storedURLPatternsExtension;
	private int _storedURLPatternsWildcard;
	private long[][][] _trieMatrixExtension;
	private long[][][] _trieMatrixWildcard;

}