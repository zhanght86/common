package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-08-29
 *
 * @author Chen Mohan
 * @category 产品备案接入检查-数据类别
 */
@Entity
@DiscriminatorValue("PRODUCT_RECORD_ACCESS_DATA_TYPE")
@BeanName(name = "产品备案接入检查-数据类别")
public class ProductRecordAccessDataType extends Dictionary {

    private static final long serialVersionUID = 9173290738377335013L;

    @BeanAttrInfo(cnName = "未解析到我司产品域名")
    public static final long DOMAIN_HOST_NOT_IN_IDC = 1;

    @BeanAttrInfo(cnName = "需变更备案域名")
    public static final long DOMAIN_HOST_NOT_IN_PRODUCT = 2;

    @BeanAttrInfo(cnName = "非国内机房产品不能备案域名")
    public static final long CAN_NOT_RECORD = 3;

    @BeanAttrInfo(cnName = "需新增接入域名")
    public static final long NEW_ACCESS = 4;

    @BeanAttrInfo(cnName = "需人工处理域名")
    public static final long MANUAL_HAND = 5;

    @BeanAttrInfo(cnName = "需人工处理域名（我司）")
    public static final long MANUAL_HAND_OUR = 6;

    @BeanAttrInfo(cnName = "直接取消接入域名")
    public static final long DIRECT_CANCEL = 7;
}
