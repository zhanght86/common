package com.sjdf.platform.dictionary.service.impl;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.utils.clazz.ClassUtils;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import com.sjdf.platform.dictionary.service.DictionaryService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Create at 2012-04-05
 * 配置库service
 *
 * @author 王正伟
 */
@Service
public class DictionaryServiceImpl extends BaseServiceImpl implements DictionaryService {

    /** 分页获取配置库数据 */
    public List<Dictionary> findByPage(Dictionary dictionary, Page page) {
        DetachedCriteria criteria;
        if (dictionary == null) {
            criteria = DetachedCriteria.forClass(Dictionary.class);
        } else {
            Class<Dictionary> clazz = null;
            if (PlatformUtils.hasText(dictionary.getClazz())) {
                clazz = ClassUtils.forname(dictionary.getClazz());
            }
            if (clazz == null) {
                clazz = Dictionary.class;
            }
            criteria = DetachedCriteria.forClass(clazz);

            if (dictionary.getValid() != 0) {
                criteria.add(Restrictions.eq("valid", dictionary.getValid()));
            }
        }

        // 排序
        List<Dictionary> result = baseDao.listByCriteria(criteria, page, Order.asc("orderBy"));
        if (dictionary == null || !PlatformUtils.hasText(dictionary.getClazz())) {
            Collections.sort(result, Dictionary.COMPARATOR);
        }

        return result;
    }

    /**
     * 添加配置数据
     *
     * @param dictionary 配置库
     * @return 消息组件
     */
    public Message add(Dictionary dictionary) {
        // 验证
        Message message = validate(dictionary, true);
        if (message.hasErrorMessage()) {
            return message;
        }

        // 保存数据
        Class<? extends Dictionary> clazz = DictionaryHelper.getClass(dictionary.getClazz());
        Dictionary d;
        try {
            d = DictionaryHelper.createDictionary(clazz, dictionary);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Message.createMessage(e.getMessage());
        }

        // 引用
        if (dictionary.getRef() != null && dictionary.getRef().getId() != 0) {
            Dictionary ref = baseDao.get(Dictionary.class, dictionary.getRef().getId());
            if (ref != null) {
                d.setRef(ref);
            }
        }

        // 系统类型
        if (dictionary.getSystemType() != null && dictionary.getSystemType().getId() != 0) {
            Dictionary systemType = baseDao.get(Dictionary.class, dictionary.getSystemType().getId());
            if (systemType != null) {
                d.setSystemType(systemType);
            }
        }

        // 子系统类型
        if (dictionary.getSubsystem() != null && dictionary.getSubsystem().getId() != 0) {
            Dictionary subsystem = baseDao.get(Dictionary.class, dictionary.getSubsystem().getId());
            if (subsystem != null) {
                d.setSubsystem(subsystem);
            }
        }

        // 功能大类
        if (dictionary.getFunctionClass() != null && dictionary.getFunctionClass().getId() != 0) {
            Dictionary functionType = baseDao.get(Dictionary.class, dictionary.getFunctionClass().getId());
            if (functionType != null) {
                d.setFunctionClass(functionType);
            }
        }

        // 操作动作
        if (dictionary.getOperateAction() != null && dictionary.getOperateAction().getId() != 0) {
            Dictionary operateAction = baseDao.get(Dictionary.class, dictionary.getOperateAction().getId());
            if (operateAction != null) {
                d.setOperateAction(operateAction);
            }
        }

        baseDao.save(d);

        return Message.createMessage().setReturnData(d).setInfo(d.wrapUpdateContent(null, true));
    }

    /**
     * 删除指定的配置数据
     *
     * @param idx 主键id
     * @return 消息组件
     */
    public Message del(long idx) {
        Dictionary dictionary = baseDao.get(Dictionary.class, idx);
        if (dictionary == null) {
            return Message.createMessage("dictionary.null");
        }

        baseDao.delete(dictionary);

        return Message.createMessage().setReturnData(dictionary).setInfo(dictionary.wrapUpdateContent(null, false));
    }

