package com.merchant.web.client.handler;
import com.merchant.web.client.exception.CustomException;
import com.merchant.web.common.entity.result.Result;
import com.merchant.web.common.enums.SystemEnum;
import com.merchant.web.common.enums.UserEnums;
import com.merchant.web.common.exception.BizException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class CustomerExceptionHandler {
	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<Object> handleBizException(CustomException e, WebRequest request) {
		CustomException bizException =new CustomException();
		if(e instanceof CustomException){
			 bizException = (CustomException) e;
			log.error("Biz Exception,Error Code:{},Error Msg:{}", bizException.getErrorCode(),bizException.getErrorMsg(), e);
		}
		return new ResponseEntity<Object>(Result.buildResult(bizException.getErrorCode(), bizException.getErrorMsg()),HttpStatus.OK);
	}

	public CustomerExceptionHandler() {
	}

	@ExceptionHandler(value = BizException.class)
	public ResponseEntity<Object> handleBizException(BizException e, WebRequest request) {
		BizException bizException =new BizException();
		if(e instanceof BizException){
			bizException = (BizException) e;
			log.error("Biz Exception,Error Code:{},Error Msg:{}", bizException.getErrorCode(),bizException.getErrorMsg(), e);
		}
		return new ResponseEntity<Object>(Result.buildResult(bizException.getErrorCode(), bizException.getErrorMsg()),HttpStatus.OK);
	}
	@ExceptionHandler(value = { Exception.class})
	public ResponseEntity<Object> handleOtherException(Exception e, WebRequest request) {
		e.printStackTrace();
		log.error("System Exception:", e.getMessage());
		if(StringUtils.isEmpty(e.getMessage())){
			return new ResponseEntity<Object>(Result.buildResult(SystemEnum.SYSTEM_BAD_RESPONSE.getCode(),SystemEnum.SYSTEM_BAD_RESPONSE.getDesc()),HttpStatus.OK);
		}
		return new ResponseEntity<Object>(Result.buildResult(SystemEnum.SYSTEM_BAD_RESPONSE.getCode(),e.getMessage()),HttpStatus.OK);
	}

	/**
	 * @desc FeignException
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = FeignException.class)
	public ResponseEntity<Object> handleFeignException(Exception e, WebRequest request) {
		log.error("System Exception:", e);
		FeignException feignException=null;
		if(e instanceof FeignException){
			feignException = (FeignException) e;
			return new ResponseEntity<Object>(Result.buildResult(SystemEnum.SYSTEM_BAD_RESPONSE.getCode()+"", feignException.getMessage()),HttpStatus.OK);
		}
		return new ResponseEntity<Object>(Result.buildResult(SystemEnum.SYSTEM_BAD_RESPONSE.getCode()+"", SystemEnum.SYSTEM_BAD_RESPONSE.getDesc()),HttpStatus.OK);
	}
	/**
	 * @desc 用户不存在异常
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = UnknownAccountException.class)
	public ResponseEntity<Object> handleUnknownAccount(UnknownAccountException e, WebRequest request) {
		log.error("System Exception:", e);
		UnknownAccountException unknownAccountException=null;
		if(e instanceof UnknownAccountException){
			return new ResponseEntity<Object>(Result.buildResult(UserEnums.USER_ACCOUNT_IS_NOT_EXITS.getCode(), UserEnums.USER_ACCOUNT_IS_NOT_EXITS.getDesc()),HttpStatus.OK);
		}
		return new ResponseEntity<Object>(Result.buildResult(SystemEnum.SYSTEM_BAD_RESPONSE.getCode()+"", SystemEnum.SYSTEM_BAD_RESPONSE.getDesc()),HttpStatus.OK);
	}

	@ExceptionHandler(value = IncorrectCredentialsException.class)
	public ResponseEntity<Object> IncorrectCredentialsException(IncorrectCredentialsException e, WebRequest request) {
		log.error("System Exception:", e);
		IncorrectCredentialsException unknownAccountException=null;
		if(e instanceof IncorrectCredentialsException){
			return new ResponseEntity<Object>(Result.buildResult(UserEnums.USER_INCORRECT_CREDENTIALS.getCode(), e.getMessage()),HttpStatus.OK);
		}
		return new ResponseEntity<Object>(Result.buildResult(SystemEnum.SYSTEM_BAD_RESPONSE.getCode()+"", SystemEnum.SYSTEM_BAD_RESPONSE.getDesc()),HttpStatus.OK);
	}
	@ExceptionHandler(value = ExcessiveAttemptsException.class)
	public ResponseEntity<Object> ExcessiveAttemptsException(ExcessiveAttemptsException e, WebRequest request) {
		log.error("System Exception:", e);
		ExcessiveAttemptsException unknownAccountException=null;
		if(e instanceof ExcessiveAttemptsException){
			return new ResponseEntity<Object>(Result.buildResult(UserEnums.USER_STATUS_IS_LOCKED.getCode(), e.getMessage()),HttpStatus.OK);
		}
		return new ResponseEntity<Object>(Result.buildResult(SystemEnum.SYSTEM_BAD_RESPONSE.getCode()+"", SystemEnum.SYSTEM_BAD_RESPONSE.getDesc()),HttpStatus.OK);
	}
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> methodNotSupportHandle(HttpRequestMethodNotSupportedException e,WebRequest request) {
		return new ResponseEntity<Object>(Result.buildResult(SystemEnum.SYSTEM_REQUEST_METHOD_BAD.getCode()+"", SystemEnum.SYSTEM_REQUEST_METHOD_BAD.getDesc()),HttpStatus.OK);
	}
}
