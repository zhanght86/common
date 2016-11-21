package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author wzq
 * @category 虚拟主机首页推荐类型
 */
@Entity
@DiscriminatorValue("VHOST_RECOMMEND_TYPE")
@BeanName(name = "虚拟主机首页推荐类型")
public class VhostRecommendType extends Dictionary {
    private static final long serialVersionUID = -4315793611674860243L;

    /** 入门 */
    @BeanAttrInfo(value = "1", orderBy = 1, cnName = "入门配置")
    public static final long BASE = 1;

    /** 最受欢迎 */
    @BeanAttrInfo(value = "2", orderBy = 2, cnName = "最受欢迎的虚拟主机")
    public static final long BEST_WELCOMED = 2;

    /** 最热销 */
    @BeanAttrInfo(value = "3", orderBy = 3, cnName = "最热销的虚拟主机")
    public static final long BEST_DEMAND = 3;

}

