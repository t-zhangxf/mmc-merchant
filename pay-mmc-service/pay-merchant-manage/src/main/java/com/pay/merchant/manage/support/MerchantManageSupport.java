package com.pay.merchant.manage.support;

import com.pay.common.entity.result.BizResult;
import com.pay.common.entity.vo.MerchantVo;
import com.pay.common.enums.SystemEnum;
import com.pay.merchant.integration.resp.MerchantAndMemberDetailResponse;
import com.pay.merchant.integration.service.MerchantFeignService;
import com.pay.merchant.manage.MerchantManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MerchantManage")
@Slf4j
public class MerchantManageSupport implements MerchantManage {
    @Autowired
    MerchantFeignService merchantFeignService;
    @Override
    public BizResult getMerchantListByMerchantNo(MerchantVo merchantVo) {
        return  BizResult.create(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),merchantFeignService.merchantDetailByMemberNo(merchantVo.getMerchantNo()).getData());
    }

    @Override
    public BizResult getMerchantAndMemberDetail(String merchantNo) {
        MerchantAndMemberDetailResponse merchantAndMemberDetailResponse= merchantFeignService.getMerchantAndMemberDetail("SP065978");
        log.info("merchantAndMemberDetailResponse:"+merchantAndMemberDetailResponse.toString());
        return BizResult.create(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),null);
    }
}
