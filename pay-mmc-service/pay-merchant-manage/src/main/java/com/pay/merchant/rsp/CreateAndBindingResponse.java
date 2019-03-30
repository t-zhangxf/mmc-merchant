package com.pay.merchant.rsp;

import lombok.*;

/**
 * @author liang_huiling Create And Binding Response
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAndBindingResponse {
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 邮箱账号
     */
    private String email;
    /**
     * 商户名称
     */
    private String merchantName;
    /**
     * 邮件发送标志（1：未发送，2：已发送）
     */
//    private byte sentFlag;
    /**
     * 邮件模板编号，重发邮件需要
     */
    private String templateId;
}
