package com.sjdf.platform.message.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 短息历史记录
 * User: ketqi
 * Date: 2015-07-16 10:46
 */
@Entity
@Table(name = "p_message_history_sms")
public class HistorySMSMessage extends BaseMessage {
    private static final long serialVersionUID = 8401867002402300058L;
}
