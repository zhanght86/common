package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author wangpeng
 * @category 域名攻击监控相关配置库
 * @date 2015-08-01
 */
@Entity
@DiscriminatorValue("DOMAIN_ATTACK_MONITOR")
@BeanName(name = "域名攻击监控配置库")
public class DomainAttackMonitor extends Dictionary {

    private static final long serialVersionUID = 6159954681045386309L;

    /**
     * @category 自动处理流程开关:0->关闭,1->打开
     */
    @BeanAttrInfo(enName = "switch", cnName = "自动处理开关", value = "1")
    public static final long SWITCH = 1;

    /**
     * @category 自动处理的域名加入DNS黑名单的默认备注
     */
    @BeanAttrInfo(enName = "defaultRemarks", cnName = "DNS黑名单的默认备注", value = "域名受攻击后进入自动处理流程，将该域名加入DNS黑名单")
    public static final long DNS_BLACKLIST_DEFAULT_REMARKS = 2;

    @BeanAttrInfo(enName = "defaultShowTime", cnName = "域名监控数据默认显示时长(单位小时)", value = "1")
    public static final long DEFAULT_SHOW_TIME = 3;
    /**
     * THRESHOLD为前缀的变量表示自动处理流程的触发阀值,当同时满足以下所有条件则进入自动处理流程。
     */
    @BeanAttrInfo(enName = "qpsThreshold", cnName = "qps阀值", value = "8000")
    public static final long THRESHOLD_QPS = 10;

    @BeanAttrInfo(enName = "rateThreshold", cnName = "rate阀值", value = "0.8")
    public static final long THRESHOLD_RATE = 11;

    @BeanAttrInfo(enName = "timesThreshold", cnName = "连续攻击次数阀值", value = "3")
    public static final long THRESHOLD_TIMES = 12;

    @BeanAttrInfo(enName = "thirdPartyDns", cnName = "域名被攻击后修改到的第三方DNS", value = "f1g1ns1.dnspod.net,f1g1ns2.dnspod.net")
    public static final long THIRD_PARTY_DNS = 20;
}
