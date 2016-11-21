package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年10月28日 下午3:38:36
 *
 * @author KETQI
 * @category cnnic资料类型(value禁止修改)
 */
@Entity
@DiscriminatorValue("CNNIC_MATERIAL_TYPE")
@BeanName(name = "cnnic资料类型")
public class CnnicMaterialType extends Dictionary {
    private static final long serialVersionUID = 6848454510006864010L;

    /** 资料类型:域名备案承诺书 */
    @BeanAttrInfo(value = "matba", cnName = "域名备案承诺书")
    public static final long MAT_BA = 1;

    /** 资料类型:gov域名申请表 */
    @BeanAttrInfo(value = "matgov", cnName = "gov域名申请表")
    public static final long MAT_GOV = 2;

    /** 资料类型:中文域名资料 */
    @BeanAttrInfo(value = "matcdn", cnName = "中文域名资料")
    public static final long MAT_CDN = 3;

    /** 资料类型:注册人资料 */
    @BeanAttrInfo(value = "matreg", cnName = "注册人资料")
    public static final long MATR_REG = 4;
}
