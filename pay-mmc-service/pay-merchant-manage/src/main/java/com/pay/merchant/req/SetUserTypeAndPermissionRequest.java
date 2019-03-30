package com.pay.merchant.req;

import lombok.*;

import java.util.List;

/**
 * 
 * @author liang_huiLing  Set UserType And Permission Request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetUserTypeAndPermissionRequest {
	/**
	 * 变更项编号（0：升级为管理员，1：调整为普通操作员）
	 */
	private Integer changeNo;
	/**
	 * 账户号
	 */
	private String email;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 商户号
	 */
	private String merchantNo;
	/**
	 * 权限编号（0：订单查询，1：订单下载）
	 */
	private Integer[] permissionId;
	/**
	 * 账号类型（0：管理员，1：操作员）
	 */
	private Byte userType;
}
