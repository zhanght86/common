package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author wangpeng
 * @category 微信微官网外连接类型
 * @date 2014-04-09
 */
@Entity
@DiscriminatorValue("WEI_XIN_MICRO_PAGE_LINK_TYPE")
@BeanName(name = "微信微官网外连接类型")
public class WeiXinMicroPageLinkType extends Dictionary {

    private static final long serialVersionUID = -2538729376528572168L;

    @BeanAttrInfo(value = "1", cnName = "链接")
    public static final long LINK = 1L;

    @BeanAttrInfo(value = "2", cnName = "微页面")
    public static final long MICRO_PAGE = 2L;

    @BeanAttrInfo(value = "3", cnName = "电话")
    public static final long TELPHONE = 3L;
}
