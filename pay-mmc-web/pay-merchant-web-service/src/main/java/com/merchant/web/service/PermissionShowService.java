package com.merchant.web.service;

import com.merchant.web.common.entity.result.Result;
import com.merchant.web.common.entity.vo.PermissionShowVo;
import com.merchant.web.common.entity.vo.PermissionVo;
import com.merchant.web.entity.User;

public interface PermissionShowService {
    Result findAllPermissionList(PermissionVo permissionVo);

    Result addPermission(PermissionShowVo permissionVo, User user,String merchantId) throws Exception;

    Result permissionListByUserNo(PermissionShowVo permissionShowVo);

    Result editPermission(PermissionShowVo permissionVo, User user,String merchantId) throws Exception;

    Result deletePermission(PermissionShowVo permissionVo, User user,String merchantId) throws Exception;
}
