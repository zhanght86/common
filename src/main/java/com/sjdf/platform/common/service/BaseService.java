package com.sjdf.platform.common.service;

import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.utils.Page;
import org.hibernate.criterion.Order;
import org.springframework.core.Ordered;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Create at 2012-04-09
 * 基础数据生成服务,负责基础数据的增删查改等操作
 *
 * @author 王正伟
 */
public interface BaseService extends Ordered {
    /** 取对象 */
    <T> T get(Class<T> clazz, Serializable id);

    /** 保存 */
    <T> void save(T t);

    /** 更新 */
    <T> void update(T t);

    /** 删除 */
    <T> void delete(T t);

    /** 删除所有 */
    <T> void deleteAll(Collection<T> beans);

    /** 保存或者更新 */
    <T> void saveOrUpdate(T t);

    /** 保存或者更新所有 */
    <T> void saveOrUpdate(Collection<T> beans);

    /** 使某个对象从session会话中被移除成为detach状态 */
    <T> void evict(T t);

    /** 使一个集合从session会话中被移除成为detach状态 */
    <T> void evict(Collection<? extends T> oList);

    /** 列出所有对象 */
    <T> List<T> listAll(Class<T> clazz);

    /**
     * 根据BaseBean中属性上的RestrictionMark注解动态生成查询条件进行相关分页查询
     *
     * @param clazz  条件和返回值的class
     * @param t      条件
     * @param page   分页组件
     * @param orders 排序
     * @param <T>    条件和返回值适配的类型限制
     * @return T的查询列表
     */
    <T extends BaseBean> List<T> list(Class<T> clazz, T t, Page page, Order... orders);
}
