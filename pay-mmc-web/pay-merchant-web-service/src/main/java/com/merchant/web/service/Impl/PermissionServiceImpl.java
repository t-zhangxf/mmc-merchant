package com.merchant.web.service.Impl;

import com.merchant.web.common.entity.result.Result;
import com.merchant.web.common.entity.vo.PermissionVo;
import com.merchant.web.common.enums.MerchantEnums;
import com.merchant.web.common.enums.SystemEnum;
import com.merchant.web.common.utils.AssertHelperUtil;
import com.merchant.web.entity.TbUserPermission;
import com.merchant.web.mapper.TbUserPermissionMapper;
import com.merchant.web.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    TbUserPermissionMapper tbUserPermissionMapper;
    @Override
    public Result findAllPermissionList(PermissionVo permissionVo) {
        AssertHelperUtil.hasText(permissionVo.getMerchantId(),MerchantEnums.MERCHANT_ID_NULL.getCode(),MerchantEnums.MERCHANT_ID_NULL.getDesc());
        JSONArray  commonPermission=new JSONArray();
        //todo 0:查询管理类
        permissionVo.setPermissionType(0);
        getPermissionList(permissionVo,commonPermission);
        //todo 1:审核类
        permissionVo.setPermissionType(1);
        getPermissionList(permissionVo,commonPermission);
        //todo 2:审核类
        permissionVo.setPermissionType(2);
        getPermissionList(permissionVo,commonPermission);
        return Result.buildResult(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),commonPermission);
    }
    public void getPermissionList(PermissionVo permissionVo ,JSONArray  commonPermission){
        permissionVo.setAttrId(1);
        permissionVo.setFatherId(0);
        List<TbUserPermission> managePermissions= tbUserPermissionMapper.findPermissionByCondition(permissionVo);
        if(managePermissions!=null&&!managePermissions.isEmpty()){
            for (TbUserPermission tbUserPermission:managePermissions){
                JSONObject permission=new JSONObject();
                JSONArray  childPermissions=new JSONArray();
                permission.put("category",tbUserPermission.getName());
                permission.put("permitId",tbUserPermission.getPsNumber());
                PermissionVo childPermissionVo=PermissionVo.builder().fatherId(tbUserPermission.getId()).attrId(1).permissionType(permissionVo.getPermissionType()).build();
                List<TbUserPermission> childPerms= tbUserPermissionMapper.findPermissionByCondition(childPermissionVo);
                if(childPerms!=null&&!childPerms.isEmpty()){
                    for(TbUserPermission tbUserPermission1:childPerms){
                        JSONObject childPermission=new JSONObject();
                        childPermission.put("category",tbUserPermission1.getName());
                        childPermission.put("permitId",tbUserPermission1.getPsNumber());
                        childPermissions.add(childPermission);
                    }
                }
                permission.put("data",childPermissions);
                commonPermission.add(permission);
            }
        }
    }
}
