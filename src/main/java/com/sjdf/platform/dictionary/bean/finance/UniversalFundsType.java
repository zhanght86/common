package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 专用款类型
 * Create at 2014年1月16日 下午1:20:44
 *
 * @author KETQI
 */
@Entity
@DiscriminatorValue("UNIVERSAL_FUNDS_TYPE")
@BeanName(name = "财务-专用款类型")
public class UniversalFundsType extends Dictionary {
    private static final long serialVersionUID = 3878594148232857866L;

    @BeanAttrInfo(cnName = "优惠")
    public static final long DISCOUNT = 1;

    @BeanAttrInfo(cnName = "专用")
    public static final long UNIVERSAL = 2;

    @BeanAttrInfo(cnName = "包销")
    public static final long UNDERWRITE = 3;
}
