package com.merchant.web.service.Impl;
import com.merchant.web.Do.UserInfoDo;
import com.merchant.web.Do.UserPermissionDo;
import com.merchant.web.common.entity.request.MerchantReq;
import com.merchant.web.common.entity.request.UserPermissionReq;
import com.merchant.web.common.entity.response.BaseDataResp;
import com.merchant.web.common.entity.response.MerchantResponse;
import com.merchant.web.common.entity.result.Result;
import com.merchant.web.common.entity.vo.PermissionShowVo;
import com.merchant.web.common.entity.vo.PermissionVo;
import com.merchant.web.common.enums.*;
import com.merchant.web.common.exception.BizException;
import com.merchant.web.common.utils.AssertHelperUtil;
import com.merchant.web.common.utils.StringUtils;
import com.merchant.web.entity.TbPermissionShow;
import com.merchant.web.entity.TbUserMerchantBind;
import com.merchant.web.entity.User;
import com.merchant.web.entity.UserBind;
import com.merchant.web.integration.feignClient.MerchantServerClient;
import com.merchant.web.integration.feignClient.PermissionServerClient;
import com.merchant.web.mapper.*;
import com.merchant.web.service.PermissionShowService;
import com.merchant.web.vo.UserPermissionVo;
import com.merchant.web.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
@Service
public class PermissionShowServiceImpl implements PermissionShowService {
    @Autowired
    TbPermissionShowMapper tbPermissionShowMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TbUserLoginAuthsMapper tbUserLoginAuthsMapper;
    @Autowired
    TbUserPermissionMapper tbUserPermissionMapper;
    @Autowired
    PermissionServerClient permissionServerClient;
    @Autowired
    MerchantServerClient merchantServerClient;
    @Autowired
    TbUserMerchantBindMapper tbUserMerchantBindMapper;

