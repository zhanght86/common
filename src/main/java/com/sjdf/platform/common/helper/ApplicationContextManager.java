package com.sjdf.platform.common.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Create at 2012-04-09
 * 提供spring的ApplicationContext
 *
 * @author 王正伟
 */
@Component("applicationContextManager")
public class ApplicationContextManager implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextManager.applicationContext = applicationContext;
    }

    public static boolean isApplicationContextInjected() {
        if (applicationContext == null) {
            return false;
        }
        boolean configurable = applicationContext instanceof ConfigurableApplicationContext;
        return !(configurable && !((ConfigurableApplicationContext) applicationContext).isActive());
    }

    public static Object getBean(String id) {
        return applicationContext.getBean(id);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static boolean isInited() {
        return applicationContext != null;
    }
}
