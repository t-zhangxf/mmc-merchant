package com.merchant.web.common.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnums {
    USER_STATUS_IS_OK("0", "user status is ok"),
    USER_STATUS_IS_LOCKED("1", "user status is locked"),
    USER_STATUS_IS_UN_OK("2", "user  status is  un ok!"),
    ;
    private String code;
    private String desc;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    UserStatusEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
