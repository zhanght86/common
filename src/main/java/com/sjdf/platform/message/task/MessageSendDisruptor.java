package com.sjdf.platform.message.task;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.helper.ApplicationContextManager;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.MessageTemplateType;
import com.sjdf.platform.dictionary.bean.MessageTemplateVariable;
import com.sjdf.platform.dictionary.bean.common.CommonGlobalConfig;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.dictionary.bean.common.SendStatus;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.message.api.EmailApi;
import com.sjdf.platform.message.api.SmsApi;
import com.sjdf.platform.message.bean.BaseMessage;
import com.sjdf.platform.message.bean.EmailMessage;
import com.sjdf.platform.message.bean.MessageApiUser;
import com.sjdf.platform.message.bean.SMSMessage;
import com.sjdf.platform.message.cache.MessageApiUserCache;
import com.sjdf.platform.messageTemplate.bean.MessageTemplate;
import com.sjdf.platform.messageTemplate.helper.MessageTemplateManager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消息发送队列
 * User: ketqi
 * Date: 2015-07-13 09:38
 */
final class MessageSendDisruptor {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(MessageSendDisruptor.class);
    private RingBuffer<MessageSendDisruptor.ValueEvent> ringBuffer;

    public MessageSendDisruptor() {
        init();
    }

    /** 初始化 */
    @SuppressWarnings("unchecked")
    private void init() {
        ExecutorService exec = Executors.newCachedThreadPool();
        Disruptor<MessageSendDisruptor.ValueEvent> disruptor = new Disruptor<>(new EventFactory<ValueEvent>() {
            @Override
            public MessageSendDisruptor.ValueEvent newInstance() {
                return new MessageSendDisruptor.ValueEvent();
            }
        }, CommonPlatformConstant.LENGTH_1024 * CommonPlatformConstant.LENGTH_32, exec, ProducerType.MULTI, new SleepingWaitStrategy());

        //消费者线程处理器
        disruptor.handleEventsWith(new MessageEventHandler(this));

        //启动队列
        ringBuffer = disruptor.start();
    }

    /** 事件持有者 */
    private static class ValueEvent<T extends BaseMessage> {
        private T message;

        public T getMessage() {
            return message;
        }

        public void setMessage(T message) {
            this.message = message;
        }
    }

    /**
     * 向队列中添加消息记录
     *
     * @param message 消息记录
     */
    public <T extends BaseMessage> void add(T message) {
        if (message == null) {
            return;
        }

        long seq = ringBuffer.next();
        try {
            //noinspection unchecked
            ringBuffer.get(seq).setMessage(message);
        } finally {
            ringBuffer.publish(seq);
        }
    }

    /**
     * 向队列中添加消息记录
     *
     * @param messageList 消息记录
     */
    public <T extends BaseMessage> void add(List<T> messageList) {
        if (messageList == null || messageList.isEmpty()) {
            return;
        }

        for (T rec : messageList) {
            add(rec);
        }
    }

    /**
     * 获取队列剩余空间
     *
     * @return long
     */
    public final long remainingCapacity() {
        return ringBuffer.remainingCapacity();
    }

    /**
     * 队列大小
     *
     * @return int
     */
    public final int bufferSize() {
        return ringBuffer.getBufferSize();
    }

    /**
     * 获取已发布事件的数目或者已经消费的总数
     *
     * @return long
     */
    public final long cursor() {
        return ringBuffer.getCursor();
    }

    /** 消费者线程处理器 */
    private static class MessageEventHandler implements EventHandler<ValueEvent> {
        private MessageSendDisruptor disruptor;
        private BaseService baseService;

        private MessageEventHandler(MessageSendDisruptor disruptor) {
            this.disruptor = disruptor;
            this.baseService = (BaseService) ApplicationContextManager.getBean("commonBaseService");
        }

        @Override
        public void onEvent(ValueEvent event, long sequence, boolean endOfBatch) throws Exception {
            BaseMessage message = event.getMessage();
            MessageApiUser user = MessageApiUserCache.getInstance().getByUserId(message.getUserId());
            if (user == null || !user.isValidate()) {
                message.setStatus(SendStatus.INVALIDATE_DATA);
                message.setRemark("userId invalidate");
                baseService.update(message);
                return;
            }

            boolean isSmsMessage = message instanceof SMSMessage;
            try {
                Map<String, String> failMap = null;
                if (isSmsMessage) {
                    failMap = send(user, (SMSMessage) message);
                } else if (message instanceof EmailMessage) {
                    failMap = send(user, (EmailMessage) message);
                }

                if (failMap == null) {
                    message.setStatus(SendStatus.SEND_FAIL);
                    message.setRemark("message type is invalidate");
                    baseService.update(message);
                    return;
                }

                if (!failMap.isEmpty()) {
                    message.addFailCounter();
                    message.setStatus(SendStatus.SEND_FAIL);
                    message.setFailAddressList(failMap.keySet());
                    message.setResultMap(failMap);
                } else {
                    message.setStatus(SendStatus.SEND_SUCCESS);
                    message.setRemark("");
                }

                baseService.update(message);
            } catch (Exception e) {
                e.printStackTrace();

                message.setStatus(SendStatus.SEND_FAIL);
                message.setRemark(PlatformUtils.getStackTrace(e));
                baseService.update(message);

                LOGGER.error(message.toString(), e);
            } finally {
                //noinspection unchecked
                event.setMessage(null);
                isRepeatSend(message);
            }
        }

