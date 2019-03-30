package com.merchant.web.client.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {
	public CustomException(){}
	private static final long serialVersionUID = 7585605292220861323L;
	private String errorCode;
	private String errorMsg;
	public CustomException(String errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public CustomException(String errorCode, String errorMsg, Exception exception) {
		super(errorMsg, exception);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
