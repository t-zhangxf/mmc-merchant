package com.merchant.web.common.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter
@Setter
@ToString
public class UserModifyPasswordVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String   email;
    private String   newPassword;
    private String   oriPassword;
    private String   credential;//用户登录令牌
}
