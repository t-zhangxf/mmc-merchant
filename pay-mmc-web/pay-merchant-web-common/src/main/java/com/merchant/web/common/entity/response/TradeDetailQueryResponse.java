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
public class TradeDetailQueryResponse<T> {
	/**
	 * 返回码
	 */
	private String bizCode;
	/**
	 * 返回描述
	 */
	private String message;
	/**
	 * 返回数据
	 */
	private data data;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class data {
		/**
		 * 基本信息
		 */
		private baseInfo baseInfo;

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		@Builder
		public static class baseInfo {
			/**
			 * 支付金额
			 */
			private BigDecimal  amount;
			/**
			 * 订单创建时间
			 */
			private String createTimeUtc;
			/**
			 * 币种
			 */
			private String currency;
			/**
			 * 商户用户唯一标志
			 */
			private String custId;
			/**
			 * 商户订单号
			 */
			private String orderId;
			/**
			 * 商品信息
			 */
			private String productInfo;
			/**
			 * 订单状态
			 */
			private Integer status;
			/**
			 * 平台订单号（交易单号）
			 */
			private String tradeNo;
			/**
			 * 备注
			 */
			private String remark;
		}
		/**
		 * 支付信息
		 */
		private payInfo payInfo;

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		@Builder
		public static class payInfo {
			/**
			 * 支付时间
			 */
			private String createTimeUtc;
			/**
			 * 支付渠道
			 */
			private String payChannel;
			/**
			 * 支付渠道流水号
			 */
			private String payChannelOrderNo;
			/**
			 * 支付单号
			 */
			private String payOrderNo;
			/**
			 * 备注
			 */
			private String remark;
		}
		/**
		 * 退款信息
		 */
		private refundInfo refundInfo;

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		@Builder
		public static class refundInfo {

			private List<refundOrderList> refundOrderList;

			@Getter
			@Setter
			@NoArgsConstructor
			@AllArgsConstructor
			@Builder
			public static class refundOrderList {
				/**
				 * 退款理由：商户传入退款原因
				 */
				private String comment;
				/**
				 * 发起时间
				 */
				private String createTimeUtc;
				/**
				 * 处理时间
				 */
				private String processTimeUtc;
				/**
				 * 退款金额
				 */
				private BigDecimal refundAmount;
				/**
				 * 退款订单号
				 */
				private String refundId;
				/**
				 * 退款状态
				 */
				private Integer refundStatus;
				/**
				 * 退款备注：退款支付返回信息
				 */
				private String remark;
			}
		}
		/**
		 * 结算信息
		 */
		private settleInfo settleInfo;

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		@Builder
		public static class settleInfo {
			/**
			 * 结算金额
			 */
			private BigDecimal settleAmount;
			/**
			 * 结算费
			 */
			private BigDecimal settleFee;
			/**
			 * 结算状态
			 */
			private Integer settleStatus;
			/**
			 * 结算时间
			 */
			private String settleTimeUtc;
		}
	}


}
