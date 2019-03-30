package com.pay.common.entity.vo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEmailVo implements Serializable {
    private  String  email;//邮件
    private  String  merchantId;//商户号
    private  String  userNo;//用户号
}
