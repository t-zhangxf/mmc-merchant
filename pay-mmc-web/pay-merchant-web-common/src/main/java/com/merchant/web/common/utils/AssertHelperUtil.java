package com.merchant.web.common.utils;
import com.merchant.web.common.exception.BizException;

import java.util.Collection;
import java.util.List;

/**
 * 
 * @author williams.qian Self Definiation
 */
public class AssertHelperUtil {
	/**
	 * Assert Parameter Has Text
	 * 
	 * @param parameter
	 * @param errorCode
	 * @param errorMsg
	 */
	public static void hasText(String parameter, String errorCode, String errorMsg) {
		if (null == parameter || "".equals(parameter.trim())) {
			throw new BizException(errorCode, errorMsg);
		}
	}
	/**
	 * Assert Parameter is Not Null
	 * 
	 * @param parameter
	 * @param errorCode
	 * @param errorMsg
	 */
	public static void notNull(Object parameter, String errorCode, String errorMsg) {
		if (null == parameter) {
			throw new BizException(errorCode, errorMsg);
		}
	}

	/**
	 * Assert Parameter Collection is Not Empty
	 * 
	 * @param parameter
	 * @param errorCode
	 * @param errorMsg
	 */
	public static void notEmpty(@SuppressWarnings("rawtypes") Collection parameter, String errorCode, String errorMsg) {
		if (null == parameter || parameter.isEmpty()) {
			throw new BizException(errorCode, errorMsg);
		}
	}

	/**
	 * Assert Parameter Collection is Empty
	 * 
	 * @param parameter
	 * @param errorCode
	 * @param errorMsg
	 */
	public static void empty(@SuppressWarnings("rawtypes") Collection parameter, String errorCode, String errorMsg) {
		if (null != parameter && !parameter.isEmpty()) {
			throw new BizException(errorCode, errorMsg);
		}
	}

	/**
	 * 
	 * @param expression
	 * @param errorCode
	 * @param errorMsg
	 */
	public static void isTrue(boolean expression, String errorCode, String errorMsg) {
		if (false == expression) {
			throw new BizException(errorCode, errorMsg);
		}
	}
	public static void isNull(String expression[], String errorCode, String errorMsg) {
		if(expression==null||expression.length==0){
			throw new BizException(errorCode, errorMsg);
		}
	}
	public static void isNull(List list, String errorCode, String errorMsg) {
		if(list==null||list.isEmpty()){
			throw new BizException(errorCode, errorMsg);
		}
	}
	/**
	 * @desc  list not null
	 * @param list
	 * @param errorCode
	 * @param errorMsg
	 */
	public static void isNotNull(List list, String errorCode, String errorMsg) {
		if(list!=null&&!list.isEmpty()){
			throw new BizException(errorCode, errorMsg);
		}
	}
}
