package com.sjdf.platform.dictionary.bean.eiss.chost;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * create in 2016年7月11日
 * @category 主服务器监控状态
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("CHOST_MONITOR_STATUS")
@BeanName(name = "主服务器监控状态")
public class ChostMonitorStatus extends Dictionary {

    private static final long serialVersionUID = 1L;

    @BeanAttrInfo(orderBy = 1, cnName = "维护中")
    public static final long MAINTENANCE = 1L;

    @BeanAttrInfo(orderBy = 2, cnName = "正常")
    public static final long NORMAL = 2L;

    @BeanAttrInfo(orderBy = 3, cnName = "停用中")
    public static final long STOP = 3L;
}
