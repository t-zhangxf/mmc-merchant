package com.pay.merchant.mapper;

import com.pay.merchant.entity.PermissionShow;
import com.pay.merchant.entity.PermissionShowExample;
import java.util.List;

import com.pay.merchant.repository.MyDbRepository;
import org.apache.ibatis.annotations.Param;
@MyDbRepository
public interface PermissionShowMapper {
    int countByExample(PermissionShowExample example);
    int deleteByExample(PermissionShowExample example);
    int deleteByPrimaryKey(Integer id);
    int insert(PermissionShow record);
    int insertSelective(PermissionShow record);
    List<PermissionShow> selectByExampleWithBLOBs(PermissionShowExample example);
    List<PermissionShow> selectByExample(PermissionShowExample example);
    PermissionShow selectByPrimaryKey(Integer id);
    int updateByExampleSelective(@Param("record") PermissionShow record, @Param("example") PermissionShowExample example);
    int updateByExampleWithBLOBs(@Param("record") PermissionShow record, @Param("example") PermissionShowExample example);
    int updateByExample(@Param("record") PermissionShow record, @Param("example") PermissionShowExample example);
    int updateByPrimaryKeySelective(PermissionShow record);
    int updateByPrimaryKeyWithBLOBs(PermissionShow record);
    int updateByPrimaryKey(PermissionShow record);
    PermissionShow selectPermissionShowByCondition(PermissionShow record);
}