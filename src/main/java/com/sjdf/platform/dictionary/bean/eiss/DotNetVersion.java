package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-07-17 09:40
 * .net版本
 */
@Entity
@DiscriminatorValue("DOT_NET_VERSION")
@BeanName(name = ".net版本")
public class DotNetVersion extends Dictionary {
    @BeanAttrInfo(cnName = "ASP.NET 1.1")
    public static final long DOTNET_11 = 1;

    @BeanAttrInfo(cnName = "ASP.NET 2.0/3.0/3.5")
    public static final long DOTNET_20_30_35 = 2;

    @BeanAttrInfo(cnName = "ASP.NET 4.0")
    public static final long DOTNET_40 = 3;
}
