package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category IP分配_使用状况
 */
@Entity
@DiscriminatorValue("IP_ALLOT_USE_MODE")
@BeanName(name = "IP分配_使用状况")
public class IpAllotUseMode extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -552655668402963218L;

    /** 分配且使用 */
    @BeanAttrInfo(value = "1", cnName = "分配且使用")
    public static final long ALLOT_AND_USE = 1L;

    /** 再次分配 */
    @BeanAttrInfo(value = "2", cnName = "再次分配")
    public static final long ALLOT_AGAIN = 2L;

    /** 自用 */
    @BeanAttrInfo(value = "3", cnName = "自用")
    public static final long USE_MYSELF = 3L;

    /** 预留 */
    @BeanAttrInfo(value = "3", cnName = "预留")
    public static final long RESERVE = 4L;
}
