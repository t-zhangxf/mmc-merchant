package com.pay.common.entity.vo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionShowVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private  Integer type;//类型
    private  String  email;//邮件
    private  String  merchantId;//商户号
    private  String  merchantName;
    private  String  permitStr;//权限字符串
    private  String  userNo;//用户号
    private  String  inviter;//邀请人
    private  String  currentUserNo;
}
