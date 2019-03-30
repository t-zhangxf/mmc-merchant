package com.pay.merchant.service;
import com.pay.common.entity.vo.UserVo;
import com.pay.merchant.entity.*;

import java.util.List;
public interface PermissionService {
    void insertUserMerchantBinding(UserMercBinding userMercBinding);
    void insertRole(Role role);
    void insertUser(UserBind user);
    void insertUserLoginAuths(UserLoginAuths userLoginAuths);
    Role  selectRoleBuUserVo(UserBind userBind);
    void insertBatch(List<RolePermission> rolePermissions );
    List<UserBind> findUserBindInfoByUserMerchant(UserBind userBindVo);
    void setUserRolePermission(List<RolePermission> rolePermissions, Role roleData, String[] permission);
    void insertUserRole(UserRole userRole);
    List<Role>    selectRoleByUserNo(UserVo userVo);
    void deleteRoleByPrimaryKeySelective(Role role);
    void deleteUserRoleByPrimaryKeySelective(UserRole userRole);
    void  deleteRolePermissionByPrimaryKeySelective(UserBind userBind);
    List<User> selectUseInfoByCondition(UserVo userVo);
    void deleteUserRoleByRoleId(UserBind userBind);
    void updateUserMerchantBindingByUserNo(UserBind userBind);
    void deleteUserMerchantBindingByUserNo(UserBind userBind);
    UserMercBinding selectUserMerchantBindingByUserNo(UserBind userBind);
}
