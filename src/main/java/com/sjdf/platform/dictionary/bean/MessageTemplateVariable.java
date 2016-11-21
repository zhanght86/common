package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-08-31
 * <p/>
 * 邮件或短信模板标签变量
 *
 * @author Chen Mohan
 */
@Entity
@DiscriminatorValue("MESSAGE_TEMPLATE_VARIABLE")
@BeanName(name = "邮件或短信模板标签变量")
public class MessageTemplateVariable extends Dictionary {
    private static final long serialVersionUID = 3931476300415503354L;

    @BeanAttrInfo(value = "{DOMAIN}", cnName = "域名")
    public static final long DOMAIN = 1;

    @BeanAttrInfo(value = "{FTPUSER}", cnName = "FTP帐号")
    public static final long FTPUSER = 2;

    @BeanAttrInfo(value = "{EISS_USER_NAME}", cnName = "会员帐号")
    public static final long EISS_USER_NAME = 3;

    @BeanAttrInfo(value = "{EISS_USER_NUM}", cnName = "会员编号")
    public static final long EISS_USER_NUM = 4;

    @BeanAttrInfo(value = "{ERS_USER_NAME}", cnName = "备案帐号")
    public static final long ERS_USER_NAME = 5;

    @BeanAttrInfo(value = "{IP_ADDRESS}", cnName = "IP地址")
    public static final long IP_ADDRESS = 6;

    @BeanAttrInfo(value = "{BUY_DATE}", cnName = "购买日期")
    public static final long BUY_DATE = 7;

    @BeanAttrInfo(value = "{EXPIRE_DATE}", cnName = "过期日期")
    public static final long EXPIRE_DATE = 8;

    @BeanAttrInfo(value = "{DAY}", cnName = "天")
    public static final long DAY = 9;

    @BeanAttrInfo(value = "{MONTH}", cnName = "月")
    public static final long MONTH = 10;

    @BeanAttrInfo(value = "{WEEK}", cnName = "周")
    public static final long WEEK = 11;

    @BeanAttrInfo(value = "{YEAR}", cnName = "年")
    public static final long YEAR = 12;

    @BeanAttrInfo(value = "{PROVIDER}", cnName = "接入商")
    public static final long PROVIDER = 13;

    @BeanAttrInfo(value = "{HOST_IP_ADDRESS}", cnName = "解析IP")
    public static final long HOST_IP_ADDRESS = 14;

    @BeanAttrInfo(value = "{RECORD_IP_ADDRESS}", cnName = "备案IP")
    public static final long RECORD_IP_ADDRESS = 15;

    @BeanAttrInfo(value = "{PRODUCT_IP_ADDRESS}", cnName = "产品IP")
    public static final long PRODUCT_IP_ADDRESS = 16;

    @BeanAttrInfo(value = "{PRODUCT_CLASS}", cnName = "产品分类")
    public static final long PRODUCT_CLASS = 17;

    @BeanAttrInfo(value = "{PRODUCT_NAME}", cnName = "产品名称")
    public static final long PRODUCT_NAME = 18;

    @BeanAttrInfo(value = "{IDC_NAME}", cnName = "机房名称")
    public static final long IDC_NAME = 19;

    @BeanAttrInfo(value = "{SCAN_NUM}", cnName = "检测次数")
    public static final long SCAN_NUM = 20;

    @BeanAttrInfo(value = "{ERROR_NUM}", cnName = "不一致次数")
    public static final long ERROR_NUM = 21;

    @BeanAttrInfo(value = "{PROCESS_STATE}", cnName = "处理状态")
    public static final long PROCESS_STATE = 22;

    @BeanAttrInfo(value = "{DB_NAME}", cnName = "数据库名")
    public static final long DB_NAME = 23;

    @BeanAttrInfo(value = "{PASSWORD}", cnName = "密码")
    public static final long PASSWORD = 24;

