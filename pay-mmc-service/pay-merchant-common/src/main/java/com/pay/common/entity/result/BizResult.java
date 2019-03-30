package com.pay.common.entity.result;

import com.pay.common.constants.CommonConstants;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author williams.qian
 *	
 * Base Api Response
 * @param <T>
 */
@Getter
@Setter
public class BizResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Return Code
	 */
	private String bizCode;
	/**
	 * Return Message
	 */
	private String message;
	/**
	 * Return Result Data
	 */
	private T data;

	private String date;
	
	/**
	 * Create Api Response
	 * @param returnCode
	 * @param message
	 * @param t
	 * @return
	 */
	public static <T> BizResult<T> create(String returnCode, String message, T t) {
		BizResult<T> response = new BizResult<T>();
		response.setBizCode(returnCode);
		response.setMessage(message);
		response.setData(t);
		response.setDate(new Date().getTime()+"");
		return response;
	}
	
	/**
	 * Build Error Api Response
	 * @param error
	 * @param message
	 * @return
	 */
	public static <T> BizResult<T> error(String error, String message) {
		return create(error, message, null);
	}
	
	/**
	 * Build Success Response
	 * @param t
	 * @return
	 */
	public static <T> BizResult<T> success(T t) {
		return create(CommonConstants.RESPONSE_CODE_OK,"Success", t);
	}
}
