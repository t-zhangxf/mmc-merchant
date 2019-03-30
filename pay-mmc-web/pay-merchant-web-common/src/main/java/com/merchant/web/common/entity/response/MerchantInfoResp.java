package com.merchant.web.common.entity.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantInfoResp {
   private String  merchantId;
   private String  merchantName;
   private  String notify;
}
