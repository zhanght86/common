package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-07-25
 *
 * @author Chen Mohan
 * @category 网警图标扫描-产品
 */
@Entity
@DiscriminatorValue("NET_SENTRY_ICON_PRODUCT")
@BeanName(name = "网警图标扫描-产品")
public class NetSentryIconProduct extends Dictionary {

    private static final long serialVersionUID = 5916558795936008063L;

    @BeanAttrInfo(value = "1", cnName = "虚拟主机")
    public static final long VHOST = 1;

    @BeanAttrInfo(value = "2", cnName = "VPS")
    public static final long VPS = 2;

    @BeanAttrInfo(value = "3", cnName = "IDC")
    public static final long IDC = 3;

    @BeanAttrInfo(value = "4", cnName = "其他")
    public static final long OTHER = 4;
}
