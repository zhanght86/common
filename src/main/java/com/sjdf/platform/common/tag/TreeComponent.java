package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.UIBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: ketqi
 * Date: 2013-04-16 13:23
 * 树结构
 */
public class TreeComponent extends UIBean {
    private static final String TEMPLATE = "tree";
    /** 树结点列表 */
    private String nodeList;
    /** 是否全局展开,默认是true */
    private String expand;

    public void setNodeList(String nodeList) {
        this.nodeList = nodeList;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    public TreeComponent(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    @Override
    protected void evaluateExtraParams() {
        super.evaluateExtraParams();
        if (expand != null) {
            addParameter("expand", findValue(expand, Boolean.class));
        } else {
            addParameter("expand", true);
        }
        addParameter("nodeList", findValue(nodeList));
    }
}
