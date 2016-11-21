package com.sjdf.platform.common.service.impl;

import com.sjdf.platform.common.annotations.Operator;
import com.sjdf.platform.common.annotations.RestrictionMark;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.dao.BaseDao;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;

/**
 * Create at 2012-04-09
 * 基础数据生成服务, 负责基础数据的增删查改等操作
 *
 * @author 王正伟
 */

@Service("commonBaseService")
public class BaseServiceImpl implements BaseService {
    protected final SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(getClass());
    @Resource(name = "commonBaseDao")
    protected BaseDao baseDao;

    @Override
    public <T> T get(Class<T> clazz, Serializable id) {
        return baseDao.get(clazz, id);
    }

    @Override
    public <T> void save(T t) {
        baseDao.save(t);
    }

    @Override
    public <T> void update(T t) {
        baseDao.update(t);
    }

    @Override
    public <T> void delete(T t) {
        baseDao.delete(t);
    }

    @Override
    public <T> void deleteAll(Collection<T> beans) {
        for (T t : beans) {
            baseDao.delete(t);
        }
    }

    @Override
    public <T> void saveOrUpdate(T t) {
        baseDao.saveOrUpdate(t);
    }

    @Override
    public <T> void saveOrUpdate(Collection<T> beans) {
        for (T t : beans) {
            baseDao.saveOrUpdate(t);
        }
    }

    /** 使某个对象从session会话中被移除成为detach状态 */
    public <T> void evict(T t) {
        baseDao.evict(t);
    }

    /** 使一个集合从session会话中被移除成为detach状态 */
    public <T> void evict(Collection<? extends T> oList) {
        baseDao.evict(oList);
    }

    /** 列出所有对象 */
    @Override
    public <T> List<T> listAll(Class<T> clazz) {
        return baseDao.listAll(clazz);
    }

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
    @Override
    public <T extends BaseBean> List<T> list(Class<T> clazz, T t, Page page, Order... orders) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        if (t != null) {
            Field[] fields = clazz.getDeclaredFields();
            try {
                for (Field field : fields) {
                    //屏蔽掉static final类型属性
                    if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                        continue;
                    }

                    Class<?> type = field.getType();
                    if (type.isPrimitive() || type.isAssignableFrom(String.class)) {
                        field.setAccessible(true);
                        RestrictionMark restrictionMark = field.getAnnotation(RestrictionMark.class);

                        //如果操作符是is null或者is not null
                        if (restrictionMark != null && !restrictionMark.disabled() && (restrictionMark.operator() == Operator.IS_NULL || restrictionMark.operator() == Operator.IS_NOT_NULL)) {
                            if (restrictionMark.operator() == Operator.IS_NOT_NULL) {
                                criteria.add(Restrictions.isNotNull(field.getName()));
                            } else if (restrictionMark.operator() == Operator.IS_NULL) {
                                criteria.add(Restrictions.isNull(field.getName()));
                            }
                        }

                        Object value = field.get(t);
                        if (value != null) {
                            if (restrictionMark != null && restrictionMark.disabled()) {
                                continue;
                            }

                            //如果是数字并且==0.0则直接屏蔽掉
                            if (value instanceof Number && ((Number) value).doubleValue() == 0.0D) {
                                continue;
                            }

                            setParameter(criteria, restrictionMark != null ? restrictionMark.operator() : null, field.getName(), value);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
        }

        return baseDao.listByCriteria(criteria, page, orders);
    }

    /**
     * 设置参数
     *
     * @param criteria  离线查询
     * @param operator  操作符
     * @param fieldName 属性名称
     * @param value     值
     */
    private void setParameter(DetachedCriteria criteria, Operator operator, String fieldName, Object value) {
        if (operator == null) {
            criteria.add(Restrictions.eq(fieldName, value));
            return;
        }

        if (operator == Operator.EQ) {
            criteria.add(Restrictions.eq(fieldName, value));
        } else if (operator == Operator.GE) {
            criteria.add(Restrictions.ge(fieldName, value));
        } else if (operator == Operator.GT) {
            criteria.add(Restrictions.gt(fieldName, value));
        } else if (operator == Operator.LE) {
            criteria.add(Restrictions.le(fieldName, value));
        } else if (operator == Operator.LIKE && value instanceof String) {
            criteria.add(Restrictions.like(fieldName, value.toString(), MatchMode.ANYWHERE));
        } else if (operator == Operator.LT) {
            criteria.add(Restrictions.lt(fieldName, value));
        } else if (operator == Operator.NE) {
            criteria.add(Restrictions.ne(fieldName, value));
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
