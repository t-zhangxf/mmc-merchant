package com.pay.merchant.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACVerifier;
import com.pay.common.constants.CacheKeyConstants;
import com.pay.common.constants.CommonConstants;
import com.pay.common.enums.SystemEnum;
import com.pay.common.enums.TokenState;
import com.pay.common.utils.AssertHelperUtil;
import com.pay.common.utils.CacheUtil;
import com.pay.common.utils.ConstantUtils;
import com.pay.common.utils.EmptyUtil;
import com.pay.merchant.service.MailService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

@Service("mailService")
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender; //自动注入的bean

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 读取模板生成指定html文件
     * @param url
     * @param email
     * @param templateName
     * @param mercId
     * @param mercNm
     * @param inviter
     * @return
     * @throws IOException
     */
    @Override
    public String getMailContentByTemplate(String url, String email, String templateName,String mercId, String mercNm, String inviter,String description) throws IOException {
        // velocity引擎
        VelocityEngine velocityEngine = new VelocityEngine();
        // 设置文件路径属性
        Properties properties = new Properties();
        String  localPath ="";
        if("local".equals(ConstantUtils.getProperty("spring.env.config"))){
            localPath = this.getClass().getResource("/template/").getPath();
        }else{
            localPath = "/work/pay-merchant-client-build/template/";
            log.info("localPath{}",localPath);
        }
        properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,localPath);
        // 引擎初始化属性配置
        velocityEngine.init(properties);
        log.info("properties{}",velocityEngine.getProperty(Velocity.FILE_RESOURCE_LOADER_PATH+localPath));
        // 加载指定模版
        Template template = velocityEngine.getTemplate(templateName, "UTF-8");
        // 填充模板内容
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("url", url);
        velocityContext.put("email", email);
        velocityContext.put("merc_id", mercId);
        velocityContext.put("merc_nm", mercNm);
        velocityContext.put("inviter", inviter);
        velocityContext.put("description", description);
        String out = localPath + email +"template.html";
        BufferedWriter writer =null;
        FileOutputStream fos =null;
        try {
            fos =new FileOutputStream(out);
            writer =new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            // 写到模板
            template.merge(velocityContext, writer);
        }catch (Exception e){
            out=null;
            log.error("template.merge fail,error msg{}",e.getMessage());
        }finally {
            if (null != writer) {
                writer.close();
            }
            if (null != fos) {
                fos.close();
            }
        }
        return out;
    }

    /**
     * 读取html内容
     * @param spath
     * @return
     * @throws IOException
     */
    @Override
    public String readHTML(String spath) throws IOException {
        InputStreamReader isReader = null;
        BufferedReader bufReader = null;
        StringBuffer buf = new StringBuffer();
        try {
            File file = new File(spath);
            isReader = new InputStreamReader(new FileInputStream(file), "utf-8");
            bufReader = new BufferedReader(isReader, 1);
            String data;
            while((data = bufReader.readLine())!= null) {
                buf.append(data);
            }
        } catch (Exception e) {
            log.error("read HTML fail");
        } finally {
            if (null != isReader) {
                isReader.close();
            }
            if (null != bufReader) {
                bufReader.close();
            }
        }
        return buf.toString();
    }

    /**
     * 将邮件token放入缓存
     * @param key 缓存key
     * @param mail 邮箱地址
     * @param type 邮件模板编号
     * @param token token值
     * @return
     */
    @Override
    public boolean saveMailTokenToCache(String key,String mail,String type,String token) {
        AssertHelperUtil.notNull(key, SystemEnum.VALIDATE_ERROR.getCode(),
                "Redis Key Cannot be empty");
        AssertHelperUtil.notNull(mail, SystemEnum.VALIDATE_ERROR.getCode(),
                "Mail Cannot be empty");
        AssertHelperUtil.notNull(type, SystemEnum.VALIDATE_ERROR.getCode(),
                "Type Key Cannot be empty");
        AssertHelperUtil.notNull(token, SystemEnum.VALIDATE_ERROR.getCode(),
                "Token Key Cannot be empty");
        try {
            List<String> list=getMailTokenFromCache(key + mail + type);
            list.add(token);
            CacheUtil.saveCache(redisTemplate, key + mail + type,
                    JSONArray.toJSONString(list), CommonConstants.EXPIRED_TIME);
            return true;
        }catch (Exception e){
            log.error("Save Mail Token To Cache Fail,error msg{}",e.getMessage());
            return false;
        }

    }

    /**
     * 从缓存获取邮件Token信息
     * @param key
     * @return
     */
    @Override
    public List<String> getMailTokenFromCache(String key) {
        AssertHelperUtil.notNull(key, SystemEnum.VALIDATE_ERROR.getCode(),
                "Redis Key Cannot be empty");
        String tokenJson=CacheUtil.getCache(redisTemplate, key);
        List<String> list=new ArrayList<>();
        if(EmptyUtil.isNotEmpty(tokenJson)){
            list=JSON.parseArray(tokenJson,String.class);
        }
        return list;
    }

}
