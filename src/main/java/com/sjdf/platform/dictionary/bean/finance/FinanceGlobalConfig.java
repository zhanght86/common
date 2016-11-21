package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;

/**
 * 财务系统全局配置
 * User: ketqi
 * Date: 2014-12-08 15:58
 */
@Entity
@DiscriminatorValue("FINANCE_GLOBAL_CONFIG")
@BeanName(name = "财务-全局配置")
public class FinanceGlobalConfig extends Dictionary {
    private static final long serialVersionUID = 2161113834620193630L;

    /** 是否显示订单编号1:显示,其他不显示 */
    @BeanAttrInfo(value = "2", cnName = "订单编号")
    public static final long SHOW_ORDER_NUMBER = 1L;

    /** 是否显示交易号1:显示,其他不显示 */
    @BeanAttrInfo(value = "1", cnName = "交易号")
    public static final long SHOW_TRANSACTION_NUMBER = 2L;

    /** 支出附件上传路径前缀 */
    @BeanAttrInfo(value = "/home/datacenter/finance/", cnName = "支出附件上传路径前缀")
    public static final long FINANCE_DATACENTER_PATH = 3L;

    /** 产品统计列表 FinanceProductClass */
    @BeanAttrInfo(value = "7,1,5,3,2,6,8", cnName = "产品统计列表")
    public static final long FINANCE_PRODUCT_CALC_LIST = 4L;

    /** 利润产品列表 FinanceProductClass */
    @BeanAttrInfo(value = "7,1,3", cnName = "利润产品列表")
    public static final long FINANCE_PROFIT_PRODUCT_CALC_LIST = 5L;

    /** 子系统充值URL */
    @BeanAttrInfo(value = "/user/cms/userCMSAction!pay.action", cnName = "子系统充值URL")
    public static final long FINANCE_SUB_SYSTEM_PAY_URL = 6L;

    /** 客户可开发票开始时间 */
    @BeanAttrInfo(value = "2015-01-01 00:00:00", cnName = "客户可开发票开始时间")
    public static final long CONSUME_INVOICE_BEGIN_TIME = 7L;

    /** 财务流水,发票统计开始时间 */
    @BeanAttrInfo(value = "2015-01-01 00:00:00", cnName = "财务流水,发票统计开始时间")
    public static final long COUNT_INVOICE_BEGIN_TIME = 8L;

    /** 发票申请类型;1:基于消费,2:基于入款 */
    @BeanAttrInfo(value = "1", cnName = "发票申请类型:1:消费,2:入款")
    public static final long INVOICE_CALC_TYPE = 9L;

    /** 税率 */
    @BeanAttrInfo(value = "1.06", cnName = "税率")
    public static final long TAX_RATE = 10L;

    /** 财务统计超管组别 */
    @BeanAttrInfo(value = "2", cnName = "财务统计超管组别")
    public static final long FINANCE_CALC_ADMIN_GROUP_ID_LIST = 11L;

    /** 是否显示订单编号 */
    public static boolean showOrderNumber() {
        String value = ConfigManager.getInstance().getValue(FinanceGlobalConfig.class, SHOW_ORDER_NUMBER);
        return "1".equals(value);
    }

    /** 是否显示交易号 */
    public static boolean showTransactionNumber() {
        String value = ConfigManager.getInstance().getValue(FinanceGlobalConfig.class, SHOW_TRANSACTION_NUMBER);
        return "1".equals(value);
    }

    /** 是否是基于入款类型的发票申请 */
    public static boolean isRemitInvoice() {
        String value = ConfigManager.getInstance().getValue(FinanceGlobalConfig.class, INVOICE_CALC_TYPE);
        return "2".equals(value);
    }

    /** 产品统计列表 */
    public static List<Long> getPrdouctCalcList() {
        String value = ConfigManager.getInstance().getValue(FinanceGlobalConfig.class, FINANCE_PRODUCT_CALC_LIST);
        return PlatformUtils.parse2LongList(value);
    }

    /** 利润产品列表 */
    public static List<Long> getProfitPrdouctCalcList() {
        String value = ConfigManager.getInstance().getValue(FinanceGlobalConfig.class, FINANCE_PROFIT_PRODUCT_CALC_LIST);
        return PlatformUtils.parse2LongList(value);
    }

    /** 财务统计超管组别列表 */
    public static List<Long> getFinanceCalcAdminGroupIdList() {
        String value = ConfigManager.getInstance().getValue(FinanceGlobalConfig.class, FINANCE_CALC_ADMIN_GROUP_ID_LIST);
        return PlatformUtils.parse2LongList(value);
    }

    /** 获取税率 */
    public static BigDecimal getTaxRate() {
        String value = ConfigManager.getInstance().getValue(FinanceGlobalConfig.class, TAX_RATE);
        return new BigDecimal(value);

    }
}
