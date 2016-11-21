package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author frank
 * @category 省份代码
 * @date 2012-8-23 上午9:11:11
 */
@Entity
@DiscriminatorValue("PROVINCE_CODE")
@BeanName(name = "省份代码")
public class ProvinceCode extends Dictionary {
    private static final long serialVersionUID = 2882157632658724000L;

    /** 北京市 */
    @BeanAttrInfo(value = "110000", orderBy = 110000, cnName = "北京市")
    public static final long BEI_JING = 110000;

    /** 天津市 */
    @BeanAttrInfo(value = "120000", orderBy = 120000, cnName = "天津市")
    public static final long TIAN_JIN = 120000;

    /** 河北省 */
    @BeanAttrInfo(value = "130000", orderBy = 130000, cnName = "河北省")
    public static final long HE_BEI = 130000;

    /** 山西省 */
    @BeanAttrInfo(value = "140000", orderBy = 140000, cnName = "山西省")
    public static final long SHAN_XI = 140000;

    /** 内蒙古自治区 */
    @BeanAttrInfo(value = "150000", orderBy = 150000, cnName = "内蒙古自治区")
    public static final long INNER_MONGOLIA = 150000;

    /** 辽宁省 */
    @BeanAttrInfo(value = "210000", orderBy = 210000, cnName = "辽宁省")
    public static final long LIAO_NING = 210000;

    /** 吉林省 */
    @BeanAttrInfo(value = "220000", orderBy = 220000, cnName = "吉林省")
    public static final long JI_LIN = 220000;

    /** 黑龙江省 */
    @BeanAttrInfo(value = "230000", orderBy = 230000, cnName = "黑龙江省")
    public static final long HEI_LONG_JIANG = 230000;

    /** 上海市 */
    @BeanAttrInfo(value = "310000", orderBy = 310000, cnName = "上海市")
    public static final long SHANG_HAI = 310000;

    /** 江苏省 */
    @BeanAttrInfo(value = "320000", orderBy = 320000, cnName = "江苏省")
    public static final long JIANG_SU = 320000;

    /** 浙江省 */
    @BeanAttrInfo(value = "330000", orderBy = 330000, cnName = "浙江省")
    public static final long ZHE_JIANG = 330000;

    /** 安徽省 */
    @BeanAttrInfo(value = "340000", orderBy = 340000, cnName = "安徽省")
    public static final long AN_HUI = 340000;

    /** 福建省 */
    @BeanAttrInfo(value = "350000", orderBy = 350000, cnName = "福建省")
    public static final long FU_JIAN = 350000;

    /** 江西省 */
    @BeanAttrInfo(value = "360000", orderBy = 360000, cnName = "江西省")
    public static final long JIANG_XI = 360000;

    /** 山东省 */
    @BeanAttrInfo(value = "370000", orderBy = 370000, cnName = "山东省")
    public static final long SHAN_DONG = 370000;

    /** 河南省 */
    @BeanAttrInfo(value = "410000", orderBy = 410000, cnName = "河南省")
    public static final long HE_NAN = 410000;

    /** 湖北省 */
    @BeanAttrInfo(value = "420000", orderBy = 420000, cnName = "湖北省")
    public static final long HU_BEI = 420000;

    /** 湖南省 */
    @BeanAttrInfo(value = "430000", orderBy = 430000, cnName = "湖南省")
    public static final long HU_NAN = 430000;

    /** 广东省 */
    @BeanAttrInfo(value = "440000", orderBy = 440000, cnName = "广东省")
    public static final long GUANG_DONG = 440000;

    /** 广西省 */
    @BeanAttrInfo(value = "450000", orderBy = 450000, cnName = "广西省")
    public static final long GUANG_XI = 450000;

    /** 海南省 */
    @BeanAttrInfo(value = "460000", orderBy = 460000, cnName = "海南省")
    public static final long HAI_NAN = 460000;

    /** 重庆市 */
    @BeanAttrInfo(value = "500000", orderBy = 500000, cnName = "重庆市")
    public static final long CHONG_QING = 500000;

    /** 四川省 */
    @BeanAttrInfo(value = "510000", orderBy = 510000, cnName = "四川省")
    public static final long SI_CHUAN = 510000;

    /** 贵州省 */
    @BeanAttrInfo(value = "520000", orderBy = 520000, cnName = "贵州省")
    public static final long GUI_ZHOU = 520000;

    /** 云南省 */
    @BeanAttrInfo(value = "530000", orderBy = 530000, cnName = "云南省")
    public static final long YUN_NAN = 530000;

    /** 西藏自治区 */
    @BeanAttrInfo(value = "540000", orderBy = 540000, cnName = "西藏自治区")
    public static final long TIBET = 540000;

    /** 陕西省 */
    @BeanAttrInfo(value = "610000", orderBy = 610000, cnName = "陕西省")
    public static final long SHAAN_XI = 610000;

    /** 甘肃省 */
    @BeanAttrInfo(value = "620000", orderBy = 620000, cnName = "甘肃省")
    public static final long GAN_SU = 620000;

    /** 青海省 */
    @BeanAttrInfo(value = "630000", orderBy = 630000, cnName = "青海省")
    public static final long QING_HAI = 630000;

    /** 宁夏回族自治区 */
    @BeanAttrInfo(value = "640000", orderBy = 640000, cnName = "宁夏回族自治区")
    public static final long NING_XIA = 640000;

    /** 新疆自治区 */
    @BeanAttrInfo(value = "650000", orderBy = 650000, cnName = "新疆自治区")
    public static final long SIN_KIANG = 650000;

}
