package com.sjdf.platform.common.conf;

import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Create at 2012-4-23
 * 配置文件读取辅助器
 *
 * @author 王正伟
 */
public abstract class ProfileProvider {
    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(ProfileProvider.class);
    private static final Properties CONFIG_PROPERTIES = new Properties();

    /** 取指定属性的值 */
    public static String getValue(String name) {
        return CONFIG_PROPERTIES.getProperty(name);
    }

    static {
        try (InputStream configInputStream = ProfileProvider.class.getResourceAsStream("/platform_common_config.properties")) {
            if (configInputStream != null) {
                CONFIG_PROPERTIES.load(configInputStream);
            }
        } catch (IOException e) {
            logger.error("加载配置文件失败", e);
            e.printStackTrace();
        }
    }

    /**
     * 访问common平台的域名
     *
     * @return 访问common平台的域名
     */
    public static String getCommonPlatformAccessDomain() {
        return getValue(ProfileMapHelper.PLATFORM_COMMON_51WEB_COM_DOMAIN);
    }
}
