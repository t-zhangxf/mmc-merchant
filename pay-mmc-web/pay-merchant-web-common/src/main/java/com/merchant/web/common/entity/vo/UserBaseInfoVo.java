package com.merchant.web.common.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter
@Setter
@ToString
public class UserBaseInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private String password;
    private String token;//链接token
    private String credential;//用户令牌 如密码
}
