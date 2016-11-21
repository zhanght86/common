package com.sjdf.platform.message.service;

import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.message.bean.EmailMessage;
import com.sjdf.platform.message.bean.SMSMessage;

import java.util.List;

/**
 * mybatis查询service
 * User: ketqi
 * Date: 2015-07-16 10:56
 */
public interface MybatisService extends BaseService {
    /**
     * 短信查询
     *
     * @param message 短信限制条件
     * @param page    分页组件
     * @return 短信列表
     */
    List<SMSMessage> querySmsList(SMSMessage message, Page page);

    /**
     * 邮件查询
     *
     * @param message 邮件限制条件
     * @param page    分页组件
     * @return 邮件列表
     */
    List<EmailMessage> queryEmailList(EmailMessage message, Page page);

    /**
     * 短信查询
     *
     * @param idx 主键
     * @return 短信列表
     */
    SMSMessage getSms(long idx);

    /**
     * 邮件查询
     *
     * @param idx 主键
     * @return 邮件列表
     */
    EmailMessage getEmail(long idx);
}
