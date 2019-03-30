package com.pay.common.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author williams.qian Empty Utils
 */
public class EmptyUtil {
	public static boolean isNotEmpty(String str) {
		return (null != str) && (!"".equals(str.trim()));
	}

	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isAnyEmpty(String... str) {
		boolean isEmpty = false;
		if (null == str || 0 == str.length) {
			isEmpty = true;
		} else {
			for (String s : str) {
				if (null == s || "".equals(s.trim())) {
					isEmpty = true;
					break;
				}
			}
		}
		return isEmpty;
	}

	public static boolean isNotNull(String str) {
		return null != str;
	}

	public static boolean isNull(String str) {
		return null == str;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(List list) {
		return (null != list) && (list.size() > 0);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list) {
		return (null == list) || (list.size() < 1);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Map map) {
		return (null != map) && (map.size() > 0);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Map map) {
		return (null == map) || (map.size() < 1);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Set set) {
		return (null != set) && (set.size() > 0);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Set set) {
		return (null == set) || (set.size() < 1);
	}

	public static boolean isNotNull(Object obj) {
		return (null != obj);
	}

	public static boolean isNull(Object obj) {
		return (null == obj);
	}
}
