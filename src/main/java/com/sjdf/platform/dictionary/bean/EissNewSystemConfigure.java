package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年8月8日 下午5:18:26
 *
 * @author KETQI
 * @category 主站系统常用配置
 */
@Entity
@DiscriminatorValue("EISSNEW_SYSTEM_CONFIGURE")
@BeanName(name = "主站系统常用配置")
public class EissNewSystemConfigure extends Dictionary {
    private static final long serialVersionUID = 2882157632658724041L;

    /** 域名解析记录修改、删除需给出提示信息的记录类型 */
    @BeanAttrInfo(value = "CNAME,A", orderBy = 1, cnName = "域名解析记录修改、删除需给出提示信息的记录类型")
    public static final long TIPS_FOR_DOMAIN_PARSE_TYPE = 1;

}
