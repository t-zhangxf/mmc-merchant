package com.pay.common.enums;

/**
 * 用户绑定渠道枚举
 */
public enum BusCnlEnum {
    /**
     * 从omc绑定
     */
    OMC("0","omc平台"),
    /**
     * 从商户平台绑定
     */
    MMC("1","mmc平台"),
    ;
    private final String code;
    private final String desc;

    BusCnlEnum(String code, String desc) {
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
