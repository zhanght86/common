package com.sjdf.platform.propconfig.init;

import com.sjdf.platform.propconfig.annotations.PropertiesConfigField;
import com.sjdf.platform.propconfig.annotations.PropertiesConfigType;
import com.sjdf.platform.propconfig.bean.PropertiesConfigFieldBean;
import com.sjdf.platform.propconfig.cache.ConfigBeanCnNameCache;
import com.sjdf.platform.propconfig.service.PropertiesConfigFieldService;
import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 2015-08-27
 * 详细说明：
 * 系统启动时会对属性配置项进行初始化。
 * 1 扫描所有的entity类，查看其是否有<code>PropertiesConfigType<code>注解，有则进入2；
 * 2 反射得到类自己所有的字段，对有<code>PropertiesConfigField<code>注解的字段，进入3；
 * 3 通过entity上的<code>PropertiesConfig<code>注解的value和字段的名字到表<code>PropertiesConfigFieldBean<code>中查找。
 * 如果找到则更新这条记录的fieldCnName(字段上的<code>PropertiesConfigField<code>注解的value)，如果没有则新增这条记录。本类下所有字段处理完成后进入4；
 * 注：这步是用来更新字段注解的中文名称，新增的注解。
 * 4 在第3步中记录下entity下所有的<code>PropertiesConfigField<code>注解字段fieldEnNameList。
 * 查找表<code>PropertiesConfigFieldBean<code>中beanConfigName等于entity上的<code>PropertiesConfigType<code>注解的value，
 * 而字段名fieldEnName不在fieldEnNameList中的记录，删除这样的记录并级联删除<code>PropertiesConfigBean<code>中对应的数据。
 * 注：当去掉字段上的<code>PropertiesConfigField<code>注解后，这步就会删除该字段的相关数据。
 * 5 和第4步原理一样。记录下entity上的<code>PropertiesConfigType<code>注解的value为newAllConfigNameList。
 * 等所有类扫描完成，更新完成后。级联删除<code>PropertiesConfigFieldBean<code>beanConfigName不在newAllConfigNameList的数据。
 * 注：当去掉entity类上的<code>PropertiesConfigType<code>注解后，这步就会删除该类的相关数据。
 *
 * @author wangpeng
 */
@Component
public class InitPropertiesConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        PropertiesConfigFieldService service = (PropertiesConfigFieldService) context.getBean("fieldService");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Map<String, ClassMetadata> clazzMetadataMap = sessionFactory.getAllClassMetadata();

        List<String> fieldEnNameList = new ArrayList<>();
        List<String> newAllConfigNameList = new ArrayList<>();
        for (ClassMetadata classMetadata : clazzMetadataMap.values()) {
            Class<?> clazz = classMetadata.getMappedClass(EntityMode.POJO);
            // 1 读取 @PropertiesConfig注解的bean类
            PropertiesConfigType beanAnnotation = clazz.getAnnotation(PropertiesConfigType.class);
            if (beanAnnotation == null) {
                continue;
            }
            // 将注解中的enName和cnName缓存
            ConfigBeanCnNameCache.getInstance().putEntity(beanAnnotation.value(), beanAnnotation.name());
            newAllConfigNameList.add(beanAnnotation.value());
            // 2 读取bean类下 @PropertiesConfig注解的字段
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                PropertiesConfigField fieldAnnotation = field.getAnnotation(PropertiesConfigField.class);
                if (fieldAnnotation == null) {
                    continue;
                }
                PropertiesConfigFieldBean fieldBean = new PropertiesConfigFieldBean();
                fieldBean.setBeanConfigName(beanAnnotation.value());
                fieldBean.setFieldEnName(field.getName());
                fieldBean.setFieldCnName(fieldAnnotation.value());
                // 3 更新 PropertiesConfigFieldBean
                service.saveOrUpdateByConfigNameAndEnName(fieldBean);
                fieldEnNameList.add(field.getName());
                ConfigBeanCnNameCache.getInstance().putField(field.getName(), fieldAnnotation.value());
            }
            // 4 删除PropertiesConfigFieldBean中过期数据
            service.deleteExpiredConfig(beanAnnotation.value(), fieldEnNameList);
            fieldEnNameList.clear();
        }
        // 5 根据现在所有注解类删除过期的注解类
        if (!newAllConfigNameList.isEmpty()) {
            service.deleteExpiredConfig(newAllConfigNameList);
        }
    }
}
