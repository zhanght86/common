package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-06-14
 *
 * @author Chen Mohan
 * @category 管局拨测处理状态
 */
@Entity
@DiscriminatorValue("MII_DIALS_STATUS")
@BeanName(name = "管局拨测处理状态")
public class MiiDialsStatus extends Dictionary {

    private static final long serialVersionUID = 7093952007771476698L;

    @BeanAttrInfo(orderBy = 101, cnName = "未处理")
    public static final long UN_HANDLED = 1;

    @BeanAttrInfo(orderBy = 102, cnName = "未处理-已通知客户")
    public static final long UN_HANDLED_NOTIFY = 2;

    @BeanAttrInfo(orderBy = 103, cnName = "未处理-已解绑（可恢复）")
    public static final long UN_HANDLED_ALLOW_RECOVERY_STOP_BINDINGS = 3;

    @BeanAttrInfo(orderBy = 104, cnName = "未处理-已解绑（不可恢复）")
    public static final long UN_HANDLED_DENY_RECOVERY_STOP_BINDINGS = 4;

    @BeanAttrInfo(orderBy = 201, cnName = "已处理-变更备案")
    public static final long HANDLED_CHANGE_RECORD_INFO = 5;

    @BeanAttrInfo(orderBy = 202, cnName = "已处理-取消接入")
    public static final long HANDLED_CANCEL_ACCESS = 6;

    @BeanAttrInfo(orderBy = 203, cnName = "已处理-已在本站接入")
    public static final long HANDLED_ALREADY_ACCESS = 7;

    @BeanAttrInfo(orderBy = 204, cnName = "已处理-非我司产品")
    public static final long HANDLED_NOT_US_PRODUCT = 8;

    @BeanAttrInfo(orderBy = 999, cnName = "处理失败")
    public static final long FAILED = 9;

    @BeanAttrInfo(orderBy = 205, cnName = "已处理-我司拨测信息准确")
    public static final long HANDLED_INFO_SUCCESS = 10;

    @BeanAttrInfo(orderBy = 100, cnName = "所有未处理")
    public static final long UN_HANDLED_ALL = 11;

    @BeanAttrInfo(orderBy = 105, cnName = "未处理-非国内产品")
    public static final long UN_HANDLED_NOT_NEED_RECORD = 12;

    @BeanAttrInfo(orderBy = 106, cnName = "未处理-备案信息变更中")
    public static final long UN_HANDLED_CHANGE_RECORD_INFO_ING = 13;
}
