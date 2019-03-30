package com.pay.merchant.manage;
import com.pay.merchant.entity.User;
import com.pay.merchant.req.CreateAddBindingRequest;
import com.pay.merchant.req.GetUserInfoRequest;
import com.pay.merchant.req.SetUserTypeAndPermissionRequest;
import com.pay.merchant.req.UnbundingRequest;
import com.pay.merchant.rsp.CreateAndBindingResponse;
import com.pay.merchant.rsp.GetUserInfoResponse;

public interface UserManager {

    /**
     * OMC创建账号，关联商户，通知用户
     * @param request
     * @return
     */
    CreateAndBindingResponse createAndBinding(CreateAddBindingRequest request) throws Exception;

    /**
     * omc查询账号信息
     * @param request
     * @return
     */
    GetUserInfoResponse getUserInfo (GetUserInfoRequest request);

    /**
     * OMC解绑商户
     * @param request
     * @return
     */
    boolean unbunding(UnbundingRequest request);

    /**
     * OMC权限设置
     * @param request
     */
    void setUserTypeAndPermission(SetUserTypeAndPermissionRequest request);

}
