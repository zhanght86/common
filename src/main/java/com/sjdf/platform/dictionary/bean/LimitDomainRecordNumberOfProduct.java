package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 * @author frank
 * @date 2012-9-20 下午5:40:53
 * @category    【限制产品域名备案个数】配置库
 */
@Entity
@DiscriminatorValue("LIMIT_DOMAIN_RECORD_NUMBER_OF_PRODUCT")
@BeanName(name = "限制产品域名备案个数")
public class LimitDomainRecordNumberOfProduct extends Dictionary {

    private static final long serialVersionUID = 2882157632658724010L;

    /** 虚拟主机 */
    @BeanAttrInfo(orderBy = 1, cnName = "虚拟主机", value = "10")
    public static final long V_HOST = 1;

    /** VPS */
    @BeanAttrInfo(orderBy = 2, cnName = "VPS", value = "60")
    public static final long VPS = 2;

    /** IDC */
    @BeanAttrInfo(orderBy = 3, cnName = "IDC", value = "60")
    public static final long IDC = 3;

    /** 云主机 */
    @BeanAttrInfo(orderBy = 7, cnName = "云主机")
    public static final long CLOUD_HOST = 7;

}
