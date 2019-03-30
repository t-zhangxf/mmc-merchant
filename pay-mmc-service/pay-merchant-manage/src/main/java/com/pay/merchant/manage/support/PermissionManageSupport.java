package com.pay.merchant.manage.support;
import com.alibaba.fastjson.JSONObject;
import com.pay.common.entity.result.BizResult;
import com.pay.common.entity.vo.PermissionShowVo;
import com.pay.common.entity.vo.UserVo;
import com.pay.common.enums.AdjustedTypeEnum;
import com.pay.common.enums.CountryEnum;
import com.pay.common.enums.MailTemplateEnum;
import com.pay.common.enums.SystemEnum;
import com.pay.common.utils.AssertHelperUtil;
import com.pay.common.utils.GenerateNoUtil;
import com.pay.merchant.entity.*;
import com.pay.merchant.integration.resp.MerchantAndMemberDetailResponse;
import com.pay.merchant.integration.service.MerchantFeignService;
import com.pay.merchant.manage.MailManage;
import com.pay.merchant.manage.PermissionManage;
import com.pay.merchant.req.MailByTemplateRequest;
import com.pay.merchant.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service("permissionManage")
@Slf4j
public class PermissionManageSupport implements PermissionManage {
    @Autowired
    PermissionService permissionService;
    @Autowired
    MailManage mailManage;
    @Resource(name = "asyncExecutor")
    private ThreadPoolTaskExecutor asyncExecutor;

