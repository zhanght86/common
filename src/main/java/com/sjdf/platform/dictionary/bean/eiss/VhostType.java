package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 主机分类
 */
@Entity
@DiscriminatorValue("VHOST_TYPE")
@BeanName(name = "主机分类")
public class VhostType extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = 2528014011710648042L;

    /** 国内域名 */
    @BeanAttrInfo(value = "国内主机", cnName = "国内主机")
    public static final long TYPE = 1L;


}
