package com.sjdf.platform.autotask.service.impl;

import com.sjdf.platform.autotask.bean.AutoTaskServer;
import com.sjdf.platform.autotask.service.AutoTaskService;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.Page;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * User: ketqi
 * Date: 2013-05-14 11:19
 * 自动任务service
 */
@Service("commonAutoTaskService")
public class AutoTaskServiceImpl extends BaseServiceImpl implements AutoTaskService {
    /**
     * @param server 查询条件
     * @param page   分页组件
     * @return 分页获取自动任务主机信息
     */
    public List<AutoTaskServer> listServer(AutoTaskServer server, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(AutoTaskServer.class);
        if (server != null) {
            if (StringUtils.hasText(server.getName())) {
                criteria.add(Restrictions.like("name", server.getName(), MatchMode.ANYWHERE));
            }
            if (StringUtils.hasText(server.getRemoteAddr())) {
                criteria.add(Restrictions.eq("remoteAddr", server.getRemoteAddr()));
            }
            if (server.getRemotePort() > 0) {
                criteria.add(Restrictions.eq("remotePort", server.getRemotePort()));
            }
            if (StringUtils.hasText(server.getRealAddr())) {
                criteria.add(Restrictions.like("realAddr", server.getRealAddr(), MatchMode.ANYWHERE));
            }
            if (server.getHeartbeat() > 0) {
                criteria.add(Restrictions.gt("heartbeat", server.getHeartbeat()));
            }
        }
        return baseDao.listByCriteria(criteria, page);
    }

    /**
     * @param server 查询条件
     * @param page   分页组件
     * @return 分页获取自动任务主机信息, 包括该主机下所有自动任务列表
     */
    public List<AutoTaskServer> listAllServer(AutoTaskServer server, Page page) {
        List<AutoTaskServer> list = listServer(server, page);
        for (AutoTaskServer s : list) {
            Hibernate.initialize(s.getAutoTaskList());
        }
        return list;
    }

    /**
     * 添加服务器
     *
     * @param server 服务器信息
     */
    public Message addServer(AutoTaskServer server) {

        return Message.createEmptyMessage();
    }
}
