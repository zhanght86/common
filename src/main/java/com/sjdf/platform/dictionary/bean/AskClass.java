package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create 2012-09-05
 *
 * @author ketqi
 * @category 咨询反馈中的问题大类
 */
@Entity
@DiscriminatorValue("ASK_CLASS")
@BeanName(name = "咨询反馈问题大类")
public class AskClass extends Dictionary {
    private static final long serialVersionUID = -1747540970213296286L;

    /**
     * 会员问题
     *
     * @BeanAttrInfo(orderBy = 1, cnName = "会员问题")
     * public static final long MEMBER = 1;
     */

    /** 财务问题 */
    @BeanAttrInfo(value = "http://help.51web.com/caiwu", orderBy = 5, cnName = "财务问题")
    public static final long FINANCIAL = 5;

    /** 虚拟主机问题 */
    @BeanAttrInfo(value = "http://help.51web.com/vhost", orderBy = 10, cnName = "虚拟主机问题")
    public static final long VHOST = 10;

    /** 云主机问题 */
    @BeanAttrInfo(value = "http://help.51web.com/vps", orderBy = 15, cnName = "云主机问题")
    public static final long CHOST = 15;

    /** VPS主机问题 */
    @BeanAttrInfo(value = "http://help.51web.com/vps", orderBy = 20, cnName = "VPS主机问题")
    public static final long VPS = 20;

    /** 域名问题 */
    @BeanAttrInfo(value = "http://help.51web.com/domain", orderBy = 25, cnName = "域名问题")
    public static final long DOMAIN = 25;

    /** 邮局问题 */
    @BeanAttrInfo(value = "http://help.51web.com/mail", orderBy = 30, cnName = "邮局问题")
    public static final long MAIL = 30;

    /** 数据库问题 */
    @BeanAttrInfo(value = "http://help.51web.com/database", orderBy = 35, cnName = "数据库问题")
    public static final long DATABASE = 35;

    /** 备案问题 */
    @BeanAttrInfo(value = "http://help.51web.com/beian", orderBy = 40, cnName = "备案问题")
    public static final long RECORD = 40;

    /** IDC问题 */
    @BeanAttrInfo(value = "http://help.51web.com/vps", orderBy = 45, cnName = "IDC问题 ")
    public static final long IDC = 45;

    /** 代理平台问题 */
    @BeanAttrInfo(orderBy = 50, cnName = "代理平台问题")
    public static final long AGENT_PLATFORM = 50;

    /** 其他问题 */
    @BeanAttrInfo(orderBy = 1000, cnName = "其他问题")
    public static final long OTHERS = 1000;

}