    @BeanAttrInfo(value = "{VERIFICATION_CODE}", cnName = "验证码")
    public static final long VERIFICATION_CODE = 25;

    @BeanAttrInfo(value = "{PRODUCT_LOGIN_USER}", cnName = "产品账号")
    public static final long PRODUCT_LOGIN_USER = 26;

    @BeanAttrInfo(value = "{DIFFERENT_FROM_RECORD}", cnName = "备案不一致次数")
    public static final long DIFFERENT_FROM_RECORD = 27;

    @BeanAttrInfo(value = "{DIFFERENT_FROM_PRODUCT}", cnName = "解析不一致次数")
    public static final long DIFFERENT_FROM_PRODUCT = 28;

    @BeanAttrInfo(value = "{COUNT}", cnName = "计数")
    public static final long COUNT = 29;

    @BeanAttrInfo(value = "{TABLE}", cnName = "表格数据")
    public static final long TABLE = 30;

    @BeanAttrInfo(value = "{ALLOW_RECOVERY_STOP_BINDINGS_TIME}", cnName = "可恢复解绑时间")
    public static final long ALLOW_RECOVERY_STOP_BINDINGS_TIME = 31;

    @BeanAttrInfo(value = "{DENY_RECOVERY_STOP_BINDINGS_TIME}", cnName = "不可恢复解绑时间")
    public static final long DENY_RECOVERY_STOP_BINDINGS_TIME = 32;

    @BeanAttrInfo(value = "{ORDER_NUMBER}", cnName = "购买单号")
    public static final long ORDER_NUMBER = 33;

    @BeanAttrInfo(value = "{PRODUCT_INFO}", cnName = "产品信息（配置）")
    public static final long PRODUCT_INFO = 34;

    @BeanAttrInfo(value = "{PORT}", cnName = "端口")
    public static final long PORT = 35;

    @BeanAttrInfo(value = "{DATE_AND_TIME}", cnName = "日期和时间")
    public static final long DATE_AND_TIME = 36;

    @BeanAttrInfo(value = "{STOP_REASON}", cnName = "停止原因")
    public static final long STOP_REASON = 37;

    @BeanAttrInfo(value = "{LINK_ADDRESS}", cnName = "链接地址")
    public static final long LINK_ADDRESS = 38;

    @BeanAttrInfo(value = "{WHOIS_INFO}", cnName = "whois信息")
    public static final long WHOIS_INFO = 39;

    @BeanAttrInfo(value = "{REASON}", cnName = "原因等字符信息")
    public static final long REASON = 40;

    @BeanAttrInfo(value = "{PROVIDER_ERROR}", cnName = "接入商错误次数")
    public static final long PROVIDER_ERROR = 41;

    @BeanAttrInfo(value = "{UPDATE_CONTENT}", cnName = "修改内容")
    public static final long UPDATE_CONTENT = 42;

    @BeanAttrInfo(value = "{DOMAIN_ORDER_RECORD_TYPE}", cnName = "订单类型")
    public static final long DOMAIN_ORDER_RECORD_TYPE = 43;

    @BeanAttrInfo(value = "{COMMENTS}", cnName = "说明")
    public static final long COMMENTS = 44;

    @BeanAttrInfo(value = "{EMAIL_FOOTER_1}", cnName = "邮件结尾_1")
    public static final long EMAIL_FOOTER_1 = 45;

    @BeanAttrInfo(value = "{EMAIL_FOOTER_2}", cnName = "邮件结尾_2")
    public static final long EMAIL_FOOTER_2 = 46;

    @BeanAttrInfo(value = "{EMAIL_FOOTER_3}", cnName = "邮件结尾_3")
    public static final long EMAIL_FOOTER_3 = 47;

    @BeanAttrInfo(value = "{EMAIL_FOOTER_4}", cnName = "邮件结尾_4")
    public static final long EMAIL_FOOTER_4 = 48;

    @BeanAttrInfo(value = "{EMAIL_FOOTER_5}", cnName = "邮件结尾_5")
    public static final long EMAIL_FOOTER_5 = 49;

