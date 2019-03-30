package com.pay.merchant.rsp;

import lombok.*;

/**
 * @author liang_huiling Merchant Add Response
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidTokenResponse {
    /**
     * 状态（0：有效，1：无效，2：过期）
     */
    private String status;

    /**
     * 邮箱账号
     */
    private String email;
    private String type;
}
