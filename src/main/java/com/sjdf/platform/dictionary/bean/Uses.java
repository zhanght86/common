package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 黄远昌
 * @category 服务器用途
 * 详细描述：
 * value:该值由小写字母单词 +数字组成，代表机器的功能
 * enName：表示命名规范中的管理域名的<域>
 * @date 2014-09-18
 */
@Entity
@DiscriminatorValue("USES")
@BeanName(name = "用途")
public class Uses extends Dictionary {

    private static final long serialVersionUID = -7273204767555275112L;

    /** 商务网站 */
    @BeanAttrInfo(value = "chost1", cnName = "商务网站", enName = "51web.cn", refAttr = ServerClass.CHOST, refClass = ServerClass.class)
    public static final long WEBSITE_USE = 1;

    /** 挂机宝 */
    @BeanAttrInfo(value = "chost2", cnName = "挂机宝", enName = "51web.cn", refAttr = ServerClass.CHOST, refClass = ServerClass.class)
    public static final long NOT_WEBSITE_USE = 2;

    /** 建站宝 */
    @BeanAttrInfo(value = "chost3", cnName = "建站宝", enName = "51web.cn", refAttr = ServerClass.CHOST, refClass = ServerClass.class)
    public static final long MINI_WEBSITE_USE = 3;

    @BeanAttrInfo(value = "chost4", cnName = "挂机宝高配", enName = "51web.cn", refAttr = ServerClass.CHOST, refClass = ServerClass.class)
    public static final long NOT_WEBSITE_USE_HIGH = 4;

    @BeanAttrInfo(value = "vhost1", cnName = "window 2003", enName = "51web.cn", refAttr = ServerClass.VHOST_DATABASE_MAIL, refClass = ServerClass.class)
    public static final long VHOST_WIN_2003 = 5;

    @BeanAttrInfo(value = "vhost2", cnName = "window 2008", enName = "51web.cn", refAttr = ServerClass.VHOST_DATABASE_MAIL, refClass = ServerClass.class)
    public static final long VHOST_WIN_2008 = 6;

    @BeanAttrInfo(value = "vhost3", cnName = "linux", enName = "51web.cn", refAttr = ServerClass.VHOST_DATABASE_MAIL, refClass = ServerClass.class)
    public static final long VHOST_LINUX = 7;
}
