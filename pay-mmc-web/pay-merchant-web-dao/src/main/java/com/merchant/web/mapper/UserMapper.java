package com.merchant.web.mapper;
import com.merchant.web.Do.UserInfoDo;
import com.merchant.web.Do.UserRolePermissionDo;
import com.merchant.web.common.entity.vo.UserRolePermissionVo;
import com.merchant.web.entity.TbUserMerchantBind;
import com.merchant.web.entity.User;
import com.merchant.web.entity.UserBind;
import com.merchant.web.repository.MyDbRepository;
import com.merchant.web.vo.UserInfoVo;
import com.merchant.web.vo.UserVo;

import java.util.List;

@MyDbRepository
public interface UserMapper {
    User selectUseInfoByCondition(UserVo userVo);
    UserInfoDo selectUserInfoByUser(UserInfoVo userInfoVo);
    List<UserRolePermissionDo> findCustomerPermissionListByMerchantNo(UserRolePermissionVo userPermissionVo);
    void insertUser(UserBind userBind);
    void insertBind(UserBind userBind);
    List <UserInfoDo> findUserBindInfoByUserMerchant(UserBind userBind);
    List<TbUserMerchantBind>  selectUserMerchantByUserNo(UserBind userBind);
    void updateUserStatusByUserNo(UserBind userBind);
}
