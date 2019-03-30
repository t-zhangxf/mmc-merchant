package com.merchant.web.client.secutity;
import com.merchant.web.common.enums.UserStatusEnums;
import com.merchant.web.entity.User;
import com.merchant.web.service.UserService;
import com.merchant.web.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    UserHelper userHelper;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        log.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        ByteSource credentialsSalt = ByteSource.Util.bytes(name);//根据用户名加盐
        String pwd = userHelper.getUserPassWord(credentialsSalt,password);
        UserVo userVo=UserVo.builder().identifier(name).identityType("0").build();
        log.info("userVo:"+userVo.toString());
        User user= userService.selectUseInfoByCondition(userVo);
        if(user==null){
            throw new UnknownAccountException();
        }else{
            log.info("pwd:"+pwd);
            userVo.setCredential(pwd);
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getCredential(),credentialsSalt,getName());
            return authenticationInfo;
        }
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }


}
