package com.pay.merchant.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface MailService {
    /**
     * 填充模板生成邮件主体
     * @param url
     * @param email
     * @param templateName
     * @param mercId
     * @param mercNm
     * @param inviter
     * @return
     */
     String getMailContentByTemplate(String url, String email,String templateName,String mercId, String mercNm, String inviter,String description) throws IOException;

    /**
     * 读取html页面信息
     * @param spath
     * @return
     */
    String readHTML(String spath) throws IOException;

    /**
     * 将邮件token放入缓存
     * @param key 缓存key
     * @param mail 邮箱地址
     * @param type 邮件模板编号
     * @param token token值
     * @return
     */
    boolean saveMailTokenToCache(String key,String mail,String type,String token);

    /**
     * 从缓存获取邮件Token信息
     * @param key
     * @return
     */
    List<String> getMailTokenFromCache(String key);


}
