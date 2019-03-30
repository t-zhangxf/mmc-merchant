package com.merchant.web.integration.feignClient;

import com.merchant.web.common.entity.request.UserPermissionReq;
import com.merchant.web.common.entity.response.BaseDataResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${mmc-server-name}", url = "${mmc-server-url}")
public interface PermissionServerClient {

    @RequestMapping(value = "/permission/add", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8;"})
    BaseDataResp addPermission(@RequestBody UserPermissionReq userPermissionReq);

    @RequestMapping(value = "/permission/edit", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8;"})
    BaseDataResp editPermission(@RequestBody UserPermissionReq userPermissionReq);

    @RequestMapping(value = "/permission/delete", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8;"})
    BaseDataResp deletePermission(@RequestBody UserPermissionReq userPermissionReq);
}
