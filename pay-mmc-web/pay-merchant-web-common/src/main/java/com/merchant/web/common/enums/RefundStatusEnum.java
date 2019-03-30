package com.merchant.web.common.enums;

/**
 * 支付状态枚举
 */
public enum RefundStatusEnum {
    /**
     * 支付中
     */
    PENDING (0,"PENDING"),
    /**
     * 成功
     */
    SUCCESS(1,"SUCCESS"),
    /**
     * 失败
     */
    FAILED(2,"FAILED"),
    ;
    private final Integer status;
    private final String desc;

    RefundStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * Get PayStatusEnum By status
     *
     * @param status
     * @return
     */
    public final static RefundStatusEnum getByStatus(Integer status) {
        for (RefundStatusEnum t : RefundStatusEnum.values()) {
            if (t.getStatus() == status) {
                return t;
            }
        }
        return null;
    }
}
