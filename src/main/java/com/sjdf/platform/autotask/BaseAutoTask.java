package com.sjdf.platform.autotask;

import com.sjdf.platform.autotask.helper.AutoTaskHelper;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 自动任务基类
 * <pre>
 * // 自动任务使用示例
 * // 继承BaseAutoTask
 * &#064;AutoTask(type = AutoTaskType.HKIDC_EMAIL_RECEIVE)
 * public class ExampleTask extends BaseAutoTask {
 *
 *  // 实现其抽象方法
 *  &#064;Override
 *  protected void executeTask(JobExecutionContext context) throws Exception {
 *      // Auto-generated method stub
 *    }
 * }
 *
 * //spring配置文件格式;
 * //注意,bean的name命名规范:类名的首字母小写+类名的其余字符+Job后缀
 * &ltbean name="exampleTaskJob" class="org.springframework.scheduling.quartz.JobDetailBean"&gt
 *  &ltproperty name="jobClass" value="com.sjdf.platform.common.autotask.ExampleTask" /&gt
 *  &ltproperty name="jobDataAsMap"&gt
 *      &ltmap&gt
 *          &ltentry key="autoTaskService" value-ref="autoTaskServiceImpl" /&gt
 *      &lt/map&gt
 *  &lt/property&gt
 * &lt/bean&gt
 *
 *
 * &ltbean id="exampleTaskTrigger" class="org.springframework.scheduling.quartz.CronTriggerBean"&gt
 *  &ltproperty name="jobDetail" ref="exampleTaskJob" /&gt
 *  &ltproperty name="cronExpression" value="0 13 1 * * ?" /&gt
 * &lt/bean&gt
 *
 * &lt!-- SchedulerFactory --&gt
 *  &ltbean id="scheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean"&gt
 *  &ltproperty name="triggers"&gt
 *  &ltlist&gt
 *  &ltref bean="exampleTaskTrigger" /&gt
 *  &lt/list&gt
 *  &lt/property&gt
 *  &lt/bean&gt
 * </pre>
 * Create at 2012-07-20
 *
 * @author 王正伟
 */
public abstract class BaseAutoTask extends QuartzJobBean {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(BaseAutoTask.class);
    /** 是否中断当前操作 */
    private volatile boolean isInterrupt = false;

    public boolean isInterrupt() {
        return isInterrupt;
    }

    /**
     * @param isInterrupt 设置中断标志位
     */
    public void setInterrupt(boolean isInterrupt) {
        this.isInterrupt = isInterrupt;
        if (isInterrupt) {// 如果是中断,则移除任务
            AutoTaskHelper.removeExecutedTask(getClass());
        } else {// 如果不是中断则添加任务
            AutoTaskHelper.addExecutingJob(this);
        }
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            executeTask(context);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
    }

    protected abstract void executeTask(JobExecutionContext context) throws Exception;
}

