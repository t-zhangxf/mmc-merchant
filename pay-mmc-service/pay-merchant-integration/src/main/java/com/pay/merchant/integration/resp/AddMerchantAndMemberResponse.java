package com.pay.merchant.integration.resp;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMerchantAndMemberResponse<T> {
	/**
	 * 返回码
	 */
	private String code;
	/**
	 * 返回描述
	 */
	private String msg;

	private data data;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class data {
		/**
		 * 会员ID
		 */
		private String memberId;
		/**
		 * 商户编号
		 */
		private String merchantNo;
	}

}
