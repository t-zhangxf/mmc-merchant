package com.merchant.web.mapper;

import com.merchant.web.Do.UserPermissionDo;
import com.merchant.web.common.entity.vo.PermissionVo;
import com.merchant.web.entity.TbUserPermission;
import com.merchant.web.repository.MyDbRepository;
import com.merchant.web.vo.UserPermissionVo;

import java.util.List;

@MyDbRepository
public interface TbUserPermissionMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(TbUserPermission record);
    int insertSelective(TbUserPermission record);
    TbUserPermission selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(TbUserPermission record);
    int updateByPrimaryKey(TbUserPermission record);
    List<UserPermissionDo> findUserPermissionByUser(UserPermissionVo userPermissionVo); //查询普通操作员权限
    List<UserPermissionDo> findAdminPermissionByUser(UserPermissionVo userPermissionVo);//管理员权限
    List<TbUserPermission> findPermissionByCondition(PermissionVo permissionVo);
    List<UserPermissionDo> findUserPermissionInfoByUser(UserPermissionVo userPermissionVo);
}