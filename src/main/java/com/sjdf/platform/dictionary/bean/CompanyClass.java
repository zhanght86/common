package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 公司类别
 *
 * @author sjdf
 */
@Entity
@DiscriminatorValue("COMPANY_CLASS")
@BeanName(name = "公司类别")
public class CompanyClass extends Dictionary {
    private static final long serialVersionUID = -3929249993058437107L;

    //当前系统类型标识
    public static final String CURRENT_COMPANY_CLASS = "current-company-class";

    /** 世纪东方 */
    @BeanAttrInfo(orderBy = 1, cnName = "世纪东方")
    public static final long SJDF = 1;

    /** 云工坊 */
    @BeanAttrInfo(orderBy = 2, cnName = "云工坊")
    public static final long YGF = 2;

    /** 网居 */
    @BeanAttrInfo(orderBy = 3, cnName = "网居")
    public static final long WJ = 3;

    /** 百优互联 */
    @BeanAttrInfo(orderBy = 4, cnName = "百优互联")
    public static final long OPTIMAL_CONNECT = 4;

    /** 世纪利信 */
    @BeanAttrInfo(orderBy = 10, cnName = "世纪利信")
    public static final long SJLX = 10;

    /** 其他 */
    @BeanAttrInfo(orderBy = 99, cnName = "其他")
    public static final long OTHER = 99;

    /** 获取当前公司类型 */
    public static long getCurrentCompanyClass() {
        String str = System.getProperty(CURRENT_COMPANY_CLASS);
        if (PlatformUtils.hasText(str)) {
            return Long.parseLong(str);
        }
        return 0L;
    }

    /** 获取当前公司名称 */
    public static String getCurrentCompanyClassName() {
        long attr = getCurrentCompanyClass();
        if (attr == 0L) {
            return "";
        }
        return ConfigManager.getInstance().getName(CompanyClass.class, attr);
    }
}
