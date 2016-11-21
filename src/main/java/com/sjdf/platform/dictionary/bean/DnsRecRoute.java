package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @category dns解析线路
 * value > 0 ：表示该线路控制面板可用，反之不可用
 * 标示该记录值的父记录值。
 * 如：enName="0",表示该记录为父记录层，
 * enName="8",表示父记录是attr="8"的记录值，主要用在页面上做层次展现
 * @Description：
 * @user：王鹏
 * @date：2014年11月6日 下午5:31:08
 */
@Entity
@DiscriminatorValue("DNS_REC_ROUTE")
@BeanName(name = "dns解析线路")
public class DnsRecRoute extends Dictionary {

    private static final long serialVersionUID = -1801544105471669930L;

    @BeanAttrInfo(value = "11", cnName = "安徽电信", orderBy = 11, enName = "201")
    public static final long ANHUI_TEL = 11;

    @BeanAttrInfo(value = "12", cnName = "北京电信", orderBy = 12, enName = "201")
    public static final long BEIJING_TEL = 12;

    @BeanAttrInfo(value = "13", cnName = "重庆电信", orderBy = 13, enName = "201")
    public static final long CHONGQING_TEL = 13;

    @BeanAttrInfo(value = "14", cnName = "福建电信", orderBy = 14, enName = "201")
    public static final long FUJIAN_TEL = 14;

    @BeanAttrInfo(value = "15", cnName = "甘肃电信", orderBy = 15, enName = "201")
    public static final long GANSU_TEL = 15;

    @BeanAttrInfo(value = "16", cnName = "广东电信", orderBy = 16, enName = "201")
    public static final long GUANGDONG_TEL = 16;

    @BeanAttrInfo(value = "17", cnName = "广西电信", orderBy = 17, enName = "201")
    public static final long GUANGXI_TEL = 17;

    @BeanAttrInfo(value = "18", cnName = "贵州电信", orderBy = 18, enName = "201")
    public static final long GUIZHOU_TEL = 18;

    @BeanAttrInfo(value = "19", cnName = "海南电信", orderBy = 19, enName = "201")
    public static final long HAINAN_TEL = 19;

    @BeanAttrInfo(value = "20", cnName = "河北电信", orderBy = 20, enName = "201")
    public static final long HEBEI_TEL = 20;

    @BeanAttrInfo(value = "21", cnName = "河南电信", orderBy = 21, enName = "201")
    public static final long HENAN_TEL = 21;

    @BeanAttrInfo(value = "22", cnName = "黑龙江电信", orderBy = 22, enName = "201")
    public static final long HEILONGJIANG_TEL = 22;

    @BeanAttrInfo(value = "23", cnName = "湖北电信", orderBy = 23, enName = "201")
    public static final long HUBEI_TEL = 23;

    @BeanAttrInfo(value = "24", cnName = "湖南电信", orderBy = 24, enName = "201")
    public static final long HUNAN_TEL = 24;

    @BeanAttrInfo(value = "25", cnName = "吉林电信", orderBy = 25, enName = "201")
    public static final long JILIN_TEL = 25;

    @BeanAttrInfo(value = "26", cnName = "江苏电信", orderBy = 26, enName = "201")
    public static final long JIANGSU_TEL = 26;

    @BeanAttrInfo(value = "27", cnName = "江西电信", orderBy = 27, enName = "201")
    public static final long JIANGXI_TEL = 27;

    @BeanAttrInfo(value = "28", cnName = "辽宁电信", orderBy = 28, enName = "201")
    public static final long LIAONING_TEL = 28;

    @BeanAttrInfo(value = "29", cnName = "内蒙古电信", orderBy = 29, enName = "201")
    public static final long NEIMENG_TEL = 29;

    @BeanAttrInfo(value = "30", cnName = "宁夏电信", orderBy = 30, enName = "201")
    public static final long NINGXIA_TEL = 30;

    @BeanAttrInfo(value = "31", cnName = "青海电信", orderBy = 31, enName = "201")
    public static final long QINGHAI_TEL = 31;

