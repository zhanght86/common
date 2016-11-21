package com.sjdf.platform.dataTemplate.service;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.dataTemplate.bean.Template;

import java.util.List;
import java.util.Map;

/**
 * Create at 2012-05-29
 * 数据模板库管理接口
 *
 * @author 王正伟
 */
public interface TemplateService extends BaseService {
    /**
     * @param template 查询条件
     * @param page     分页组件
     * @return 分页查询模板
     */
    List<Template> list(Template template, Page page);

    /**
     * @param template   待添加或修改的模板
     * @param parameters 配置数据
     * @return 添加或修改的模板
     */
    Message saveOrUpdate(Template template, Map<String, String[]> parameters);

    /**
     * @param idx 模板id
     * @return 删除模板
     */
    Message del(long idx);

    /**
     * @param idx 主键id
     * @return 模板数据
     * 获取指定模板详情
     */
    Template get(long idx);
}
