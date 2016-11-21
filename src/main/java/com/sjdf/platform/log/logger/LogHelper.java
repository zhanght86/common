package com.sjdf.platform.log.logger;

import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.bean.LogBean;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.*;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Create at 2013年8月8日 下午5:22:52
 * 日志记录辅助器
 *
 * @author KETQI
 */
public final class LogHelper {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(LogHelper.class);
    private SessionFactory sessionFactory;
    private static LogHelper instance = new LogHelper();
    private static final String STATUS = "status";

    private LogHelper() {
        sessionFactory = new Configuration().configure(LogHelper.class.getResource("/hibernate-logger.cfg.xml")).buildSessionFactory();
    }

    public static LogHelper getInstance() {
        return instance;
    }

    /** 获取日志session */
    public Session openSession() {
        return sessionFactory.openSession();
    }

    public void saveOrUpdate(LogBean log) {
        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(log);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage(), e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public LogBean getLogger(LogBean log) {
        List<LogBean> list = list(log, null);
        return (list != null && !list.isEmpty()) ? list.get(0) : null;
    }

    public LogBean getLogger(long idx) {
        Session session = openSession();
        try {
            return (LogBean) session.get(LogBean.class, idx);
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    /**
     * 统计条数
     *
     * @param log 限制条件
     * @return 大小
     */
    public long counter(LogBean log) {
        Session session = openSession();
        try {
            Criteria criteria = createCriteria(log).getExecutableCriteria(session);
            CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
            Number counter = (Number) criteriaImpl.setProjection(Projections.rowCount()).uniqueResult();
            return counter != null ? counter.longValue() : 0L;
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return 0L;
    }

    /**
     * 统计条数
     *
     * @param log           限制条件
     * @param includeStatus 状态
     * @return 条数
     */
    public long counterInclude(LogBean log, long... includeStatus) {
        return counter(log, true, includeStatus);
    }

    /**
     * 统计条数
     *
     * @param log           限制条件
     * @param excludeStatus 状态
     * @return 条数
     */
    public long counterExclude(LogBean log, long... excludeStatus) {
        return counter(log, false, excludeStatus);
    }

    /**
     * 分页查询
     *
     * @param log  限制条件
     * @param page 分页组件
     * @return 日志列表
     */
    public List<LogBean> list(LogBean log, Page page) {
        return listByCriteria(createCriteria(log), page, Order.desc("id"));
    }

    /**
     * @param log           限制条件
     * @param page          分页组件
     * @param includeStatus 状态列表
     * @return 日志列表
     */
    public List<LogBean> listInclude(LogBean log, Page page, long... includeStatus) {
        DetachedCriteria criteria = createCriteria(log);
        if (includeStatus != null && includeStatus.length > 0) {
            if (includeStatus.length == 1) {
                criteria.add(Restrictions.eq(STATUS, includeStatus[0]));
            } else {
                criteria.add(Restrictions.in(STATUS, wrapArrays(includeStatus)));
            }
        }

        return listByCriteria(criteria, page, Order.desc("id"));
    }

    /**
     * @param log           限制条件
     * @param page          分页组件
     * @param excludeStatus 状态列表
     * @return 日志列表
     */
    public List<LogBean> listExclude(LogBean log, Page page, long... excludeStatus) {
        DetachedCriteria criteria = createCriteria(log);
        if (excludeStatus != null && excludeStatus.length > 0) {
            if (excludeStatus.length == 1) {
                criteria.add(Restrictions.ne(STATUS, excludeStatus[0]));
            } else {
                criteria.add(Restrictions.not(Restrictions.in(STATUS, wrapArrays(excludeStatus))));
            }
        }

        return listByCriteria(criteria, page, Order.desc("id"));
    }

    /**
     * 删除指定的日志
     *
     * @param idx 日志id
     */
    public void delete(long idx) {
        if(idx <= 0L){
            return;
        }

        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("delete from LogBean where id = ?").setParameter(0, idx).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage(), e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private DetachedCriteria createCriteria(LogBean log) {
        DetachedCriteria criteria = DetachedCriteria.forClass(LogBean.class);
        if (log != null) {
            if (log.getFunctionClass() != 0L) {
                criteria.add(Restrictions.eq("functionClass", log.getFunctionClass()));
            }

            if (log.getFunctionType() != 0L) {
                criteria.add(Restrictions.eq("functionType", log.getFunctionType()));
            }

            if (log.getSubsystemType() != 0L) {
                criteria.add(Restrictions.eq("subsystemType", log.getSubsystemType()));
            }

            if (PlatformUtils.hasText(log.getResourceId())) {
                criteria.add(Restrictions.eq("resourceId", log.getResourceId()));
            }

            if (log.getLogType() != 0L) {
                criteria.add(Restrictions.eq("logType", log.getLogType()));
            }

            if (log.getSourceProductId() != 0L) {
                criteria.add(Restrictions.eq("sourceProductId", log.getSourceProductId()));
            }

            if (log.getSourceServerId() != 0L) {
                criteria.add(Restrictions.eq("sourceServerId", log.getSourceServerId()));
            }

            if (log.getTargetServerId() != 0L) {
                criteria.add(Restrictions.eq("targetServerId", log.getTargetServerId()));
            }

            if (log.getStatus() != 0L) {
                criteria.add(Restrictions.eq("status", log.getStatus()));
            }

            if (PlatformUtils.hasText(log.getHandler())) {
                criteria.add(Restrictions.eq("handler", log.getHandler()));
            }

            if (log.getCreateTime() != null) {
                criteria.add(Restrictions.ge("updateTime", log.getCreateTime()));
            }

            if (log.getUpdateTime() != null) {
                criteria.add(Restrictions.le("updateTime", log.getUpdateTime()));
            }
        }
        return criteria;
    }

    /**
     * 根据条件, 分页, 分页参数及排序选择对象
     * 如果分页组件参与查询时,如果当前分页中的总记录数为0或被要求强制重新加载总记录时,均会从数据库中查询记录总数
     *
     * @param detachedCriteria 条件
     * @param page             分页组件
     * @param orders           排序条件
     */
    @SuppressWarnings("unchecked")
    private <T> List<T> listByCriteria(final DetachedCriteria detachedCriteria, final Page page, final Order... orders) {
        Session session = openSession();
        try {
            if (page == null) {
                if (orders != null) {
                    for (Order order : orders) {
                        detachedCriteria.addOrder(order);
                    }
                }
                return (List<T>) detachedCriteria.getExecutableCriteria(session).list();
            }

            Criteria criteria = detachedCriteria.getExecutableCriteria(session);
            if (page.getTotalCount() <= 0) {
                CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
                Projection projection = criteriaImpl.getProjection();
                Number counter = (Number) criteriaImpl.setProjection(Projections.rowCount()).uniqueResult();
                if (counter != null) {
                    page.setTotalCount(counter.intValue());
                }
                criteriaImpl.setProjection(projection);
                if (projection == null) {
                    criteriaImpl.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
                }
            }
            if (StringUtils.hasText(page.getOrderBy())) {
                if ("desc".equals(page.getOrderType())) {
                    criteria.addOrder(Order.desc(page.getOrderBy()));
                } else {
                    criteria.addOrder(Order.asc(page.getOrderBy()));
                }
            }
            if (orders != null) {
                for (Order order : orders) {
                    criteria.addOrder(order);
                }
            }
            return criteria.setFirstResult(page.getStartIndex()).setMaxResults(page.getPageSize()).list();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return Collections.emptyList();
    }

    private long counter(LogBean log, boolean isIncluded, long... statusList) {
        DetachedCriteria detachedCriteria = createCriteria(log);
        if (statusList != null && statusList.length > 0) {

            if (statusList.length == 1) {
                if (isIncluded) {
                    detachedCriteria.add(Restrictions.eq(STATUS, statusList[0]));
                } else {
                    detachedCriteria.add(Restrictions.ne(STATUS, statusList[0]));
                }
            } else {
                if (isIncluded) {
                    detachedCriteria.add(Restrictions.in(STATUS, wrapArrays(statusList)));
                } else {
                    detachedCriteria.add(Restrictions.not(Restrictions.in(STATUS, wrapArrays(statusList))));
                }
            }
        }

        Session session = openSession();
        try {
            Criteria criteria = detachedCriteria.getExecutableCriteria(session);
            CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
            Number counter = (Number) criteriaImpl.setProjection(Projections.rowCount()).uniqueResult();
            return counter != null ? counter.longValue() : 0L;
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return 0L;
    }

    /**
     * 记录修改对象日志
     *
     * @return 日志信息 field1:old->new,field2:old->new
     */
    public static <T> String createUpdateLogInfo(T oldt, T newt) {
        if (oldt == null || newt == null) {
            return "";
        }
        StringBuilder info = new StringBuilder();
        Field[] oldFields = oldt.getClass().getDeclaredFields();
        Field[] newFields = newt.getClass().getDeclaredFields();
        try {
            for (Field oldField : oldFields) {
                oldField.setAccessible(true);
                for (Field f : newFields) {
                    f.setAccessible(true);
                    if (oldField.getName().equals(f.getName()) && oldField.get(oldt) != null && !oldField.get(oldt).equals(f.get(newt)) || f.get(newt) != null && !f.get(newt).equals(oldField.get(oldt))) {
                        info.append(f.getName()).append(":").append(oldField.get(oldt)).append("->").append(f.get(newt)).append(",");
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (info.length() > 0) {
            info.deleteCharAt(info.length() - 1);
        }
        return info.toString();
    }

    private static List<Long> wrapArrays(long... statuses) {
        List<Long> list = new ArrayList<>();
        if (statuses == null) {
            return list;
        }
        for (long status : statuses) {
            list.add(status);
        }
        return list;
    }
}
