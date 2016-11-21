package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2013-11-14
 *
 * @author 黄远昌
 * @category 【MIME】常量
 */
@Entity
@DiscriminatorValue("MIME_TYPE")
@BeanName(name = "MIME常量")
public class MimeType extends Dictionary {
    private static final long serialVersionUID = -5191771687795644751L;
    /** .ani */
    @BeanAttrInfo(value = ".ani|application/octet-stream", cnName = ".ani", enName = ".ani", orderBy = 1)
    public static final long ANI = 1;
    /** .flv */
    @BeanAttrInfo(value = ".flv|application/octet-stream", cnName = ".flv", enName = ".flv", orderBy = 2)
    public static final long FLV = 2;
    /** .mid */
    @BeanAttrInfo(value = ".mid|application/octet-stream", cnName = ".mid", enName = ".mid", orderBy = 3)
    public static final long MID = 3;
    /** .wma */
    @BeanAttrInfo(value = ".wma|application/octet-stream", cnName = ".wma", enName = ".wma", orderBy = 4)
    public static final long WMA = 4;
    /** .mpg */
    @BeanAttrInfo(value = ".mpg|application/octet-stream", cnName = ".mpg", enName = ".mpg", orderBy = 5)
    public static final long MPG = 5;
    /** .pdf */
    @BeanAttrInfo(value = ".pdf|application/octet-stream", cnName = ".pdf", enName = ".pdf", orderBy = 6)
    public static final long PDF = 6;
    /** .exe */
    @BeanAttrInfo(value = ".exe|application/octet-stream", cnName = ".exe", enName = ".exe", orderBy = 7)
    public static final long EXE = 7;
    /** .doc */
    @BeanAttrInfo(value = ".doc|application/octet-stream", cnName = ".doc", enName = ".doc", orderBy = 8)
    public static final long DOC = 8;
}
