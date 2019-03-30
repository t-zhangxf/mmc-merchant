package com.pay.merchant.req;

import lombok.*;

/**
 * 
 * @author liang_huiLing  Mail By Template Request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailByTemplateRequest {
	/**
	 * 邮箱账号
	 */
	private String email;
	/**
	 * 跳转链接
	 */
	private String url;
	/**
	 * 邮件主题
	 */
	private String subject;
	/**
	 * 邮件模板
	 */
	private String templateName;
	/**
	 * 商户号
	 */
	private String mercId;
	/**
	 * 商户名称
	 */
	private String mercNm;
	/**
	 * 邀请人
	 */
	private String inviter;
	/**
	 * 权限变更描述
	 */
	private String description;

}
