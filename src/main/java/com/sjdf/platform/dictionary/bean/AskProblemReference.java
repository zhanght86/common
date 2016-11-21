package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-09-07
 *
 * @author ketqi
 * @category 咨询反馈相关问题参考
 */
@Entity
@DiscriminatorValue("Ask_Problem_Reference")
@BeanName(name = "咨询反馈相关问题参考")
public class AskProblemReference extends Dictionary {
    private static final long serialVersionUID = -3634124717966248285L;
    // =======================域名续费=========================
    // 万网域名过期续费及赎回
    @BeanAttrInfo(refClass = AskType.class, refAttr = AskType.DOMAIN_RENEW, value = "http://help.51web.com/view.php?id=416", cnName = "万网域名过期续费及赎回")
    public static final long DOMAIN_RENEW_1 = 2011;

    // 新网域名过期续费及赎回
    @BeanAttrInfo(refClass = AskType.class, refAttr = AskType.DOMAIN_RENEW, value = "http://help.51web.com/view.php?id=419", cnName = "新网域名过期续费及赎回")
    public static final long DOMAIN_RENEW_2 = 2012;

    // 新网互联域名过期续费及赎回
    @BeanAttrInfo(refClass = AskType.class, refAttr = AskType.DOMAIN_RENEW, value = "http://help.51web.com/view.php?id=421", cnName = "新网互联域名过期续费及赎回")
    public static final long DOMAIN_RENEW_3 = 2013;

    // 商务中国域名过期续费及赎回
    @BeanAttrInfo(refClass = AskType.class, refAttr = AskType.DOMAIN_RENEW, value = "http://help.51web.com/view.php?id=423", cnName = "商务中国域名过期续费及赎回")
    public static final long DOMAIN_RENEW_4 = 2014;

    // 中资源域名过期续费及赎回
    @BeanAttrInfo(refClass = AskType.class, refAttr = AskType.DOMAIN_RENEW, value = "http://help.51web.com/view.php?id=424", cnName = "中资源域名过期续费及赎回")
    public static final long DOMAIN_RENEW_5 = 2015;

    // 我会员下有足够的续费金额，但续费域名提示不成功，怎么办？
    @BeanAttrInfo(refClass = AskType.class, refAttr = AskType.DOMAIN_RENEW, value = "http://help.51web.com/view.php?id=455", cnName = "我会员下有足够的续费金额，但续费域名提示不成功，怎么办？")
    public static final long DOMAIN_RENEW_6 = 2016;

    // 我域名已经续费成功了，但是还是访问不了是怎么回事？
    @BeanAttrInfo(refClass = AskType.class, refAttr = AskType.DOMAIN_RENEW, value = "http://help.51web.com/view.php?id=456", cnName = "我域名已经续费成功了，但是还是访问不了是怎么回事？")
    public static final long DOMAIN_RENEW_7 = 2017;

}
