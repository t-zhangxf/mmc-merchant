package com.pay.merchant.manage.support;

import com.alibaba.fastjson.JSON;
import com.pay.common.enums.*;
import com.pay.common.utils.AssertHelperUtil;
import com.pay.common.utils.CheckEmailUtil;
import com.pay.common.utils.EmptyUtil;
import com.pay.common.utils.GenerateNoUtil;
import com.pay.merchant.entity.*;
import com.pay.merchant.integration.req.PageQueryRequest;
import com.pay.merchant.integration.resp.MerchantAndMemberDetailResponse;
import com.pay.merchant.integration.resp.PageQueryResponse;
import com.pay.merchant.integration.service.MerchantFeignService;
import com.pay.merchant.manage.MailManage;
import com.pay.merchant.manage.UserManager;
import com.pay.merchant.req.*;
import com.pay.merchant.rsp.CreateAndBindingResponse;
import com.pay.merchant.rsp.GetUserInfoResponse;
import com.pay.merchant.service.UserRoleService;
import com.pay.merchant.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("userManager")
@Slf4j
public class UserManageSupport implements UserManager {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    MailManage mailManage;

    @Autowired
    MerchantFeignService merchantFeignService;

    /**
     * 创建用户并绑定商户
     *
     * @param request
     * @return
     */
    @Override
    @Transactional
    public CreateAndBindingResponse createAndBinding(CreateAddBindingRequest request) throws Exception {
        AssertHelperUtil.notNull(request, SystemEnum.VALIDATE_ERROR.getCode(),
                "Create And Binding 【Request】 Cannot be empty");
        AssertHelperUtil.hasText(request.getEmail(), SystemEnum.VALIDATE_ERROR.getCode(),
                "Create And Binding 【email】 Cannot be empty");
        AssertHelperUtil.hasText(request.getMerchantNo(), SystemEnum.VALIDATE_ERROR.getCode(),
                "Create And Binding 【merchantNo】 Cannot be empty");
        AssertHelperUtil.hasText(String.valueOf(request.getUserType()), SystemEnum.VALIDATE_ERROR.getCode(),
                "Create And Binding 【userType】 Cannot be empty");
        AssertHelperUtil.isTrue(CheckEmailUtil.checkEmail(request.getEmail()), SystemEnum.VALIDATE_ERROR.getCode(),
                "【email】 illegal");
        AssertHelperUtil.isTrue(request.getUserType()==0, SystemEnum.USER_TYPE_ILLEGAL.getCode(),
                SystemEnum.USER_TYPE_ILLEGAL.getDesc());
        MerchantAndMemberDetailResponse merchant=merchantFeignService.getMerchantAndMemberDetail(request.getMerchantNo());
        AssertHelperUtil.isTrue(merchant.getData() != null, SystemEnum.MEMBER_NOT_EXIST.getCode(),
                SystemEnum.MEMBER_NOT_EXIST.getDesc());
        String country=merchant.getData().getMember().getCountry();
        String merchantName=merchant.getData().getMerchant().getMerchantName();
        UserMercBinding binding = userService.getAdminBindingFromDb(request.getMerchantNo());
        AssertHelperUtil.isTrue(binding == null, SystemEnum.MERCHANT_LINKED_DUPLICATE.getCode(),
                SystemEnum.MERCHANT_LINKED_DUPLICATE.getDesc());
        Users users = userService.getUserByMail(request.getEmail());
        if (users == null) {
            /**
             * 添加用户基本信息
             */
            users = new Users();
            users.setUserNo(GenerateNoUtil.randomStr("U"));
            users.setEmail(request.getEmail());
            users.setActiveStatus(UserStatusEnum.UNACTIVATED.getStatus());
            Integer affected = userService.saveUsersInfo(users);
            Assert.isTrue(affected > 0, "Insert User Info Failed");
            /**
             * 添加用户授权信息
             */
            UserLoginAuths userLoginAuths = new UserLoginAuths();
            userLoginAuths.setUserNo(users.getUserNo());
            userLoginAuths.setIdentityType(IdentityTypeEnum.EMAIL.getIdentityType());
            userLoginAuths.setIdentifier(request.getEmail());
            userLoginAuths.setCredential("");
            userLoginAuths.setInternalFlag(InternalFlagEnum.IN.getInternalFlag());
            affected = userService.saveUserLoginAuths(userLoginAuths);
            Assert.isTrue(affected > 0, "Insert User Login Auths Failed");
        }
        UserMercBinding userMerc = userService.getUserMerchantBindingFromDb(request.getMerchantNo(), users.getUserNo());
        AssertHelperUtil.isTrue(userMerc == null, SystemEnum.MERCHANT_LINKED_DUPLICATE.getCode(),
                "该商户已关联指定的账号，无法重复关联");
        /**
         * 添加绑定商户信息
         */
        UserMercBinding userMercBinding = new UserMercBinding();
        userMercBinding.setUserNo(users.getUserNo());
        userMercBinding.setUserType(request.getUserType());
        userMercBinding.setMercId(request.getMerchantNo());
        userMercBinding.setBindingDate(new Date());
        userMercBinding.setStatus(BindingStatusEnum.BINDING.getStatus());
        userMercBinding.setBusCnl(BusCnlEnum.MMC.getCode());
        Integer affected = userService.saveUserMercBinding(userMercBinding);
        Assert.isTrue(affected > 0, "Insert User Merchant Binding Failed");

        /**
         * 添加用户角色信息
         */
        UserRole userRole = new UserRole();
        userRole.setMercId(request.getMerchantNo());
        userRole.setUserNo(users.getUserNo());
        userRole.setRoleId(0);
        affected = userRoleService.saveUserRole(userRole);
        Assert.isTrue(affected > 0, "Insert User Role Failed");

        CreateAndBindingResponse response = CreateAndBindingResponse.builder().email(request.getEmail()).
                merchantName(request.getMerchantName()).merchantNo(request.getMerchantNo())
                .templateId("0").build();
        return response;
    }

