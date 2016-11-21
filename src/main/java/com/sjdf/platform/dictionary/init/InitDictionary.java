package com.sjdf.platform.dictionary.init;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.LangType;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import com.sjdf.platform.dictionary.service.DictionaryService;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Create at 2012-04-05
 * 初始化配置库数据
 *
 * @author 王正伟
 */
public class InitDictionary implements ApplicationContextAware, Ordered {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(InitDictionary.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DictionaryService dictionaryService = (DictionaryService) applicationContext.getBean("dictionaryServiceImpl");

        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
        Map<String, ClassMetadata> clazzMetadataMap = sessionFactory.getAllClassMetadata();

        // 1.初始化相关数据
        // 记录含有引用的数据记录
        Set<Dictionary> refList = new HashSet<>();
        for (ClassMetadata classMetadata : clazzMetadataMap.values()) {
            Class<?> pojoClass = classMetadata.getMappedClass(EntityMode.POJO);

            // 过滤
            if (!Dictionary.class.isAssignableFrom(pojoClass)) {
                continue;
            }

            // 屏蔽Dictionary
            if (pojoClass.isAssignableFrom(Dictionary.class)) {
                continue;
            }

            @SuppressWarnings("unchecked")
            Class<? extends Dictionary> clazz = (Class<? extends Dictionary>) pojoClass;

            // 1.1初始化配置库的名称
            BeanName beanName = clazz.getAnnotation(BeanName.class);
            DictionaryHelper.initClazzName(clazz, beanName.name());

            // 1.2初始化必要属性
            StringBuilder hql = new StringBuilder();
            hql.append("select count(id) from ");
            hql.append(clazz.getSimpleName());
            hql.append(" where attr = ?");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // 必须是public static final long 约束的属性
                if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && "long".equals(field.getType().getName())) {
                    long attr;
                    try {
                        attr = (long) field.get(null);
                    } catch (Exception e) {
                        LOGGER.error("必须是public static final long 约束的属性", e);
                        continue;
                    }
                    long count = dictionaryService.countHql(hql.toString(), attr);

                    if (count == 0) {
                        BeanAttrInfo beanAttrInfo = field.getAnnotation(BeanAttrInfo.class);
                        if (beanAttrInfo != null) {
                            String value = beanAttrInfo.value();
                            long orderBy = beanAttrInfo.orderBy();
                            String cnName = beanAttrInfo.cnName();
                            String enName = beanAttrInfo.enName();
                            long systemType = beanAttrInfo.systemType();
                            Class<? extends Dictionary> refClass = beanAttrInfo.refClass();
                            long refAttr = beanAttrInfo.refAttr();
                            if (!StringUtils.hasText(cnName)) {
                                dictionaryService.save(DictionaryHelper.createDictionary(clazz, attr));
                            } else {
                                if (!StringUtils.hasText(enName)) {
                                    enName = field.getName().toLowerCase().replaceAll("_", " ");
                                }
                                Dictionary dictionary = new Dictionary();
                                dictionary.setAttr(attr);
                                dictionary.setOrderBy(orderBy);
                                dictionary.setValue(value);
                                dictionary.setEnName(enName);
                                dictionary.setCnName(cnName);
                                dictionary.setLangType(LangType.DEFAULT);
                                dictionary.setValid(CommonPlatformConstant.LENGTH_2);

                                Dictionary targetDictionary;
                                try {
                                    targetDictionary = DictionaryHelper.createDictionary(clazz, dictionary);
                                } catch (Exception e) {
                                    LOGGER.error(e.getMessage(), e);
                                    continue;
                                }
                                if (targetDictionary == null) {
                                    continue;
                                }
                                dictionaryService.save(targetDictionary);
                                dictionary.setId(targetDictionary.getId());

                                // ***************处理引用相关信息***************
                                // 系统类型
                                if (systemType != 0) {
                                    try {
                                        dictionary.setSystemType(DictionaryHelper.createDictionary(SystemType.class, systemType));
                                        refList.add(dictionary);
                                    } catch (Exception e) {
                                        LOGGER.error(e.getMessage(), e);
                                    }
                                }

                                // 引用类型不是默认的Class && 应用属性不能为空
                                if (!refClass.getName().equals(Dictionary.class.getName()) && refAttr != 0 && !(clazz.getName().equals(refClass.getName()) && attr == refAttr)) {
                                    // 不能引用属性本身
                                    try {
                                        dictionary.setRef(DictionaryHelper.createDictionary(refClass, refAttr));
                                        refList.add(dictionary);
                                    } catch (Exception e) {
                                        LOGGER.error(e.getMessage(), e);
                                    }
                                }
                            }
                        } else {
                            Dictionary temp;
                            try {
                                temp = DictionaryHelper.createDictionary(clazz, attr);
                            } catch (Exception e) {
                                LOGGER.error(e.getMessage(), e);
                                continue;
                            }
                            if (temp != null) {
                                dictionaryService.save(temp);
                            }
                        }
                    }
                }
            }
        }

        // 处理含有引用的数据
        for (Dictionary dictionary : refList) {
            // 处理引用
            if (dictionary.getRef() != null) {
                Dictionary ref = dictionaryService.getDictionary(dictionary.getRef().getClass(), dictionary.getRef().getAttr());
                dictionary.setRef(ref);
            }
            // 处理系统类型
            if (dictionary.getSystemType() != null) {
                Dictionary systemType = dictionaryService.getDictionary(SystemType.class, dictionary.getSystemType().getAttr());
                dictionary.setSystemType(systemType);
            }

            // 更新数据
            try {
                Message message = dictionaryService.update(dictionary, false);
                if (message.hasErrorMessage()) {
                    LOGGER.error(message.getErrorMessage() + dictionary.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("处理含有引用的数据时,更新失败", e);
            }
        }

        // 2.初始化配置库数据
        dictionaryService.updateCache();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
