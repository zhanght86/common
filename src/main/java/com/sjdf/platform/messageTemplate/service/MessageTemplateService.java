package com.sjdf.platform.messageTemplate.service;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.messageTemplate.bean.MessageTemplate;

import java.util.List;

/**
 * Create at 2013年8月8日 下午5:24:11
 * 邮件短信模板service
 *
 * @author KETQI
 */
public interface MessageTemplateService extends BaseService {
    /**
     * 分页查询邮件短信模板
     *
     * @param template 模板
     * @param page     分页组件
     * @return 模板列表
     */
    List<MessageTemplate> list(MessageTemplate template, Page page);

    /**
     * 添加邮件短信模板
     *
     * @param template 模板
     * @return Message
     */
    Message add(MessageTemplate template);

    /**
     * 邮件短信模板更新
     *
     * @param template 模板
     * @return Message
     */
    Message modify(MessageTemplate template);

    /**
     * 删除邮件短信模板
     *
     * @param idx 模板id
     * @return 消息组件
     */
    Message del(long idx);

    /**
     * 根据邮件短信模板标识获取邮件短信模板
     *
     * @param templateType 模板类型
     * @return 模板
     */
    MessageTemplate getByTemplateType(String templateType);
}
