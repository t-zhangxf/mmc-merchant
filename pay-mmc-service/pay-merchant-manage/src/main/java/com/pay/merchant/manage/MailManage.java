package com.pay.merchant.manage;

import com.pay.common.entity.result.BizResult;
import com.pay.common.entity.vo.EmailVo;
import com.pay.common.entity.vo.UserEmailVo;
import com.pay.merchant.req.MailByTemplateRequest;
import com.pay.merchant.req.ResendMailRequest;
import com.pay.merchant.rsp.ValidTokenResponse;

public interface MailManage {
    /**
     * 指定模板发送邮件
     * @param request
     */
    boolean sendByTemplate(MailByTemplateRequest request) throws Exception;

    /**
     * 生成邮箱链接地址
     * @param email 邮箱账号
     * @param type 邮件模板类型
     * @return
     */
    String createLink(String email,String type);

    /**
     * token 校验
     * @param token
     * @return
     */
    ValidTokenResponse validToken(String token);
    /**
     * 重发邮件
     * @param request
     * @return
     */
    boolean resendMail(ResendMailRequest request);
    BizResult resetPasswordSendEmail(EmailVo emailVo) throws Exception;
    BizResult EmailNotify(UserEmailVo userEmailVo) throws Exception;
}
