package com.sjdf.platform.dictionary.bean.hkidc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2014年4月15日 上午11:01:32
 *
 * @author KETQI
 * @category hkidc域名存在状态
 */
@Entity
@DiscriminatorValue("HKIDC_DOMAIN_EXIST_TYPE")
@BeanName(name = "hkidc-域名存在状态")
public class DomainExistType extends Dictionary {
    private static final long serialVersionUID = 1055980897254223047L;

    @BeanAttrInfo(cnName = "购买")
    public static final long BUY = 1;

    @BeanAttrInfo(cnName = "转入")
    public static final long TRANSFER_IN = 5;

    @BeanAttrInfo(cnName = "转出")
    public static final long TRANSFER_OUT = 10;
}
