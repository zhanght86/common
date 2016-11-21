package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: ketqi
 * Date: 2013-04-16 13:27
 * 树标签
 */
public class TreeTag extends AbstractUITag {
    private static final long serialVersionUID = 4324335999772005409L;
    /** 树结点列表 */
    private String nodeList;
    /** 是否全局展开,默认是true */
    private String expand;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new TreeComponent(stack, req, res);
    }

    @Override
    protected void populateParams() {
        super.populateParams();
        TreeComponent tree = (TreeComponent) component;
        tree.setNodeList(nodeList);
        tree.setExpand(expand);
    }

    public void setNodeList(String nodeList) {
        this.nodeList = nodeList;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }
}
