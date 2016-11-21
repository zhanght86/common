package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-11-15
 *
 * @author 邱忠福
 * @category 解析状态
 */
@Entity
@DiscriminatorValue("PARSE_STATUS")
@BeanName(name = "解析状态")
public class ParseState extends Dictionary {

    private static final long serialVersionUID = 4693849094932328419L;

    /** 与产品一致 */
    @BeanAttrInfo(value = "1", cnName = "与产品一致")
    public static final long IN_LINE_WITH_PRODUCT = 1;

    /** 与产品不一致 */
    @BeanAttrInfo(value = "2", cnName = "与产品不一致")
    public static final long NOT_IN_LINE_WITH_PRODUCT = 2;

    /** 未解析 */
    @BeanAttrInfo(value = "3", cnName = "未解析")
    public static final long NOT_PARSE = 3;
}
