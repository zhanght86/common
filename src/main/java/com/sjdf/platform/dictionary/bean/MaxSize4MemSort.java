package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-11-29
 *
 * @author 邱忠福
 * @category 内存排序在数据库中取得的最大记录条数
 */
@Entity
@DiscriminatorValue("MAX_SIZE_FOR_MEM_SORT")
@BeanName(name = "内存排序在数据库中取得的最大记录条数")
public class MaxSize4MemSort extends Dictionary {
    private static final long serialVersionUID = -680973024213213526L;

    /** 内存排序在数据库中取得的最大记录条数 */
    @BeanAttrInfo(value = "1000", cnName = "内存排序在数据库中取得的最大记录条数")
    public static final long MAX_SIZE = 1;
}
