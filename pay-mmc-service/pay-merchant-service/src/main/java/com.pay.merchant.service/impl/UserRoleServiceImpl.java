package com.pay.merchant.service.impl;

import com.pay.common.enums.SystemEnum;
import com.pay.common.utils.AssertHelperUtil;
import com.pay.merchant.entity.UserRole;
import com.pay.merchant.entity.UserRoleExample;
import com.pay.merchant.mapper.UserRoleMapper;
import com.pay.merchant.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    /**
     * 新增用户角色绑定信息
     * @param userRole
     * @return
     */
    @Override
    public Integer saveUserRole(UserRole userRole) {
        AssertHelperUtil.notNull(userRole, SystemEnum.VALIDATE_ERROR.getCode(),
                "UserRole Record Cannot be empty");
        return userRoleMapper.insertSelective(userRole);
    }

    /**
     * 删除用户角色绑定信息
     * @param mercId
     * @param userNo
     * @return
     */
    @Override
    public Integer deleteUserRole(String mercId, String userNo) {
        AssertHelperUtil.notNull(mercId, SystemEnum.VALIDATE_ERROR.getCode(),
                "mercId Cannot be empty");
        AssertHelperUtil.notNull(userNo, SystemEnum.VALIDATE_ERROR.getCode(),
                "userNo Cannot be empty");
        UserRoleExample e=new UserRoleExample();
        e.createCriteria().andMercIdEqualTo(mercId).andUserNoEqualTo(userNo).andIsDeletedEqualTo(0);
        return userRoleMapper.deleteByExample(e);
    }

    /**
     * 修改用户商户角色
     * @param mercId
     * @param userNo
     * @param roleId
     * @return
     */
    @Override
    public Integer modifyUserRole(String mercId, String userNo, Integer roleId) {
        AssertHelperUtil.notNull(mercId, SystemEnum.VALIDATE_ERROR.getCode(),
                "mercId Cannot be empty");
        AssertHelperUtil.notNull(userNo, SystemEnum.VALIDATE_ERROR.getCode(),
                "mercId Cannot be empty");
        AssertHelperUtil.notNull(userNo, SystemEnum.VALIDATE_ERROR.getCode(),
                "roleId Cannot be empty");
        UserRoleExample e=new UserRoleExample();
        e.createCriteria().andMercIdEqualTo(mercId).andUserNoEqualTo(userNo).andIsDeletedEqualTo(0);
        UserRole userRole=new UserRole();
        userRole.setRoleId(roleId);
        return userRoleMapper.updateByExampleSelective(userRole,e);
    }
}
