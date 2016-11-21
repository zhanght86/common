package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-03-11
 *
 * @author 黄远昌
 * @category 软件库
 */
@Entity
@DiscriminatorValue("SOFTWARE_LIBRARY")
@BeanName(name = "软件库")
public class SoftwareLibrary extends Dictionary {
    private static final long serialVersionUID = 2938620502781642536L;

    /** 操作日志; */
    @BeanAttrInfo(value = "http://download.51chost.com", cnName = "软件库网址")
    public static final long URL = 1;
}
