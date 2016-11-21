package com.sjdf.platform.dictionary.bean.common;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2015-08-26
 * 配置value值说明：
 * value是用逗号分隔的两个字符串。
 * 逗号前面的字符串表示配置属性bean，@PropertiesConfigType注解的值
 * 逗号后面的字符串表示对应配置库类的全路径
 *
 * @author wangpeng
 * @category 属性配置项配置库
 */
@Entity
@DiscriminatorValue("PROPERTIES_CONFIG")
@BeanName(name = "属性配置项")
public class PropertiesConfig extends Dictionary {

    private static final long serialVersionUID = 4320710156216228761L;

    @BeanAttrInfo(value = "home_product_public_server,com.sjdf.platform.dictionary.bean.ServerClass",
            cnName = "服务器<==>服务器分类配置项")
    public static final long SERVER_CLASS_CONFIG = 1;
}
