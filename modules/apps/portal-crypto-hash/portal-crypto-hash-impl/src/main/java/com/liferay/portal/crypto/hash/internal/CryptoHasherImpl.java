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

package com.liferay.portal.crypto.hash.internal;

import com.liferay.portal.crypto.hash.CryptoHasher;
import com.liferay.portal.crypto.hash.generation.CryptoHashGenerationResponse;
import com.liferay.portal.crypto.hash.verification.CryptoHashVerificationContext;
import com.liferay.portal.kernel.security.SecureRandomUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;

/**
 * @author Arthur Chan
 * @author Carlos Sierra Andrés
 */
@Component(service = CryptoHasher.class)
public class CryptoHasherImpl implements CryptoHasher {

	public CryptoHasherImpl() throws NoSuchAlgorithmException {
		_cryptoHashProvider = new CryptoHashProvider(
			"SHA-256",
			HashMapBuilder.put(
				"saltSize", 0
			).build());
	}

	@Override
	public CryptoHashGenerationResponse generate(byte[] input)
		throws Exception {

		byte[] pepper = null;
		String pepperId = null;

		final CryptoHashProviderResponse cryptoHashProviderResponse =
			_cryptoHashProvider.generate(
				pepper, _cryptoHashProvider.generateSalt(), input);

		return new CryptoHashGenerationResponse(
			cryptoHashProviderResponse.getHash(),
			new CryptoHashVerificationContext(
				pepperId, cryptoHashProviderResponse.getSalt(),
				cryptoHashProviderResponse.getCryptoHashProviderName(),
				cryptoHashProviderResponse.getCryptoHashProviderProperties()));
	}

	@Override
	public boolean verify(
			byte[] input, byte[] hash,
			CryptoHashVerificationContext... cryptoHashVerificationContexts)
		throws Exception {

		for (CryptoHashVerificationContext cryptoHashVerificationContext :
				cryptoHashVerificationContexts) {

			CryptoHashProvider cryptoHashProvider = new CryptoHashProvider(
				cryptoHashVerificationContext.getCryptoHashProviderName(),
				cryptoHashVerificationContext.
					getCryptoHashProviderProperties());

			// process salt

			Optional<byte[]> optionalSalt =
				cryptoHashVerificationContext.getSaltOptional();

			final CryptoHashProviderResponse hashProviderResponse =
				cryptoHashProvider.generate(
					null, optionalSalt.orElse(null), input);

			input = hashProviderResponse.getHash();
		}

		return Arrays.equals(input, hash);
	}

	private final CryptoHashProvider _cryptoHashProvider;

	private static class CryptoHashProvider {

		public CryptoHashProvider(
				String cryptoHashProviderName,
				Map<String, ?> cryptoHashProviderProperties)
			throws NoSuchAlgorithmException {

			_cryptoHashProviderName = cryptoHashProviderName;
			_cryptoHashProviderProperties = cryptoHashProviderProperties;

			_messageDigest = MessageDigest.getInstance(cryptoHashProviderName);
		}

		public CryptoHashProviderResponse generate(
			byte[] pepper, byte[] salt, byte[] input) {

			if (pepper == null) {
				pepper = new byte[0];
			}

			if (salt == null) {
				salt = new byte[0];
			}

			byte[] bytes = new byte[pepper.length + salt.length + input.length];

			System.arraycopy(pepper, 0, bytes, 0, pepper.length);
			System.arraycopy(salt, 0, bytes, pepper.length, salt.length);
			System.arraycopy(
				input, 0, bytes, pepper.length + salt.length, input.length);

			return new CryptoHashProviderResponse(
				_messageDigest.digest(bytes), salt, _cryptoHashProviderName,
				_cryptoHashProviderProperties);
		}

		public byte[] generateSalt() {
			int saltSize = (Integer)_cryptoHashProviderProperties.get(
				"saltSize");

			byte[] salt = new byte[saltSize];

			for (int i = 0; i < saltSize; ++i) {
				salt[i] = SecureRandomUtil.nextByte();
			}

			return salt;
		}

		private final String _cryptoHashProviderName;
		private final Map<String, ?> _cryptoHashProviderProperties;
		private final MessageDigest _messageDigest;

	}

	private static final class CryptoHashProviderResponse {

		public CryptoHashProviderResponse(
			byte[] hash, byte[] salt, String name, Map<String, ?> properties) {

			_hash = hash;
			_salt = salt;
			_name = name;
			_properties = properties;
		}

		public String getCryptoHashProviderName() {
			return _name;
		}

		public Map<String, ?> getCryptoHashProviderProperties() {
			return _properties;
		}

		public byte[] getHash() {
			return _hash;
		}

		public byte[] getSalt() {
			return _salt;
		}

		private final byte[] _hash;
		private final String _name;
		private final Map<String, ?> _properties;
		private final byte[] _salt;

	}

}