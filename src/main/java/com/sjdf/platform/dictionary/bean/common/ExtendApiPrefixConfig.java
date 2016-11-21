package com.sjdf.platform.dictionary.bean.common;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.SystemType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2015-07-25 11:01
 */
@Entity
@DiscriminatorValue("EXTEND_API_PREFIX_CONFIG")
@BeanName(name = "对外接口前缀配置")
public class ExtendApiPrefixConfig extends Dictionary {
    private static final long serialVersionUID = -9203979700613580214L;

    /** 世纪利信-公司管理系统 */
    @BeanAttrInfo(value = "http://ess.scsjlx.com/", cnName = "世纪利信-公司管理系统")
    public static final long SJLX_CMS = SystemType.SJLX_CMS;
}
