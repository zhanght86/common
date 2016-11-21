package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DOMAIN_DNS_RES_TYPE")
@BeanName(name = "域名解析记录类型")
public class DomainDnsResType extends Dictionary {

    private static final long serialVersionUID = 1027497564391055677L;

    @BeanAttrInfo(value = "A", cnName = "A", orderBy = 2)
    public static final long A = 1;

    @BeanAttrInfo(value = "CNAME", cnName = "CNAME", orderBy = 1)
    public static final long CNAME = 2;

    @BeanAttrInfo(value = "MX", cnName = "MX", orderBy = 5)
    public static final long MX = 3;

    @BeanAttrInfo(value = "TXT", cnName = "TXT", orderBy = 8)
    public static final long TXT = 4;

    @BeanAttrInfo(value = "AAAA", cnName = "AAAA", orderBy = 4)
    public static final long AAAA = 5;

    @BeanAttrInfo(value = "DNAME", cnName = "DNAME", orderBy = 3)
    public static final long DNAME = 6;

    @BeanAttrInfo(value = "显性URL", cnName = "显性URL", orderBy = 6)
    public static final long DOMINANT_URL = 7;

    @BeanAttrInfo(value = "隐性URL", cnName = "隐性URL", orderBy = 7)
    public static final long RECESSIVE_URL = 8;
}
