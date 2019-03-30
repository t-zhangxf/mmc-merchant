package com.merchant.web.common.entity.request;

import lombok.*;

/**
 * 
 * @author liang_hl Trade DetailQueryRequest
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeDetailQueryRequest {
	/**
	 * 商户号
	 */
	private String merchantId;
	/**
	 * 商户订单号
	 */
	private String orderId;
}
