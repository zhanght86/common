package com.sjdf.platform.datacenter.action;

/**
 * User: ketqi
 * Date: 2013-03-16 16:29
 * 数据中心管理
 */
public class DataCenterAction {

    /**
     * <pre>
     * URL:user/common/DataCenterAction!uploadPage.action
     * </pre>
     * 资料上传页面
     */
    public String uploadPage() {

        return "upload";
    }

    /**
     * <pre>
     * URL:user/common/DataCenterAction!upload.action
     * </pre>
     * 资料上传
     */
    public String upload() {
        return "upload";
    }
}
