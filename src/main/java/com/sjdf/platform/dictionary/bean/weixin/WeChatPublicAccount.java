package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.CompanyClass;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WECHAT_APP_ID_SECRET")
@BeanName(name = "微信公众账号管理")
public class WeChatPublicAccount extends Dictionary {

    private static final long serialVersionUID = 3062866527317253404L;

    /**
     * @category 世纪东方的微信公众账号
     */
    @BeanAttrInfo(value = "sjdfowner@51web.com", cnName = "世纪东方的微信公众账号", enName = "http://client.51web.com/api/")
    public static final long SJDF = CompanyClass.SJDF;

    /**
     * @category 云工坊的微信公众账号
     */
    @BeanAttrInfo(value = "sjdfowner@51web.com", cnName = "云工坊的微信公众账号", enName = "http://client.51web.com/api/")
    public static final long YGF = CompanyClass.YGF;

    /**
     * @category 网居的微信公众账号
     */
    @BeanAttrInfo(value = "sjdfowner@51web.com", cnName = "网居的微信公众账号", enName = "http://client.e8088.com/api/")
    public static final long WJ = CompanyClass.WJ;

    /**
     * @category 世纪利信的微信公众账号
     */
    @BeanAttrInfo(value = "sjdfowner@51web.com", cnName = "世纪利信的微信公众账号", enName = "http://client.51web.com/api/")
    public static final long SJLX = CompanyClass.SJLX;
}
