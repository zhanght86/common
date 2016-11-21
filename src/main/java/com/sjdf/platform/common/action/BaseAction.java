package com.sjdf.platform.common.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.cache.WeChatQrcodeSessionCache;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.utils.ResolveDomain;
import com.sjdf.platform.dataTemplate.bean.Template;
import com.sjdf.platform.dataTemplate.helper.TemplateManager;
import com.sjdf.platform.dictionary.bean.CompanyClass;
import com.sjdf.platform.dictionary.bean.ConstGlobal;
import com.sjdf.platform.dictionary.bean.InterfaceRetCode;
import com.sjdf.platform.dictionary.bean.eiss.EissVariable;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.location.helper.LocationManager;
import com.sjdf.platform.location.vo.LocationVo;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.List;

/**
 * 所有action的基础
 * Create at 2012-04-09
 *
 * @author 王正伟
 */
public class BaseAction extends ActionSupport {
    private static final long serialVersionUID = 4093500745974580856L;
    protected final SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(getClass());
    protected ActionContext actionContext = ActionContext.getContext();
    protected HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
    protected HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
    protected HttpSession session = request.getSession(true);

    /** 主键 */
    protected long idx;

    /** 分页工具 */
    protected Page page = new Page();

    /** 经md5加密之后的数据 */
    protected String vercode;

    /** 时间戳 */
    protected String vertime;
    /** 提示信息 */
    protected String tipMessage;
    /** 提示信息 */
    protected String errorMsg;
    /** 提示信息后转移地址 */
    protected String tipMessageUrl;

    public String error() {
        String prefix = getBasePath();
        if (PlatformUtils.hasText(tipMessageUrl) && !tipMessageUrl.startsWith(prefix)) {
            tipMessageUrl = prefix + tipMessageUrl;
        }
        return "error";
    }

    /** 获取项目访问根路径 */
    public String getBasePath() {
        StringBuilder basePath = new StringBuilder(CommonPlatformConstant.LENGTH_128);
        basePath.append(ServletActionContext.getRequest().getScheme());
        basePath.append("://");
        basePath.append(ServletActionContext.getRequest().getServerName());
        basePath.append(":");
        basePath.append(ServletActionContext.getRequest().getServerPort());
        basePath.append(ServletActionContext.getRequest().getContextPath());
        basePath.append("/");
        return basePath.toString();
    }

    /** 获取访问顶级域名 */
    public String getTopDomain() {
        return ResolveDomain.getDomain(ServletActionContext.getRequest().getServerName());
    }

    /** 
     * @category 微信二维码链接地址
     */
    public String getWechatQrcodeUrl() {
        String wechatQrcodeUrl = WeChatQrcodeSessionCache.getInstance().getQrcodeUrl(session.getId());
        if (session.getAttribute("userName") != null 
                && PlatformUtils.hasText(session.getAttribute("userName").toString())) {
            wechatQrcodeUrl = "img/weixin.jpg";
        }
        return wechatQrcodeUrl;
    }

