package com.pay.common.enums;

import lombok.Getter;

@Getter
public enum SystemEnum {
	SYSTEM_SUCCESS("200", "SYSTEM IS SUCCESS"),
	SYSTEM_FAILED("1404", "SYSTEM IS FAILED"),
	SYSTEM_ERROR("9999", "System unusually busy,Try Later"),
	SYSTEM_SINGER_ERROR("1501", "SYSTEM SINGER ERROR"),
	SYSTEM_FORBIDDEN_ERROR("1502", "SYSTEM SINGER ERROR"),
	SYSTEM_BAD_REQUEST("1503", "SYSTEM BAD REQUEST"),
	SYSTEM_NULL_DATA("1504", "SYSTEM DATA IS NULL"),
	SYSTEM_PARAMETER_NULL("1505","SYSTEM PARAMETER IS NULL"),
	VALIDATE_ERROR("1506",  "INVALID REQUEST ARGUMENTS"),
	SYSTEM_BAD_RESPONSE("1507", "SYSTEM BAD RESPONSE"),
	USER_NOT_EXIST("2001", "USER NOT EXIST"),
	MERCHANT_NOT_EXIST("2002", "MERCHANT NOT EXIST"),
	USER_TYPE_ILLEGAL("2003", "USER TYPE ILLEGAL"),
	BINDING_ILLEGAL("2004", "NO BINDING RELATIONSHIP"),
	ACCOUNT_ACTIVATED("2005", "Account has been activated"),
	MEMBER_NOT_EXIST("2006", "缺少会员信息，请补充该商户必要信息后再关联，可通过【管理账号】获取服务"),
	MERCHANT_LINKED_DUPLICATE("2007", "该商户已关联管理员账号，无法添加更多"),
	ILLEGAL_CHANGE("2008", "ILLEGAL CHANGE"),
	USER_MERCHANT_IS_NULL("6195", "user  merchant is null"),
	USER_INFO_IS_NOT_NULL("6194", "userInfo is null"),
	USER_EMAIL_IS_ACTIVE("624","user email had been actived"),
	USER_PERMISSION_IS_ERROR("6197", "user permission is error"),
	TEMPLATE_ID_ILLEGAL("2009", "TEMPLATE ID ILLEGAL");
	private String code;
    private String desc;
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	SystemEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
