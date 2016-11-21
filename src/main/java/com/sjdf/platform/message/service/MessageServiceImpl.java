package com.sjdf.platform.message.service;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.command.RuntimeExec;
import com.sjdf.platform.common.command.RuntimeExecResult;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.*;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.bean.common.CommonGlobalConfig;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.dictionary.bean.common.SendStatus;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.bean.LogUser;
import com.sjdf.platform.message.bean.*;
import com.sjdf.platform.message.cache.MessageApiUserCache;
import com.sjdf.platform.message.cache.SendApiConfigCache;
import com.sjdf.platform.message.constant.MassMessageType;
import com.sjdf.platform.message.task.SendManager;
import com.sjdf.platform.message.utils.MessageExcelUtils;
import com.sjdf.platform.rbac.bean.TreeNode;
import net.sf.json.JSONObject;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * 消息service实现
 * User: ketqi
 * Date: 2015-07-01 14:53
 */
public class MessageServiceImpl extends BaseServiceImpl implements MessageService {
    /**
     * 消息接口配置列表
     *
     * @param config 筛选限制条件
     * @param page   分页组件
     * @return 接口配置列表
     */
    @Override
    public List<SendApiConfig> configList(SendApiConfig config, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SendApiConfig.class);
        if (config != null) {
            if (config.getMessageType() != 0L) {
                criteria.add(Restrictions.eq("messageType", config.getMessageType()));
            }
            if (config.getValid() != 0L) {
                criteria.add(Restrictions.eq("valid", config.getValid()));
            }
            if (PlatformUtils.hasText(config.getHostUrl())) {
                criteria.add(Restrictions.like("hostUrl", config.getHostUrl(), MatchMode.ANYWHERE));
            }
            if (PlatformUtils.hasText(config.getSourceAddress())) {
                criteria.add(Restrictions.like("sourceAddress", config.getSourceAddress(), MatchMode.ANYWHERE));
            }
            if (PlatformUtils.hasText(config.getPersonalName())) {
                criteria.add(Restrictions.like("personalName", config.getPersonalName(), MatchMode.ANYWHERE));
            }
            if (PlatformUtils.hasText(config.getUserName())) {
                criteria.add(Restrictions.like("userName", config.getUserName(), MatchMode.ANYWHERE));
            }
        }
        return baseDao.listByCriteria(criteria, page);
    }

    /**
     * 添加或者更新接口配置
     *
     * @param config   接口配置
     * @param operator 操作人员
     * @return 消息组件
     */
    @Override
    public Message saveOrUpdateConfig(SendApiConfig config, String operator) {
        if (config == null) {
            return Message.createMessage("message.send.api.config.invalidate");
        }
        Message message = config.valid();
        if (message.hasErrorMessage()) {
            return message;
        }

        SendApiConfig oldConfig;
        if (config.getId() > 0L && (oldConfig = SendApiConfigCache.getInstance().get(config.getId())) != null) {
            if (!oldConfig.getName().equals(config.getName()) && existApiConfigName(config.getName())) {
                return Message.createMessage("message.send.api.config.name.repeat.invalidate");
            }

            String info = oldConfig.wrapUpdateContent(config, true);
            oldConfig.setName(config.getName());
            oldConfig.setMessageType(config.getMessageType());
            oldConfig.setValid(config.getValid());
            oldConfig.setSendApiImpl(config.getSendApiImpl());
            oldConfig.setHostUrl(config.getHostUrl());
            oldConfig.setSourceAddress(config.getSourceAddress());
            oldConfig.setPersonalName(config.getPersonalName());
            oldConfig.setUserName(config.getUserName());
            oldConfig.setPwd(config.getPwd());
            oldConfig.setUpdateTime(new Date());
            oldConfig.setUpdateUser(operator);
            baseDao.update(oldConfig);

            SendApiConfigCache.getInstance().put(oldConfig);
            logger.infoDB(createLog(operator, oldConfig.getId(), FunctionType.COMMON_MESSAGE_MANAGE, OperatorAction.MODIFY, LogType.INFO, info));
        } else {
            if (existApiConfigName(config.getName())) {
                return Message.createMessage("message.send.api.config.name.repeat.invalidate");
            }

            config.setCreateUser(operator);
            config.setUpdateUser(operator);
            baseDao.save(config);
            SendApiConfigCache.getInstance().put(config);
            logger.infoDB(createLog(operator, config.getId(), FunctionType.COMMON_MESSAGE_MANAGE, OperatorAction.ADD, LogType.INFO, config.wrapUpdateContent(null, false)));
            oldConfig = config;
        }

        return Message.createMessage().setReturnData(oldConfig);
    }

    /**
     * 删除接口配置
     *
     * @param idx      接口配置id
     * @param operator 操作人员
     * @return 消息组件
     */
    @Override
    public Message delConfig(long idx, String operator) {
        SendApiConfig oldConfig;
        if (idx > 0 && (oldConfig = SendApiConfigCache.getInstance().get(idx)) != null) {
            baseDao.delete(oldConfig);
            SendApiConfigCache.getInstance().remove(oldConfig);
            logger.infoDB(createLog(operator, idx, FunctionType.COMMON_MESSAGE_MANAGE, OperatorAction.DELETE, LogType.INFO, oldConfig.wrapUpdateContent(null, false)));
        }

        return Message.createEmptyMessage();
    }

    /**
     * 信息接口授权列表
     *
     * @param user 限制条件
     * @param page 分页组件
     * @return 列表
     */
    @Override
    public List<MessageApiUser> userList(MessageApiUser user, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(MessageApiUser.class);
        if (user != null) {
            if (user.getCompany() != 0L) {
                criteria.add(Restrictions.eq("company", user.getCompany()));
            }
            if (user.getSystemType() != 0L) {
                criteria.add(Restrictions.eq("systemType", user.getSystemType()));
            }
            if (user.getValid() != 0L) {
                criteria.add(Restrictions.eq("valid", user.getValid()));
            }
            if (PlatformUtils.hasText(user.getSmsApi())) {
                criteria.add(Restrictions.like("smsApi", user.getSmsApi() + CommonPlatformConstant.COMMA_SEPARATOR, MatchMode.ANYWHERE));
            }
            if (PlatformUtils.hasText(user.getEmailApi())) {
                criteria.add(Restrictions.like("emailApi", user.getEmailApi() + CommonPlatformConstant.COMMA_SEPARATOR, MatchMode.ANYWHERE));
            }
            if (PlatformUtils.hasText(user.getUserId())) {
                criteria.add(Restrictions.eq("userId", user.getUserId()));
            }
        }
        return baseDao.listByCriteria(criteria, page);
    }

    /**
     * 添加或者更新信息接口授权信息
     *
     * @param user     信息接口授权信息
     * @param operator 操作人员
     * @return 消息组件
     */
    @Override
    public Message saveOrUpdateUser(MessageApiUser user, String operator) {
        if (user == null) {
            return Message.createMessage("message.api.user.invalidate");
        }
        Message message = user.valid();
        if (message.hasErrorMessage()) {
            return message;
        }

        MessageApiUser oldUser;
        if (user.getId() > 0L && (oldUser = MessageApiUserCache.getInstance().get(user.getId())) != null) {
            if (!oldUser.getUserId().equals(user.getUserId()) && existApiUserId(user.getUserId())) {
                return Message.createMessage("message.api.user.userId.repeat.invalidate");
            }

            String info = oldUser.wrapUpdateContent(user, true);
            oldUser.setValid(user.getValid());
            oldUser.setCompany(user.getCompany());
            oldUser.setConnPwd(user.getConnPwd());
            oldUser.setEmailApi(user.getEmailApi());
            oldUser.setSmsApi(user.getSmsApi());
            oldUser.setSystemType(user.getSystemType());
            oldUser.setUserId(user.getUserId());
            oldUser.setName(user.getName());
            oldUser.setRemark(user.getRemark());
            oldUser.setUpdateTime(new Date());
            oldUser.setUpdateUser(operator);
            baseDao.update(oldUser);

            MessageApiUserCache.getInstance().put(oldUser);
            logger.infoDB(createLog(operator, oldUser.getId(), FunctionType.COMMON_MESSAGE_MANAGE, OperatorAction.MODIFY, LogType.INFO, info));
        } else {
            if (existApiUserId(user.getUserId())) {
                return Message.createMessage("message.api.user.userId.repeat.invalidate");
            }

            user.setCreateUser(operator);
            user.setUpdateUser(operator);
            baseDao.save(user);
            MessageApiUserCache.getInstance().put(user);
            logger.infoDB(createLog(operator, user.getId(), FunctionType.COMMON_MESSAGE_MANAGE, OperatorAction.MODIFY, LogType.INFO, user.wrapUpdateContent(null, false)));
        }

        return Message.createEmptyMessage();
    }

    /**
     * 短信列表
     *
     * @param message 短信限制条件
     * @param page    分页组件
     * @return 短信列表
     */
    @Override
    public List<SMSMessage> smsMessageList(SMSMessage message, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SMSMessage.class);
        wrapMessageRestrictions(criteria, message);
        return baseDao.listByCriteria(criteria, page, Order.desc("id"));
    }

    /**
     * 添加或更新短信
     *
     * @param message  短信消息
     * @param xls      短信Excel模板
     * @param operator 操作人员
     * @return 消息组件
     */
    @Override
    public Message saveOrUpdateSms(SMSMessage message, File xls, String operator) {
        if (message == null) {
            return Message.createMessage("message.send.message.invalidate");
        }

        //如果Excel模板文件存在
        if (xls != null && xls.exists()) {
            Message userinvalidate = Message.createMessage("message.send.api.config.user.name.invalidate");
            if (!PlatformUtils.hasText(message.getUserId())) {
                return userinvalidate;
            } else {
                MessageApiUser user = MessageApiUserCache.getInstance().getByUserId(message.getUserId());
                if (user == null || !user.isValidate()) {
                    return userinvalidate;
                }

                message.setCompany(user.getCompany());
                message.setSystemType(user.getSystemType());
            }

            List<SMSMessage> messageList;
            try {
                messageList = MessageExcelUtils.parse(xls);
            } catch (Exception e) {
                logger.error("Excel parse fail", e);
                return Message.createMessage("message.send.message.excel.parse.fail");
            }
            if (PlatformUtils.isNotEmpty(messageList)) {
                for (SMSMessage sms : messageList) {
                    sms.setUserId(message.getUserId());
                    sms.setCompany(message.getCompany());
                    sms.setSystemType(message.getSystemType());
                    sms.setStatus(SendStatus.WAIT_SEND);
                    sms.setFailAddress(sms.getAddress());
                    sms.setCreateUser(operator);
                    sms.setUpdateUser(operator);
                    sms.setSendTimeList(message.getSendTimeList());
                    sendMessage(sms);
                }
            }

            return Message.createEmptyMessage();
        }


        SMSMessage oldMessage;
        if (message.getId() > 0L && (oldMessage = baseDao.get(SMSMessage.class, message.getId())) != null) {
            if (oldMessage.getStatus() != SendStatus.SEND_SUCCESS) {
                oldMessage.setStatus(message.getStatus());
            }
            oldMessage.setContent(message.getContent());
            oldMessage.setUpdateTime(new Date());
            oldMessage.setUpdateUser(operator);
            baseDao.update(oldMessage);
            if (SendStatus.isSendStatus(oldMessage.getStatus())) {
                SendManager.getInstance().send(oldMessage);
            }
            logger.infoDB(createLog(operator, oldMessage.getId(), FunctionType.COMMON_MESSAGE_MANAGE, OperatorAction.MODIFY, LogType.INFO, oldMessage.toString()));
        } else {
            Message msg = message.valid();
            if (msg.hasErrorMessage()) {
                return msg;
            }
            message.setCreateUser(operator);
            message.setUpdateUser(operator);

            sendMessage(message);
        }

        return Message.createEmptyMessage();
    }

    /**
     * 添加或更新邮件信息
     *
     * @param message  邮件信息
     * @param operator 操作人员
     * @return 消息组件
     */
    @Override
    public Message saveOrUpdateEmail(EmailMessage message, String operator) {
        if (message == null) {
            return Message.createMessage("message.send.message.invalidate");
        }

        EmailMessage oldMessage;
        if (message.getId() > 0L && (oldMessage = baseDao.get(EmailMessage.class, message.getId())) != null) {
            if (oldMessage.getStatus() != SendStatus.SEND_SUCCESS) {
                oldMessage.setStatus(message.getStatus());
            }
            oldMessage.setTitle(message.getTitle());
            oldMessage.setContent(message.getContent());
            oldMessage.setUpdateTime(new Date());
            oldMessage.setUpdateUser(operator);
            baseDao.update(oldMessage);
            if (SendStatus.isSendStatus(oldMessage.getStatus())) {
                SendManager.getInstance().send(oldMessage);
            }
            logger.infoDB(createLog(operator, oldMessage.getId(), FunctionType.COMMON_MESSAGE_MANAGE, OperatorAction.MODIFY, LogType.INFO, oldMessage.toString()));
        } else {
            Message msg = message.valid();
            if (msg.hasErrorMessage()) {
                return msg;
            }

            message.setUpdateUser(operator);
            message.setCreateUser(operator);
            //发送消息
            sendMessage(message);
        }

        return Message.createEmptyMessage();
    }

    /**
     * 更新消息发送状态
     *
     * @param messageType 消息类型
     * @param ids         消息id列表
     * @param status      状态
     * @param operator    操作人员
     * @return 消息组件
     */
    @Override
    public Message updateStatus(long messageType, long[] ids, long status, String operator) {
        if (ids == null || ids.length == 0) {
            return Message.createEmptyMessage();
        }

        if (!ConfigManager.getInstance().existAttr(SendStatus.class, status)) {
            return Message.createMessage("message.send.message.status.invalidate");
        }

        if (messageType == MessageType.SMS) {
            for (long id : ids) {
                SMSMessage oldMessage = baseDao.get(SMSMessage.class, id);
                if (oldMessage == null || oldMessage.getStatus() == SendStatus.SEND_SUCCESS) {
                    continue;
                }
                oldMessage.setStatus(status);
                oldMessage.setUpdateUser(operator);
                baseDao.update(oldMessage);

                if (SendStatus.isSendStatus(oldMessage.getStatus())) {
                    SendManager.getInstance().send(oldMessage);
                }
            }
        } else if (messageType == MessageType.EMAIL) {
            for (long id : ids) {
                EmailMessage oldMessage = baseDao.get(EmailMessage.class, id);
                if (oldMessage == null || oldMessage.getStatus() == SendStatus.SEND_SUCCESS) {
                    continue;
                }

                oldMessage.setStatus(status);
                oldMessage.setUpdateUser(operator);
                baseDao.update(oldMessage);

                if (SendStatus.isSendStatus(oldMessage.getStatus())) {
                    SendManager.getInstance().send(oldMessage);
                }
            }
        } else if (messageType == MessageType.WECHAT) {
            for (long id : ids) {
                WechatMessage oldMessage = baseDao.get(WechatMessage.class, id);
                if (oldMessage == null || oldMessage.getStatus() == SendStatus.SEND_SUCCESS) {
                    continue;
                }

                oldMessage.setStatus(status);
                oldMessage.setUpdateUser(operator);
                baseDao.update(oldMessage);

                if (SendStatus.isSendStatus(oldMessage.getStatus())) {
                    return sendWechatMessage(oldMessage);
                }
            }
        }
        return Message.createEmptyMessage();
    }

    /**
     * 邮件列表
     *
     * @param message 邮件限制列表
     * @param page    分页组件
     * @return 邮件列表
     */
    @Override
    public List<EmailMessage> emailMessageList(EmailMessage message, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(EmailMessage.class);
        wrapMessageRestrictions(criteria, message);
        if (message != null && PlatformUtils.hasText(message.getTitle())) {
            criteria.add(Restrictions.like("title", message.getTitle(), MatchMode.ANYWHERE));
        }
        return baseDao.listByCriteria(criteria, page, Order.desc("id"));
    }

    /**
     * 获取待发送的短信列表
     *
     * @return 短信列表
     */
    @Override
    public List<SMSMessage> getWaitSendSmsList() {
        Map<String, Object> params = new HashMap<>();
        params.put("statusList", SendStatus.getWaitSendList());
        params.put("sendTime", new Date());
        return baseDao.find("from SMSMessage where status in :statusList and sendTime <= :sendTime", params);
    }

    /**
     * 获取待发送的邮件列表
     *
     * @return 邮件列表
     */
    @Override
    public List<EmailMessage> getWaitSendEmailList() {
        Map<String, Object> params = new HashMap<>();
        params.put("statusList", SendStatus.getWaitSendList());
        params.put("sendTime", new Date());
        return baseDao.find("from EmailMessage where status in :statusList and sendTime <= :sendTime", params);
    }

    /**
     * 信息模板树
     *
     * @param idx         上级id
     * @param messageType 消息类型
     * @return 信息模板列表
     */
    @Override
    public List<InfoTemplate> listTemplateTree(long idx, long messageType) {
        DetachedCriteria criteria = DetachedCriteria.forClass(InfoTemplate.class);

        if (idx == 0) {
            criteria.add(Restrictions.isNull("parent"));
        } else {
            criteria.createAlias("parent", "parent").add(Restrictions.eq("parent.id", idx));
        }

        if (messageType != 0L) {
            criteria.add(Restrictions.eq("messageType", messageType));
        }

        List<InfoTemplate> list = baseDao.listByCriteria(criteria);
        for (InfoTemplate tree : list) {
            tree.hibernateInitialize();
        }

        // 构建根结点
        if (idx <= 0) {
            InfoTemplate node = TreeNode.createRootNode(InfoTemplate.class);
            node.setChildList(list);
            return Collections.singletonList(node);
        }

        return list;
    }

    /**
     * 添加模板
     *
     * @param info     模板信息
     * @param operator 操作人员
     * @return 消息组件
     */
    @Override
    public Message addTemplate(InfoTemplate info, String operator) {
        if (info == null) {
            return Message.createMessage("message.info.template.nonexist");
        }

        // 名称空值检查
        if (!PlatformUtils.hasText(info.getName())) {
            return Message.createMessage("message.info.template.name.empty");
        }

        // 惟一性检查
        if (hasRoleName(info.getName())) {
            return Message.createMessage("message.info.template.name.exist", info.getName());
        }

        // parent
        if (info.getParent() != null && info.getParent().getId() > 0) {
            InfoTemplate parent = baseDao.get(InfoTemplate.class, info.getParent().getId());
            if (parent == null) {
                return Message.createMessage("message.info.template.parent.nonexist");
            }
            info.parent(parent);
        } else {
            info.parent(null);
        }

        baseDao.save(info);
        return Message.createEmptyMessage();
    }

    /**
     * 删除模板
     *
     * @param idx      模板信息id
     * @param operator 操作人员
     * @return 消息组件
     */
    @Override
    public Message delTemplate(long idx, String operator) {
        InfoTemplate info = baseDao.get(InfoTemplate.class, idx);
        // 空值检查
        if (info == null) {
            return Message.createMessage("message.info.template.nonexist");
        }

        if (info.getChildList() != null && !info.getChildList().isEmpty()) {
            return Message.createMessage("message.info.template.childList.delete.fail", info.getName());
        }

        // 删除信息
        baseDao.delete(info);
        return Message.createEmptyMessage();
    }

    /**
     * 更新模板
     *
     * @param info     模板信息
     * @param operator 操作人员
     * @return 消息组件
     */
    @Override
    public Message updateTemplate(InfoTemplate info, String operator) {
        InfoTemplate oldInfo;
        // 空值检查
        if (info == null || info.getId() <= 0 || (oldInfo = baseDao.get(InfoTemplate.class, info.getId())) == null) {
            return Message.createMessage("message.info.template.nonexist");
        }

        // 名称空值检查
        if (!PlatformUtils.hasText(info.getName())) {
            return Message.createMessage("message.info.template.name.empty");
        }

        // 名称重复检查
        if (!info.getName().equals(oldInfo.getName())) {
            if (hasRoleName(info.getName())) {
                return Message.createMessage("message.info.template.name.exist", info.getName());
            }
            oldInfo.setName(info.getName());
            baseDao.update(oldInfo);
        }
        return Message.createEmptyMessage();
    }

    /**
     * 转移邮件信息
     *
     * @param businessUserIdList 业务类型邮件的用户列表
     * @param isIncluded         信息是否包含businessUserIdList
     */
    @Override
    public void transferEmail(Set<String> businessUserIdList, boolean isIncluded) {
        String fields = "id,company,systemType,userId,address,failAddress,content,send_status,send_time,sendApiName,remark,createUser,createTime,updateUser,updateTime,m_title,ccAddress,bccAddress,m_group,m_type,sendUser";

        StringBuilder sql = new StringBuilder(CommonPlatformConstant.LENGTH_512);
        sql.append("insert into p_message_history_email(").append(fields).append(")");
        sql.append(" select ").append(fields).append(" from p_message_email where createTime <= :createTime");

        Map<String, Object> params = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        String deleteSql = "delete from EmailMessage where createTime <= :createTime";

        //1).将当前表的业务类型邮件，一年以前的移动到backup表;业务类型邮件：验证码，相关系统的通知（续费，购买，备案）
        //2).将当前表的非业务类型邮件，一个月以前的移动到backup表;非业务类型：广告，工单，监控
        if (businessUserIdList == null || businessUserIdList.isEmpty()) {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
            params.put("createTime", calendar.getTime());

            logger.info(sql.toString());
            baseDao.updateSql(sql.toString(), params);

            logger.info(deleteSql);
            baseDao.update(deleteSql, params);
        } else {
            String str = " and userId ";
            if (isIncluded) {
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
                str += " in :list";
            } else {
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
                str += " not in :list";
            }
            sql.append(str);
            deleteSql += str;

            params.put("createTime", calendar.getTime());
            params.put("list", businessUserIdList);
            logger.info(sql.toString());
            baseDao.updateSql(sql.toString(), params);

            logger.info(deleteSql);
            baseDao.update(deleteSql, params);
        }
    }

    /** 转移短信信息 */
    @Override
    public void transferSms() {
        //将当前表的短信，一年以前的移动到backup表
        String fields = "id,company,systemType,userId,address,failAddress,content,send_status,send_time,sendUser,sendApiName,remark,createUser,createTime,updateUser,updateTime";

        StringBuilder sql = new StringBuilder(CommonPlatformConstant.LENGTH_512);
        sql.append("insert into p_message_history_sms(").append(fields).append(")");
        sql.append(" select ").append(fields).append(" from p_message_sms where createTime <= :createTime");

        Map<String, Object> params = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        params.put("createTime", calendar.getTime());

        logger.info(sql.toString());
        baseDao.updateSql(sql.toString(), params);

        //删除
        baseDao.update("delete from SMSMessage where createTime <= :createTime", params);
    }

    /** 删除备份信息（转储至本地文件备份） */
    @Override
    public void delMessageBackup(long messageType) {
        Map<String, Object> params = new HashMap<>();
        // 备份文件存储路径
        String rootPath;
        // 备份邮件列表
        List<HistoryEmailMessage> emailList = null;
        // 备份短信列表
        List<HistorySMSMessage> smsList = null;
        // 抽取数据
        Calendar calendar = Calendar.getInstance();
        if (messageType == MessageType.EMAIL) {
            // 将backup表的2年以前的数据进行删除
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - CommonPlatformConstant.LENGTH_2);
            params.put("createTime", calendar.getTime());
            emailList = baseDao.find("from HistoryEmailMessage where createTime <= :createTime", params);
            rootPath = ConfigManager.getInstance().getValue(CommonGlobalConfig.class, CommonGlobalConfig.EMAIL_STORE_LOCAL_FILE_PATH);
        } else if (messageType == MessageType.SMS) {
            // 将backup表的5年以前的数据进行删除
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - CommonPlatformConstant.LENGTH_5);
            params.put("createTime", calendar.getTime());
            smsList = baseDao.find("from HistorySMSMessage where createTime <= :createTime", params);
            rootPath = ConfigManager.getInstance().getValue(CommonGlobalConfig.class, CommonGlobalConfig.SMS_STORE_LOCAL_FILE_PATH);
        } else {
            logger.error("delMessageBackup messageType invalidate:" + messageType);
            return;
        }

        logger.info("delMessageBackup start rootPath:" + rootPath);
        StringBuilder builder = new StringBuilder();
        builder.append(rootPath);
        builder.append(calendar.get(Calendar.YEAR));
        // Calendar.MONTH的范围是（0~11）
        builder.append("/").append(calendar.get(Calendar.MONTH) + 1);
        builder.append("/").append(DateUtils.formatDate(calendar.getTime())).append(".txt");

        // 创建文件并写数据
        FileUtils.createFile(builder.toString());
        Path path = Paths.get(builder.toString());
        long count = 0L;
        try (BufferedWriter bw = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            // 保存备份邮件
            if (emailList != null && !emailList.isEmpty()) {
                count += emailList.size();
                for (HistoryEmailMessage backupBean : emailList) {
                    bw.write(backupBean.toString());
                    bw.newLine();
                }
                bw.flush();
                // 删除历史数据
                baseDao.deleteAll(emailList);
            }
            // 保存备份短信
            if (smsList != null && !smsList.isEmpty()) {
                count += smsList.size();
                for (HistorySMSMessage backupBean : smsList) {
                    bw.write(backupBean.toString());
                    bw.newLine();
                }
                bw.flush();
                // 删除历史数据
                baseDao.deleteAll(smsList);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        logger.info("delMessageBackup 1.删除历史数据数量：" + count);

        // 3.打包历史数据
        logger.info("delMessageBackup 2.打包历史数据");
        File root = new File(rootPath);
        if (!root.exists()) {
            return;
        }

        File[] yearFiles = root.listFiles();
        if (yearFiles == null) {
            return;
        }

        for (File file : yearFiles) {
            // 是目录并且文件夹的名称不等于备份年限
            if (file.isDirectory() && !file.getName().equals(String.valueOf(calendar.get(Calendar.YEAR)))) {
                // tar -zcvf /data/home/email/2015.tar.gz /data/home/email/2015
                // tar -zcvf /data/home/sms/2015.tar.gz /data/home/sms/2015
                String gzPath = rootPath + file.getName() + ".tar.gz";
                StringBuilder command = new StringBuilder();
                command.append("tar -zcvf ");
                command.append(gzPath).append(" ");
                command.append(file.getAbsolutePath());

                RuntimeExecResult result = RuntimeExec.instance().exec(command.toString());
                if (result.getRet() == 0) {
                    // 打包完毕后删除
                    FileUtils.delFile(file.getAbsolutePath());
                }
            }
        }
        logger.info("delMessageBackup end");
    }

    /** 检测模板名称是否存在 */
    private boolean hasRoleName(String name) {
        return baseDao.countHql("select count(t) from InfoTemplate t where t.name = ?", name) > 0;
    }

    /**
     * 封装查询条件
     *
     * @param criteria 条件
     * @param message  消息
     */
    private void wrapMessageRestrictions(DetachedCriteria criteria, BaseMessage message) {
        if (message != null) {
            if (message.getCompany() != 0L) {
                criteria.add(Restrictions.eq("company", message.getCompany()));
            }
            if (message.getSystemType() != 0L) {
                criteria.add(Restrictions.eq("systemType", message.getSystemType()));
            }
            if (PlatformUtils.hasText(message.getUserId())) {
                criteria.add(Restrictions.eq("userId", message.getUserId()));
            }
            if (PlatformUtils.hasText(message.getAddress())) {
                criteria.add(Restrictions.like("address", message.getAddress(), MatchMode.ANYWHERE));
            }
            if (PlatformUtils.hasText(message.getContent())) {
                criteria.add(Restrictions.like("content", message.getContent(), MatchMode.ANYWHERE));
            }
            if (message.getStatus() != 0L) {
                criteria.add(Restrictions.eq("status", message.getStatus()));
            }
            if (message.getCreateTime() != null) {
                criteria.add(Restrictions.gt("sendTime", message.getCreateTime()));
            }
            if (message.getUpdateTime() != null) {
                criteria.add(Restrictions.lt("sendTime", message.getUpdateTime()));
            }
        }
    }

    /**
     * 判断消息接口配置名称是否存在
     *
     * @param name 名称
     * @return bool
     */
    private boolean existApiConfigName(String name) {
        Number number = baseDao.numberHql("select count(id) from SendApiConfig where name = ?", name);
        return number != null && number.intValue() > 0;
    }

    /**
     * 判断消息接口授权名称是否存在
     *
     * @param userId 授权用户名称
     * @return bool
     */
    private boolean existApiUserId(String userId) {
        Number number = baseDao.numberHql("select count(id) from MessageApiUser where userId = ?", userId);
        return number != null && number.intValue() > 0;
    }

    /** 创建日志,超管后台 */
    private LogBean createLog(String loginUser, long resourceId, long functionType, long operatorAction, long logType, String content) {
        LogBean log = new LogBean();
        log.setCreateUser(loginUser);
        log.setUpdateUser(loginUser);
        log.setLogUser(new LogUser(SystemType.EISS_COMMON, PlatformUtils.hasText(loginUser) ? loginUser : "system", "common", Tools.getIpAddress()));
        log.setSubsystemType(SubsystemType.BUSINESS_BACKGROUND);
        log.setFunctionClass(FunctionClass.COMMON_FOUNTION);
        log.setFunctionType(functionType);
        log.setOperatorAction(operatorAction);
        log.setResourceId(String.valueOf(resourceId));
        log.setLogType(logType);
        log.setMessage("");
        if (logType == LogType.INFO) {
            log.setOperationalContent(content);
            log.setErrorInfo("");
        } else {
            log.setOperationalContent(content);
            log.setErrorInfo(content);
        }
        return log;
    }

    /**
     * 统计当前发送的推荐码邮件
     *
     * @param title    邮件标题
     * @param sendUser 发送用户
     */
    @Override
    public long countRecommendEmail(String title, String sendUser) {
        String hql = "select count(*) from EmailMessage where title=? and sendUser=? and createTime between ? and ?";
        Number number = baseDao.numberHql(hql, title, sendUser, DateUtils.getBeginOfDate(), DateUtils.getEndOfDate());
        return number.longValue();
    }

    /**
     * 添加或更新微信信息
     *
     * @param message  微信信息
     * @param operator 操作人员
     * @return 消息组件
     */
    @Override
    public Message saveOrUpdateWechat(WechatMessage message, String operator) {
        if (message == null) {
            return Message.createMessage("message.send.message.invalidate");
        }
        WechatMessage oldMessage;
        WechatMessage sendMessage = message;
        if (message.getId() > 0L && (oldMessage = baseDao.get(WechatMessage.class, message.getId())) != null) {
            if (oldMessage.getStatus() != SendStatus.SEND_SUCCESS) {
                oldMessage.setStatus(message.getStatus());
            }
            oldMessage.setContent(message.getContent());
            oldMessage.setUpdateTime(new Date());
            oldMessage.setUpdateUser(operator);
            oldMessage.setSendUser(operator);
            sendMessage = oldMessage;
            logger.infoDB(createLog(operator, oldMessage.getId(), FunctionType.COMMON_MESSAGE_MANAGE, OperatorAction.MODIFY, LogType.INFO, oldMessage.toString()));
        } else {
            message.setCompany(Long.valueOf(ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.CURR_COMPANY_CLASS)));
            message.setSystemType(SystemType.EISS);
            message.setCreateUser(operator);
            message.setUpdateUser(operator);
            message.setSendTime(new Date());
            message.setSendUser(operator);
        }
        Message result = sendWechatMessage(sendMessage);
        if (result.hasErrorMessage()) {
            sendMessage.setStatus(SendStatus.SEND_FAIL);
        } else {
            sendMessage.setStatus(SendStatus.SEND_SUCCESS);
        }
        baseDao.saveOrUpdate(sendMessage);
        return result;
    }

    /**
     * 微信列表
     *
     * @param message 微信限制列表
     * @param page    分页组件
     * @return 微信列表
     */
    @Override
    public List<WechatMessage> wechatMessageList(WechatMessage message, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(WechatMessage.class);
        wrapMessageRestrictions(criteria, message);
        if (message != null && PlatformUtils.hasText(message.getMassType())) {
            criteria.add(Restrictions.like("massType", message.getMassType()));
        }
        return baseDao.listByCriteria(criteria, page, Order.desc("id"));
    }

    /**
     * 发送微信消息
     *
     * @param message 消息
     * @return 消息组件
     */
    private Message sendWechatMessage(WechatMessage message) {
        // 发送消息 
        JSONObject json;
        boolean sendText = MassMessageType.TEXT.equals(message.getMassType());
        String content = sendText ? message.getContent() : message.getMediaId();
        if (message.isToAll()) {
            json = WeChatApiManager.getInstance().sendAllMass(Boolean.TRUE, 0, content, message.getMassType());
        } else {
            json = WeChatApiManager.getInstance().sendMass(Arrays.asList(Tools.string2Array(message.getAddress())), content, message.getMassType());
        }
        if (json.has("errcode") && json.getInt("errcode") > 0) {
            logger.error("添加或更新微信信息失败：" + json.toString());
            WechatMessage oldMessage = baseDao.get(WechatMessage.class, message.getId());
            oldMessage.setStatus(SendStatus.SEND_FAIL);
            baseDao.update(oldMessage);
            return Message.createMessage("wechat.send.mass.error", json.toString());
        }
        return Message.createEmptyMessage();
    }

    //发送信息
    private <T extends BaseMessage> void sendMessage(T message) {
        List<Date> sendTimeList = message.getSendTimeList();
        if (sendTimeList != null && !sendTimeList.isEmpty()) {
            if (sendTimeList.size() == 1) {
                Date date = sendTimeList.get(0);
                if (date == null) {
                    date = new Date();
                }

                message.setSendTime(date);
                baseDao.save(message);
                SendManager.getInstance().send(message);
            } else {
                boolean isEmail = message instanceof EmailMessage;
                for (Date date : sendTimeList) {
                    if (date == null) {
                        continue;
                    }

                    BaseMessage temp = isEmail ? ((EmailMessage) message).clone() : ((SMSMessage) message).clone();
                    temp.setSendTime(date);
                    baseDao.save(temp);
                    SendManager.getInstance().send(temp);
                }
            }
        } else {
            if (message.getSendTime() == null) {
                message.setSendTime(new Date());
            }
            baseDao.save(message);
            SendManager.getInstance().send(message);
        }
    }
}
