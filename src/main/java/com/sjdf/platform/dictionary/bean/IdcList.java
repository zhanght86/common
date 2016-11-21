package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-9-20
 *
 * @author 邱忠福
 * @category 机房列表
 */
@Entity
@DiscriminatorValue("IDC_LIST")
@BeanName(name = "机房列表")
public class IdcList extends Dictionary {

    private static final long serialVersionUID = 4437874436597657846L;

    /** 德阳电信 */
    @BeanAttrInfo(value = "13", cnName = "德阳电信")
    public static final long DE_YANG_DIAN_XIN = 1;

    /** 绵阳电信 */
    @BeanAttrInfo(value = "14", cnName = "绵阳电信")
    public static final long MIAN_YANG_DIAN_XIN = 2;

    /** 四川电信（推荐） */
    @BeanAttrInfo(value = "30", cnName = "四川电信（推荐）")
    public static final long SI_CHUAN_DIAN_XIN_TUI_JIAN = 3;

    /** 北京双线（智能双线） */
    @BeanAttrInfo(value = "20", cnName = "北京双线（智能双线）")
    public static final long BEI_JING_SHUANG_XIAN = 4;
}
