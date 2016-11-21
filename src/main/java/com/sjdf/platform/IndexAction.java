package com.sjdf.platform;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.annotations.Permission;

/**
 * User: ketqi
 * Date: 2013-08-06 16:58
 * 首页action
 */
public class IndexAction extends BaseAction {
    private static final long serialVersionUID = -5120036024318747663L;

    /**
     * <pre>
     * admin/common/IndexAction!index.action
     * </pre>
     * 首页
     */
    @Permission(code = "06011201", name = "首页")
    public String index() {

        return "index";
    }
}
