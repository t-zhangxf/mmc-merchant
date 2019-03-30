package com.merchant.web.common.enums;

import lombok.Getter;

@Getter
public enum  BindStatusEnums {
     BIND_STATUS_IS_UN_OK("0", "unbind"),
     BIND_STATUS_IS_OK("1", "bind"),
     BIND_STATUS_IS_DELIVER("2", "deliver"),
    BIND_SOURCE_IS_OMC("0", "omc"),
    BIND_SOURCE_IS_MERCHANT("1", "merchant"),
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
    BindStatusEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
