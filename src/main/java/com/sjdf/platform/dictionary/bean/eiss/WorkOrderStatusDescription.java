package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2014年5月4日 下午5:58:37
 * 工单系统参数配置
 *
 * @author KETQI
 */
@Entity
@DiscriminatorValue("WORK_ORDER_EVALUATED_CONFIG")
@BeanName(name = "工单系统已评价参数配置")
public class WorkOrderStatusDescription extends Dictionary {
    private static final long serialVersionUID = 2568442778133447964L;

    /** 已评价——但愿这是您的最后一个故障 */
    @BeanAttrInfo(value = "但愿这是您的最后一个故障！", cnName = "已评价——但愿这是您的最后一个故障！")
    public static final long WORK_ORDER_EVALUATED1 = 1;

    /** 已受理——感谢您对我们工作的评价 */
    @BeanAttrInfo(value = "感谢您对我们工作的评价！", cnName = "已评价—-感谢您对我们工作的评价！	")
    public static final long WORK_ORDER_EVALUATED2 = 2;

    /** 已受理——工单已收，万难无忧！ */
    @BeanAttrInfo(value = "希望您能满意我们的服务！", cnName = "已评价——希望您能满意我们的服务！")
    public static final long WORK_ORDER_EVALUATED3 = 3;
    /** 已受理——工单已收，万难无忧！ */
    @BeanAttrInfo(value = "我们的服务客官满意吗？", cnName = "已评价——我们的服务客官满意吗？")
    public static final long WORK_ORDER_EVALUATED4 = 4;
    /** 已受理——工单已收，万难无忧！ */
    @BeanAttrInfo(value = "们总想为您做的更多更好！！", cnName = "已评价——们总想为您做的更多更好！")
    public static final long WORK_ORDER_EVALUATED5 = 5;
}
