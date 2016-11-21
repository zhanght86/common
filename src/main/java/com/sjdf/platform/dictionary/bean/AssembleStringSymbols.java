package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author frank
 * @date 2012-8-24 下午4:31:37
 * @category 组合字符串符号
 */
@Entity
@DiscriminatorValue("ASSEMBLE_STRING_SYMBOLS")
@BeanName(name = "组合字符串符号")
public class AssembleStringSymbols extends Dictionary {

    private static final long serialVersionUID = 2882157632658724004L;

    /** 逗号 */
    @BeanAttrInfo(value = ",", orderBy = 1, cnName = "逗号")
    public static final long COMMA = 1;

    /** 分号 */
    @BeanAttrInfo(value = ";", orderBy = 2, cnName = "分号")
    public static final long SEMICOLON = 2;

    /** 冒号 */
    @BeanAttrInfo(value = ":", orderBy = 3, cnName = "冒号")
    public static final long COLON = 3;

    /** 英文句号 */
    @BeanAttrInfo(value = ".", orderBy = 4, cnName = "英文句号")
    public static final long EN_PERIOD = 4;

    /** 空格 */
    @BeanAttrInfo(value = " ", orderBy = 5, cnName = "空格")
    public static final long SPACE = 5;

    /** 分号空格 */
    @BeanAttrInfo(value = "; ", orderBy = 6, cnName = "分号空格")
    public static final long SEMICOLON_SPACE = 6;

    /** 连接号 */
    @BeanAttrInfo(value = "-", orderBy = 7, cnName = "连接号")
    public static final long HYPHEN = 7;

    /** 正斜线 */
    @BeanAttrInfo(value = "/", orderBy = 8, cnName = "正斜线")
    public static final long SLASH = 8;

    /** 反斜线 */
    @BeanAttrInfo(value = "\\", orderBy = 9, cnName = "反斜线")
    public static final long BACKSLASH = 9;

}
