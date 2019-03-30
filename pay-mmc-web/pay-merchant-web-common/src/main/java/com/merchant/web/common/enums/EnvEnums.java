package com.merchant.web.common.enums;

import lombok.Getter;

@Getter
public enum EnvEnums {
    ENV_LOCAL("local", "local"),
    ENV_DEV("dev", "dev"),
    ENV_ALPHA("alpha", "alpha"),
    ENV_PROD("prod", "prod");
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
    EnvEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
