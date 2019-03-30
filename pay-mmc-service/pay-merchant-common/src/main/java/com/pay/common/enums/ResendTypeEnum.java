package com.pay.common.enums;

public enum   ResendTypeEnum {
    resend_register("0","注册通知重发"),
    resend_active("1","激活通知"),
    resend_unbunding("2","解绑通知"),
    ;

    private String code;
    private String desc;

    private ResendTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public final static boolean hasCode(String code){
        boolean hasCode=false;
        for(ResendTypeEnum r:ResendTypeEnum.values()){
            if(r.getCode().equals(code)){
                hasCode=true;
                break;
            }
        }
        return hasCode;
    }
}
