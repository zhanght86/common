package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author wangpeng
 * @category IP信息查询配置
 */
@Entity
@DiscriminatorValue("IP_INFO_SEARCH_CONF")
@BeanName(name = "IP信息查询配置")
public class IPInfoSearchConf extends Dictionary {

    private static final long serialVersionUID = 1600192489910762344L;

    /**
     * @category 当前使用的查询接口类
     */
    @BeanAttrInfo(value = "1", cnName = "当前使用的查询接口:1 收费接口,2 免费接口", enName = "currentIpInfoSearchApiClass")
    public static final long CURRENT_IP_INFO_SEARCH_CLASS = 1;

    /**
     * @category 付费接口url
     */
    @BeanAttrInfo(value = "http://api.ipip.net/ip/search?token=defa7542a3a68bb47e87d1badb834161ce033fc6&ip="
            , cnName = "付费接口url", enName = "payApiUrl")
    public static final long PAY_API_URL = 2;
}
