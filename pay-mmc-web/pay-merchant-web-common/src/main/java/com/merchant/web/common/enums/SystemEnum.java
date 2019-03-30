package com.merchant.web.common.enums;

import lombok.Getter;

@Getter
public enum SystemEnum {
	SYSTEM_SUCCESS("200", "system request is success"),
	SYSTEM_FAILED("1404", "system is failed"),
	SYSTEM_SINGER_ERROR("1501", "system signer is error"),
	SYSTEM_FORBIDDEN_ERROR("1502", "system forbidden is error"),
	SYSTEM_BAD_REQUEST("1503", "system request is bad"),
	SYSTEM_NULL_DATA("1504", "system request data is null"),
	SYSTEM_PARAMETER_NULL("1505","some parameters are missing from the request"),
	SYSTEM_BAD_RESPONSE("1506", "system bad response"),
	SYSTEM_REQUEST_URL_BAD("1507", "system request url is bad"),
	SYSTEM_REQUEST_METHOD_BAD("1507", "system request method bad"),
	;
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