    /**
     * 更新配置信息
     *
     * @param dictionary 配置库
     * @param needNotify 是否需要同步数据
     * @return 消息组件
     */
    public Message update(Dictionary dictionary, boolean needNotify) {
        // 验证
        Message message = validate(dictionary, false);
        if (message.hasErrorMessage()) {
            return message;
        }

        // 更新数据
        Dictionary oldDictionary = baseDao.get(Dictionary.class, dictionary.getId());
        if (oldDictionary == null) {
            return Message.createMessage("dictionary.null");
        }

        // 处理引用
        if (dictionary.getRef() != null && dictionary.getRef().getId() != 0) {
            Dictionary ref = baseDao.get(Dictionary.class, dictionary.getRef().getId());
            // 验证循环引用
            message = valid(oldDictionary.getId(), ref);
            if (message.hasErrorMessage()) {
                return message;
            }
            dictionary.setRef(ref);
        } else {
            dictionary.setRef(null);
        }

        // 处理系统类型
        if (dictionary.getSystemType() != null && dictionary.getSystemType().getId() != 0) {
            Dictionary systemType = baseDao.get(Dictionary.class, dictionary.getSystemType().getId());
            // 验证循环引用
            message = valid(oldDictionary.getId(), systemType);
            if (message.hasErrorMessage()) {
                return message;
            }
            dictionary.setSystemType(systemType);
        } else {
            dictionary.setSystemType(null);
        }

        // 处理子系统类型
        if (dictionary.getSubsystem() != null && dictionary.getSubsystem().getId() != 0) {
            Dictionary subsystem = baseDao.get(Dictionary.class, dictionary.getSubsystem().getId());
            // 验证循环引用
            message = valid(oldDictionary.getId(), subsystem);
            if (message.hasErrorMessage()) {
                return message;
            }

            dictionary.setSubsystem(subsystem);
        } else {
            dictionary.setSubsystem(null);
        }

        // 处理功能大类
        if (dictionary.getFunctionClass() != null && dictionary.getFunctionClass().getId() != 0) {
            Dictionary functionType = baseDao.get(Dictionary.class, dictionary.getFunctionClass().getId());
            // 验证循环引用
            message = valid(oldDictionary.getId(), functionType);
            if (message.hasErrorMessage()) {
                return message;
            }

            dictionary.setFunctionClass(functionType);
        } else {
            dictionary.setFunctionClass(null);
        }

        // 处理操作动作
        if (dictionary.getOperateAction() != null && dictionary.getOperateAction().getId() != 0) {
            Dictionary operateAction = baseDao.get(Dictionary.class, dictionary.getOperateAction().getId());
            // 验证循环引用
            message = valid(oldDictionary.getId(), operateAction);
            if (message.hasErrorMessage()) {
                return message;
            }

            dictionary.setOperateAction(operateAction);
        } else {
            dictionary.setOperateAction(null);
        }

        String info = oldDictionary.wrapUpdateContent(dictionary, true);

        oldDictionary.setAttr(dictionary.getAttr());
        oldDictionary.setCnName(dictionary.getCnName());
        oldDictionary.setEnName(dictionary.getEnName());
        oldDictionary.setLangType(dictionary.getLangType());
        oldDictionary.setOrderBy(dictionary.getOrderBy());
        oldDictionary.setValid(dictionary.getValid());
        oldDictionary.setValue(dictionary.getValue());

        oldDictionary.setRef(dictionary.getRef());
        oldDictionary.setSystemType(dictionary.getSystemType());
        oldDictionary.setSubsystem(dictionary.getSubsystem());
        oldDictionary.setFunctionClass(dictionary.getFunctionClass());
        oldDictionary.setOperateAction(dictionary.getOperateAction());

        baseDao.update(oldDictionary);

        return Message.createMessage().setReturnData(oldDictionary).setInfo(info);
    }

    /**
     * 查询数量
     *
     * @param hql    hql
     * @param params 参数列表
     * @return 数量
     */
    public long countHql(final String hql, final Object... params) {
        return baseDao.countHql(hql, params);
    }

    /** 验证数据的有效性 */
    private Message validate(Dictionary dictionary, boolean isAdd) {
        if(dictionary == null){
            return Message.createMessage("dictionary.null");
        }
        if (!PlatformUtils.hasText(dictionary.getCnName())) {
            return Message.createMessage("dictionary.cn.name.null");
        }
        if (dictionary.getAttr() < 0) {
            return Message.createMessage("dictionary.attr.gt.0");
        }

        Class<?> clazz;
        if (isAdd) {
            clazz = ClassUtils.forname(dictionary.getClazz());
            if (clazz == null) {
                return Message.createMessage("dictionary.class.invalidate");
            }
            // 验证属性名唯一
            return validateAttr(clazz, dictionary.getAttr());
        } else {
            Dictionary oldDictionary = baseDao.get(Dictionary.class, dictionary.getId());
            if (oldDictionary == null) {
                return Message.createMessage("dictionary.null");
            }
            clazz = oldDictionary.getClass();
            if (dictionary.getAttr() != oldDictionary.getAttr()) {
                // 验证属性名唯一
                return validateAttr(clazz, dictionary.getAttr());
            }
        }
        return Message.createEmptyMessage();
    }

    /**
     * 验证属性名唯一
     *
     * @param clazz 类型
     * @param attr  属性
     * @return 消息组件
     */
    private Message validateAttr(Class<?> clazz, long attr) {
        StringBuilder hql = new StringBuilder(CommonPlatformConstant.LENGTH_32);
        hql.append("select count(id) from ");
        hql.append(clazz.getSimpleName());
        hql.append(" where attr = ?");
        long count = baseDao.countHql(hql.toString(), attr);
        return count > 0 ? Message.createMessage("dictionary.exist.attr") : Message.createEmptyMessage();
    }

    /**
     * 更新配置数据缓存库
     */
    public void updateCache() {
        DictionaryHelper.initDictionaryList(findByPage(null, null));
    }

    /**
     * 根据配置类型获取配置库数据
     *
     * @param clazz 类型
     * @return 配置库列表
     */
    @SuppressWarnings("unchecked")
    public List<Dictionary> getDictionaryList(String clazz) {
        return (List<Dictionary>) DictionaryHelper.getDictionary(clazz);
    }

    /**
     * 从数据库中获取指定数据
     *
     * @param clazz 类型
     * @param attr  属性值
     * @return 配置库
     */
    public <T extends Dictionary> T getDictionary(Class<T> clazz, long attr) {
        List<T> list = baseDao.find("from " + clazz.getSimpleName() + " where attr = ?", attr);
        return (list != null && !list.isEmpty()) ? list.get(0) : null;
    }

    /** 验证引用是否是循环引用 */
    private Message valid(long id, Dictionary ref) {
        if (id == ref.getId()) {
            return Message.createMessage("dictionary.ref.loop");
        }

        if (ref.getRef() != null && ref.getRef().getId() != 0) {
            Dictionary dictionary = baseDao.get(Dictionary.class, ref.getRef().getId());
            return valid(id, dictionary);
        }
        return Message.createEmptyMessage();
    }
}