    /**
     * omc查询账号信息
     * @param request
     * @return
     */
    @Override
    public GetUserInfoResponse getUserInfo(GetUserInfoRequest request) {
        GetUserInfoResponse response=new GetUserInfoResponse();
        List<GetUserInfoResponse.userDetail> rows=new ArrayList<>();
        if(EmptyUtil.isNotEmpty(request.getEmail())){
            Users users = userService.getUserByMail(request.getEmail());
            if(users==null){
                return GetUserInfoResponse.builder().rows(rows).build();
            }
            List<UserMercBinding> list= userService.queryUserBinding("",users.getUserNo());
            if(EmptyUtil.isNotEmpty(list)){
                for(UserMercBinding userMercBinding:list){
                    MerchantAndMemberDetailResponse  merchantAndMemberDetail=merchantFeignService.
                            getMerchantAndMemberDetail(userMercBinding.getMercId());
                    if(merchantAndMemberDetail.getData()!=null){
                        GetUserInfoResponse.userDetail resp=GetUserInfoResponse.userDetail.builder().activeFlag(users.getActiveStatus()).bindingFlag(0)
                                .createTime(merchantAndMemberDetail.getData().getMember().getOpenTime())
                                .email(users.getEmail())
                                .merchantNo(userMercBinding.getMercId())
                                .merchantName(merchantAndMemberDetail.getData().getMerchant().getMerchantName())
                                .userType(userMercBinding.getUserType())
                                .simpleName(merchantAndMemberDetail.getData().getMerchant().getSimpleName()).build();
                        rows.add(resp);
                    }
                }
            }else{
                GetUserInfoResponse.userDetail resp=GetUserInfoResponse.userDetail.builder().email(users.getEmail()).activeFlag(users.getActiveStatus()).bindingFlag(1)
                        .userType((byte)2).build();
                rows.add(resp);
            }
             response=GetUserInfoResponse.builder().rows(rows).build();
        }else{
            PageQueryRequest req=new PageQueryRequest();
            if(request.getPageNum()!=null){
                req.setPageIndex(request.getPageNum());
            }else{
                req.setPageIndex(1);
            }
            if(request.getPageSize()!=null){
               req.setPageSize(request.getPageSize());
            }else{
                req.setPageSize(1000);
            }

            if(EmptyUtil.isNotEmpty(request.getMerchantNo())){
                req.setMerchantNo(request.getMerchantNo());
            }
            if(EmptyUtil.isNotEmpty(request.getMerchantName())){
                req.setMerchantName(request.getMerchantName());
            }
            if(request.getStartTime()!=null){
                req.setStartTime(request.getStartTime());
            }
            if(request.getEndTime()!=null){
                req.setEndTime(request.getEndTime());
            }
            PageQueryResponse pageQueryResponse=merchantFeignService.pageQuery(req);
            log.info("pageQueryResponse{}",JSON.toJSONString(pageQueryResponse));
            List<PageQueryResponse.data.rows> list=pageQueryResponse.getData().getRows();
            if(EmptyUtil.isNotEmpty(list)){
                for(PageQueryResponse.data.rows merchant:list){
                    List<UserMercBinding> bindingList= userService.queryUserBinding( merchant.getMerchantNo(),"");
                    if(EmptyUtil.isNotEmpty(bindingList)){
                        for(UserMercBinding userMercBinding:bindingList){
                            Users users=userService.getFromDb(userMercBinding.getUserNo());
                            GetUserInfoResponse.userDetail resp=GetUserInfoResponse.userDetail.builder().activeFlag(users.getActiveStatus()).bindingFlag(0)
                                    .createTime(merchant.getCreateTime())
                                    .email(users.getEmail()).merchantNo(userMercBinding.getMercId())
                                    .merchantName(merchant.getMerchantName())
                                    .userType(userMercBinding.getUserType())
                                    .simpleName(merchant.getSimpleName()).build();
                            rows.add(resp);
                        }
                    }else{
                        GetUserInfoResponse.userDetail resp=GetUserInfoResponse.userDetail.builder().bindingFlag(1)
                                .createTime(merchant.getCreateTime())
                                .merchantNo(merchant.getMerchantNo())
                                .merchantName(merchant.getMerchantName())
                                .userType((byte)3)
                                .simpleName(merchant.getSimpleName()).build();
                        rows.add(resp);
                    }
                }
                 response=GetUserInfoResponse.builder().rows(rows).build();
            }else{
                return GetUserInfoResponse.builder().rows(rows).build();
            }
        }
        log.info("response{}",JSON.toJSONString(response));
        return response;
    }

