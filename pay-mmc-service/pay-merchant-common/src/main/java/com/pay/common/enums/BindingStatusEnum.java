package com.pay.common.enums;

/**
 * 绑定状态枚举
 */
public enum BindingStatusEnum {
    /**
     * 绑定
     */
    BINDING((byte)1,"BINDING"),
    /**
     * 解绑
     */
    UNBUNDING((byte)2,"UNBUNDING"),
    ;
    private final byte status;
    private final String desc;

    BindingStatusEnum(byte status, String desc) {
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
