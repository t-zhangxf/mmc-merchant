package com.pay.merchant.service;

/**
 * 商户服务
 */
public interface MerchantService {
    /**
     * 根据商户号查询商户详情
     * @param merc_id
     */
    void getMerchantDetail(String merc_id);

    /**
     * 根据商户号查询商户会员详情
     * @param merc_id
     */
    void getMemberDetail(String merc_id);

}
