package com.pay.merchant.service.impl;
import com.pay.common.entity.vo.UserVo;
import com.pay.merchant.entity.*;
import com.pay.merchant.mapper.*;
import com.pay.merchant.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserMercBindingMapper userMercBindingMapper;
    @Autowired
    UserLoginAuthsMapper userLoginAuthsMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    PermissionShowMapper permissionShowMapper;

    @Override
    public void insertUserMerchantBinding(UserMercBinding userMercBinding) {
        userMercBindingMapper.insertSelective(userMercBinding);////用户商户绑定库
    }
    @Override
    public void insertRole(Role role) {
        roleMapper.insertSelective(role);
    }
    @Override
    public Role selectRoleBuUserVo(UserBind userBind) {
     return  userMapper.selectRoleBuUserVo(userBind);
    }
    @Override
    public void insertBatch(List<RolePermission> rolePermissions) {
        rolePermissionMapper.insertBatch(rolePermissions);
    }

    @Override
    public List<UserBind> findUserBindInfoByUserMerchant(UserBind userBindVo) {
        return userMapper.findUserBindInfoByUserMerchant(userBindVo);
    }

    @Override
    public void insertUserRole(UserRole userRole) {
        userRoleMapper.insertSelective(userRole);
    }

    @Override
    public void insertUser(UserBind user) {
        userMapper.insertUser(user);//用户库
    }

    @Override
    public void insertUserLoginAuths(UserLoginAuths userLoginAuths) {
        userLoginAuthsMapper.insertSelective(userLoginAuths);//登录账号
    }

    @Override
    public List<User> selectUseInfoByCondition(UserVo userVo) {
        return userMapper.selectUseInfoByCondition(userVo);
    }

    @Override
    public void deleteRoleByPrimaryKeySelective(Role role) {
        roleMapper.deleteByPrimaryKey(role.getId());
    }

    @Override
    public void deleteUserRoleByRoleId(UserBind userBind) {
        userMapper.deleteUserRoleByRoleId(userBind);
    }

    @Override
    public void updateUserMerchantBindingByUserNo(UserBind userBind) {
        userMapper.updateUserMerchantBindingByUserNo(userBind);
    }

    @Override
    public void deleteUserMerchantBindingByUserNo(UserBind userBind) {
        userMapper.deleteUserMerchantBindingByUserNo(userBind);
    }

    @Override
    public UserMercBinding selectUserMerchantBindingByUserNo(UserBind userBind) {
        return userMapper.selectUserMerchantBindingByUserNo(userBind);
    }

    public void setUserRolePermission(List<RolePermission> rolePermissions, Role roleData, String[] permission) {
        Map<String,Integer> permissionMap=new HashMap<String,Integer>();
        if(permission!=null&&permission.length!=0){
            for(String str:permission){
                PermissionShow permissionShow=permissionShowMapper.selectPermissionShowByCondition(PermissionShow.builder().psNumber(str).build());
                if(permissionShow!=null){
                    for(String perm:permissionShow.getFatherIds().split(",")){//father
                        if(StringUtils.isNotEmpty(perm)){// isShow 是否展示 0:不展示 1:展示
                            RolePermission rolePermission=RolePermission.builder().roleId(roleData.getId()).permissionId(Integer.valueOf(perm)).isShow(0).build();
                            setUserPermission(roleData, permissionMap, permissionShow, perm, rolePermission);
                        }
                    }
                    for (String perm:permissionShow.getChildIds().split(",")){//child
                        if(StringUtils.isNotEmpty(perm)) {
                            RolePermission rolePermission = RolePermission.builder().roleId(roleData.getId()).permissionId(Integer.valueOf(perm)).isShow(0).build();
                            setUserPermission(roleData, permissionMap, permissionShow, perm, rolePermission);
                        }
                    }
                }
            }
        }
        if(permissionMap!=null&&!permissionMap.isEmpty()){
            permissionMap.forEach((k, v) -> {
                RolePermission rolePermission = RolePermission.builder().roleId(roleData.getId()).permissionId(Integer.valueOf(k.split(",")[1])).isShow(v).build();
                rolePermissions.add(rolePermission);
            });
        }
        permissionMap.clear();
    }
    /**
     * @desc  设置用户用户权限
     * @param roleData
     * @param permissionMap
     * @param permissionShow
     * @param perm
     * @param rolePermission
     */
    private void setUserPermission(Role roleData, Map<String, Integer> permissionMap, PermissionShow permissionShow, String perm, RolePermission rolePermission) {
        if (!permissionMap.containsKey(roleData.getId() + "," + perm)) {
            if (permissionShow.getPsId().equals(perm)) {
                rolePermission.setIsShow(1);//显示
            }
            permissionMap.put(roleData.getId() + "," + perm, rolePermission.getIsShow());
        } else if (permissionMap.containsKey(roleData.getId() + "," + perm) && permissionShow.getPsId().equals(perm)) {
            rolePermission.setIsShow(1);//显示
            permissionMap.put(roleData.getId() + "," + perm, rolePermission.getIsShow());
        }
    }

    @Override
    public List<Role> selectRoleByUserNo(UserVo userVo) {
        return userMapper.selectRoleByUserNo(userVo);
    }

    @Override
    public void deleteUserRoleByPrimaryKeySelective(UserRole userRole) {
            userRoleMapper.deleteByPrimaryKey(userRole.getId());
    }
    @Override
    public void deleteRolePermissionByPrimaryKeySelective(UserBind userBind) {
        userMapper.deleteRolePermissionByRoleId(userBind);
    }
}
