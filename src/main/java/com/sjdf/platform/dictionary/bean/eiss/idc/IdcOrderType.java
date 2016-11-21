package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-03-16 10:51
 */
@Entity
@DiscriminatorValue("IDC_ORDER_TYPE")
@BeanName(name = "IDC订单类型")
public class IdcOrderType extends Dictionary {
    private static final long serialVersionUID = 511176554173832826L;

    @BeanAttrInfo(cnName = "购买")
    public static final long BUY = 1;

    @BeanAttrInfo(cnName = "变更")
    public static final long CHANGE = 5;

    @BeanAttrInfo(cnName = "续费")
    public static final long RENEW = 10;

    @BeanAttrInfo(cnName = "上架")
    public static final long HAS_UP_SHELF = 15;

    @BeanAttrInfo(cnName = "下架")
    public static final long HAS_OFF_SHELF = 20;
}
