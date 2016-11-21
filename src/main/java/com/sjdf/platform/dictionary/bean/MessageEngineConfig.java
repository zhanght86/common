/**
 *
 */
package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author ketqi
 *         value 标识使用平台的消息接收接口地址
 * @Created 2013-1-8 上午11:55:03
 * @category 消息引擎配置
 */
@Entity
@DiscriminatorValue("MESSAGE_ENGINE_CONFIG")
@BeanName(name = "消息引擎配置")
public class MessageEngineConfig extends Dictionary {
    private static final long serialVersionUID = -8458246775404620282L;

    /** 配置库 */
    @BeanAttrInfo(value = "http://client.51web.com/other/common/platform/MessageEngineAction!handle.action;http://beian.51web.com/other/common/platform/MessageEngineAction!handle.action", cnName = "配置库 ")
    public static final long CONFIGURATION_LIBRARY = 1;

    /** 邮件短信模板 */
    @BeanAttrInfo(value = "http://client.51web.com/other/common/platform/MessageEngineAction!handle.action;http://beian.51web.com/other/common/platform/MessageEngineAction!handle.action", cnName = "邮件短信模板")
    public static final long MAIL_SMS_TEMPLATE = 2;

    /** 地理位置信息 */
    @BeanAttrInfo(value = "http://client.51web.com/other/common/platform/MessageEngineAction!handle.action;http://beian.51web.com/other/common/platform/MessageEngineAction!handle.action", cnName = "地理位置信息")
    public static final long LOCATION = 3;

}
