package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 弹性购买资源类型
 */
@Entity
@DiscriminatorValue("FLEXIBLE_BUY_RES_TYPE")
@BeanName(name = "弹性购买资源类型")
public class FlexibleBuyResType extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = 8295707658016944787L;

    /** 带宽 */
    @BeanAttrInfo(orderBy = 1, cnName = "带宽", enName = "M")
    public static final long BANDWIDTH = 1L;

    /** CPU */
    @BeanAttrInfo(orderBy = 2, cnName = "CPU", enName = "核")
    public static final long CPU = 2L;

    /** 内存 */
    @BeanAttrInfo(orderBy = 3, cnName = "内存", enName = "G")
    public static final long MEMORY = 3L;

    /** 硬盘 */
    @BeanAttrInfo(orderBy = 4, cnName = "硬盘", enName = "G")
    public static final long DISK = 4L;

    /** 域名个数 */
    @BeanAttrInfo(orderBy = 5, cnName = "域名个数", enName = "个", value = "")
    public static final long DOMAIN_NUM = 5L;

    /** IP个数 */
    @BeanAttrInfo(orderBy = 6, cnName = "IP个数", enName = "个", value = "")
    public static final long IP_NUM = 6L;

}
