package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author frank
 * @date 2012-10-29 下午4:03:47
 * @category 关键字，特殊字
 */
@Entity
@DiscriminatorValue("KEYWORD")
@BeanName(name = "关键字，特殊字")
public class Keyword extends Dictionary {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 2882157632658724030L;

    /** 先生 */
    @BeanAttrInfo(value = "先生", orderBy = 1, cnName = "先生")
    public static final long SIR = 1;

    /** 小姐 */
    @BeanAttrInfo(value = "小姐", orderBy = 2, cnName = "小姐")
    public static final long MISS = 2;

    /** 女士 */
    @BeanAttrInfo(value = "女士", orderBy = 3, cnName = "女士")
    public static final long MADAM = 3;

    /** 个人 */
    @BeanAttrInfo(value = "个人", orderBy = 4, cnName = "个人")
    public static final long PERSONAL = 4;

    /** 公司 */
    @BeanAttrInfo(value = "公司", orderBy = 5, cnName = "公司")
    public static final long COMPANY = 5;

    /** 成都世纪东方网络通信有限公司 */
    @BeanAttrInfo(value = "成都世纪东方网络通信有限公司", orderBy = 6, cnName = "成都世纪东方网络通信有限公司")
    public static final long CHENGDU_ORIENTAL_CENTURY_COMPANY = 6;

    /** 成都世纪东方 */
    @BeanAttrInfo(value = "成都世纪东方", orderBy = 7, cnName = "成都世纪东方")
    public static final long CHENGDU_ORIENTAL_CENTURY = 7;

    /** 工作室 */
    @BeanAttrInfo(value = "工作室", orderBy = 8, cnName = "工作室")
    public static final long STUDIO = 8;

    /** 个人网站 */
    @BeanAttrInfo(value = "个人网站", orderBy = 9, cnName = "个人网站")
    public static final long PERSONAL_WEBSITE = 9;

    /** 博客 */
    @BeanAttrInfo(value = "博客", orderBy = 10, cnName = "博客")
    public static final long BLOG = 10;

    /** 官网 */
    @BeanAttrInfo(value = "官网", orderBy = 11, cnName = "官网")
    public static final long OFFICIAL_WEBSITE = 11;

    /** 论坛 */
    @BeanAttrInfo(value = "论坛", orderBy = 12, cnName = "论坛")
    public static final long FORUM = 12;

    /** 中心 */
    @BeanAttrInfo(value = "中心", orderBy = 13, cnName = "中心")
    public static final long CENTER_ZH = 13;

    /** 域名扫描关键字 */
    @BeanAttrInfo(value = "私服,传奇", orderBy = 99, cnName = "域名扫描关键字")
    public static final long DOMAIN_SCAN_KEY_WORD = 99;
}
