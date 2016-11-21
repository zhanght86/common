package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年10月24日 下午4:27:12
 *
 * @author KETQI
 * @category hkidc环境动态配置
 */
@Entity
@DiscriminatorValue("HKIDC_ENV_CONF")
@BeanName(name = "hkidc环境动态配置")
public class HkidcEnvConf extends Dictionary {
    private static final long serialVersionUID = 3419185334043857214L;

    /** cn英文域名正式接口路由;1:正常策略,2:抢注策略 */
    @BeanAttrInfo(value = "1", cnName = "cn英文域名正式接口路由;1:正常策略,2:抢注策略")
    public static final long CN_DOMAIN_API_ROUTING = 1;
}
