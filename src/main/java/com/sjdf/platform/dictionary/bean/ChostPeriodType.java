package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author laberwu
 * @category 云主机周期类型
 * @ClassName ChostPeriodType
 * @Created 2012 2012-11-28 上午11:58:41
 */
@Entity
@DiscriminatorValue("CHOST_PERIOD_TYPE")
@BeanName(name = "云主机周期类型")
public class ChostPeriodType extends Dictionary {

    /**
     * @category
     * @field long serialVersionUID
     * @created 2012 2012-11-28 上午11:59:12
     */
    private static final long serialVersionUID = -7336760209776657798L;

    @BeanAttrInfo(value = "7", cnName = "初期")
    public static final long INITIAL_TYPE = 1;

    @BeanAttrInfo(value = "35", cnName = "调试期")
    public static final long DEBUG_TYPE = 2;

    @BeanAttrInfo(value = "99999999", cnName = "稳定期")
    public static final long STABLE_TYPE = 3;
}
