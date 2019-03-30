package com.merchant.web.service.Impl;

import com.merchant.web.common.entity.request.MerchantReq;
import com.merchant.web.common.entity.response.MerchantInfoResp;
import com.merchant.web.common.entity.response.MerchantResponse;
import com.merchant.web.common.enums.SystemEnum;
import com.merchant.web.integration.feignClient.MerchantServerClient;
import com.merchant.web.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    MerchantServerClient merchantServerClient;
    @Override
    public MerchantInfoResp getMerchantInfoByCondition(MerchantReq merchantReq) {
        MerchantInfoResp merchantInfoResp=new MerchantInfoResp();
        MerchantResponse  merchantResponse= merchantServerClient.getMerchantInfoByCondition(merchantReq);
        if(SystemEnum.SYSTEM_SUCCESS.getCode().equals(merchantResponse.getBizCode())){
        MerchantResponse.Merchant data= merchantResponse.getData();
        if(data!=null){
            merchantInfoResp.setMerchantId(data.getMerchantNo());
            if(!StringUtils.isEmpty(data.getSimpleName())){
                merchantInfoResp.setMerchantName(data.getSimpleName());
            }else {
                merchantInfoResp.setMerchantName(data.getMerchantName());
            }
         }
        }
        return merchantInfoResp;
    }
}
