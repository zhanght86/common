package com.sjdf.platform.dictionary.service;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.dictionary.bean.Dictionary;

import java.util.List;

/**
 * Create at 2012-04-05
 * 配置库service
 *
 * @author 王正伟
 */
public interface DictionaryService extends BaseService {
    /**
     * 分页获取配置库数据
     *
     * @param dictionary 配置库
     * @param page       分页组件
     * @return 配置库列表
     */
    List<Dictionary> findByPage(Dictionary dictionary, Page page);

    /**
     * 添加配置数据
     *
     * @param dictionary 配置库
     * @return 消息组件
     */
    Message add(Dictionary dictionary);

    /**
     * 删除指定的配置数据
     *
     * @param idx 组件id
     * @return 消息组件
     */
    Message del(long idx);

    /**
     * 更新配置信息
     *
     * @param dictionary 配置库
     * @param needNotify 是否需要同步数据
     * @return 消息组件
     */
    Message update(Dictionary dictionary, boolean needNotify);

    /**
     * 更新配置数据缓存库
     */
    void updateCache();

    /**
     * 根据配置类型获取配置库数据
     *
     * @param clazz 类型
     * @return 配置库列表
     */
    List<Dictionary> getDictionaryList(String clazz);

    /**
     * 从数据库中获取指定数据
     *
     * @param clazz 类型
     * @param attr  属性
     * @return 配置库
     */
    <T extends Dictionary> T getDictionary(Class<T> clazz, long attr);

    /**
     * 查询数量
     *
     * @param hql    hql
     * @param params 参数列表
     * @return 数量
     */
    long countHql(final String hql, final Object... params);
}