    /**
     * 解绑商户
     *
     * @param request
     * @return
     */
    @Override
    @Transactional
    public boolean unbunding(UnbundingRequest request) {
        AssertHelperUtil.notNull(request, SystemEnum.VALIDATE_ERROR.getCode(),
                "Unbunding 【Request】 Cannot be empty");
        AssertHelperUtil.hasText(request.getEmail(), SystemEnum.VALIDATE_ERROR.getCode(),
                "Unbunding 【email】 Cannot be empty");
        AssertHelperUtil.hasText(request.getMerchantNo(), SystemEnum.VALIDATE_ERROR.getCode(),
                "Unbunding 【merchantNo】 Cannot be empty");
        AssertHelperUtil.hasText(request.getMerchantName(), SystemEnum.VALIDATE_ERROR.getCode(),
                "Unbunding 【merchantName】 Cannot be empty");
        Users users = userService.getUserByMail(request.getEmail());
        AssertHelperUtil.isTrue(users != null, SystemEnum.USER_NOT_EXIST.getCode(),
                SystemEnum.USER_NOT_EXIST.getDesc());
        UserMercBinding userMercBinding = userService.getUserMerchantBindingFromDb(request.getMerchantNo(), users.getUserNo());
        AssertHelperUtil.isTrue(userMercBinding != null, SystemEnum.BINDING_ILLEGAL.getCode(),
                SystemEnum.BINDING_ILLEGAL.getDesc());
        Integer affected = userService.deleteUserMerchantBinding(request.getMerchantNo(), users.getUserNo());
        AssertHelperUtil.isTrue(affected > 0, SystemEnum.SYSTEM_ERROR.getCode(),
                SystemEnum.SYSTEM_ERROR.getDesc());
        affected = userRoleService.deleteUserRole(request.getMerchantNo(), users.getUserNo());
        AssertHelperUtil.isTrue(affected > 0, SystemEnum.SYSTEM_ERROR.getCode(),
                SystemEnum.SYSTEM_ERROR.getDesc());
        return true;
    }

