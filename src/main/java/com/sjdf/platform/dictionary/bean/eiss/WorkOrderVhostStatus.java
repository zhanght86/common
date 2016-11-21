package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-08-22 16:15
 */
@Entity
@DiscriminatorValue("WORK_ORDER_VHOST_STATUS")
@BeanName(name = "工单虚拟主机状态")
public class WorkOrderVhostStatus extends Dictionary {
    private static final long serialVersionUID = 955852259897336501L;

    @BeanAttrInfo(cnName = "暂停中")
    public static final long PAUSE = WebStatus.PAUSE;

    @BeanAttrInfo(cnName = "过期停止")
    public static final long STOP_EXPIRED = WebStatus.STOP_EXPIRED;

    @BeanAttrInfo(cnName = "系统停止-超过系统流量限制")
    public static final long SYSTEM_STOP_MORE_THAN_FLOW_LIMIT = WebStatus.SYSTEM_STOP_MORE_THAN_FLOW_LIMIT;

    @BeanAttrInfo(cnName = "运行中-超大小")
    public static final long RUN_ABOVE_SIZE = WebStatus.RUN_ABOVE_SIZE;

    @BeanAttrInfo(cnName = "未开通")
    public static final long NONE_CREATE = WebStatus.NONE_CREATE;

    @BeanAttrInfo(cnName = "DNS在我司")
    public static final long DNS_OWN = 100L;

    @BeanAttrInfo(cnName = "能获取管理密码")
    public static final long CAN_QUERY_MANAGE_PWD = 101L;

    @BeanAttrInfo(cnName = "不能获取管理密码")
    public static final long CAN_NOT_QUERY_MANAGE_PWD = 102L;

    @BeanAttrInfo(cnName = "域名绑定已存在")
    public static final long DOMAIN_BINDED = 103L;

    @BeanAttrInfo(cnName = "管理员停止")
    public static final long ADMIN_STOP = WebStatus.ADMIN_STOP;

    @BeanAttrInfo(cnName = "支持下载")
    public static final long SUPPORT_DOWNLOAD = 104L;

    @BeanAttrInfo(cnName = "不支持下载")
    public static final long NOT_SUPPORT_DOWNLOAD = 105L;
}
