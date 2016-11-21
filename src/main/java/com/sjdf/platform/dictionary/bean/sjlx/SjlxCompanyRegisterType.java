package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SJLX_COMPANY_REGISTER_TYPE")
@BeanName(name = "sjlx-公司注册类型")
public class SjlxCompanyRegisterType extends Dictionary {
    private static final long serialVersionUID = -3110994558836163788L;

    /** 国有企业 */
    @BeanAttrInfo(cnName = "国有企业")
    public static final long STATE_OWNED_ENTERPRISES = 1;

    /** 集体所有制企业 */
    @BeanAttrInfo(cnName = "集体所有制企业")
    public static final long COLLECTIVE_OWNERSHIP_ENTERPRISE = 2;

    /** 私营企业 */
    @BeanAttrInfo(cnName = "私营企业")
    public static final long PRIVATE_ENTERPRISE = 3;

    /** 股份制企业 */
    @BeanAttrInfo(cnName = "股份制企业")
    public static final long JOINT_STOCK_ENTERPRISE = 4;

    /** 联营企业 */
    @BeanAttrInfo(cnName = "联营企业")
    public static final long JOINT_VENTURE = 5;

    /** 外商投资企业 */
    @BeanAttrInfo(cnName = "外商投资企业")
    public static final long FOREIGN_INVESTED_ENTERPRISES = 6;
    /** 港、澳、台投资企业 */
    @BeanAttrInfo(cnName = "港、澳、台投资企业")
    public static final long INVESTMENT_ENTERPRISES = 7;
}
