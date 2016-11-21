package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 王鸣忠
 * @category 域名解析记录批量修改的类型
 */
@Entity
@DiscriminatorValue("DOMAIN_DNS_RES_EDIT_TYPE")
@BeanName(name = "域名解析记录批量修改的类型")
public class DomainDnsResEditType extends Dictionary {
    private static final long serialVersionUID = -7651712925573770661L;
    /** 主机记录 */
    @BeanAttrInfo(value = "1", cnName = "主机记录")
    public static final long HOST_RECORD = 1;
    /** 记录类型 */
    @BeanAttrInfo(value = "2", cnName = "记录类型")
    public static final long RECORD_TYPE = 2;
    /** 线路类型 */
    @BeanAttrInfo(value = "3", cnName = "线路类型")
    public static final long VIEW_TYPE = 3;
    /** 记录值 */
    @BeanAttrInfo(value = "4", cnName = "记录值")
    public static final long RECORD_VALUE = 4;
    /** 优先级 */
    @BeanAttrInfo(value = "5", cnName = "优先值")
    public static final long LEVEL = 5;
    /** 生效时间(TTL) */
    @BeanAttrInfo(value = "6", cnName = "生效时间(TTL)")
    public static final long TTL = 6;
    /** 转发 */
    @BeanAttrInfo(value = "7", cnName = "转发")
    public static final long URL_HIDDEN = 7;


}
