package com.pay.merchant.mapper;

import com.pay.merchant.entity.SmsValidate;
import com.pay.merchant.entity.SmsValidateExample;
import java.util.List;

import com.pay.merchant.repository.MyDbRepository;
import org.apache.ibatis.annotations.Param;
@MyDbRepository
public interface SmsValidateMapper {
    int countByExample(SmsValidateExample example);

    int deleteByExample(SmsValidateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsValidate record);

    int insertSelective(SmsValidate record);

    List<SmsValidate> selectByExample(SmsValidateExample example);

    SmsValidate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SmsValidate record, @Param("example") SmsValidateExample example);

    int updateByExample(@Param("record") SmsValidate record, @Param("example") SmsValidateExample example);

    int updateByPrimaryKeySelective(SmsValidate record);

    int updateByPrimaryKey(SmsValidate record);
}