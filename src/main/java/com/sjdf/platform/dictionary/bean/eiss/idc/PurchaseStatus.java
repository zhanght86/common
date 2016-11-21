package com.sjdf.platform.dictionary.bean.eiss.idc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * 采购状态
 * @date 2016年3月31日上午10:20:18
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("PURCHASE_STATUS")
@BeanName(name = "采购状态")
public class PurchaseStatus extends Dictionary {

    private static final long serialVersionUID = 8602772649706499451L;

    @BeanAttrInfo(cnName = "无需采购")
    public static final long NO_NEED = 1;

    @BeanAttrInfo(cnName = "需要采购")
    public static final long NEED = 2;

    @BeanAttrInfo(cnName = "采购中")
    public static final long PURCHASEING = 3;

    @BeanAttrInfo(cnName = "采购完成")
    public static final long PURCHASED = 4;
}
