package com.sjdf.platform.message.api;

import com.sjdf.platform.message.bean.SendApiConfig;

import java.io.File;

/**
 * 邮件发送接口
 * User: ketqi
 * Date: 2015-07-03 11:07
 */
public interface EmailApi {
    /**
     * 邮件发送
     *
     * @param toAddrs 收件人邮件地址(可多个,使用,; 分隔)
     * @param title   邮件标题
     * @param body    邮件内容
     * @return 发送结果, null:表示成功
     */
    String send(String toAddrs, String title, String body);

    /**
     * 邮件发送
     *
     * @param toAddrs     收件人邮件地址(可多个,使用,; 分隔)
     * @param title       邮件标题
     * @param body        邮件内容
     * @param attachments 附件列表
     * @return 发送结果, null:表示成功
     */
    String send(String toAddrs, String title, String body, File... attachments);

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
    String send(String toAddrs, String ccAddrs, String bccAddrs, String title, String body, File... attachments);

    /** 获取发该信息的接口配置 */
    SendApiConfig getConfig();
}
