package com.merchant.web.vo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo implements Serializable {
    private static final long serialVersionUID = 3026591685413490671L;
    private String email;
    private String userStatus;//0:正常数据
    private Integer identityType;//登录类型，0：邮箱，1：手机，2：第三方账号
    private String credential;//登录令牌
    private String activeStatus;//激活状态
}