    @BeanAttrInfo(value = "32", cnName = "山东电信", orderBy = 32, enName = "201")
    public static final long SHANDONG_TEL = 32;

    @BeanAttrInfo(value = "33", cnName = "山西电信", orderBy = 33, enName = "201")
    public static final long SHANXI_TEL = 33;

    @BeanAttrInfo(value = "34", cnName = "陕西电信", orderBy = 34, enName = "201")
    public static final long SHAANXI_TEL = 34;

    @BeanAttrInfo(value = "35", cnName = "上海电信", orderBy = 35, enName = "201")
    public static final long SHANGHAI_TEL = 35;

    @BeanAttrInfo(value = "36", cnName = "四川电信", orderBy = 36, enName = "201")
    public static final long SICHUAN_TEL = 36;

    @BeanAttrInfo(value = "37", cnName = "天津电信", orderBy = 37, enName = "201")
    public static final long TIANJIN_TEL = 37;

    @BeanAttrInfo(value = "38", cnName = "西藏电信", orderBy = 38, enName = "201")
    public static final long XIZANG_TEL = 38;

    @BeanAttrInfo(value = "39", cnName = "新疆电信", orderBy = 39, enName = "201")
    public static final long XINJIANG_TEL = 39;

    @BeanAttrInfo(value = "40", cnName = "云南电信", orderBy = 40, enName = "201")
    public static final long YUNNAN_TEL = 40;

    @BeanAttrInfo(value = "41", cnName = "浙江电信", orderBy = 41, enName = "201")
    public static final long ZHEJIANG_TEL = 41;

    @BeanAttrInfo(value = "101", cnName = "安徽联通", orderBy = 101, enName = "202")
    public static final long ANHUI_UNI = 101;

    @BeanAttrInfo(value = "102", cnName = "北京联通", orderBy = 102, enName = "202")
    public static final long BEIJING_UNI = 102;

    @BeanAttrInfo(value = "103", cnName = "重庆联通", orderBy = 103, enName = "202")
    public static final long CHONGQING_UNI = 103;

    @BeanAttrInfo(value = "104", cnName = "福建联通", orderBy = 104, enName = "202")
    public static final long FUJIAN_UNI = 104;

    @BeanAttrInfo(value = "105", cnName = "甘肃联通", orderBy = 105, enName = "202")
    public static final long GANSU_UNI = 105;

    @BeanAttrInfo(value = "106", cnName = "广东联通", orderBy = 106, enName = "202")
    public static final long GUANGDONG_UNI = 106;

    @BeanAttrInfo(value = "107", cnName = "广西联通", orderBy = 107, enName = "202")
    public static final long GUANGXI_UNI = 107;

    @BeanAttrInfo(value = "108", cnName = "贵州联通", orderBy = 108, enName = "202")
    public static final long GUIZHOU_UNI = 108;

    @BeanAttrInfo(value = "109", cnName = "海南联通", orderBy = 109, enName = "202")
    public static final long HAINAN_UNI = 109;

    @BeanAttrInfo(value = "110", cnName = "河北联通", orderBy = 110, enName = "202")
    public static final long HEBEI_UNI = 110;

    @BeanAttrInfo(value = "111", cnName = "河南联通", orderBy = 111, enName = "202")
    public static final long HENAN_UNI = 111;

    @BeanAttrInfo(value = "112", cnName = "黑龙江联通", orderBy = 112, enName = "202")
    public static final long HEILONGJIANG_UNI = 112;

    @BeanAttrInfo(value = "113", cnName = "湖北联通", orderBy = 113, enName = "202")
    public static final long HUBEI_UNI = 113;

    @BeanAttrInfo(value = "114", cnName = "湖南联通", orderBy = 114, enName = "202")
    public static final long HUNAN_UNI = 114;

    @BeanAttrInfo(value = "115", cnName = "吉林联通", orderBy = 115, enName = "202")
    public static final long JILIN_UNI = 115;

