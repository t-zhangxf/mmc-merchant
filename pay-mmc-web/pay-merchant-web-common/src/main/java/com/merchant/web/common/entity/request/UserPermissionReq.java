package com.merchant.web.common.entity.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionReq {
    private String email;//邮件
    private String merchantId;//商户号
    private String userNo;//用户号
    private String permitStr;//权限字符串
    private String merchantName;
    private String inviter;//邀请人
    private String currentUserNo;//当前用户号

}