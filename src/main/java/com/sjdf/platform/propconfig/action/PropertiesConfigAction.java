package com.sjdf.platform.propconfig.action;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.annotations.Permission;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.common.vo.FixedVo;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.propconfig.bean.PropertiesConfigBean;
import com.sjdf.platform.propconfig.service.PropertiesConfigService;
import com.sjdf.platform.propconfig.vo.PropertiesConfigVo;

/**
 * 2015-08-28
 * 属性配置管理
 *
 * @author wangpeng
 */
public class PropertiesConfigAction extends BaseAction {
    private static final long serialVersionUID = -766155904156179951L;

    @Autowired
    private PropertiesConfigService configService;
    private SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(PropertiesConfigAction.class);
    private PropertiesConfigVo configVo;
    private PropertiesConfigBean config;
    private String param;

    public String list() {
        if (configVo != null && configVo.getAttr() > 0) {
            configVo = configService.findConfigVo(configVo.getAttr());
        } else {
            configService.initAllPropertiesConfig();
        }
        return "list";
    }

    @Permission(code = "01041312", name = "修改属性配置")
    public String update() {
        try {
            JSONArray arr = JSONArray.fromObject(param);
            configService.updateConfig(arr);
            AjaxSupport.sendSuccessText(getText("command.completed.successfully"));
        } catch (Exception e) {
            AjaxSupport.sendSuccessText(getText("command.completed.fail"));
            logger.error(e.getMessage(), e);
        }
        return NONE;
    }

    public String post() {
        config = configService.findConfigForDefault(idx);
        return "post";
    }

    public String updateDefault() {
        try {
            Message message = configService.updateDefaultConfig(config);
            if (message.hasErrorMessage()) {
                AjaxSupport.sendFailText(getText(message));
            } else {
                AjaxSupport.sendSuccessText(getText("command.completed.successfully"));
            }
        } catch (Exception e) {
            AjaxSupport.sendFailText(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return NONE;
    }

    public List<FixedVo> getConfigFixedVoList() {
        return configService.getFixedVoList();
    }

    public PropertiesConfigVo getConfigVo() {
        return configVo;
    }

    public void setConfigVo(PropertiesConfigVo configVo) {
        this.configVo = configVo;
    }

    public final PropertiesConfigBean getConfig() {
        return config;
    }

    public final void setConfig(PropertiesConfigBean config) {
        this.config = config;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
