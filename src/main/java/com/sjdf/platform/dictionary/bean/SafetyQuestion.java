/**
 *
 */
package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author ketqi
 * @Created 2013-3-7 下午1:52:48
 * @category 密码保护之安全问题
 */
@Entity
@DiscriminatorValue("SAFETY_QUESTION")
@BeanName(name = "密码保护之安全问题")
public class SafetyQuestion extends Dictionary {
    private static final long serialVersionUID = 6666726514428796917L;

    /** 我母亲的姓名？ */
    @BeanAttrInfo(value = "我母亲的姓名？", cnName = "我母亲的姓名？")
    public static final long QUESTION_1 = 1;

    /** 我父亲的姓名？ */
    @BeanAttrInfo(value = "我父亲的姓名？", cnName = "我父亲的姓名？")
    public static final long QUESTION_2 = 2;

    /** 您的工号或者学号是？ */
    @BeanAttrInfo(value = "您的工号或者学号是？", cnName = "您的工号或者学号是？")
    public static final long QUESTION_3 = 3;

    /** 对您影响最大的人名字是？ */
    @BeanAttrInfo(value = "对您影响最大的人名字是？", cnName = "对您影响最大的人名字是？")
    public static final long QUESTION_4 = 4;

    /** 我初中班主任的名字是？ */
    @BeanAttrInfo(value = "我初中班主任的名字是？", cnName = "我初中班主任的名字是？")
    public static final long QUESTION_5 = 5;

    /** 您自己最容易记住的词？ */
    @BeanAttrInfo(value = "您自己最容易记住的词？", cnName = "您自己最容易记住的词？")
    public static final long QUESTION_6 = 6;

    /** 您最喜欢吃的菜是什么？ */
    @BeanAttrInfo(value = "您最喜欢吃的菜是什么？", cnName = "您最喜欢吃的菜是什么？")
    public static final long QUESTION_7 = 7;

    /** 我最爱的人的名字？ */
    @BeanAttrInfo(value = "我最爱的人的名字？", cnName = "我最爱的人的名字？")
    public static final long QUESTION_8 = 8;
}
