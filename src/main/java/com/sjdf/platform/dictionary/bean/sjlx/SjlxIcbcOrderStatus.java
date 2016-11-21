package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 工商订单状态(100一个状态段)
 * User: ketqi
 * Date: 2015-06-06 10:44
 */
@Entity
@DiscriminatorValue("SJLX_ICBC_ORDER_STATUS")
@BeanName(name = "sjlx-工商订单状态")
public class SjlxIcbcOrderStatus extends Dictionary {
    private static final long serialVersionUID = 8343897801211351431L;

    /** 待办理 */
    @BeanAttrInfo(cnName = "待办理")
    public static final long WAIT_HANDLE = 1;

    /** 办理中 */
    @BeanAttrInfo(cnName = "办理中")
    public static final long HANDLING = 100;

    /** 办理中-核名 */
    @BeanAttrInfo(cnName = "核名")
    public static final long HANDLING_VERIFY = 101;

    /** 办理中-执照办理 */
    @BeanAttrInfo(cnName = "执照办理")
    public static final long HANDLING_LICENSE = 105;

    /** 办理中-税务办理 */
    @BeanAttrInfo(cnName = "税务办理")
    public static final long HANDLING_TAX = 110;

    /** 办理中-代码证 */
    @BeanAttrInfo(cnName = "代码证")
    public static final long HANDLING_ORG_CERTIFICATE = 115;

    /** 办理中-印章刻制 */
    @BeanAttrInfo(cnName = "印章刻制")
    public static final long HANDLING_SEAL = 120;
    /** 已办理 */
    @BeanAttrInfo(cnName = "已办理")
    public static final long HANDLED = 200;

    /** 将状态转换为3个状态(待办理,办理中,已办理) */
    public long convert() {
        long attr = getAttr();
        if (attr > HANDLING && attr < HANDLED) {
            attr = HANDLING;
        }
        return attr;
    }
}
