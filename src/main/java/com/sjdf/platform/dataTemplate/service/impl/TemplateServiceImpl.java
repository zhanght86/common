package com.sjdf.platform.dataTemplate.service.impl;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.dataTemplate.bean.Template;
import com.sjdf.platform.dataTemplate.service.TemplateService;
import com.sjdf.platform.dictionary.bean.TemplateType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Create at 2012-05-29
 * 数据模板库管理接口实现
 *
 * @author 王正伟
 */
@Service
public class TemplateServiceImpl extends BaseServiceImpl implements TemplateService {
    /**
     * @param template 查询条件
     * @param page     分页组件
     * @return 分页查询模板
     */
    public List<Template> list(Template template, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Template.class);
        if (template != null) {
            if (template.getId() != 0) {
                criteria.add(Restrictions.eq("id", template.getId()));
            }
            if (template.getSystemType() > 0) {
                criteria.add(Restrictions.eq("systemType", template.getSystemType()));
            }
            if (StringUtils.hasText(template.getMemberNumber())) {
                criteria.add(Restrictions.eq("memberNumber", template.getMemberNumber()));
            }
            if (template.getClazz() > 0) {
                criteria.add(Restrictions.eq("clazz", template.getClazz()));
            }

            if (template.getType() > 0) {
                criteria.add(Restrictions.eq("type", template.getType()));
            } else if (!template.getAllTypeList().isEmpty()) {
                criteria.add(Restrictions.in("type", template.getAllTypeList()));
            }

            if (StringUtils.hasText(template.getSystemName())) {
                criteria.add(Restrictions.eq("systemName", template.getSystemName()));
            }
            if (StringUtils.hasText(template.getName())) {
                criteria.add(Restrictions.like("name", template.getName()));
            }

        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Template> list = baseDao.listByCriteria(criteria, page);
        for (Template t : list) {
            Hibernate.initialize(t.getData());
        }
        return list;
    }

    /**
     * @param template   待添加或修改的模板
     * @param parameters 配置数据
     * @return 添加或修改的模板
     */
    public Message saveOrUpdate(Template template, Map<String, String[]> parameters) {
        Map<String, String> data = template.getData();
        if (parameters != null) {
            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {

                switch (entry.getKey()) {
                    case "template.systemType":
                    case "template.memberNumber":
                    case "template.memberName":
                    case "template.clazz":
                    case "template.type":
                    case "template.systemName":
                    case "template.name":
                        continue;
                    default:
                        break;
                }

                String[] value = entry.getValue();
                if (value != null && value.length > 0) {
                    data.put(entry.getKey(), value[0]);
                }
            }
        }

        // 设置产品大分类
        if (template.getType() > 0) {
            TemplateType type = ConfigManager.getInstance().getDictionary(TemplateType.class, template.getType());
            if (type != null) {
                template.setClazz(type.getRef().getAttr());
            }
        }

        String info;
        Template oldTemplate = baseDao.findOne("from Template where memberNumber = ? and type = ? and systemName = ?", template.getMemberNumber(), template.getType(), template.getSystemName());
        if (oldTemplate != null) {
            info = oldTemplate.wrapUpdateContent(template, true);
            oldTemplate.setName(template.getName());
            oldTemplate.setData(template.getData());
            baseDao.update(oldTemplate);
        } else {
            info = template.wrapUpdateContent(null, false);
            baseDao.save(template);
            oldTemplate = template;
        }
        return Message.createMessage().setInfo(info).setReturnData(oldTemplate);
    }

    /**
     * @param idx 模板id
     * @return 删除模板
     */
    public Message del(long idx) {
        Template template = baseDao.get(Template.class, idx);
        if (template != null) {
            baseDao.delete(template);
            return Message.createMessage().setInfo(template.wrapUpdateContent(null, false)).setReturnData(template);
        }
        return Message.createMessage("message.template.null");
    }

    /**
     * @param idx 主键id
     * @return 模板数据
     * 获取指定模板详情
     */
    public Template get(long idx) {
        Template template = baseDao.get(Template.class, idx);
        if (template != null) {
            Hibernate.initialize(template.getData());
        }
        return template;
    }
}
