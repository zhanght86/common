package com.sjdf.platform.autotask.helper;

import com.sjdf.platform.autotask.BaseAutoTask;
import com.sjdf.platform.autotask.annotations.AutoTask;
import com.sjdf.platform.common.helper.ApplicationContextManager;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.dictionary.bean.AutoTaskType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.scheduling.quartz.JobDetailBean;

import java.util.Map;

/**
 * Create at 2012-02-14
 * 自动任务管理器
 *
 * @author ketqi
 */
public class AutoTaskController implements Ordered {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AutoTaskController.class);

    @SuppressWarnings("unchecked")
    public AutoTaskController() {
        ApplicationContext applicationContext = ApplicationContextManager.getApplicationContext();

        // 将自动任务上面的注解添加到自动任务辅助器AutoTaskHelper中
        String[] names = applicationContext.getBeanNamesForType(JobDetailBean.class);
        for (String name : names) {
            JobDetailBean jobDetail = (JobDetailBean) applicationContext.getBean(name);
            Class<?> temp = jobDetail.getJobClass();
            if (BaseAutoTask.class.isAssignableFrom(temp)) {
                AutoTask autoTask = temp.getAnnotation(AutoTask.class);
                AutoTaskType autoTaskType = ConfigManager.getInstance().getDictionary(AutoTaskType.class, autoTask.type());
                AutoTaskHelper.addAutoTask4All((Class<? extends BaseAutoTask>) temp, autoTaskType);
            }
        }
    }

    /**
     * <pre>
     * 如自动任务com.sjdf.platform.autotask.ExampleTask则 @param classSimpleName:ExampleTask
     * spring自动任务配置文件中的Trigger的名称必须为exampleTaskTrigger(classSimpleName首字母小写+Trigger)
     * </pre>
     * 启动某个自动任务
     *
     * @param classSimpleName 自动任务的classSimpleName
     */
    public static Message start(String classSimpleName) {
        Map<Class<? extends BaseAutoTask>, AutoTaskType> allAutoTaskMap = AutoTaskHelper.getAllAutoTaskMap();
        if (allAutoTaskMap.isEmpty()) {
            return Message.createMessage("auto.task.init.fail");
        }

        for (Class<? extends BaseAutoTask> clazz : allAutoTaskMap.keySet()) {
            if (clazz.getSimpleName().equals(classSimpleName)) {
                JobDetailBean jobDetail = (JobDetailBean) ApplicationContextManager.getBean(AutoTaskHelper.getJobName(clazz));
                SchedulerFactory schedulerFactory = new StdSchedulerFactory();
                try {
                    Scheduler scheduler = schedulerFactory.getScheduler();
                    SimpleTrigger simpleTrigger = new SimpleTrigger(classSimpleName + "Trigger", classSimpleName + "TriggerGroup");
                    scheduler.scheduleJob(jobDetail, simpleTrigger);
                    scheduler.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage(), e);
                    return Message.createMessage("auto.task.start.fail", classSimpleName, e.getMessage());
                }
                return Message.createEmptyMessage();
            }
        }

        return Message.createMessage("auto.task.start.fail", classSimpleName, " ");
    }

    /** 停止某个自动任务 */
    public static Message stop(String classSimpleName) {
        Map<Class<? extends BaseAutoTask>, BaseAutoTask> executingTaskMap = AutoTaskHelper.getExecutingJobMap();
        if (executingTaskMap.isEmpty()) {
            return Message.createMessage("auto.task.not.exist.executing");
        }

        for (Class<? extends BaseAutoTask> clazz : executingTaskMap.keySet()) {
            if (clazz.getSimpleName().equals(classSimpleName)) {
                BaseAutoTask baseAutoTask = executingTaskMap.get(clazz);
                baseAutoTask.setInterrupt(true);
                return Message.createEmptyMessage();
            }
        }
        return Message.createMessage("auto.task.stop.fail", classSimpleName, " ");
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
