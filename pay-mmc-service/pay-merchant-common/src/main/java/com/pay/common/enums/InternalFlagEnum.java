package com.pay.common.enums;

/**
 * 登录账号是否内部账号枚举
 */
public enum InternalFlagEnum {
    /**
     * 内部账号
     */
    IN((byte)0,"IN"),
    /**
     * 外部账号
     */
    OUT((byte)1,"OUT"),
    ;
    private final byte internalFlag;
    private final String desc;

    InternalFlagEnum(byte internalFlag, String desc) {
        this.internalFlag = internalFlag;
        this.desc = desc;
    }

    public byte getInternalFlag() {
        return internalFlag;
    }

    public String getDesc() {
        return desc;
    }
}
