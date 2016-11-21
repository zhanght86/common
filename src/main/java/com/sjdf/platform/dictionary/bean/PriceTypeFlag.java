/**
 *
 */
package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created 2013-3-2 上午10:46:27
 *
 * @author ketqi
 * @category 价格类型标识位(购买, 续费, 域名转出, 域名过户, 域名赎回, 域名删除)
 */
@Entity
@DiscriminatorValue("PRICE_TYPE_FLAG")
@BeanName(name = "价格类型标识位")
public class PriceTypeFlag extends Dictionary {
    private static final long serialVersionUID = 5961755498631724584L;

    /** 购买 */
    @BeanAttrInfo(orderBy = 1, value = "Buy", cnName = "购买")
    public static final long PRICE_BUY = 1;

    /** 续费 */
    @BeanAttrInfo(orderBy = 2, value = "Renew", cnName = "续费")
    public static final long PRICE_RENEW = 2;

    /** 域名转出 */
    @BeanAttrInfo(orderBy = 3, value = "Out", cnName = "域名转出", systemType = SystemType.EISS)
    public static final long PRICE_DOMAIN_ROLL_OUT = 3;

    /** 域名过户 */
    @BeanAttrInfo(orderBy = 4, value = "Owner", cnName = "域名过户", systemType = SystemType.EISS)
    public static final long PRICE_DOMAIN_TRANSFER = 4;

    /** 域名赎回 */
    @BeanAttrInfo(orderBy = 5, value = "Ransom", cnName = "域名赎回", systemType = SystemType.EISS)
    public static final long PRICE_DOMAIN_RANSOM = 5;

    /** 域名删除 */
    @BeanAttrInfo(orderBy = 6, cnName = "域名删除", systemType = SystemType.EISS)
    public static final long PRICE_DOMAIN_DELETE = 6;

    /** 域名转入 */
    @BeanAttrInfo(orderBy = 7, value = "In", cnName = "域名转入", systemType = SystemType.EISS)
    public static final long PRICE_DOMAIN_IN = 7;

    /** 产品退款 */
    @BeanAttrInfo(orderBy = 8, value = "Refund", cnName = "退款")
    public static final long PRICE_REFUND = 8;

    /** 升级(仅仅使用在程序中,不出现在价格表中.使用此标识不判断有效时间范围) */
    @BeanAttrInfo(orderBy = 99, value = "Upgrade", cnName = "升级")
    public static final long UPGRADE = 99;

    public static List<? extends Dictionary> getPriceTypeFlagList() {
        long companyClass = CompanyClass.getCurrentCompanyClass();
        if (companyClass == CompanyClass.SJLX) {
            return ConfigManager.getInstance().getDictionaryList(PriceTypeFlag.class, SystemType.SJLX_CMS);
        }
        return ConfigManager.getInstance().getDictionary(PriceTypeFlag.class);
    }
}
