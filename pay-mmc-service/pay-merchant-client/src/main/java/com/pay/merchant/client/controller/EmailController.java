package com.pay.merchant.client.controller;

import com.pay.common.entity.result.BizResult;
import com.pay.common.entity.vo.EmailVo;
import com.pay.common.entity.vo.UserEmailVo;
import com.pay.common.enums.SystemEnum;
import com.pay.merchant.manage.MailManage;
import com.pay.merchant.rsp.ValidTokenResponse;
import com.pay.merchant.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/email")
@Api(description = "【邮箱服务】")
public class EmailController {
    @Autowired
    MailManage mailManage;
    @ApiOperation(value = "验证邮箱url有效期，获取邮箱", notes = "验证邮箱url有效期，获取邮箱", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/user/validToken", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8;"})
    public BizResult validToken(
            @RequestBody @ApiParam(name = "邮箱对象", value = "传入json格式", required = true) EmailVo emailVo
    ) throws Exception {
        ValidTokenResponse validTokenResponse= mailManage.validToken(emailVo.getToken());
        return BizResult.create(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),validTokenResponse);
    }
    @ApiOperation(value = "resetPasswordSendEmail", notes = "验证邮箱url有效期，获取邮箱", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/resetPasswordSendEmail", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8;"})
    public BizResult resetPasswordSendEmail(
            @RequestBody @ApiParam(name = "邮箱对象", value = "传入json格式", required = true) EmailVo emailVo
    ) throws Exception {
        return mailManage.resetPasswordSendEmail(emailVo);
    }
    @ApiOperation(value = "notify", notes = "验证邮箱url有效期，获取邮箱", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/notify", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8;"})
    public BizResult notify(
            @RequestBody @ApiParam(name = "邮箱对象", value = "传入json格式", required = true) UserEmailVo userEmailVo
    ) throws Exception {
        return mailManage.EmailNotify(userEmailVo);
    }
}