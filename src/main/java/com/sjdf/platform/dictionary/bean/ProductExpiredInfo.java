package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-3-19
 *
 * @author 邱忠福
 * @category 产品过期情况
 */
@Entity
@DiscriminatorValue("PRODUCT_EXPIRED_INFO")
@BeanName(name = "产品过期情况")
public class ProductExpiredInfo extends Dictionary {

    private static final long serialVersionUID = -5646308377895099553L;

    /** 当天到期 */
    @BeanAttrInfo(value = "0", cnName = "当天到期")
    public static final long THE_SAME_DAY = 1;

    /** 7天内到期 */
    @BeanAttrInfo(value = "7", cnName = "7天内到期")
    public static final long SEVEN_DAYS = 2;

    /** 30天内到期 */
    @BeanAttrInfo(value = "30", cnName = "30天内到期")
    public static final long THIRTY_DAYS = 3;

    /** 过期产品 */
    @BeanAttrInfo(value = "-1", cnName = "过期产品")
    public static final long EXPIRED_PRODUCT = 4;

}
