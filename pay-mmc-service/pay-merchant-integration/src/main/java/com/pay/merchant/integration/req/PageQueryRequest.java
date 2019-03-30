package com.pay.merchant.integration.req;

import lombok.*;

import java.util.Date;

/**
 * 
 * @author liang_hl Add Merchant And Member PageQueryRequest
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageQueryRequest {

	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 商户编码
	 */
	private String merchantNo;
	/**
	 * 分页页码
	 */
	private Integer pageIndex;
	/**
	 * 分页大小
	 */
	private Integer pageSize;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
}
