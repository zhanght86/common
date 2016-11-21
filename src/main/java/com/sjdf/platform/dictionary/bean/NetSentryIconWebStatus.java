package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-07-25
 *
 * @author Chen Mohan
 * @category 网警图标扫描-访问状态
 */
@Entity
@DiscriminatorValue("NET_SENTRY_ICON_WEB_STATUS")
@BeanName(name = "网警图标扫描-访问状态")
public class NetSentryIconWebStatus extends Dictionary {

    private static final long serialVersionUID = 3539366064222873735L;

    @BeanAttrInfo(value = "1", cnName = "可访问")
    public static final long SUCCESS = 1;

    @BeanAttrInfo(value = "2", cnName = "不可访问")
    public static final long ERROR = 2;

    @BeanAttrInfo(value = "3", cnName = "未检测")
    public static final long NOT_SCAN = 3;
}
