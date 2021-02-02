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

package com.liferay.portal.crypto.hash.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.crypto.hash.CryptoHasher;
import com.liferay.portal.crypto.hash.generation.CryptoHashGenerationResponse;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Arthur Chan
 * @author Carlos Sierra Andrés
 */
@RunWith(Arquillian.class)
public class CryptoHasherTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testGenerationAndVerification() throws Exception {
		final CryptoHashGenerationResponse cryptoHashGenerationResponse =
			_cryptoHasher.generate(_PASSWORD.getBytes());

		Assert.assertTrue(
			_cryptoHasher.verify(
				_PASSWORD.getBytes(), cryptoHashGenerationResponse.getHash(),
				cryptoHashGenerationResponse.getSalt()));

		Assert.assertFalse(
			_cryptoHasher.verify(
				_WRONG_PASSWORD.getBytes(),
				cryptoHashGenerationResponse.getHash(),
				cryptoHashGenerationResponse.getSalt()));
	}

	@Test
	public void testVerificationWithFixedHashAndSalt() throws Exception {
		_cryptoHasher.verify(
			_PASSWORD.getBytes(), _PASSWORD_HASH_WITH_SALT, _SALT_1.getBytes());
	}

	private static final String _PASSWORD = "password";

	private static final byte[] _PASSWORD_HASH_WITH_SALT =
		UnicodeFormatter.hexToBytes(
			"ee765094649dcc6b5e89a91663cbeb80ecceed035e13201da471a97d30534f57" +
				"1dd8974729feb4e1696485b1e054672d91c9e774514921c067028a46bcb6" +
					"f1c5");

	private static final String _SALT_1 = "salt1";

	private static final String _WRONG_PASSWORD = "wrongPassword";

	@Inject
	private CryptoHasher _cryptoHasher;

}