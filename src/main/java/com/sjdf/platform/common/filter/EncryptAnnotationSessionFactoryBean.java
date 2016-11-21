package com.sjdf.platform.common.filter;

import com.sjdf.platform.common.utils.AES;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import java.util.Properties;

/**
 * User: ketqi
 * Date: 2013-07-18 10:32
 * <p/>
 * 解密hibernate中的敏感信息, 采用AES加密;如连接地址,用户名和密码
 */
public class EncryptAnnotationSessionFactoryBean extends AnnotationSessionFactoryBean {
    //是否使用加密敏感信息
    private boolean encrypt;

    @Override
    protected void postProcessMappings(Configuration config) throws HibernateException {
        if (encrypt) {
            Properties properties = config.getProperties();

            //hibernate.connection.url
            properties.setProperty(Environment.URL, AES.decrypt(properties.getProperty(Environment.URL)));

            //hibernate.connection.username
            properties.setProperty(Environment.USER, AES.decrypt(properties.getProperty(Environment.USER)));

            //hibernate.connection.password
            properties.setProperty(Environment.PASS, AES.decrypt(properties.getProperty(Environment.PASS)));
        }
        super.postProcessMappings(config);
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }
}
