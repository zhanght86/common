package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-07-25
 *
 * @author Chen Mohan
 * @category 网警图标扫描-解析IP
 */
@Entity
@DiscriminatorValue("NET_SENTRY_ICON_DOMAIN_PARSE")
@BeanName(name = "网警图标扫描-解析IP")
public class NetSentryIconDomainParse extends Dictionary {

    private static final long serialVersionUID = 2144042672748973816L;

    @BeanAttrInfo(value = "1", cnName = "与产品IP一致")
    public static final long SAME_PRODUCT = 1;

    @BeanAttrInfo(value = "2", cnName = "与产品IP不一致")
    public static final long DIFFERENT_PRODUCT = 2;

    @BeanAttrInfo(value = "3", cnName = "机房IP范围内")
    public static final long INSIDE_IDC = 3;

    @BeanAttrInfo(value = "4", cnName = "机房IP范围外")
    public static final long OUTSIDE_IDC = 4;
}
