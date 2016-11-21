package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BaiduYunJiaSu")
@BeanName(name = "百度云加速")
public class BaiduYunJiaSu extends Dictionary {

    private static final long serialVersionUID = -1023168292120623389L;

    /** 百度云加速 */
    @BeanAttrInfo(value = "34dd7fdd3794468dabd5c38fa5ec4672", cnName = "KEY", enName = "ACCESS_KEY")
    public static final long ACCESS_KEY = 1;

    /** 百度云加速 */
    @BeanAttrInfo(value = "87cff2ceb89344ef91b953a56c58be9d", cnName = "密匙", enName = "SECRET_KEY")
    public static final long SECRET_KEY = 2;


    /** 百度云加速云主机域名 */
    @BeanAttrInfo(value = "test65.51web.net", cnName = "百度云加速云主机域名", enName = "BAI_DU_DOMAIN")
    public static final long BAI_DU_DOMAIN = 3;

    /** 百度云加速连续开通间隔（分钟） */
    @BeanAttrInfo(value = "10", cnName = "百度云加速连续开通间隔（分钟）", enName = "OPEN_MINUTES")
    public static final long OPEN_MINUTES = 4;

}