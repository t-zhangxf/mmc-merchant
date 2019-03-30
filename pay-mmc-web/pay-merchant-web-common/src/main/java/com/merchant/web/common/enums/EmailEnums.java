package com.merchant.web.common.enums;

import lombok.Getter;

@Getter
public enum EmailEnums {
    EMAIL_TOKEN_NORMAL("0", "email token normal"),
    EMAIL_TOKEN_EFFECT("1", "email token invalid"),
    EMAIL_TOKEN_EXPIRE("2", "email token expire"),
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
    EmailEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