        /**
         * 发送短信
         *
         * @param user    授权用户
         * @param message 消息
         * @return 发送结果
         */
        private Map<String, String> send(MessageApiUser user, SMSMessage message) {
            List<String> addressList = message.getFailAddressList();
            Map<String, String> failMap = new HashMap<>();
            for (String address : addressList) {
                SmsApi api = user.getOneValidSmsApi();
                if (api == null) {
                    message.setStatus(SendStatus.INVALIDATE_DATA);
                    message.setRemark("sms api invalidate");
                    baseService.update(message);
                    LOGGER.error("sms message api create instance fail:", message.getUserId());
                    return failMap;
                }

                message.setSendApiName(api.getConfig().getName());
                String result = api.send(address, message.getContent());
                if (PlatformUtils.hasText(result)) {
                    failMap.put(address, api.getConfig().getName() + result);
                }
            }
            return failMap;
        }

        /**
         * 发送邮件
         *
         * @param user    授权用户
         * @param message 消息
         * @return 发送结果
         */
        private Map<String, String> send(MessageApiUser user, EmailMessage message) {
            List<String> addressList = message.getFailAddressList();
            Map<String, String> failMap = new HashMap<>();
            for (String address : addressList) {
                EmailApi api = user.getOneValidEmailApi();

                if (api == null) {
                    message.setStatus(SendStatus.INVALIDATE_DATA);
                    message.setRemark("email api invalidate");
                    baseService.update(message);
                    LOGGER.error("email message api create instance fail:", message.getUserId());
                    return failMap;
                }

                message.setSendApiName(api.getConfig().getName());
                String result = api.send(address, message.getCcAddress(), message.getBccAddress(), message.getTitle(), message.getContent());
                if (PlatformUtils.hasText(result)) {
                    failMap.put(address, api.getConfig().getName() + result);
                }
            }
            return failMap;
        }

        /**
         * 但短息发送失败时,邮件通知相关人员
         *
         * @param message 消息
         */
        private void isRepeatSend(BaseMessage message) {
            if (message.getStatus() == SendStatus.SEND_FAIL && message.getFailCounter() <= CommonPlatformConstant.LENGTH_4) {
                disruptor.add(message);

                //如果发送短信失败，邮件通知到特定账号
                if (message.getFailCounter() == CommonPlatformConstant.LENGTH_4 && message instanceof SMSMessage) {
                    List<MessageApiUser> userList = MessageApiUserCache.getInstance().getList(MessageType.EMAIL);
                    if (userList.isEmpty()) {
                        return;
                    }
                    MessageApiUser apiUser = userList.get(0);

                    MessageTemplate template = MessageTemplateManager.getInstance().getSingle(MessageTemplateType.COMMON_MESSAGE_SMS_SEND_FAIL_NOTIFY);
                    if (template == null) {
                        return;
                    }
                    Map<String, String> map = new HashMap<>();
                    map.put(ConfigManager.getInstance().getValue(MessageTemplateVariable.class, MessageTemplateVariable.MOBILE), message.getFailAddress());
                    map.put(ConfigManager.getInstance().getValue(MessageTemplateVariable.class, MessageTemplateVariable.REASON), message.getRemark());
                    map.put(ConfigManager.getInstance().getValue(MessageTemplateVariable.class, MessageTemplateVariable.UPDATE_CONTENT), message.getContent());
                    String content = template.renderEmail(map);
                    if (content == null) {
                        return;
                    }

                    EmailMessage email = new EmailMessage();
                    email.setStatus(SendStatus.WAIT_SEND);
                    email.setSendTime(new Date());
                    email.setAddress(ConfigManager.getInstance().getValue(CommonGlobalConfig.class, CommonGlobalConfig.SMS_FAIL_NOTIFY_EMAIL_ADRESS));
                    email.setFailAddress(email.getAddress());
                    email.setContent(content);
                    email.setTitle(template.getTitle());
                    email.setCompany(apiUser.getCompany());
                    email.setSystemType(apiUser.getSystemType());
                    email.setUserId(apiUser.getUserId());
                    email.setCreateUser(email.getUserId());
                    email.setUpdateUser(email.getUserId());
                    baseService.save(email);
                    disruptor.add(email);
                }
            }
        }
    }
}
