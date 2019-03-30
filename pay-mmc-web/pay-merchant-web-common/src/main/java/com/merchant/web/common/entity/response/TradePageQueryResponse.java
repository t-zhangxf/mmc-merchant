package com.merchant.web.common.entity.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradePageQueryResponse<T> {
	/**
	 * 返回码
	 */
	private String bizCode;
	/**
	 * 返回描述
	 */
	private String message;
	/**
	 * 页码
	 */
	private Integer pageNum;
	/**
	 * 每页记录数
	 */
	private Integer pageSize;
	/**
	 * 总记录数
	 */
	private Integer totalElements;

	private data data;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class data {
		/**
		 * 列信息
		 */
		private List<payOrderList> payOrderList;

		/**
		 * 列信息
		 */
		private List<rows> rows;

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		@Builder
		public static class rows {
			/**
			 * 支付金额
			 */
			private BigDecimal  amount;
			/**
			 * 订单创建时间
			 */
			private String createTimeUtc;
			/**
			 * 商户订单号
			 */
			private String orderId;
			/**
			 * 支付渠道
			 */
			private String payChannel;
			/**
			 * 业务订单号（交易单号）
			 */
			private String tradeNo;
			/**
			 * 支付状态
			 */
			private Integer payStatus;
			/**
			 * 	退款状态
			 */
			private Integer refundStatus;
		}

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		@Builder
		public static class payOrderList {
			/**
			 * 支付金额
			 */
			private BigDecimal  amount;
			/**
			 * 订单创建时间
			 */
			private String createTimeUtc;
			/**
			 * 商户订单号
			 */
			private String orderId;
			/**
			 * 支付渠道
			 */
			private String payChannel;
			/**
			 * 业务订单号（交易单号）
			 */
			private String tradeNo;
			/**
			 * 支付状态
			 */
			private Integer payStatus;
			/**
			 * 	退款状态
			 */
			private Integer refundStatus;
			/**
			 * 第三方渠道流水号
			 */
			private String channelTxnNo;
			/**
			 * 退款说明
			 */
			private String comment;
			/**
			 * 商户用户id
			 */
			private String custId;
			/**
			 *商品信息
			 */
			private String productInfo;
			/**
			 *退款金额
			 */
			private BigDecimal refundAmount;
			/**
			 * 退款创建Utc时间戳时间
			 */
			private String refundCreateTimeUtc;
			/**
			 * 退款申请号
			 */
			private String refundId;
			/**
			 *退款更新时间
			 */
			private String refundUpdateTimeUtc;
			/**
			 * 支付备注
			 */
			private String remark;
			/**
			 * 订单更新时间
			 */
			private String updateTimeUtc;
			/**
			 * 支付订单号
			 */
			private String payOrderNo;
		}
	}


}
