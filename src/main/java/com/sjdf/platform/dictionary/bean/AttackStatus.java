package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("attack_status")
@BeanName(name = "攻击跟踪状态")
public class AttackStatus extends Dictionary {

    @BeanAttrInfo(cnName = "正常汇总")
    public static final long STATE1 = 1;

    @BeanAttrInfo(cnName = "正常代理")
    public static final long STATE2 = 2;

    @BeanAttrInfo(cnName = "被攻击")
    public static final long STATE3 = 3;

    @BeanAttrInfo(cnName = "退出代理")
    public static final long STATE4 = 4;


}