package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 网站语言类别
 */
@Entity
@DiscriminatorValue("WEB_LAN_TYPE")
@BeanName(name = "网站语言类别")
public class WebLanType extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -2425031599494153949L;

    /** 中文简体 */
    @BeanAttrInfo(value = "1", cnName = "中文简体")
    public static final long CHS = 1L;

    /** 中文繁体 */
    @BeanAttrInfo(value = "2", cnName = "中文繁体")
    public static final long CHT = 2L;

    /** 维吾尔语 */
    @BeanAttrInfo(value = "3", cnName = "维吾尔语")
    public static final long UEY = 3L;

    /** 哈萨克语 */
    @BeanAttrInfo(value = "4", cnName = "哈萨克语")
    public static final long KAZAKH = 4L;

    /** 柯尔克孜语 */
    @BeanAttrInfo(value = "5", cnName = "柯尔克孜语")
    public static final long KIRGIZ = 5L;

    /** 蒙古语 */
    @BeanAttrInfo(value = "6", cnName = "蒙古语")
    public static final long MONGOLIAN = 6L;

    /** 藏语 */
    @BeanAttrInfo(value = "7", cnName = "藏语")
    public static final long TIBETAN = 7L;

    /** 壮语 */
    @BeanAttrInfo(value = "8", cnName = "壮语")
    public static final long ZHUANG = 8L;

    /** 朝鲜语 */
    @BeanAttrInfo(value = "9", cnName = "朝鲜语")
    public static final long KOREAN = 9L;

    /** 彝文 */
    @BeanAttrInfo(value = "10", cnName = "彝文")
    public static final long YI = 10L;

    /** 苗语 */
    @BeanAttrInfo(value = "11", cnName = "苗语")
    public static final long MIAO = 11L;

    /** 英语 */
    @BeanAttrInfo(value = "12", cnName = "英语")
    public static final long EN = 12L;

    /** 日语 */
    @BeanAttrInfo(value = "13", cnName = "日语")
    public static final long JP = 13L;

    /** 法语 */
    @BeanAttrInfo(value = "14", cnName = "法语")
    public static final long FRENCH = 14L;

    /** 俄罗斯语 */
    @BeanAttrInfo(value = "15", cnName = "俄罗斯语")
    public static final long RUSSIAN = 15L;

    /** 西班牙语 */
    @BeanAttrInfo(value = "16", cnName = "西班牙语")
    public static final long SPANISH = 16L;

    /** 阿拉伯语 */
    @BeanAttrInfo(value = "17", cnName = "阿拉伯语")
    public static final long ARABIC = 17L;
}
