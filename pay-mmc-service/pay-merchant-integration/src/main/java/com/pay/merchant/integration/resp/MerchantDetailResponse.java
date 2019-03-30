package com.pay.merchant.integration.resp;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MerchantDetailResponse<T> {
    private String code;
    private String msg;
    private Merchant data;
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Merchant {
        /**
         * BD电子邮件
         */
        private String bdEmail;
        /**
         * BD联系电话
         */
        private String bdMobile;
        /**
         * BD姓名
         */
        private String bdName;
        /**
         * 商户行业
         */
        private String industry;
        /**
         * 商户logo
         */
        private String logoUrl;
        /**
         * 会员ID
         */
        private String memberId;
        /**
         * 商户App
         */
        private String merchantApp;
        /**
         * 商户名称
         */
        private String merchantName;
        /**
         * 商户编号
         */
        private String merchantNo;
        /**
         * 商户状态
         */
        private String merchantStatus;
        /**
         * 1：特约商户 2：普通商户
         */
        private String merchantType;
        /**
         * 商户网址
         */
        private String merchantUrl;
        /**
         * 商户简称
         */
        private String simpleName;
    }
}
