package com.merchant.web.mapper;
import com.merchant.web.entity.TbUserLoginAuths;
import com.merchant.web.repository.MyDbRepository;
@MyDbRepository
public interface TbUserLoginAuthsMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(TbUserLoginAuths record);
    int insertSelective(TbUserLoginAuths record);
    TbUserLoginAuths selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(TbUserLoginAuths record);
    int updateByPrimaryKey(TbUserLoginAuths record);
}