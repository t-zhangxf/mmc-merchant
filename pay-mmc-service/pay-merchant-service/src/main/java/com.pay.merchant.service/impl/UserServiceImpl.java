package com.pay.merchant.service.impl;

import com.alibaba.fastjson.JSON;
import com.pay.common.constants.CacheKeyConstants;
import com.pay.common.constants.CommonConstants;
import com.pay.common.entity.vo.UserVo;
import com.pay.common.enums.SystemEnum;
import com.pay.common.utils.AssertHelperUtil;
import com.pay.common.utils.CacheUtil;
import com.pay.common.utils.EmptyUtil;
import com.pay.merchant.entity.*;
import com.pay.merchant.mapper.UserLoginAuthsMapper;
import com.pay.merchant.mapper.UserMapper;
import com.pay.merchant.mapper.UserMercBindingMapper;
import com.pay.merchant.mapper.UsersMapper;
import com.pay.merchant.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    UserLoginAuthsMapper userLoginAuthsMapper;

    @Autowired
    UserMercBindingMapper userMercBindingMapper;

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public User selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据邮箱查询账号信息
     * @param email
     * @return
     */
    @Override
    public Users getUserByMail(String email) {
        Assert.hasText(email, "Email Cannot be empty");
        Users users=getFromCacheByMail(email);
        if(null==users){
            boolean lock = CacheUtil.lock(redisTemplate, CacheKeyConstants.KEY_MERCHANT_USER_PREFIX_LOCK + email, 10,
                    TimeUnit.SECONDS);
            try {
                if (lock) {
                    users=getFromDbByMail(email);
                    if(null!=users){
                        CacheUtil.saveCache(redisTemplate, CacheKeyConstants.KEY_MERCHANT_USER_PREFIX+email,
                                JSON.toJSONString(users), CommonConstants.EXPIRED_TIME);
                    }
                }
            }catch (Exception e){
                log.error("getUserByMail Exception,error msg{}",e.getMessage());
            }finally {
                if (lock) {
                    CacheUtil.unlock(redisTemplate, CacheKeyConstants.KEY_MERCHANT_USER_PREFIX_LOCK + email);
                }
            }
        }
        return users;
    }

    /**
     * 从数据库查用户信息
     * @param email
     * @return
     */
    @Override
    public Users getFromDbByMail(String email) {
        Assert.hasText(email, "Email Cannot be empty");
        UsersExample e=new UsersExample();
        e.createCriteria().andEmailEqualTo(email).andIsDeletedEqualTo((byte)0);
        List<Users>  list=usersMapper.selectByExample(e);
        return EmptyUtil.isNotEmpty(list) ? list.get(0) : null;
    }

    @Override
    public Users getFromDb(String userNo) {
        Assert.hasText(userNo, "userNo Cannot be empty");
        UsersExample e=new UsersExample();
        e.createCriteria().andUserNoEqualTo(userNo).andIsDeletedEqualTo((byte)0);
        List<Users>  list=usersMapper.selectByExample(e);
        return EmptyUtil.isNotEmpty(list) ? list.get(0) : null;
    }

    /**
     * 从缓存查用户信息
     * @param email
     * @return
     */
    @Override
    public Users getFromCacheByMail(String email) {
        Assert.hasText(email, "Email Cannot be empty");
        String userJson=CacheUtil.getCache(redisTemplate, CacheKeyConstants.KEY_MERCHANT_USER_PREFIX + email);
        Users users=null;
        if(EmptyUtil.isNotEmpty(userJson)){
            users=JSON.parseObject(userJson,Users.class);
        }
        return users;
    }

    /**
     * 新增用户信息
     * @param users
     * @return
     */
    @Override
    public Integer saveUsersInfo(Users users) {
        AssertHelperUtil.notNull(users, SystemEnum.VALIDATE_ERROR.getCode(),
                "Users Record Cannot be empty");
        return usersMapper.insertSelective(users);
    }

    /**
     * 新增登录授权信息
     * @param userLoginAuths
     * @return
     */
    @Override
    public Integer saveUserLoginAuths(UserLoginAuths userLoginAuths) {
        AssertHelperUtil.notNull(userLoginAuths, SystemEnum.VALIDATE_ERROR.getCode(),
                "User Login Auths Record Cannot be empty");
        return userLoginAuthsMapper.insertSelective(userLoginAuths);
    }

    /**
     * 新增绑定商户信息
     * @param userMercBinding
     * @return
     */
    @Override
    public Integer saveUserMercBinding(UserMercBinding userMercBinding) {
        AssertHelperUtil.notNull(userMercBinding, SystemEnum.VALIDATE_ERROR.getCode(),
                "User Login Auths Record Cannot be empty");
        return userMercBindingMapper.insertSelective(userMercBinding);
    }

    /**
     * 查询商户的管理员绑定信息
     * @param merc_id
     * @return
     */
    @Override
    public UserMercBinding getAdminBindingFromDb(String merc_id) {
        Assert.hasText(merc_id, "MerchantId Cannot be empty");
        UserMercBindingExample e=new UserMercBindingExample();
        e.createCriteria().andMercIdEqualTo(merc_id).andUserTypeEqualTo((byte)0).andIsDeletedEqualTo((byte)0);
        List<UserMercBinding> list=userMercBindingMapper.selectByExample(e);
        return EmptyUtil.isNotEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询用户商户绑定信息
     * @param mercId
     * @param userNo
     * @return
     */
    @Override
    public UserMercBinding getUserMerchantBindingFromDb(String mercId,String userNo) {
        Assert.hasText(mercId, "【MerchantId】 Cannot be empty");
        Assert.hasText(userNo, "【UserNo】 Cannot be empty");
        UserMercBindingExample e=new UserMercBindingExample();
        e.createCriteria().andMercIdEqualTo(mercId).andUserNoEqualTo(userNo).andIsDeletedEqualTo((byte)0);
        List<UserMercBinding> list=userMercBindingMapper.selectByExample(e);
        return EmptyUtil.isNotEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询用户绑定商户信息
     * @param userNo
     * @return
     */
    @Override
    public List<UserMercBinding> queryUserBinding(String mercId,String userNo) {
        UserMercBindingExample e=new UserMercBindingExample();
        UserMercBindingExample.Criteria ca=e.createCriteria().andIsDeletedEqualTo((byte)0);
        if(EmptyUtil.isNotEmpty(mercId)){
            ca.andMercIdEqualTo(mercId);
        }
        if(EmptyUtil.isNotEmpty(userNo)){
            ca.andUserNoEqualTo(userNo);
        }
        List<UserMercBinding> list=userMercBindingMapper.selectByExample(e);
        return list;
    }

    /**
     * 统计用户绑定商户数
     * @param userNo
     * @return
     */
    @Override
    public Integer countUserBinding(String userNo) {
        Assert.hasText(userNo, "【UserNo】 Cannot be empty");
        UserMercBindingExample e=new UserMercBindingExample();
        e.createCriteria().andUserNoEqualTo(userNo).andIsDeletedEqualTo((byte)0);
        return userMercBindingMapper.countByExample(e);
    }

    /**
     * 删除用户商户绑定关系
     * @param mercId
     * @param userNo
     * @return
     */
    @Override
    public Integer deleteUserMerchantBinding(String mercId,String userNo) {
        Assert.hasText(mercId, "【MerchantId】 Cannot be empty");
        Assert.hasText(userNo, "【UserNo】 Cannot be empty");
        UserMercBindingExample e=new UserMercBindingExample();
        e.createCriteria().andMercIdEqualTo(mercId).andUserNoEqualTo(userNo).andIsDeletedEqualTo((byte)0);
        return userMercBindingMapper.deleteByExample(e);
    }

    /**
     * 更新用户类型
     * @param mercId
     * @param userNo
     * @param userType
     * @return
     */
    @Override
    public Integer modifyBindingUserType(String mercId, String userNo, Byte userType) {
        Assert.hasText(mercId, "【MerchantId】 Cannot be empty");
        Assert.hasText(userNo, "【UserNo】 Cannot be empty");
        Assert.hasText(userNo, "【userType】 Cannot be empty");
        UserMercBindingExample e=new UserMercBindingExample();
        e.createCriteria().andMercIdEqualTo(mercId).andUserNoEqualTo(userNo).andIsDeletedEqualTo((byte)0);
        UserMercBinding userMercBinding=new UserMercBinding();
        userMercBinding.setUserType(userType);
        return  userMercBindingMapper.updateByExampleSelective(userMercBinding,e);
    }
    @Override
    public List<UserMercBinding> findUserMerchantInfoByEmail(UserBind userBind) {
        return userMapper.findUserMerchantInfoByEmail(userBind);
    }

    @Override
    public void updateUserStatusByUserNo(UserBind userBind) {
        userMapper.updateUserStatusByUserNo(userBind);
    }

    @Override
    public List<User> selectUseInfoByCondition(UserVo userVo) {
        return userMapper.selectUseInfoByCondition(userVo);
    }
}
