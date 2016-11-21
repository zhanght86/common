package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.ProductClass;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-05-27 15:27
 * 工单系统业务类型(虚拟主机、云主机、数据库、域名、邮局、IDC、备案、财务及会员)
 */
@Entity
@DiscriminatorValue("WORK_ORDER_BUSINESS_TYPE")
@BeanName(name = "工单系统业务类型")
public class WorkOrderBusinessType extends Dictionary {
    private static final long serialVersionUID = 7515603997111816138L;

    /** 虚拟主机 */
    @BeanAttrInfo(cnName = "虚拟主机")
    public static final long VHOST = ProductClass.VHOST;

    /** 邮局 */
    @BeanAttrInfo(cnName = "邮局")
    public static final long MAIL = ProductClass.MAIL;

    /** 数据库 */
    @BeanAttrInfo(cnName = "数据库")
    public static final long DATABASE = ProductClass.DATABASE;

    /** 备案 */
    @BeanAttrInfo(cnName = "备案")
    public static final long RECORD = 4;

    /** 域名 */
    @BeanAttrInfo(cnName = "域名")
    public static final long DOMAIN = ProductClass.DOMAIN;

    /** IDC */
    @BeanAttrInfo(cnName = "IDC")
    public static final long IDC = ProductClass.IDC;

    /** 云主机 */
    @BeanAttrInfo(cnName = "云主机")
    public static final long CHOST = ProductClass.CHOST;

    /** 微信 */
    @BeanAttrInfo(cnName = "微信")
    public static final long WEIXIN = ProductClass.WEIXIN;

    /** 财务&会员 */
    @BeanAttrInfo(cnName = "财务&会员")
    public static final long FINANCE_MEMBER = 20L;

    /** 业务咨询 */
    @BeanAttrInfo(cnName = "业务咨询")
    public static final long BUSINESS_CONSULTATION = 25L;


}
