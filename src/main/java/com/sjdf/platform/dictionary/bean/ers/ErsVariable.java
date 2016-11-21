package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 备案系统可变配置
 */
@Entity
@DiscriminatorValue("ERS_VARIABLE")
@BeanName(name = "备案系统可变配置")
public class ErsVariable extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = -8185881168399598426L;

    /** logo图片地址 */
    @BeanAttrInfo(value = "/image/login/top_logo.gif", cnName = "logo图片地址")
    public static final long LOGO_IMG_URL = 1;

    /** 底部图片地址 */
    @BeanAttrInfo(value = "/image/login/top_logo.gif", cnName = "logo图片地址")
    public static final long FOOT_IMG_URL = 2;

    /** 底部信息 */
    @BeanAttrInfo(value = "<ul><li>成都世纪东方网络通信有限公司版权所有&nbsp;Copyright ©2002-2011&nbsp;ISP证编号：川B2-20030101号&nbsp;蜀ICP备07500933号</li><li>公司地址：成都市高新区天府三街218号峰汇中心2栋-1506（世纪东方）&nbsp;</li></ul>", cnName = "底部信息 ")
    public static final long FOOT_INFO = 3;

    /** 主站地址 */
    @BeanAttrInfo(value = "http://www.51web.com", cnName = "主站地址")
    public static final long EISS_URL = 4;

    /** 帮助中心地址 */
    @BeanAttrInfo(value = "http://help.51web.com", cnName = "帮助中心地址")
    public static final long HELP_URL = 5;

    /** 公司完整名称 */
    @BeanAttrInfo(value = "成都世纪东方网络通信有限公司", cnName = "公司完整名称")
    public static final long COMPANY_FULL_NAME = 6;

    /** 公司简称 */
    @BeanAttrInfo(value = "成都世纪东方", cnName = "公司简称")
    public static final long COMPANY_SIMPLE_NAME = 7;

    /** 不能包含的单位名称 */
    @BeanAttrInfo(value = "世纪东方", cnName = "不能包含的单位名称")
    public static final long NOT_CONTAIN_UNIT_NAME = 8;

    /** 备案系统联系电话号码 */
    @BeanAttrInfo(value = "000000", cnName = "备案系统联系电话号码")
    public static final long RECORD_SPECIAL_LINE = 9;

    /** 公司总机 */
    @BeanAttrInfo(value = "00000", cnName = "公司总机")
    public static final long COMPANY_SWITCHBOARD = 10;

    /** 是否是世纪东方 */
    @BeanAttrInfo(value = "1", cnName = "是否是世纪东方")
    public static final long WHETHER_SJDF = 11;

    /** 备案报备ID(企业标识) */
    @BeanAttrInfo(value = "0000", cnName = "备案报备ID(企业标识)")
    public static final long ISP_ID = 12;

    /** 备案密码 */
    @BeanAttrInfo(value = "0000", cnName = "备案密码")
    public static final long USER_PASS = 13;

    /** 接口密钥 */
    @BeanAttrInfo(value = "0000", cnName = "接口密钥")
    public static final long SECURITY_KEY = 14;

    /** 偏移量 */
    @BeanAttrInfo(value = "0000", cnName = "偏移量")
    public static final long SECURITY_IV = 15;

    /** 修改NDS链接地址 */
    @BeanAttrInfo(value = "0000", cnName = "修改NDS链接地址")
    public static final long MODIFY_DNS_URL = 16;

    /** 模拟登录备案域名 */
    @BeanAttrInfo(value = "test2.51web.com", cnName = "模拟登录备案域名")
    public static final long SIMULATION_LOGIN_DOMAIN = 17;

    /** 是否使用自己的接口 */
    @BeanAttrInfo(value = "1", cnName = "是否使用自己的接口")
    public static final long USE_OWN_INTERFACE = 18;

    /** 网站负责人图片相对web根目录地址 */
    @BeanAttrInfo(value = "download/webChiefPhoto/normal_check.jpg", cnName = "网站负责人图片相对web根目录地址")
    public static final long WEB_CHIEF_IMAG_URL = 19;

    /** 接入商名称(用于管局接口验证) */
    @BeanAttrInfo(value = "成都世纪东方网络通信有限公司", cnName = "接入商名称(用于管局接口验证)")
    public static final long ISP_NAME = 20;
}
