package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author wangpeng
 * @category 微信支持的事件类型
 * @date 2014-04-09
 */
@Entity
@DiscriminatorValue("WEI_XIN_EVENT_TYPE")
@BeanName(name = "微信支持的事件类型")
public class WeiXinEventType extends Dictionary {

    private static final long serialVersionUID = 4355082210565041431L;

    @BeanAttrInfo(value = "SUBSCRIBE", cnName = "订阅")
    public static final long SUBSCRIBE = 1L;

    @BeanAttrInfo(value = "UNSUBSCRIBE", cnName = "取消订阅")
    public static final long UNSUBSCRIBE = 2L;

    @BeanAttrInfo(value = "SCAN", cnName = "扫描带参数二维码(用户已关注时)")
    public static final long SCAN = 3L;

    @BeanAttrInfo(value = "LOCATION", cnName = "上报地理位置")
    public static final long LOCATION = 4L;

    @BeanAttrInfo(value = "CLICK", cnName = "点击菜单拉取消息")
    public static final long CLICK = 5L;

    @BeanAttrInfo(value = "VIEW", cnName = "点击菜单跳转链接")
    public static final long VIEW = 6L;
}
