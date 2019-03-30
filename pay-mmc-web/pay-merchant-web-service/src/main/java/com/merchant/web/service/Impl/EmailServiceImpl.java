package com.merchant.web.service.Impl;
import com.alibaba.fastjson.JSON;
import com.merchant.web.Do.UserInfoDo;
import com.merchant.web.common.constants.CacheKeyConstants;
import com.merchant.web.common.entity.request.EmailReq;
import com.merchant.web.common.entity.request.UserEmailReq;
import com.merchant.web.common.entity.response.EmailRes;
import com.merchant.web.common.entity.result.Result;
import com.merchant.web.common.entity.result.ResultVo;
import com.merchant.web.common.entity.vo.EmailVo;
import com.merchant.web.common.entity.vo.PermissionEmailVo;
import com.merchant.web.common.enums.EmailEnums;
import com.merchant.web.common.enums.SystemEnum;
import com.merchant.web.common.enums.UserEnums;
import com.merchant.web.common.utils.AssertHelperUtil;
import com.merchant.web.common.utils.CacheUtil;
import com.merchant.web.entity.User;
import com.merchant.web.integration.feignClient.EmailServerClient;
import com.merchant.web.mapper.TbUserLoginAuthsMapper;
import com.merchant.web.mapper.UserMapper;
import com.merchant.web.service.EmailService;
import com.merchant.web.vo.UserInfoVo;
import com.merchant.web.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    EmailServerClient emailServerClient;

    @Autowired
    TbUserLoginAuthsMapper tbUserLoginAuthsMapper;

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public Boolean sendEmail(EmailVo emailVo) {
        Boolean flag = false;
        try {
            EmailRes emailRes = emailServerClient.sendEmail(emailVo);
            log.info("emailRes{}" ,JSON.toJSONString(emailRes));
            if (SystemEnum.SYSTEM_SUCCESS.getCode().equals(emailRes.getBizCode())) {
                flag = true;
            }
        } catch (Exception e) {
            log.info("send email happened exception:" + e.getMessage());
        }
        return flag;
    }

    @Override
    public ResultVo sendEmailPwd(EmailVo emailVo) throws Exception {
        AssertHelperUtil.notNull(emailVo.getEmail(), SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "【email】 Cannot be empty");
        UserInfoVo userInfoVo = UserInfoVo.builder().email(emailVo.getEmail()).identityType(0).userStatus("0").activeStatus("1").build();
        UserInfoDo userInfoDo = userMapper.selectUserInfoByUser(userInfoVo);
        AssertHelperUtil.notNull(userInfoDo, UserEnums.USER_EMAIL_IS_NOT_EXITS.getCode(), UserEnums.USER_EMAIL_IS_NOT_EXITS.getDesc());
        try {
            if (this.sendEmail(emailVo)) {
                return ResultVo.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(), SystemEnum.SYSTEM_SUCCESS.getDesc());//重置密码成功
            } else {
                return ResultVo.buildResult(SystemEnum.SYSTEM_BAD_RESPONSE.getCode(), SystemEnum.SYSTEM_BAD_RESPONSE.getDesc());//邮箱发送错误
            }
        } catch (Exception e) {
            log.info("reset password send email happened exception:" + e.getMessage());
            throw new Exception(e);
        }
    }

    /**
     * @param emailVo
     * @return
     * @throws Exception
     * @desc valid email token effect
     */
    @Override
    public Result emailValidToken(EmailVo emailVo) throws Exception {
        AssertHelperUtil.hasText(emailVo.getToken(), SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "【token】 cannot be empty");
        EmailRes emailRes=  emailServerClient.emailValidToken(EmailReq.builder().token(emailVo.getToken()).build());
        AssertHelperUtil.isTrue(checkEmailToken(emailVo),UserEnums.USER_EMAIL_TOKEN_EFFECT.getCode(),UserEnums.USER_EMAIL_TOKEN_EFFECT.getDesc());
        if (UserEnums.SYSTEM_SUCCESS.getCode().equals(emailRes.getBizCode())) {//验证
            Map<String, Object> data = (Map<String, Object>) emailRes.getData();
            JSONObject dataJsonObject=new JSONObject();
            dataJsonObject.put("email",data.get("email"));
            if (EmailEnums.EMAIL_TOKEN_NORMAL.getCode().equals((String) data.get("status"))) {
                return  Result.buildResult(UserEnums.SYSTEM_SUCCESS.getCode(),UserEnums.SYSTEM_SUCCESS.getDesc(),dataJsonObject);
            }
        }
        return  Result.buildResult(SystemEnum.SYSTEM_BAD_RESPONSE.getCode(),SystemEnum.SYSTEM_BAD_RESPONSE.getDesc(),"");
    }
   public Boolean  checkEmailToken(EmailVo emailVo) {
       EmailRes emailRes = emailServerClient.emailValidToken(EmailReq.builder().token(emailVo.getToken()).build());
       if (UserEnums.SYSTEM_SUCCESS.getCode().equals(emailRes.getBizCode())) {//验证
           Map<String, Object> data = (Map<String, Object>) emailRes.getData();
           String emailToken = CacheUtil.getObjectValueByKey(redisTemplate, CacheKeyConstants.KEY_MERCHANT_MAIL_TOKEN_PREFIX + (String) data.get("email") + (String) data.get("type"));
           if (EmailEnums.EMAIL_TOKEN_NORMAL.getCode().equals((String) data.get("status"))) {
               if (StringUtils.isEmpty(emailToken)) {
                   return false;
               }
               return true;
           }else if(EmailEnums.EMAIL_TOKEN_EXPIRE.getCode().equals((String) data.get("status"))){
               AssertHelperUtil.isTrue(false,UserEnums.USER_EMAIL_TOKEN_EXPIRE.getCode(),UserEnums.USER_EMAIL_TOKEN_EXPIRE.getDesc());
           }
       }
       return false;
   }
    @Override
    public ResultVo notify(PermissionEmailVo permissionEmailVo,User user) throws Exception {
        AssertHelperUtil.hasText(permissionEmailVo.getEmail(), SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.hasText(permissionEmailVo.getMerchantId(), SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.hasText(permissionEmailVo.getUserNo(), SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        UserEmailReq userEmailReq=UserEmailReq.builder().email(permissionEmailVo.getEmail()).merchantId(permissionEmailVo.getMerchantId()).userNo(permissionEmailVo.getUserNo()).build();
        User  userVo=  userMapper.selectUseInfoByCondition(UserVo.builder().userNo(permissionEmailVo.getUserNo()).identityType("0").build());
        AssertHelperUtil.notNull(userVo,UserEnums.USER_INFO_IS_NOT_NULL.getCode(),UserEnums.USER_INFO_IS_NOT_NULL.getDesc());
        EmailRes   emailRes=emailServerClient.userEmailNotify(userEmailReq);
        return ResultVo.buildResult(emailRes.getBizCode(),emailRes.getMessage());
    }
}
