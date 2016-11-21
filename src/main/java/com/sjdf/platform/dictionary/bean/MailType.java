package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2013-05-21
 *
 * @author 陈绍刚
 * @category 邮局类型
 */
@Entity
@DiscriminatorValue("MAIL_TYPE")
@BeanName(name = "邮局类型")
public class MailType extends Dictionary {

    private static final long serialVersionUID = -1498980828860433687L;

    /** iGenus */
    @BeanAttrInfo(value = "6", cnName = "iGenus")
    public static final long IGENUS = 6;
    /** CoreMail */
    @BeanAttrInfo(value = "7", cnName = "CoreMail")
    public static final long COREMAIL = 7;
    /** WinWebMail */
    @BeanAttrInfo(value = "10", cnName = "WinWebMail")
    public static final long WINWEBMAIL = 10;
    /** Microsoft Exchange */
    @BeanAttrInfo(value = "11", cnName = "Microsoft Exchange")
    public static final long MICROSOFT_EXCHANGE = 11;

}
