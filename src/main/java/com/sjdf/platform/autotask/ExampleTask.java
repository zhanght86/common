package com.sjdf.platform.autotask;

import com.sjdf.platform.autotask.annotations.AutoTask;
import com.sjdf.platform.dictionary.bean.AutoTaskType;
import org.quartz.JobExecutionContext;

/**
 * Create at 2012-07-20
 * 自动任务示例
 *
 * @author 王正伟
 */
@AutoTask(type = AutoTaskType.HKIDC_EMAIL_RECEIVE)
public class ExampleTask extends BaseAutoTask {

    @Override
    protected void executeTask(JobExecutionContext context) throws Exception {
        // Auto-generated method stub
    }
}
