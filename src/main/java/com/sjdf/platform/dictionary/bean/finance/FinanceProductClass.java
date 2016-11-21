package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.CompanyClass;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.ProductClass;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 财务系统产品分类
 * User: ketqi
 * Date: 2014-12-08 16:27
 */
@Entity
@DiscriminatorValue("FINANCE_PRODUCT_CLASS")
@BeanName(name = "产品分类")
public class FinanceProductClass extends Dictionary {
    private static final long serialVersionUID = -4304583492192620156L;

    /** 虚拟主机 */
    @BeanAttrInfo(cnName = "虚拟主机", orderBy = 2, systemType = SystemType.EISS)
    public static final long VHOST = ProductClass.VHOST;

    /** 邮局 */
    @BeanAttrInfo(cnName = "邮局", orderBy = 6, systemType = SystemType.EISS)
    public static final long MAIL = ProductClass.MAIL;

    /** 数据库 */
    @BeanAttrInfo(cnName = "数据库", orderBy = 8, systemType = SystemType.EISS)
    public static final long DATABASE = ProductClass.DATABASE;

    /** 域名 */
    @BeanAttrInfo(cnName = "域名", orderBy = 1, systemType = SystemType.EISS)
    public static final long DOMAIN = ProductClass.DOMAIN;

    /** IDC */
    @BeanAttrInfo(cnName = "IDC", orderBy = 5, systemType = SystemType.EISS)
    public static final long IDC = ProductClass.IDC;

    /** 云主机 */
    @BeanAttrInfo(cnName = "云主机", orderBy = 3, systemType = SystemType.EISS)
    public static final long CHOST = ProductClass.CHOST;

    /** 微信 */
    @BeanAttrInfo(cnName = "微信", orderBy = 9, systemType = SystemType.EISS)
    public static final long WEIXIN = ProductClass.WEIXIN;

    /** 建站超市 */
    @BeanAttrInfo(cnName = "建站超市", orderBy = 4, systemType = SystemType.EISS)
    public static final long CREATE_SITE_SUPERMARKET = 97;

    /** 外部邮局 */
    @BeanAttrInfo(cnName = "外部邮局", orderBy = 7, systemType = SystemType.EISS)
    public static final long OUTER_EMAIL = 98;

    /** 初始流水 */
    @BeanAttrInfo(cnName = "初始流水", orderBy = 100, systemType = SystemType.EISS)
    public static final long INIT = 100;

    /** 记账管理 */
    @BeanAttrInfo(cnName = "记账管理", orderBy = 200, systemType = SystemType.SJLX_CMS)
    public static final long SJLX_FINANCE_MANAGER = 200;

    /** 其他 */
    @BeanAttrInfo(cnName = "其他", orderBy = 10000)
    public static final long OTHER = ProductClass.OTHER;

    /**
     * 根据公司类型获取指定的产品分类列表
     *
     * @return 分类列表
     */
    public static List<? extends Dictionary> getProductClassList() {
        List<? extends Dictionary> list;

        long company = CompanyClass.getCurrentCompanyClass();
        if (company == CompanyClass.SJDF || company == CompanyClass.YGF || company == CompanyClass.WJ) {
            list = ConfigManager.getInstance().getDictionaryList(FinanceProductClass.class, SystemType.EISS);
        } else if (company == CompanyClass.SJLX) {
            list = ConfigManager.getInstance().getDictionaryList(FinanceProductClass.class, SystemType.SJLX_CMS);
        } else {
            list = ConfigManager.getInstance().getDictionary(FinanceProductClass.class);
        }
        Collections.sort(list, Dictionary.COMPARATOR);

        return list;
    }

    /**
     * 判断指定类型是否是主机产品
     *
     * @param productClass 产品大分类
     * @return bool
     */
    public static boolean isHostProduct(long productClass) {
        return hostProduct().contains(productClass);
    }

    public static List<Long> hostProduct() {
        return Arrays.asList(VHOST, CHOST, CREATE_SITE_SUPERMARKET, MAIL, OUTER_EMAIL, DATABASE);
    }

    /**
     * 财务系统产品大分类转换(FinanceProductClass-->ProductClass)
     *
     * @param productMajor 财务产品大分类(FinanceProductClass)
     * @return 产品大分类(ProductClass)
     * @see com.sjdf.platform.dictionary.bean.finance.FinanceProductClass
     * @see com.sjdf.platform.dictionary.bean.ProductClass
     */
    public static long convert(long productMajor) {
        if (productMajor == FinanceProductClass.CREATE_SITE_SUPERMARKET) {
            return FinanceProductClass.VHOST;
        } else if (productMajor == FinanceProductClass.OUTER_EMAIL) {
            return FinanceProductClass.MAIL;
        }

        return productMajor;
    }

    /**
     * 判断指定的产品大分类是否有管理名称
     *
     * @param productMajor 产品大分类
     * @return bool
     */
    public static boolean noExistManagerName(long productMajor) {
        return productMajor == FinanceProductClass.WEIXIN || productMajor == FinanceProductClass.IDC || productMajor == FinanceProductClass.OTHER;
    }
}
