package com.merchant.web.mapper;
import com.merchant.web.common.entity.vo.PermissionShowVo;
import com.merchant.web.entity.TbPermissionShow;
import com.merchant.web.repository.MyDbRepository;

import java.util.List;

@MyDbRepository
public interface TbPermissionShowMapper {
   List<TbPermissionShow> selectPermissionShows(PermissionShowVo permissionShowVo);
}