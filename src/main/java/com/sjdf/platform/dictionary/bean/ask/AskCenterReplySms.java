package com.sjdf.platform.dictionary.bean.ask;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create 2012-09-05
 *
 * @author ketqi
 * @category 咨询反馈回复时_短信内容
 */
@Entity
@DiscriminatorValue("ASK_CENTER_REPLY_SMS")
@BeanName(name = "咨询反馈回复时_短信内容")
public class AskCenterReplySms extends Dictionary {
    private static final long serialVersionUID = -1747540970213296286L;

    @BeanAttrInfo(value = "客户您好，您提交的问题已经处理好了.请查看。有问题及时联系我们，客服竭诚为您提供服务。", cnName = "已经处理好了.请查看")
    public static final long SMS_1 = 1;

    @BeanAttrInfo(value = "客户您好，您提交的问题还有不清楚的地方，现在还需要您的配合，请见消息后尽快查看咨询反馈，我们会竭诚为你提供服务", cnName = "需要您的配合")
    public static final long SMS_2 = 2;

    @BeanAttrInfo(value = "客户您好，您提交的问题目前暂时处理不到，已经跟踪给相关人员进行处理去了，请稍后查看结果或者向在线客服了解情况。", cnName = "跟踪给相关人员")
    public static final long SMS_3 = 3;

    @BeanAttrInfo(value = "客户您好，您所提交的问题我们正在处理中，稍后请注意查看回复，谢谢您对我们工作的支持！", cnName = "处理中")
    public static final long SMS_4 = 4;
}
