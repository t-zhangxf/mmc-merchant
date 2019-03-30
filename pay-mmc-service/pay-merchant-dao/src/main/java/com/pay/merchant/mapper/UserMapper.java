package com.pay.merchant.mapper;
import com.pay.common.entity.vo.UserVo;
import com.pay.merchant.entity.*;
import com.pay.merchant.repository.MyDbRepository;

import java.util.List;

@MyDbRepository
public interface UserMapper {
    User selectByPrimaryKey(String id);
    void insertUser(UserBind userBind);
    void insertBind(UserBind userBind);
    List<User> selectUseInfoByCondition(UserVo userVo);
    Role selectRoleBuUserVo(UserBind userBind);
    List<UserBind> findUserBindInfoByUserMerchant(UserBind userBind);
    List<Role>    selectRoleByUserNo(UserVo userVo);
    void deleteUserRoleByRoleId(UserBind userBind);
    void deleteRolePermissionByRoleId(UserBind userBind);
    void updateUserMerchantBindingByUserNo(UserBind userBind);
    Integer deleteUserMerchantBindingByUserNo(UserBind userBind);
    UserMercBinding selectUserMerchantBindingByUserNo(UserBind userBind);
    List<UserMercBinding> findUserMerchantInfoByEmail(UserBind userBind);
    void  updateUserStatusByUserNo(UserBind userBind);
}
