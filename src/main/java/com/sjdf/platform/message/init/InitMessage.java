package com.sjdf.platform.message.init;

import com.sjdf.platform.common.helper.ApplicationContextManager;
import com.sjdf.platform.common.init.StrutsStartupInitable;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.message.bean.EmailMessage;
import com.sjdf.platform.message.bean.SMSMessage;
import com.sjdf.platform.message.service.MessageService;
import com.sjdf.platform.message.task.SendManager;
import org.apache.struts2.dispatcher.Dispatcher;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * 消息相关初始化;启动时将待发送状态的消息添加到队列中
 * User: ketqi
 * Date: 2015-07-13 11:45
 */
public class InitMessage implements StrutsStartupInitable {
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public long systemType() {
        return SystemType.EISS_COMMON;
    }

    public void init(ServletContext servletContext, Dispatcher dispatcher) {
        MessageService messageService = (MessageService) ApplicationContextManager.getBean("commonMessageServiceImpl");
        List<SMSMessage> smsList = messageService.getWaitSendSmsList();
        if (smsList != null && !smsList.isEmpty()) {
            SendManager.getInstance().send(smsList);
        }

        List<EmailMessage> emailList = messageService.getWaitSendEmailList();
        if (emailList != null && !emailList.isEmpty()) {
            SendManager.getInstance().send(emailList);
        }
    }
}
