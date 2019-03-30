package com.merchant.web.common.enums;

import lombok.Getter;

@Getter
public enum MerchantEnums {
    MERCHANT_ID_NULL("300", "merchantId is null"),
    MERCHANT_NAME_NULL("301", "merchantName is null");
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
    MerchantEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
