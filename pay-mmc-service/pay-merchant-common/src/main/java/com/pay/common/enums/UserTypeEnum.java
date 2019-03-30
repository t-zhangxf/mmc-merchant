package com.pay.common.enums;

/**
 * 用户类型枚举
 */
public enum UserTypeEnum {
    /**
     * 管理员
     */
    ADMINISTRATORS((byte)0,"管理员"),
    /**
     * 操作员
     */
    OPERATOR((byte)1,"操作员"),
    ;
    private final Byte code;
    private final String desc;

    UserTypeEnum(Byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public final static boolean hasCode(Byte code){
        boolean hasCode=false;
        for(UserTypeEnum r:UserTypeEnum.values()){
            if(r.getCode().equals(code)){
                hasCode=true;
                break;
            }
        }
        return hasCode;
    }
}
