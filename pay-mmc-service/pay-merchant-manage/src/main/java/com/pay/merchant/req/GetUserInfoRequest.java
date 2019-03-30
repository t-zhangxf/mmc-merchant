package com.pay.merchant.req;

import lombok.*;

import java.util.Date;

/**
 * 
 * @author liang_huiLing  Get User Info Request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserInfoRequest {
	/**
	 * 查询开始时间
	 */
	private Date merchantBeginTime;
	/**
	 * 查询结束时间
	 */
	private Date merchantEndTime;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 登录账号名
	 */
	private String email;
	/**
	 * 商户名
	 */
	private String merchantName;
	/**
	 * 商户号
	 */
	private String merchantNo;
	/**
	 * 分页页码
	 */
	private Integer pageNum;
	/**
	 * 分页页码
	 */
	private Integer pageSize;
}
