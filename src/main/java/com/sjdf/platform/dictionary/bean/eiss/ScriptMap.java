package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-07-17 10:20
 * 脚本映射
 */
@Entity
@DiscriminatorValue("SCRIPT_MAP")
@BeanName(name = "脚本映射")
public class ScriptMap extends Dictionary {

    @BeanAttrInfo(cnName = "Shtml")
    public static final long SHTML = 1;

    @BeanAttrInfo(cnName = "禁止下载")
    public static final long DENY_DOWNLOAD = 2;

    @BeanAttrInfo(cnName = "ASP")
    public static final long ASP = 3;

    @BeanAttrInfo(cnName = "ASP.NET")
    public static final long DOTNET = 4;

    @BeanAttrInfo(cnName = "PHP")
    public static final long PHP = 5;

    @BeanAttrInfo(cnName = "Perl")
    public static final long PERL = 6;

    @BeanAttrInfo(cnName = "Python")
    public static final long PYTHON = 7;

    @BeanAttrInfo(cnName = "Jsp")
    public static final long JSP = 8;

    @BeanAttrInfo(cnName = "Ruby")
    public static final long RUBY = 9;
}
