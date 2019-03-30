package com.merchant.web.common.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String  userLoginType;//用户登录类型
}
