package com.merchant.web.client.controller;
import com.merchant.web.client.secutity.UserHelper;
import com.merchant.web.client.secutity.page.PermissionPage;
import com.merchant.web.common.entity.result.BizResult;
import com.merchant.web.common.entity.result.Result;
import com.merchant.web.common.entity.vo.PermissionShowVo;
import com.merchant.web.common.entity.vo.PermissionVo;
import com.merchant.web.common.entity.vo.UserRolePermissionVo;
import com.merchant.web.common.enums.SystemEnum;
import com.merchant.web.common.utils.AssertHelperUtil;
import com.merchant.web.entity.User;
import com.merchant.web.service.PermissionShowService;
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

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/permit")
@Api(description = "【权限服务】")
public class PermissionController {
    @Autowired
    PermissionPage permissionPage;
    @Autowired
    PermissionShowService permissionService;
    @Autowired
    UserHelper userHelper;
    @ApiOperation(value = "设置服务-查询列表", notes = "设置服务-查询列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/query", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public BizResult query(
            @RequestBody @ApiParam(name="权限对象",value="传入json格式",required=true) UserRolePermissionVo permissionVo,
            HttpServletRequest httpServletRequest
    ) throws Exception {
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        return permissionPage.findPermissionListByMerchantNo(permissionVo);
    }
    @ApiOperation(value = "添加成员-权限列表", notes = "添加成员-权限列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/list", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public Result permissionList(
            @RequestBody @ApiParam(name="权限对象",value="传入json格式",required=true) PermissionVo permissionVo,
            HttpServletRequest httpServletRequest
    ) throws Exception {
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        return permissionService.findAllPermissionList(permissionVo);
    }
    //  发送邮件
    @ApiOperation(value = "添加成员-提交操作", notes = "添加成员-提交操作", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/add", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public Result add(
            @RequestBody @ApiParam(name="权限对象",value="传入json格式",required=true) PermissionShowVo permissionShowVo,
            HttpServletRequest httpServletRequest
    ) throws Exception {
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        User user =(User) userHelper.getSubject().getPrincipal();
        return permissionService.addPermission(permissionShowVo,user,userHelper.getCurrentMerchantId(httpServletRequest));
    }
    @ApiOperation(value = "编辑成员-权限列表", notes = "添加成员-提交操作", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/userNo", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public Result permissionListByUserNo(
            @RequestBody @ApiParam(name="权限对象",value="传入json格式",required=true) PermissionShowVo permissionShowVo,HttpServletRequest httpServletRequest
    ) throws Exception {
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        return permissionService.permissionListByUserNo(permissionShowVo);
    }

    @ApiOperation(value = "编辑成员-提交", notes = "编辑成员-提交", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/editSave", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public Result editSave(
            @RequestBody @ApiParam(name="权限对象",value="传入json格式",required=true) PermissionShowVo permissionShowVo,
            HttpServletRequest httpServletRequest
    ) throws Exception {
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        User user =(User) userHelper.getSubject().getPrincipal();
        return permissionService.editPermission(permissionShowVo,user,userHelper.getCurrentMerchantId(httpServletRequest));
    }
    @ApiOperation(value = "删除成员-提交", notes = "删除成员-提交", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/delete", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public Result delete(
            @RequestBody @ApiParam(name="权限对象",value="传入json格式",required=true) PermissionShowVo permissionShowVo,
            HttpServletRequest httpServletRequest
    ) throws Exception {
        AssertHelperUtil.notNull(httpServletRequest, SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "request 【parameter】 is null");
        User user =(User) userHelper.getSubject().getPrincipal();
        return permissionService.deletePermission(permissionShowVo,user,userHelper.getCurrentMerchantId(httpServletRequest));
    }


}
