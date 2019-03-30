package com.pay.merchant.mapper;

import com.pay.merchant.entity.Users;
import com.pay.merchant.entity.UsersExample;
import java.util.List;

import com.pay.merchant.repository.MyDbRepository;
import org.apache.ibatis.annotations.Param;
@MyDbRepository
public interface UsersMapper {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}