package com.sjdf.platform.autotask.service;

import com.sjdf.platform.autotask.bean.AutoTaskServer;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;

import java.util.List;

/**
 * User: ketqi
 * Date: 2013-05-14 11:19
 * 自动任务service
 */
public interface AutoTaskService extends BaseService {
    /**
     * @param server 查询条件
     * @param page   分页组件
     * @return 分页获取自动任务主机信息
     */
    List<AutoTaskServer> listServer(AutoTaskServer server, Page page);

    /**
     * @param server 查询条件
     * @param page   分页组件
     * @return 分页获取自动任务主机信息, 包括该主机下所有自动任务列表
     */
    List<AutoTaskServer> listAllServer(AutoTaskServer server, Page page);
}
