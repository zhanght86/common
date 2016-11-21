package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author wangpeng
 * @category 微信图文外连接类型
 * @date 2014-04-09
 */
@Entity
@DiscriminatorValue("WEI_XIN_LINK_TYPE")
@BeanName(name = "微信图文外连接类型")
public class WeiXinLinkType extends Dictionary {

    private static final long serialVersionUID = -2538729376528572168L;

    @BeanAttrInfo(value = "1", cnName = "链接")
    public static final long LINK = 1L;

    @BeanAttrInfo(value = "2", cnName = "微活动")
    public static final long ACTIVITY = 2L;

    @BeanAttrInfo(value = "3", cnName = "业务模块")
    public static final long BUSINESS = 3L;

    @BeanAttrInfo(value = "4", cnName = "微页面")
    public static final long MICRO_PAGE = 4L;

    @BeanAttrInfo(value = "5", cnName = "电话")
    public static final long TELPHONE = 5L;
}
