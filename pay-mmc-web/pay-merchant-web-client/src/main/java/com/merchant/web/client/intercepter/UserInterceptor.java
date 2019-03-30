package com.merchant.web.client.intercepter;

import com.github.pagehelper.StringUtil;
import com.merchant.web.client.exception.CustomException;
import com.merchant.web.client.secutity.UserHelper;
import com.merchant.web.common.enums.MerchantEnums;
import com.merchant.web.common.enums.SystemEnum;
import com.merchant.web.common.enums.UserEnums;
import com.merchant.web.common.utils.AssertHelperUtil;
import com.merchant.web.common.utils.StringUtils;
import com.merchant.web.entity.TbLoginLog;
import com.merchant.web.entity.User;
import com.merchant.web.mapper.UserMapper;
import com.merchant.web.service.UserService;
import com.merchant.web.vo.UserPermissionVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    UserHelper userHelper;
    @Value("${server.context-path}")
    private  String contextPath;
    @Value("${errorUrl}")
    private String errorUrl;
    @Value("${modifyUrl}")
    private String modifyUrl;
    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url=request.getRequestURI().replaceAll(contextPath,"");
        if(errorUrl.equals(url)){
            throw new CustomException(SystemEnum.SYSTEM_REQUEST_URL_BAD.getCode(), SystemEnum.SYSTEM_REQUEST_URL_BAD.getDesc());
        }
        userHelper.setHeader(request, response);//set request header
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            log.info("session:" + "browser options had request");
            return true;
        }
        User user= userHelper.getCurrentUser(request);
        AssertHelperUtil.notNull(user,UserEnums.USER_REDIRECT_PAGE.getCode(),UserEnums.USER_REDIRECT_PAGE.getDesc());
        String ip=userHelper.getRemoteIP(request);
        TbLoginLog tbLoginLog= TbLoginLog.builder().requestUrl(url).userNo(user.getUserNo()).currentLoginIp(ip).build();
        userService.saveLoginLog(tbLoginLog);
        if(modifyUrl.equals(url)){
            return true;
        }
        String merchantId=userHelper.getCurrentMerchantId(request);
        AssertHelperUtil.hasText(merchantId,UserEnums.USER_MERCHANT_IS_NULL.getCode(),UserEnums.USER_MERCHANT_IS_NULL.getDesc());
        if(userService.checkUserPermission(UserPermissionVo.builder().userNo(user.getUserNo()).url(url).merchantId(merchantId).isNotMerchantId("ALL").userStatus(0).build())){
            return true;
        }else{
            throw new CustomException(UserEnums.USER_HAS_NO_PERMISSION.getCode(), UserEnums.USER_HAS_NO_PERMISSION.getDesc());
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
