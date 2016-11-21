package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Create at 2012-05-22
 *
 * @author 王正伟
 * @category 价格类型(天, 周, 月, 季, 半年, 年)
 */
@Entity
@DiscriminatorValue("PRICE_TYPE")
@BeanName(name = "价格类型")
public class PriceType extends Dictionary {
    private static final long serialVersionUID = -1108639288986452345L;
    /** 天 */
    @BeanAttrInfo(value = "1", cnName = "天")
    public static final long DAY = 1;

    /** 周 */
    @BeanAttrInfo(value = "2", cnName = "周")
    public static final long WEEK = 2;

    /** 月 */
    @BeanAttrInfo(value = "3", cnName = "月")
    public static final long MONTH = 3;

    /** 季 */
    @BeanAttrInfo(value = "4", cnName = "季")
    public static final long QUARTER = 4;

    /** 半年 */
    @BeanAttrInfo(value = "5", cnName = "半年")
    public static final long HALF_YEAR = 5;

    /** 年 */
    @BeanAttrInfo(value = "6", cnName = "年")
    public static final long YEAR = 6;

    public static List<PriceType> getGEMonthList() {
        List<PriceType> list = new ArrayList<>(CommonPlatformConstant.LENGTH_4);
        list.add(ConfigManager.getInstance().getDictionary(PriceType.class, PriceType.MONTH));
        list.add(ConfigManager.getInstance().getDictionary(PriceType.class, PriceType.QUARTER));
        list.add(ConfigManager.getInstance().getDictionary(PriceType.class, PriceType.HALF_YEAR));
        list.add(ConfigManager.getInstance().getDictionary(PriceType.class, PriceType.YEAR));
        return list;
    }
}
