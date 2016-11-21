package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractRequiredListTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create at 2013年11月12日 上午10:44:28
 * 带有过滤功能的select标签
 *
 * @author KETQI
 */
public class FilterSelectTag extends AbstractRequiredListTag {
    private static final long serialVersionUID = 5451489520523347829L;

    protected String emptyOption;
    protected String headerKey;
    protected String headerValue;
    protected String multiple;
    protected String size;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new FilterSelectComponent(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        FilterSelectComponent select = (FilterSelectComponent) component;
        select.setEmptyOption(emptyOption);
        select.setHeaderKey(headerKey);
        select.setHeaderValue(headerValue);
        select.setMultiple(multiple);
        select.setSize(size);
    }

    public void setEmptyOption(String emptyOption) {
        this.emptyOption = emptyOption;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
