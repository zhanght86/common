package com.sjdf.platform.message.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 邮件消息
 * User: ketqi
 * Date: 2015-07-01 11:03
 */
@Entity
@Table(name = "p_message_email")
public class EmailMessage extends BaseEmailMessage implements Cloneable {
    private static final long serialVersionUID = 1609530046948111035L;

    @Override
    public EmailMessage clone() {
        EmailMessage message = null;
        try {
            message = (EmailMessage) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error(e.getMessage(), e);
        }
        return message;
    }
}
