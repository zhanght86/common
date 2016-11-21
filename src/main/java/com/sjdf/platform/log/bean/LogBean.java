package com.sjdf.platform.log.bean;

import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Create at 2012-04-28
 * <p/>
 * 日志记录
 *
 * @author 王正伟
 */
@Entity
@Table(name = "p_log")
public class LogBean extends LogBaseBean {
    private static final long serialVersionUID = 8573633355431408273L;

    public LogBean() {
    }

    public LogBean(boolean isDBLog) {
        super(isDBLog);
    }

    public LogBean(long subsystemType, String resourceId, String operationalContent, String errorInfo) {
        super(subsystemType, resourceId, operationalContent, errorInfo);
    }

    public LogBackupBean toLogBackupBean() {
        LogBackupBean log = new LogBackupBean();
        try {
            BeanUtils.copyProperties(this, log, new String[]{"tmpErrorInfo", "tmpOperationalContent"});
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return log;
    }
}
