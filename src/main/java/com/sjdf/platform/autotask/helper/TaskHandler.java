package com.sjdf.platform.autotask.helper;

import com.sjdf.platform.autotask.vo.RequestVo;

import java.util.LinkedList;

/**
 * Create at 2012-11-07
 * 任务处理器
 *
 * @author ketqi
 */
public final class TaskHandler {
    private static LinkedList<RequestVo> cacheList;

    private TaskHandler() {
        cacheList = new LinkedList<>();
    }

    public synchronized void add(RequestVo taskVo) {
        cacheList.add(taskVo);
    }
}
