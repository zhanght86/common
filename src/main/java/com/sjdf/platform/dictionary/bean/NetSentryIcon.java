package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-07-25
 *
 * @author Chen Mohan
 * @category 网警图标
 */
@Entity
@DiscriminatorValue("NET_SENTRY_ICON")
@BeanName(name = "网警图标")
public class NetSentryIcon extends Dictionary {

    private static final long serialVersionUID = -6562267208057502969L;

    @BeanAttrInfo(value = "1", cnName = "有")
    public static final long FOUND = 1;

    @BeanAttrInfo(value = "2", cnName = "无")
    public static final long NOT_FOUND = 2;

    @BeanAttrInfo(value = "3", cnName = "未检测")
    public static final long NOT_SCAN = 3;
}
