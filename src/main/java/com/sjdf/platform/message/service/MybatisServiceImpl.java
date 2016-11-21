package com.sjdf.platform.message.service;

import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.message.bean.BaseEmailMessage;
import com.sjdf.platform.message.bean.BaseMessage;
import com.sjdf.platform.message.bean.EmailMessage;
import com.sjdf.platform.message.bean.SMSMessage;
import com.sjdf.platform.mybatis.MessageMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis查询service实现
 * User: ketqi
 * Date: 2015-07-16 10:57
 */
public class MybatisServiceImpl extends BaseServiceImpl implements MybatisService {
    private MessageMapper messageMapper;

    /**
     * 短信查询
     *
     * @param message 短信限制条件
     * @param page    分页组件
     * @return 短信列表
     */
    @Override
    public List<SMSMessage> querySmsList(SMSMessage message, Page page) {
        Map<String, Object> params = wrapMessageRestrictions(message, page);
        int count = messageMapper.countSms(params);
        page.setTotalCount(count);
        return messageMapper.querySms(params);
    }

    /**
     * 邮件查询
     *
     * @param message 邮件限制条件
     * @param page    分页组件
     * @return 邮件列表
     */
    @Override
    public List<EmailMessage> queryEmailList(EmailMessage message, Page page) {
        Map<String, Object> params = wrapMessageRestrictions(message, page);
        int count = messageMapper.countEmail(params);
        page.setTotalCount(count);
        return messageMapper.queryEmail(params);
    }

    /**
     * 短信查询
     *
     * @param idx 主键
     * @return 短信列表
     */
    @Override
    public SMSMessage getSms(long idx) {
        return messageMapper.getSms(idx);
    }

    /**
     * 邮件查询
     *
     * @param idx 主键
     * @return 邮件列表
     */
    @Override
    public EmailMessage getEmail(long idx) {
        return messageMapper.getEmail(idx);
    }

    /**
     * 封装查询条件
     *
     * @param message 消息
     * @param page    分页组件
     * @return 查询条件map
     */
    private Map<String, Object> wrapMessageRestrictions(BaseMessage message, Page page) {
        Map<String, Object> params = new HashMap<>();
        if (message != null) {
            if (message.getCompany() != 0L) {
                params.put("company", message.getCompany());
            }
            if (message.getSystemType() != 0L) {
                params.put("systemType", message.getSystemType());
            }
            if (PlatformUtils.hasText(message.getUserId())) {
                params.put("userId", message.getUserId());
            }
            if (PlatformUtils.hasText(message.getAddress())) {
                params.put("address", message.getAddress());
            }
            if (PlatformUtils.hasText(message.getContent())) {
                params.put("content", message.getContent());
            }
            if (message.getStatus() != 0L) {
                params.put("status", message.getStatus());
            }
            if (message.getCreateTime() != null) {
                params.put("createTime", message.getCreateTime());
            }
            if (message.getUpdateTime() != null) {
                params.put("updateTime", message.getUpdateTime());
            }
            if (message instanceof BaseEmailMessage) {
                BaseEmailMessage emailMessage = (BaseEmailMessage) message;
                if (PlatformUtils.hasText(emailMessage.getTitle())) {
                    params.put("title", emailMessage.getTitle());
                }
            }
        }

        params.put("startIndex", page.getStartIndex());
        params.put("pageSize", page.getPageSize());
        return params;
    }

    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }
}
