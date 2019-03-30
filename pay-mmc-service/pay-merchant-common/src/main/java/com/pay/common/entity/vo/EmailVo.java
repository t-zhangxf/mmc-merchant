package com.pay.common.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class EmailVo implements Serializable {
    private static final long serialVersionUID = -7032284145697889716L;
    private String token;
    private String email;//邮箱账号
}
