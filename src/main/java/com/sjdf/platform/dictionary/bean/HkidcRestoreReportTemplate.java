package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2012-12-21
 *
 * @author 黄远昌
 * @category HKIDC赎回报告内容模板
 */
@Entity
@DiscriminatorValue("HKIDC_RESTORE_REPORT_TEMPLATE")
@BeanName(name = "HKIDC赎回报告内容模板")
public class HkidcRestoreReportTemplate extends Dictionary {
    private static final long serialVersionUID = -6509455639491888284L;
    /** 恢复域名原因的简要说明 */
    @BeanAttrInfo(value = "The user wants to continue to use this domain name.", cnName = "恢复域名原因的简要说明")
    public static final long RESTORE_REASON = 0;
    /** 域名不赎回说明 */
    @BeanAttrInfo(value = "The user restores this domain name in one's person.", cnName = "域名不赎回说明")
    public static final long STATEMENT1 = 2;
    /** 承认赎回报告中信息真实 */
    @BeanAttrInfo(value = "The restore report information is real and effective.", cnName = "域名不赎回说明")
    public static final long STATEMENT2 = 3;
}
