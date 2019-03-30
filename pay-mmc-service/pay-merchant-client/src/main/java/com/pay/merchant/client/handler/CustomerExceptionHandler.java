package com.pay.merchant.client.handler;
import com.pay.common.entity.result.BizResult;
import com.pay.common.enums.SystemEnum;
import com.pay.common.exceptions.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class CustomerExceptionHandler {
	@ExceptionHandler(value = BizException.class)
	public ResponseEntity<Object> handleBizException(BizException e, WebRequest request) {
		BizException bizException =new BizException();
		if(e instanceof BizException){
			bizException = (BizException) e;
			log.error("Biz Exception,Error Code:{},Error Msg:{}", bizException.getErrorCode(),bizException.getErrorMsg(), e);
		}
		return new ResponseEntity<Object>(BizResult.create(bizException.getErrorCode(), bizException.getErrorMsg(),null),HttpStatus.OK);
	}

	@ExceptionHandler(value = { Exception.class})
	public ResponseEntity<Object> handleOtherException(Exception e, WebRequest request) {
		e.printStackTrace();
		log.error("System Exception:", e.getMessage());
		if(StringUtils.isEmpty(e.getMessage())){
			return new ResponseEntity<Object>(BizResult.create(SystemEnum.SYSTEM_BAD_RESPONSE.getCode(),SystemEnum.SYSTEM_BAD_RESPONSE.getDesc(),null),HttpStatus.OK);
		}
		return new ResponseEntity<Object>(BizResult.create(SystemEnum.SYSTEM_BAD_RESPONSE.getCode(),e.getMessage(),null),HttpStatus.OK);
	}
}
