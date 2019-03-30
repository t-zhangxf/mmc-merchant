package com.pay.merchant.manage;

import com.pay.common.entity.result.BizResult;
import com.pay.common.entity.vo.MerchantVo;

public interface MerchantManage {
    BizResult getMerchantListByMerchantNo(MerchantVo merchantVo);
    BizResult  getMerchantAndMemberDetail(String merchantNo);
}