    /**
     * 权限设置
     * @param request
     */
    @Override
    public void setUserTypeAndPermission(SetUserTypeAndPermissionRequest request) {
        AssertHelperUtil.notNull(request, SystemEnum.VALIDATE_ERROR.getCode(),
                "Set UserType And Permission 【Request】 Cannot be empty");
        AssertHelperUtil.hasText(request.getEmail(), SystemEnum.VALIDATE_ERROR.getCode(),
                "Set UserType And Permission 【email】 Cannot be empty");
        AssertHelperUtil.hasText(request.getMerchantNo(), SystemEnum.VALIDATE_ERROR.getCode(),
                "Set UserType And Permission 【merchantNo】 Cannot be empty");
        AssertHelperUtil.hasText(String.valueOf(request.getChangeNo()), SystemEnum.VALIDATE_ERROR.getCode(),
                "Set UserType And Permission 【ChangeNo】 Cannot be empty");
        AssertHelperUtil.hasText(String.valueOf(request.getUserType()), SystemEnum.VALIDATE_ERROR.getCode(),
                "Set UserType And Permission 【UserType】 Cannot be empty");
        AssertHelperUtil.isTrue(UserTypeEnum.hasCode(request.getUserType()), SystemEnum.VALIDATE_ERROR.getCode(),
                "【userType】 Illegal");
        Users users = userService.getUserByMail(request.getEmail());
        AssertHelperUtil.isTrue(users != null, SystemEnum.USER_NOT_EXIST.getCode(),
                SystemEnum.USER_NOT_EXIST.getDesc());
        if(request.getUserType()==(byte)0){
            UserMercBinding binding = userService.getAdminBindingFromDb(request.getMerchantNo());
            AssertHelperUtil.isTrue(binding == null, SystemEnum.MERCHANT_LINKED_DUPLICATE.getCode(),
                    SystemEnum.MERCHANT_LINKED_DUPLICATE.getDesc());
        }
        List<UserMercBinding> list=userService.queryUserBinding(request.getMerchantNo(),users.getUserNo());
        AssertHelperUtil.isTrue(EmptyUtil.isNotEmpty(list), SystemEnum.BINDING_ILLEGAL.getCode(),
                SystemEnum.BINDING_ILLEGAL.getDesc());
        AssertHelperUtil.isTrue(request.getUserType()!=list.get(0).getUserType(), SystemEnum.ILLEGAL_CHANGE.getCode(),
                SystemEnum.ILLEGAL_CHANGE.getDesc());
        Integer affected = userService.modifyBindingUserType(request.getMerchantNo(),users.getUserNo(),request.getUserType());
        AssertHelperUtil.isTrue(affected > 0, SystemEnum.SYSTEM_ERROR.getCode(),
                SystemEnum.SYSTEM_ERROR.getDesc());
        affected=userRoleService.modifyUserRole(request.getMerchantNo(),users.getUserNo(), Integer.valueOf(request.getUserType()));
        AssertHelperUtil.isTrue(affected > 0, SystemEnum.SYSTEM_ERROR.getCode(),
                SystemEnum.SYSTEM_ERROR.getDesc());
        MerchantAndMemberDetailResponse merchant=merchantFeignService.getMerchantAndMemberDetail(request.getMerchantNo());
        String country=merchant.getData().getMember().getCountry();
        MailByTemplateRequest mailRequest = new MailByTemplateRequest();
        mailRequest.setEmail(request.getEmail());
        mailRequest.setMercId(request.getMerchantNo());
        mailRequest.setMercNm(merchant.getData().getMerchant().getMerchantName());
        AdjustedTypeEnum adjustedTypeEnum=AdjustedTypeEnum.getByType(request.getChangeNo());
        String templateId="";
        if(CountryEnum.CHINA.getCode().equals(country)){
            templateId="m003";
            mailRequest.setDescription(adjustedTypeEnum.getChineseDesc());
        }else{
            templateId="m033";
            mailRequest.setDescription(adjustedTypeEnum.getEnglishDesc());
        }
        MailTemplateEnum templateEnum=MailTemplateEnum.getByTemplateId(templateId);
        mailRequest.setTemplateName(templateEnum.getTemplateName());
        mailRequest.setSubject(templateEnum.getSubject());
        String url = mailManage.createLink(request.getEmail(), templateEnum.getTemplateId());
        mailRequest.setUrl(url);
        try {
            mailManage.sendByTemplate(mailRequest);
        } catch (Exception e) {
            log.error("Send By Template Fail,error msg{}", e.getMessage());
        }
    }

}
