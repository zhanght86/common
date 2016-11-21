package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 支付状态
 * Create at 2014年1月17日 下午5:16:11
 *
 * @author KETQI
 */
@Entity
@DiscriminatorValue("PAY_STATUS")
@BeanName(name = "财务-支付状态")
public class PayStatus extends Dictionary {
    private static final long serialVersionUID = -2472615195731787508L;

    @BeanAttrInfo(cnName = "等待支付")
    public static final long WAIT_PAY = 1;

    @BeanAttrInfo(cnName = "支付成功")
    public static final long PAY_SUCCESS = 2;
}
