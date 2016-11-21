package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2014年1月17日 下午2:51:57
 * <p/>
 * 款项流动状态
 *
 * @author KETQI
 */
@Entity
@DiscriminatorValue("FINANCE_FLOW_STATUS")
@BeanName(name = "财务-款项流动状态")
public class FinanceFlowStatus extends Dictionary {
    private static final long serialVersionUID = -7819407035899668995L;

    @BeanAttrInfo(cnName = "收入")
    public static final long INCOME = 1;

    @BeanAttrInfo(cnName = "支出")
    public static final long DISBURSE = 2;
}
