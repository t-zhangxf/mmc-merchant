package com.pay.common.enums;

/**
 * 国家枚举
 */
public enum CountryEnum {
    /**
     * 中国
     */
    CHINA("CN","CHINA"),
    /**
     * 印度
     */
    INDIA("IN","INDIA"),
    ;
    private final String code;
    private final String desc;

    CountryEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
