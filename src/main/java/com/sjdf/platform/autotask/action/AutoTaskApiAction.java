package com.sjdf.platform.autotask.action;

import com.sjdf.platform.common.action.BaseAction;

/**
 * Create at 2012-11-07
 * 自动任务跟踪Action
 *
 * @author ketqi
 */
public class AutoTaskApiAction extends BaseAction {
    private static final long serialVersionUID = 6723549198973769498L;
    private String clazz;//待处理的自动任务的simpleClassName

    //启动
    public String start() {

        return NONE;
    }

    //停止
    public String stop() {

        return NONE;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
