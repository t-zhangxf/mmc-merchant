package com.pay.merchant.manage.support;

import com.alibaba.fastjson.JSON;
import com.pay.common.constants.CacheKeyConstants;
import com.pay.common.entity.vo.UserEmailVo;
import com.pay.common.entity.vo.UserVo;
import com.pay.common.enums.*;
import com.pay.common.entity.result.BizResult;
import com.pay.common.entity.vo.EmailVo;
import com.pay.common.utils.AssertHelperUtil;
import com.pay.common.utils.EmptyUtil;
import com.pay.common.utils.JavaMailUtils;
import com.pay.common.utils.TokenUtil;
import com.pay.merchant.entity.User;
import com.pay.merchant.entity.UserBind;
import com.pay.merchant.entity.UserMercBinding;
import com.pay.merchant.entity.Users;
import com.pay.merchant.integration.resp.MerchantAndMemberDetailResponse;
import com.pay.merchant.integration.service.MerchantFeignService;
import com.pay.merchant.manage.MailManage;
import com.pay.merchant.req.MailByTemplateRequest;
import com.pay.merchant.req.ResendMailRequest;
import com.pay.merchant.rsp.ValidTokenResponse;
import com.pay.merchant.service.MailService;
import com.pay.merchant.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.*;

import static com.pay.common.utils.JavaMailUtils.getInvalidAddress;

@Service("mailManage")
@Slf4j
public class MailManageSupport implements MailManage {
    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender; //自动注入的bean

    @Autowired
    MerchantFeignService merchantFeignService;

    @Value("${activate.account.url}")
    private String activateAccount;

    @Value("${login.page.url}")
    private String loginPage;

    @Value("${login.reset.password.url}")
    private String resetPasswordUrl;//商户平台重设密码url

    @Resource(name = "asyncExecutor")
    private ThreadPoolTaskExecutor asyncExecutor;

    @Override
    public boolean sendByTemplate(MailByTemplateRequest request) throws Exception {
        AssertHelperUtil.notNull(request, SystemEnum.VALIDATE_ERROR.getCode(),
                "Send Mail By Template Request Cannot be empty");
        AssertHelperUtil.notNull(request.getEmail(), SystemEnum.VALIDATE_ERROR.getCode(),
                "Send Mail By Template,Param{Email} Cannot be empty");
        AssertHelperUtil.notNull(request.getTemplateName(), SystemEnum.VALIDATE_ERROR.getCode(),
                "Send Mail By Template,Param TemplateName Cannot be empty");
        AssertHelperUtil.notNull(request.getSubject(), SystemEnum.VALIDATE_ERROR.getCode(),
                "Send Mail By Template,Param Subject Cannot be empty");
        boolean sent_flag = false;
        MimeMessage message = null;
        message = mailSender.createMimeMessage();
        JavaMailUtils.setFromAndTo(message, request.getEmail());
        message.setSubject(request.getSubject());
        String path = mailService.getMailContentByTemplate(request.getUrl(), request.getEmail(), request.getTemplateName(), request.getMercId(), request.getMercNm(), request.getInviter(),request.getDescription());
        log.info(path);
        String readHTML = mailService.readHTML(path);
        log.info(readHTML);
        message.setContent(readHTML, "text/html; charset=utf-8");
        try {
            mailSender.send(message);
            sent_flag = true;
            log.info("mailSender 邮件已发送！！！！！！");
        } catch (MailSendException e) {
            Set<String> tmpInvalidMails = getInvalidAddress(e);
            AssertHelperUtil.isTrue(EmptyUtil.isEmpty(tmpInvalidMails),SystemEnum.SYSTEM_ERROR.getCode(),"The recipient does not exist");
            log.error("mail Send fail,error msg{}", e.getMessage());
        }
        return sent_flag;
    }

