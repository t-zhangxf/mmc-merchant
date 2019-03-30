package com.pay.merchant.integration.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author liang_hl Add Merchant And Member PageQueryRequest
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMerchantAndMemberRequest {

	/**
	 * 行业
	 */
	private String industry;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 商户类型 1：商户 2：个人
	 */
	private String merchantType;
	/**
	 * 商户简称
	 */
	private String simpleName;
}
