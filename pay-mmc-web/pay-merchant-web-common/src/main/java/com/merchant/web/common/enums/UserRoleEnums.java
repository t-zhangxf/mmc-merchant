package com.merchant.web.common.enums;

import lombok.Getter;

@Getter
public enum UserRoleEnums {
    USER_ADMIN(0, "USER ADMIN"),
    USER_CUSTOMER(1, "USER CUSTOMER")
    ;
    private Integer code;
    private String desc;
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    UserRoleEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
