package com.sjdf.platform.autotask.bean;

import com.sjdf.platform.common.bean.BaseBean;

import javax.persistence.ManyToOne;

/**
 * User: ketqi Date: 2013-05-14 10:34
 * 自动任务具体参数信息
 */
public class AutoTaskInfo extends BaseBean {
    private static final long serialVersionUID = -1767865467109911873L;
    /** 自动任务所在服务器 */
    @ManyToOne
    private AutoTaskServer server;
    /** 自动任务class的全路径 */
    private String clazz;
    /**
     * 自动任务类型
     *
     * @see com.sjdf.platform.dictionary.bean.AutoTaskType
     */
    private long autoTaskType;
    /**
     * 执行状态
     *
     * @see com.sjdf.platform.dictionary.bean.ExecuteStatus
     */
    private long status;

    public AutoTaskServer getServer() {
        return server;
    }

    public void setServer(AutoTaskServer server) {
        this.server = server;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public long getAutoTaskType() {
        return autoTaskType;
    }

    public void setAutoTaskType(long autoTaskType) {
        this.autoTaskType = autoTaskType;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
