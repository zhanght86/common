package com.sjdf.platform.messageTemplate.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.annotations.Permission;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.LoggerManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.messageTemplate.bean.MessageTemplate;
import com.sjdf.platform.messageTemplate.helper.MessageTemplateHelper;
import com.sjdf.platform.messageTemplate.helper.MessageTemplateManager;
import com.sjdf.platform.messageTemplate.service.MessageTemplateService;
import com.sjdf.platform.notify.service.NotifyTrackService;
import com.sjdf.platform.rbac.helper.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * 邮件和短信模板
 * Create at 2012-08-06
 *
 * @author 王正伟
 */
public class MessageTemplateAction extends BaseAction {
    private static final long serialVersionUID = -5819856925151842076L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(MessageTemplateAction.class);
    @Autowired
    private MessageTemplateService templateService;
    @Autowired
    private NotifyTrackService notifyTrackService;
    private List<MessageTemplate> templateList;
    private MessageTemplate template;
    private List<? extends Dictionary> templateTypeList = Collections.emptyList();
    private String action = "";
    /** 页面显示的错误信息 */
    private String error;
    // 昵称
    private String name;

    /**
     * admin/common/MessageTemplateAction!list.action
     * 邮件模板列表
     *
     * @return result
     */
    @Permission(code = "06010301", name = "邮件短信模板列表")
    public String list() {
        templateList = templateService.list(template, page);
        return "list";
    }

    /**
     * <pre>
     * URL:admin/common/MessageTemplateAction!preAdd.action
     * </pre>
     * <p/>
     * 添加页面
     *
     * @return result
     */
    @Permission(code = "06010302", name = "邮件短信模板添加页面")
    public String preAdd() {
        action = "admin/common/MessageTemplateAction!add.action";
        return "post";
    }

    /**
     * <pre>
     * URL:admin/common/MessageTemplateAction!add.action
     * </pre>
     * <p/>
     * 添加邮件模板
     *
     * @return result
     */
    @Permission(code = "06010303", name = "添加邮件短信模板", supportedCode = "010302")
    public String add() {
        // 添加邮件模板
        Message message = templateService.add(template);
        if (message.hasErrorMessage()) {
            error = getText(message);
        } else {
            notifyTrackService.saveMessageTemplate(template, OperatorAction.ADD);
            MessageTemplateHelper.add(template);
            saveLog(OperatorAction.ADD, LogType.INFO, template.getTitle(), message.getInfo(), null);
        }
        return "redirectList";
    }

    /**
     * <pre>
     * URL:admin/common/MessageTemplateAction!edit
     * </pre>
     * <p/>
     * 编辑邮件模板页面
     *
     * @return result
     */
    @Permission(code = "06010304", name = "邮件短信模板编辑页面")
    public String edit() {
        action = "admin/common/MessageTemplateAction!update.action";
        template = templateService.get(MessageTemplate.class, idx);
        return "post";
    }

    /**
     * <pre>
     * URL:admin/common/MessageTemplateAction!update.action
     * </pre>
     * <p/>
     * 更新邮件模板
     *
     * @return result
     */
    @Permission(code = "06010305", name = "更新邮件短信模板", supportedCode = "010304")
    public String update() {
        Message message = templateService.modify(template);
        if (message.hasErrorMessage()) {
            action = "admin/common/MessageTemplateAction!update.action";
            error = getText(message);
            return "post";
        } else {
            template = (MessageTemplate) message.getReturnData();
            notifyTrackService.saveMessageTemplate(template, OperatorAction.MODIFY);
            MessageTemplateHelper.add(template);
            saveLog(OperatorAction.MODIFY, LogType.INFO, template.getTitle(), message.getInfo(), null);
        }
        template = null;
        return "redirectList";
    }

    /**
     * <pre>
     * URL:admin/common/MessageTemplateAction!del.action
     * </pre>
     * <p/>
     * 删除邮件模板
     *
     * @return result
     */
    @Permission(code = "06010306", name = "删除邮件短信模板")
    public String del() {
        Message message = templateService.del(idx);
        if (message.hasErrorMessage()) {
            error = getText(message);
        } else {
            template = (MessageTemplate) message.getReturnData();
            notifyTrackService.saveMessageTemplate(template, OperatorAction.DELETE);
            MessageTemplateHelper.delete(template);
            saveLog(OperatorAction.DELETE, LogType.INFO, template.getTitle(), message.getInfo(), null);
        }
        return list();
    }

    /**
     * <pre>
     * URL:admin/common/MessageTemplateAction!getListDictionaryAjax.action
     * </pre>
     * 获取执行系统类型下的邮件短信模板列表
     */
    public String getListDictionaryAjax() {
        List<? extends Dictionary> list = ConfigManager.getInstance().getDictionaryList(MessageTemplateType.class, idx);
        AjaxSupport.sendSuccessTextOnly(null, list, "attr", "name");
        return NONE;
    }

    /**
     * <pre>
     * URL:admin/common/MessageTemplateAction!getMessageTemplateVariableAjax.action
     * </pre>
     * 获取执行系统类型下的邮件短信模板列表
     */
    public String getMessageTemplateVariableAjax() {
        List<Dictionary> list = MessageTemplateManager.getInstance().getMessageTemplateVariable(idx, Boolean.parseBoolean(name));
        AjaxSupport.sendSuccessTextOnly(null, list, "value", "name");
        return NONE;
    }

    private void saveLog(long operatorAction, long logType, String resourceId, String operationalContent, String errorInfo) {
        LogBean log = LoggerManager.getInstance().createLog(UserHelper.getCurrentLoginUser().getUsername(), FunctionClass.COMMON_PLATFORM, FunctionType.COMMON_PLATFORM_MESSAGE_TEMPLATE, SubsystemType.OTHER, operatorAction, logType, resourceId, operationalContent, errorInfo, 0L);
        if (logType == LogType.INFO) {
            LOGGER.infoDB(log);
        } else {
            LOGGER.errorDB(log);
        }
    }

    /** 系统类型列表 */
    public List<? extends Dictionary> getSystemTypeList() {
        return ConfigManager.getInstance().getDictionary(SystemType.class);
    }

    /** 有效标记列表 */
    public List<? extends Dictionary> getValidMarkList() {
        return ConfigManager.getInstance().getDictionary(ValidMark.class);
    }

    public List<? extends Dictionary> getTemplateTypeList() {
        if (template != null && template.getSystemType() > 0) {
            templateTypeList = ConfigManager.getInstance().getDictionaryList(MessageTemplateType.class, template.getSystemType());
        }
        return templateTypeList;
    }

    public MessageTemplate getTemplate() {
        return template;
    }

    public void setTemplate(MessageTemplate template) {
        this.template = template;
    }

    public List<MessageTemplate> getTemplateList() {
        return templateList;
    }

    public String getAction() {
        return action;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
