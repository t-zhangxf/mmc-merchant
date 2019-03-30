package com.merchant.web.common.enums;

import lombok.Getter;

@Getter
public enum PageEnums {
    PERMISSION_MERCHANT_PAGE_SIZE_IS_NULL("700", "pageSize is null"),
    PERMISSION_MERCHANT_PAGE_NUMBER_IS_NULL("701", "pageNumber is null"),
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
    PageEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
