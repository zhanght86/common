package com.sjdf.platform.message.cache;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.utils.clazz.LocalClassFinder;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.message.api.EmailApi;
import com.sjdf.platform.message.api.SmsApi;
import com.sjdf.platform.message.bean.SendApiConfig;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 消息发送接口实现缓存
 * User: ketqi
 * Date: 2015-07-03 11:08
 */
public final class MessageApiImplCache {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(MessageApiImplCache.class);
    private static final String PACKAGE_PATH = "com.sjdf.platform.message.api.impl";
    private final ConcurrentMap<String, MessageApiInfo<? extends SmsApi>> smsCache;
    private final ConcurrentMap<String, MessageApiInfo<? extends EmailApi>> emailCache;

    private MessageApiImplCache() {
        smsCache = new ConcurrentHashMap<>();
        emailCache = new ConcurrentHashMap<>();

        LocalClassFinder finder = new LocalClassFinder(PACKAGE_PATH);
        //sms
        List<Class<? extends SmsApi>> smsClassList = finder.findSubClass(SmsApi.class);
        for (Class<? extends SmsApi> clazz : smsClassList) {
            BeanName beanName = clazz.getAnnotation(BeanName.class);
            if (beanName != null) {
                smsCache.put(clazz.getSimpleName(), new MessageApiInfo<>(beanName.name(), clazz));
            }
        }

        //email
        List<Class<? extends EmailApi>> emailClassList = finder.findSubClass(EmailApi.class);
        for (Class<? extends EmailApi> clazz : emailClassList) {
            BeanName beanName = clazz.getAnnotation(BeanName.class);
            if (beanName != null) {
                emailCache.put(clazz.getSimpleName(), new MessageApiInfo<>(beanName.name(), clazz));
            }
        }
    }

    /** 单例 */
    private static class SingleHolder {
        private static final MessageApiImplCache INSTANCE = new MessageApiImplCache();
    }

    public static MessageApiImplCache getInstance() {
        return SingleHolder.INSTANCE;
    }

    /**
     * 获取接口中文名称
     *
     * @param messageType 消息类型
     * @return 名称
     */
    public String name(long messageType, String sendApiImpl) {
        MessageApiInfo<?> info = null;
        if (messageType == MessageType.SMS) {
            info = smsCache.get(sendApiImpl);
        } else if (messageType == MessageType.EMAIL) {
            info = emailCache.get(sendApiImpl);
        }

        if (info != null) {
            return info.getName();
        }
        return "";
    }

    /** 短信接口名称列表 */
    public Map<String, String> smsNameMap() {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, MessageApiInfo<? extends SmsApi>> entry : smsCache.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getName());
        }
        return map;
    }

    /** 邮件接口名称列表 */
    public Map<String, String> emailNameMap() {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, MessageApiInfo<? extends EmailApi>> entry : emailCache.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getName());
        }
        return map;
    }

    /*** 获取所有接口名称列表 */
    public Map<String, String> allNameMap() {
        Map<String, String> map = new HashMap<>();
        map.putAll(smsNameMap());
        map.putAll(emailNameMap());
        return map;
    }

    /**
     * 创建短信接口实例
     *
     * @param config 短信接口配置
     * @return 短信接口实例
     */
    public SmsApi createSmsApi(SendApiConfig config) {
        if (config == null || !PlatformUtils.hasText(config.getSendApiImpl()) || config.getMessageType() != MessageType.SMS) {
            return null;
        }

        return (SmsApi) createObject(config);
    }

    /**
     * 创建邮件接口实例
     *
     * @param config 邮件接口配置
     * @return 邮件接口实例
     */
    public EmailApi createEmailApi(SendApiConfig config) {
        if (config == null || !PlatformUtils.hasText(config.getSendApiImpl()) || config.getMessageType() != MessageType.EMAIL) {
            return null;
        }

        return (EmailApi) createObject(config);
    }


    /**
     * 创建接口实例
     *
     * @param config 接口配置
     * @return 实例
     */
    private Object createObject(SendApiConfig config) {
        MessageApiInfo<?> apiInfo = null;
        if (config.getMessageType() == MessageType.SMS) {
            apiInfo = smsCache.get(config.getSendApiImpl());
        } else if (config.getMessageType() == MessageType.EMAIL) {
            apiInfo = emailCache.get(config.getSendApiImpl());
        }
        if (apiInfo == null) {
            return null;
        }

        Class<?> clazz = apiInfo.getClazz();
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(SendApiConfig.class);
            return constructor.newInstance(config);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("create send api instance fail", e);
        }
        return null;
    }

    /**
     * 接口信息
     */
    public static class MessageApiInfo<T> {
        /** 名称 */
        private String name;
        /** 接口实例类型 */
        private Class<T> clazz;

        public MessageApiInfo(String name, Class<T> clazz) {
            this.name = name;
            this.clazz = clazz;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Class<T> getClazz() {
            return clazz;
        }

        public void setClazz(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            MessageApiInfo<?> that = (MessageApiInfo<?>) o;

            return clazz.equals(that.clazz);

        }

        @Override
        public int hashCode() {
            return clazz.hashCode();
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
