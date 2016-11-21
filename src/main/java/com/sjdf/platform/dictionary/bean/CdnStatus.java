package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 邱忠福
 * @category CND状态
 * @date 2013-4-19
 */
@Entity
@DiscriminatorValue("CND_STATUS")
@BeanName(name = "CND状态")
public class CdnStatus extends Dictionary {

    private static final long serialVersionUID = 4351521903299704733L;

    /** 启用 */
    @BeanAttrInfo(value = "0", cnName = "启用")
    public static final long SWITCH_ON = 1;

    /** 禁用 */
    @BeanAttrInfo(value = "1", cnName = "禁用")
    public static final long SWITCH_OFF = 2;
}
