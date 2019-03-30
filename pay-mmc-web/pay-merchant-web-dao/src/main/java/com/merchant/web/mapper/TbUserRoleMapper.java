package com.merchant.web.mapper;

import com.merchant.web.Do.UserRoleDo;
import com.merchant.web.repository.MyDbRepository;
import com.merchant.web.entity.TbUserRole;
import com.merchant.web.vo.UserRoleVo;

import java.util.List;

@MyDbRepository
public interface TbUserRoleMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(TbUserRole record);
    int insertSelective(TbUserRole record);
    TbUserRole selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(TbUserRole record);
    int updateByPrimaryKey(TbUserRole record);
    List<UserRoleDo> selectUserRoleByUser(UserRoleVo userRoleVo);
}