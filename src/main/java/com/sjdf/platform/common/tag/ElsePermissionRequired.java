package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.ContextBean;

import java.io.Writer;
import java.util.Map;

/**
 * 用户指定权限的tag的else分支
 * User: ketqi
 * Date: 2016-05-09 17:22
 */
public class ElsePermissionRequired extends ContextBean {

    public ElsePermissionRequired(ValueStack stack) {
        super(stack);
    }

    @Override
    public boolean start(Writer writer) {
        Map context = stack.getContext();
        Boolean result = (Boolean) context.get(PermissionRequired.PERMISSION_ANSWER);
        context.remove(PermissionRequired.PERMISSION_ANSWER);

        return !(result == null || result);
    }
}
