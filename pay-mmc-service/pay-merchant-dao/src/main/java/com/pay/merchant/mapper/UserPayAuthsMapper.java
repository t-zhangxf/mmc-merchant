package com.pay.merchant.mapper;

import com.pay.merchant.entity.UserPayAuths;
import com.pay.merchant.entity.UserPayAuthsExample;
import java.util.List;

import com.pay.merchant.repository.MyDbRepository;
import org.apache.ibatis.annotations.Param;
@MyDbRepository
public interface UserPayAuthsMapper {
    int countByExample(UserPayAuthsExample example);

    int deleteByExample(UserPayAuthsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserPayAuths record);

    int insertSelective(UserPayAuths record);

    List<UserPayAuths> selectByExample(UserPayAuthsExample example);

    UserPayAuths selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserPayAuths record, @Param("example") UserPayAuthsExample example);

    int updateByExample(@Param("record") UserPayAuths record, @Param("example") UserPayAuthsExample example);

    int updateByPrimaryKeySelective(UserPayAuths record);

    int updateByPrimaryKey(UserPayAuths record);
}