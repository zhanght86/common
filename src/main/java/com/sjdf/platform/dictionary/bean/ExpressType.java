package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-04-12
 *
 * @author 王正伟
 * @category 快递模板分类, 禁止修改enName所对应的值
 */
@Entity
@DiscriminatorValue("EXPRESS_TYPE")
@BeanName(name = "快递模板分类")
public class ExpressType extends Dictionary {
    private static final long serialVersionUID = 3728439537606242477L;
    /** 平信 */
    @BeanAttrInfo(value = "ordinary", cnName = "平信", enName = "ordinary")
    public static final long ORDINARY = 1;

    /** 圆通 */
    @BeanAttrInfo(value = "yto", cnName = "圆通", enName = "yto")
    public static final long YTO = 2;

    /** 顺丰 */
    @BeanAttrInfo(value = "sf", cnName = "顺丰", enName = "sf")
    public static final long SF = 3;

    /** EMS */
    @BeanAttrInfo(value = "ems", cnName = "EMS", enName = "ems")
    public static final long EMS = 4;

    /** 挂号信 */
    @BeanAttrInfo(value = "letter", cnName = "挂号信", enName = "ordinary")
    public static final long REGISTERED_LETTER = 5;

    /** 顺丰到付 */
    @BeanAttrInfo(value = "sf", cnName = "顺丰到付", enName = "sf")
    public static final long SF2PAY = 7;

    /** 自提 */
    @BeanAttrInfo(cnName = "自提")
    public static final long VISIT_HOLD = 99;
}
