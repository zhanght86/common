package com.sjdf.platform.dictionary.bean.monitor;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 监控任务类型
 * User: ketqi
 * Date: 2015-04-30 10:38
 */
@Entity
@DiscriminatorValue("MONITOR_TASK_TYPE")
@BeanName(name = "监控任务类型")
public class MonitorTaskType extends Dictionary {
    private static final long serialVersionUID = 5322733946861338437L;

    /** CDN服务器监控 */
    @BeanAttrInfo(cnName = "CDN服务器监控")
    public static final long CDN_SERVER_MONITOR = 1;

    /** 域名访问监控 */
    @BeanAttrInfo(cnName = "域名访问监控")
    public static final long DOMAIN_ACCESS_MONITOR = 5;
}
