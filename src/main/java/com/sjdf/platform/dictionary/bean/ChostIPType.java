package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-14
 *
 * @author 王正伟
 * @category 云主机IP类型
 */
@Entity
@DiscriminatorValue("CHOSTIP_TYPE")
@BeanName(name = "云主机IP类型")
public class ChostIPType extends Dictionary {
    private static final long serialVersionUID = 2941826606229590435L;

    /** 共享IP */
    @BeanAttrInfo(value = "1", cnName = "共享IP")
    public static final long SHARE = 1;
    /** 独立IP */
    @BeanAttrInfo(value = "2", cnName = "独立IP")
    public static final long ALONE = 2;
}
