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

package com.liferay.portal.kernel.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Michael C. Han
 * @author Shuyang Zhou
 * @author Marta Medio
 * @deprecated As of Mueller (7.2.x), Replaced by {@link
 *             InetAddressProviderUtil}
 */
@Deprecated
public class InetAddressUtil {

	public static InetAddress getInetAddressByName(String domain)
		throws UnknownHostException {

		return InetAddressProviderUtil.getInetAddressByName(domain);
	}

	public static String getLocalHostName() throws Exception {
		return InetAddressProviderUtil.getLocalHostName();
	}

	public static InetAddress getLoopbackInetAddress()
		throws UnknownHostException {

		return InetAddressProviderUtil.getLoopbackInetAddress();
	}

	public static boolean isLocalInetAddress(InetAddress inetAddress) {
		return InetAddressProviderUtil.isLocalInetAddress(inetAddress);
	}

}