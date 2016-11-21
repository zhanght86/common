package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: ketqi
 * Date: 2014-07-28 09:48
 * 线程执行器
 */
public class ExecutorPoolService {
    private static ExecutorService executorService;

    private ExecutorPoolService() {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * CommonPlatformConstant.LENGTH_2);
    }

    private static ExecutorPoolService instance = new ExecutorPoolService();

    public static ExecutorPoolService getInstance() {
        return instance;
    }

    public ExecutorService getExecutor() {
        return executorService;
    }

    public void execute(Runnable command) {
        executorService.execute(command);
    }
}
