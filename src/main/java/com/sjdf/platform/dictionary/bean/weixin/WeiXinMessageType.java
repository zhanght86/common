package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author wangpeng
 * @category 微信支持的消息类型
 * @date 2014-04-09
 */
@Entity
@DiscriminatorValue("WEI_XIN_MESSAGE_TYPE")
@BeanName(name = "微信支持的消息类型")
public class WeiXinMessageType extends Dictionary {

    private static final long serialVersionUID = -3836398885764189448L;

    @BeanAttrInfo(value = "text", cnName = "文本消息")
    public static final long TEXT = 1L;

    @BeanAttrInfo(value = "image", cnName = "图片消息")
    public static final long IMAGE = 2L;

    @BeanAttrInfo(value = "voice", cnName = "语音消息")
    public static final long VOICE = 3L;

    @BeanAttrInfo(value = "video", cnName = "视频消息")
    public static final long VIDEO = 4L;

    @BeanAttrInfo(value = "music", cnName = "音乐消息")
    public static final long MUSIC = 5L;

    @BeanAttrInfo(value = "news", cnName = "图文消息")
    public static final long NEWS = 6L;

    @BeanAttrInfo(value = "location", cnName = "地理位置")
    public static final long LOCATION = 7L;

    @BeanAttrInfo(value = "link", cnName = "链接消息")
    public static final long LINK = 8L;

    @BeanAttrInfo(value = "event", cnName = "事件消息")
    public static final long EVENT = 9L;
}
