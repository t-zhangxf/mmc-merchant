package com.merchant.web.common.entity.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantResp {
  private String  merchantId;
  private String  merchantName;
}
