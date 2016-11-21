package com.sjdf.platform.common.helper;

import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Create at 2013年8月14日 上午9:40:36
 * <p/>
 * 国际资源化辅助工具;默认是以message开始的文件
 *
 * @author KETQI
 */
public final class ResourceBundleHelper {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(ResourceBundleHelper.class);
    /** 国际资源化文件 */
    private ResourceBundle resourceBundle;

    private ResourceBundleHelper() {
        resourceBundle = ResourceBundle.getBundle("message", Locale.CHINA, getClass().getClassLoader());
    }

    /** 单例;达到lazy loading效果 */
    private static class SingletonHolder {
        private static final ResourceBundleHelper INSTANCE = new ResourceBundleHelper();
    }

    /**
     * 获取国际资源化辅助工具单例
     *
     * @return ResourceBundleHelper
     */
    public static ResourceBundleHelper getInstance() {
        return ResourceBundleHelper.SingletonHolder.INSTANCE;
    }

    /**
     * 资源国际化
     *
     * @param message 消息组件
     * @return 资源化字符串
     */
    public String getText(Message message) {
        Object[] temp = new Object[message.getParameterList().size()];
        String value;
        try {
            value = resourceBundle.getString(message.getErrorMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return message.getErrorMessage();
        }

        return MessageFormat.format(value, message.getParameterList().toArray(temp));
    }

    /**
     * 资源国际化
     *
     * @param key  职员标识
     * @param args 需要替换的值
     * @return 资源化字符串
     */
    public String getText(String key, Object... args) {
        String value;
        try {
            value = resourceBundle.getString(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return key;
        }
        return MessageFormat.format(value, args);
    }
}
