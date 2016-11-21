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
 * @Created 2013-5-30 上午10:37:15
 * @category 邮件模板结尾
 */
@Entity
@DiscriminatorValue("EMAIL_FOOTER")
@BeanName(name = "邮件模板结尾")
public class EmailFooter extends Dictionary {
    private static final long serialVersionUID = -1164672897360631928L;

    @BeanAttrInfo(value = "邮件结尾_1", cnName = "邮件结尾_1")
    public static final long EMAIL_FOOTER_1 = MessageTemplateVariable.EMAIL_FOOTER_1;

    @BeanAttrInfo(value = "邮件结尾_2", cnName = "邮件结尾_2")
    public static final long EMAIL_FOOTER_2 = MessageTemplateVariable.EMAIL_FOOTER_2;

    @BeanAttrInfo(value = "邮件结尾_3", cnName = "邮件结尾_3")
    public static final long EMAIL_FOOTER_3 = MessageTemplateVariable.EMAIL_FOOTER_3;

    @BeanAttrInfo(value = "邮件结尾_4", cnName = "邮件结尾_4")
    public static final long EMAIL_FOOTER_4 = MessageTemplateVariable.EMAIL_FOOTER_4;

    @BeanAttrInfo(value = "邮件结尾_5", cnName = "邮件结尾_5")
    public static final long EMAIL_FOOTER_5 = MessageTemplateVariable.EMAIL_FOOTER_5;
}
