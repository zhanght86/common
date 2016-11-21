package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-08-14 09:58
 */
@Entity
@DiscriminatorValue("IDC_STATUS")
@BeanName(name = "IDC状态")
public class IdcStatus extends Dictionary {
    private static final long serialVersionUID = -8636729048806284220L;

    /** 未开通 */
    @BeanAttrInfo(cnName = "未开通")
    public static final long NOT_OPENED = 0;

    /** 已开通 */
    @BeanAttrInfo(cnName = "已开通")
    public static final long HAS_OPENED = 1;

    /** 暂停中 */
    @BeanAttrInfo(cnName = "暂停中")
    public static final long PAUSE = 2;

    /** 处理中 */
    @BeanAttrInfo(cnName = "处理中")
    public static final long PROCESSING = 3;

    /** 已被完全删除 */
    @BeanAttrInfo(cnName = "已被完全删除")
    public static final long HAS_ALL_REMOVE = 4;

    /** 被停止 */
    @BeanAttrInfo(cnName = "被停止")
    public static final long BE_STOPPED = 5;

    /** 待开通 */
    @BeanAttrInfo(cnName = "待开通")
    public static final long STAY_OPEN = 6;

    /** 到期停止 */
    @BeanAttrInfo(cnName = "到期停止")
    public static final long DUE_TO_STOP = 7;

    /** 超容量停止 */
    @BeanAttrInfo(cnName = "超容量停止")
    public static final long SUPER_CAPACITY_TO_STOP = 8;

    /** 超流量停止 */
    @BeanAttrInfo(cnName = "超流量停止")
    public static final long SUPER_TRAFFIC_TO_STOP = 9;

    /** 因备案停止 */
    @BeanAttrInfo(cnName = "因备案停止")
    public static final long FOR_RECORD_TO_STOP = 10;

    /** 仅删除web站点 */
    @BeanAttrInfo(cnName = "仅删除web站点")
    public static final long ONLY_REMOVE_WEB = 17;
}
