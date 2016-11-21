package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ContextBeanTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: ketqi
 * Date: 2013-07-25 17:01
 * 数据模板标签
 */
public class TemplateTag extends ContextBeanTag {
    private static final long serialVersionUID = -662046795276558789L;
    private String userId;//用户编号
    private long systemType;//系统类型
    private long templateClass;// 数据模板大分类
    private long templateType;//模板分类
    private int width;//宽度

    @Override
    public Component getBean(ValueStack valueStack, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return new TemplateComponent(valueStack);
    }

    @Override
    protected void populateParams() {
        super.populateParams();
        TemplateComponent tag = (TemplateComponent) getComponent();
        tag.setSystemType(systemType);
        tag.setUserId(userId);
        tag.setTemplateClass(templateClass);
        tag.setTemplateType(templateType);
        tag.setWidth(width);
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
}
