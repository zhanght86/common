package com.sjdf.platform.messageTemplate.service.impl;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.EmailManager;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.dictionary.bean.MessageTemplateType;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.messageTemplate.bean.MessageTemplate;
import com.sjdf.platform.messageTemplate.service.MessageTemplateService;
import com.sjdf.platform.rbac.helper.UserHelper;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 邮件短信模板service
 * Create at 2013年8月8日 下午5:24:11
 *
 * @author KETQI
 */
@Service
public class MessageTemplateServiceImpl extends BaseServiceImpl implements MessageTemplateService {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(MessageTemplateServiceImpl.class);

    /**
     * 分页查询邮件模板
     *
     * @param template 模板
     * @param page     分页组件
     * @return 模板列表
     */
    public List<MessageTemplate> list(MessageTemplate template, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(MessageTemplate.class);
        if (template != null) {
            if (template.getId() != 0) {
                criteria.add(Restrictions.eq("id", template.getId()));
            }
            // 系统类型
            if (template.getSystemType() > 0) {
                criteria.add(Restrictions.eq("systemType", template.getSystemType()));
            }
            // 模板标识符
            if (template.getTemplateType() > 0) {
                criteria.add(Restrictions.eq("templateType", template.getTemplateType()));
            }
            // 邮件标题
            if (StringUtils.hasText(template.getTitle())) {
                criteria.add(Restrictions.like("title", template.getTitle(), MatchMode.ANYWHERE));
            }
            // 邮件是否有效
            if (template.getEmailValid() > 0) {
                criteria.add(Restrictions.eq("emailValid", template.getEmailValid()));
            }
            // 短信是否有效
            if (template.getSmsValid() > 0) {
                criteria.add(Restrictions.eq("smsValid", template.getSmsValid()));
            }
            // 邮件内容
            if (StringUtils.hasText(template.getEmail())) {
                criteria.add(Restrictions.like("email", template.getEmail(), MatchMode.ANYWHERE));
            }
            // 短信内容
            if (StringUtils.hasText(template.getSms())) {
                criteria.add(Restrictions.like("sms", template.getSms(), MatchMode.ANYWHERE));
            }
        }
        return baseDao.listByCriteria(criteria, page);
    }

    /**
     * 添加邮件模板
     *
     * @param template 模板
     * @return Message
     */
    public Message add(MessageTemplate template) {
        Message message = validate(template);
        if (message.hasErrorMessage()) {
            return message;
        }
        baseDao.save(template);

        // 发送测试邮件
        try {
            EmailManager.getInstance().send(template);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return Message.createMessage().setReturnData(template).setInfo(template.wrapUpdateContent(null, false));
    }

    /**
     * 邮件模板更新
     *
     * @param template 模板
     * @return Message
     */
    public Message modify(MessageTemplate template) {
        Message message = validate(template);
        if (message.hasErrorMessage()) {
            return message;
        }

        MessageTemplate oldTemplate = get(MessageTemplate.class, template.getId());
        if (oldTemplate == null) {
            return Message.createMessage("message.template.not.exist");
        }

        String info = oldTemplate.wrapUpdateContent(template, true);
        try {
            oldTemplate.setUpdateTime(new Date());
            oldTemplate.setEmail(template.getEmail());
            oldTemplate.setSms(template.getSms());
            oldTemplate.setEmailValid(template.getEmailValid());
            oldTemplate.setSmsValid(template.getSmsValid());
            oldTemplate.setSystemType(template.getSystemType());
            oldTemplate.setTemplateType(template.getTemplateType());
            oldTemplate.setTitle(template.getTitle());
            oldTemplate.setUpdateUser(UserHelper.getCurrentLoginUser().getUsername());
            baseDao.update(oldTemplate);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            return Message.createMessage("message.template.update.fail");
        }

        // 发送测试邮件
        try {
            EmailManager.getInstance().send(oldTemplate);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("send test email fail", e);
        }

        return Message.createMessage().setInfo(info).setReturnData(oldTemplate);
    }

    /**
     * 删除邮件模板
     *
     * @param idx 模板id
     * @return 消息组件
     */
    public Message del(long idx) {
        MessageTemplate template = get(MessageTemplate.class, idx);
        if (template == null) {
            return Message.createMessage("message.template.not.exist");
        }
        delete(template);

        return Message.createMessage().setInfo(template.wrapUpdateContent(null, false)).setReturnData(template);
    }

    /**
     * 根据邮件标识获取邮件模板
     *
     * @param templateType 模板类型
     * @return 模板
     */
    public MessageTemplate getByTemplateType(String templateType) {
        List<MessageTemplate> list = baseDao.find("from MessageTemplate where templateType = ?", templateType);
        return (list != null && !list.isEmpty()) ? list.get(0) : null;
    }

    /** 验证邮件模板的有效性 */
    private Message validate(MessageTemplate template) {
        if (template == null) {
            return Message.createMessage("message.template.null");
        }

        if (ConfigManager.getInstance().getValue(SystemType.class, template.getSystemType()) == null) {
            return Message.createMessage("message.template.systemType.invalid");
        }

        if (ConfigManager.getInstance().getValue(MessageTemplateType.class, template.getTemplateType()) == null) {
            return Message.createMessage("message.template.templateType.invalid");
        }

        if (!(StringUtils.hasText(template.getEmail()) || StringUtils.hasText(template.getSms()))) {
            return Message.createMessage("message.template.email.or.sms.null");
        }

        if (StringUtils.hasText(template.getEmail()) && !StringUtils.hasText(template.getTitle())) {
            return Message.createMessage("message.template.title.null");
        }

        long size = baseDao.countHql("select count(*) from MessageTemplate where templateType = ?", template.getTemplateType());
        if (template.getId() == 0) {
            if (size != 0) {
                return Message.createMessage("message.template.templateType.exist", ConfigManager.getInstance().getName(MessageTemplateType.class, template.getTemplateType()));
            }
        } else {
            if (!(size == 0 || size == 1)) {
                return Message.createMessage("message.template.templateType.exist", ConfigManager.getInstance().getName(MessageTemplateType.class, template.getTemplateType()));
            }
        }
        return Message.createEmptyMessage();
    }
}
