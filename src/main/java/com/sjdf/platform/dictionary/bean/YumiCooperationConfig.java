package com.sjdf.platform.dictionary.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

/**
 * 
 * 2016-02-18
 * @category 玉米网合作相关配置
 * @author wangpeng
 *
 */
@Entity
@DiscriminatorValue("YUMI_COOPERATION_CONFIG")
@BeanName(name = "玉米网合作相关配置")
public class YumiCooperationConfig extends Dictionary{

    private static final long serialVersionUID = -1122689222687250113L;

    /** 域名合作用户 */
    @BeanAttrInfo(value = "backorder@dnbiz.com", cnName = "域名合作用户")
    public static final long DOMAIN_COOPERATION_MEMBER = 1;

    /** 域名对域名开放天数，即域名保留天数 */
    @BeanAttrInfo(value = "3", cnName = "域名开放天数")
    public static final long DOMAIN_OPEN_DAYS = 2;

    @BeanAttrInfo(value=".cc,.com,.info,.mobi,.net,.org,.tv", cnName = "可预订的域名后缀")
    public static final long BOOKABLE_DOMAIN_SUFFIX = 3;

    @BeanAttrInfo(value="sjdf", cnName = "我司在账号在玉米网的APP KEY")
    public static final long API_KEY = 4;

    @BeanAttrInfo(value="NOdKKDTofF", cnName = "我司在账号在玉米网的私钥")
    public static final long PRIVATE_KEY = 5;

    @BeanAttrInfo(value="0.5", cnName = "增加价格比例，必须大于0.419，否则违约")
    public static final long INCREASE_PRICE_PROPORTION = 6;

    @BeanAttrInfo(value="8", cnName = "世纪东方自定义美元对人民币汇率，不得低于实际当前汇率")
    public static final long USD_TO_RMB_EXCHANGE_RATE = 7;

    @BeanAttrInfo(value="0.1", cnName = "违约赔付比例，不能低于0.1")
    public static final long BREACH_INDEMNITY_PROPORTION = 8;

    @BeanAttrInfo(value="一口价域名购买失败", cnName = "一口价域名购买失败")
    public static final long BUY_FAIL_ORDER_ITEM_NAME = 9;

    @BeanAttrInfo(value="http://open.yumi.com/api/rest/", cnName="玉米网接口请求地址")
    public static final long YUMI_POST_URL = 10;

    @BeanAttrInfo(value="1", cnName="功能开关：1开，2关")
    public static final long FUNCTION_SWITCH = 999;
}
