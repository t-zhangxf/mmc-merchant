package com.pay.merchant.req;

import lombok.*;

/**
 * 
 * @author liang_huiLing  Unbunding Request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnbundingRequest {
	/**
	 * 账号名
	 */
	private String email;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 关联商户号
	 */
	private String merchantNo;
}
