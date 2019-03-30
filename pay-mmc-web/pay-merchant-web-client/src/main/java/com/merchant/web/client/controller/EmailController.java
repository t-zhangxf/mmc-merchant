package com.merchant.web.client.controller;

import com.merchant.web.client.exception.CustomException;
import com.merchant.web.client.secutity.UserHelper;
import com.merchant.web.common.entity.result.Result;
import com.merchant.web.common.entity.result.ResultVo;
import com.merchant.web.common.entity.vo.EmailVo;
import com.merchant.web.common.entity.vo.PermissionEmailVo;
import com.merchant.web.common.entity.vo.UserEmailVo;
import com.merchant.web.common.enums.SystemEnum;
import com.merchant.web.common.enums.UserEnums;
import com.merchant.web.common.utils.AssertHelperUtil;
import com.merchant.web.entity.User;
import com.merchant.web.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/mail")
@Api(description = "【邮箱服务】")
public class EmailController {
    @Autowired
    EmailService emailService;
    @Autowired
    UserHelper userHelper;
    @ApiOperation(value = "重置密码-发送邮件", notes = "重置密码-发送邮件", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/send/pwd", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public ResultVo sendEmail(@RequestBody EmailVo emailVo,HttpServletRequest httpServletRequest) throws Exception{
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        log.info("sendEmail :"+emailVo.toString());
        return emailService.sendEmailPwd(emailVo);
    }

    @ApiOperation(value = "getUserEmailByToken", notes = "根据token获取邮箱", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getUserEmailByToken", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public Result emailValidToken(@RequestBody EmailVo emailVo,HttpServletRequest httpServletRequest) throws Exception{
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        log.info("emailValidToken request :"+emailVo.toString());
        return emailService.emailValidToken(emailVo);
    }
    @ApiOperation(value = "notify", notes = "邮件激活通知", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/notify", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public ResultVo notify(@RequestBody PermissionEmailVo permissionEmailVo,HttpServletRequest httpServletRequest) throws Exception{
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        log.info("notify request :"+permissionEmailVo.toString());
        User user =(User) userHelper.getSubject().getPrincipal();
        return emailService.notify(permissionEmailVo,user);
    }
}
