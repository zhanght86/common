package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年10月28日 下午3:33:29
 *
 * @author KETQI
 * @category cnnic操作类型(value禁止修改)
 */
@Entity
@DiscriminatorValue("CNNIC_OPERATION_TYPE")
@BeanName(name = "cnnic操作类型")
public class CnnicOperationType extends Dictionary {
    private static final long serialVersionUID = 6993352804706114145L;

    /** 操作类型:域名转移操作 */
    @BeanAttrInfo(value = "domaintransfer", cnName = "域名转移")
    public static final long DOMAIN_TRANSFER = 1;

    /** 操作类型:域名删除操作 */
    @BeanAttrInfo(value = "domaindelete", cnName = "域名删除")
    public static final long DOMAIN_DELETE = 2;

    /** 操作类型:域名续费操作 */
    @BeanAttrInfo(value = "domainrenew", cnName = "域名续费")
    public static final long DOMAIN_RENEW = 3;

    /** 操作类型:注册人删除 */
    @BeanAttrInfo(value = "regdelete", cnName = "注册人删除")
    public static final long REG_DELETE = 4;
}
