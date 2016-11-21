package com.sjdf.platform.common.conf;

import com.opensymphony.xwork2.ActionContext;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

/**
 * User: ketqi Date: 2013-04-15 15:49
 * 配置加载器, 此配置加载器用于加载根下的config.properties，此配置中配置有整个框架的一些配置信息
 */
public class ConfigLoader {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(ConfigLoader.class);
    private Properties configProperties = new Properties();

    public ConfigLoader() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
    }

    /** 价格缓存实例 */
    private static class SingletonHolder {
        private static final ConfigLoader INSTANCE = new ConfigLoader();
    }

    public static ConfigLoader getInstance() {
        return ConfigLoader.SingletonHolder.INSTANCE;
    }

    /** 取指定属性的值,如果为空,则默认返回为空字符串 */
    public String getValue(String name) {
        String v = configProperties.getProperty(name);
        return v == null ? "" : v;
    }

    /** 取locale设置 */
    public static Locale getLocale() {
        if (ActionContext.getContext() == null) {
            return Locale.CHINA;// 默认中国
        }
        return ActionContext.getContext().getLocale();
    }

    public void init() throws IOException {
        String[] configLocations = "classpath*:config.properties,classpath*:platform_common_config.properties,classpath*:conf/**/config.properties".split(",");
        ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();
        for (String configFileLocation : configLocations) {
            Resource[] resources = rpr.getResources(configFileLocation);
            for (Resource resource : resources) {
                loadProperty(resource.getURL());
            }
        }
    }

    private void loadProperty(URL url) throws IOException {
        try (InputStream inputStream = url.openStream()) {
            final Properties properties = new Properties();
            properties.load(inputStream);
            for (String key : properties.stringPropertyNames()) {
                String v = properties.getProperty(key);
                if (!PlatformUtils.hasText(v)) {
                    continue;
                }
                if (configProperties.containsKey(key)) {
                    String value = configProperties.getProperty(key);
                    if (PlatformUtils.hasText(value)) {
                        // 这里需要以,进行分隔
                        v += "," + value;
                    }
                }
                configProperties.setProperty(key, v);
            }
        }
    }
}
