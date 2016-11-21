package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-02-15 11:37
 */
@Entity
@DiscriminatorValue("SJLX_DELIVER_ITEM")
@BeanName(name = "sjlx-交付项目")
public class SjlxDeliverItem extends Dictionary{
    private static final long serialVersionUID = -3044187554391203599L;

    @BeanAttrInfo(cnName = "章     程")
    public static final long ITEM_1 = 1;

    @BeanAttrInfo(cnName = "支付密码器")
    public static final long ITEM_2 = 2;

    @BeanAttrInfo(cnName = "密码信封")
    public static final long ITEM_3 = 3;

    @BeanAttrInfo(cnName = "印 鉴 卡")
    public static final long ITEM_4 = 4;

    @BeanAttrInfo(cnName = "公    章")
    public static final long ITEM_5 = 5;

    @BeanAttrInfo(cnName = "法 人 章")
    public static final long ITEM_6 = 6;

    @BeanAttrInfo(cnName = "财务专用章")
    public static final long ITEM_7 = 7;

    @BeanAttrInfo(cnName = "发票专用章")
    public static final long ITEM_8 = 8;

    @BeanAttrInfo(cnName = "银行回单柜卡")
    public static final long ITEM_9 = 9;

    @BeanAttrInfo(cnName = "银行U盾")
    public static final long ITEM_10 = 10;

    @BeanAttrInfo(cnName = "易证通U盾")
    public static final long ITEM_11 = 11;
}
