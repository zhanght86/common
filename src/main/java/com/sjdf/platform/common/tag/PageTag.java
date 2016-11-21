package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ContextBeanTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create at 2012-10-18
 * page 标签
 *
 * @author ketqi
 */
public class PageTag extends ContextBeanTag {
    private static final long serialVersionUID = -4012032563122605747L;
    protected String value;

    @Override
    public Component getBean(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        return new PageComponent(stack, request);
    }

    @Override
    protected void populateParams() {
        super.populateParams();
        PageComponent tag = (PageComponent) getComponent();
        tag.setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
