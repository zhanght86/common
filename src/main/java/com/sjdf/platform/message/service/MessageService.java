package com.sjdf.platform.message.service;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.message.bean.*;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * 消息service
 * User: ketqi
 * Date: 2015-07-01 14:53
 */
public interface MessageService extends BaseService {
    /**
     * 消息接口配置列表
     *
     * @param config 筛选限制条件
     * @param page   分页组件
     * @return 接口配置列表
     */
    List<SendApiConfig> configList(SendApiConfig config, Page page);

    /**
     * 添加或者更新接口配置
     *
     * @param config   接口配置
     * @param operator 操作人员
     * @return 消息组件
     */
    Message saveOrUpdateConfig(SendApiConfig config, String operator);

    /**
     * 删除接口配置
     *
     * @param idx      接口配置id
     * @param operator 操作人员
     * @return 消息组件
     */
    Message delConfig(long idx, String operator);

    /**
     * 信息接口授权列表
     *
     * @param user 限制条件
     * @param page 分页组件
     * @return 列表
     */
    List<MessageApiUser> userList(MessageApiUser user, Page page);

    /**
     * 添加或者更新信息接口授权信息
     *
     * @param user     信息接口授权信息
     * @param operator 操作人员
     * @return 消息组件
     */
    Message saveOrUpdateUser(MessageApiUser user, String operator);

    /**
     * 短信列表
     *
     * @param message 短信限制条件
     * @param page    分页组件
     * @return 短信列表
     */
    List<SMSMessage> smsMessageList(SMSMessage message, Page page);

    /**
     * 添加或更新短信
     *
     * @param message  短信消息
     * @param xls      短信Excel模板
     * @param operator 操作人员
     * @return 消息组件
     */
    Message saveOrUpdateSms(SMSMessage message, File xls, String operator);

    /**
     * 添加或更新邮件信息
     *
     * @param message  邮件信息
     * @param operator 操作人员
     * @return 消息组件
     */
    Message saveOrUpdateEmail(EmailMessage message, String operator);

    /**
     * 更新消息发送状态
     *
     * @param messageType 消息类型
     * @param ids         消息id列表
     * @param status      状态
     * @param operator    操作人员
     * @return 消息组件
     */
    Message updateStatus(long messageType, long[] ids, long status, String operator);

    /**
     * 邮件列表
     *
     * @param message 邮件限制列表
     * @param page    分页组件
     * @return 邮件列表
     */
    List<EmailMessage> emailMessageList(EmailMessage message, Page page);

    /**
     * 获取待发送的短信列表
     *
     * @return 短信列表
     */
    List<SMSMessage> getWaitSendSmsList();

    /**
     * 获取待发送的邮件列表
     *
     * @return 邮件列表
     */
    List<EmailMessage> getWaitSendEmailList();

    /**
     * 信息模板树
     *
     * @param idx         上级id
     * @param messageType 消息类型
     * @return 信息模板列表
     */
    List<InfoTemplate> listTemplateTree(long idx, long messageType);

    /**
     * 添加模板
     *
     * @param info     模板信息
     * @param operator 操作人员
     * @return 消息组件
     */
    Message addTemplate(InfoTemplate info, String operator);

    /**
     * 删除模板
     *
     * @param idx      模板信息id
     * @param operator 操作人员
     * @return 消息组件
     */
    Message delTemplate(long idx, String operator);

    /**
     * 更新模板
     *
     * @param info     模板信息
     * @param operator 操作人员
     * @return 消息组件
     */
    Message updateTemplate(InfoTemplate info, String operator);

    /**
     * 转移邮件信息
     *
     * @param businessUserIdList 业务类型邮件的用户列表
     * @param isIncluded         信息是否包含businessUserIdList
     */
    void transferEmail(Set<String> businessUserIdList, boolean isIncluded);

    /** 转移短信信息 */
    void transferSms();

    /** 删除备份信息 */
    void delMessageBackup(long messageType);

    /**
     * @param title    邮件标题
     * @param sendUser 发送用户
     * @return
     * @category 统计当前发送的推荐码邮件
     */
    long countRecommendEmail(String title, String sendUser);

    /**
     * 添加或更新微信信息
     *
     * @param message  微信信息
     * @param operator 操作人员
     * @return 消息组件
     */
    Message saveOrUpdateWechat(WechatMessage message, String operator);

    /**
     * 微信列表
     *
     * @param message 微信限制列表
     * @param page    分页组件
     * @return 微信列表
     */
    List<WechatMessage> wechatMessageList(WechatMessage message, Page page);

}
