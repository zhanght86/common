package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 公共配置
 * User: ketqi
 * Date: 2015-05-20 17:21
 */
@Entity
@DiscriminatorValue("SJLX_COMMON_CONFIG")
@BeanName(name = "sjlx-公共配置")
public class CommonConfig extends Dictionary {
    private static final long serialVersionUID = -7022426914988788575L;

    /** 会员头像存放路径 */
    @BeanAttrInfo(value = "/home/wwwroot/spi.cdnhost.cn/sjlx/", cnName = "会员头像存放路径")
    public static final long MEMBER_PHOTO_PATH = 1;

    /** 公司附件存放路径 */
    @BeanAttrInfo(value = "/home/datacenter/sjlx/cms/company/", cnName = "公司附件存放路径")
    public static final long COMPANY_ATTATHMENT_PATH = 2;

    /** 附件支持的文件格式 */
    @BeanAttrInfo(value = ".doc,.txt,.xls,.xlsx,.zip,.rar,.jpg,.gif,.png,.jpeg,.bmp", cnName = "附件支持的文件格式")
    public static final long ATTACHMENT_FORMAT = 10;

    /** 附件支持的文件大小,单位K */
    @BeanAttrInfo(value = "10240", cnName = "附件支持的文件大小,单位K")
    public static final long ATTACHMENT_LENGTH = 11;


    /** 一般纳税的地税营业税0.03 */
    @BeanAttrInfo(value = "0.03", cnName = "一般纳税的地税营业税0.03")
    public static final long COMPANY_BUSINESS_TAX = 28;

    /** 一般纳税的地税营业税大于30000 */
    @BeanAttrInfo(value = "30000", cnName = "一般纳税的地税营业税大于30000")
    public static final long COMPANY_VAT = 29;
    /** 城市建设附加费 */
    @BeanAttrInfo(value = "0.07", cnName = "一般纳税的城市建设附加费")
    public static final long CITY = 30;

    /** 教育附加费 */
    @BeanAttrInfo(value = "0.03", cnName = "一般纳税的教育附加费")
    public static final long EDU = 31;

    /** 地方教育附加费 */
    @BeanAttrInfo(value = "0.02", cnName = "一般纳税的地方教育附加费")
    public static final long LOCAL = 32;

    /** 价调基金 */
    @BeanAttrInfo(value = "0.0007", cnName = "一般纳税的价调基金")
    public static final long PRICEADJUSTMENT = 33;

    /** 税额 */
    @BeanAttrInfo(value = "0.17", cnName = "一般纳税的销项税额参数 0.17")
    public static final long TAX = 34;


    /**************************小规模**********************/

    /** 营业收入大于30000 */
    @BeanAttrInfo(value = "30000", cnName = "小规模地税营业税")
    public static final long SCOMPANY_VAT = 35;
    /** 城市建设附加费 */
    @BeanAttrInfo(value = "0.07", cnName = "小规模城市建设附加费")
    public static final long TYPE1CITY = 36;

    /** 教育附加费 */
    @BeanAttrInfo(value = "0.03", cnName = "小规模教育附加费")
    public static final long TYPE1EDU = 37;

    /** 地方教育附加费 */
    @BeanAttrInfo(value = "0.02", cnName = "小规模地方教育附加费")
    public static final long TYPE1LOCAL = 38;

    /** 价调基金 */
    @BeanAttrInfo(value = "0.0007", cnName = "小规模价调基金")
    public static final long TYPE1PRICEADJUSTMENT = 39;
    /** 小规模地税营业税 */
    @BeanAttrInfo(value = "0.03", cnName = "小规模地税营业税")
    public static final long LAND_TAX = 40;

    /** 小规模国税（增值税） */
    @BeanAttrInfo(value = "90000", cnName = "小规模国税（增值税）")
    public static final long SGCOMPANY_VAT = 41;
    /** 小规模国税（增值税）应税收入 */
    @BeanAttrInfo(value = "1.03", cnName = "小规模国税（增值税）应税收入")
    public static final long SBUSINESS_INCOME = 42;
    /** 小规模地税营业税0.05 */
    @BeanAttrInfo(value = "0.05", cnName = "小规模地税营业税0.05")
    public static final long LAND_TAX5 = 43;

    /** 到期通知时间如：15,7 */
    @BeanAttrInfo(value = "15,7", cnName = "到期通知时间如：15,7")
    public static final long EXPIRE_DAYS = 45;

    /** 投资人数限制 */
    @BeanAttrInfo(value = "10", cnName = "投资人数限制")
    public static final long INVESTMENT_SIZE_LIMIT = 46;

    /** 核名提交后管理员接收短信,手机列表 */
    @BeanAttrInfo(value = "18602884088,15982158742", cnName = "核名提交后管理员接收短信,手机列表")
    public static final long ADMIN_MOBILE_LIST = 50;

    /** 每个月5号 */
    @BeanAttrInfo(value = "5", cnName = "每个月5号")
    public static final long MONTH_OF_DAY5 = 51;

    /** 每个月13号 */
    @BeanAttrInfo(value = "13", cnName = "每个月13号")
    public static final long MONTH_OF_DAY13 = 52;

    /** 核名后定位产品大类，小类 */
    @BeanAttrInfo(value = "274,29", cnName = "核名后定位产品大类，小类")
    public static final long PRODUCT_LOACTION = 53;
    
    @BeanAttrInfo(value = "9" , cnName = "电话前缀")
    public static final long PHONE_PREFIX = 54;
    
    @BeanAttrInfo(value = "028" , cnName = "成都区号")
    public static final long AREA_CODE = 55;
    
    @BeanAttrInfo(value = "http://www.ip138.com:8080/search.asp?action=mobile&mobile=%s", cnName = "手机归属地查询API")
    public static final long PHONE_ADDRESS = 56;

    @BeanAttrInfo(value = "7" , cnName = "自动派单时间阀值")
    public static final long AUTO_ASSIGN_DAYS = 58;

    @BeanAttrInfo(value = "四川省" , cnName = "默认省份")
    public static final long DEFAULT_PROVINCE = 59;

    @BeanAttrInfo(value = "成都市" , cnName = "默认城市")
    public static final long DEFAULT_CITY = 60;

    @BeanAttrInfo(value = "10,11" , cnName = "默认可选附件")
    public static final long DEFAULT_OPT_ATT = 62;

    /** 投资人数限制 */
    public static long getInvestmentSizeLimit() {
        CommonConfig config = ConfigManager.getInstance().getDictionary(CommonConfig.class, CommonConfig.INVESTMENT_SIZE_LIMIT);
        if (config != null) {
            return config.getLongValue();
        }
        return 10L;
    }
}
