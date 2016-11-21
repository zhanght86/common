package com.sjdf.platform.rbac.helper;

import com.sjdf.platform.common.helper.ApplicationContextManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

/**
 * User: ketqi
 * Date: 2013-04-15 15:16
 * Action辅助器
 */
public abstract class ActionUtils {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(ActionUtils.class);

    /** 根据配置的className取得当前请求的类 */
    public static Class<?> getClassByConfigClassName(String configClassName) {
        Class<?> clazz;
        try {
            clazz = Class.forName(configClassName);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            try {
                clazz = ApplicationContextManager.getApplicationContext().getType(configClassName);
            } catch (Exception ex) {
                clazz = null;
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return clazz;
    }
}
