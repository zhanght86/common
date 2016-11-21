package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 脚本类型
 */
@Entity
@DiscriminatorValue("SCRIPT_TYPE")
@BeanName(name = "脚本类型")
public class ScriptType extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 8861191922430710085L;
    /** shtml */
    @BeanAttrInfo(value = "Shtml", orderBy = 1, cnName = "Shtml")
    public static final long SHTML = 1;
    /** 禁止下载 */
    @BeanAttrInfo(value = "禁止下载", orderBy = 2, cnName = "禁止下载")
    public static final long PROHIBIT_DOWNLOAD = 2;
    /** ASP */
    @BeanAttrInfo(value = "ASP", orderBy = 3, cnName = "ASP")
    public static final long ASP = 3;
    /** ASP_NET */
    @BeanAttrInfo(value = "ASP_NET", orderBy = 4, cnName = "ASP_NET")
    public static final long ASP_NET = 4;
    /** PHP */
    @BeanAttrInfo(value = "PHP", orderBy = 5, cnName = "PHP")
    public static final long PHP = 5;
    /** PERL */
    @BeanAttrInfo(value = "PERL", orderBy = 6, cnName = "PERL")
    public static final long PERL = 6;
    /** Python */
    @BeanAttrInfo(value = "Python", orderBy = 7, cnName = "Python")
    public static final long PYTHON = 7;
    /** Jsp */
    @BeanAttrInfo(value = "Jsp", orderBy = 8, cnName = "Jsp")
    public static final long JSP = 8;
    /** Ruby */
    @BeanAttrInfo(value = "Ruby", orderBy = 9, cnName = "Ruby")
    public static final long RUBY = 9;

}
