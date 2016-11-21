package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 王鸣忠
 * @category 数据库产品状态
 * @date 2014-7-17 19:25:25
 */
@Entity
@DiscriminatorValue("DATABASE_STATUS")
@BeanName(name = "数据库产品状态")
public class DatabaseStatus extends Dictionary {
    private static final long serialVersionUID = 8088399722708750329L;
    /** 运行中 */
    @BeanAttrInfo(value = "1", orderBy = 1, cnName = "运行中")
    public static final long RUN = 1;

    /** 暂停中 */
    @BeanAttrInfo(value = "2", orderBy = 2, cnName = "暂停中")
    public static final long PAUSE = 2;

    /** 处理中 */
    @BeanAttrInfo(value = "3", orderBy = 3, cnName = "处理中")
    public static final long PENDING = 3;

    /** 待开通 */
    @BeanAttrInfo(value = "4", orderBy = 4, cnName = "待开通")
    public static final long STAY_CREATE = 4;

    /** 未开通 */
    @BeanAttrInfo(value = "5", orderBy = 5, cnName = "未开通")
    public static final long NONE_CREATE = 5;

    /** 系统停止 */
    @BeanAttrInfo(value = "6", orderBy = 6, cnName = "系统停止")
    public static final long SYSTEM_STOP = 6;

    /** 管理员停止 */
    @BeanAttrInfo(value = "7", orderBy = 7, cnName = "管理员停止")
    public static final long ADMIN_STOP = 7;

    /** 过期停止 */
    @BeanAttrInfo(value = "8", orderBy = 8, cnName = "过期停止")
    public static final long STOP_EXPIRED = 8;

    /** 管理员停止-产品升级 */
    @BeanAttrInfo(value = "9", orderBy = 9, cnName = "管理员停止-产品升级")
    public static final long STOP_EXPIRED_UPGRADE = 9;

    /** 运行中-超大小 */
    @BeanAttrInfo(value = "10", orderBy = 10, cnName = "运行中-超大小")
    public static final long RUN_EXCEEDS_SIZE_LIMIT = 10;

    /** 迁移中 */
    @BeanAttrInfo(value = "11", orderBy = 11, cnName = "迁移中")
    public static final long MIGRATION = 11;
}
