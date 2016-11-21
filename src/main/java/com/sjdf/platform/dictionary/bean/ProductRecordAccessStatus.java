package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-08-29
 *
 * @author Chen Mohan
 * @category 产品备案接入检查-处理状态
 */
@Entity
@DiscriminatorValue("PRODUCT_RECORD_ACCESS_STATUS")
@BeanName(name = "产品备案接入检查-处理状态")
public class ProductRecordAccessStatus extends Dictionary {

    private static final long serialVersionUID = 5239756008786603875L;

    @BeanAttrInfo(cnName = "需取消接入")
    public static final long CANCEL_ACCESS = 1;

    @BeanAttrInfo(cnName = "需变更备案")
    public static final long CHANGE_RECORD = 2;

    @BeanAttrInfo(cnName = "需新增接入")
    public static final long NEW_ACCESS = 3;

    @BeanAttrInfo(cnName = "需人工处理")
    public static final long MANUAL_HAND = 4;

    @BeanAttrInfo(cnName = "需人工处理（我司）")
    public static final long MANUAL_HAND_OUR = 5;

    @BeanAttrInfo(cnName = "需直接取消接入")
    public static final long DIRECT_CANCEL = 6;
}
