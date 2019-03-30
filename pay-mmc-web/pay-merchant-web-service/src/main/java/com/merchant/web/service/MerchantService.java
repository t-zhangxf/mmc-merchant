package com.merchant.web.service;

import com.merchant.web.common.entity.request.MerchantReq;
import com.merchant.web.common.entity.response.MerchantInfoResp;
public interface MerchantService {
 MerchantInfoResp getMerchantInfoByCondition(MerchantReq merchantReq);
}
