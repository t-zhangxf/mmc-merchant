package com.pay.merchant.req;

import lombok.*;

/**
 * 
 * @author liang_huiLing  Resend Mail Request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResendMailRequest {
	/**
	 * 邮箱账号
	 */
	private String email;
	/**
	 * 商户号
	 */
	private String merchantNo;
	/**
	 * 邮件模板编号
	 */
	private String templateId;
}
