package com.sjdf.platform.message.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.dictionary.bean.CompanyClass;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.ValidMark;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.dictionary.bean.common.SendStatus;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 邮件短信base action
 * User: ketqi
 * Date: 2015-07-08 16:10
 */
public class MessageBaseAction extends BaseAction {
    private static final long serialVersionUID = 7414991849155117508L;
    @Autowired
    protected MessageService messageService;

    /** 合作伙伴 */
    public List<? extends Dictionary> getCompanyClassList() {
        return ConfigManager.getInstance().getDictionary(CompanyClass.class);
    }

    /** 公司类型 */
    public List<? extends Dictionary> getSystemTypeList() {
        return ConfigManager.getInstance().getDictionary(SystemType.class);
    }

    /** 发送状态 */
    public List<? extends Dictionary> getSendStatusList() {
        return ConfigManager.getInstance().getDictionary(SendStatus.class);
    }

    /** 有效状态 */
    public List<? extends Dictionary> getValidMarkList() {
        return ConfigManager.getInstance().getDictionary(ValidMark.class);
    }

    /** 消息类型 */
    public List<? extends Dictionary> getMessageTypeList() {
        return ConfigManager.getInstance().getDictionary(MessageType.class);
    }
}
