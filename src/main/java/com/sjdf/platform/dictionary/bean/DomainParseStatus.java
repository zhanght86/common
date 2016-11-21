package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 * create at 2013-3-23
 *
 * @author dingyan
 * @category 域名解析状态
 */
@Entity
@DiscriminatorValue("DOMAIN_PARSE_STATUS")
@BeanName(name = "域名解析状态")
public class DomainParseStatus extends Dictionary {
    private static final long serialVersionUID = -481932161857378125L;

    /** @category 未开通 */
    @BeanAttrInfo(value = "1", cnName = "未开通")
    public static final long NOT_OPENED = 1;

    /** @category 已开通 */
    @BeanAttrInfo(value = "2", cnName = "已开通")
    public static final long HAS_OPENED = 2;
}
