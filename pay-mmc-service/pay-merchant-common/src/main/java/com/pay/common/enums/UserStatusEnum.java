package com.pay.common.enums;

/**
 * 用户状态枚举
 */
public enum UserStatusEnum {
    /**
     * 正常
     */
    NORMAL((byte)0,"NORMAL"),
    /**
     * 被禁用
     */
    FORBIDDEN((byte)1,"FORBIDDEN"),
    /**
     * 待激活
     */
    UNACTIVATED ((byte)2,"UNACTIVATED "),
    ;
    private final byte status;
    private final String desc;

    UserStatusEnum(byte status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public byte getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
