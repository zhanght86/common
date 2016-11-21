package com.sjdf.platform.dataTemplate.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dataTemplate.bean.Template;
import com.sjdf.platform.dataTemplate.helper.TemplateManager;
import com.sjdf.platform.dataTemplate.service.TemplateService;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.LoggerManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Create at 2012-05-29
 * 数据模板库管理Action
 *
 * @author 王正伟
 */
public class TemplateAction extends BaseAction {
    private static final long serialVersionUID = -5314486780612930213L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(TemplateAction.class);
    @Autowired
    private TemplateService templateService;
    private List<Template> templateList;
    private Template template;

    /**
     * /api/common/TemplateAction!list.action
     * <p/>
     * 模板列表
     */
    public String list() {
        templateList = templateService.list(template, page);
        return "list";
    }

    /**
     * /api/common/TemplateAction!addOrUpdate.action
     *
     * @return 添加或更新模板
     */
    public String addOrUpdate() {
        // 验证
        if (template == null) {
            printErrorXml("模板不能为空");
            return NONE;
        }
        if (!StringUtils.hasText(template.getMemberNumber())) {
            printErrorXml("会员编号不能为空");
            return NONE;
        }
        if (template.getType() <= 0) {
            printErrorXml("模板类型不能为空");
            return NONE;
        } else {
            TemplateType templateType = ConfigManager.getInstance().getDictionary(TemplateType.class, template.getType());
            if (templateType == null) {
                printErrorXml("指定的模板类型不存在");
                return NONE;
            }
        }
        if (template.getSystemType() <= 0) {
            printErrorXml("系统类型不能为空");
            return NONE;
        } else {
            SystemType systemType = ConfigManager.getInstance().getDictionary(SystemType.class, template.getSystemType());
            if (systemType == null) {
                printErrorXml("指定的系统类型不存在");
                return NONE;
            }
        }
        if (!StringUtils.hasText(template.getSystemName())) {
            printErrorXml("模板系统名称不能为空");
            return NONE;
        }

        // 处理数据
        Map<String, String[]> parameters = ServletActionContext.getRequest().getParameterMap();
        Message message = templateService.saveOrUpdate(template, parameters);
        if (!message.hasErrorMessage()) {
            template = (Template) message.getReturnData();
            saveLog(template.getMemberNumber(), OperatorAction.ADD, LogType.INFO, template.getMemberNumber(), message.getInfo(), null);
        }
        printWrite("status:0");
        return NONE;
    }

    /**
     * @return 删除指定的模板
     */
    public String del() {
        Message message = templateService.del(idx);
        if (!message.hasErrorMessage()) {
            template = (Template) message.getReturnData();
            saveLog(template.getMemberNumber(), OperatorAction.ADD, LogType.INFO, template.getMemberNumber(), message.getInfo(), null);
        }
        printWrite("status:0");
        return NONE;
    }

    /**
     * /api/common/TemplateAction!get.action
     *
     * @return 查询并返回模板
     */
    public String get() {
        if (template == null) {
            printErrorXml("模板不能为空");
            return NONE;
        }

        if (!(template.getId() != 0 || StringUtils.hasText(template.getMemberNumber()))) {
            printErrorXml("id或会员编号不能为空");
            return NONE;
        }

        List<Template> list = templateService.list(template, null);
        Collections.sort(list, new Comparator<Template>() {
            @Override
            public int compare(Template o1, Template o2) {
                String name1 = o1.getSystemName();
                String name2 = o2.getSystemName();
                if (PlatformUtils.hasText(name1) && PlatformUtils.hasText(name2)) {
                    return name1.compareTo(name2);
                }
                return 0;
            }
        });
        printWrite(TemplateManager.parse(list));
        return NONE;
    }

    private void saveLog(String operator, long operatorAction, long logType, String resourceId, String operationalContent, String errorInfo) {
        LogBean log = LoggerManager.getInstance().createLog(operator, FunctionClass.COMMON_PLATFORM, FunctionType.COMMON_PLATFORM_TEMPLATE, SubsystemType.OTHER, operatorAction, logType, resourceId, operationalContent, errorInfo, 0L);
        if (logType == LogType.INFO) {
            LOGGER.infoDB(log);
        } else {
            LOGGER.errorDB(log);
        }
    }

    public List<Template> getTemplateList() {
        return templateList;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
