package com.merchant.web.client.controller;

import com.merchant.web.common.entity.result.ResultLogin;
import com.merchant.web.common.entity.vo.EmailVo;
import com.merchant.web.common.entity.vo.UserVo;
import com.merchant.web.common.enums.SystemEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(description = "【商户服务】")
public class MerChantController {

    @ApiOperation(value = "根据token获取email", notes = "根据token获取email", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/user/validToken", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public String login(
            @RequestBody @ApiParam(name="邮箱对象",value="传入json格式",required=true) EmailVo emailVo
    ) throws Exception {
      return null;
//        return ResultLogin.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),subject.getSession().getId().toString());
    }
}
