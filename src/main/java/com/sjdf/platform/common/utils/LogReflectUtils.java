package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.dictionary.bean.LogType;
import com.sjdf.platform.dictionary.bean.SubsystemType;
import com.sjdf.platform.log.bean.LogUser;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 2015年3月11日 下午2:08:45
 */
public class LogReflectUtils {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(LogReflectUtils.class);

    public static void saveNewLog(Object source) {
        com.sjdf.platform.log.bean.LogBean logBean;
        try {
            logBean = new com.sjdf.platform.log.bean.LogBean();
            LogUser logUser = new LogUser();
            logBean.setCreateTime(new Date());
            @SuppressWarnings("rawtypes")
            Class sourceClz = source.getClass();
            // 得到Class对象所表征的类的所有属性(包括私有属性)
            Field[] fields = sourceClz.getDeclaredFields();
            if (fields.length == 0) {
                fields = sourceClz.getSuperclass().getDeclaredFields();
            }
            Field[] parents = sourceClz.getSuperclass().getDeclaredFields();
            for (Field field : parents) {
                field.setAccessible(true);// 修改访问权限
                String fieldName = field.getName();
                String value = "";
                if ("long".equals(field.getType().getName())) {
                    value = "" + field.getLong(source);
                } else if ("java.lang.String".equals(field.getType().getName())) {
                    value = String.valueOf(field.get(source));
                }
                if ("createUser".equals(fieldName)) {
                    logUser.setLoginUser(value);
                }
            }
            for (Field field : fields) {
                field.setAccessible(true);// 修改访问权限
                String fieldName = field.getName();
                String value = "";
                if ("long".equals(field.getType().getName())) {
                    value = "" + field.getLong(source);
                } else if ("java.lang.String".equals(field.getType().getName())) {
                    value = String.valueOf(field.get(source));
                }
                if ("operatorType".equals(fieldName)) {
                    long s = Long.valueOf(value);
                    if (s == 1) {
                        logBean.setSubsystemType(SubsystemType.BUSINESS_BACKGROUND);
                    }
                    if (s == CommonPlatformConstant.LENGTH_2) {
                        logBean.setSubsystemType(SubsystemType.MANAGER_CENTER);
                    }
                    if (s == CommonPlatformConstant.LENGTH_3) {
                        logBean.setSubsystemType(SubsystemType.CONTROL_PANEL);
                    }
                    if (s == CommonPlatformConstant.LENGTH_4) {
                        logBean.setSubsystemType(SubsystemType.AUTO_TASK);
                    }
                    if (s == CommonPlatformConstant.LENGTH_5) {
                        logBean.setSubsystemType(SubsystemType.API);
                    }
                }
                if ("company".equals(fieldName)) {
                    logUser.setCompany(value);
                }
                if ("ipAddress".equals(fieldName)) {
                    logUser.setIpAddress(value);
                }
                if ("resourceClass".equals(fieldName)) {
                    logBean.setFunctionClass(Long.valueOf(value));
                }
                if ("resourceType".equals(fieldName)) {
                    logBean.setFunctionType(Long.valueOf(value));
                }
                if ("operateAction".equals(fieldName)) {
                    logBean.setOperatorAction(Long.valueOf(value));
                }
                if ("resourceId".equals(fieldName)) {
                    logBean.setResourceId(value);
                }
                if ("description".equals(fieldName)) {
                    logBean.setOperationalContent(value);
                }
                if ("logType".equals(fieldName)) {
                    logBean.setLogType(Long.valueOf(value));
                }
                if ("errorInfo".equals(fieldName)) {
                    logBean.setErrorInfo(value);
                }
            }
            logBean.setLogUser(logUser);
            if (logBean.getLogType() == LogType.INFO) {
                LOGGER.infoDB(logBean);
            } else if (logBean.getLogType() == LogType.ERROR) {
                LOGGER.errorDB(logBean);
            }
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
