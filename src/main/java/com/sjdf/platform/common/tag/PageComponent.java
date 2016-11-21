package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.conf.ProfileProvider;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.ContextBean;
import org.apache.struts2.dispatcher.RequestMap;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Create at 2013年8月8日 下午5:01:00
 * 分页标签
 *
 * @author KETQI
 */
public class PageComponent extends ContextBean {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(PageComponent.class);
    // page组件的css样式表单
    public static final String PAGE_CSS = "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://" + ProfileProvider.getCommonPlatformAccessDomain() + "/css/page.css\"/>";
    public static final String PAGE_SCRIPT = "<script type=\"text/javascript\" src=\"http://" + ProfileProvider.getCommonPlatformAccessDomain() + "/js/page.js\"></script>";
    public static final String CLRF = "\n";
    protected String value;
    private HttpServletRequest request;

    public PageComponent(ValueStack stack, HttpServletRequest request) {
        super(stack);
        this.request = request;
    }

    @Override
    public boolean start(Writer writer) {
        boolean result = super.start(writer);
        if (value == null || "".equals(value)) {
            value = "page";
        }

        PageVo pageVo = new PageVo(value);
        Page page = (Page) findValue(value);
        try {
            writer.write(toHTML(page, pageVo));
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }

        return result;
    }

