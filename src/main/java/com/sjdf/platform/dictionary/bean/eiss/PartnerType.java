package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.CompanyClass;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-06-11 16:16
 * 合作伙伴类型(value表示所对应的会员,谨慎修改)
 */
@Entity
@DiscriminatorValue("PARTNER_TYPE")
@BeanName(name = "合作伙伴类型")
public class PartnerType extends Dictionary {
    private static final long serialVersionUID = 3871560102889452831L;

    /** 世纪东方 */
    @BeanAttrInfo(cnName = "世纪东方")
    public static final long COMPANY_SJDF = 1;

    /** 百优互联 */
    @BeanAttrInfo(value = "100ucard", cnName = "百优互联")
    public static final long COMPANY_100U = 2;
    /** 云道 */
    @BeanAttrInfo(value = "zjisp", cnName = "云道")
    public static final long COMPANY_YUNDAO = 3;
    /** 网居 */
    @BeanAttrInfo(value = "e8088cd", cnName = "网居")
    public static final long COMPANY_WANGJU = 4;


    public static long findCurrentPartner() {
        if (CompanyClass.getCurrentCompanyClass() == CompanyClass.SJDF) {
            return COMPANY_SJDF;
        } else if (CompanyClass.getCurrentCompanyClass() == CompanyClass.WJ) {
            return COMPANY_WANGJU;
        } else if (CompanyClass.getCurrentCompanyClass() == CompanyClass.YGF) {
            return COMPANY_YUNDAO;
        } else if (CompanyClass.getCurrentCompanyClass() == CompanyClass.OPTIMAL_CONNECT) {
            return COMPANY_100U;
        }

        return 0;
    }
}
