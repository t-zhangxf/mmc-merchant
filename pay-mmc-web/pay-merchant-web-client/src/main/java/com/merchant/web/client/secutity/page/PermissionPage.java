package com.merchant.web.client.secutity.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.merchant.web.Do.UserRolePermissionDo;
import com.merchant.web.common.entity.result.BizResult;
import com.merchant.web.common.entity.vo.UserRolePermissionVo;
import com.merchant.web.common.enums.MerchantEnums;
import com.merchant.web.common.enums.PageEnums;
import com.merchant.web.common.enums.SystemEnum;
import com.merchant.web.common.utils.AssertHelperUtil;
import com.merchant.web.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Slf4j
@Component
public class PermissionPage {
    @Autowired
    private UserMapper userMapper;

    public BizResult findPermissionListByMerchantNo(UserRolePermissionVo userPermissionVo){
        AssertHelperUtil.hasText(userPermissionVo.getMerchantId(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.hasText(userPermissionVo.getPageSize(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        AssertHelperUtil.hasText(userPermissionVo.getPageNum(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),SystemEnum.SYSTEM_PARAMETER_NULL.getDesc());
        PageHelper.startPage(Integer.parseInt(userPermissionVo.getPageNum()), Integer.parseInt(userPermissionVo.getPageSize()));
        List<UserRolePermissionDo> customerPermissions=userMapper.findCustomerPermissionListByMerchantNo(userPermissionVo);
        PageInfo<UserRolePermissionDo> pageInfo=new PageInfo<UserRolePermissionDo>(customerPermissions);
        List<UserRolePermissionDo> permissions=pageInfo.getList();
        if(permissions!=null&&!permissions.isEmpty()){
            for(UserRolePermissionDo userRolePermissionDo:permissions){
                userRolePermissionDo.setCreateTime(userRolePermissionDo.getStartDate().getTime()+"");
            }
        }
        return BizResult.buildBizResult(userPermissionVo.getPageNum(),userPermissionVo.getPageSize(),pageInfo.getTotal()+"",SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),permissions);
    }

}
