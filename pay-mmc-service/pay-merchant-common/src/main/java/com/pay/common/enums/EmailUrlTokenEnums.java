package com.pay.common.enums;

import lombok.Getter;

@Getter
public enum EmailUrlTokenEnums {
    EMAIL_URL_TOKEN_EFFECT("0", "VALID"),
    EMAIL_TOKEN_NORMAL("1", "INVALID"),
    EMAIL_TOKEN_EXPIRE("2", "EXPIRED"),
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
    EmailUrlTokenEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static String getValue(String state) {
        for (EmailUrlTokenEnums mccEnum : EmailUrlTokenEnums.values()) {
            if (mccEnum.getCode().equals(state)) {
                return mccEnum.desc;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(EmailUrlTokenEnums.getValue("0"));
    }
}
