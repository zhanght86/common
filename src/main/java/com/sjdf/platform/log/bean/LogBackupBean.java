package com.sjdf.platform.log.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Create at 2015-09-16
 * 备份日志记录
 *
 * @author 王正伟
 */
@Entity
@Table(name = "p_log_backup")
public class LogBackupBean extends LogBaseBean {
    private static final long serialVersionUID = -8118365211609386484L;

    public LogBackupBean() {
    }

    public LogBackupBean(boolean isDBLog) {
        super(isDBLog);
    }

    public LogBackupBean(long subsystemType, String resourceId, String operationalContent, String errorInfo) {
        super(subsystemType, resourceId, operationalContent, errorInfo);
    }
}
