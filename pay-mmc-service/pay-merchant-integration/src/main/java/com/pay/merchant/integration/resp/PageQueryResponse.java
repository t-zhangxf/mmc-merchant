package com.pay.merchant.integration.resp;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageQueryResponse<T> {
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
		 * total
		 */
		private Integer total;
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
			 * 创建时间
			 */
			private Date createTime;
			/**
			 * 商户名称
			 */
			private String merchantName;
			/**
			 * 商户编码
			 */
			private String merchantNo;
			/**
			 * 商户简称
			 */
			private String simpleName;

		}
	}


}
