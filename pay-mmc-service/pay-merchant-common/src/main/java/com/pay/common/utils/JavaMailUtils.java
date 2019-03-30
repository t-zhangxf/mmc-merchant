package com.pay.common.utils;

import com.google.common.collect.Lists;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.SendFailedException;
import javax.mail.internet.*;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 发送邮件工具类
 */
@Slf4j
public class JavaMailUtils {

    /**
     * 设置发件/收件邮箱地址
     * @param message 邮件对象
//     * @param toccProperty 收件人配置
     * @param tocc 收件人
     * @return
     * @throws Exception
     */
    public static MimeMessageHelper setFromAndTo(MimeMessage message , String tocc)throws Exception{
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        String mail=  ConstantUtils.getProperty(toccProperty);
//        String[] tos =  mail.split(",");
        // 设置邮件抄送者地址
//        if(tos != null && tos.length != 0){
//            InternetAddress[] toCC = new InternetAddress[tos.length];
//            // 设置邮件消息的发送者
//            for (int i = 0; i < tos.length; i++) {
//                toCC[i] = new InternetAddress(tos[i]);
//            }
//            helper.setTo(toCC);
//        }
        helper.setTo(tocc);
        String alias=javax.mail.internet.MimeUtility.encodeText("SHAREit Biz");
//        helper.setFrom(new InternetAddress(alias + "<noreply@ushareit.com>"));
        helper.setFrom(new InternetAddress(alias + "<noreply@ushareit.com>"));
        return helper;
    }
    public static List<String> mailList(String[] tos ){
        List<String> mails=Lists.newArrayList();
        for (String  arr:tos
             ) {
          mails.add(arr);
        }
        return mails;
    }





    /**
     * @desc  邮箱地址无效再次发送
     * @param message
     * @param tos
     * @return
     * @throws Exception
     */
    public static MimeMessageHelper setUserMailReceiver(MimeMessage message,String[] tos) throws Exception{
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        InternetAddress[] toCC = new InternetAddress[tos.length];
        if(tos != null && tos.length != 0){
            for (int i = 0; i < tos.length; i++) {
                toCC[i] = new InternetAddress(tos[i]);
            }
            helper.setTo(toCC);
        }
        helper.setFrom("noreply@pay.com");
        return helper;
    }
    /**
     * @desc  list to array
     * @param mailList
     * @return  array
     */
    public static String[] List2Array(List<String> mailList,Set<String> tmpInvalidMails ){
        if (!tmpInvalidMails.isEmpty()){//收件人地址有误
                mailList.removeAll(tmpInvalidMails);
                String[] strings = new String[mailList.size()];
                return mailList.toArray(strings);
        }
        return null;
    }
    /**
     * @desc  获取收件人无效地址
     * @param e
     * @return
     */
    public static Set<String> getInvalidAddress(MailSendException e){
        Set<String> mails = new HashSet<>();
        for(Exception exception: e.getFailedMessages().values()){
            if(exception instanceof SendFailedException){
                for(Address address: ((SendFailedException) exception).getInvalidAddresses()){
                    log.info("收件人无效地址:"+address.toString());
                    mails.add(address.toString());
                }
            }
        }
        return mails;
    }



    /**
     * 添加邮件附件
     * @param multipart 邮件正文对象
     * @param localPath 附件文件存放路径
     * @return
     * @throws Exception
     */
    public static MimeMultipart addAttachment(MimeMultipart  multipart, String  localPath) throws Exception {
        log.info("localPath{}",localPath);
        File file = new File(localPath);//生成报表的文件夹
        File[] tempList = file.listFiles();
        log.info("tempList.length{}",tempList.length);
        if(tempList!=null&&tempList.length>0){
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    log.info("tempList[i]{}=" , tempList[i]);
                    BodyPart attachmentBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(tempList[i]);
                    attachmentBodyPart.setDataHandler(new DataHandler(source));
                    attachmentBodyPart.setFileName(MimeUtility.encodeText(tempList[i].getName(),"UTF-8","B"));
                    multipart.addBodyPart(attachmentBodyPart);
                }
            }
        }else{
            return null;
        }
        return multipart;
    }
}