    /**
     * 生成邮箱链接地址
     *
     * @param email 邮箱账号
     * @param type  邮件模板类型
     * @return
     */
    @Override
    @Transactional
    public String createLink(String email, String type) {
        String url = "";
        if (MailTemplateEnum.m002.getTemplateId().equals(type)|| MailTemplateEnum.m022.getTemplateId().equals(type)
                ||MailTemplateEnum.m003.getTemplateId().equals(type)|| MailTemplateEnum.m033.getTemplateId().equals(type)
                ||MailTemplateEnum.m005.getTemplateId().equals(type)|| MailTemplateEnum.m055.getTemplateId().equals(type)) {
            url = loginPage;
        }else{
            Calendar cal=Calendar.getInstance();
            cal.add(Calendar.DATE,1);
            Long time=cal.getTime().getTime();
            Map<String, Object> map = new HashMap<>();
            map.put("email", email);//邮箱
            map.put("exp", time);//过期时间
            map.put("type", type);//邮件模板类型
            String token = TokenUtil.createToken(map);
            log.info("token{}", token);
            log.info("exp{}", time);
            mailService.saveMailTokenToCache(CacheKeyConstants.KEY_MERCHANT_MAIL_TOKEN_PREFIX,
                    email, type, token);
            if (MailTemplateEnum.m001.getTemplateId().equals(type) || MailTemplateEnum.m011.getTemplateId().equals(type)
                    ||MailTemplateEnum.m006.getTemplateId().equals(type)|| MailTemplateEnum.m066.getTemplateId().equals(type)) {
                url = activateAccount + "?sid=" + token + "&exp="+map.get("exp");
            }
            if(MailTemplateEnum.m004.getTemplateId().equals(type)||MailTemplateEnum.m044.getTemplateId().equals(type)){
                url = resetPasswordUrl + "?sid=" + token+ "&exp="+map.get("exp");
            }
        }
        return url;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    @Override
    public ValidTokenResponse validToken(String token) {
        AssertHelperUtil.notNull(token, SystemEnum.VALIDATE_ERROR.getCode(),
                "Valid Token Request token Cannot be empty");
        Map<String, Object> map=TokenUtil.validToken(token);
        String state = map.get("state").toString();
        String email="";
        String type="";
        if (TokenState.VALID.toString().equals(state)) {
            JSONObject jsonObject = (JSONObject) map.get("data");
            email=jsonObject.get("email").toString();
            type=jsonObject.get("type").toString();
        }
        ValidTokenResponse response=ValidTokenResponse.builder().status(state)
                .email(email).type(type).build();
        return response;
    }

    /**
     * 重发邮件
     * @param request
     * @return
     */
    @Override
    public boolean resendMail(ResendMailRequest request) {
        AssertHelperUtil.notNull(request, SystemEnum.VALIDATE_ERROR.getCode(),
                "Resend Mail Request Cannot be empty");
        AssertHelperUtil.hasText(request.getEmail(), SystemEnum.VALIDATE_ERROR.getCode(),
                "【Email】 Cannot be empty");
        AssertHelperUtil.hasText(request.getMerchantNo(), SystemEnum.VALIDATE_ERROR.getCode(),
                "【merchantNo】 Cannot be empty");
        AssertHelperUtil.hasText(request.getTemplateId(), SystemEnum.VALIDATE_ERROR.getCode(),
                "【templateId】 Cannot be empty");
        //邮件编号（0：注册通知重发，1：激活通知）
        MerchantAndMemberDetailResponse response=merchantFeignService.getMerchantAndMemberDetail(request.getMerchantNo());
        log.info("MerchantAndMemberDetailResponse{}",JSON.toJSONString(response));
        AssertHelperUtil.isTrue("200".equals(response.getCode()), SystemEnum.MERCHANT_NOT_EXIST.getCode(),
                "MERCHANT NOT EXIST");
        Users users = userService.getUserByMail(request.getEmail());
        AssertHelperUtil.isTrue(users!=null, SystemEnum.USER_NOT_EXIST.getCode(),
                "USER NOT EXIST");
        AssertHelperUtil.isTrue(ResendTypeEnum.hasCode(request.getTemplateId()), SystemEnum.TEMPLATE_ID_ILLEGAL.getCode(),
                "Template Id Illegal");
        AssertHelperUtil.isTrue(response.getData().getMember()!=null, SystemEnum.VALIDATE_ERROR.getCode(),
                "【merchant member】 Cannot be empty");
        AssertHelperUtil.isTrue(response.getData().getMember().getCountry()!=null, SystemEnum.VALIDATE_ERROR.getCode(),
                "【merchant country】 Cannot be empty");
        String country=response.getData().getMember().getCountry();
        MailByTemplateRequest req=new MailByTemplateRequest();
        req.setMercId(request.getMerchantNo());
        req.setMercNm(response.getData().getMerchant().getMerchantName());
        req.setEmail(request.getEmail());
        String url="";
        String templateId="";
        if("1".equals(request.getTemplateId())){
            AssertHelperUtil.isTrue(users.getActiveStatus()==2, SystemEnum.ACCOUNT_ACTIVATED.getCode(),
                    SystemEnum.ACCOUNT_ACTIVATED.getDesc());
            if(CountryEnum.CHINA.getCode().equals(country)){
                templateId="m001";
            }else{
                templateId="m011";
            }
        }
        if("0".equals(request.getTemplateId())){
            if (UserStatusEnum.UNACTIVATED.getStatus() == users.getActiveStatus()) {
                if(CountryEnum.CHINA.getCode().equals(country)){
                    templateId="m001";
                }else{
                    templateId="m011";
                }
            } else {
                if(CountryEnum.CHINA.getCode().equals(country)){
                    templateId="m002";
                }else{
                    templateId="m022";
                }
            }
        }
        if("2".equals(request.getTemplateId())){
            if(CountryEnum.CHINA.getCode().equals(country)){
                templateId="m007";
            }else{
                templateId="m077";
            }
        }
        MailTemplateEnum templateEnum=MailTemplateEnum.getByTemplateId(templateId);
        req.setTemplateName(templateEnum.getTemplateName());
        req.setSubject(templateEnum.getSubject());
        url = createLink(request.getEmail(), templateEnum.getTemplateId());
        req.setUrl(url);
        boolean sent_flag = false;
        try {
            sent_flag = sendByTemplate(req);
        } catch (Exception e) {
            log.error("Send By Template Fail,error msg{}", e.getMessage());
        }
        return sent_flag;
    }
    /**
     * reset password  send email
     * @param emailVo
     * @return
     */
    @Override
    public BizResult resetPasswordSendEmail(EmailVo emailVo) throws Exception{
        AssertHelperUtil.notNull(emailVo.getEmail(), SystemEnum.VALIDATE_ERROR.getCode(),"Resend Mail Request Email Cannot be empty");
        Users users = userService.getUserByMail(emailVo.getEmail());
        AssertHelperUtil.isTrue(users!=null, SystemEnum.USER_NOT_EXIST.getCode(), "USER NOT EXIST");
        List<UserMercBinding>  userMercBindings= userService.findUserMerchantInfoByEmail(UserBind.builder().email(emailVo.getEmail()).build());
        AssertHelperUtil.isNull(userMercBindings,SystemEnum.USER_MERCHANT_IS_NULL.getCode(),SystemEnum.USER_MERCHANT_IS_NULL.getDesc());
        MerchantAndMemberDetailResponse response=merchantFeignService.getMerchantAndMemberDetail(userMercBindings.get(0).getMercId());
        String country=response.getData().getMember().getCountry();
        String templateId="m044";
        if(CountryEnum.CHINA.getCode().equals(country)){
            templateId="m004";
        }
        String   url=createLink(emailVo.getEmail(),templateId);
        MailTemplateEnum templateEnum=MailTemplateEnum.getByTemplateId(templateId);
        // 邮箱设置 发送类
        MailByTemplateRequest request=MailByTemplateRequest.builder().email(emailVo.getEmail()).templateName(templateEnum.getTemplateName()).url(url).subject(templateEnum.getSubject()).build();
        asyncExecutor.execute(new Runnable(){
            public void run() {
                try {
                    sendByTemplate(request);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return BizResult.create(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),null);
    }
    @Override
    public BizResult EmailNotify(UserEmailVo userEmailVo) throws Exception{
        List<User>  users=userService.selectUseInfoByCondition(UserVo.builder().userStatus("0").userNo(userEmailVo.getUserNo()).identityType("0").build());
        AssertHelperUtil.isNull(users,SystemEnum.USER_INFO_IS_NOT_NULL.getCode(),SystemEnum.USER_INFO_IS_NOT_NULL.getDesc());
        User user=users.get(0);
        if("1".equals(user.getActiveStatus())){
            return BizResult.create(SystemEnum.USER_EMAIL_IS_ACTIVE.getCode(),SystemEnum.USER_EMAIL_IS_ACTIVE.getDesc(),null);
        }
        MerchantAndMemberDetailResponse response=merchantFeignService.getMerchantAndMemberDetail(userEmailVo.getMerchantId());
        String country=response.getData().getMember().getCountry();
        String templateId="";
        if(CountryEnum.CHINA.getCode().equals(country)){
            templateId="m006";
        }else {
            templateId="m066";
        }
        log.info("inviter{}",user.getCreator());
        User  u=userService.selectUseInfoByCondition(UserVo.builder().userNo(user.getCreator()).identityType("0").build()).get(0);
        MailTemplateEnum templateEnum=MailTemplateEnum.getByTemplateId(templateId);
        String   url=createLink(userEmailVo.getEmail(),templateId);
        MailByTemplateRequest request=MailByTemplateRequest.builder().email(userEmailVo.getEmail()).mercNm(response.getData().getMerchant().getMerchantName()).mercId(response.getData().getMerchant().getMerchantNo()).templateName(templateEnum.getTemplateName()).inviter(u.getEmail()).url(url).subject(templateEnum.getSubject()).build();
        asyncExecutor.execute(new Runnable(){
            public void run() {
             try {
                    sendByTemplate(request);
                 }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return BizResult.create(SystemEnum.SYSTEM_SUCCESS.getCode(),SystemEnum.SYSTEM_SUCCESS.getDesc(),null);
    }
}
