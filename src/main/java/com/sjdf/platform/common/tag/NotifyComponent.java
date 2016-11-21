package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.conf.ProfileProvider;
import com.sjdf.platform.common.utils.AES;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.Notify;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.apache.struts2.components.ContextBean;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通知标签component类
 * 2014-06-19
 *
 * @author 王鹏
 */
public class NotifyComponent extends ContextBean {

    public static final String CLRF = "\n";
    public static final String NOTIFY_SCRIPT = "<script type=\"text/javascript\" src=\"http://" + ProfileProvider.getCommonPlatformAccessDomain() + "/js/notify.js\"></script>";
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(NotifyComponent.class);

    private HttpServletRequest reqeust;
    private StringBuilder js = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
    private StringBuilder html = new StringBuilder(CommonPlatformConstant.LENGTH_1024);

    public NotifyComponent(ValueStack stack, HttpServletRequest req) {
        super(stack);
        reqeust = req;
    }

    public void buildHtml(Writer write) throws IOException {
        ConfigManager configManager = ConfigManager.getInstance();
        List<? extends Dictionary> notifyList = configManager.getDictionary(Notify.class);
        for (Dictionary dictionary : notifyList) {
            String func = null;
            String api = null;
            String url = null;
            String value = dictionary.getValue();
            String[] values = value.split("\\|");
            for (String v : values) {
                if (v.startsWith("FUNC:")) {
                    func = v.substring(CommonPlatformConstant.LENGTH_5, v.length());
                }
                if (v.startsWith("API:")) {
                    api = v.substring(CommonPlatformConstant.LENGTH_4, v.length());
                }
                if (v.startsWith("URL:")) {
                    url = v.substring(CommonPlatformConstant.LENGTH_4, v.length());
                }
            }

            Pattern pattern = Pattern.compile("\\$\\{session\\.([a-z0-9A-Z-_]+)\\}");
            Matcher m = pattern.matcher(api);
            while (m.find()) {
                String attr = "";
                Object sessionAttr = reqeust.getSession().getAttribute(m.group(1));
                if (sessionAttr != null) {
                    attr = URLEncoder.encode(sessionAttr.toString(), "utf-8");
                }
                api = api.replace(m.group(), attr);
                url = url.replace(m.group(), attr);
            }

            // 对参数进行AES加密以及URL编码
            String[] urlArr = url.split("\\?");
            String params = AES.encrypt(urlArr[1]);
            params = URLEncoder.encode(params, "UTF-8");
            url = urlArr[0] + "?params=" + params;

            write.write("<script type='text/javascript' src='" + api + "'></script>" + CLRF);
            html.append("<div id='div_");
            html.append(func);
            html.append("' style='display:none;line-height:20px'><a href='");
            html.append(url);
            html.append("' target='_blank'><font color='#FF0000'>");
            html.append(dictionary.getName());
            html.append("</font></a></font></div>");

            js.append("if(").append(func).append("()){");
            js.append("document.getElementById('floatChat').style.display = '';");
            js.append("document.getElementById('div_").append(func).append("').style.display = '';");
            js.append("}");
            js.append(CLRF);
        }
    }

    @Override
    public boolean start(Writer writer) {
        try {
            buildHtml(writer);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        StringBuilder divHtml = new StringBuilder(CommonPlatformConstant.LENGTH_3096);
        divHtml.append("<div style='display:none;POSITION: absolute;right: 3%; top:800px;z-index:999;' id='floatChat'>");
        divHtml.append("<div style='width:300px;border:#ff9933 1px solid; background-color:#ffffe5; padding:10px; line-height:20px; font-size:12px; height:100%; text-align:left;'>");
        divHtml.append("<font color='#009900' onClick='chatCancel()' style='cursor:pointer; float:right;'>[关闭]</font><br />");
        divHtml.append(html.toString());
        divHtml.append("</div></div>");
        try {
            writer.write(divHtml.toString());
            StringBuilder jsBuilder = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
            jsBuilder.append("<script type='text/javascript'>");
            jsBuilder.append(js.toString());
            jsBuilder.append("</script>");
            writer.write(jsBuilder.toString());
            writer.write(NOTIFY_SCRIPT);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return super.start(writer);
    }

    public HttpServletRequest getReqeust() {
        return reqeust;
    }

    public void setReqeust(HttpServletRequest reqeust) {
        this.reqeust = reqeust;
    }
}
