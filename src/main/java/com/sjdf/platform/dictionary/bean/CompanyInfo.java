package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-04-06
 *
 * @author 王正伟
 * @category 公司信息
 */

@Entity
@DiscriminatorValue("COMPANY_INFO")
@BeanName(name = "公司信息")
public class CompanyInfo extends Dictionary {
    private static final long serialVersionUID = -6461302852745845945L;
    /** 寄件人所在省 */
    @BeanAttrInfo(value = "四川省", cnName = "省份")
    public static final long EXPRESS_DELIVERY_PROVINCE = 1;
    /** 寄件人所在市 */
    @BeanAttrInfo(value = "成都市", cnName = "城市")
    public static final long EXPRESS_DELIVERY_CITY = 2;
    /** 寄件人所在单位名称 */
    @BeanAttrInfo(value = "成都世纪东方网络通信有限公司", cnName = "所在单位名称")
    public static final long EXPRESS_DELIVERY_COMPANY_NAME = 3;
    /** 寄件人姓名 */
    @BeanAttrInfo(value = "邱红", cnName = "姓名")
    public static final long EXPRESS_DELIVERY_FROM = 4;
    /** 寄件人地址 */
    @BeanAttrInfo(value = "成都市高新区天府三街218号峰汇中心2栋-1506", cnName = "地址")
    public static final long EXPRESS_DELIVERY_ADDRESS = 5;
    /** 寄件人座机电话 */
    @BeanAttrInfo(value = "028-82001809", cnName = "座机电话")
    public static final long EXPRESS_DELIVERY_PHONE = 6;
    /** 寄件人手机号码 */
    @BeanAttrInfo(value = "15719455216", cnName = "手机号码")
    public static final long EXPRESS_DELIVERY_MOBILE_PHONE = 7;
    /** 公司名称 */
    @BeanAttrInfo(value = "成都世纪东方网络通信有限公司", cnName = "公司名称")
    public static final long COMPANY_NAME = 8;
    /** 公司地址 */
    @BeanAttrInfo(value = "成都市高新区天府三街218号峰汇中心2栋-1506", cnName = "公司地址")
    public static final long COMPANY_ADDRESS = 9;
    /** 公司电话 */
    @BeanAttrInfo(value = "4000-120-151&nbsp;&nbsp;&nbsp;&nbsp;028-82001809", cnName = "公司电话")
    public static final long COMPANY_TEL = 10;
    /** 公司邮箱 */
    @BeanAttrInfo(value = "service@51web.com", cnName = "公司邮箱")
    public static final long COMPANY_EMAIL = 11;
    /** 公司网站 */
    @BeanAttrInfo(value = "51web.com", cnName = "公司网站")
    public static final long COMPANY_SITE = 12;
    /** 公司标识 */
    @BeanAttrInfo(value = "sjdf", cnName = "公司标识")
    public static final long COMPANY_SIGN = 13;
    /** 传真 */
    @BeanAttrInfo(value = "021-33568880-802", cnName = "传真")
    public static final long COMPANY_FAX = 14;
}