    @BeanAttrInfo(value = "116", cnName = "江苏联通", orderBy = 116, enName = "202")
    public static final long JIANGSU_UNI = 116;

    @BeanAttrInfo(value = "117", cnName = "江西联通", orderBy = 117, enName = "202")
    public static final long JIANGXI_UNI = 117;

    @BeanAttrInfo(value = "118", cnName = "辽宁联通", orderBy = 118, enName = "202")
    public static final long LIAONING_UNI = 118;

    @BeanAttrInfo(value = "119", cnName = "内蒙古联通", orderBy = 119, enName = "202")
    public static final long NEIMENG_UNI = 119;

    @BeanAttrInfo(value = "120", cnName = "宁夏联通", orderBy = 120, enName = "202")
    public static final long NINGXIA_UNI = 120;

    @BeanAttrInfo(value = "121", cnName = "青海联通", orderBy = 121, enName = "202")
    public static final long QINGHAI_UNI = 121;

    @BeanAttrInfo(value = "122", cnName = "山东联通", orderBy = 122, enName = "202")
    public static final long SHANDONG_UNI = 122;

    @BeanAttrInfo(value = "123", cnName = "山西联通", orderBy = 123, enName = "202")
    public static final long SHANXI_UNI = 123;

    @BeanAttrInfo(value = "124", cnName = "陕西联通", orderBy = 124, enName = "202")
    public static final long SHAANXI_UNI = 124;

    @BeanAttrInfo(value = "125", cnName = "上海联通", orderBy = 125, enName = "202")
    public static final long SHANGHAI_UNI = 125;

    @BeanAttrInfo(value = "126", cnName = "四川联通", orderBy = 126, enName = "202")
    public static final long SICHUAN_UNI = 126;

    @BeanAttrInfo(value = "127", cnName = "天津联通", orderBy = 127, enName = "202")
    public static final long TIANJIN_UNI = 127;

    @BeanAttrInfo(value = "128", cnName = "西藏联通", orderBy = 128, enName = "202")
    public static final long XIZANG_UNI = 128;

    @BeanAttrInfo(value = "129", cnName = "新疆联通", orderBy = 129, enName = "202")
    public static final long XINJIANG_UNI = 129;

    @BeanAttrInfo(value = "130", cnName = "云南联通", orderBy = 130, enName = "202")
    public static final long YUNNAN_UNI = 130;

    @BeanAttrInfo(value = "131", cnName = "浙江联通", orderBy = 131, enName = "202")
    public static final long ZHEJIANG_UNI = 131;

    @BeanAttrInfo(value = "191", cnName = "香港", orderBy = 191, enName = "0")
    public static final long HONGKONG = 191;

    @BeanAttrInfo(value = "192", cnName = "澳门", orderBy = 192, enName = "0")
    public static final long AOMEN = 192;

    @BeanAttrInfo(value = "193", cnName = "台湾", orderBy = 193, enName = "0")
    public static final long TAIWAN = 193;

    @BeanAttrInfo(value = "201", cnName = "电信", orderBy = 201, enName = "0")
    public static final long TELECOM = 201;

    @BeanAttrInfo(value = "202", cnName = "联通", orderBy = 202, enName = "0")
    public static final long UNICOM = 202;

    @BeanAttrInfo(value = "203", cnName = "教育网", orderBy = 203, enName = "0")
    public static final long EDUCATION = 203;

    @BeanAttrInfo(value = "204", cnName = "移动", orderBy = 204, enName = "0")
    public static final long MOBILE = 204;

    @BeanAttrInfo(value = "205", cnName = "铁通", orderBy = 205, enName = "0")
    public static final long TIETONG = 205;

    @BeanAttrInfo(value = "253", cnName = "国内", orderBy = 253, enName = "0")
    public static final long CHINA = 253;

    @BeanAttrInfo(value = "254", cnName = "海外", orderBy = 254, enName = "0")
    public static final long FOREIGN = 254;

    @BeanAttrInfo(value = "255", cnName = "通用", orderBy = 1, enName = "0")
    public static final long COMMON = 255;

}
