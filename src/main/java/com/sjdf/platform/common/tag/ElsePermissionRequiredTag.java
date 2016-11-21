package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ContextBeanTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户指定权限的tag的else分支
 * User: ketqi
 * Date: 2016-05-09 17:20
 */
public class ElsePermissionRequiredTag extends ContextBeanTag {
    private static final long serialVersionUID = 3449917011750304453L;

    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new ElsePermissionRequired(stack);
    }
}
