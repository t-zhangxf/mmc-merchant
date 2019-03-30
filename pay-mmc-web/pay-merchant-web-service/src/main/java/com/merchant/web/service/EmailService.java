package com.merchant.web.service;

import com.merchant.web.common.entity.result.Result;
import com.merchant.web.common.entity.result.ResultVo;
import com.merchant.web.common.entity.vo.EmailVo;
import com.merchant.web.common.entity.vo.PermissionEmailVo;
import com.merchant.web.entity.User;

public interface EmailService {
   Boolean sendEmail(EmailVo emailVo);
   ResultVo sendEmailPwd(EmailVo emailVo) throws Exception;
   Result emailValidToken(EmailVo emailVo)throws Exception;
   Boolean  checkEmailToken(EmailVo emailVo);
   ResultVo notify(PermissionEmailVo permissionEmailVo,User user) throws Exception;
}
