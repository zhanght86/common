package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年8月8日 下午5:19:03
 *
 * @author KETQI
 * @category 常用正则表达式匹配模式
 */
@Entity
@DiscriminatorValue("MATCH_PATTERN")
@BeanName(name = "常用正则表达式匹配模式")
public class MatchPattern extends Dictionary {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 2882157632658724022L;

    /** 电子邮件 */
    @BeanAttrInfo(value = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", orderBy = 1, cnName = "电子邮件")
    public static final long E_MAIL = 1;

    /** 办公电话（座机号码） */
    @BeanAttrInfo(value = "(086-)?(\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d{3}-\\d{8})", orderBy = 2, cnName = "办公电话（座机号码）")
    public static final long LANDLINE_NUMBER = 2;

    /** 手机号码 */
    @BeanAttrInfo(value = "^[1][3,4,5,7,8]+\\d{9}", orderBy = 3, cnName = "手机号码")
    public static final long MOBILE_PHONE_NUMBER = 3;

    /** 中文名输入 */
    @BeanAttrInfo(value = "^[\u4e00-\u9fa5]+$", orderBy = 4, cnName = "中文名输入")
    public static final long CHINESE_NAME = 4;

    /** 字符串中首尾空行或空格 */
    @BeanAttrInfo(value = "^\\s*|\\s*$", orderBy = 5, cnName = "字符串 中首尾空行或空格")
    public static final long BLANK_BEGIN_END = 5;

    /** HTML标签 */
    @BeanAttrInfo(value = "<(\\S*?)[^>]*>.*?</\\1>|<.*? />", orderBy = 6, cnName = "HTML标签")
    public static final long HTML_TAGS = 6;

    /** URL */
    @BeanAttrInfo(value = "[a-zA-z]+://[^\\s]*", orderBy = 7, cnName = "URL")
    public static final long URL = 7;

    /** IP */
    @BeanAttrInfo(value = "^(?:(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))$", orderBy = 8, cnName = "IP")
    public static final long IP = 8;

    /** ID */
    @BeanAttrInfo(value = "[a-zA-Z][a-zA-Z0-9_]{4,15}$", orderBy = 9, cnName = "ID")
    public static final long ID = 9;

    /** QQ */
    @BeanAttrInfo(value = "[1-9][0-9]{4,13}", orderBy = 10, cnName = "QQ")
    public static final long QQ = 10;

    /** 邮编 */
    @BeanAttrInfo(value = "[1-9]\\d{5}(?!\\d)", orderBy = 11, cnName = "邮编")
    public static final long POST_CODE = 11;

    /** 身份证 */
    @BeanAttrInfo(value = "^(^\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$", orderBy = 12, cnName = "身份证")
    public static final long ID_CARD = 12;

    /** 工商营业执照号码 */
    @BeanAttrInfo(value = "(\\d|\\-){13}|(\\d|\\-){15}", orderBy = 13, cnName = "工商营业执照号码")
    public static final long BUSINESS_NUM = 13;

    /** 工商营业执照号码内容 */
    @BeanAttrInfo(value = "\\d+\\-?+", orderBy = 14, cnName = "只包含数字和连接符（-）")
    public static final long ONLY_HYPHEN_AND_NUM = 14;

    /** 组织结构代码 */
    @BeanAttrInfo(value = "([a-zA-Z][0-9]{7})|([0-9]{8})|(([a-zA-Z]{2}[0-9]{6}))-[A-Za-z0-9]", orderBy = 15, cnName = "组织结构代码")
    public static final long ORGANIZATION_CERTIFICATE_NUM = 15;

    /** 限制仅为中文名或者英文名 */
    @BeanAttrInfo(value = "([\\u4E00-\\u9FFF]{2,})|([a-zA-Z ]+)", orderBy = 16, cnName = "限制仅为中文名或者英文名")
    public static final long CN_OR_ZH_NAME = 16;

    /** 限制仅为英文名 */
    @BeanAttrInfo(value = "[A-Z][a-z]*( |$)+", orderBy = 17, cnName = "限制仅为英文名")
    public static final long ENGLISH_NAME = 17;

    /** 15位身份证号码 */
    @BeanAttrInfo(value = "\\d{15}", orderBy = 18, cnName = "15位身份证号码")
    public static final long FIFTEEN_ID_CARD_NUM = 18;

    /** 限制仅为英文名 比如：YaoMing */
    @BeanAttrInfo(value = "([A-Z]|[a-z])*( |$)+", orderBy = 19, cnName = "限制仅为英文名（允许出现多个大写字母）")
    public static final long ENGLISH_NAME2 = 19;

    /** 域名格式 */
    @BeanAttrInfo(value = "^(((([a-z0-9\u4e00-\u9fa5]+-?)+[a-z0-9\u4e00-\u9fa5]+)|([a-z0-9\u4e00-\u9fa5]+))\\.)((((([a-z0-9\u4e00-\u9fa5]+-?)+[a-z0-9\u4e00-\u9fa5]+)|([a-z0-9\u4e00-\u9fa5]+))\\.))*((([a-z0-9\u4e00-\u9fa5]+-?)+[a-z0-9\u4e00-\u9fa5]+)|([a-z0-9\u4e00-\u9fa5]+))$", orderBy = 20, cnName = "域名格式")
    public static final long DOMAIN_FORMAT = 20;

    /** 通信地址 */
    @BeanAttrInfo(value = "[\\u4E00-\\u9FFF0-9a-zA-Z]{2,}", orderBy = 21, cnName = "通信地址")
    public static final long LETTET_ADDRESS = 21L;

    /** 特殊字符 */
    @BeanAttrInfo(value = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]", orderBy = 22, cnName = "特殊字符")
    public static final long SPECIAL_CHARACTER = 22L;

    /** 是否包含中文信息 */
    @BeanAttrInfo(value = ".*[\u4e00-\u9fa5]+.*$", orderBy = 23, cnName = "是否包含中文信息")
    public static final long IS_CONTAINS_CHINESE = 23;

    /** 6-20位必须包含数字和字母，允许包含下划线的字符串 */
    @BeanAttrInfo(value = "^(?!\\D+$)(?![^a-zA-Z]+$)\\w{6,20}$", orderBy = 24, cnName = "密码验证（6-20位必须包含数字和字母，允许包含下划线的字符串）")
    public static final long PASSWORD = 24;

    /** 只能包含汉字、英文、“_”和数字 */
    @BeanAttrInfo(value = "[\u4e00-\u9fa5\\w]{2,255}", orderBy = 25, cnName = "公司名称验证")
    public static final long COMPANY_NAME = 25;

    /** 手机号码 */
    @BeanAttrInfo(value = "^[0-9]{8}$", orderBy = 26, cnName = "香港手机号码")
    public static final long MOBILE_PHONE_NUMBER_FOR_HONGKONG = 26;

    /** 只能是数字，字母，中文及.·()（）《》 、*/
    @BeanAttrInfo(value = "[0-9a-zA-Z\\u4e00-\\u9fa5\\.\\·\\(\\)\\（\\）\\《\\》\\s\\、]+", orderBy = 27, cnName = "单位名称验证")
    public static final long REG_DWMC = 27;

    /** 只能是字母*/
    @BeanAttrInfo(value = "^[A-Za-z]+$", orderBy = 28, cnName = "英文字符验证")
    public static final long CHARACTER = 28;
}
