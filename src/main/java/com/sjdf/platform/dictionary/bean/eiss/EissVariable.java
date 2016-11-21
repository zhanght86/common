package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 主站系统可变配置
 */
@Entity
@DiscriminatorValue("EISS_VARIABLE")
@BeanName(name = "主站系统可变配置")
public class EissVariable extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = 5694650427448533026L;

    /** 财务接口连接地址 */
    @BeanAttrInfo(value = "http://apps.cp.51web.com/CaiWuGuanLi/caiWuApi.jsp", cnName = "财务接口连接地址")
    public static final long ACCOUNT_URL = 1L;

    /** 财务接口连接密码 */
    @BeanAttrInfo(value = "Noa~1mvu}RUINrKco;([yg`1Vtg0Y_cH", cnName = "财务接口连接密码")
    public static final long ACCOUNT_CONN_PWD = 2L;

    /** 当前activemq消息名(即当前系统代理会员名称) */
    @BeanAttrInfo(value = "eiss_new", cnName = "当前activemq消息名(即当前系统代理会员名称)")
    public static final long ACTIVE_MQ_MEMBER = 3L;

    /** cookieDomain */
    @BeanAttrInfo(value = ".51web.com", cnName = "cookieDomain")
    public static final long COOKIE_DOMAIN = 5L;

    /**
     * @category 营销QQ内容
     */
    @BeanAttrInfo(value = "请加企业QQ：4000280166为好友，联系业务员为您升级为VIP客户！享受我们优质的售后服务和完善的消费保障！", enName = "9:00-22:30", cnName = "营销QQ内容")
    public static final long MARKET_QQ = 6L;

    /** 超管后台logo地址 */
    @BeanAttrInfo(value = "http://apps.cp.51web.com/AdminLogin/layout_r1_c1.gif", cnName = "超管后台logo地址")
    public static final long EISS_ADMIN_PLATFORM_LOGOG_URL_ADDRESS = 7L;

    /** 超管后台底部文字 */
    @BeanAttrInfo(value = "成都世纪东方网络通信有限公司(http://www.51web.com) ©2002-2015 All Rights Reserved.", cnName = "超管后台底部文字")
    public static final long EISS_ADMIN_PLATFORM_FOOT_TEXT = 8L;
}
