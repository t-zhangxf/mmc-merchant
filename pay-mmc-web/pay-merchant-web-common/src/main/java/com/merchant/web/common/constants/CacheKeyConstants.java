package com.merchant.web.common.constants;

/**
 * 
 *  Cache Key Constants
 */
public class CacheKeyConstants {
	/**
	 * Separator of :
	 */
	public static final String SEPARATOR = ":";
	/**
	 * Key Prefix Of Mmc Users Prefix
	 */
	public static final String KEY_MERCHANT_USER_PREFIX = "mmc:merchant:user:";

	/**
	 * Key Prefix Of Mmc Users Lock Prefix
	 */
	public static final String KEY_MERCHANT_USER_PREFIX_LOCK = "mmc:merchant:user:lock:";

	/**
	 * Key Prefix Of Mmc Mail Token Prefix
	 */
	public static final String KEY_MERCHANT_MAIL_TOKEN_PREFIX = "mmc:merchant:mail:token:";
	public static final Long EXPIRED_TIME = 3600 * 24 * 1l;
}
