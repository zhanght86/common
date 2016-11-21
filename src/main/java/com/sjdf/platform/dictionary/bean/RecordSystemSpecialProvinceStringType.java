package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年8月8日 下午5:19:48
 *
 * @author KETQI
 * @category 备案特殊省份(字符串)
 */
@Entity
@DiscriminatorValue("RECORD_SYSTEM_SPECIAL_PROVINCE_STRING_TYPE")
@BeanName(name = "备案特殊省份(字符串)")
public class RecordSystemSpecialProvinceStringType extends Dictionary {

    private static final long serialVersionUID = 2882157632658724033L;

    /** 不需要【邮寄资料】的省份：上海、四川、广西、湖南、河南、河北、新疆、福建、江苏、北京、贵州 */
    @BeanAttrInfo(orderBy = 1, cnName = "不需要【邮寄资料】的省份ID", value = "310000,510000,450000,430000,410000,130000,650000,350000,320000,110000,520000")
    public static final long NOT_NEED_MAILING_INFO_PROVINCE = 1;

    /** 可变更IP的省份：北京,天津,辽宁,江苏,浙江,安徽,江西,河南,广东,广西,重庆,四川,贵州,河北,上海,福建,云南 */
    @BeanAttrInfo(orderBy = 2, cnName = "可变更IP的省份ID", value = "110000,120000,210000,320000,330000,340000,360000,410000,440000,450000,500000,510000,520000,530000,310000,350000,130000")
    public static final long CAN_MODIFY_IP_PROVINCE = 2;

    /** 可变更IP并直接上报管局的省份：四川 */
    @BeanAttrInfo(orderBy = 3, cnName = "可变更IP并直接上报的省份ID", value = "510000")
    public static final long CAN_MODIFY_IP_AND_UPLOAD = 3;

    /** 不能在我司备案的省份:甘肃,黑龙江,山东,西藏,天津 */
    @BeanAttrInfo(orderBy = 4, cnName = "不能在我司备案的省份", value = "620000,230000,370000,540000,120000")
    public static final long CAN_NOT_RECORD_PROVINCE = 4;

    /** 需要网站负责人授权书的省份（前提法人和网站负责人姓名不一致）：湖南省，湖北省 */
    @BeanAttrInfo(orderBy = 5, cnName = "需要网站负责人授权书的省份（前提法人和网站负责人姓名不一致）", value = "430000,420000")
    public static final long NEED_WEB_RESP_WARRANT_CERTIFICATE_PROVINCE = 5;

    /** 主办单位性质为非个人，网站名称必须是主办单位名称的省份（即网站名称默认为主办单位名称，不能修改）：四川省 */
    @BeanAttrInfo(orderBy = 6, cnName = "主办单位性质为非个人，网站名称必须是主办单位名称的省份（即网站名称默认为主办单位名称，不能修改）", value = "510000")
    public static final long IMPERSONAL_WEBNAME_IS_ORGANIZER_PROVINCE = 6;

    /** 新增接入不用上报附件的省份： */
    @BeanAttrInfo(orderBy = 7, cnName = "新增接入不用上报附件的省份")
    public static final long NEW_ACCESS_NOT_REPORT_ATTACHMENT_PROVINCE = 7;

    /** 一个网站只能填写一个域名的省份：重庆 */
    @BeanAttrInfo(value = "500000", orderBy = 8, cnName = "一个网站只能填写一个域名")
    public static final long ONE_WEB_ONE_DOMAIN_PROVINCE = 8;

    /** 手机号码归属地要同主体所在市区一致的省份：辽宁 */
    @BeanAttrInfo(value = "210000", orderBy = 9, cnName = "手机号码归属地要同主体所在市区一致的省份")
    public static final long MOBILE_CITY_IS_SPONSOR_CITY_PROVINCE = 9;

    /** 个人备案需要本地户口(证件住所和身份证所属必须在本地省份)的省份：山西 */
    @BeanAttrInfo(value = "140000", orderBy = 10, cnName = "个人备案需要本地户口(证件住所和身份证所属必须在本地省份)的省份")
    public static final long PERSONAL_RECORD_NEED_LOCAL_PROVINCE = 10;

    /** 自动任务重复上报需要当天上报的省份： */
    @BeanAttrInfo(value = "", orderBy = 11, cnName = "自动任务重复上报需要当天上报的省份")
    public static final long AUTOTASK_REUPLOAD_NEED_TODAY_UPLOAD_PROVINCE = 11;

    /** 不能够进行中文转码域名备案的备案号省份字： */
    @BeanAttrInfo(value = "京", orderBy = 12, cnName = "不能够进行中文转码域名备案的备案号省份字")
    public static final long CAN_NOT_TRANSCODE_RECORD_PRO = 12;

    /** 境外域名不能够进行备案的省份： */
    @BeanAttrInfo(value = "110000", orderBy = 13, cnName = "境外域名不能够进行备案的省份")
    public static final long OUTSIDE_DOMAIN_NOT_RECORD_PRO = 13;

    /** 变更备案性质不能改变，主办单位名称不能改变的省份： */
    @BeanAttrInfo(value = "110000", orderBy = 14, cnName = "变更备案性质不能改变，主办单位名称不能改变的省份")
    public static final long NATURE_AND_NAME_NOT_CHANGE = 14L;

    /**
     * 特定单位性质非个人org的域名只能允许提交备案的省份，与【特定省份非个人org的域名允许提交备案的单位性质】组合使用
     *
     * @see com.sjdf.platform.dictionary.bean.RecordSystemConfigure.ORG_ONLY_ALLOW_RECORD_NATURE
     */
    @BeanAttrInfo(value = "500000", orderBy = 15, cnName = "特定单位性质非个人org的域名只能允许提交备案的省份")
    public static final long ORG_ONLY_ALLOW_RECORD_PRO = 15L;

    /** 直辖市省份ID集合： */
    @BeanAttrInfo(value = "110000,310000,120000,50000", orderBy = 16, cnName = "直辖市省份ID集合")
    public static final long MUNICIPALITY_PROVINCE = 16L;

    /** 需要上传域名证书到管局的省份ID集合  */
    @BeanAttrInfo(value = "320000", orderBy = 17, cnName = "需要上传域名证书到管局的省份ID集合")
    public static final long UPLOAD_DOMAIN_CERTIFICATE_PROVINCE = 17L;

    /** 需要主体负责人授权书的省份（前提法人和主体负责人姓名不一致）：四川省 */
    @BeanAttrInfo(orderBy = 18, cnName = "需要主体负责人授权书的省份（前提法人和主体负责人姓名不一致）", value = "510000")
    public static final long NEED_ZT_RESP_WARRANT_CERTIFICATE_PROVINCE = 18L;

    /** 需要上传主体负责人授权书到管局的省份（前提法人和主体负责人姓名不一致）：四川省  */
    @BeanAttrInfo(value = "510000", orderBy = 19, cnName = "需要上传主体负责人授权书到管局的省份")
    public static final long UPLOAD_ZT_RESP_WARRANT_CERTIFICATE_PROVINCE = 19L;

    /** 需要上传网站负责人授权书到管局的省份（（前提法人和网站负责人姓名不一致））：上海  */
    @BeanAttrInfo(value = "310000", orderBy = 20, cnName = "需要上传网站负责人授权书到管局的省份")
    public static final long UPLOAD_WEB_RESP_WARRANT_CERTIFICATE_PROVINCE = 20L;
}