    @BeanAttrInfo(value = "{EXPRESS_COMPANY}", cnName = "物流公司")
    public static final long EXPRESS_COMPANY = 50;

    @BeanAttrInfo(value = "{UNIQUE_NUMBER}", cnName = "单号")
    public static final long UNIQUE_NUMBER = 51;

    @BeanAttrInfo(value = "{SPONSOR_NAME}", cnName = "主办单位名称")
    public static final long SPONSOR_NAME = 52;

    @BeanAttrInfo(value = "{AUDIT_OPINION}", cnName = "审核意见")
    public static final long AUDIT_OPINION = 53;

    @BeanAttrInfo(value = "{ADDRESS}", cnName = "通信地址")
    public static final long ADDRESS = 54;

    @BeanAttrInfo(value = "{PRINCIPAL}", cnName = "负责人")
    public static final long PRINCIPAL = 55;

    @BeanAttrInfo(value = "{PRODUCT_TYPE}", cnName = "产品小分类")
    public static final long PRODUCT_TYPE = 56;

    @BeanAttrInfo(value = "{MOBILE}", cnName = "手机号码")
    public static final long MOBILE = 57;

    @BeanAttrInfo(value = "{ACCOUNT}", cnName = "公众账号")
    public static final long ACCOUNT = 58;

    @BeanAttrInfo(value = "{CURRENT_SIZE}", cnName = "当前大小")
    public static final long CURRENT_SIZE = 59;

    @BeanAttrInfo(value = "{QUOTA_SIZE}", cnName = "配额大小")
    public static final long QUOTA_SIZE = 60;

    @BeanAttrInfo(value = "{STATUS}", cnName = "状态")
    public static final long STATUS = 61;

    @BeanAttrInfo(value = "{SOLUTION}", cnName = "解决方案")
    public static final long SOLUTION = 62;

    @BeanAttrInfo(value = "{PERCENT1}", cnName = "第一级返款比例")
    public static final long PERCENT1 = 65;

    @BeanAttrInfo(value = "{PERCENT2}", cnName = "第二级返款比例")
    public static final long PERCENT2 = 66;

    @BeanAttrInfo(value = "{PERCENT3}", cnName = "第三级返款比例")
    public static final long PERCENT3 = 67;

    @BeanAttrInfo(value = "{PERCENT4}", cnName = "第四级返款比例")
    public static final long PERCENT4 = 68;

    @BeanAttrInfo(value = "{PERCENT5}", cnName = "第五级返款比例")
    public static final long PERCENT5 = 69;

    @BeanAttrInfo(value = "{DELAY1}", cnName = "第一级延长")
    public static final long DELAY1 = 70;

    @BeanAttrInfo(value = "{DELAY2}", cnName = "第二级延长")
    public static final long DELAY2 = 71;

    @BeanAttrInfo(value = "{DELAY3}", cnName = "第三级延长")
    public static final long DELAY3 = 72;

    @BeanAttrInfo(value = "{DELAY4}", cnName = "第四级延长")
    public static final long DELAY4 = 73;

    @BeanAttrInfo(value = "{DELAY5}", cnName = "第五级延长")
    public static final long DELAY5 = 74;

    @BeanAttrInfo(value = "{RECOMMENDCODE}", cnName = "推荐码")
    public static final long RECOMMENDCODE = 75;

    @BeanAttrInfo(value = "{EMAIL}", cnName = "电子邮箱")
    public static final long EMAIL = 76;

    @BeanAttrInfo(value = "{COMPANYNAME}", cnName = "企业名字")
    public static final long COMPANYNAME = 77;

    @BeanAttrInfo(value = "{TEMP_VARIABLE}", cnName = "临时参数")
    public static final long TEMP_VARIABLE = 999;

    @BeanAttrInfo(value = "{RECOVERYDATE}", cnName = "网卡恢复时间")
    public static final long RECOVERYDATE = 80;

