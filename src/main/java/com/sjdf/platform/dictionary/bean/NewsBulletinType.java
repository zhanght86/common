package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 新闻公告类型
 */
@Entity
@DiscriminatorValue("NEWS_BULLETIN_TYPE")
@BeanName(name = "新闻公告类型")
public class NewsBulletinType extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 6581134121562035148L;

    /** 最新公告 */
    @BeanAttrInfo(value = "1", cnName = "最新公告")
    public static final long LATEST_BULLETIN = 1;

    /** 优惠活动 */
    @BeanAttrInfo(value = "2", cnName = "优惠活动")
    public static final long PROMOTION = 2;

    /** 行业新闻 */
    @BeanAttrInfo(value = "3", cnName = "行业新闻")
    public static final long TRADE_NEWS = 3;

}
