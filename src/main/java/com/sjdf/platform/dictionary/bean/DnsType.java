package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-3-5
 *
 * @author 丁yan
 * @category DNS区分
 */
@Entity
@DiscriminatorValue("DNS_TYPE")
@BeanName(name = "DNS区分")
public class DnsType extends Dictionary {
    private static final long serialVersionUID = 2580900264104702901L;

    /** 本站DNS */
    @BeanAttrInfo(value = "1", cnName = "本站DNS")
    public static final long LOCATION_DNS = 1;

    /** 注册商DNS */
    @BeanAttrInfo(value = "2", cnName = "注册商DNS")
    public static final long REGISTER_DNS = 2;

    /** 其它DNS */
    @BeanAttrInfo(value = "3", cnName = "其它DNS")
    public static final long OTHERS_DNS = 3;
}
