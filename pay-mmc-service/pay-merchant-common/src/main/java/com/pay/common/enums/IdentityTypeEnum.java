package com.pay.common.enums;

/**
 * 登录类型枚举
 */
public enum IdentityTypeEnum {
    /**
     * 邮箱
     */
    EMAIL((byte)0,"EMAIL"),
    /**
     * 手机
     */
    PHONE((byte)1,"PHONE"),
    ;
    private final byte identityType;
    private final String desc;

    IdentityTypeEnum(byte identityType, String desc) {
        this.identityType = identityType;
        this.desc = desc;
    }

    public byte getIdentityType() {
        return identityType;
    }

    public String getDesc() {
        return desc;
    }
}
