package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.*;

/**
 * User: ketqi
 * Date: 2014-07-23 11:46
 * 执行步骤
 */
@Entity
@DiscriminatorValue("EXECUTE_STEP")
@BeanName(name = "执行步骤")
public class ExecuteStep extends Dictionary {
    private static final long serialVersionUID = 7002188605234840708L;

    /** 虚拟主机迁移-检测 */
    @BeanAttrInfo(orderBy = 1, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "检测")
    public static final long VHOST_MIGRATE_CHECK = 1;

    /** 虚拟主机迁移-同服务器升级 */
    @BeanAttrInfo(orderBy = 5, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "同服务器升级")
    public static final long VHOST_MIGRATE_SAME_SERVER_UPGRADE = 5;

    /** 虚拟主机迁移-发送打包请求 */
    @BeanAttrInfo(orderBy = 15, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "发送打包请求")
    public static final long VHOST_MIGRATE_SEND_WRAP_REQUEST = 15;

    /** 虚拟主机迁移-开设站点 */
    @BeanAttrInfo(orderBy = 20, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "开设站点")
    public static final long VHOST_MIGRATE_CREATE_SITE = 20;

    /** 虚拟主机迁移-创建mysql数据库 */
    @BeanAttrInfo(orderBy = 25, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "创建mysql数据库")
    public static final long VHOST_MIGRATE_CREATE_MYSQL_DATABASE = 25;

    /** 虚拟主机迁移-创建mssql数据库 */
    @BeanAttrInfo(orderBy = 30, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "创建mssql数据库")
    public static final long VHOST_MIGRATE_CREATE_MSSQL_DATABASE = 30;

    /** 虚拟主机迁移-迁移站点数据 */
    @BeanAttrInfo(orderBy = 35, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "迁移站点数据")
    public static final long VHOST_MIGRATE_SITE_DATA = 35;

    /** 虚拟主机迁移-迁移mysql数据库数据 */
    @BeanAttrInfo(orderBy = 40, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "迁移mysql数据库数据")
    public static final long VHOST_MIGRATE_MYSQL_DATABASE_DATA = 40;

    /** 虚拟主机迁移-迁移mssql数据库数据 */
    @BeanAttrInfo(orderBy = 45, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "迁移mssql数据库数据")
    public static final long VHOST_MIGRATE_MSSQL_DATABASE_DATA = 45;

    /** 虚拟主机迁移-校验站点数据 */
    @BeanAttrInfo(orderBy = 50, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "校验站点数据")
    public static final long VHOST_MIGRATE_CHECK_SITE_CODE = 50;

    /** 虚拟主机迁移-校验mysql数据库数据 */
    @BeanAttrInfo(orderBy = 55, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "校验mysql数据库数据")
    public static final long VHOST_MIGRATE_CHECK_MYSQL_DATABASE_CODE = 55;

    /** 虚拟主机迁移-校验mssql数据库数据 */
    @BeanAttrInfo(orderBy = 60, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "校验mssql数据库数据")
    public static final long VHOST_MIGRATE_CHECK_MSSQL_DATABASE_CODE = 60;

    /** 虚拟主机迁移-解压站点数据 */
    @BeanAttrInfo(orderBy = 65, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "解压站点数据")
    public static final long VHOST_MIGRATE_UNZIP_SITE = 65;

    /** 虚拟主机迁移-解压mysql数据库数据 */
    @BeanAttrInfo(orderBy = 70, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "解压mysql数据库数据")
    public static final long VHOST_MIGRATE_UNZIP_MYSQL_DATABASE = 70;

    /** 虚拟主机迁移-解压mssql数据库数据 */
    @BeanAttrInfo(orderBy = 75, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "解压mssql数据库数据")
    public static final long VHOST_MIGRATE_UNZIP_MSSQL_DATABASE = 75;

    /** 虚拟主机迁移-还原mysql数据库数据 */
    @BeanAttrInfo(orderBy = 80, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "还原mysql数据库数据")
    public static final long VHOST_MIGRATE_RESTORE_MYSQL_DATABASE = 80;

    /** 虚拟主机迁移-还原mssql数据库数据 */
    @BeanAttrInfo(orderBy = 85, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "还原mssql数据库数据")
    public static final long VHOST_MIGRATE_RESTORE_MSSQL_DATABASE = 85;

    /** 虚拟主机迁移-目标站点环境设置 */
    @BeanAttrInfo(orderBy = 90, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "目标站点环境设置")
    public static final long VHOST_MIGRATE_SET_ENV = 90;

    /** 虚拟主机迁移-确认完成 */
    @BeanAttrInfo(orderBy = 95, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "确认完成")
    public static final long VHOST_MIGRATE_CONFIRM_COMPLETE = 95;

    /** 虚拟主机迁移-确认取消 */
    @BeanAttrInfo(orderBy = 100, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "确认取消")
    public static final long VHOST_MIGRATE_CONFIRM_CANCEL = 100;

    /** 虚拟主机迁移-清理数据 */
    @BeanAttrInfo(orderBy = 105, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "清理数据")
    public static final long VHOST_MIGRATE_CLEAR_DATA = 105;

    /** 虚拟主机迁移-发送邮件短信通知 */
    @BeanAttrInfo(orderBy = 110, refClass = FunctionType.class, refAttr = FunctionType.VHOST_MIGRATE, cnName = "发送邮件短信通知")
    public static final long VHOST_MIGRATE_SEND_MAIN_SMS_NOTIFY = 110;

    public static List<ExecuteStep> getStepList() {
        List<ExecuteStep> list = new ArrayList<>();
        for (long attr : getStepSet()) {
            list.add(ConfigManager.getInstance().getDictionary(ExecuteStep.class, attr));
        }
        Collections.sort(list, Dictionary.COMPARATOR);
        return list;
    }

    public static Set<Long> getStepSet() {
        Set<Long> set = new HashSet<>();
        set.add(ExecuteStep.VHOST_MIGRATE_SEND_WRAP_REQUEST);
        set.add(ExecuteStep.VHOST_MIGRATE_CREATE_SITE);
        set.add(ExecuteStep.VHOST_MIGRATE_SITE_DATA);
        set.add(ExecuteStep.VHOST_MIGRATE_MYSQL_DATABASE_DATA);
        set.add(ExecuteStep.VHOST_MIGRATE_MSSQL_DATABASE_DATA);
        set.add(ExecuteStep.VHOST_MIGRATE_SET_ENV);
        set.add(ExecuteStep.VHOST_MIGRATE_CONFIRM_COMPLETE);
        set.add(ExecuteStep.VHOST_MIGRATE_CONFIRM_CANCEL);
        set.add(ExecuteStep.VHOST_MIGRATE_CLEAR_DATA);
        set.add(ExecuteStep.VHOST_MIGRATE_SEND_MAIN_SMS_NOTIFY);
        return set;
    }
}
