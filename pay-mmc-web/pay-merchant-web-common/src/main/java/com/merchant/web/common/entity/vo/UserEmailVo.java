package com.merchant.web.common.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserEmailVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String password;
    private String userName;
    private String credential;//用户令牌 如密码
    private String token;//重置密码链接token
}
