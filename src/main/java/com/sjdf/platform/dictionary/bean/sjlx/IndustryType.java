package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("SJLX_Industry_type")
@BeanName(name = "sjlx-行业类型")
public class IndustryType extends Dictionary {
    private static final long serialVersionUID = -6176671603785321061L;

    /** 营业税 */
    @BeanAttrInfo(cnName = "营业税")
    public static final long COMPANY_TAX1 = 1;

    /** 增值税 */
    @BeanAttrInfo(cnName = "增值税")
    public static final long COMPANY_TAX2 = 2;

    /** 营业税/增值税 */
    @BeanAttrInfo(cnName = "营业税/增值税")
    public static final long COMPANY_TAX3 = 3;

    /** 计算机 */
    @BeanAttrInfo(cnName = "计算机", refClass = IndustryType.class, refAttr = COMPANY_TAX1)
    public static final long COMPUTER = 10;
    /** 餐饮 */
    @BeanAttrInfo(cnName = "餐饮", refClass = IndustryType.class, refAttr = COMPANY_TAX2)
    public static final long CATERINGREPAST = 11;

    /**
     * @return 分类列表
     */
    public static List<? extends Dictionary> getIndustryType() {
        return getIndustryType(0L);
    }

    /**
     * 小分类
     *
     * @param refAttr 大分类属性值
     * @return 附件小分类列表
     */
    public static List<? extends Dictionary> getIndustryType(long refAttr) {
        return ConfigManager.getInstance().getRefDictionary(IndustryType.class, refAttr);
    }


    /**
     * 小分类
     *
     * @param refAttr 大分类属性值
     * @return 附件小分类列表
     */
    public static List<? extends Dictionary> getAllIndustryType() {
        List<? extends Dictionary> list = ConfigManager.getInstance().getDictionary(IndustryType.class);
        if (list.isEmpty()) {
            return list;
        }
        // 过滤
        List<Dictionary> result = new ArrayList<>(CommonPlatformConstant.LENGTH_20);
        for (Dictionary d : list) {
            Dictionary ref = d.getRef();
            if (ref != null) {
                result.add(d);
            }
        }
        return result;
    }
}
