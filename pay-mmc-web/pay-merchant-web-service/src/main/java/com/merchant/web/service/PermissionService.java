package com.merchant.web.service;

import com.merchant.web.common.entity.result.Result;
import com.merchant.web.common.entity.vo.PermissionVo;

public interface PermissionService {
    Result findAllPermissionList(PermissionVo permissionVo);
}
