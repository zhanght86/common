package com.sjdf.platform.common.conf;

import com.sjdf.platform.common.utils.AES;
import com.sjdf.platform.common.utils.PlatformUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;

/**
 * 支持AES解密的属性占位符
 * User: ketqi
 * Date: 2014-12-19 09:40
 */
public class DencryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private static Properties props;
    /** 是否使用加密敏感信息* */
    private boolean encrypt;
    /** 需要解密的属性列表 */
    private Set<String> encryptedProps = Collections.emptySet();

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        String value = propertyValue;
        if (encrypt && encryptedProps.contains(propertyName)) {
            String decryptedPropValue = AES.decrypt(value);
            if (PlatformUtils.hasText(decryptedPropValue)) {
                value = decryptedPropValue;
            }
        }
        return super.convertProperty(propertyName, value);
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        DencryptPropertyPlaceholderConfigurer.props = props;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

    public void setEncryptedProps(Set<String> encryptedProps) {
        this.encryptedProps = encryptedProps;
    }

    public static String getValue(String key) {
        return DencryptPropertyPlaceholderConfigurer.props.getProperty(key);
    }

    public static String getValue(String key, String defaultValue) {
        return DencryptPropertyPlaceholderConfigurer.props.getProperty(key, defaultValue);
    }
}
