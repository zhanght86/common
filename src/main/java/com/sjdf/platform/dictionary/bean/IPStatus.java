package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2015-09-18
 *
 * @author wangpeng
 * @category IP状态
 */
@Entity
@DiscriminatorValue("IP_STATUS")
@BeanName(name = "IP状态")
public class IPStatus extends Dictionary {

    private static final long serialVersionUID = 7236081608454893832L;

    @BeanAttrInfo(cnName = "正常")
    public static final long NORMAL = 1;

    @BeanAttrInfo(cnName = "禁用")
    public static final long DISABLE = 2;

    @BeanAttrInfo(cnName="占用")
    public static final long OCCUPATION = 3;

    @BeanAttrInfo(cnName="预定")
    public static final long DESTINE = 4;
}
