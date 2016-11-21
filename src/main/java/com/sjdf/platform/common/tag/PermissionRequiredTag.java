package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ContextBeanTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: ketqi
 * Date: 2013-05-20 16:16
 * 用户指定权限的tag
 */
public class PermissionRequiredTag extends ContextBeanTag {
    private static final long serialVersionUID = -234280842211048638L;
    /** 权限代码 */
    private String code;

    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new PermissionRequired(stack);
    }

    @Override
    protected void populateParams() {
        super.populateParams();
        PermissionRequired permissionRequired = (PermissionRequired) component;
        permissionRequired.setCode(code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        //ignore
    }
}
