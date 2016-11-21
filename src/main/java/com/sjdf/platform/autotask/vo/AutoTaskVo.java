package com.sjdf.platform.autotask.vo;

/**
 * Create at 2013年8月8日 下午4:44:55
 * 自动任务vo
 *
 * @author KETQI
 */
public class AutoTaskVo {
    /** 自动任务名称;即类的simpleName */
    private String name;
    /** 自动任务类型;参考AutoTaskType */
    private long type;
    /** 自动任务运行状态;停止,运行中 */
    private boolean status;

    public AutoTaskVo() {
    }

    public AutoTaskVo(String name, long type, boolean status) {
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
