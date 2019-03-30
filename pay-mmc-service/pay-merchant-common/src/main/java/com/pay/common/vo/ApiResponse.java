package com.pay.common.vo;

import com.pay.common.constants.CommonConstants;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 
 * @author williams.qian
 *
 * Base Api Response
 * @param <T>
 */
@Getter
@Setter
public class ApiResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Return Code
	 */
	private String returnCode;
	/**
	 * Return Message
	 */
	private String message;
	/**
	 * Return Result Data
	 */
	private T result;
	
	/**
	 * Create Api Response
	 * @param returnCode
	 * @param message
	 * @param t
	 * @return
	 */
	public static <T> ApiResponse<T> create(String returnCode, String message, T t) {
		ApiResponse<T> response = new ApiResponse<T>();
		response.setReturnCode(returnCode);
		response.setMessage(message);
		response.setResult(t);
		return response;
	}
	
	/**
	 * Build Error Api Response
	 * @param error
	 * @param message
	 * @return
	 */
	public static <T> ApiResponse<T> error(String error, String message) {
		return create(error, message, null);
	}
	
	/**
	 * Build Success Response
	 * @param t
	 * @return
	 */
	public static <T> ApiResponse<T> success(T t) {
		return create(CommonConstants.RESPONSE_CODE_OK,"Success", t);
	}
}