    @Autowired
    MerchantFeignService merchantFeignService;
    static   String templateId="";
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public BizResult add(PermissionShowVo permissionShowVo) throws Exception{
        List<RolePermission> rolePermissions=new ArrayList<RolePermission>();
        JSONObject data=new JSONObject();
        try {
            // 设置用户权限
            templateId =addUserPermission(permissionShowVo, rolePermissions, data);
            //** 发送邮件功能
            asyncExecutor.execute(new Runnable(){
                public void run() {
                    try {
                         sendEmailVo(permissionShowVo, templateId);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
         }catch (Exception e){
            throw new Exception(e);
         }
         rolePermissions.clear();
         return BizResult.create(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),data);
    }

    /**
     * @desc 添加用户权限
     * @param permissionShowVo
     * @param rolePermissions
     * @param data
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    public String addUserPermission(PermissionShowVo permissionShowVo, List<RolePermission> rolePermissions, JSONObject data) {
        String templateId;
        String userNo=GenerateNoUtil.randomStr("U");
        UserVo userVo=UserVo.builder().email(permissionShowVo.getEmail()).build();
        List<User>  users= permissionService.selectUseInfoByCondition(userVo);
        String  roleNumber=GenerateNoUtil.randomStr("ROLE_");
        MerchantAndMemberDetailResponse response=merchantFeignService.getMerchantAndMemberDetail(permissionShowVo.getMerchantId());
        String country=response.getData().getMember().getCountry();
        if(users!=null&&!users.isEmpty()){//已存在登录账户
            //用户绑定商户信息
            userNo=users.get(0).getUserNo();//已有userNo
            UserMercBinding userMercBinding=permissionService.selectUserMerchantBindingByUserNo(UserBind.builder().userNo(userNo).merChantId(permissionShowVo.getMerchantId()).build());
            if(userMercBinding!=null){
                permissionService.updateUserMerchantBindingByUserNo(UserBind.builder().userNo(userNo).merChantId(permissionShowVo.getMerchantId()).isDeleted(Byte.valueOf("0")).status("1").build());
                setExitsUserRolePermission(permissionShowVo, rolePermissions, userNo, roleNumber,true);
            }else {
                setExitsUserRolePermission(permissionShowVo, rolePermissions, userNo, roleNumber,false);
            }
            if((byte)1==users.get(0).getActiveStatus()){//已激活
                if(CountryEnum.CHINA.getCode().equals(country)){
                    templateId="m005";
                }else {
                    templateId="m055";
                }
                data.put("isRegistered","1");
            }else{//未激活
                if(CountryEnum.CHINA.getCode().equals(country)){
                    templateId="m006";
                }else {
                    templateId="m066";
                }
                data.put("isRegistered","0");
            }

         }else {
            saveRole(permissionShowVo, rolePermissions, userNo, roleNumber);

            if(CountryEnum.CHINA.getCode().equals(country)){
                templateId="m006";
            }else {
                templateId="m066";
            }
            data.put("isRegistered","0");
         }

        log.info("templateId{}",templateId);
        return templateId;
    }

    private boolean sendEmailVo(PermissionShowVo permissionShowVo, String templateId) throws Exception {
        try {
            log.info("permissionShowVo{}",permissionShowVo);
            log.info("templateId{}",templateId);
            MailByTemplateRequest mailRequest = new MailByTemplateRequest();
            mailRequest.setEmail(permissionShowVo.getEmail());
            mailRequest.setInviter(permissionShowVo.getInviter());
            mailRequest.setMercId(permissionShowVo.getMerchantId());
            mailRequest.setMercNm(permissionShowVo.getMerchantName());
            MailTemplateEnum templateEnum=MailTemplateEnum.getByTemplateId(templateId);
            mailRequest.setTemplateName(templateEnum.getTemplateName());//管理员账号新注册待激活
            mailRequest.setSubject(templateEnum.getSubject());
            String url = mailManage.createLink(permissionShowVo.getEmail(), templateEnum.getTemplateId());
            mailRequest.setUrl(url);
            mailManage.sendByTemplate(mailRequest);
        }catch (Exception e){
            log.info("sendEmail had exception:"+e.getMessage());
            return false;
        }
         return true;
    }

    /**
     * 设置已存在用户角色信息
     * @param permissionShowVo
     * @param rolePermissions
     * @param userNo
     * @param roleNumber
     */
    private void setExitsUserRolePermission(PermissionShowVo permissionShowVo, List<RolePermission> rolePermissions, String userNo, String roleNumber,Boolean flag) {
        if(!flag){
            UserMercBinding userMercBinding=UserMercBinding.builder().userNo(userNo).userType(Byte.valueOf("1")).status(Byte.valueOf("1")).busCnl("1").mercId(permissionShowVo.getMerchantId()).creator(permissionShowVo.getUserNo()).modifier(permissionShowVo.getUserNo()).build();
            permissionService.insertUserMerchantBinding(userMercBinding);
        }
        //用户角色信息入库
        Role role=Role.builder().name(userNo).roleNumber(roleNumber).type(AdjustedTypeEnum.downgrade.getType()).description("操作员-角色").build();
        permissionService.insertRole(role);
        //查询用户角色信息
        UserBind userBind=UserBind.builder().roleNumber(roleNumber).build();
        Role roleData= permissionService.selectRoleBuUserVo(userBind);
        UserRole userRole=UserRole.builder().userNo(userNo).roleId(roleData.getId()).mercId(permissionShowVo.getMerchantId()).build();
        permissionService.insertUserRole(userRole);
        String  permission[]=permissionShowVo.getPermitStr().split(",");
        permissionService.setUserRolePermission(rolePermissions, roleData, permission);
        AssertHelperUtil.isNull(rolePermissions,SystemEnum.USER_PERMISSION_IS_ERROR.getCode(),SystemEnum.USER_PERMISSION_IS_ERROR.getDesc());
        permissionService.insertBatch(rolePermissions);
    }
    /**
     * save user  role
     * @param permissionShowVo
     * @param rolePermissions
     * @param userNo
     * @param roleNumber
     */
    private void saveRole(PermissionShowVo permissionShowVo, List<RolePermission> rolePermissions, String userNo, String roleNumber) {
        UserBind user=UserBind.builder().userNo(userNo).email(permissionShowVo.getEmail()).creator(permissionShowVo.getUserNo()).modifier(permissionShowVo.getUserNo()).build();
        permissionService.insertUser(user);//用户库
        UserLoginAuths userLoginAuths=UserLoginAuths.builder().userNo(userNo).identifier(permissionShowVo.getEmail()).creator(permissionShowVo.getUserNo()).modifier(permissionShowVo.getUserNo()).credential("").build();
        permissionService.insertUserLoginAuths(userLoginAuths);//登录账号
        setExitsUserRolePermission(permissionShowVo, rolePermissions, userNo, roleNumber,false);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public BizResult edit(PermissionShowVo permissionShowVo) throws Exception {
        String  roleNumber=GenerateNoUtil.randomStr("ROLE_");
        List<RolePermission> rolePermissions=new ArrayList<RolePermission>();
        UserVo userVo=UserVo.builder().userNo(permissionShowVo.getUserNo()).build();
        try {
            List<Role> roles=permissionService.selectRoleByUserNo(userVo);
            deleteUserRolePermission(permissionShowVo, roles);
            // 用户角色信息
            Role role=Role.builder().name(permissionShowVo.getUserNo()).roleNumber(roleNumber).type(AdjustedTypeEnum.downgrade.getType()).description("操作员-角色").build();
            permissionService.insertRole(role);
            //查询用户角色信息
            UserBind userBind=UserBind.builder().roleNumber(roleNumber).build();
            Role roleData= permissionService.selectRoleBuUserVo(userBind);
            UserRole userRole=UserRole.builder().userNo(permissionShowVo.getUserNo()).roleId(roleData.getId()).mercId(permissionShowVo.getMerchantId()).build();
            permissionService.insertUserRole(userRole);
            String  permission[]=permissionShowVo.getPermitStr().split(",");
            permissionService.setUserRolePermission(rolePermissions, roleData, permission);
            AssertHelperUtil.isNull(rolePermissions,SystemEnum.USER_PERMISSION_IS_ERROR.getCode(),SystemEnum.USER_PERMISSION_IS_ERROR.getDesc());
            permissionService.insertBatch(rolePermissions);
            rolePermissions.clear();
            return BizResult.create(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),null);
        }catch (Exception e){
            log.info("edit user permission had exception:"+e.getMessage());
            throw new Exception(e);
        }
    }
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public BizResult delete(PermissionShowVo permissionShowVo) throws Exception {
        try {
            //解绑商户
//            permissionService.updateUserMerchantBindingByUserNo(UserBind.builder().userNo(permissionShowVo.getUserNo()).merChantId(permissionShowVo.getMerchantId()).isDeleted(Byte.valueOf("1")).status("2").build());
            permissionService.deleteUserMerchantBindingByUserNo(UserBind.builder().userNo(permissionShowVo.getUserNo()).merChantId(permissionShowVo.getMerchantId()).build());
            UserVo userVo=UserVo.builder().userNo(permissionShowVo.getUserNo()).build();
            List<Role> roles=permissionService.selectRoleByUserNo(userVo);
            //解绑用户权限
            deleteUserRolePermission(permissionShowVo, roles);
            return BizResult.create(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),null);
        }catch (Exception e){
            log.info("delete  user permission had exception:"+e.getMessage());
            throw new Exception(e);
        }
    }
    /**
     * @desc 解绑用户权限
     * @param permissionShowVo
     * @param roles
     */
    private void deleteUserRolePermission(PermissionShowVo permissionShowVo, List<Role> roles) {
        if(roles!=null&&!roles.isEmpty()){
            //清空操作员权限
            UserBind userBind=UserBind.builder().roleId("1").userNo(permissionShowVo.getUserNo()).merChantId(permissionShowVo.getMerchantId()).build();
            permissionService.deleteUserRoleByRoleId(userBind);
            for(Role role:roles){
                // 清空角色
                permissionService.deleteRoleByPrimaryKeySelective(role);
                UserRole userRole=UserRole.builder().roleId(role.getId()).build();
                // 清空用户角色信息
                UserBind usRole=UserBind.builder().roleId(role.getId()+"").userNo(permissionShowVo.getUserNo()).merChantId(permissionShowVo.getMerchantId()).build();
                permissionService.deleteUserRoleByRoleId(usRole);
                // 清空角色权限表
                if(AdjustedTypeEnum.upgrade.getType()==role.getId()||AdjustedTypeEnum.downgrade.getType()==role.getId()){
                    continue;
                }else {
                    UserBind rolePermission=UserBind.builder().roleId(role.getId()+"").userNo(permissionShowVo.getUserNo()).merChantId(permissionShowVo.getMerchantId()).build();
                    permissionService.deleteRolePermissionByPrimaryKeySelective(rolePermission);
                }
            }
        }
    }
}
