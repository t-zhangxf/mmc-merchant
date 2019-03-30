package com.pay.merchant.mapper;

import com.pay.merchant.entity.UserMercBinding;
import com.pay.merchant.entity.UserMercBindingExample;
import java.util.List;

import com.pay.merchant.repository.MyDbRepository;
import org.apache.ibatis.annotations.Param;
@MyDbRepository
public interface UserMercBindingMapper {
    int countByExample(UserMercBindingExample example);

    int deleteByExample(UserMercBindingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserMercBinding record);

    int insertSelective(UserMercBinding record);

    List<UserMercBinding> selectByExample(UserMercBindingExample example);

    UserMercBinding selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserMercBinding record, @Param("example") UserMercBindingExample example);

    int updateByExample(@Param("record") UserMercBinding record, @Param("example") UserMercBindingExample example);

    int updateByPrimaryKeySelective(UserMercBinding record);

    int updateByPrimaryKey(UserMercBinding record);
}