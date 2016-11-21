package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-11-29 上午9:49:14
 *
 * @author KETQI
 * @category 数据模板大分类
 */
@Entity
@DiscriminatorValue("TEMPLATE_CLASS")
@BeanName(name = "数据模板大分类")
public class TemplateClass extends Dictionary {
    private static final long serialVersionUID = 8539581779780562550L;

    /** 信息模板 */
    @BeanAttrInfo(orderBy = 1, cnName = "信息模板")
    public static final long INFO_TEMPLATE_CLASS = 1;

    /** 产品模板 */
    @BeanAttrInfo(orderBy = 5, cnName = "产品模板")
    public static final long PRODUCT_TEMPLATE_CLASS = 5;
}
