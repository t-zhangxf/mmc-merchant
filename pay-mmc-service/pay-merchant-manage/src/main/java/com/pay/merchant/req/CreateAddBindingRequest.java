package com.pay.merchant.req;

import lombok.*;

/**
 * 
 * @author liang_huiLing  Merchant Add Request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAddBindingRequest {
	/**
	 * 邮箱账号
	 */
	private String email;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 商户号
	 */
	private String merchantNo;
	/**
	 * 账号类型（0：管理员，1：操作员）
	 */
	private byte userType;
}
