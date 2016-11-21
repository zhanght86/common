package com.sjdf.platform.log.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.bean.LogBackupBean;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.service.LogService;
import com.sjdf.platform.log.vo.LogVo;

/**
 * 日志管理Service
 * Create at 2012-07-06
 *
 * @author 王正伟
 */
@Service("commonLogService")
public class LogServiceImpl extends BaseServiceImpl implements LogService {
    /**
     * @param log  检索条件
     * @param page 分页工具
     * @return 检索的日志
     * 分页检索日志
     */
    @Override
    public List<LogBean> list(LogVo log, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(LogBean.class);
        wrap(log, criteria);
        return baseDao.listByCriteria(criteria, page, Order.desc("id"));
    }

    /**
     * @param log  检索条件
     * @param page 分页工具
     * @return 检索的备份日志
     */
    @Override
    public List<LogBackupBean> listHistory(LogVo log, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(LogBackupBean.class);
        wrap(log, criteria);
        return baseDao.listByCriteria(criteria, page, Order.desc("id"));
    }

    @Override
    public Message updateResourceId(LogBean logger) {
        if (logger != null && PlatformUtils.hasText(logger.getResourceId())) {
            LogBean oldLog = this.get(LogBean.class, logger.getId());
            if (oldLog != null) {
                oldLog.setResourceId(logger.getResourceId());
                update(oldLog);
            }
        } else {
            return Message.createMessage("log.or.resourceId.inexistence");
        }

        return Message.createEmptyMessage();
    }

    /**
     * 封装查询条件
     *
     * @param log      限制条件
     * @param criteria 约束
     */
    private void wrap(LogVo log, DetachedCriteria criteria) {
        if (log == null) {
            return;
        }
        // 系统类型
        if (log.getSystemType() > 0) {
            criteria.add(Restrictions.eq("logUser.systemType", log.getSystemType()));
        }

        // 操作人(登录账号)
        if (PlatformUtils.hasText(log.getLoginUser())) {
            criteria.add(Restrictions.like("logUser.loginUser", log.getLoginUser(), MatchMode.ANYWHERE));
        }

        // 操作人中文姓名（单位名称）
        if (PlatformUtils.hasText(log.getCompany())) {
            criteria.add(Restrictions.like("logUser.company", log.getCompany(), MatchMode.ANYWHERE));
        }

        // 操作人ip地址
        if (PlatformUtils.hasText(log.getIpAddress())) {
            criteria.add(Restrictions.eq("logUser.ipAddress", log.getIpAddress()));
        }

        // 子系统类别
        if (log.getSubsystemType() > 0) {
            criteria.add(Restrictions.eq("subsystemType", log.getSubsystemType()));
        }

        // 操作目标所属分类（功能大类）
        if (log.getFunctionClass() > 0) {
            criteria.add(Restrictions.eq("functionClass", log.getFunctionClass()));
        }

        // 操作类型
        if (log.getOperatorAction() > 0) {
            criteria.add(Restrictions.eq("operatorAction", log.getOperatorAction()));
        }

        // 日志类型
        if (log.getLogType() > 0) {
            criteria.add(Restrictions.eq("logType", log.getLogType()));
        }

        // 功能名称（功能小类）
        if (log.getFunctionType() > 0) {
            criteria.add(Restrictions.eq("functionType", log.getFunctionType()));
        } else if (log.getFunctionTypeList() != null && !log.getFunctionTypeList().isEmpty()) {
            criteria.add(Restrictions.in("functionType", log.getFunctionTypeList()));
        }

        // 操作目标即资源
        if (PlatformUtils.hasText(log.getResourceId())) {
            criteria.add(Restrictions.eq("resourceId", log.getResourceId()));
        }
        if (log.getResourceIdList() != null && !log.getResourceIdList().isEmpty()) {
            criteria.add(Restrictions.in("resourceId", log.getResourceIdList()));
        }

        // 操作内容
        if (PlatformUtils.hasText(log.getOperationalContent())) {
            criteria.add(Restrictions.like("operationalContent", log.getOperationalContent(), MatchMode.ANYWHERE));
        }

        // 操作后的结果信息
        if (PlatformUtils.hasText(log.getErrorInfo())) {
            criteria.add(Restrictions.like("errorInfo", log.getErrorInfo(), MatchMode.ANYWHERE));
        }

        // 开始时间
        if (log.getBeginTime() != null) {
            criteria.add(Restrictions.ge("createTime", log.getBeginTime()));
        }

        // 结束时间
        if (log.getEndTime() != null) {
            criteria.add(Restrictions.le("createTime", log.getEndTime()));
        }
    }
}
