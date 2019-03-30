package com.pay.merchant.rsp;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author liang_huiling Get User Info Response
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserInfoResponse {
    /**
     * 总笔数
     */
//    private Integer total;

    private List<userDetail> rows;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class userDetail {
        /**
         * 激活状态（1：未激活，2：已激活）
         */
        private byte activeFlag;
        /**
         * 是否关联商户号（0：已关联，1：未关联，仅有商户号）
         */
        private Integer bindingFlag;
        /**
         * 创建时间
         */
        private Date createTime;
        /**
         * 关联登录账号
         */
        private String email;
        /**
         * 商户名
         */
        private String  merchantName;
        /**
        /**
         * 商户简称
         */
        private String  simpleName;
        /**
         * 商户号
         */
        private String merchantNo;
        /**
         * 账号类型（0：管理员，1：操作员，2：任意，3：无账号）
         */
        private byte userType;
    }
}
