package com.sjdf.platform.common.dao.impl;

import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.dao.BaseDao;
import com.sjdf.platform.common.utils.LogReflectUtils;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.dictionary.bean.LogType;
import com.sjdf.platform.dictionary.bean.SubsystemType;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.core.Ordered;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 基础数据访问实现类<br/>
 * 一般情况下,系统中其他数据访问接口继承BaseDAO接口,而实现则直接继承此类以实现各自数据访问逻辑
 * Create at 2012-11-24
 *
 * @author 王正伟
 */
@Repository("commonBaseDao")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

    @Resource
    protected void setCommonSessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    /** 保存 */
    @Override
    public <T> void save(T t) {
        if (t instanceof BaseBean) {
            BaseBean baseBean = (BaseBean) t;
            if (baseBean.getCreateTime() == null) {
                baseBean.setCreateTime(new Date());
            }
            if (baseBean.getUpdateTime() == null) {
                baseBean.setUpdateTime(new Date());
            }

            if (baseBean instanceof com.sjdf.platform.log.bean.LogBean) {
                LogBean logBean = (LogBean) baseBean;
                SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(BaseDaoImpl.class);
                if (logBean.getSubsystemType() == 0) {
                    logBean.setSubsystemType(SubsystemType.BUSINESS_BACKGROUND);
                }
                if (logBean.getLogType() == LogType.INFO) {
                    logger.infoDB(logBean);
                } else if (logBean.getLogType() == LogType.ERROR) {
                    logger.errorDB(logBean);
                }

                return;
            }
        }
        if ("com.sjdf.eiss.bean.LogBean".equals(t.getClass().getName())) {
            LogReflectUtils.saveNewLog(t);
        }


        getHibernateTemplate().save(t);
    }

    /** 添加集合 */
    @Override
    public <T> void saveAll(Collection<T> list) {
        getHibernateTemplate().saveOrUpdateAll(list);
    }

    /** 添加更新 */
    @Override
    public <T> void saveOrUpdate(T t) {
        getHibernateTemplate().saveOrUpdate(t);
    }

    /** 添加更新集合 */
    @Override
    public <T> void saveOrUpdateAll(Collection<T> list) {
        getHibernateTemplate().saveOrUpdateAll(list);
    }

    /** 合并 */
    @Override
    public <T> void marge(T t) {
        getHibernateTemplate().merge(t);
    }

    /** 删除 */
    @Override
    public <T> void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    /** 删除列表中所有对象 */
    @Override
    public <T> void deleteAll(Collection<T> collection) {
        getHibernateTemplate().deleteAll(collection);
    }

    /** 更新 */
    @Override
    public <T> void update(T t) {
        if (t instanceof BaseBean) {
            BaseBean baseBean = (BaseBean) t;
            if (baseBean.getUpdateTime() == null) {
                baseBean.setUpdateTime(new Date());
            }
        }
        getHibernateTemplate().update(t);
    }

    /** 更新 */
    @Override
    public <T> void updateAndFlush(T t) {
        update(t);
        flush();
    }

    /** 更新集合 */
    @Override
    public <T> void updateAll(Collection<T> list) {
        getHibernateTemplate().saveOrUpdateAll(list);
    }

    /** hql更新数据 */
    @Override
    public void update(final String hql, final Map<String, Object> params) {
        getHibernateTemplate().execute(new HibernateCallback<Void>() {
            @Override
            public Void doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if (params != null && !params.isEmpty()) {
                    query.setProperties(params);
                }
                query.executeUpdate();
                return null;
            }
        });
    }

    /** hql更新数据 */
    @Override
    public void updateHql(final String hql, final Object... params) {
        getHibernateTemplate().execute(new HibernateCallback<Void>() {
            @Override
            public Void doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if (params != null) {
                    for (int i = 0, size = params.length; i < size; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
                query.executeUpdate();
                return null;
            }
        });
    }

    /** sql更新数据 */
    @Override
    public void updateSql(final String sql, final Map<String, Object> params) {
        getHibernateTemplate().execute(new HibernateCallback<Void>() {
            @Override
            public Void doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql);
                if (params != null && !params.isEmpty()) {
                    query.setProperties(params);
                }
                query.executeUpdate();
                return null;
            }
        });
    }

    /** sql更新数据 */
    @Override
    public void updateSql(final String sql, final Object... params) {
        getHibernateTemplate().execute(new HibernateCallback<Void>() {
            @Override
            public Void doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql);
                if (params != null) {
                    for (int i = 0, size = params.length; i < size; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
                query.executeUpdate();
                return null;
            }
        });
    }

    /** 取对象 */
    @Override
    public <T> T get(Class<T> clazz, Serializable id) {
        return getHibernateTemplate().get(clazz, id);
    }

    /** 列出所有对象 */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> listAll(Class<T> clazz) {
        return getHibernateTemplate().find("from " + clazz.getSimpleName());
    }

    /** 列出所有对象 */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> listAll(Class<T> clazz, String orderBy) {
        StringBuilder hql = new StringBuilder();
        hql.append("from ");
        hql.append(clazz.getSimpleName());
        if (orderBy != null && !"".equals(orderBy)) {
            hql.append(" order by ");
            hql.append(orderBy);
        }
        return getHibernateTemplate().find(hql.toString());
    }

    /** 列出所有对象的主键 */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<Long> listAllIds(Class<T> clazz) {
        return getHibernateTemplate().find("select id from " + clazz.getSimpleName());
    }

    /**
     * 根据条件, 分页, 分页参数及排序选择对象
     * 如果分页组件参与查询时,如果当前分页中的总记录数为0或被要求强制重新加载总记录时,均会从数据库中查询记录总数
     *
     * @param detachedCriteria 条件
     * @param page             分页组件
     * @param orders           排序条件
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> listByCriteria(final DetachedCriteria detachedCriteria, final Page page, final Order... orders) {
        if (page == null) {
            if (orders != null) {
                for (Order order : orders) {
                    detachedCriteria.addOrder(order);
                }
            }
            return (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria);
        }
        return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
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
            }
        });
    }

    /** 根据条件, 排序选择对象 */
    @Override
    public <T> List<T> listByCriteria(DetachedCriteria criteria, Order... orders) {
        return listByCriteria(criteria, null, orders);
    }

    /** 使某个对象从session会话中被移除, 从而成为detach状态 */
    @Override
    public <T> void evict(T t) {
        getHibernateTemplate().evict(t);
    }

    /** 使一个集合从session会话中被移除, 从而成为detach状态 */
    @Override
    public <T> void evict(Collection<? extends T> oList) {
        for (T t : oList) {
            getHibernateTemplate().evict(t);
        }
    }

    /** 获取一条记录 */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T findOne(final String hql, final Object... params) {
        List<T> list = getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
            @Override
            public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if (params != null && params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
                query.setMaxResults(1);
                return query.list();
            }
        });

        return (list != null && !list.isEmpty()) ? list.get(0) : null;
    }

    /** hql查询对象 */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> find(String hql, Object... params) {
        return getHibernateTemplate().find(hql, params);
    }

    /** hql查询对象 */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> find(final String hql, final Map<String, Object> params) {
        return getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
            @Override
            public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if (params != null && !params.isEmpty()) {
                    query.setProperties(params);
                }
                return query.list();
            }
        });
    }

    /**
     * @param hql    查询语句
     * @param params 参数
     * @return hql查询并返回List<Map<String, Object>>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> find2Map(final String hql, final Object... params) {
        return getHibernateTemplate().executeFind(new HibernateCallback<List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if (params != null && params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
                query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                return query.list();
            }
        });
    }

    /**
     * @param hql    查询语句
     * @param params 参数
     * @return hql查询并返回List<Map<String, Object>>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> find2Map(final String hql, final Map<String,Object> params) {
        return getHibernateTemplate().executeFind(new HibernateCallback<List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if (params != null && !params.isEmpty()) {
                    query.setProperties(params);
                }
                query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                return query.list();
            }
        });
    }

    /**
     * @param hql    查询语句
     * @param page   分页控件
     * @param params 查询语句参数
     * @return 使用hql分页查询数据
     */
    @Override
    public <T> List<T> find(final String hql, final Page page, final Object... params) {
        return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            @Override
            @SuppressWarnings("unchecked")
            public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
                Query queryResult = session.createQuery(hql);
                // 如果带有参数,则设置参数
                if (params != null && params.length > 0) {
                    // 需要分页控件
                    if (page != null) {
                        String countHql = "select count(*) ";
                        if (!hql.toLowerCase().startsWith("from")) {
                            countHql += hql.substring(hql.toLowerCase().indexOf("from"));
                        } else {
                            countHql += hql;
                        }
                        Query queryCount = session.createQuery(countHql);
                        for (int i = 0; i < params.length; i++) {
                            queryResult.setParameter(i, params[i]);
                            queryCount.setParameter(i, params[i]);
                        }
                        page.setTotalCount(((Number) queryCount.list().get(0)).intValue());
                        queryResult.setMaxResults(page.getPageSize());
                        queryResult.setFirstResult(page.getStartIndex());
                    } else {
                        // 不需要分页控件
                        for (int i = 0; i < params.length; i++) {
                            queryResult.setParameter(i, params[i]);
                        }
                    }
                }
                return queryResult.list();
            }
        });
    }

    /**
     * 本地sql查询
     *
     * @return map封装的列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> sqlQuery(final String sql, final Object... params) {
        return getHibernateTemplate().executeFind(new HibernateCallback<List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(sql);
                if (params != null && params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
                query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                return query.list();
            }
        });
    }

    /**
     * 本地sql查询
     *
     * @return 实体bean列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> sqlQuery(final Class<T> clazz, final String sql, final Object... params) {
        return getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
            @Override
            public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(sql);
                if (params != null && params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
                query.addEntity(clazz);
                return query.list();
            }
        });
    }

    /**
     * 本地sql查询
     *
     * @param clazz  实体bean的类型
     * @param sql    sql查询语句
     * @param page   分页组件
     * @param params 参数列表
     * @return 实体bean列表
     */
    @Override
    public <T> List<T> sqlQuery(final Class<T> clazz, final String sql, final Page page, final Object... params) {
        return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            @Override
            @SuppressWarnings("unchecked")
            public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery queryResult = session.createSQLQuery(sql);
                // 如果带有参数,则设置参数
                if (params != null && params.length > 0) {
                    // 需要分页控件
                    if (page != null) {
                        String countSql = "select count(*) ";
                        if (!sql.toLowerCase().startsWith("from")) {
                            countSql += sql.substring(sql.toLowerCase().indexOf("from"));
                        } else {
                            countSql += sql;
                        }
                        SQLQuery queryCount = session.createSQLQuery(countSql);
                        for (int i = 0; i < params.length; i++) {
                            queryResult.setParameter(i, params[i]);
                            queryCount.setParameter(i, params[i]);
                        }
                        page.setTotalCount(((Number) queryCount.list().get(0)).intValue());
                        queryResult.setMaxResults(page.getPageSize());
                        queryResult.setFirstResult(page.getStartIndex());
                    } else {
                        // 不需要分页控件
                        for (int i = 0; i < params.length; i++) {
                            queryResult.setParameter(i, params[i]);
                        }
                    }
                }
                queryResult.addEntity(clazz);
                return queryResult.list();
            }
        });
    }

    /**
     * 查询数量
     *
     * @param hql    hql查询语句
     * @param params 参数列表
     * @return Number
     */
    @Override
    public Number numberHql(final String hql, final Object... params) {
        return getHibernateTemplate().execute(new HibernateCallback<Number>() {
            @Override
            public Number doInHibernate(Session session) throws HibernateException, SQLException {
                Query queryCount = session.createQuery(hql);
                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        queryCount.setParameter(i, params[i]);
                    }
                }
                return (Number) queryCount.uniqueResult();
            }
        });
    }

    /**
     * 查询数量
     *
     * @param hql    hql查询语句
     * @param params 参数列表
     * @return Number
     */
    @Override
    public Number numberHql(final String hql, final Map<String, Object> params) {
        return getHibernateTemplate().execute(new HibernateCallback<Number>() {
            @Override
            public Number doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if (params != null && !params.isEmpty()) {
                    query.setProperties(params);
                }
                return (Number) query.uniqueResult();
            }
        });
    }

    /**
     * 查询数量
     *
     * @param sql    sql查询语句
     * @param params 参数列表
     * @return Number
     */
    @Override
    public Number numberSql(final String sql, final Object... params) {
        return getHibernateTemplate().execute(new HibernateCallback<Number>() {
            @Override
            public Number doInHibernate(Session session) throws HibernateException, SQLException {
                Query queryCount = session.createSQLQuery(sql);
                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        queryCount.setParameter(i, params[i]);
                    }
                }
                return (Number) queryCount.uniqueResult();
            }
        });
    }

    /**
     * 查询数量
     *
     * @param sql    sql查询语句
     * @param params 参数列表
     * @return Number
     */
    @Override
    public Number numberSql(final String sql, final Map<String, Object> params) {
        return getHibernateTemplate().execute(new HibernateCallback<Number>() {
            @Override
            public Number doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql);
                if (params != null && !params.isEmpty()) {
                    query.setProperties(params);
                }
                return (Number) query.uniqueResult();
            }
        });
    }

    /**
     * 查询数量
     *
     * @param hql    hql查询语句
     * @param params 参数列表
     * @return Long
     */
    @Override
    public long countHql(final String hql, final Object... params) {
        return numberHql(hql, params).longValue();
    }

    /**
     * 查询数量
     *
     * @param sql    sql查询语句
     * @param params 参数列表
     * @return Long
     */
    @Override
    public long countSql(final String sql, final Object... params) {
        return numberSql(sql, params).longValue();
    }

    /** Force this session to flush */
    @Override
    public void flush() {
        getHibernateTemplate().flush();
    }

    /** Completely clear the session */
    @Override
    public void clear() {
        getHibernateTemplate().clear();
    }

    /**
     * 打开一个新的数据库连接
     *
     * @return Session
     */
    @Override
    public Session openSession() {
        return getSessionFactory().openSession();
    }

    /**
     * 使用当前session
     *
     * @return Session
     */
    @Override
    public Session currentSession() {
        return getSession();
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
