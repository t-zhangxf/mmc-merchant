package com.merchant.web.service;

import com.merchant.web.Do.UserPermissionDo;
import com.merchant.web.common.entity.response.MerchantInfoResp;
import com.merchant.web.common.entity.response.MerchantResp;
import com.merchant.web.common.entity.result.ResultPermission;
import com.merchant.web.common.entity.result.ResultVo;
import com.merchant.web.common.entity.vo.UserBaseInfoVo;
import com.merchant.web.common.entity.vo.UserEmailVo;
import com.merchant.web.common.entity.vo.UserModifyPasswordVo;
import com.merchant.web.entity.TbLoginLog;
import com.merchant.web.entity.User;
import com.merchant.web.vo.UserPermissionVo;
import com.merchant.web.vo.UserRoleVo;
import com.merchant.web.vo.UserVo;

import java.util.List;
import java.util.Set;

public interface UserService {
    User selectByPrimaryKey(String id);
    User selectUseInfoByCondition(UserVo userVo);
    Set<UserPermissionDo> findUserPermissionByUserPermission(UserPermissionVo userPermissionVo);
    Boolean checkUserPermission(UserPermissionVo userPermissionVo);
    Set<UserPermissionDo> selectUserPermissionByUserRole(UserPermissionVo userPermissionVo,Set<UserPermissionDo> commonUserPermissions);
    ResultPermission getUserPermissionByUserRole(UserRoleVo userRoleVo);
    ResultVo reSetPwdFirst(UserBaseInfoVo userVo) throws  Exception;
    ResultVo reSetPwd(UserEmailVo userEmailVo) throws  Exception;
    ResultVo modify(UserModifyPasswordVo userEmailVo) throws  Exception;
    void saveLoginLog(TbLoginLog tbLoginLog);
    List<MerchantInfoResp> getMerchantResp(User userDo);
    Boolean checkUserMerchant(User user);
}