    protected String toHTML(Page page, PageVo pageVo) {
        RequestMap reqMap = (RequestMap) ActionContext.getContext().get("request"); // 获取请求Map
        String acPath = (String) reqMap.get("javax.servlet.forward.servlet_path"); // 获取跳转的action路径

        // 查询字符串;屏蔽page中的属性
        StringBuilder queryString = new StringBuilder(CommonPlatformConstant.LENGTH_1024);

        // 封装请求参数
        Map<String, String[]> paramMap = request.getParameterMap();

        try {
            for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
                if (!(entry.getKey().equals(pageVo.currentPage()) || entry.getKey().equals(pageVo.pageSize()) || entry.getKey().equals(pageVo.orderBy()) || entry.getKey().equals(pageVo.orderType()))) {
                    if (entry.getValue() != null && entry.getValue().length > 1) {
                        for (String val : entry.getValue()) {
                            queryString.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(val, HttpSocket.UTF8));
                        }
                    } else {
                        queryString.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue()[0], HttpSocket.UTF8));
                    }
                }
            }
        } catch (UnsupportedEncodingException ignore) {
            ignore.printStackTrace();
            LOGGER.error(ignore.getMessage(), ignore);
        }

        // 表单action路径
        StringBuilder formAction = new StringBuilder(queryString.length() + CommonPlatformConstant.LENGTH_50);
        formAction.append(request.getRequestURL().toString().replace(request.getServletPath(), ""));
        formAction.append(acPath);

        StringBuilder html = new StringBuilder(CommonPlatformConstant.LENGTH_15 * CommonPlatformConstant.LENGTH_100);
        html.append(PAGE_CSS).append(CLRF);// css样式
        html.append(form(queryString.toString(), formAction, page, pageVo)).append(CLRF);// form表单
        html.append(PAGE_SCRIPT).append(CLRF);

        return html.toString();
    }

    // 构造form表单
    private String form(String queryString, StringBuilder formAction, Page page, PageVo pageVo) {
        String pageFormActionUrl = formAction.toString();
        // 请求路径
        StringBuilder requestURL = new StringBuilder(queryString.length() + CommonPlatformConstant.LENGTH_100);
        requestURL.append(formAction).append("?");
        requestURL.append(pageVo.pageSize()).append("=").append(page.getPageSize());

        try {
            requestURL.append("&").append(pageVo.orderBy()).append("=").append(URLEncoder.encode(page.orderByInfo(), HttpSocket.UTF8));
            requestURL.append("&").append(pageVo.orderType()).append("=").append(URLEncoder.encode(page.orderTypeInfo(), HttpSocket.UTF8));
        } catch (UnsupportedEncodingException ignore) {
            ignore.printStackTrace();
            LOGGER.error(ignore.getMessage(), ignore);
        }


        if (queryString.length() > 0) {
            formAction.append("?").append(queryString.substring(1, queryString.length()));
            requestURL.append(queryString);
        }

        String urlLink = requestURL.append("&").append(pageVo.currentPage()).append("=").toString();
        // 【首页】URL地址
        String pageFirst = urlLink + "1";
        // 【尾页】的URL地址
        String pageLast = urlLink + page.getTotalPage();
        // 【上一页】的URL地址
        String pagePrevious;
        if (page.isHasPreviousPage()) {
            pagePrevious = urlLink + (page.getCurrentPage() - 1);
        } else {
            pagePrevious = pageFirst;
        }
        // 【下一页】的URL地址
        String pageNext;
        if (page.isHasNextPage()) {
            pageNext = urlLink + (page.getCurrentPage() + 1);
        } else {
            pageNext = pageLast;
        }

        // 分页表单表单;共xx页xx条 【首页】【上一页】【下一页】【尾页】每页条 到第页 GO
        StringBuilder form = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        form.append("<form id=\"PageFormTag\" action=\"").append(PlatformUtils.xssEncodeUrl(formAction.toString())).append("\" method=\"post\" ").append("enctype=\"application/x-www-form-urlencoded\" style=\"margin:0px;padding:0px;\">");
        form.append("<div id=\"PageDivTag\" class=\"manu\">");
        form.append("共<span>").append(page.getTotalPage()).append("</span>页");
        form.append("<span>").append(page.getTotalCount()).append("</span>条&nbsp;&nbsp;");
        form.append("<a id=\"PageFirstLinkTag\" href=\"").append(PlatformUtils.xssEncodeUrl(pageFirst)).append("\">首页</a>");
        form.append("<a id=\"PagePreviousLinkTag\" href=\"").append(PlatformUtils.xssEncodeUrl(pagePrevious)).append("\">上一页</a>");
        form.append("<a id=\"PageNextLinkTag\" href=\"").append(PlatformUtils.xssEncodeUrl(pageNext)).append("\">下一页</a>");
        form.append("<a id=\"PageLastLinkTag\" href=\"").append(PlatformUtils.xssEncodeUrl(pageLast)).append("\">尾页</a>");
        form.append("每页<input id=\"PageSizeInput\" name=\"").append(pageVo.pageSize()).append("\" type=\"text\" value=\"").append(page.getPageSize()).append("\" onKeypress=\"if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;\"/>条&nbsp;&nbsp;");
        form.append("到第<input id=\"").append("PageCurrentPageTag").append("\" name=\"").append(pageVo.currentPage()).append("\" type=\"text\" value=\"").append(page.getCurrentPage()).append("\" onKeypress=\"if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;\"/>页&nbsp;&nbsp;");
        form.append("<input type=\"submit\" value=\"GO\" style=\"border: 1px solid #dedede; cursor: pointer\" />");
        form.append("</div>");
        form.append("<input type=\"hidden\" name=\"").append(value).append(".orderBy\" id=\"PageOrderByHidden\" value=\"").append(PlatformUtils.xssEncodeUrl(page.orderByInfo())).append("\"/>");
        form.append("<input type=\"hidden\" name=\"").append(value).append(".orderType\" id=\"PageOrderTypeHidden\" value=\"").append(PlatformUtils.xssEncodeUrl(page.orderTypeInfo())).append("\"/>");
        form.append("<input type=\"hidden\" ").append("id=\"PageCheckIdHidden\" value=\"").append(PlatformUtils.xssEncodeUrl(pageVo.checkId())).append("\"/>");
        form.append("<input type=\"hidden\" ").append("id=\"PageFormActionURLHidden\" value=\"").append(PlatformUtils.xssEncodeUrl(pageFormActionUrl)).append("\"/>");
        form.append("</form>");

        return form.toString();
    }

    /**
     * page传输数据是url所对应的名称
     *
     * @author ketqi
     */
    class PageVo {
        // 前缀,Page组件在action中的变量名
        private String prefix;

        public PageVo(String prefix) {
            this.prefix = prefix;
        }

        public String currentPage() {
            return prefix + ".currentPage";
        }

        public String pageSize() {
            return prefix + ".pageSize";
        }

        public String totalCount() {
            return prefix + ".totalCount";
        }

        public String checkId() {
            return prefix + ".checkId";
        }

        public String orderBy() {
            return prefix + ".orderBy";
        }

        public String orderType() {
            return prefix + ".orderType";
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /** 获取项目访问根路径 */
    public String getBasePath() {
        StringBuilder basePath = new StringBuilder(CommonPlatformConstant.LENGTH_512);
        basePath.append(ServletActionContext.getRequest().getScheme());
        basePath.append("://");
        basePath.append(ServletActionContext.getRequest().getServerName());
        basePath.append(":");
        basePath.append(ServletActionContext.getRequest().getServerPort());
        basePath.append(ServletActionContext.getRequest().getContextPath());
        basePath.append("/");
        return basePath.toString();
    }
}
