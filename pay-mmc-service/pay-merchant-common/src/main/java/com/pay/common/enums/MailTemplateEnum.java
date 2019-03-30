package com.pay.common.enums;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public enum MailTemplateEnum {
	m001("m001","m001.vm", "管理员账号新注册待激活", "欢迎启用SHAREit商户服务"),
	m002("m002","m002.vm", "管理员账号已激活", "欢迎启用SHAREit商户服务"),
	m003("m003","m003.vm", "权限调整通知", "账号变动通知"),
	m004("m004","m004.vm", "账号密码重设申请邮件", "账号密码重设申请邮件"),
	m005("m005","m005.vm", "添加成员-账号已注册", "商户平台访问权限开启通知"),
	m006("m006","m006.vm", "添加成员-账号新注册", "欢迎使用商户平台服务，请激活您的账号"),
	m007("m007","m007.vm", "解绑变动通知", "账号变动通知"),
	m011("m011","m011.vm", "Login ID is created & needs activation", "Welcome to SHAREit Merchant Platform."),
	m022("m022","m022.vm", "Login ID already exists", "Welcome to SHAREit Merchant Platform."),
	m033("m033","m033.vm", "Adjusted the type of combined operator ID", "Notification - Access permission has changed."),
	m044("m044","m044.vm", "Reset Password", "Apply to Reset Login Password"),
	m055("m055","m055.vm", "active user",  "Merchant Service is authorized"),
	m066("m066","m066.vm", "new user",  "Welcome to SHAREit Merchant Platform. Please active your Login ID"),
	m077("m077","m077.vm", "Unlinked an Admin ID with a Merchant ID", "Notification - Access permission has changed."),
	;
	private final String templateId;

	private final String templateName;

	private final String description;

	private final String subject;

	private MailTemplateEnum(String templateId,String templateName, String description, String subject) {
		this.templateId=templateId;
		this.templateName = templateName;
		this.description = description;
		this.subject = subject;
	}

	public String getTemplateId() {
		return templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public String getDescription() {
		return description;
	}

	public String getSubject() {
		return subject;
	}

	/**
	 * Get template By templateId
	 *
	 * @param templateId
	 * @return
	 */
	public final static MailTemplateEnum getByTemplateId(String templateId) {
		for (MailTemplateEnum t : MailTemplateEnum.values()) {
			if (t.getTemplateId() == templateId) {
				return t;
			}
		}
		return null;
	}
}
