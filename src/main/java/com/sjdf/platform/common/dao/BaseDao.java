package com.sjdf.platform.common.dao;

import com.sjdf.platform.common.utils.Page;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.core.Ordered;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 基础数据访问实现类<br/>
 * 一般情况下,系统中其他数据访问接口继承BaseDAO接口,而实现则直接继承此类以实现各自数据访问逻辑
 * Create at 2012-11-24
 *
 * @author 王正伟
 */
public interface BaseDao extends Ordered {
    /** 保存 */
    <T> void save(T t);

    /** 添加集合 */
    <T> void saveAll(Collection<T> list);

    /** 添加更新 */
    <T> void saveOrUpdate(T t);

    /** 添加更新集合 */
    <T> void saveOrUpdateAll(Collection<T> list);

    /** 合并 */
    <T> void marge(T t);

    /** 删除 */
    <T> void delete(T t);

    /** 删除列表中所有对象 */
    <T> void deleteAll(Collection<T> collection);

    /** 更新 */
    <T> void update(T t);

    /** 更新 */
    <T> void updateAndFlush(T t);

    /** 更新集合 */
    <T> void updateAll(Collection<T> list);

    /** hql更新数据 */
    void update(String hql, Map<String, Object> params);

    /** hql更新数据 */
    void updateHql(final String hql, final Object... params);

    /** sql更新数据 */
    void updateSql(String sql, Map<String, Object> params);

    /** sql更新数据 */
    void updateSql(final String sql, final Object... params);

    /** 取对象 */
    <T> T get(Class<T> clazz, Serializable id);

    /** 列出所有对象 */
    <T> List<T> listAll(Class<T> clazz);

    /** 列出所有对象 */
    <T> List<T> listAll(Class<T> clazz, String orderBy);

    /** 列出所有对象的主键 */
    <T> List<Long> listAllIds(Class<T> clazz);

    /**
     * @param criteria 条件
     * @param page     分页组件
     * @param orders   排序条件
     *                 根据条件分页查询
     */
    <T> List<T> listByCriteria(DetachedCriteria criteria, Page page, Order... orders);

    /** 根据条件, 排序选择对象 */
    <T> List<T> listByCriteria(DetachedCriteria criteria, Order... orders);

    /** 使某个对象从session会话中被移除成为detach状态 */
    <T> void evict(T t);

    /** 使一个集合从session会话中被移除成为detach状态 */
    <T> void evict(Collection<? extends T> oList);

    /** 获取一条记录 */
    <T> T findOne(final String hql, Object... params);

    /** hql查询对象 */
    <T> List<T> find(String hql, Object... params);

    /** hql查询对象 */
    <T> List<T> find(String hql, Map<String, Object> params);

    /**
     * @param hql    查询语句
     * @param params 参数
     * @return List<Map<String, Object>>
     * hql查询并返回List<Map<String, Object>>
     */
    List<Map<String, Object>> find2Map(String hql, Object... params);

    /**
     * @param hql    查询语句
     * @param params 参数
     * @return List<Map<String, Object>>
     * hql查询并返回List<Map<String, Object>>
     */
    List<Map<String, Object>> find2Map(String hql, Map<String, Object> params);

    /**
     * @param hql    查询语句
     * @param page   分页控件
     * @param params 查询语句参数
     * @return 实体bean列表
     * 使用hql分页查询数据
     */
    <T> List<T> find(final String hql, final Page page, final Object... params);

    /**
     * @return map封装的列表
     * 本地sql查询
     */
    List<Map<String, Object>> sqlQuery(final String sql, final Object... params);

    /**
     * @return 实体bean列表
     * 本地sql查询
     */
    <T> List<T> sqlQuery(final Class<T> clazz, final String sql, final Object... params);

    /**
     * @param clazz  实体bean类型
     * @param sql    sql语句
     * @param page   分页对象
     * @param params 参数集合
     * @return 本地sql分页查询
     */
    <T> List<T> sqlQuery(final Class<T> clazz, final String sql, final Page page, final Object... params);

    /**
     * @param hql    hql查询语句
     * @param params 参数列表
     * @return Number
     * 查询数量
     */
    Number numberHql(final String hql, final Object... params);

    /**
     * 查询数量
     *
     * @param hql    hql查询语句
     * @param params 参数列表
     * @return Number
     */
    Number numberHql(final String hql, final Map<String, Object> params);

    /**
     * @param sql    sql查询语句
     * @param params 参数列表
     * @return Number
     * 查询数量
     */
    Number numberSql(final String sql, final Object... params);

    /**
     * 查询数量
     *
     * @param sql    sql查询语句
     * @param params 参数列表
     * @return Number
     */
    Number numberSql(final String sql, final Map<String, Object> params);

    /**
     * @param hql    hql查询语句
     * @param params 参数列表
     * @return long
     * 查询数量
     */
    long countHql(final String hql, final Object... params);

    /**
     * @param sql    sql查询语句
     * @param params 参数列表
     * @return long
     * 查询数量
     */
    long countSql(final String sql, final Object... params);

    /**
     * Force this session to flush
     */
    void flush();

    /**
     * Completely clear the session
     */
    void clear();

    /**
     * @return Session
     * 打开一个数据库连接
     */
    Session openSession();

    /**
     * @return Session
     * 使用单前session
     */
    Session currentSession();
}
