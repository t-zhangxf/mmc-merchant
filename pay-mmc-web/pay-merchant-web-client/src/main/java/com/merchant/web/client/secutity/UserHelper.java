package com.merchant.web.client.secutity;

import com.github.pagehelper.StringUtil;
import com.merchant.web.client.exception.CustomException;
import com.merchant.web.common.enums.SystemEnum;
import com.merchant.web.entity.User;
import com.merchant.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @desc 获取当前用户
 */
@Slf4j
@Component
public class UserHelper {
    @Value("${x-token}")
    private  String xToken;
    @Value("${shiro.redis.session}")
    private  String redisSession;
    @Value("${principals.session.key}")
    private  String sessionKey;
    @Value("${plat.redis.expire.time}")
    private  Integer expireTime;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * @desc get user subject
     * @return
     */
    public  Subject getSubject() throws UnavailableSecurityManagerException{
            SecurityUtils.getSubject().getSession().setTimeout(-1000l);
            return SecurityUtils.getSubject();
    }
   public void loginOut(HttpServletRequest httpServletRequest,String xToken){
       httpServletRequest.getSession().invalidate();
       redisTemplate.delete(redisSession+xToken);
   }


    public String getUserPassWord(ByteSource credentialsSalt,String password){
        return new SimpleHash("MD5", password,credentialsSalt, 1024).toString();
    }

    /**
     * desc  encryptPassword
     * @param userName
     * @param password
     * @return
     */
   public String encryptPassword(String userName,String password){
        ByteSource credentialsSalt = ByteSource.Util.bytes(userName);//根据用户名加盐
        return getUserPassWord(credentialsSalt,password);
   }

    /**
     *
     * get currentMerchantId
     * @param httpServletRequest
     * @return
     */
   public String getCurrentMerchantId(HttpServletRequest httpServletRequest){
       String merchantId = WebUtils.toHttp(httpServletRequest).getHeader("merchantId");
       if(StringUtil.isEmpty(merchantId)){
           merchantId=WebUtils.toHttp(httpServletRequest).getParameter("merchantId");
       }
       return merchantId;
   }

    public User getCurrentUser(HttpServletRequest httpServletRequest) {
          String headerSessionId = WebUtils.toHttp(httpServletRequest).getHeader(xToken);
          if(StringUtil.isEmpty(headerSessionId)){
              headerSessionId=WebUtils.toHttp(httpServletRequest).getParameter(xToken);;
          }
         if(!StringUtil.isEmpty(headerSessionId)){
            log.info("sessionId:"+headerSessionId);
            String tokenKey=redisSession+headerSessionId;
            SimpleSession simpleSession=(SimpleSession)redisTemplate.opsForValue().get(tokenKey);
            if(simpleSession!=null){
                Map<Object, Object> map=(Map<Object, Object>)	simpleSession.getAttributes();
                if(!map.isEmpty()){
                    HttpSession  session= httpServletRequest.getSession(true);
                    SimplePrincipalCollection simplePrincipalCollection =(SimplePrincipalCollection) map.get(sessionKey);
                    return (User)  simplePrincipalCollection.getPrimaryPrincipal();
                }
            }
        }
        return null;
    }


    /**
     * @desc options cors
     * @param request
     * @param response
     */
    public  void setHeader(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
//      response.setHeader("Access-Control-Allow-Headers", "Accept-Encoding, Accept-Language, Accept-Params,Content-Transfer-Encoding, Content-Type, X-Token, Content-Version, If-Match, If-Modified-Since, If-None-Match, If-Range, If-Unmodified-Since, Keep-Alive, X-Content-Duration, X-Content-Security-Policy, X-Content-Type-Options, X-CustomHeader, X-DNSPrefetch-Control, X-Forwarded-For, X-Forwarded-Port, X-Forwarded-Proto, X-Frame-Options, X-Modified, X-OTHER, X-PING, X-PINGOTHER, X-Powered-By, X-Requested-With，merchantId，merchantid,token,language");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Expose-Headers", "*");
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }
    public  String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
