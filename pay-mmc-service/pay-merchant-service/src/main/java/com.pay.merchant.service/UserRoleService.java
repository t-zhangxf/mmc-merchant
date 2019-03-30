package com.pay.merchant.service;

import com.pay.merchant.entity.*;

/**
 * 用户角色服务
 */
public interface UserRoleService {
    /**
     * 新增用户角色绑定信息
     * @param userRole
     * @return
     */
    Integer saveUserRole(UserRole userRole);

    /**
     * 删除用户角色绑定信息
     * @param mercId
     * @param userNo
     * @return
     */
    Integer deleteUserRole(String mercId,String userNo);

    /**
     * 修改用户角色绑定信息
     * @param mercId
     * @param userNo
     * @return
     */
    Integer modifyUserRole(String mercId,String userNo,Integer roleId);
}
