package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2013-3-15 上午10:34:42
 * 【域名解析服务器类别】类别
 *
 * @author 陈绍刚
 */
@Entity
@DiscriminatorValue("DNS_SERVER_TYPE")
@BeanName(name = "域名解析服务器类别")
public class DNSServerType extends Dictionary {

    private static final long serialVersionUID = 2033891787563261553L;

    /** DNS服务器 */
    @BeanAttrInfo(value = "DNS", orderBy = 1, cnName = "DNS服务器")
    public static final long DNS = 1;

    /** 转发服务器 */
    @BeanAttrInfo(value = "URL", orderBy = 2, cnName = "转发服务器")
    public static final long URL = 2;

    /** CDN专用服务器 */
    @BeanAttrInfo(value = "CDN", orderBy = 3, cnName = "CDN服务器")
    public static final long CDN = 3;

    public static boolean isDnsOrCdn(String value) {
        return isDns(value) || isCdn(value);
    }

    public static boolean isDns(String value) {
        return PlatformUtils.hasText(value) && value.equals(ConfigManager.getInstance().getValue(DNSServerType.class, DNSServerType.DNS));
    }

    public static boolean isCdn(String value) {
        return PlatformUtils.hasText(value) && value.equals(ConfigManager.getInstance().getValue(DNSServerType.class, DNSServerType.CDN));
    }
}
