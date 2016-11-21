/**
 *
 */
package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author ketqi
 * @Created 2012-12-24 上午11:53:06
 * @category 域名产品状态
 */
@Entity
@DiscriminatorValue("DOMAIN_PRODUCT_STATUS")
@BeanName(name = "域名产品状态")
public class DomainProductStatus extends Dictionary {
    private static final long serialVersionUID = -8577018177100553962L;

    /** 正常 */
    @BeanAttrInfo(orderBy = 1, cnName = "正常")
    public static final long NORMAL = 1;

    /** 禁用 */
    @BeanAttrInfo(orderBy = 2, cnName = "禁用")
    public static final long DISABLE = 2;

    /** 隐藏 */
    @BeanAttrInfo(orderBy = 3, cnName = "隐藏")
    public static final long HIDE = 3;
}
