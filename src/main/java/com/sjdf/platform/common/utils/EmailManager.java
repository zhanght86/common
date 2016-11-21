package com.sjdf.platform.common.utils;

import com.sjdf.platform.common.conf.ProfileMapHelper;
import com.sjdf.platform.common.conf.ProfileProvider;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.messageTemplate.bean.MessageTemplate;
import org.springframework.util.StringUtils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create at 2013年8月8日 下午5:08:28
 * 邮件管理器
 *
 * @author KETQI
 */
public final class EmailManager {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(EmailManager.class);
    // 发送给收件人
    public static final int RECIPIENT_TYPE_TO = 1;
    // 抄送
    public static final int RECIPIENT_TYPE_CC = 2;
    // 暗抄送
    public static final int RECIPIENT_TYPE_BCC = 4;
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^([a-z0-9A-Z]+[_-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");// 邮件验证正则表达式
    // 进行邮件服务器用户认证
    private Authenticator auth;
    // 获取系统环境
    private Properties props;
    private static EmailManager instance = new EmailManager();

    private EmailManager() {
        // 获取系统环境
        props = System.getProperties();
        props.put("mail.smtp.host", ProfileProvider.getValue(ProfileMapHelper.SYSTEM_EMAIL_HOST));
        props.put("mail.smtp.auth", "true");
        // 进行邮件服务器用户认证
        auth = new EmailAutherticator();
    }

    public static EmailManager getInstance() {
        return instance;
    }

    /**
     * @param mailTo        邮件接收方的地址
     * @param mailSubject   邮件主题
     * @param mailBody      邮件正文
     * @param recipientType 接受者类型
     * @throws Exception 发送文本格式的邮件
     */
    public void send(String mailTo, String mailSubject, String mailBody, int recipientType) throws Exception {
        if (!StringUtils.hasText(mailTo)) {
            return;
        }

        Session session = Session.getInstance(props, auth);
        Address address = new InternetAddress(ProfileProvider.getValue(ProfileMapHelper.SYSTEM_EMAIL_ADDRESS), ProfileProvider.getValue(ProfileMapHelper.SYSTEM_EMAIL_FROM_NAME));// 设置邮件发送方的地址和姓名
        // 设置session,和邮件服务器进行通讯。
        MimeMessage message = new MimeMessage(session);
        // 设置邮件主题
        message.setSubject(mailSubject);
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件发送者的地址
        message.setFrom(address);

        // ==================设置邮件正文==================
        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart multipart = new MimeMultipart();
        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart bodyPart = new MimeBodyPart();
        // 设置HTML内容
        bodyPart.setContent(mailBody, "text/html; charset=utf-8");
        multipart.addBodyPart(bodyPart);

        // 将MiniMultipart对象设置为邮件内容
        message.setContent(multipart);

        // ==================设置邮件接收方的地址==================
        // 发送
        if ((recipientType & RECIPIENT_TYPE_TO) == RECIPIENT_TYPE_TO) {
            // 添加邮件接收方
            message.addRecipients(Message.RecipientType.TO, praseAddress(mailTo));
        }

        // 抄送
        if ((recipientType & RECIPIENT_TYPE_CC) == RECIPIENT_TYPE_CC) {
            // 添加邮件抄送接收方
            message.addRecipients(Message.RecipientType.CC, praseAddress(mailTo));
        }

        // 暗抄
        if ((recipientType & RECIPIENT_TYPE_BCC) == RECIPIENT_TYPE_BCC) {
            // 添加邮件暗抄送接收方
            message.addRecipients(Message.RecipientType.BCC, praseAddress(mailTo));
        }

        // 发送邮件
        Transport.send(message);
    }

    /**
     * @param mailTo      邮件接收方的地址
     * @param mailSubject 邮件主题
     * @param mailBody    邮件正文
     * @throws Exception 发送文本格式的邮件
     */
    public void send(String mailTo, String mailSubject, String mailBody) throws Exception {
        send(mailTo, mailSubject, mailBody, RECIPIENT_TYPE_TO);
    }

    /**
     * @param template 邮件模板
     *                 发送邮件
     */
    public void send(MessageTemplate template) throws Exception {
        String mailBody = template.renderEmail(null);
        if (mailBody != null) {
            send(template.getMailAddress(), template.getTitle(), mailBody);
        }
    }

    /** 用来进行服务器对用户的认证 */
    public class EmailAutherticator extends Authenticator {
        private String username;
        private String password;

        public EmailAutherticator() {
            username = ProfileProvider.getValue(ProfileMapHelper.SYSTEM_EMAIL_USERNAME);
            password = ProfileProvider.getValue(ProfileMapHelper.SYSTEM_EMAIL_PWD);
        }

        public EmailAutherticator(String user, String pwd) {
            username = user;
            password = pwd;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }

    /**
     * @param singleEmailAddress 地址
     * @return 验证邮件地址格式是否有效
     */
    public static boolean validate(String singleEmailAddress) {
        Matcher matcher = EMAIL_PATTERN.matcher(singleEmailAddress);
        return matcher.find();
    }

    /**
     * @param emailAddress 地址
     * @return 解析邮件地址
     */
    public static InternetAddress[] praseAddress(String emailAddress) {
        if (!StringUtils.hasText(emailAddress)) {
            return new InternetAddress[0];
        }

        String[] strs = emailAddress.split(";");
        List<String> list = new ArrayList<>();
        for (String str : strs) {
            if (StringUtils.hasText(str) && validate(str)) {
                list.add(str);
            }
        }

        InternetAddress[] addresses = new InternetAddress[list.size()];
        for (int i = 0; i < addresses.length; i++) {
            try {
                addresses[i] = new InternetAddress(list.get(i));
            } catch (AddressException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }

        return addresses;
    }
}
