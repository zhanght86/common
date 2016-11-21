package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年11月20日 下午16:49:24
 *
 * @author 黄远昌
 * @category 建站超市
 */
@Entity
@DiscriminatorValue("WEBSITE_SUPERMARKET")
@BeanName(name = "建站超市")
public class WebsiteSupermarket extends Dictionary {
    private static final long serialVersionUID = 4894357524357180939L;

    @BeanAttrInfo(value = "建站超市空间", orderBy = 1, cnName = "建站超市产品分类名称")
    public static final long WEBSITE_SUPERMARKET_CLS_NAME = 1;
}
