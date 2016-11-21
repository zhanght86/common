package com.sjdf.platform.message.api.impl;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.verify.RegexVerify;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.message.api.EmailApi;
import com.sjdf.platform.message.bean.SendApiConfig;
import org.springframework.util.StringUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 默认邮件发送接口实现
 * User: ketqi
 * Date: 2015-07-03 14:32
 */
@BeanName(name = "默认邮件发送接口")
public class DefaultEmailApiImpl extends AbstractEmailApi implements EmailApi {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(DefaultEmailApiImpl.class);

    public DefaultEmailApiImpl(SendApiConfig config) {
        super(config);
    }

    /**
     * 邮件发送
     *
     * @param toAddrs 收件人邮件地址(可多个,使用,; 分隔)
     * @param title   邮件标题
     * @param body    邮件内容
     * @return 发送结果, null:表示成功
     */
    public String send(String toAddrs, String title, String body) {
        return send(toAddrs, null, null, title, body);
    }

    /**
     * 邮件发送
     *
     * @param toAddrs     收件人邮件地址(可多个,使用,; 分隔)
     * @param title       邮件标题
     * @param body        邮件内容
     * @param attachments 附件列表
     * @return 发送结果, null:表示成功
     */
    public String send(String toAddrs, String title, String body, File... attachments) {
        return send(toAddrs, null, null, title, body, attachments);
    }

    /**
     * 邮件发送
     *
     * @param toAddrs     收件人邮件地址(可多个,使用,; 分隔)
     * @param ccAddrs     邮件抄送接收方地址(可多个,使用,; 分隔)
     * @param bccAddrs    邮件暗抄送接收方地址(可多个,使用,; 分隔)
     * @param title       邮件标题
     * @param body        邮件内容
     * @param attachments 附件列表
     * @return 发送结果, null:表示成功
     */
    @Override
    public String send(String toAddrs, String ccAddrs, String bccAddrs, String title, String body, File... attachments) {
        Properties props = new Properties();
        props.putAll(System.getProperties()); // 获取系统环境

        Authenticator auth = new EmailAutherticator(getUsername(), getPassword()); // 进行邮件服务器用户认证
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", getHost());
        //两分钟读超时时间
        props.put("mail.smtp.timeout", CommonPlatformConstant.LENGTH_2 * CommonPlatformConstant.BRANCH_OF_MILLISECOND);
        //1分钟连接超时时间
        props.put("mail.smtp.connectiontimeout", CommonPlatformConstant.BRANCH_OF_MILLISECOND);

        try {
            // 发件人邮件地址,设置邮件发送方的地址和姓名
            Address fromAddress = new InternetAddress(getSourceAddress(), getPersonalName());

            // 设置session,和邮件服务器进行通讯。
            Session session = Session.getInstance(props, auth);
            Message message = new MimeMessage(session);
            // 设置邮件主题
            message.setSubject(title);
            // 设置邮件发送日期
            message.setSentDate(new Date());

            // 设置邮件正文
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart multipart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart bodyPart = new MimeBodyPart();
            // 设置HTML内容
            bodyPart.setContent(body, "text/html; charset=utf-8");
            multipart.addBodyPart(bodyPart);

            //上传附件
            if (attachments != null) {
                for (File file : attachments) {
                    if (file == null) {
                        continue;
                    }

                    bodyPart = new MimeBodyPart();
                    bodyPart.setFileName(file.getName());
                    bodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
                    multipart.addBodyPart(bodyPart);
                }
            }

            // 将MiniMultipart对象设置为邮件内容
            message.setContent(multipart);
            // 设置邮件发送者的地址
            message.setFrom(fromAddress);

            // 添加邮件接收方
            if (PlatformUtils.hasText(toAddrs)) {
                message.addRecipients(Message.RecipientType.TO, praseAddress(toAddrs));
            }

            // 添加邮件抄送接收方
            if (PlatformUtils.hasText(ccAddrs)) {
                message.addRecipients(Message.RecipientType.CC, praseAddress(ccAddrs));
            }

            // 添加邮件暗抄送接收方
            if (PlatformUtils.hasText(bccAddrs)) {
                message.addRecipients(Message.RecipientType.BCC, praseAddress(bccAddrs));
            }

            // 发送邮件
            Transport.send(message);
        } catch (Exception e) {
            LOGGER.error("send email fail:" + toAddrs + ";" + title, e);
            return PlatformUtils.getStackTrace(e);
        }
        return null;
    }

    /**
     * @param emailAddress 邮件地址
     * @return 解析邮件地址
     */
    public static InternetAddress[] praseAddress(String emailAddress) {
        if (!StringUtils.hasText(emailAddress)) {
            return new InternetAddress[0];
        }

        List<String> emailList = PlatformUtils.parse2StrList(emailAddress);
        List<String> list = new ArrayList<>();
        for (String str : emailList) {
            if (PlatformUtils.hasText(str) && RegexVerify.checkEmail(str)) {
                list.add(str);
            }
        }

        InternetAddress[] addresses = new InternetAddress[list.size()];
        try {
            for (int i = 0; i < addresses.length; i++) {
                addresses[i] = new InternetAddress(list.get(i));
            }
        } catch (AddressException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return addresses;
    }

    /**
     * 用来进行服务器对用户的认证
     */
    private class EmailAutherticator extends Authenticator {
        private final String username;
        private final String password;

        public EmailAutherticator(String user, String pwd) {
            super();
            username = user;
            password = pwd;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }
}
