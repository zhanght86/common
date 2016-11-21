package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2012-11-13
 *
 * @author Hunk
 * @category 管理员备注类型
 */
@Entity
@DiscriminatorValue("CHOST_ADMIN_NOTES_TYPE")
@BeanName(name = "管理员备注类型")
public class AdminNotesType extends Dictionary {

    private static final long serialVersionUID = 3330899808650009267L;
    /** 业务备注 */
    @BeanAttrInfo(value = "1", cnName = "业务备注")
    public static final long BUSINESS_NOTES = 1;
    /** 业务到技术的交接备注 */
    @BeanAttrInfo(value = "2", cnName = "业务到技术的交接备注")
    public static final long BUSINESS_TO_TECHNOLOGY_NOTES = 2;
    /** 购买第2天 */
    @BeanAttrInfo(value = "3", cnName = "购买第2天")
    public static final long BUYED_TRACK_2 = 3;
    /** 购买第3天 */
    @BeanAttrInfo(value = "4", cnName = "购买第3天")
    public static final long BUYED_TRACK_3 = 4;
    /** 购买7天 */
    @BeanAttrInfo(value = "5", cnName = "购买7天")
    public static final long BUYED_TRACK_7 = 5;
    /** 购买15天 */
    @BeanAttrInfo(value = "6", cnName = "购买15天")
    public static final long BUYED_TRACK_15 = 6;
    /** 购买25天 */
    @BeanAttrInfo(value = "7", cnName = "购买25天")
    public static final long BUYED_TRACK_25 = 7;
    /** 购买30天 */
    @BeanAttrInfo(value = "8", cnName = "购买30天")
    public static final long BUYED_TRACK_30 = 8;
    /** 到期前跟踪 */
    @BeanAttrInfo(value = "9", cnName = "到期前跟踪")
    public static final long EXPIRE_TRACK = 9;
    /** 到期前20天 */
    @BeanAttrInfo(value = "10", cnName = "到期前20天")
    public static final long EXPIRE_TRACK_20 = 10;
    /** 到期前5天 */
    @BeanAttrInfo(value = "11", cnName = "到期前5天")
    public static final long EXPIRE_TRACK_5 = 11;
    /** 续费备注 */
    @BeanAttrInfo(value = "12", cnName = "续费备注")
    public static final long RENEW_TRACK_NOTES = 12;
    /** 解析相关处理备注 */
    @BeanAttrInfo(value = "13", cnName = "解析相关处理备注")
    public static final long ANALYTIC_NOTES = 13;
    /** 其他备注 */
    @BeanAttrInfo(value = "99", cnName = "其他备注")
    public static final long OTHER_NOTES = 99;
}
