package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2013-12-2
 *
 * @author 王鸣忠
 * @category
 */
@Entity
@DiscriminatorValue("TITLE_KEYWORDS_DSCRIPTION")
@BeanName(name = "标题-关键字-描述")
public class TitleKeywordsDscription extends Dictionary {
    private static final long serialVersionUID = 2193419504381925886L;

    /** 标题 */
    @BeanAttrInfo(value = "1", cnName = "新闻资讯_世纪东方")
    public static final long TITLE = 1;

    /** 关键字 */
    @BeanAttrInfo(value = "2", cnName = "新闻资讯")
    public static final long KEYWORDS = 2;

    /** 描述 */
    @BeanAttrInfo(value = "3", cnName = "世纪东方新闻资讯，最新，最全的域名、虚拟主机资讯信息，域名交易资讯，虚拟主机知识学习，尽在世纪东方。")
    public static final long DSCRIPTION = 3;

}
