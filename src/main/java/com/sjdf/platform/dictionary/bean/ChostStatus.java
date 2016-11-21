package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-22
 *
 * @author 王正伟
 * @category 云主机状态
 */
@Entity
@DiscriminatorValue("CHOST_STATUS")
@BeanName(name = "云主机状态")
public class ChostStatus extends Dictionary {
    private static final long serialVersionUID = 8731922379720868254L;
    /** 运行中 */
    @BeanAttrInfo(value = "1", cnName = "运行中")
    public static final long RUN = 1;
    /** 暂停中 */
    @BeanAttrInfo(value = "2", cnName = "暂停中")
    public static final long PAUSE = 2;
    /** 迁移中 */
    @BeanAttrInfo(value = "3", cnName = "迁移中")
    public static final long MOVING = 3;
    /** 安装中 */
    @BeanAttrInfo(value = "4", cnName = "安装中")
    public static final long INSTALLING = 4;
    /** 已关机 */
    @BeanAttrInfo(value = "5", cnName = "已关机")
    public static final long POWEROFF = 5;
    /** 系统停止-超过系统流量限制 */
    @BeanAttrInfo(value = "6", cnName = "系统停止-超过系统流量限制")
    public static final long SYSTEM_STOP_MORE_THAN_FLOW_LIMIT = 6;
    /** 管理员停止 */
    @BeanAttrInfo(value = "7", cnName = "管理员停止")
    public static final long ADMIN_STOP = 7;
    /** 管理员停止-未备案 */
    @BeanAttrInfo(value = "8", cnName = "管理员停止-未备案")
    public static final long ADMIN_STOP_NONE_RECORD = 8;
    /** 管理员停止-超速 */
    @BeanAttrInfo(value = "9", cnName = "管理员停止-超速")
    public static final long ADMIN_STOP_BANDWIDTH_TOO_LARGE = 9;
    /** 管理员停止-非法信息 */
    @BeanAttrInfo(value = "10", cnName = "管理员停止-非法信息")
    public static final long ADMIN_STOP_ILLEGAL = 10;
    /** 管理员停止-攻击 */
    @BeanAttrInfo(value = "11", cnName = "管理员停止-攻击")
    public static final long ADMIN_STOP_ATTACK = 11;
    /** 过期停止 */
    @BeanAttrInfo(value = "12", cnName = "过期停止")
    public static final long STOP_EXPIRED = 12;

}
