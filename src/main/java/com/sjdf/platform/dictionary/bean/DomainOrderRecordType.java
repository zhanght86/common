package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-2-21
 *
 * @author 丁yan
 * @category 域名记录类型
 */
@Entity
@DiscriminatorValue("DOMAIN_ORDER_RECORD_TYPE")
@BeanName(name = "域名记录类型")
public class DomainOrderRecordType extends Dictionary {
    private static final long serialVersionUID = -6912383566623619320L;

    /** 购买 */
    @BeanAttrInfo(value = "1", cnName = "购买")
    public static final long BUY_RECORD = 1;

    /** 续费 */
    @BeanAttrInfo(value = "2", cnName = "续费")
    public static final long RENEW_RECORD = 2;

    /** 转入 */
    @BeanAttrInfo(value = "3", cnName = "转入")
    public static final long TRANSFER_IN_RECORD = 3;

    /** 转出 */
    @BeanAttrInfo(value = "4", cnName = "转出")
    public static final long TRANSFER_OUT_RECORD = 4;

    /** 过户 */
    @BeanAttrInfo(value = "5", cnName = "过户")
    public static final long TRANSFER_RECORD = 5;

    /** 赎回 */
    @BeanAttrInfo(value = "6", cnName = "赎回")
    public static final long RANSOM_RECORD = 6;
}