    @Override
    public Result findAllPermissionList(PermissionVo permissionVo) {
        AssertHelperUtil.hasText(permissionVo.getMerchantId(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        JSONArray  commonPermission=new JSONArray();
        PermissionShowVo permissionShowVo=PermissionShowVo.builder().build();
        //  管理类 0
        permissionShowVo.setType(0);
        this.getPermission(commonPermission,permissionShowVo);
        // 操作类
        permissionShowVo.setType(1);
        this.getPermission(commonPermission,permissionShowVo);
        // 审核类
        permissionShowVo.setType(2);
        this.getPermission(commonPermission,permissionShowVo);
        return Result.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),commonPermission);
    }
    /**
     * @desc  设置管理类
     * @param commonPermission
     * @param permissionShowVo
     */
    public  void getPermission(JSONArray commonPermission,PermissionShowVo permissionShowVo){
        List<TbPermissionShow> tbPermissionShow= tbPermissionShowMapper.selectPermissionShows(permissionShowVo);
        if(tbPermissionShow!=null&&!tbPermissionShow.isEmpty()){
            for (TbPermissionShow permissionShow:tbPermissionShow ){
                JSONObject commonJsonObject=new JSONObject();
                commonJsonObject.put("name",permissionShow.getName());
                commonJsonObject.put("category",permissionShow.getType());
                commonJsonObject.put("permitId",permissionShow.getPsNumber());
                commonPermission.add(commonJsonObject);
            }
        }
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Result addPermission(PermissionShowVo permissionVo,User user,String merchantId) throws Exception {
        AssertHelperUtil.hasText(permissionVo.getMerchantId(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.isTrue(permissionVo.getMerchantId().equals(merchantId),UserEnums.USER_MERCHANT_NOT_THE_SAME_AS.getCode(),UserEnums.USER_MERCHANT_NOT_THE_SAME_AS.getDesc());
        AssertHelperUtil.hasText(permissionVo.getEmail(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.hasText(permissionVo.getPermitStr(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.isNull(permissionVo.getPermitStr().split(","),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.isTrue(StringUtils.emailFormat(permissionVo.getEmail()),UserEnums.USER_EMAIL_FORMAT_IS_ERROR.getCode(),UserEnums.USER_EMAIL_FORMAT_IS_ERROR.getDesc());
        AssertHelperUtil.isTrue(!permissionVo.getEmail().equals(user.getIdentifier()),UserEnums.USER_NOT_ADD_MYSELF.getCode(),UserEnums.USER_NOT_ADD_MYSELF.getDesc());//不能添加一个和自己相同的账号
        AssertHelperUtil.isTrue(!(permissionVo.getPermitStr().split(",").length>1 && Arrays.asList(permissionVo.getPermitStr().split(",")).contains("PS_10005")),UserEnums.USER_PERMISSION_IS_ERROR.getCode(),UserEnums.USER_PERMISSION_IS_ERROR.getDesc());
        List<Integer> bindStatus=new ArrayList<Integer>();
        bindStatus.add(1);
        UserBind userBind=UserBind.builder().merChantId(permissionVo.getMerchantId()).email(permissionVo.getEmail()).bindStatus(bindStatus).build();
        List <UserInfoDo> userInfoDos=userMapper.findUserBindInfoByUserMerchant(userBind);
        if(userInfoDos!=null&&!userInfoDos.isEmpty()){// 所添加的用户已经绑定过商户，请求进行权限配置
            return Result.buildResult(UserEnums.USER_MERCHANT_HAD_ADDED.getCode(),UserEnums.USER_MERCHANT_HAD_ADDED.getDesc());
        }
        try {
            MerchantResponse merchantResponse= merchantServerClient.getMerchantInfoByCondition(MerchantReq.builder().merchantNo(permissionVo.getMerchantId()).build());
            String merChantName="";
            if(SystemEnum.SYSTEM_SUCCESS.getCode().equals(merchantResponse.getBizCode())){
                merChantName=merchantResponse.getData().getMerchantName();
            }else {
                return Result.buildResult(merchantResponse.getBizCode(),merchantResponse.getMessage());
            }
            UserPermissionReq userPermissionReq=UserPermissionReq.builder().email(permissionVo.getEmail()).merchantId(permissionVo.getMerchantId()).permitStr(permissionVo.getPermitStr()).userNo(user.getUserNo()).merchantName(merChantName).inviter(user.getIdentifier()).build();
            BaseDataResp  baseDataResp= permissionServerClient.addPermission(userPermissionReq);
            log.info("baseDataResp:"+baseDataResp.toString());
           if(SystemEnum.SYSTEM_SUCCESS.getCode().equals(baseDataResp.getBizCode())){
                return new Result(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),baseDataResp.getData().getIsRegistered());
           }else {
                return Result.buildResult(baseDataResp.getBizCode(),baseDataResp.getMessage());
           }
        }catch (Exception e){
            e.printStackTrace();
           log.info("add user permission had exception:"+e.getMessage());
            throw  new BizException(SystemEnum.SYSTEM_BAD_RESPONSE.getCode(),e.getMessage());
        }

    }

    @Override
    public Result permissionListByUserNo(PermissionShowVo permissionShowVo) {
        AssertHelperUtil.hasText(permissionShowVo.getMerchantId(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.hasText(permissionShowVo.getUserNo(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        UserVo userVo=UserVo.builder().userNo(permissionShowVo.getUserNo()).identityType("0").build();
        User  user= userMapper.selectUseInfoByCondition(userVo);
        AssertHelperUtil.notNull(user,UserEnums.USER_INFO_IS_NOT_NULL.getCode(),UserEnums.USER_INFO_IS_NOT_NULL.getDesc());//用户号为空
        UserBind userBind=UserBind.builder().merChantId(permissionShowVo.getMerchantId()).email(user.getIdentifier()).build();
        List <UserInfoDo> userInfoDos=userMapper.findUserBindInfoByUserMerchant(userBind);
        AssertHelperUtil.isNull(userInfoDos,UserEnums.USER_MERCHANT_IS_NULL.getCode(),UserEnums.USER_MERCHANT_IS_NULL.getDesc());//商户信息为空
        JSONArray  commonPermission=new JSONArray();
        PermissionShowVo permissionShow=PermissionShowVo.builder().userNo(user.getUserNo()).build();
        //  管理类 0
        permissionShow.setType(0);
        this.getEditPermissions(commonPermission,permissionShow);
        // 操作类
        permissionShow.setType(1);
        this.getEditPermissions(commonPermission,permissionShow);
        // 审核类
        permissionShow.setType(2);
        this.getEditPermissions(commonPermission,permissionShow);

        return Result.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),commonPermission);
    }

    /**
     * @desc 编辑管理类
     * @param commonPermission
     * @param permissionShowVo
     */
    public  void getEditPermissions(JSONArray commonPermission,PermissionShowVo permissionShowVo){
        List<TbPermissionShow> tbPermissionShow= tbPermissionShowMapper.selectPermissionShows(permissionShowVo);
        if(tbPermissionShow!=null&&!tbPermissionShow.isEmpty()){
            for (TbPermissionShow permissionShow:tbPermissionShow ){
                JSONObject commonJsonObject=new JSONObject();
                commonJsonObject.put("name",permissionShow.getName());
                commonJsonObject.put("category",permissionShow.getType());
                commonJsonObject.put("permitId",permissionShow.getPsNumber());
                UserPermissionVo userPermissionVo=UserPermissionVo.builder().merchantId(permissionShowVo.getMerchantId()).userNo(permissionShowVo.getUserNo()).psNumber(permissionShow.getPsNumber()).build();
                List<UserPermissionDo>  userPermissionDos =  tbUserPermissionMapper.findUserPermissionInfoByUser(userPermissionVo);
                if(userPermissionDos!=null&&!userPermissionDos.isEmpty()){
                   commonJsonObject.put("isChecked","1");
                }else {
                    commonJsonObject.put("isChecked","0");
                }
                commonPermission.add(commonJsonObject);
            }
        }
    }
    @Override
    public Result editPermission(PermissionShowVo permissionVo, User user,String merchantId) throws Exception {
        AssertHelperUtil.hasText(permissionVo.getUserNo(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.hasText(permissionVo.getMerchantId(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.isTrue(permissionVo.getMerchantId().equals(merchantId),UserEnums.USER_MERCHANT_NOT_THE_SAME_AS.getCode(),UserEnums.USER_MERCHANT_NOT_THE_SAME_AS.getDesc());
        AssertHelperUtil.hasText(permissionVo.getPermitStr(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.isNull(permissionVo.getPermitStr().split(","),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        try {
            UserVo userVo=UserVo.builder().userNo(permissionVo.getUserNo()).identityType("0").userStatus("0").build();
            User  userDo= userMapper.selectUseInfoByCondition(userVo);
            AssertHelperUtil.notNull(userDo,UserEnums.USER_INFO_IS_NOT_NULL.getCode(),UserEnums.USER_INFO_IS_NOT_NULL.getDesc());//用户号为空
            AssertHelperUtil.isTrue(!permissionVo.getUserNo().equals(user.getUserNo()),UserEnums.USER_NOT_DELETE_MYSELF.getCode(),UserEnums.USER_NOT_DELETE_MYSELF.getDesc());//成员不能删除本身权限
            TbUserMerchantBind  tbUserMerchantBind=  tbUserMerchantBindMapper.selectUserMerchantBindByCondition(TbUserMerchantBind.builder().userNo(permissionVo.getUserNo()).mercId(merchantId).build());
            Byte userType=tbUserMerchantBind.getUserType();
            AssertHelperUtil.isTrue(!Byte.valueOf("0").equals(userType),UserEnums.USER_NOT_DELETE_ADMIN_PERMISSIONS.getCode(),UserEnums.USER_NOT_DELETE_ADMIN_PERMISSIONS.getDesc());//成员不删除admin权限
            MerchantResponse merchantResponse= merchantServerClient.getMerchantInfoByCondition(MerchantReq.builder().merchantNo(permissionVo.getMerchantId()).build());
            String merChantName="";
            if(SystemEnum.SYSTEM_SUCCESS.getCode().equals(merchantResponse.getBizCode())){
                merChantName=merchantResponse.getData().getMerchantName();
            }else {
                return Result.buildResult(merchantResponse.getBizCode(),merchantResponse.getMessage());
            }
            UserPermissionReq userPermissionReq=UserPermissionReq.builder().email(permissionVo.getEmail()).merchantId(permissionVo.getMerchantId()).permitStr(permissionVo.getPermitStr()).userNo(permissionVo.getUserNo()).merchantName(merChantName).currentUserNo(user.getUserNo()).inviter(user.getEmail()).build();
            BaseDataResp  baseDataResp= permissionServerClient.editPermission(userPermissionReq);
            if(SystemEnum.SYSTEM_SUCCESS.getCode().equals(baseDataResp.getBizCode())){
                return Result.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc());
            }else {
                return Result.buildResult(baseDataResp.getBizCode(),baseDataResp.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("edit user permission had exception:"+e.getMessage());
            return Result.buildResult(SystemEnum.SYSTEM_BAD_RESPONSE.getCode(),e.getMessage());
        }

    }
    @Override
    public Result deletePermission(PermissionShowVo permissionVo, User user,String merchantId) throws Exception {
        AssertHelperUtil.hasText(permissionVo.getMerchantId(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.isTrue(permissionVo.getMerchantId().equals(merchantId),UserEnums.USER_MERCHANT_NOT_THE_SAME_AS.getCode(),UserEnums.USER_MERCHANT_NOT_THE_SAME_AS.getDesc());
        AssertHelperUtil.hasText(permissionVo.getUserNo(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        UserVo userVo=UserVo.builder().userNo(permissionVo.getUserNo()).identityType("0").userStatus("0").build();
        try {
            MerchantResponse merchantResponse= merchantServerClient.getMerchantInfoByCondition(MerchantReq.builder().merchantNo(permissionVo.getMerchantId()).build());
            if(SystemEnum.SYSTEM_SUCCESS.getCode().equals(merchantResponse.getBizCode())){
                log.info("merchantResponse："+merchantResponse.getData().toString());
            }else {
                return Result.buildResult(UserEnums.USER_MERCHANT_IS_NULL.getCode(),UserEnums.USER_MERCHANT_IS_NULL.getDesc());
            }
            User  userInfo= userMapper.selectUseInfoByCondition(userVo);
            AssertHelperUtil.notNull(userInfo,UserEnums.USER_INFO_IS_NOT_NULL.getCode(),UserEnums.USER_INFO_IS_NOT_NULL.getDesc());//用户号为空
            AssertHelperUtil.isTrue(!userInfo.getUserNo().equals(user.getUserNo()),UserEnums.USER_NOT_DELETE_MYSELF.getCode(),UserEnums.USER_NOT_DELETE_MYSELF.getDesc());//成员不能删除本身权限
            TbUserMerchantBind  tbUserMerchantBind=  tbUserMerchantBindMapper.selectUserMerchantBindByCondition(TbUserMerchantBind.builder().userNo(permissionVo.getUserNo()).mercId(merchantId).build());
            Byte userType=tbUserMerchantBind.getUserType();
            AssertHelperUtil.isTrue(!Byte.valueOf("0").equals(userType),UserEnums.USER_NOT_DELETE_ADMIN_PERMISSIONS.getCode(),UserEnums.USER_NOT_DELETE_ADMIN_PERMISSIONS.getDesc());//成员不删除admin权限
            UserPermissionReq userPermissionReq=UserPermissionReq.builder().merchantId(permissionVo.getMerchantId()).userNo(permissionVo.getUserNo()).build();
            BaseDataResp  baseDataResp= permissionServerClient.deletePermission(userPermissionReq);
            if(SystemEnum.SYSTEM_SUCCESS.getCode().equals(baseDataResp.getBizCode())){
                return Result.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc());
            }else {
                return Result.buildResult(baseDataResp.getBizCode(),baseDataResp.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("deletePermission :"+e.getMessage());
            return Result.buildResult(SystemEnum.SYSTEM_BAD_RESPONSE.getCode(),e.getMessage());
        }
    }
}
