package com.sjdf.platform.message.action;

import com.sjdf.platform.common.annotations.Permission;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.message.bean.SendApiConfig;
import com.sjdf.platform.message.cache.MessageApiImplCache;
import com.sjdf.platform.rbac.helper.UserHelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 接口配置Action
 * User: ketqi
 * Date: 2015-07-01 17:31
 */
public class ConfigAction extends MessageBaseAction {
    private static final long serialVersionUID = -2206757761404615713L;
    private SendApiConfig config;
    private List<SendApiConfig> configList;
    private Map<String, String> messageApiImplMap;

    /**
     * admin/common/config!list.action
     * 接口配置列表
     *
     * @return result
     */
    @Permission(code = "06011301", name = "信息发送接口管理")
    public String list() {
        configList = messageService.configList(config, page);
        messageApiImplMap = MessageApiImplCache.getInstance().allNameMap();
        return "list";
    }

    /**
     * admin/common/config!edit.action
     * 接口配置编辑
     *
     * @return result
     */
    @Permission(code = "06011302", name = "信息发送接口编辑")
    public String edit() {
        if (idx > 0) {
            config = messageService.get(SendApiConfig.class, idx);
            if (config != null) {
                if (config.getMessageType() == MessageType.SMS) {
                    messageApiImplMap = MessageApiImplCache.getInstance().smsNameMap();
                } else {
                    messageApiImplMap = MessageApiImplCache.getInstance().emailNameMap();
                }
            }
        }
        return "edit";
    }

    /**
     * admin/common/config!post.action
     * 接口配置添加或者更新
     *
     * @return result
     */
    @Permission(code = "06011303", name = "信息发送接口添加或更新")
    public String post() {
        Message message = messageService.saveOrUpdateConfig(config, UserHelper.getCurrentLoginUser().getName());
        if (message.hasErrorMessage()) {
            AjaxSupport.sendFailText(getText(message));
        } else {
            AjaxSupport.sendSuccessText(SUCCESS);
        }
        return NONE;
    }

    /**
     * admin/common/config!del.action
     * 接口配置删除
     *
     * @return result
     */
    @Permission(code = "06011304", name = "信息发送接口删除")
    public String del() {
        Message message = messageService.delConfig(idx, UserHelper.getCurrentLoginUser().getName());
        if (message.hasErrorMessage()) {
            AjaxSupport.sendFailText(getText(message));
        } else {
            AjaxSupport.sendSuccessText(SUCCESS);
        }
        return NONE;
    }

    /**
     * admin/common/config!sendApiImpl.action
     * 根据消息类型获取接口实例列表
     *
     * @return result
     */
    public String sendApiImpl() {
        if (idx == MessageType.SMS) {
            messageApiImplMap = MessageApiImplCache.getInstance().smsNameMap();
        } else {
            messageApiImplMap = MessageApiImplCache.getInstance().emailNameMap();
        }
        AjaxSupport.sendSuccessText(null, messageApiImplMap);
        return NONE;
    }

    public SendApiConfig getConfig() {
        return config;
    }

    public void setConfig(SendApiConfig config) {
        this.config = config;
    }

    public List<SendApiConfig> getConfigList() {
        return configList;
    }

    public Map<String, String> getMessageApiImplMap() {
        if (messageApiImplMap == null) {
            messageApiImplMap = Collections.emptyMap();
        }
        return messageApiImplMap;
    }
}
