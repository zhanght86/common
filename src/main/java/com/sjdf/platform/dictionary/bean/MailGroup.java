package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author laberwu
 * @category 邮件组别
 * @ClassName MailGroup
 * @Created 2012 2012-9-19 上午10:33:47
 */
@Entity
@DiscriminatorValue("MAIL_GROUP")
@BeanName(name = "邮件组别")
public class MailGroup extends Dictionary {

    /**
     * @category
     * @field long serialVersionUID
     * @created 2012 2012-9-19 上午10:34:07
     */
    private static final long serialVersionUID = 5711649221791209939L;

    /** 代理下的客户; */
    @BeanAttrInfo(value = "1", cnName = "代理下的客户")
    public static final long AGENT_UNDER = 1;

}
