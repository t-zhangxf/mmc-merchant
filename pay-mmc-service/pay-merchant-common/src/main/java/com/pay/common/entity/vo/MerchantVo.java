package com.pay.common.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class MerchantVo implements Serializable {
    private static final long serialVersionUID = -7032284145697889716L;
    private String  merchantNo;//商户号
}
