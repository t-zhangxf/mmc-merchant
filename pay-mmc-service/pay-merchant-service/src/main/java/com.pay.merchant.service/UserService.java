package com.pay.merchant.service;

import com.pay.common.entity.vo.UserVo;
import com.pay.merchant.entity.*;

import java.util.List;

/**
 * 用户服务
 */
public interface UserService {
    User selectByPrimaryKey(String id);

    /**
     * 根据邮箱查询账号信息
     * @param email
     * @return
     */
    Users getUserByMail(String email);

    /**
     * 从数据库查询账号信息
     * @param email
     * @return
     */
    Users getFromDbByMail(String email);

    /**
     * 从数据库查询账号信息
     * @param userNo
     * @return
     */
    Users getFromDb(String userNo);

    /**
     * 从缓存查询账号信息
     * @param email
     * @return
     */
    Users getFromCacheByMail(String email);

    /**
     * 新增用户信息
     * @param users
     * @return
     */
    Integer saveUsersInfo(Users users);

    /**
     * 新增登录授权信息
     * @param userLoginAuths
     * @return
     */
    Integer saveUserLoginAuths(UserLoginAuths userLoginAuths);

    /**
     * 新增绑定商户信息
     * @param userMercBinding
     * @return
     */
    Integer saveUserMercBinding(UserMercBinding userMercBinding);

    /**
     * 查询商户的管理员绑定信息
     * @param mercId
     * @return
     */
    UserMercBinding getAdminBindingFromDb(String mercId);

    /**
     * 查询用户商户绑定信息
     * @param mercId
     * @param userNo
     * @return
     */
    UserMercBinding getUserMerchantBindingFromDb(String mercId, String userNo);

    /**
     * 查询绑定列表
     * @param userNo
     * @return
     */
    List<UserMercBinding> queryUserBinding(String mercId,String userNo);

    /**
     * 统计用户绑定商户数
     * @param userNo
     * @return
     */
    Integer countUserBinding(String userNo);

    /**
     * 删除用户商户绑定关系
     * @param mercId
     * @param userNo
     * @return
     */
    Integer deleteUserMerchantBinding(String mercId,String userNo);

    /**
     * 更新用户商户账号类型
     * @param mercId
     * @param userNo
     * @param userType
     * @return
     */
    Integer modifyBindingUserType(String mercId,String userNo,Byte userType);

    List<UserMercBinding> findUserMerchantInfoByEmail(UserBind userBind);
    void  updateUserStatusByUserNo(UserBind userBind);
    List<User> selectUseInfoByCondition(UserVo userVo);
}
