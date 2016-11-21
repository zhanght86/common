package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年8月19日 上午10:06:24
 *
 * @author KETQI
 * @category 通用网址类型, value请不要随便更改, 同上家同步的
 */
@Entity
@DiscriminatorValue("WEBSITE_TYPE")
@BeanName(name = "通用网址类型")
public class WebsiteType extends Dictionary {
    private static final long serialVersionUID = -2057280140586777505L;

    /** 普通词 */
    @BeanAttrInfo(value = "normal", cnName = "普通词", enName = "normal")
    public static final long NORMAL = 1;

    /** 准通用词 */
    @BeanAttrInfo(value = "zhungeneral", cnName = "准通用词", enName = "zhungeneral")
    public static final long ZHUN_GENERAL = 2;

    /** 普通通用词 */
    @BeanAttrInfo(value = "normalgeneral", cnName = "普通通用词", enName = "normalgeneral")
    public static final long NORMAL_GENERAL = 3;

    /** 黄金通用词 */
    @BeanAttrInfo(value = "goldgeneral", cnName = "黄金通用词", enName = "goldgeneral")
    public static final long GOLD_GENERAL = 4;
}
