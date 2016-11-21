package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-4-16 上午11:48:26
 *
 * @author frank
 * @category 附件格式
 */
@Entity
@DiscriminatorValue("ATTACHMENT_FORMAT")
@BeanName(name = "附件格式")
public class AttachmentFormat extends Dictionary {
    private static final long serialVersionUID = 2882157632658724037L;

    /** jpg */
    @BeanAttrInfo(value = "jpg", orderBy = 1, cnName = "jpg")
    public static final long JPG = 1;

    /** jpeg */
    @BeanAttrInfo(value = "jpeg", orderBy = 2, cnName = "jpeg")
    public static final long JPEG = 2;

    /** png */
    @BeanAttrInfo(value = "png", orderBy = 3, cnName = "png")
    public static final long PNG = 3;

    /** bmp */
    @BeanAttrInfo(value = "bmp", orderBy = 4, cnName = "bmp")
    public static final long BMP = 4;

    /** gif */
    @BeanAttrInfo(value = "gif", orderBy = 5, cnName = "gif")
    public static final long GIF = 5;

    /** phd */
    @BeanAttrInfo(value = "phd", orderBy = 6, cnName = "phd")
    public static final long PHD = 6;

    /** tif */
    @BeanAttrInfo(value = "tif", orderBy = 7, cnName = "tif")
    public static final long TIF = 7;

    /** pcx */
    @BeanAttrInfo(value = "pcx", orderBy = 8, cnName = "pcx")
    public static final long PCX = 8;

    /** zip */
    @BeanAttrInfo(value = "zip", orderBy = 9, cnName = "zip")
    public static final long ZIP = 9;

    /** zipx */
    @BeanAttrInfo(value = "zipx", orderBy = 10, cnName = "zipx")
    public static final long ZIPX = 10;

    /** rar */
    @BeanAttrInfo(value = "rar", orderBy = 11, cnName = "rar")
    public static final long RAR = 11;

    /** 7z */
    @BeanAttrInfo(value = "7z", orderBy = 12, cnName = "7z")
    public static final long ZIP_7Z = 12;

    /** iso */
    @BeanAttrInfo(value = "iso", orderBy = 13, cnName = "iso")
    public static final long ISO = 13;

    /** img */
    @BeanAttrInfo(value = "img", orderBy = 14, cnName = "img")
    public static final long IMG = 14;

    /** doc */
    @BeanAttrInfo(value = "doc", orderBy = 15, cnName = "doc")
    public static final long DOC = 15;

    /** xls */
    @BeanAttrInfo(value = "xls", orderBy = 16, cnName = "xls")
    public static final long XLS = 16;

    /** ppt */
    @BeanAttrInfo(value = "ppt", orderBy = 17, cnName = "ppt")
    public static final long PPT = 17;

    /** jar */
    @BeanAttrInfo(value = "jar", orderBy = 18, cnName = "jar")
    public static final long JAR = 18;

}
