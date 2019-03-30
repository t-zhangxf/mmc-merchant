package com.merchant.web.common.enums;

/**
 * 支付状态枚举
 */
public enum PayStatusEnum {
    /**
     * 全部
     */
    ALL (-1,"ALL"),
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

    PayStatusEnum(Integer status, String desc) {
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
    public final static PayStatusEnum getByStatus(Integer status) {
        for (PayStatusEnum t : PayStatusEnum.values()) {
            if (t.getStatus() == status) {
                return t;
            }
        }
        return null;
    }

    public final static boolean hasStatus(Integer status){
        boolean hasStatus=false;
        for(PayStatusEnum r:PayStatusEnum.values()){
            if(r.getStatus()==status){
                hasStatus=true;
                break;
            }
        }
        return hasStatus;
    }
}
