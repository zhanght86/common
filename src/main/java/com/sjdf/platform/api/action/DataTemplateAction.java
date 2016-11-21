package com.sjdf.platform.api.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.dataTemplate.bean.Template;
import com.sjdf.platform.dataTemplate.helper.TemplateManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.util.List;

/**
 * User: ketqi
 * Date: 2013-07-26 09:11
 * 数据模板API接口
 */
public class DataTemplateAction extends BaseAction {
    private static final long serialVersionUID = 2628889341008792630L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(DataTemplateAction.class);
    private Template template;

    /**
     * <pre>
     *     other/common/platform/DataTemplateAction!get.action
     * </pre>
     * 获取指定模板详情
     */
    public String get() {
        if (idx > 0) {
            template = new Template();
            template.setId(idx);

            List<Template> templateList = null;
            try {
                templateList = TemplateManager.getInstance().get(template);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }

            if (templateList != null && !templateList.isEmpty() && (template = templateList.get(0)) != null) {
                AjaxSupport.sendSuccessText(null, template.getData());
                return NONE;
            }
        }

        AjaxSupport.sendFailText(getText("message.template.not.exist"));
        return NONE;
    }

    /**
     * <pre>
     *     other/common/platform/DataTemplateAction!getFixedVo.action
     * </pre>
     * 获取所有模板id和name列表
     */
    public String getFixedVo() {
        List<Template> resultTemplateList = null;
        try {
            resultTemplateList = TemplateManager.getInstance().getAll(template);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        if (resultTemplateList == null || resultTemplateList.isEmpty()) {
            AjaxSupport.sendSuccessText("list is empty");
        } else {
            AjaxSupport.sendSuccessTextOnly(null, resultTemplateList, "id", "name");
        }
        return NONE;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
