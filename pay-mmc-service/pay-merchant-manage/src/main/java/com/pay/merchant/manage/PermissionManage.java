package com.pay.merchant.manage;

import com.pay.common.entity.result.BizResult;
import com.pay.common.entity.vo.PermissionShowVo;

public interface PermissionManage {
    BizResult add(PermissionShowVo permissionShowVo) throws Exception;
    BizResult edit(PermissionShowVo permissionShowVo) throws Exception;
    BizResult delete(PermissionShowVo permissionShowVo) throws Exception;
}
