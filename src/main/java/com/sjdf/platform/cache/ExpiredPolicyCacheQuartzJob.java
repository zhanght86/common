package com.sjdf.platform.cache;

import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Create at 2014年3月14日 上午11:14:37
 * 无效数据清理
 *
 * @author KETQI
 */
public class ExpiredPolicyCacheQuartzJob implements Job {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(ExpiredPolicyCacheQuartzJob.class);

    /** 过期数据清理逻辑 */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            ExpiredPolicyCache<?> cache = (ExpiredPolicyCache<?>) context.getMergedJobDataMap().get(ExpiredPolicyCache.class.getSimpleName());
            cache.clearExpiredData();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
    }
}