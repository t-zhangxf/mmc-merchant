package com.pay.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 
 * @author williams.qian Generate String No Util
 */
public class GenerateNoUtil {

	/**
	 * Get Random String Of Length 32
	 * 
	 * @return
	 */
	public static String randomStr32() {
		String dateTime = DateUtil.getNow17();
		String randomS = RandomStringUtils.randomAlphabetic(15);
		return dateTime + randomS;
	}

	/**
	 * Generate Order No
	 * 
	 * @return
	 */
	public static String generateOrderNo() {
		String randomS = RandomStringUtils.randomAlphabetic(19);
		return System.currentTimeMillis() + randomS;
	}

	/**
	 * Get Random String by self prefix String
	 * 
	 * @param prefix
	 * @return
	 */
	public static String randomStr(String prefix) {
		if (EmptyUtil.isNotEmpty(prefix)) {
			String dateTime = DateUtil.getNow17();
			String randomS = RandomStringUtils.randomAlphabetic(12);
			return prefix + dateTime + randomS;
		} else {
			return randomStr32();
		}
	}
	
}
