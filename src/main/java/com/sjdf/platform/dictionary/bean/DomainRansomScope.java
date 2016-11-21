package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-03-11
 *
 * @author 王正伟
 * @category 域名赎回范围
 */
@Entity
@DiscriminatorValue("DOMAIN_RANSOM_SCOPE")
@BeanName(name = "域名赎回范围")
public class DomainRansomScope extends Dictionary {
    private static final long serialVersionUID = 6742851611372883022L;

    /** 域名赎回开始时间:30天 */
    @BeanAttrInfo(value = "30", cnName = "域名赎回开始时间")
    public static final long RANSOM_BEGIN = 1;

    /** 域名赎回结束时间:60天 */
    @BeanAttrInfo(value = "60", cnName = "域名赎回结束时间")
    public static final long RANSOM_END = 2;

}
