package com.sjdf.platform.print.action;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.annotations.Permission;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.LoggerManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.print.bean.PrintAddress;
import com.sjdf.platform.print.service.PrintAddressService;
import com.sjdf.platform.rbac.helper.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Create at 2012-03-16 /admin/common/PrintAction
 * 打印邮递详情单
 *
 * @author 王正伟
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class PrintAction extends BaseAction {
    private static final long serialVersionUID = 7301003843788737338L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(PrintAction.class);
    @Autowired
    protected PrintAddressService printAddressService;
    private PrintAddress addressVo;
    private List<PrintAddress> addressList;

    @Permission(code = "06011101", name = "打印地址列表")
    public String list() {
        addressList = printAddressService.list(addressVo, page);
        return "list";
    }

    @Permission(code = "06011102", name = "打印地址添加")
    public String add() {
        Message message = printAddressService.add(addressVo);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            saveLog(OperatorAction.ADD, LogType.INFO, addressVo.getName(), message.getInfo(), null);
            printWrite(SUCCESS);
        }
        return NONE;
    }

    @Permission(code = "06011103", name = "打印地址编辑")
    public String edit() {
        addressVo = printAddressService.get(PrintAddress.class, idx);
        return "post";
    }

    @Permission(code = "06011104", name = "打印地址更新")
    public String update() {
        Message message = printAddressService.update(addressVo);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            addressVo = (PrintAddress) message.getReturnData();
            saveLog(OperatorAction.ADD, LogType.INFO, addressVo.getName(), message.getInfo(), null);
            printWrite(SUCCESS);
        }
        return NONE;
    }

    @Permission(code = "06011105", name = "打印地址删除")
    public String del() {
        Message message = printAddressService.del(idx);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            addressVo = (PrintAddress) message.getReturnData();
            saveLog(OperatorAction.DELETE, LogType.INFO, addressVo.getName(), message.getInfo(), null);
            printWrite(SUCCESS);
        }
        return NONE;
    }

    public String indirectPrint() {
        vertime = DateUtils.formatDateTimestamp(new Date());

        // 连接密码
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        StringBuilder valid = new StringBuilder(CommonPlatformConstant.LENGTH_64).append(connpwd).append(vertime);
        vercode = MD5.md5(valid.toString());

        return "indirectPrint";
    }

    // 收件地址
    public List<PrintAddress> getAddressList() {
        if (addressList == null) {
            addressList = printAddressService.list(null, null);
        }
        return addressList;
    }

    // 快递模板分类
    public List<? extends Dictionary> getExpressTypeList() {
        return ConfigManager.getInstance().getDictionary(ExpressType.class);
    }

    private void saveLog(long operatorAction, long logType, String resourceId, String operationalContent, String errorInfo) {
        LogBean log = LoggerManager.getInstance().createLog(UserHelper.getCurrentLoginUser().getUsername(), FunctionClass.COMMON_PLATFORM, FunctionType.COMMON_PLATFORM_PRINT_ADDRESS, SubsystemType.OTHER, operatorAction, logType, resourceId, operationalContent, errorInfo, 0L);
        if (logType == LogType.INFO) {
            LOGGER.infoDB(log);
        } else {
            LOGGER.errorDB(log);
        }
    }

    public PrintAddress getAddressVo() {
        return addressVo;
    }

    public void setAddressVo(PrintAddress addressVo) {
        this.addressVo = addressVo;
    }

    /** 获取vercode和vertime封装的验证url字符串 */
    public String getVercodeAndCertime() {
        String vertime = DateUtils.formatTimestamp(new Date());
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vercode = MD5.md5(connpwd + vertime);
        return new StringBuilder(CommonPlatformConstant.LENGTH_100).append("vercode=").append(vercode).append("&vertime=").append(vertime).toString();
    }
}
