package com.sjdf.platform.associate.service;

import com.sjdf.platform.associate.bean.Associate;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;

import java.util.List;

/**
 * Create at 2012-08-07
 * 关联信息service
 *
 * @author 王正伟
 */
public interface AssociateService extends BaseService {
    /**
     * 分页查询关联信息
     *
     * @param associate 关联信息
     * @param page      分页组件
     * @return 关联信息列表
     */
    List<Associate> list(Associate associate, Page page);

    /**
     * 保存关联信息
     *
     * @param associate 关联信息
     * @return 消息组件
     */
    Message saveOrUpdate(Associate associate);

    /**
     * 获取关联信息
     *
     * @param associate 关联信息
     * @return 关联信息列表
     */
    List<Associate> get(Associate associate);

    /**
     * 验证用户名和密码的有效性
     *
     * @param associate 关联信息
     * @return 消息组件
     */
    Message permissionValid(Associate associate);
}
