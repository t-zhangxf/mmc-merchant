package com.merchant.web.vo;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionVo {
    private static final long serialVersionUID = 3026591685413490671L;
    private String userNo;
    private Integer identityType;
    private String identifier;
    private Object credential;
    private Integer userStatus;
    private String url;
    private String merchantId;
    private String isNotMerchantId;
    private String attrId;//是否按钮开关
    private String psNumber;
}
