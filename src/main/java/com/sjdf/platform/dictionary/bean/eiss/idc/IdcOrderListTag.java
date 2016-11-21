package com.sjdf.platform.dictionary.bean.eiss.idc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * 机柜和机位状态配置
 * @date 2016年3月8日下午3:33:46
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("IDC_ORDER_LIST_TAG")
@BeanName(name = "IDC订单列表标签")
public class IdcOrderListTag extends Dictionary{

    private static final long serialVersionUID = -1888681522553364310L;

    @BeanAttrInfo(cnName = "业务部未审核")
    public static final long TAG_1 = 1;

    @BeanAttrInfo(cnName = "业务部已完成")
    public static final long TAG_3 = 3;

    @BeanAttrInfo(cnName = "客服部未确认")
    public static final long TAG_11 = 11;

    @BeanAttrInfo(cnName = "客服部已确认")
    public static final long TAG_12 = 12;

    @BeanAttrInfo(cnName = "客服部已完成")
    public static final long TAG_13 = 13;

    @BeanAttrInfo(cnName = "采购部未确认")
    public static final long TAG_21 = 21;

    @BeanAttrInfo(cnName = "采购部已确认")
    public static final long TAG_22 = 22;

    @BeanAttrInfo(cnName = "采购部已完成")
    public static final long TAG_23 = 23;

    @BeanAttrInfo(cnName = "技术部未确认")
    public static final long TAG_31 = 31;

    @BeanAttrInfo(cnName = "技术部已确认")
    public static final long TAG_32 = 32;

    @BeanAttrInfo(cnName = "技术部已完成")
    public static final long TAG_33 = 33;

    @BeanAttrInfo(cnName = "技术部未复核")
    public static final long TAG_41 = 41;

    @BeanAttrInfo(cnName = "已完成")
    public static final long FINAL_TAG = 43;

    public static boolean isFinishStatus(long tag) {
        return tag % 10 % 3 == 0;
    }
}
