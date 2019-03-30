package com.pay.merchant.client.controller;
import com.pay.common.entity.result.BizResult;
import com.pay.common.entity.vo.PermissionShowVo;
import com.pay.merchant.manage.PermissionManage;
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
@RestController
@RequestMapping("/permission")
@Api(description = "【权限服务】")
@Slf4j
public class PermissionController {

    @Autowired
    PermissionManage permissionManage;
    @ApiOperation(value = "添加成员-提交操作", notes = "添加成员-提交操作", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/add", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public BizResult add(
            @RequestBody @ApiParam(name="权限对象",value="传入json格式",required=true) PermissionShowVo permissionShowVo
    )throws Exception {
       return  permissionManage.add(permissionShowVo);
    }
    @ApiOperation(value = "编辑成员-提交操作", notes = "添加成员-提交操作", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/edit", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public BizResult edit(
            @RequestBody @ApiParam(name="权限对象",value="传入json格式",required=true) PermissionShowVo permissionShowVo
    )throws Exception {
        return  permissionManage.edit(permissionShowVo);
    }
    @ApiOperation(value = "删除成员-提交操作", notes = "添加成员-提交操作", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/delete", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public BizResult delete(
            @RequestBody @ApiParam(name="权限对象",value="传入json格式",required=true) PermissionShowVo permissionShowVo
    )throws Exception {
        return  permissionManage.delete(permissionShowVo);
    }
}
