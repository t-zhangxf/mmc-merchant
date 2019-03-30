package com.merchant.web.integration.feignClient;
import com.merchant.web.common.entity.request.EmailReq;
import com.merchant.web.common.entity.request.UserEmailReq;
import com.merchant.web.common.entity.response.EmailRes;
import com.merchant.web.common.entity.vo.EmailVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(name = "${mmc-server-name}", url = "${mmc-server-url}")
public interface EmailServerClient {
    /**
     * @desc valid  email effect
     * @param emailReq
     * @return
     */
    @RequestMapping(value = "/email/user/validToken", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8;"})
    public EmailRes emailValidToken(@RequestBody EmailReq emailReq);

    /**
     * @desc reset password and sendEmail
     * @param emailVo
     * @return
     */
    @RequestMapping(value = "/email/resetPasswordSendEmail", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8;"})
    EmailRes sendEmail(@RequestBody EmailVo emailVo);

    @RequestMapping(value = "/email/notify", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8;"})
    EmailRes userEmailNotify(@RequestBody UserEmailReq userEmailReq);
}
