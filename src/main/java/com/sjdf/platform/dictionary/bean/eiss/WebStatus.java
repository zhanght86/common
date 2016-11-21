package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-07-16 10:49
 * 虚拟主机web状态
 */
@Entity
@DiscriminatorValue("WEB_STATUS")
@BeanName(name = "虚拟主机web状态")
public class WebStatus extends Dictionary {

    @BeanAttrInfo(cnName = "运行中")
    public static final long RUN = 1;

    @BeanAttrInfo(cnName = "暂停中")
    public static final long PAUSE = 2;

    @BeanAttrInfo(cnName = "处理中")
    public static final long PENDING = 3;

    @BeanAttrInfo(cnName = "待开通")
    public static final long STAY_CREATE = 4;

    @BeanAttrInfo(cnName = "未开通")
    public static final long NONE_CREATE = 5;

    @BeanAttrInfo(cnName = "系统停止-超过系统流量限制")
    public static final long SYSTEM_STOP_MORE_THAN_FLOW_LIMIT = 6;

    @BeanAttrInfo(cnName = "管理员停止")
    public static final long ADMIN_STOP = 7;

    @BeanAttrInfo(cnName = "管理员停止-非法信息关闭")
    public static final long ADMIN_STOP_ILLEGAL = 8;

    @BeanAttrInfo(cnName = "管理员停止-网站被攻击")
    public static final long ADMIN_STOP_ATTACK = 9;

    @BeanAttrInfo(cnName = "管理员停止-网站流量过大")
    public static final long ADMIN_STOP_TRAFFIC_TOO_LARGE = 10;

    @BeanAttrInfo(cnName = "管理员停止-网站程序异常")
    public static final long ADMIN_STOP_PROGRAM_EXCEPTION = 11;

    @BeanAttrInfo(cnName = "管理员停止-超过系统CPU限制")
    public static final long ADMIN_STOP_CPU_TOO_HIGH = 12;

    @BeanAttrInfo(cnName = "过期停止")
    public static final long STOP_EXPIRED = 13;

    @BeanAttrInfo(cnName = "运行中-超大小")
    public static final long RUN_ABOVE_SIZE = 14;

    @BeanAttrInfo(cnName = "迁移中")
    public static final long MIGRATION = 15;
}
