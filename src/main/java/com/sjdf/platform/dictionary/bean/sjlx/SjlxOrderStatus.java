package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 订单状态
 * User: ketqi
 * Date: 2015-06-02 14:47
 */
@Entity
@DiscriminatorValue("SJLX_ORDER_STATUS")
@BeanName(name = "sjlx-订单状态")
public class SjlxOrderStatus extends Dictionary {
    private static final long serialVersionUID = 679788143973721247L;

    /** 待办理 */
    @BeanAttrInfo(cnName = "待办理")
    public static final long WAIT_HANDLE = 1;

    /** 办理中 */
    @BeanAttrInfo(cnName = "办理中")
    public static final long HANDLING = 100;
    /** 已办理 */
    @BeanAttrInfo(cnName = "已办理")
    public static final long HANDLED = 200;
}
