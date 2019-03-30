package com.merchant.web.service.Impl;
import com.merchant.web.Do.UserInfoDo;
import com.merchant.web.Do.UserPermissionDo;
import com.merchant.web.common.constants.CacheKeyConstants;
import com.merchant.web.common.entity.request.EmailReq;
import com.merchant.web.common.entity.request.MerchantReq;
import com.merchant.web.common.entity.response.EmailRes;
import com.merchant.web.common.entity.response.MerchantInfoResp;
import com.merchant.web.common.entity.result.ResultPermission;
import com.merchant.web.common.entity.result.ResultVo;
import com.merchant.web.common.entity.vo.EmailVo;
import com.merchant.web.common.entity.vo.UserBaseInfoVo;
import com.merchant.web.common.entity.vo.UserEmailVo;
import com.merchant.web.common.entity.vo.UserModifyPasswordVo;
import com.merchant.web.common.enums.SystemEnum;
import com.merchant.web.common.enums.UserEnums;
import com.merchant.web.common.utils.AssertHelperUtil;
import com.merchant.web.common.utils.CacheUtil;
import com.merchant.web.entity.*;
import com.merchant.web.integration.feignClient.EmailServerClient;
import com.merchant.web.mapper.*;
import com.merchant.web.service.EmailService;
import com.merchant.web.service.MerchantService;
import com.merchant.web.service.UserService;
import com.merchant.web.vo.UserInfoVo;
import com.merchant.web.vo.UserPermissionVo;
import com.merchant.web.vo.UserRoleVo;
import com.merchant.web.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TbUserPermissionMapper tbUserPermissionMapper;

    @Autowired
    TbUserRoleMapper tbUserRoleMapper;
    @Autowired
    TbUserLoginAuthsMapper tbUserLoginAuthsMapper;
    @Autowired
    EmailServerClient emailServerClient;
    @Autowired
    TbLoginLogMapper tbLoginLogMapper;
    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    EmailService emailService;//邮箱服务
    @Override
    public User selectByPrimaryKey(String userNo) {
        return userMapper.selectUseInfoByCondition(UserVo.builder().userNo(userNo).build());
    }
    @Autowired
    MerchantService merchantService;
    @Override
    public User selectUseInfoByCondition(UserVo userVo) {
        return userMapper.selectUseInfoByCondition(userVo);
    }

    @Override
    public Set<UserPermissionDo> findUserPermissionByUserPermission(UserPermissionVo userPermissionVo) {
        Set<UserPermissionDo> commonUserPermissions = new HashSet<UserPermissionDo>();
        //查询用户权限
        List<UserPermissionDo> userPermissionDos = tbUserPermissionMapper.findUserPermissionByUser(userPermissionVo);
        if (userPermissionDos != null && !userPermissionDos.isEmpty()) {
            for (UserPermissionDo userPermissionDo : userPermissionDos) {
                commonUserPermissions.add(userPermissionDo);
            }
        }
        return commonUserPermissions;
    }

    @Override
    public ResultPermission getUserPermissionByUserRole(UserRoleVo userRoleVo) {
        Set<UserPermissionDo> commonUserPermissions = new HashSet<UserPermissionDo>();
        UserPermissionVo userPermissionVo = UserPermissionVo.builder().userNo(userRoleVo.getUserNo()).merchantId(userRoleVo.getMercId()).build();
        commonUserPermissions = this.selectUserPermissionByUserRole(userPermissionVo, commonUserPermissions);
        return ResultPermission.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(), SystemEnum.SYSTEM_SUCCESS.getDesc(), commonUserPermissions, null);
    }
    @Override
    public Set<UserPermissionDo> selectUserPermissionByUserRole(UserPermissionVo userPermissionVo, Set<UserPermissionDo> commonUserPermissions) {
        List<UserPermissionDo> userPermissionDos = tbUserPermissionMapper.findUserPermissionByUser(userPermissionVo);
        if (userPermissionDos != null && !userPermissionDos.isEmpty()) {
            for (UserPermissionDo userPermissionDo : userPermissionDos) {
                commonUserPermissions.add(userPermissionDo);
            }
        }
        return commonUserPermissions;
    }

    /**
     * @param userPermissionVo
     * @return
     * @desc checkUserPermission
     */
    @Override
    public Boolean checkUserPermission(UserPermissionVo userPermissionVo) {
        if (this.findUserPermissionByUserPermission(userPermissionVo) != null && !this.findUserPermissionByUserPermission(userPermissionVo).isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ResultVo reSetPwdFirst(UserBaseInfoVo userVo) throws Exception {
        AssertHelperUtil.hasText(userVo.getUserName(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【userName】 cannot be empty");
        AssertHelperUtil.hasText(userVo.getPassword(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【password】 cannot be empty");
        AssertHelperUtil.hasText(userVo.getToken(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【token】 cannot be empty");
        AssertHelperUtil.isTrue(emailService.checkEmailToken(EmailVo.builder().token(userVo.getToken()).build()), UserEnums.USER_EMAIL_TOKEN_EFFECT.getCode(), UserEnums.USER_EMAIL_TOKEN_EFFECT.getDesc());
        UserInfoVo userInfoVo = UserInfoVo.builder().email(userVo.getUserName()).identityType(0).userStatus("0").build();
        UserInfoDo userInfoDo = userMapper.selectUserInfoByUser(userInfoVo);
        CacheUtil.delete(redisTemplate,CacheKeyConstants.KEY_MERCHANT_USER_PREFIX+userVo.getUserName());
        log.info("删除是否成功：********************"+userVo.getUserName());
        userMapper.updateUserStatusByUserNo(UserBind.builder().userNo(userInfoDo.getUserNo()).activeStatus(Byte.valueOf("1")).build());
        setUserPwdAndCleanToken(userVo, userInfoDo);
        return ResultVo.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(), SystemEnum.SYSTEM_SUCCESS.getDesc());//重置密码成功
    }
    /**
     * @param userEmailVo
     * @return
     * @throws Exception
     * @desc 忘记密码功能
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ResultVo reSetPwd(UserEmailVo userEmailVo) throws Exception {
        AssertHelperUtil.hasText(userEmailVo.getUserName(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【userName】 cannot be empty");
        AssertHelperUtil.hasText(userEmailVo.getPassword(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【password】 cannot be empty");
        AssertHelperUtil.hasText(userEmailVo.getToken(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【token】 cannot be empty");
        UserBaseInfoVo userVo = new UserBaseInfoVo();
        userVo.setToken(userEmailVo.getToken());
        userVo.setUserName(userEmailVo.getUserName());
        userVo.setCredential(userEmailVo.getCredential());
        AssertHelperUtil.isTrue(emailService.checkEmailToken(EmailVo.builder().token(userVo.getToken()).build()), UserEnums.USER_EMAIL_TOKEN_EFFECT.getCode(), UserEnums.USER_EMAIL_TOKEN_EFFECT.getDesc());
        UserInfoVo userInfoVo = UserInfoVo.builder().email(userVo.getUserName()).identityType(0).userStatus("0").activeStatus("1").build();
        UserInfoDo userInfoDo = userMapper.selectUserInfoByUser(userInfoVo);
        AssertHelperUtil.notNull(userInfoDo, UserEnums.USER_EMAIL_IS_UN_ACTIVE.getCode(), UserEnums.USER_EMAIL_IS_UN_ACTIVE.getDesc());
        this.setUserPwdAndCleanToken(userVo,userInfoDo);
        return ResultVo.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(), SystemEnum.SYSTEM_SUCCESS.getDesc());//重置密码成功
    }
    /**
     * set user password and clean email token
     * @param userVo
     * @param userInfoDo
     */
    private void setUserPwdAndCleanToken(UserBaseInfoVo userVo, UserInfoDo userInfoDo) {
        TbUserLoginAuths tbUserLoginAuths = TbUserLoginAuths.builder().id(userInfoDo.getId()).credential(userVo.getCredential()).build();
        tbUserLoginAuthsMapper.updateByPrimaryKeySelective(tbUserLoginAuths);
        EmailRes emailRes=getEmailInfo(userVo);
        Map<String, Object> data = (Map<String, Object>) emailRes.getData();
        CacheUtil.delete(redisTemplate,CacheKeyConstants.KEY_MERCHANT_MAIL_TOKEN_PREFIX+(String)data.get("email")+(String) data.get("type"));
    }

    public EmailRes getEmailInfo(UserBaseInfoVo userVo){
       return emailServerClient.emailValidToken(EmailReq.builder().token(userVo.getToken()).build());
    }
    /**
     * 修改密码
     * @param userEmailVo
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ResultVo modify(UserModifyPasswordVo userEmailVo) throws Exception {
        AssertHelperUtil.hasText(userEmailVo.getNewPassword(), SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),
                "【newPassword】 Cannot be empty");
        AssertHelperUtil.hasText(userEmailVo.getOriPassword(), SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),
                "【oriPassword】 Cannot be empty");
        AssertHelperUtil.hasText(userEmailVo.getEmail(), SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),
                "【email】 Cannot be empty");
        UserInfoVo userInfoVo = UserInfoVo.builder().email(userEmailVo.getEmail()).identityType(0).userStatus("0").activeStatus("1").build();
        UserInfoDo userInfoDo = userMapper.selectUserInfoByUser(userInfoVo);
        AssertHelperUtil.notNull(userInfoDo, UserEnums.USER_EMAIL_IS_NOT_EXITS.getCode(), UserEnums.USER_EMAIL_IS_NOT_EXITS.getDesc());
        TbUserLoginAuths tbUserLoginAuths = TbUserLoginAuths.builder().id(userInfoDo.getId()).credential(userEmailVo.getCredential()).build();
        tbUserLoginAuthsMapper.updateByPrimaryKeySelective(tbUserLoginAuths);
        return ResultVo.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(), SystemEnum.SYSTEM_SUCCESS.getDesc());//重置密码成功
    }

    @Override
    public void saveLoginLog(TbLoginLog tbLoginLog) {
        tbLoginLogMapper.insertSelective(tbLoginLog);
    }
    @Override
    public List<MerchantInfoResp> getMerchantResp(User userDo) {
        List<MerchantInfoResp> merchantResp=new ArrayList<MerchantInfoResp>();
          List<TbUserMerchantBind>  merchants= userMapper.selectUserMerchantByUserNo(UserBind.builder().userNo(userDo.getUserNo()).build());
          if(merchants!=null&&!merchants.isEmpty()){
               for(TbUserMerchantBind merchant:merchants){
                   MerchantInfoResp  merchantInfoResp=merchantService.getMerchantInfoByCondition(MerchantReq.builder().merchantNo(merchant.getMercId()).build());
                   if(merchantInfoResp!=null){
                       merchantResp.add(merchantInfoResp);
                   }
               }
          }
         return merchantResp;
    }
    @Override
    public Boolean checkUserMerchant(User user) {
        List<TbUserMerchantBind>  merchants= userMapper.selectUserMerchantByUserNo(UserBind.builder().userNo(user.getUserNo()).build());
        if(merchants!=null&&!merchants.isEmpty()){
             return true;
        }
        return false;
    }
}
