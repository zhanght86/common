package com.sjdf.platform.message.utils;

import com.sjdf.platform.message.helper.MessageSendManager;

import java.util.Date;

/**
 * 邮件短信发送辅助工具
 *
 * @author ketqi
 * @author wangpeng
 *         Date: 2015-07-17 17:26
 */
public final class MessageSendUtils {

    private MessageSendUtils() {

    }

    /** 单例;达到lazy loading效果 */
    private static class SingletonHolder {
        private static final MessageSendUtils INSTANCE = new MessageSendUtils();
    }

    public static MessageSendUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 发送短信
     *
     * @param mobile 电话号码
     * @param sms    短信内容
     * @return 发送结果;null:表示发送成功
     * @author ketqi
     */
    public String sms(String mobile, String sms) {
        return sms(mobile, sms, null);
    }

    /**
     * 发送短信
     *
     * @param mobile   电话号码
     * @param sms      短信内容
     * @param sendTime 发送时间
     * @return 发送结果;null:表示发送成功
     * @author ketqi
     */
    public String sms(String mobile, String sms, Date sendTime) {
        Account account = new Account();
        return MessageSendManager.getInstance().sms(account.getUsername(), account.getPassword(), mobile, sms, sendTime);
    }

    /**
     * 发送短信
     *
     * @param mobile   电话号码
     * @param sms      短信内容
     * @param sendTime 发送时间
     * @param sendUser 发送邮件的用户
     * @return 发送结果;null:表示发送成功
     * @author wangpeng
     */
    public String sms(String mobile, String sms, Date sendTime, String sendUser) {
        Account account = new Account();
        return MessageSendManager.getInstance().sms(account.getUsername(), account.getPassword(), mobile, sms, sendTime, sendUser);
    }

    /**
     * 发送邮件
     *
     * @param to      邮件目标地址
     * @param title   标题
     * @param content 邮件内容
     * @return 发送结果;null:表示发送成功
     * @author ketqi
     */
    public String email(String to, String title, String content) {
        return email(to, title, content, null);
    }

    /**
     * 发送邮件
     *
     * @param to       邮件目标地址
     * @param title    标题
     * @param content  邮件内容
     * @param sendTime 发送时间
     * @return 发送结果;null:表示发送成功
     * @author ketqi
     */
    public String email(String to, String title, String content, Date sendTime) {
        return email(to, null, null, title, content, sendTime);
    }

    /**
     * 发送邮件
     *
     * @param to       邮件目标地址
     * @param cc       抄送地址
     * @param bcc      暗送地址
     * @param title    标题
     * @param content  邮件内容
     * @param sendTime 发送时间
     * @return 发送结果;null:表示发送成功
     * @author ketqi
     */
    public String email(String to, String cc, String bcc, String title, String content, Date sendTime) {
        Account account = new Account();
        return MessageSendManager.getInstance().email(account.getUsername(), account.getPassword(), to, cc, bcc, title, content, sendTime);
    }

    /**
     * 发送邮件
     *
     * @param to       邮件目标地址
     * @param title    标题
     * @param content  邮件内容
     * @param sendTime 发送时间
     * @param mailType 邮件类型
     * @param sendUser 发送用户
     * @return 发送结果;null:表示发送成功
     * @author wangpeng
     */
    public String email(String to, String title, String content, Date sendTime, long mailType, String sendUser) {
        return email(to, null, null, title, content, sendTime, mailType, sendUser);
    }

    /**
     * 发送邮件
     *
     * @param to       邮件目标地址
     * @param cc       抄送地址
     * @param bcc      暗送地址
     * @param title    标题
     * @param content  邮件内容
     * @param sendTime 发送时间
     * @param mailType 邮件类型
     * @param sendUser 发送用户
     * @return 发送结果;null:表示发送成功
     * @author wangpeng
     */
    public String email(String to, String cc, String bcc, String title, String content, Date sendTime, long mailType, String sendUser) {
        Account account = new Account(mailType);
        return MessageSendManager.getInstance().email(account.getUsername(), account.getPassword(), to, cc, bcc, title, content, sendTime, sendUser);
    }
}
