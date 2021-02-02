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

package com.liferay.portal.crypto.hash.verification;

import java.util.Map;
import java.util.Optional;

/**
 * @author Arthur Chan
 * @author Carlos Sierra Andrés
 */
public final class CryptoHashVerificationContext {

	public CryptoHashVerificationContext(
		String pepperId, byte[] salt, String cryptoHashProviderName,
		Map<String, ?> cryptoHashProviderProperties) {

		_pepperId = pepperId;
		_salt = salt;
		_cryptoHashProviderName = cryptoHashProviderName;
		_cryptoHashProviderProperties = cryptoHashProviderProperties;
	}

	public String getCryptoHashProviderName() {
		return _cryptoHashProviderName;
	}

	public Map<String, ?> getCryptoHashProviderProperties() {
		return _cryptoHashProviderProperties;
	}

	public Optional<String> getPepperIdOptional() {
		return Optional.ofNullable(_pepperId);
	}

	public Optional<byte[]> getSaltOptional() {
		return Optional.ofNullable(_salt);
	}

	private final String _cryptoHashProviderName;
	private final Map<String, ?> _cryptoHashProviderProperties;
	private final String _pepperId;
	private final byte[] _salt;

}