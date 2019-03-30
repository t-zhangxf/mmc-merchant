package com.merchant.web.common.entity.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private String merchantNo;
    private String userNo;
}
