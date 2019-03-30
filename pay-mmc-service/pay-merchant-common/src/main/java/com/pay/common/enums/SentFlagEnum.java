package com.pay.common.enums;

/**
 * 邮件发送状态
 */
public enum SentFlagEnum {
    /**
     * 未发送
     */
    UNSENT((byte)1,"UNSENT"),
    /**
     * 已发送
     */
    SENT((byte)2,"SENT"),
    ;
    private final byte status;
    private final String desc;

    SentFlagEnum(byte status, String desc) {
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