    @BeanAttrInfo(value = "{USER_QQ}", cnName = "QQ号码")
    public static final long USER_QQ = 81;

    @BeanAttrInfo(value = "{PRODUCT_PRICE}", cnName = "产品价格")
    public static final long PRODUCT_PRICE = 82;

    @BeanAttrInfo(value = "{BUSINESS_ADMIN_NAME}", cnName = "业务专员名称")
    public static final long BUSINESS_ADMIN_NAME = 83;

    @BeanAttrInfo(value = "{BUSINESS_ADMIN_PHONE}", cnName = "业务专员电话号码")
    public static final long BUSINESS_ADMIN_PHONE = 84;

    @BeanAttrInfo(value = "{COMPANY_COUNTRY}", cnName = "公司所在区县")
    public static final long COMPANY_COUNTRY = 100;

    @BeanAttrInfo(value = "{ICBC_NAME}", cnName = "工商专员名称")
    public static final long ICBC_NAME = 101;

    @BeanAttrInfo(value = "{ICBC_TEL}", cnName = "工商专员电话")
    public static final long ICBC_TEL = 102;

    @BeanAttrInfo(value = "{BANK_NAME}", cnName = "银行专员名称")
    public static final long BANK_NAME = 103;

    @BeanAttrInfo(value = "{BANK_TEL}", cnName = "银行专员电话")
    public static final long BANK_TEL = 104;

    @BeanAttrInfo(value = "{TAX_NAME}", cnName = "税务专员名称")
    public static final long TAX_NAME = 105;

    @BeanAttrInfo(value = "{TAX_TEL}", cnName = "税务专员电话")
    public static final long TAX_TEL = 106;

    @BeanAttrInfo(value = "{ACCOUNT_NAME}", cnName = "记账专员名称")
    public static final long ACCOUNT_NAME = 107;

    @BeanAttrInfo(value = "{ACCOUNT_TEL}", cnName = "记账专员电话")
    public static final long ACCOUNT_TEL = 108;

    @BeanAttrInfo(value = "{OPPERATOR_NAME}", cnName = "经办人姓名")
    public static final long OPPERATOR_NAME = 109;

    @BeanAttrInfo(value = "{OPPERATOR_TEL}", cnName = "经办人电话")
    public static final long OPPERATOR_TEL = 110;

    @BeanAttrInfo(value = "{LEGAL_NAME}", cnName = "法人姓名")
    public static final long LEGAL_NAME = 111;

    @BeanAttrInfo(value = "{LEGAL_TEL}", cnName = "法人电话")
    public static final long LEGAL_TEL = 112;

    @BeanAttrInfo(value = "{NO_REPORT_NUM}", cnName = "记账专员的未纳税申报企业个数")
    public static final long NO_REPORT_NUM = 113;

    @BeanAttrInfo(value = "{FINANCE_NAME}", cnName = "财务负责人姓名")
    public static final long FINANCE_NAME = 114;

    @BeanAttrInfo(value = "{REPORT_DATE}", cnName = "申报日期")
    public static final long REPORT_DATE = 115;

    @BeanAttrInfo(value = "{COMPANY_SHORTNAME}", cnName = "企业简称")
    public static final long COMPANY_SHORTNAME = 116;

    @BeanAttrInfo(value = "{NEXT_HANDLE_DATE}", cnName = "下次办理时间")
    public static final long NEXT_HANDLE_DATE = 117;

    @BeanAttrInfo(value = "{LAND_TAX_NAME}", cnName = "公司所属地税局")
    public static final long LAND_TAX_NAME = 118;

    @BeanAttrInfo(value = "{IDC_ORDER_TYPE}", cnName = "订单类型")
    public static final long IDC_ORDER_TYPE = 119;

    @BeanAttrInfo(value = "{IDC_ORDER_NUM}", cnName = "订单编号")
    public static final long IDC_ORDER_NUM = 120;
}
