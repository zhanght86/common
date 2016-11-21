package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2014-08-14 10:28
 */
@Entity
@DiscriminatorValue("WORK_ORDER_DOMAIN_STATUS")
@BeanName(name = "工单域名状态")
public class WorkOrderDomainStatus extends Dictionary {
    private static final long serialVersionUID = -3751239613259855286L;

    /** 客户端hold */
    @BeanAttrInfo(cnName = "客户端hold")
    public static final long CLIENT_HOLD = 90L;

    /** 过期 */
    @BeanAttrInfo(cnName = "过期")
    public static final long STOP_EXPIRED = 95L;

    /** 过期后续费 */
    @BeanAttrInfo(cnName = "过期后续费")
    public static final long EXPIRED_RENEW = 93L;

    /** 赎回期 */
    @BeanAttrInfo(cnName = "赎回期")
    public static final long REDEEM = 100L;

    /** 否,无 */
    @BeanAttrInfo(cnName = "否,无")
    public static final long NO = 110L;

    /** 是,有 */
    @BeanAttrInfo(cnName = "是,有")
    public static final long YES = 111L;

    /** 木有解析记录-绑定我司产品 */
    @BeanAttrInfo(cnName = "木有解析记录-绑定我司产品")
    public static final long NO_YES = 112L;

    /** 木有解析记录-未绑定我司 */
    @BeanAttrInfo(cnName = "木有解析记录-未绑定我司")
    public static final long NO_NO = 113L;

    /** 有解析记录-未绑定我司产品 */
    @BeanAttrInfo(cnName = "有解析记录-未绑定我司产品")
    public static final long YES_NO = 114L;

    /** 有解析记录-绑定我司产品-解析一致 */
    @BeanAttrInfo(cnName = "有解析记录-绑定我司产品-解析一致")
    public static final long YES_YES_YES = 115L;

    /** 有解析记录-绑定我司产品-解析不一致 */
    @BeanAttrInfo(cnName = "有解析记录-绑定我司产品-解析不一致")
    public static final long YES_YES_NO = 116L;
}
