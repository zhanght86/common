package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 代理级别
 * 2013-08-23
 * <pre>
 * value:标识数据库id
 * orderBy:标识是直接客户还是代理;0:直接客户,1:代理
 * attr:会员级别大小;数值越大,级别越高
 * enName:可以开发票的产品大类:如虚拟主机,云主机,数据库
 * </pre>
 *
 * @author 许长贵
 */
@Entity
@DiscriminatorValue("AGENT_LEVEL")
@BeanName(name = "代理级别")
public class AgentLevel extends Dictionary {
    private static final long serialVersionUID = -2106751034719540442L;

    @BeanAttrInfo(value = "16", orderBy = 0, cnName = "友好会员", enName = "1,2,3,5,6,7,8,97,98,100,200,201")
    public static final long FRIEND_AGENT = 1;

    @BeanAttrInfo(value = "1", orderBy = 0, cnName = "VIP 客户", enName = "1,2,3,5,6,7,8,97,98,100,200,201")
    public static final long VIP_AGENT = 10;

    @BeanAttrInfo(value = "4", orderBy = 1, cnName = "金牌代理", enName = "1,2,3,7,8,97,98,100,200,201")
    public static final long GOLD_AGENT = 100;

    @BeanAttrInfo(value = "6", orderBy = 1, cnName = "核心代理", enName = "1,2,3,7,8,97,98,100,200,201")
    public static final long CORE_AGENT = 110;

    @BeanAttrInfo(value = "7", orderBy = 1, cnName = "钻石代理", enName = "1,2,3,7,8,97,98,100,200,201")
    public static final long DIAMOND_AGENT = 120;

    @BeanAttrInfo(value = "17", orderBy = 1, cnName = "伙伴代理", enName = "1,2,3,7,8,97,98,100,200,201")
    public static final long PARTNER_AGENT = 130;

}
