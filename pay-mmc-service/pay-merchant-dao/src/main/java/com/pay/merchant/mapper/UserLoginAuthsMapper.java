package com.pay.merchant.mapper;

import com.pay.merchant.entity.UserLoginAuths;
import com.pay.merchant.entity.UserLoginAuthsExample;
import java.util.List;

import com.pay.merchant.repository.MyDbRepository;
import org.apache.ibatis.annotations.Param;
@MyDbRepository
public interface UserLoginAuthsMapper {
    int countByExample(UserLoginAuthsExample example);

    int deleteByExample(UserLoginAuthsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginAuths record);

    int insertSelective(UserLoginAuths record);

    List<UserLoginAuths> selectByExample(UserLoginAuthsExample example);

    UserLoginAuths selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserLoginAuths record, @Param("example") UserLoginAuthsExample example);

    int updateByExample(@Param("record") UserLoginAuths record, @Param("example") UserLoginAuthsExample example);

    int updateByPrimaryKeySelective(UserLoginAuths record);

    int updateByPrimaryKey(UserLoginAuths record);
}