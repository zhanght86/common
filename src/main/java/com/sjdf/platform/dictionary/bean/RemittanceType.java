package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2013-07-01 14:06
 * 汇款类型, 1:标识银行;英文名称和值不允许修改,财务历史数据问题
 */
@Entity
@DiscriminatorValue("REMITTANCE_TYPE")
@BeanName(name = "汇款类型")
public class RemittanceType extends Dictionary {
    private static final long serialVersionUID = -3215002262149526664L;
    /** 工行 */
    @BeanAttrInfo(value = "1", cnName = "工行", enName = "ICBC")
    public static final long ICBC_TYPE = 1;
    /** 建行 */
    @BeanAttrInfo(value = "1", cnName = "建行", enName = "CCB")
    public static final long CCB_TYPE = 2;
    /** 农行 */
    @BeanAttrInfo(value = "1", cnName = "农行", enName = "ABC")
    public static final long ABC_TYPE = 3;
    /** 招行 */
    @BeanAttrInfo(value = "1", cnName = "招行", enName = "CMB")
    public static final long CMB_TYPE = 4;
    /** 交行 */
    @BeanAttrInfo(value = "1", cnName = "交行", enName = "BCOM")
    public static final long BCOM_TYPE = 5;
    /** 兴业 */
    @BeanAttrInfo(value = "1", cnName = "兴业", enName = "CIB")
    public static final long CIB_TYPE = 6;
    /** 中行 */
    @BeanAttrInfo(value = "1", cnName = "中行", enName = "BOC")
    public static final long BOC_TYPE = 7;
    /** 盛付通 */
    @BeanAttrInfo(value = "0", cnName = "盛付通", enName = "SHENGPAY")
    public static final long SHENGPAY_TYPE = 23;
    /** 对公 */
    @BeanAttrInfo(value = "1", cnName = "对公", enName = "ABC_Company")
    public static final long ABC_COMPANY_TYPE = 12;
    /** 邮储 */
    @BeanAttrInfo(value = "1", cnName = "邮储", enName = "PSBC")
    public static final long PSBC_TYPE = 13;
    /** 电汇 */
    @BeanAttrInfo(value = "1", cnName = "电汇", enName = "DianHui")
    public static final long DIAN_HUI_TYPE = 15;
    /** QQ */
    @BeanAttrInfo(value = "0", cnName = "QQ", enName = "Tenpay_person")
    public static final long TENPAY_PERSON_TYPE = 22;
    /** 财付通 */
    @BeanAttrInfo(value = "0", cnName = "财付通", enName = "TENPAY")
    public static final long TENPAY_TYPE = 17;
    /** 支付宝 */
    @BeanAttrInfo(value = "0", cnName = "支付宝", enName = "ALIPAY")
    public static final long ALIPAY_TYPE = 18;
    /** 快钱 */
    @BeanAttrInfo(value = "0", cnName = "快钱", enName = "BILL")
    public static final long BILL_TYPE = 19;
    /** 现金 */
    @BeanAttrInfo(value = "1", cnName = "现金", enName = "CASH")
    public static final long CASH_TYPE = 20;
    /** 华夏 */
    @BeanAttrInfo(value = "1", cnName = "华夏", enName = "HXB")
    public static final long HXB_TYPE = 21;
    /** 民生 */
    @BeanAttrInfo(value = "1", cnName = "民生", enName = "CMBC")
    public static final long CMBC_TYPE = 24;
    /** 中信 */
    @BeanAttrInfo(value = "1", cnName = "中信", enName = "CITIC")
    public static final long CITIC_TYPE = 25;
    /** 深圳发展 */
    @BeanAttrInfo(value = "1", cnName = "深圳发展", enName = "SDB")
    public static final long SDB_TYPE = 26;
    /** 光大 */
    @BeanAttrInfo(value = "1", cnName = "光大", enName = "CEB")
    public static final long CEB_TYPE = 27;
    /** 广东发展 */
    @BeanAttrInfo(value = "1", cnName = "广东发展", enName = "GDB")
    public static final long GDB_TYPE = 28;
    /** 浦发银行 */
    @BeanAttrInfo(value = "1", cnName = "浦发", enName = "SPDB")
    public static final long SPDB_TYPE = 31;

    /** 主站转账 */
    @BeanAttrInfo(value = "1", cnName = "主站转账", enName = "transfer")
    public static final long MASTER_TRANSFER = 29;

    /** 成都银行 */
    @BeanAttrInfo(value = "1", cnName = "成都银行")
    public static final long CD_TYPE = 32;

    /** 支出专用银行 */
    @BeanAttrInfo(value = "1", cnName = "支出专用银行")
    public static final long EXPENDITURE_SPECIAL_PURPOSE_TYPE = 33;

    /** 卡密 */
    @BeanAttrInfo(value = "1", cnName = "卡密")
    public static final long CARD_SECRET = 34;

    /**
     * 判断指定类型是否是在现支付
     *
     * @param type 银行类型
     * @return bool
     */
    public static boolean isOnline(long type) {
        return "0".equals(ConfigManager.getInstance().getValue(RemittanceType.class, type));
    }
}
