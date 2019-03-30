package com.merchant.web.mapper;

import com.merchant.web.entity.TbLoginLog;
import com.merchant.web.repository.MyDbRepository;

@MyDbRepository
public interface TbLoginLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbLoginLog record);

    int insertSelective(TbLoginLog record);

    TbLoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbLoginLog record);

    int updateByPrimaryKeyWithBLOBs(TbLoginLog record);

    int updateByPrimaryKey(TbLoginLog record);
}