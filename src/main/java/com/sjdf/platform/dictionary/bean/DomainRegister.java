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
 * @Created 2012-12-24 上午11:22:55
 * @category 域名注册商
 */
@Entity
@DiscriminatorValue("DOMAIN_REGISTER")
@BeanName(name = "域名注册商")
public class DomainRegister extends Dictionary {
    private static final long serialVersionUID = 7506856599604218792L;

    /** 本站系统 */
    @BeanAttrInfo(orderBy = 1, cnName = "本站系统")
    public static final long LOCAL_SYSTEM = 1;

    /** 中国新网 */
    @BeanAttrInfo(orderBy = 2, value = "XIN NET TECHNOLOGY CORPORATION", cnName = "中国新网", enName = "北京新网数码信息技术有限公司")
    public static final long XINNET_COM = 2;

    /** 中国万网 */
    @BeanAttrInfo(orderBy = 3, value = "HICHINA ZHICHENG TECHNOLOGY LTD.", cnName = "中国万网", enName = "北京万网志成科技有限公司")
    public static final long NET_CN = 3;

    /** 商务中国 */
    @BeanAttrInfo(orderBy = 4, value = "BIZCN.COM, INC.", cnName = "商务中国", enName = "厦门东南融通在线科技有限公司(原厦门华商盛世网络有限公司)")
    public static final long BIZCN_COM = 4;

    /** 新网互联 */
    @BeanAttrInfo(orderBy = 5, value = "BEIJING INNOVATIVE LINKAGE TECHNOLOGY LTD. DBA DNS.COM.CN", cnName = "新网互联", enName = "北京新网互联科技有限公司")
    public static final long DNS_COM_CN = 5;

    /** 中资源 */
    @BeanAttrInfo(orderBy = 6, value = "XIAMEN CHINASOURCE INTERNET SERVICE CO., LTD.", cnName = "中资源", enName = "厦门市中资源网络服务有限公司")
    public static final long ZZY_CN = 6;

    /** 商务中国1 */
    @BeanAttrInfo(orderBy = 7, value = "BIZCN.COM, INC.", cnName = "商务中国1", enName = "厦门东南融通在线科技有限公司(原厦门华商盛世网络有限公司)")
    public static final long BIZ_CN = 7;

    /** 中国万网2 */
    @BeanAttrInfo(orderBy = 8, value = "HICHINA ZHICHENG TECHNOLOGY LTD.", cnName = "中国万网2", enName = "北京万网志成科技有限公司")
    public static final long NET_CN_2 = 8;

    /** 中国万网3 */
    @BeanAttrInfo(orderBy = 9, value = "HICHINA ZHICHENG TECHNOLOGY LTD.", cnName = "中国万网3", enName = "北京万网志成科技有限公司")
    public static final long NET_CN_3 = 9;

    /** 51hkidc */
    @BeanAttrInfo(orderBy = 10, value = "HKIDC INTERNATIONAL LIMITED", cnName = "51hkidc")
    public static final long HKIDC_COM = 10;

    /** 网局中国万网4 */
    @BeanAttrInfo(orderBy = 18, value = "HICHINA ZHICHENG TECHNOLOGY LTD.", cnName = "中国万网4", enName = "北京万网志成科技有限公司")
    public static final long NET_CN_4 = 18;

    /** 网局中国新网2 */
    @BeanAttrInfo(orderBy = 19, value = "XIN NET TECHNOLOGY CORPORATION", cnName = "中国新网2", enName = "北京新网数码信息技术有限公司")
    public static final long XINNET_COM_2 = 19;

    /** 网局新网互联2 */
    @BeanAttrInfo(orderBy = 20, value = "BEIJING INNOVATIVE LINKAGE TECHNOLOGY LTD. DBA DNS.COM.CN", cnName = "新网互联2", enName = "北京新网互联科技有限公司")
    public static final long DNS_COM_CN_2 = 20;

    /** 云工坊新网3 */
    @BeanAttrInfo(orderBy = 21, value = "XIN NET TECHNOLOGY CORPORATION", cnName = "云工坊新网", enName = "北京新网数码信息技术有限公司")
    public static final long XINNET_COM_3 = 21;

    /** 中国新网-云道 */
    @BeanAttrInfo(orderBy = 31, value = "XIN NET TECHNOLOGY CORPORATION", cnName = "中国新网-云道", enName = "北京新网数码信息技术有限公司")
    public static final long XINNET_COM_YUNDAO = 31;

    /** 新网互联-云道 */
    @BeanAttrInfo(orderBy = 32, value = "BEIJING INNOVATIVE LINKAGE TECHNOLOGY LTD. DBA DNS.COM.CN", cnName = "新网互联-云道", enName = "北京新网互联科技有限公司")
    public static final long DNS_COM_CN_YUNDAO = 32;

    /** 商务中国-云道 */
    @BeanAttrInfo(orderBy = 33, value = "BIZCN.COM, INC.", cnName = "商务中国-云道", enName = "厦门东南融通在线科技有限公司(原厦门华商盛世网络有限公司)")
    public static final long BIZ_CN_YUNDAO = 33;
}
