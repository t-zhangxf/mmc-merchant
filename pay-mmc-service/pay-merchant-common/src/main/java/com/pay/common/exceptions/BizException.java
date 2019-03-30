package com.pay.common.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author williams.qian Biz Exception
 */
@Getter
@Setter
public class BizException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1303437917234498631L;
	/**
	 * Error Code
	 */
	private String errorCode;
	/**
	 * Error Msg
	 */
	private String errorMsg;
	public BizException() {}
	public BizException(String errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public BizException(String errorCode, String errorMsg, Exception exception) {
		super(errorMsg, exception);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
