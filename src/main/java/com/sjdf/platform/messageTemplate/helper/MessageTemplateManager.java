package com.sjdf.platform.messageTemplate.helper;

import com.sjdf.platform.common.init.InitManager;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.MessageTemplateVariable;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.messageTemplate.bean.MessageTemplate;
import com.sjdf.platform.net.HttpSocket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 邮件或短信模板库管理器
 * Create at 2012-08-02
 *
 * @author 王正伟
 */
public final class MessageTemplateManager extends InitManager {
    private static MessageTemplateManager instance = new MessageTemplateManager();
    // 邮件模板结尾
    private static final Set<Long> EMAIL_FOOTER_LIST = new HashSet<>(5);

    static {
        // 邮件模板结尾
        EMAIL_FOOTER_LIST.add(MessageTemplateVariable.EMAIL_FOOTER_1);
        EMAIL_FOOTER_LIST.add(MessageTemplateVariable.EMAIL_FOOTER_2);
        EMAIL_FOOTER_LIST.add(MessageTemplateVariable.EMAIL_FOOTER_3);
        EMAIL_FOOTER_LIST.add(MessageTemplateVariable.EMAIL_FOOTER_4);
        EMAIL_FOOTER_LIST.add(MessageTemplateVariable.EMAIL_FOOTER_5);
    }

    private MessageTemplateManager() {
        super();
        // 初始化邮件或短信模板库管理器数据
        // 获取xml数据
        String xml = getData();
        destroyData();

        List<MessageTemplate> list = MessageTemplateHelper.parse(xml);
        MessageTemplateHelper.init(list);
    }

    @Override
    protected String getXmlData() {
        String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.MESSAGE_TEMPLATE_GET_URL);
        logger.info("url:" + url);

        HttpSocket httpSocket = new HttpSocket();
        httpSocket.setUrl(url);

        try {
            httpSocket.doGet();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return httpSocket.getResponseData();
    }

    public static MessageTemplateManager getInstance() {
        return instance;
    }

    /**
     * 从缓存中获取指定的邮件短信模板
     *
     * @param systemType 系统类型
     */
    public List<MessageTemplate> get(long systemType) {
        return get(systemType, 0);
    }

    /**
     * 从缓存中获取指定的邮件短信模板
     *
     * @param messageTemplateType 模板类型
     */
    public MessageTemplate getSingle(long messageTemplateType) {
        return MessageTemplateHelper.get(messageTemplateType);
    }

    /**
     * 从缓存中获取指定的邮件短信模板
     *
     * @param systemType          系统类型
     * @param messageTemplateType 模板类型
     */
    public MessageTemplate getSingle(long systemType, long messageTemplateType) {
        if (messageTemplateType > 0) {
            return getSingle(messageTemplateType);
        }

        List<MessageTemplate> list = get(systemType, messageTemplateType);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * 从缓存中获取指定的邮件短信模板
     *
     * @param systemType          系统类型
     * @param messageTemplateType 模板类型
     */
    public List<MessageTemplate> get(long systemType, long messageTemplateType) {
        return MessageTemplateHelper.get(systemType, messageTemplateType);
    }

    /**
     * 获取邮件或短信模板标签变量
     *
     * @param messageTemplateType 模板类型
     * @param isMobile            是否是手机
     */
    public List<Dictionary> getMessageTemplateVariable(long messageTemplateType, boolean isMobile) {
        return MessageTemplateHelper.getMessageTemplateVariable(messageTemplateType, isMobile);
    }

    /**
     * @param messageTemplateType 获取邮件模板标签变量
     */
    public List<Dictionary> getEmailMessageTemplateVariable(long messageTemplateType) {
        return MessageTemplateHelper.getMessageTemplateVariable(messageTemplateType, false);
    }

    /**
     * 邮件模板结尾
     */
    public static Set<Long> getSystemEmailFooterList() {
        return EMAIL_FOOTER_LIST;
    }

    /**
     * 邮件模板结尾
     */
    public static List<Dictionary> getSystemEmailFooterDicList() {
        Set<Long> longSet = EMAIL_FOOTER_LIST;
        List<Dictionary> list = new ArrayList<>();
        for (long idx : longSet) {
            list.add(ConfigManager.getInstance().getDictionary(MessageTemplateVariable.class, idx));
        }
        return list;
    }
}
