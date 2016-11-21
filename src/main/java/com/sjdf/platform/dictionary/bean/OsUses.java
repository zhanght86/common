package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 王鸣忠
 * @category 操作系统用途
 * @date 2014-9-2
 */
@Entity
@DiscriminatorValue("OS_USES")
@BeanName(name = "操作系统用途")
public class OsUses extends Dictionary {

    private static final long serialVersionUID = 1853183068577134517L;

    /** 世纪东方专用 (世纪东方专用，其他子公司不用) */
    @BeanAttrInfo(value = "1", cnName = "世纪东方专用", orderBy = 1)
    public static final long SJDF_USE = 1;

    /** OEM 专用 (子公司专用（指定代理用户），世纪东方无法用) */
    @BeanAttrInfo(value = "2", cnName = "OEM 专用", orderBy = 2)
    public static final long OEM_USE = 2;

    /** 通用 (世纪东方+指定代理用户) */
    @BeanAttrInfo(value = "3", cnName = "通用", orderBy = 3)
    public static final long COMMON_USE = 3;
}
