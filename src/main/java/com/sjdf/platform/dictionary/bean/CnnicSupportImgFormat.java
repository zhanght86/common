package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年11月11日 上午9:17:48
 *
 * @author KETQI
 * @category cnnic审核所支持的图片格式(cnName和enName禁止修改)
 */
@Entity
@DiscriminatorValue("CNNIC_SUPPORT_IMG_FORMAT")
@BeanName(name = "cnnic审核所支持的图片格式")
public class CnnicSupportImgFormat extends Dictionary {
    private static final long serialVersionUID = -3863286682743324046L;

    @BeanAttrInfo(cnName = "jpg", enName = "image/jpeg")
    public static final long JPG = 1;

    @BeanAttrInfo(cnName = "jpeg", enName = "image/jpeg")
    public static final long JPEG = 2;

    @BeanAttrInfo(cnName = "png", enName = "image/png")
    public static final long PNG = 3;

    @BeanAttrInfo(cnName = "gif", enName = "image/gif")
    public static final long GIF = 4;

    @BeanAttrInfo(cnName = "bmp", enName = "image/bmp")
    public static final long BMP = 5;
}
