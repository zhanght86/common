package com.sjdf.platform.associate.action;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.associate.bean.Associate;
import com.sjdf.platform.associate.helper.AssociateManager;
import com.sjdf.platform.associate.service.AssociateService;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.AES;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.LoggerManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * Create at 2012-08-07
 * 关联信息管理
 *
 * @author 王正伟
 */
public class AssociateAction extends BaseAction {
    private static final long serialVersionUID = -6446347655142917135L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AssociateAction.class);

    @Autowired
    private AssociateService associateService;
    private Associate associate;
    private List<Associate> associateList;

    /**
     * 验证并跳转
     *
     * @return result
     */
    public String index() {
        return list();
    }

    /**
     * user/common/AssociateAction!list.action
     * 关联信息列表
     *
     * @return result
     */
    public String list() {
        if (!valid()) {
            tipMessage = "连接密码错误";
            tipMessageUrl = "";
            return "-1";
        }

        associateList = associateService.list(associate, page);
        permissioonValid(associateList);
        return "list";
    }

    /**
     * user/common/AssociateAction!addOrUpdate.action
     * 添加处理
     *
     * @return result
     */
    public String addOrUpdate() {
        Message message = associateService.saveOrUpdate(associate);
        if (message.hasErrorMessage()) {
            printWrite("status:-1;result:" + getText(message));
        } else {
            saveLog(associate.getCurrentUser(), OperatorAction.ADD, LogType.INFO, associate.getCurrentUser(), message.getInfo(), null);
            printWrite("status:1;result:" + associate.getAssociateUser());
        }
        return NONE;
    }

    /**
     * user/common/AssociateAction!cancel.action
     * 取消关联
     *
     * @return result
     */
    public String cancel() {
        associate = associateService.get(Associate.class, idx);
        if (associate != null) {
            associate.setValid(ValidMark.INVALID);
            associate.setCancelTime(new Date());
            associateService.update(associate);

            saveLog(associate.getCurrentUser(), OperatorAction.DELETE, LogType.INFO, associate.getCurrentUser(), associate.wrapUpdateContent(null, true), null);
        }
        getVercodeInfo();
        return "index";
    }

    /**
     * user/common/AssociateAction!get.action
     * 关联信息查询接口
     */
    public String get() {
        String connPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String checkSum = MD5.md5(connPwd + vertime);
        if (!checkSum.equals(vercode)) {
            printErrorXml("连接密码错误");
            return NONE;
        }

        if (associate == null) {
            printErrorXml("关联信息不能为空");
            return NONE;
        }

        if (associate.getCurrentSystemType() <= 0) {
            printErrorXml("当前系统类型不能为空");
            return NONE;
        }

        if (!StringUtils.hasText(associate.getCurrentUser())) {
            printErrorXml("当前用户名不能为空");
            return NONE;
        }

        List<Associate> list = associateService.get(associate);
        permissioonValid(list);

        String xml = AssociateManager.parse(list);
        printWrite(xml);
        return NONE;
    }

    public String warn() {
        return "warn";
    }

    /**
     * 验证用户名和密码的有效性,并更新状态
     */
    private void permissioonValid(List<Associate> resultList) {
        for (Associate a : resultList) {
            if (ValidMark.VALID != a.getValid()) {
                continue;
            }

            String pwd = a.getAssociatePwd();
            a.setAssociatePwd(AES.decrypt(pwd));
            Message message = associateService.permissionValid(a);
            a.setAssociatePwd(pwd);

            if (message.hasErrorMessage() && "associate.validate.fail".equals(message.getErrorMessage())) {
                a.setValid(ValidMark.INVALID);
                a.setInvalidTime(new Date());
                associateService.update(a);
            }
            a.setRemark(getText(message));
        }
    }

    /**
     * 验证连接密码
     *
     * @return result
     */
    private boolean valid() {
        if (associate == null) {
            return false;
        }

        Date time = DateUtils.parseDateTimeStamp(vertime);
        if (time != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(time);
            c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + CommonPlatformConstant.LENGTH_30);
            if (new Date().compareTo(c.getTime()) >= 0) {
                return false;
            }
        }

        String conPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String checkSum = conPwd + associate.getCurrentUser() + vertime;
        checkSum = MD5.md5(checkSum);
        return checkSum.equals(vercode);
    }

    private void saveLog(String operator, long operatorAction, long logType, String resourceId, String operationalContent, String errorInfo) {
        LogBean log = LoggerManager.getInstance().createLog(operator, FunctionClass.COMMON_PLATFORM, FunctionType.COMMON_PLATFORM_ASSOCIATE, SubsystemType.OTHER, operatorAction, logType, resourceId, operationalContent, errorInfo, 0L);
        if (logType == LogType.INFO) {
            LOGGER.infoDB(log);
        } else {
            LOGGER.errorDB(log);
        }
    }

    public List<? extends Dictionary> getSystemTypeList() {
        List<? extends Dictionary> list = ConfigManager.getInstance().getDictionary(SystemType.class);
        for (ListIterator<? extends Dictionary> listIterator = list.listIterator(); listIterator.hasNext(); ) {
            Dictionary d = listIterator.next();
            if (d.getAttr() == SystemType.DOMAIN || d.getAttr() == SystemType.FINANCE || d.getAttr() == SystemType.OTHER) {
                listIterator.remove();
            }
        }
        return list;
    }

    /**
     * 生成vercode
     *
     * @return result
     */
    public String getVercodeInfo() {
        vertime = DateUtils.formatDateTimestamp(new Date());
        String conPwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String checkSum = new StringBuilder(CommonPlatformConstant.LENGTH_256).append(conPwd).append(associate.getCurrentUser()).append(vertime).toString();
        vercode = MD5.md5(checkSum);
        return vercode;
    }

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public List<Associate> getAssociateList() {
        return associateList;
    }
}
