package com.sjdf.platform.dictionary.bean.wj;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WJ_REFUND_REASON")
@BeanName(name = "网居退款原因")
public class RefundReason extends Dictionary{

    private static final long serialVersionUID = 7827848661609208292L;

    @BeanAttrInfo(cnName = "缺货" , orderBy = 1)
    public static final long OUT_OF_STOCK = 1;

    @BeanAttrInfo(cnName = "产品质量原因-无法远程" , orderBy = 2)
    public static final long CANNOT_REMOTE = 2;

    @BeanAttrInfo(cnName = "产品质量原因-配置无法满足需求" , orderBy = 3)
    public static final long DONT_MEET_REQUIREMENTS = 3;

    @BeanAttrInfo(cnName = "暂时不需要此产品" , orderBy = 4)
    public static final long NO_NEED = 4;

    @BeanAttrInfo(cnName = "自己不会使用" , orderBy = 5)
    public static final long NOT_USE = 5;

    @BeanAttrInfo(cnName = "没有需要的系统" , orderBy = 6)
    public static final long MISSING_SYSTEM = 6;

    @BeanAttrInfo(cnName = "服务态度不好" , orderBy = 7)
    public static final long SERVICE_NOT_GOOD = 7;

    @BeanAttrInfo(cnName = "产品不稳定" , orderBy = 2)
    public static final long PRODUCT_NOT_STABLE = 8;
}
