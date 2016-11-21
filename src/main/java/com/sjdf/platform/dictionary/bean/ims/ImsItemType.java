package com.sjdf.platform.dictionary.bean.ims;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: Hunk
 * Date: 2016-10-20 15:53
 */
@Entity
@DiscriminatorValue("Ims_Item_Type")
@BeanName(name = "IMS元素类型")
public class ImsItemType extends Dictionary {

    private static final long serialVersionUID = 6949373242649145704L;

    @BeanAttrInfo(value = "如：内衣、内裤、鞋、帽等", cnName = "大分类")
    public static final long IMS_CLS = 1;

    @BeanAttrInfo(value = "如：金薇、GCC、李宁等", cnName = "品牌")
    public static final long IMS_BRAND = 2;

    @BeanAttrInfo(value = "如：呼吸款、高端款等", cnName = "产品类型")
    public static final long IMS_TYPE = 3;

    @BeanAttrInfo(value = "如：男士、女士等", cnName = "款式")
    public static final long IMS_STYLE = 4;

    @BeanAttrInfo(value = "如：红色、黑色等", cnName = "颜色")
    public static final long IMS_COLOR = 5;

    @BeanAttrInfo(value = "如：70、80等", cnName = "大小")
    public static final long IMS_SIZE = 6;

    @BeanAttrInfo(value = "如：A、B、C、L、XL等", cnName = "型号")
    public static final long IMS_MODEL = 7;
}
