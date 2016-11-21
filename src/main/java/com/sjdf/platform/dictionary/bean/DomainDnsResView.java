package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2013-02-27
 *
 * @author 陈绍刚
 * @category 域名解析线路类型
 */
@Entity
@DiscriminatorValue("DOMAIN_DNS_RES_VIEW")
@BeanName(name = "域名解析线路类型")
public class DomainDnsResView extends Dictionary {

    private static final long serialVersionUID = -8985061746513328214L;
    /** 通用 */
    @BeanAttrInfo(value = "ANY", cnName = "通用")
    public static final long ANY = 1;
    /** 电信 */
    @BeanAttrInfo(value = "CHINANET", cnName = "电信")
    public static final long CHINANET = 2;
    /** 网通 */
    @BeanAttrInfo(value = "CNCGROUP", cnName = "网通")
    public static final long CNCGROUP = 3;

}
