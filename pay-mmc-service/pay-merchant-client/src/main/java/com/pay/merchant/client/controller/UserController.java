package com.pay.merchant.client.controller;
import com.alibaba.fastjson.JSON;
import com.pay.common.entity.result.BizResult;
import com.pay.common.enums.SystemEnum;
import com.pay.merchant.manage.MailManage;
import com.pay.merchant.manage.UserManager;
import com.pay.merchant.req.CreateAddBindingRequest;
import com.pay.merchant.req.*;
import com.pay.merchant.rsp.CreateAndBindingResponse;
import com.pay.merchant.rsp.GetUserInfoResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(description = "【用户服务】")
public class UserController {

    @Autowired
    UserManager userManager;

    @Autowired
    MailManage mailManage;


    /**
     * Create User And Binding Merchant
     *
     * @param request
     * @return
     */
    @RequestMapping("/createAndBinding")
    public BizResult<CreateAndBindingResponse> createAndBinding(@RequestBody CreateAddBindingRequest request) throws Exception {
        return BizResult.success(userManager.createAndBinding(request));
    }

    /**
     * Unbunding User And Binding Merchant
     *
     * @param request
     * @return
     */
    @RequestMapping("/unbunding")
    public BizResult<Boolean> unbunding(@RequestBody UnbundingRequest request) {
        return BizResult.success(userManager.unbunding(request));
    }

    /**
     * Resend Mail
     *
     * @param request
     * @return
     */
    @RequestMapping("/resendMail")
    public BizResult resendMail(@RequestBody ResendMailRequest request) {
        Boolean isSuccess=mailManage.resendMail(request);
        if(isSuccess){
            return BizResult.success(isSuccess);
        }
        return BizResult.error(SystemEnum.SYSTEM_ERROR.getCode(),SystemEnum.SYSTEM_ERROR.getDesc());
    }

    /**
     * Get User Info
     *
     * @param request
     * @return
     */
    @RequestMapping("/getUserInfo")
    public BizResult<GetUserInfoResponse> getUserInfo(@RequestBody GetUserInfoRequest request) {
        return BizResult.success(userManager.getUserInfo(request));
    }

    /**
     * Set UserType And Permission
     *
     * @param request
     * @return
     */
    @RequestMapping("/setUserTypeAndPermission")
    public BizResult setUserTypeAndPermission(@RequestBody SetUserTypeAndPermissionRequest request) {
        userManager.setUserTypeAndPermission(request);
        return BizResult.success("");
    }

}
