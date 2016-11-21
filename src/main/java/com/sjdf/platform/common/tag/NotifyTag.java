package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ContextBeanTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通知标签
 * 2014-06-19
 *
 * @author 王鹏
 */
public class NotifyTag extends ContextBeanTag {

    private static final long serialVersionUID = -2697149595843797374L;

    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new NotifyComponent(stack, req);
    }

    @Override
    protected void populateParams() {
        super.populateParams();
    }

}
