package com.sjdf.platform.message.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 历史邮件消息
 * User: ketqi
 * Date: 2015-07-01 11:03
 */
@Entity
@Table(name = "p_message_history_email")
public class HistoryEmailMessage extends BaseEmailMessage {
    private static final long serialVersionUID = -2041777310706938818L;
}
