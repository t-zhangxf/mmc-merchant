package com.merchant.web.integration.feignClient;

import com.merchant.web.common.entity.request.MerchantReq;
import com.merchant.web.common.entity.response.MerchantResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${mmc-server-name}", url = "${mmc-server-url}")
public interface MerchantServerClient {
    @RequestMapping(value = "/merchant/list/v1", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8;"})
    public MerchantResponse getMerchantInfoByCondition(@RequestBody MerchantReq merchantReq);
}
