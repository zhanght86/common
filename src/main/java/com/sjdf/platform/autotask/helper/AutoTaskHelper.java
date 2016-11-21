package com.sjdf.platform.autotask.helper;

import com.sjdf.platform.autotask.BaseAutoTask;
import com.sjdf.platform.dictionary.bean.AutoTaskType;

import java.util.*;
import java.util.Map.Entry;

/**
 * Create at 2012-02-14
 * 自动任务辅助
 *
 * @author 王正伟
 */
public final class AutoTaskHelper {
    /** 所有自动任务列表;key:clazz,value:autoTaskType参考AtuoTaskType */
    private static final Map<Class<? extends BaseAutoTask>, AutoTaskType> ALL_AUTO_TASK_MAP = new HashMap<>();
    /** 正在执行的任务列表 */
    private static final Map<Class<? extends BaseAutoTask>, BaseAutoTask> EXECUTING_TASK_MAP = new HashMap<>();
    /** 已排序的自动任务列表 */
    private static List<Map.Entry<Class<? extends BaseAutoTask>, AutoTaskType>> sortList = null;

    private AutoTaskHelper() {
    }

    /** 获取job的名称 */
    public static String getJobName(Class<? extends BaseAutoTask> clazz) {
        return clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1, clazz.getSimpleName().length()) + "Job";
    }

    /** 获取所有的自动任务 */
    public static Map<Class<? extends BaseAutoTask>, AutoTaskType> getAllAutoTaskMap() {
        return Collections.unmodifiableMap(ALL_AUTO_TASK_MAP);
    }

    /** 添加自动任务 */
    public static void addAutoTask4All(Class<? extends BaseAutoTask> autoTaskClass, AutoTaskType autoTaskType) {
        synchronized (ALL_AUTO_TASK_MAP) {
            AutoTaskHelper.ALL_AUTO_TASK_MAP.put(autoTaskClass, autoTaskType);
        }
    }

    /** 获取正在执行的任务列表 */
    public static Map<Class<? extends BaseAutoTask>, BaseAutoTask> getExecutingJobMap() {
        return Collections.unmodifiableMap(EXECUTING_TASK_MAP);
    }

    /**
     * 判断指定的自动任务类型是否在运行
     *
     * @param clazz 自动任务类型
     */
    public static boolean inExecutJobMap(Class<? extends BaseAutoTask> clazz) {
        return EXECUTING_TASK_MAP.containsKey(clazz);
    }

    /** 移除中断或完成的自动任务 */
    public static void removeExecutedTask(Class<? extends BaseAutoTask> autoTaskClass) {
        synchronized (EXECUTING_TASK_MAP) {
            EXECUTING_TASK_MAP.remove(autoTaskClass);
        }
    }

    /** 添加正在执行的自动任务 */
    public static void addExecutingJob(BaseAutoTask autoTask) {
        synchronized (EXECUTING_TASK_MAP) {
            AutoTaskHelper.EXECUTING_TASK_MAP.put(autoTask.getClass(), autoTask);
        }
    }

    /** 返回已排序的自动任务列表 */
    public static synchronized List<Map.Entry<Class<? extends BaseAutoTask>, AutoTaskType>> getSortedAutoTaskList() {
        if (sortList == null) {
            sortList = new ArrayList<>(ALL_AUTO_TASK_MAP.entrySet());
            Collections.sort(sortList, new Comparator<Map.Entry<Class<? extends BaseAutoTask>, AutoTaskType>>() {
                @Override
                public int compare(Entry<Class<? extends BaseAutoTask>, AutoTaskType> o1, Entry<Class<? extends BaseAutoTask>, AutoTaskType> o2) {
                    return (int) (o1.getValue().getOrderBy() - o2.getValue().getOrderBy());
                }
            });
        }
        return sortList;
    }
}
