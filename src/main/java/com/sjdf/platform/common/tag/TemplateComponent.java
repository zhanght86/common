package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.conf.ProfileProvider;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dataTemplate.bean.Template;
import com.sjdf.platform.dataTemplate.helper.TemplateManager;
import com.sjdf.platform.dictionary.bean.CompanyClass;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.xss.EscapeUtils;
import org.apache.struts2.components.ContextBean;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;

/**
 * User: ketqi
 * Date: 2013-07-25 17:02
 * <p/>
 * 数据模板标签
 */
public class TemplateComponent extends ContextBean {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(TemplateComponent.class);
    private static final String CLRF = "\n";
    private static final String TEMPLATE_CSS = "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://" + ProfileProvider.getCommonPlatformAccessDomain() + "/css/template.css\"/>";
    private static final String TEMPLATE_SCRIPT = "<script type=\"text/javascript\" src=\"http://" + ProfileProvider.getCommonPlatformAccessDomain() + "/js/template.js\"></script>";
    private static final String FILTER_CSS = "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://" + ProfileProvider.getCommonPlatformAccessDomain() + "/css/sfilter.css\"/>";
    private static final String FILTER_SCRIPT = "<script type=\"text/javascript\" src=\"http://" + ProfileProvider.getCommonPlatformAccessDomain() + "/js/sfilter.js\"></script>";

    //用户编号
    private String userId;
    //系统类型
    private long systemType;
    // 数据模板大分类
    private long templateClass;
    //模板分类
    private long templateType;
    //宽度
    private int width;

    public TemplateComponent(ValueStack stack) {
        super(stack);
    }

    @Override
    public boolean start(Writer writer) {
        boolean result = super.start(writer);
        try {
            writer.write(toHtml());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    public String toHtml() {
        String vertime = DateUtils.formatDateTimestamp(new Date());
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vercode = MD5.md5(connpwd + userId + vertime);

        Template region = new Template();
        region.setSystemType(systemType);
        region.setType(templateType);
        region.setMemberNumber(userId);
        region.setClazz(templateClass);
        List<Template> templateList = TemplateManager.getInstance().getAll(region);


        StringBuilder html = new StringBuilder(CommonPlatformConstant.LENGTH_3096);
        html.append(FILTER_CSS).append(CLRF);
        html.append(FILTER_SCRIPT).append(CLRF);
        html.append(TEMPLATE_CSS).append(CLRF);
        html.append(TEMPLATE_SCRIPT).append(CLRF);
        html.append(js());

        html.append("<div class=\"template-yuan-box\" style=\"width:").append(width).append("px\">");
        if (CompanyClass.getCurrentCompanyClass() == CompanyClass.SJDF) {
            html.append("信息库支持通过用户关联的方式来共享信息或附件。被关联的用户不修改密码，则该关联一直有效,");
            html.append("<a href=\"http://").append(ProfileProvider.getCommonPlatformAccessDomain()).append("/user/common/AssociateAction!index.action?associate.currentSystemType=").append(systemType).append("&associate.currentUser=").append(userId).append("&vertime=").append(vertime).append("&vercode=").append(vercode).append("\" target=\"_blank\" id=\"associate\">【关联信息】</a><br/>");
        } else {
            html.append("信息模板可以让您尽可能方便地重复使用在我司已录入过的信息。<br/>");
        }
        html.append("<div class=\"template_tip_left\">选择信息模版</div>");
        html.append("<select id=\"loadTemplate\" style=\"width:450px;height:20px;float:left\">");
        html.append("<option value=\"0\">==============选择信息模板=============</option>");
        for (Template template : templateList) {
            html.append("<option value=\"").append(template.getId()).append("\">").append(EscapeUtils.escapeHtml(template.getName())).append("</option>");
        }
        html.append("</select>");
        html.append("</div>");
        return html.toString();
    }

    private String js() {
        StringBuilder js = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        js.append("<script type=\"text/javascript\">");
        js.append("$(function () {");
        js.append("$('#template_refresh').click(function () {");
        js.append("var loadTemplate = $('#loadTemplate').empty();");
        js.append("var options = \"<option value='0'>==============选择信息模板=============</option>\";");
        js.append("var template = {");
        js.append("'template.systemType': '").append(systemType).append("',");
        js.append("'template.clazz': '").append(templateClass).append("',");
        js.append("'template.type': '").append(templateType).append("',");
        js.append("'template.memberNumber': '").append(userId).append("'");
        js.append("};");
        js.append("$.post('other/common/platform/DataTemplateAction!getFixedVo.action', template, function (re) {");
        js.append("if (re['bool']) {");
        js.append("var result = re['result'];");
        js.append("for (var i = 0; i < result.length; i++) {");
        js.append("options += \"<option value='\" + result[i]['id'] + \"'>\" + result[i]['name'] + \"</option>\";");
        js.append("}");
        js.append("} else {");
        js.append("alert(re['message']);");
        js.append("}");
        js.append("loadTemplate.append(options);");
        js.append("}, 'json');");
        js.append("});");
        js.append("});");
        js.append("</script>");
        return js.toString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getSystemType() {
        return systemType;
    }

    public void setSystemType(long systemType) {
        this.systemType = systemType;
    }

    public long getTemplateClass() {
        return templateClass;
    }

    public void setTemplateClass(long templateClass) {
        this.templateClass = templateClass;
    }

    public long getTemplateType() {
        return templateType;
    }

    public void setTemplateType(long templateType) {
        this.templateType = templateType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TemplateComponent{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", systemType=").append(systemType);
        sb.append(", templateClass=").append(templateClass);
        sb.append(", templateType=").append(templateType);
        sb.append('}');
        return sb.toString();
    }
}
