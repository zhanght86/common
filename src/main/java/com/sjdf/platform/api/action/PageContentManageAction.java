package com.sjdf.platform.api.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.common.verify.RegexVerify;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.pageContent.service.VersionManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * 页面内容管理访问接口
 *
 * @author sjdf
 */
public class PageContentManageAction extends BaseAction {
    private static final long serialVersionUID = 4423475797399102242L;
    /** 系列类型 */
    private String systemType;
    /** 页面内容版本管理服务 */
    @Autowired
    private VersionManageService versionService;

    /** 外部接口，初始化加载页面内容 */
    public String init() {
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        // 参数系统类型校验
        if (!StringUtils.hasText(systemType) || !RegexVerify.isNumeric(systemType)) {
            printErrorXml("系统类型错误，无效访问，请配置系统类型");
            return NONE;
        }

        String md5Str = MD5.md5(systemType + connpwd + vertime);
        if (!md5Str.equals(vercode)) {
            printErrorXml("校验失败，无效访问");
            return NONE;
        }

        Long sysType = Long.parseLong(systemType);
        String xml = versionService.findXmlBySystem(sysType);
        printWrite(xml);

        return NONE;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

}
