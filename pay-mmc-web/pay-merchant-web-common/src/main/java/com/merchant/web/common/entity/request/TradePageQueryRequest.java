package com.merchant.web.common.entity.request;

import lombok.*;

/**
 * 
 * @author liang_hl Trade PageQueryRequest
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradePageQueryRequest {

	/**
	 * 商户号
	 */
	private String merchantId;
	/**
	 * 商户订单号
	 */
	private String orderId;
	/**
	 * 分页页码
	 */
	private Integer pageNum;
	/**
	 * 分页大小
	 */
	private Integer pageSize;
	/**
	 * 支付渠道
	 */
	private String payChannel;
	/**
	 * 业务订单号（交易单号）
	 */
	private String tradeNo;
	/**
	 * 订单状态,0：待支付，1：支付成功，2：支付失败
	 */
	private Integer status;
	/**
	 * 开始时间
	 */
	private String timeFrom;
	/**
	 * 结束时间
	 */
	private String timeTo;
}
