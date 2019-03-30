package com.merchant.web.common.utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
public class GenerateNoUtil {
	public static String randomStr32() {
		String dateTime = DateUtil.getNow17();
		String randomS = RandomStringUtils.randomAlphabetic(15);
		return dateTime + randomS;
	}
	public static String generateOrderNo() {
		String randomS = RandomStringUtils.randomAlphabetic(19);
		return System.currentTimeMillis() + randomS;
	}
	public static String randomStr(String prefix) {
		if (StringUtils.isNotEmpty(prefix)) {
			String dateTime = DateUtil.getNow17();
			String randomS = RandomStringUtils.randomAlphabetic(12);
			return prefix + dateTime + randomS;
		} else {
			return randomStr32();
		}
	}
	
}
