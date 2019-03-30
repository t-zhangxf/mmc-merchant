package com.merchant.web.client.controller;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.StringUtil;
import com.merchant.web.client.exception.CustomException;
import com.merchant.web.client.secutity.UserHelper;
import com.merchant.web.common.entity.request.MerchantReq;
import com.merchant.web.common.entity.result.Result;
import com.merchant.web.common.entity.result.ResultLogin;
import com.merchant.web.common.entity.result.ResultPermission;
import com.merchant.web.common.entity.result.ResultVo;
import com.merchant.web.common.entity.vo.*;
import com.merchant.web.common.enums.MerchantEnums;
import com.merchant.web.common.enums.SystemEnum;
import com.merchant.web.common.enums.UserEnums;
import com.merchant.web.common.utils.AssertHelperUtil;
import com.merchant.web.entity.User;
import com.merchant.web.service.MerchantService;
import com.merchant.web.service.UserService;
import com.merchant.web.vo.UserPermissionVo;
import com.merchant.web.vo.UserRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(description = "【用户服务】")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserHelper userHelper;
    @Autowired
    MerchantService merchantService;
    @Value("${x-token}")
    private  String xToken;
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/login", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public ResultLogin login(
            @RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) UserVo user,
            HttpServletRequest httpServletRequest
    ) throws Exception {
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        AssertHelperUtil.hasText(user.getUsername(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【username】cannot be empty");
        AssertHelperUtil.hasText(user.getPassword(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【password】cannot be empty");
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = userHelper.getSubject();
        subject.login(token);
        User userDo=(User) subject.getPrincipal();
        AssertHelperUtil.isTrue(userService.checkUserMerchant(userDo),UserEnums.USER_MERCHANT_IS_NULL.getCode(),UserEnums.USER_MERCHANT_IS_NULL.getDesc());
        return ResultLogin.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),subject.getSession().getId().toString(),userService.getMerchantResp(userDo));
    }
    @ApiOperation(value = "用户商户权限列表", notes = "用户登录", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/permit/list", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public ResultPermission permitList (
         @RequestBody MerchantVo merchantVo,
         HttpServletRequest httpServletRequest
      )throws Exception{
         AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        log.info("get permission list request{}",JSON.toJSONString(merchantVo));
         AssertHelperUtil.hasText(merchantVo.getMerchantId(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【merchantId】 cannot be empty");
         User user =(User) userHelper.getSubject().getPrincipal();
         UserRoleVo  userRoleVo=UserRoleVo.builder().mercId(merchantVo.getMerchantId()).userNo(user.getUserNo()).build();
         return userService.getUserPermissionByUserRole(userRoleVo);
    }
    @ApiOperation(value = "用户登录退出", notes = "用户登录退出", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/loginOut", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public ResultVo loginOut (HttpServletRequest httpServletRequest) throws Exception{
         log.info("user had loginOut");
         AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
         userHelper.loginOut(httpServletRequest,WebUtils.toHttp(httpServletRequest).getHeader(xToken));
        return ResultVo.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc());
    }
    @ApiOperation(value = "首次设置密码", notes = "首次设置密码", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/reset/pwd/first", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public ResultVo reSetPwdFirst (@RequestBody UserBaseInfoVo userVo,HttpServletRequest httpServletRequest) throws Exception{
        log.info("reSetPwdFirst :"+userVo.toString());
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        userVo.setCredential(userHelper.encryptPassword(userVo.getUserName(),userVo.getPassword()));
        return  userService.reSetPwdFirst(userVo);
    }
    @ApiOperation(value = "重置密码", notes = "重置密码", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/reset/pwd/reSet", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public ResultVo reSetPwd(@RequestBody UserEmailVo userEmailVo,HttpServletRequest httpServletRequest) throws Exception{
        log.info("reSetPwdFirst :"+userEmailVo.toString());
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        userEmailVo.setCredential(userHelper.encryptPassword(userEmailVo.getUserName(),userEmailVo.getPassword()));
        return  userService.reSetPwd(userEmailVo);
    }

    @ApiOperation(value = "修改用户密码", notes = "修改用户密码", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/modify", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public ResultVo modify(@RequestBody UserModifyPasswordVo userEmailVo,HttpServletRequest httpServletRequest) throws Exception{
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        log.info("modify password request :"+userEmailVo.toString());
        User user =(User) userHelper.getSubject().getPrincipal();
        String oldPassWord=userHelper.encryptPassword(userEmailVo.getEmail(),userEmailVo.getOriPassword());
        AssertHelperUtil.isTrue(user.getCredential().equals(oldPassWord),UserEnums.USER_ORI_PASSWORD_IS_ERROR.getCode(),UserEnums.USER_ORI_PASSWORD_IS_ERROR.getDesc());
        userEmailVo.setCredential(userHelper.encryptPassword(userEmailVo.getEmail(),userEmailVo.getNewPassword()));
        return  userService.modify(userEmailVo);
    }
}
