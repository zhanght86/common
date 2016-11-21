package com.sjdf.platform.cache;

import com.sjdf.platform.CommonPlatformConstant;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <pre>
 * quartz 时间配置规则
 * 格式: [秒] [分] [小时] [日] [月] [周] [年]
 *
 *  序号 说明 是否必填    允许填写的值 允许的通配符
 *  1   秒   是   0-59     , - * /
 *  2   分   是   0-59     , - * /
 *  3  小时   是   0-23     , - * /
 *  4   日   是   1-31     , - * ? / L W
 *  5   月   是   1-12 or JAN-DEC      , - * /
 *  6   周   是   1-7 or SUN-SAT   , - * ? / L #
 *  7   年   否   empty 或 1970-2099   , - * /
 * 通配符说明:
 * 表示所有值. 例如:在分的字段上设置 "*",表示每一分钟都会触发。
 * ? 表示不指定值。使用的场景为不需要关心当前设置这个字段的值。例如:要在每月的10号触发一个操作，但不关心是周几，所以需要周位置的那个字段设置为"?" 具体设置为 0 0 0 10 * ?
 * - 表示区间。例如 在小时上设置 "10-12",表示 10,11,12点都会触发。
 * , 表示指定多个值，例如在周字段上设置 "MON,WED,FRI" 表示周一，周三和周五触发
 * / 用于递增触发。如在秒上面设置"5/15" 表示从5秒开始，每增15秒触发(5,20,35,50)。在月字段上设置'1/3'所示每月1号开始，每隔三天触发一次。
 * L 表示最后的意思。在日字段设置上，表示当月的最后一天(依据当前月份，如果是二月还会依据是否是润年[leap]), 在周字段上表示星期六，相当于"7"或"SAT"。如果在"L"前加上数字，则表示该数据的最后一个。例如在周字段上设置"6L"这样的格式,则表示“本月最后一个星期五"
 * W 表示离指定日期的最近那个工作日(周一至周五). 例如在日字段上设置"15W"，表示离每月15号最近的那个工作日触发。如果15号正好是周六，则找最近的周五(14号)触发, 如果15号是周未，则找最近的下周一(16号)触发.如果15号正好在工作日(周一至周五)，则就在该天触发。如果指定格式为 "1W",它则表示每月1号往后最近的工作日触发。如果1号正是周六，则将在3号下周一触发。(注，"W"前只能设置具体的数字,不允许区间"-").
 * 小提示
 * 'L'和 'W'可以一组合使用。如果在日字段上设置"LW",则表示在本月的最后一个工作日触发(一般指发工资 )
 *
 * # 序号(表示每月的第几个周几)，例如在周字段上设置"6#3"表示在每月的第三个周六.注意如果指定"#5",正好第五周没有周六，则不会触发该配置(用在母亲节和父亲节再合适不过了)
 * 小提示
 * 周字段的设置，若使用英文字母是不区分大小写的 MON 与mon相同.
 *
 * 常用示例:
 * 0 0 12 * * ?    每天12点触发
 * 0 15 10 ? * *   每天10点15分触发
 * 0 15 10 * * ?   每天10点15分触发
 * 0 15 10 * * ? * 每天10点15分触发
 * 0 15 10 * * ? 2005  2005年每天10点15分触发
 * 0 * 14 * * ?    每天下午的 2点到2点59分每分触发
 * 0 0/5 14 * * ?  每天下午的 2点到2点59分(整点开始，每隔5分触发)
 * 0 0/5 14,18 * * ?
 *
 * 每天下午的 2点到2点59分(整点开始，每隔5分触发)
 * 每天下午的 18点到18点59分(整点开始，每隔5分触发)
 * 0 0-5 14 * * ?  每天下午的 2点到2点05分每分触发
 * 0 10,44 14 ? 3 WED  3月分每周三下午的 2点10分和2点44分触发
 * 0 15 10 ? * MON-FRI 从周一到周五每天上午的10点15分触发
 * 0 15 10 15 * ?  每月15号上午10点15分触发
 * 0 15 10 L * ?   每月最后一天的10点15分触发
 * 0 15 10 ? * 6L  每月最后一周的星期五的10点15分触发
 * 0 15 10 ? * 6L 2002-2005    从2002年到2005年每月最后一周的星期五的10点15分触发
 * 0 15 10 ? * 6#3 每月的第三周的星期五开始触发
 * 0 0 12 1/5 * ?  每月的第一个中午开始每隔5天触发一次
 * 0 11 11 11 11 ? 每年的11月11号 11点11分触发(光棍节)
 * </pre>
 * <p/>
 * 带有过期策略的缓存
 * Create at 2014年3月14日 上午9:20:09
 *
 * @author KETQI
 */
