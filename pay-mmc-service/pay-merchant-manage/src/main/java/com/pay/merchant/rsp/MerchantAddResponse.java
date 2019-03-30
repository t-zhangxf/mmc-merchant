package com.pay.merchant.rsp;

import lombok.*;

/**
 * @author liang_huiling Merchant Add Response
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantAddResponse {
    /**
     * 商户号
     */
    private String merchant_no;
}
