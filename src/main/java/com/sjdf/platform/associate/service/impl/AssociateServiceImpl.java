package com.sjdf.platform.associate.service.impl;

import com.sjdf.platform.associate.bean.Associate;
import com.sjdf.platform.associate.service.AssociateService;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.*;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.bean.ValidMark;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create at 2012-08-07
 * 关联信息service
 *
 * @author 王正伟
 */
@Service
public class AssociateServiceImpl extends BaseServiceImpl implements AssociateService {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AssociateServiceImpl.class);

    /**
     * 分页查询关联信息
     *
     * @param associate 关联信息
     * @param page      分页组件
     * @return 关联信息列表
     */
    public List<Associate> list(Associate associate, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Associate.class);
        if (associate != null) {
            if (associate.getCurrentSystemType() != 0) {
                criteria.add(Restrictions.eq("currentSystemType", associate.getCurrentSystemType()));
            }
            if (StringUtils.hasText(associate.getCurrentUser())) {
                criteria.add(Restrictions.eq("currentUser", associate.getCurrentUser()));
            }
        }
        return baseDao.listByCriteria(criteria, page, Order.desc("valid"), Order.desc("invalidTime"), Order.desc("id"));
    }

    /**
     * 保存关联信息
     *
     * @param associate 关联信息
     * @return 消息组件
     */
    public Message saveOrUpdate(Associate associate) {
        Message message = valid(associate);
        if (message.hasErrorMessage()) {
            return message;
        }

        message = permissionValid(associate);
        if (message.hasErrorMessage()) {
            return message;
        }

        associate.setIpAddress(Tools.getIpAddress());
        associate.setValid(ValidMark.VALID);
        // 将密码加密
        associate.setAssociatePwd(AES.encrypt(associate.getAssociatePwd()));

        // 保存或更新
        String info;
        Associate oldAssociate = get(associate.getCurrentSystemType(), associate.getCurrentUser(), associate.getAssociateSystemType(), associate.getAssociateUser());
        if (oldAssociate != null) {
            info = oldAssociate.wrapUpdateContent(associate, true);
            baseDao.evict(oldAssociate);
            associate.setId(oldAssociate.getId());
            baseDao.marge(associate);
        } else {
            info = associate.wrapUpdateContent(null, false);
            baseDao.save(associate);
        }
        return Message.createMessage().setReturnData(associate).setInfo(info);
    }

    /**
     * 获取关联信息
     *
     * @param associate 关联信息
     * @return 关联信息列表
     */
    public List<Associate> get(Associate associate) {
        return baseDao.find("from Associate where currentSystemType = ? and currentUser = ? and valid = ?", associate.getCurrentSystemType(), associate.getCurrentUser(), ValidMark.VALID);
    }

    /**
     * 验证用户名和密码的有效性
     *
     * @param associate 关联信息
     * @return 消息组件
     */
    public Message permissionValid(Associate associate) {
        // 时间戳
        String timeStamp = DateUtils.formatDateTimestamp(new Date());
        // 连接密码
        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        // 获取MD5加密字串
        String checkSum = MD5.md5(connPwd + timeStamp);

        String url = null;
        Map<String, String> postData = new HashMap<>();
        postData.put("timeStamp", timeStamp);
        postData.put("timestamp", timeStamp);
        postData.put("checkSum", checkSum);
        postData.put("useName", associate.getAssociateUser());

        HttpSocket httpSocket;
        if (associate.getAssociateSystemType() == SystemType.EISS) {
            url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.EISS_LOGIN_VALID_URL);
            postData.put("password", MD5.md5(associate.getAssociatePwd()));
        } else if (associate.getAssociateSystemType() == SystemType.RECORD) {
            url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.RECORD_LOGIN_VALID_URL);
            postData.put("password", associate.getAssociatePwd());
        }

        httpSocket = new HttpSocket(url, postData);
        try {
            httpSocket.doPost();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            return Message.createMessage("common.message", e.getMessage());
        }

        // Status:-1;Reason:连接密码错误
        // Status:0
        // Status:1;yhId:1
        String result = httpSocket.getResponseData();
        if (StringUtils.hasText(result)) {
            int i;
            if ((i = result.indexOf("Status:-1;Reason:")) != -1) {
                return Message.createMessage("common.message", result.substring(i));
            } else if ("Status:0".equals(result)) {
                return Message.createMessage("associate.validate.fail");
            } else if (result.contains("Status:1")) {
                return Message.createEmptyMessage();
            } else {
                return Message.createMessage("common.message.fail");
            }
        }
        return Message.createEmptyMessage();
    }

    /**
     * 基本信息验证
     *
     * @param associate 关联信息
     * @return 消息组件
     */
    private Message valid(Associate associate) {
        if (associate == null) {
            return Message.createMessage("associate.null");
        }
        if (associate.getCurrentSystemType() <= 0) {
            return Message.createMessage("associate.currentSystemType.null");
        }
        if (!StringUtils.hasText(associate.getCurrentUser())) {
            return Message.createMessage("associate.currentUser.null");
        }
        if (associate.getAssociateSystemType() <= 0) {
            return Message.createMessage("associate.associateSystemType.null");
        }
        if (!StringUtils.hasText(associate.getAssociateUser())) {
            return Message.createMessage("associate.associateUser.null");
        }
        if (!StringUtils.hasText(associate.getAssociatePwd())) {
            return Message.createMessage("associate.associatePwd.null");
        }
        return Message.createEmptyMessage();
    }

    /**
     * 验证关联信息的唯一性
     *
     * @param currentSystemType   当前系统类型
     * @param currentUser         当前用户名或者会员编号
     * @param associateSystemType 关联的系统类型
     * @param associateUser       关联的用户
     * @return 关联信息
     */
    private Associate get(long currentSystemType, String currentUser, long associateSystemType, String associateUser) {
        return baseDao.findOne("from Associate where currentSystemType = ? and currentUser = ? and associateSystemType = ? and associateUser = ? and valid = ?", currentSystemType, currentUser, associateSystemType, associateUser, ValidMark.VALID);
    }
}
