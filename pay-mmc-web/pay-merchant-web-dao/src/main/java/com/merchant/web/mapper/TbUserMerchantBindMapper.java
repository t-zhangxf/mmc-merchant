package com.merchant.web.mapper;
import com.merchant.web.entity.TbUserMerchantBind;
import com.merchant.web.repository.MyDbRepository;

@MyDbRepository
public interface TbUserMerchantBindMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(TbUserMerchantBind record);
    int insertSelective(TbUserMerchantBind record);
    TbUserMerchantBind selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(TbUserMerchantBind record);
    int updateByPrimaryKey(TbUserMerchantBind record);
    TbUserMerchantBind  selectUserMerchantBindByCondition(TbUserMerchantBind record);
}