    /** 获取项目访问静态根路径 */
    public String getStaticPath() {
        return ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.EISS_HOME_STATIC_PATH);
    }

    /** 获取主站根URL */
    public String getHomeBaseUrl() {
        return ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.HOME_BASE_URL);
    }

    /** 获取管理中心根URL */
    public String getUserBaseUrl() {
        return ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.USER_BASE_URL);
    }

    /** 获取管理中心PHP根URL */
    public String getUserPhpBaseUrl() {
        return ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.USER_PHP_BASE_URL);
    }

    /** 获取业务后台根URL */
    public String getAdminBaseUrl() {
        return ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.ADMIN_BASE_URL);
    }

    /** 获取控制面板根URL */
    public String getConpanelBaseUrl() {
        return ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.CONPANEL_BASE_URL);
    }

    /** 获取帮助中心根URL */
    public String getHelpBaseUrl() {
        return ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.HELP_BASE_URL);
    }

    /** 获取备案系统根URL */
    public String getBeianBaseUrl() {
        return ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.BEIAN_BASE_URL);
    }

    /** 获取业务公共平台根URL */
    public String getCommonBaseUrl() {
        return ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.COMMON_BASE_URL);
    }

    /** 获取会员系统URL */
    public String getMemberSystemUrl() {
        return ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.MEMBER_SYSTEM_URL);
    }

    /** 获取财务系统URL */
    public String getFinanceSystemUrl() {
        return ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.FINANCE_BASE_URL);
    }

    /** 获取模拟登陆控制面板根URL */
    public String getClientBaseUrl() {
        return ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.CLIENT_BASE_URL);
    }

    /**
     * @param image 图片浏览
     */
    protected void printImage(File image) {
        if (image == null || !image.exists()) {
            return;
        }

        response.setContentType("image/jpeg");// 设定输出的类型
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(image)); BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            byte[] data = new byte[CommonPlatformConstant.LENGTH_4096];// 缓冲字节数
            int size;
            while ((size = bis.read(data)) != -1) {
                bos.write(data, 0, size);
            }
            bos.flush();// 清空输出缓冲流
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("图片浏览失败", e);
        }
    }

    /** 向请求方传输数据 */
    protected void printWrite(String data) {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.print(data);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("数据传输失败", e);
        }
    }

    /** 向请求方传输数据 */
    protected void printWrite(boolean isSuccess, String data) {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        builder.append("bool:").append(isSuccess).append(";");
        builder.append("message:").append(data);
        printWrite(builder.toString());
    }

    /** 向请求方传输xml数据 */
    protected void printErrorXml(String error) {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<error><![CDATA[").append(error).append("]]></error>");
        printWrite(xml.toString());
    }

    /**
     * @param file 下载文件文件
     */
    protected void download(File file) {
        if (file == null || !file.exists()) {
            return;
        }

        // 设置response的编码方式
        response.setContentType("application/x-msdownload");

        // 写明要下载的文件的大小
        response.setContentLength((int) file.length());

        // 缓冲字节数
        byte[] data = new byte[CommonPlatformConstant.LENGTH_4096];
        int size;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            // 设置附加文件名
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes(), "iso-8859-1"));

            while ((size = bis.read(data)) != -1) {
                bos.write(data, 0, size);
            }
            // 清空输出缓冲流
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件件下载失败", e);
        }
    }

    /** 获取省份 */
    public String provinceList() {
        List<LocationVo> list = LocationManager.getInstance().getProvinceList();
        AjaxSupport.sendSuccessTextOnly(null, list, "code", "cnName", "enName", "postal", "areaCode", "abbreviation");
        return NONE;
    }

    /** 获取指定省份代码下的城市 */
    public String cityList() {
        List<LocationVo> list = LocationManager.getInstance().getCityList(vercode);
        AjaxSupport.sendSuccessTextOnly(null, list, "code", "cnName", "enName", "postal", "areaCode", "abbreviation");
        return NONE;
    }

    /** 获取指定城市代码下的区县 */
    public String countyList() {
        List<LocationVo> list = LocationManager.getInstance().getCountyList(vercode);
        AjaxSupport.sendSuccessTextOnly(null, list, "code", "cnName", "enName", "postal", "areaCode", "abbreviation");
        return NONE;
    }

    /** 获取指定代码的具体信息 */
    public String location() {
        LocationVo vo = LocationManager.getInstance().getLocation(vercode);
        AjaxSupport.sendSuccessTextOnly(null, vo, "code", "cnName", "enName", "postal", "areaCode", "abbreviation");
        return NONE;
    }

    /**
     * @return result
     * 加载模板并判断模版是否为个人的，判断逻辑是更具模版名字中"_"的个数
     */
    public String loadTemplate() {
        Template template = new Template();
        // 更具所选的模版ID来获取模版数据
        template.setId(idx);
        List<Template> templateList = TemplateManager.getInstance().get(template);
        if (!templateList.isEmpty()) {
            String templateJson = templateList.get(0).toJson();
            String templateName = templateList.get(0).getSystemName();
            if (PlatformUtils.hasText(templateName)) {
                int count = 0;
                // 遍历模版名字，找出＂＿＂的个数来确认是否为个人模版
                for (int i = 0, size = templateName.length() - 1; i <= size; i++) {
                    if ("_".equals(templateName.substring(i, i + 1))) {
                        count++;
                    }
                }
                // 更具模版名字中带“_”的个数来判断是企业还是个人模版；如果“＿”个数为２则是个人模版，３则为企业模版
                if (count == CommonPlatformConstant.LENGTH_2) {
                    templateJson = templateJson.substring(0, templateJson.length() - 1);
                    templateJson = templateJson + ",\"isPerson\":\"true\"}";
                } else if (count == CommonPlatformConstant.LENGTH_3) {
                    templateJson = templateJson.substring(0, templateJson.length() - 1);
                    templateJson = templateJson + ",\"isPerson\":\"false\"}";
                }
            }
            printWrite(templateJson);
        } else {
            printWrite("{}");
        }
        return NONE;
    }

    /** 通过一个消息对象取消息对象中的消息信息 */
    public String getText(Message message) {
        return getText(message.getErrorMessage(), message.getParameterList());
    }

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getVercode() {
        return vercode;
    }

    protected String createXML(long returnCode, String tipMessage) {
        return createXML(returnCode, null, tipMessage);
    }

    protected String createXML(long returnCode, String info, String tipMessage) {
        ConfigManager cm = ConfigManager.getInstance();
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        xml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
        xml.append("<interface>");
        xml.append("<return_code>");
        xml.append("<![CDATA[").append(cm.getValue(InterfaceRetCode.class, returnCode)).append("]]>");
        xml.append("</return_code>");
        xml.append("<return_msg>");
        if (returnCode == InterfaceRetCode.SUCCESS) {
            if (PlatformUtils.hasText(tipMessage)) {
                xml.append("<![CDATA[").append(tipMessage).append("]]>");
            } else {
                xml.append("<![CDATA[").append(cm.getName(InterfaceRetCode.class, returnCode)).append("]]>");
            }
        } else {
            if (PlatformUtils.hasText(tipMessage)) {
                xml.append("<![CDATA[").append(tipMessage).append("]]>");
            } else {
                xml.append("<![CDATA[").append(cm.getName(InterfaceRetCode.class, returnCode)).append("]]>");
            }
        }
        xml.append("</return_msg>");
        if (returnCode == InterfaceRetCode.SUCCESS && PlatformUtils.hasText(info)) {
            xml.append(info);
        }
        xml.append("</interface>");
        return xml.toString();
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    public String getVertime() {
        return vertime;
    }

    public void setVertime(String vertime) {
        this.vertime = vertime;
    }

    public String getTipMessage() {
        return tipMessage;
    }

    public void setTipMessage(String tipMessage) {
        this.tipMessage = tipMessage;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTipMessageUrl() {
        return tipMessageUrl;
    }

    public void setTipMessageUrl(String tipMessageUrl) {
        this.tipMessageUrl = tipMessageUrl;
    }

    /** 获取CookDomain */
    public String getCookDomain() {
        return ConfigManager.getInstance().getValue(EissVariable.class, EissVariable.COOKIE_DOMAIN);
    }

    /** 得到当前的系统类型 */
    public long getCurrentCompanyClass() {
        return CompanyClass.getCurrentCompanyClass();
    }
}
