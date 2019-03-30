package com.merchant.web.common.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class MerchantVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String merchantId;
}
