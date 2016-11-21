package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2015-10-09
 *
 * @author wangpeng
 * @category 产品购买成功后的提示
 */
@Entity
@DiscriminatorValue("SUCCESSFUL_BUY_TIP")
@BeanName(name = "产品购买成功后的提示")
public class SuccessfulBuyTip extends Dictionary {

    private static final long serialVersionUID = -3029564116620521385L;

    private static final String EXAMPLE = "亲，感谢您对我司的支持，如果是第一次购买，建议亲仔细阅读以下事项：<br>"
            + "1、请务必尽快加我们为QQ好友，加后级别会调为VIP，享受便捷服务；<br>"
            + "2、技术问题请提交技术支持，工程师30分钟内回复。";

    @BeanAttrInfo(value = EXAMPLE, cnName = "云主机购买成功提示")
    public static final long CHOST_TIP = 1L;

    @BeanAttrInfo(value = EXAMPLE, cnName = "虚拟主机购买成功提示")
    public static final long VHOST_TIP = 2L;

    @BeanAttrInfo(value = EXAMPLE, cnName = "数据库购买成功提示")
    public static final long DATABASE_TIP = 3L;

    @BeanAttrInfo(value = EXAMPLE, cnName = "邮局购买成功提示")
    public static final long MAIL_TIP = 4L;

    @BeanAttrInfo(value = EXAMPLE, cnName = "域名购买成功提示")
    public static final long DOMAIN_TIP = 5L;
}
