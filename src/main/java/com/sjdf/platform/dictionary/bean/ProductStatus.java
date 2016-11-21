package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-06-01
 * 产品状态
 *
 * @author 王正伟
 */
@Entity
@DiscriminatorValue("PRODUCT_STATUS")
@BeanName(name = "产品状态")
public class ProductStatus extends Dictionary {
    private static final long serialVersionUID = 2882157649969724165L;

    /** 运行中 */
    @BeanAttrInfo(value = "1", cnName = "运行中", enName = "executing")
    public static final long EXECUTING = 1;

    /** 处理中 */
    @BeanAttrInfo(value = "3", cnName = "处理中", enName = "handling")
    public static final long HANDLING = 3;

    /** 已删除 */
    @BeanAttrInfo(value = "6", cnName = "已删除", enName = "deleted")
    public static final long DELETED = 6;

    /** 管理员停止 */
    @BeanAttrInfo(value = "8", cnName = "管理员停止", enName = "admin stop")
    public static final long ADMIN_STOP = 8;

    /** 过期停止 */
    @BeanAttrInfo(value = "9", cnName = "过期停止", enName = "expired stop")
    public static final long EXPIRED_STOP = 9;
}
