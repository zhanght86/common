package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author frank
 * @category 单位性质
 */
@Entity
@DiscriminatorValue("COMPANY_TYPE")
@BeanName(name = "单位性质")
public class CompanyType extends Dictionary {
    private static final long serialVersionUID = 2882157775429724167L;

    /** 军队 */
    @BeanAttrInfo(orderBy = 1, cnName = "军队")
    public static final long COMPANY_TYPE_ARMY = 1;

    /** 政府机关 */
    @BeanAttrInfo(orderBy = 2, cnName = "政府机关")
    public static final long COMPANY_TYPE_GOVERNMENT = 2;

    /** 事业单位 */
    @BeanAttrInfo(orderBy = 3, cnName = "事业单位")
    public static final long COMPANY_TYPE_INSTITUTIONS = 3;

    /** 企业 */
    @BeanAttrInfo(orderBy = 4, cnName = "企业")
    public static final long COMPANY_TYPE_ENTERPRISE = 4;

    /** 个人 */
    @BeanAttrInfo(orderBy = 5, cnName = "个人")
    public static final long COMPANY_TYPE_PERSONAL = 5;

    /** 社会团体 */
    @BeanAttrInfo(orderBy = 6, cnName = "社会团体")
    public static final long COMPANY_TYPE_COMMUNITY_GROUPS = 6;

}