public class ExpiredPolicyCache<T> {
    private static final String JOB_GROUP_NAME = "EXPIRED_POLICY_CACHE";
    private static final String TRIGGER_GROUP_NAME = "EXPIRED_POLICY_CACHE";

    private ConcurrentMap<Serializable, Entry<T>> cacheMap;
    private String cacheName;
    /** 过期时间,单位millisecond */
    private long expiredTime;
    /** 定时任务表达式,参考quartz说明文档 */
    private String cronExpression;
    /** 标识过期数据清理定时任务是否被触发 */
    private volatile boolean triggered = false;

    public ExpiredPolicyCache(String cacheName, long expiredTime, String cronExpression) {
        this.cacheName = cacheName;
        this.expiredTime = expiredTime;
        this.cronExpression = cronExpression;
        cacheMap = new ConcurrentHashMap<>();
    }

    /**
     * 添加缓存数据
     *
     * @param key   主键
     * @param value 待缓存的数据
     * @return oldValue
     */
    public synchronized T put(Serializable key, T value) {
        if (key != null && value != null) {
            Entry<T> entry = cacheMap.put(key, new Entry<>(value));
            if (entry != null) {
                return entry.value;
            }
        }
        return null;
    }

    /**
     * 添加缓存数据
     *
     * @param key   主键
     * @param value 待缓存的数据
     * @return oldValue
     */
    public T putIfAbsent(Serializable key, T value) {
        if (key != null && value != null) {
            Entry<T> entry = cacheMap.putIfAbsent(key, new Entry<>(value));
            if (entry != null) {
                return entry.value;
            }
        }
        return null;
    }

    /**
     * 获取缓存中的数据
     *
     * @param key 主键
     * @return 缓存数据
     */
    public T get(Serializable key) {
        if (key != null) {
            Entry<T> entry = cacheMap.get(key);
            if (entry != null) {
                entry.currentTime = System.currentTimeMillis();
                return entry.value;
            }
        }
        return null;
    }

    /**
     * 清除缓存中的数据
     *
     * @param key 主键
     * @return 缓存数据
     */
    public T remove(Serializable key) {
        if (key != null) {
            Entry<T> entry = cacheMap.remove(key);
            if (entry != null) {
                entry.currentTime = System.currentTimeMillis();
                return entry.value;
            }
        }
        return null;
    }

    /**
     * 获取所有缓存中的数据
     *
     * @param needUpdateTime 是否更新时间
     * @return 缓存数据列表
     */
    public List<T> getAll(boolean needUpdateTime) {
        List<T> list = new ArrayList<>(cacheMap.size());
        for (Entry<T> entry : cacheMap.values()) {
            if (needUpdateTime) {
                entry.currentTime = System.currentTimeMillis();
            }
            list.add(entry.value);
        }
        return list;
    }

    /**
     * 获取所有缓存中的数据
     *
     * @return 缓存数据列表
     */
    public List<T> getAll() {
        return getAll(true);
    }

    /** 清理过期数据 */
    synchronized void clearExpiredData() {
        for (Map.Entry<Serializable, Entry<T>> mapEntry : cacheMap.entrySet()) {
            long currentTime = mapEntry.getValue().currentTime;
            if (new Date().getTime() >= (currentTime + expiredTime)) {
                cacheMap.remove(mapEntry.getKey());
            }
        }
    }

    /** 数据过期清理定时任务 */
    public synchronized void trigger() {
        if (triggered) {
            return;
        }
        triggered = true;

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            // 任务名，任务组，任务执行类
            JobDetail jobDetail = new JobDetail(cacheName, JOB_GROUP_NAME, ExpiredPolicyCacheQuartzJob.class);
            jobDetail.getJobDataMap().put(ExpiredPolicyCache.class.getSimpleName(), this);
            // 触发器;触发器名,触发器组
            CronTrigger trigger = new CronTrigger(cacheName, TRIGGER_GROUP_NAME);
            // 触发器时间设定
            trigger.setCronExpression(cronExpression);
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    static class Entry<T> {
        // 使用该value的最近时间
        long currentTime;
        // 缓存数据
        T value;

        public Entry(T value) {
            this.currentTime = System.currentTimeMillis();
            this.value = value;
        }

        public final long getCurrentTime() {
            return currentTime;
        }

        public final T getValue() {
            return value;
        }

        public final T setValue(T newValue) {
            T oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public int hashCode() {
            final int prime = CommonPlatformConstant.LENGTH_31;
            int result = 1;
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Entry<?> other = (Entry<?>) obj;
            if (value == null) {
                if (other.value != null) {
                    return false;
                }
            } else if (!value.equals(other.value)) {
                return false;
            }
            return true;
        }
    }
}
