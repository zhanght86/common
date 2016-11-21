package com.sjdf.platform.dictionary.cache;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.LangType;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.Collator;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置库, 数据字典辅助工具(本地使用)
 * Create at 2012-04-05
 *
 * @author 王正伟
 */
public abstract class DictionaryHelper {
    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(DictionaryHelper.class);
    /** 所有的配置信息 */
    private static List<? extends Dictionary> allDictionaryList;
    /** 配置类所对应的名称<className,中文名称> */
    private static Map<String, String> clazzNameMap = new HashMap<>();
    /** 配置类所对应的名称<className,class> */
    private static Map<String, Class<? extends Dictionary>> clazzClassMap = new HashMap<>();
    /** 配置类所对应的配置数据<className,List<Dictionary>> */
    private static Map<String, LinkedList<Dictionary>> dictionaryListMap;
    /** 配置类所对应的有效配置数据<className,List<Dictionary>> */
    private static Map<String, LinkedList<Dictionary>> validDictionaryListMap;
    /** 配置类所对应的配置数据 的id映射 */
    private static Map<Long, Dictionary> idDictionaryMap;

    /**
     * @param clazz 配置数据的类型
     * @param attr  属性值
     * @return Dictionary
     * 实例化子字典
     */
    public static <T extends Dictionary> T createDictionary(Class<T> clazz, long attr) {
        Dictionary dictionary = new Dictionary();
        dictionary.setAttr(attr);
        dictionary.setValue(String.valueOf(attr));
        dictionary.setEnName(String.valueOf(attr));
        dictionary.setLangType(LangType.DEFAULT);
        dictionary.setValid(1);
        return createDictionary(clazz, dictionary);
    }

    /**
     * @param clazz 配置数据的类型
     * @param attr  属性值
     * @return Dictionary
     * 实例化子字典
     */
    public static <T extends Dictionary> T createDictionary(Class<T> clazz, int attr, String value, String cnName, String enName) {
        Dictionary dictionary = new Dictionary();
        dictionary.setAttr(attr);
        dictionary.setValue(value);
        dictionary.setEnName(enName);
        dictionary.setCnName(cnName);
        dictionary.setLangType(LangType.DEFAULT);
        dictionary.setValid(CommonPlatformConstant.LENGTH_2);
        return createDictionary(clazz, dictionary);
    }

    /**
     * @param clazz      配置数据的类型
     * @param dictionary 配置信息
     * @return Dictionary
     * 实例化子字典
     */
    public static <T extends Dictionary> T createDictionary(Class<T> clazz, Dictionary dictionary) {
        T t;
        try {
            t = clazz.newInstance();
        } catch (Exception e) {
            logger.error("实例化子字典失败 " + clazz, e);
            throw new IllegalArgumentException("实例化子字典失败" + clazz);
        }
        t.setId(dictionary.getId());
        t.setAttr(dictionary.getAttr());
        t.setCnName(dictionary.getCnName());
        t.setEnName(dictionary.getEnName());
        t.setValue(dictionary.getValue());
        t.setLangType(dictionary.getLangType());
        t.setOrderBy(dictionary.getOrderBy());
        t.setValid(dictionary.getValid());
        if (dictionary.getRef() != null && dictionary.getRef().getId() != 0) {
            t.setRef(dictionary.getRef());
        }
        if (dictionary.getFunctionClass() != null && dictionary.getFunctionClass().getId() != 0) {
            t.setFunctionClass(dictionary.getFunctionClass());
        }
        if (dictionary.getOperateAction() != null && dictionary.getOperateAction().getId() != 0) {
            t.setOperateAction(dictionary.getOperateAction());
        }
        if (dictionary.getSubsystem() != null && dictionary.getSubsystem().getId() != 0) {
            t.setSubsystem(dictionary.getSubsystem());
        }
        if (dictionary.getSystemType() != null && dictionary.getSystemType().getId() != 0) {
            t.setSystemType(dictionary.getSystemType());
        }
        return t;
    }

    /**
     * @param clazz      配置数据的类型
     * @param dictionary 配置信息
     * @return Dictionary
     * 实例化子字典
     */
    @SuppressWarnings("unchecked")
    public static <T extends Dictionary> T createDictionary(String clazz, Dictionary dictionary) {
        Class<T> c;
        try {
            c = (Class<T>) Class.forName(clazz);
        } catch (Exception e) {
            logger.error("实例化子字典失败,更新打包eiss_common项目 " + clazz, e);
            return null;
        }
        return createDictionary(c, dictionary);
    }

    // -----------------------------------内部使用----------------------------------------

    /** 获取形象性名称 */
    public static String getClazzHumaneName(Class<? extends Dictionary> clazz) {
        return clazz == null ? null : clazzNameMap.get(clazz.getName());
    }

    public static List<Map.Entry<String, String>> getSortedClazzName() {
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(clazzNameMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Entry<String, String> o1, Entry<String, String> o2) {
                return Collator.getInstance(java.util.Locale.CHINA).compare(o1.getValue(), o2.getValue());
            }
        });
        return list;
    }

    /** 获取Class */
    public static Class<? extends Dictionary> getClass(String className) {
        return clazzClassMap.get(className);
    }

    /** 获取所有形象性名称 */
    public static Map<String, String> getClassNameMap() {
        return clazzNameMap;
    }

    /** 获取字典数据 */
    public static <T extends Dictionary> List<T> getAllDictionary(Class<T> clazz) {
        if (clazz == null) {
            return Collections.emptyList();
        }

        @SuppressWarnings("unchecked")
        List<T> list = (List<T>) dictionaryListMap.get(clazz.getName());
        if (list == null) {
            list = Collections.emptyList();
        }
        list = new ArrayList<>(list);
        return list;
    }

    /** 获取所有字典数据 */
    public static List<? extends Dictionary> getAllDictionary() {
        return allDictionaryList;
    }

    /** 获取id映射的所有字典数据 */
    public static Map<Long, Dictionary> getIdDictionaryMap() {
        return idDictionaryMap;
    }

    /** 根据id获取配置数据 */
    public static Dictionary getDictionary(long id) {
        return idDictionaryMap.get(id);
    }

    // -------------------------------------外部接口---------------------------------------

    /** 获取有效字典数据条目 */
    public static List<Dictionary> getDictionary() {
        List<Dictionary> resultList = new ArrayList<>();
        for (List<Dictionary> list : validDictionaryListMap.values()) {
            resultList.addAll(list);
        }
        return resultList;
    }

    /** 获取有效字典数据条目 */
    public static <T extends Dictionary> List<T> getDictionary(String clazz) {
        @SuppressWarnings("unchecked")
        List<T> list = (List<T>) validDictionaryListMap.get(clazz);
        if (list == null) {
            return Collections.emptyList();
        }
        list = new ArrayList<>(list);
        Collections.sort(list, Dictionary.COMPARATOR);
        return list;
    }

    /** 获取有效字典数据条目 */
    public static <T extends Dictionary> List<T> getDictionary(Class<T> clazz) {
        return getDictionary(clazz.getName());
    }

    /**
     * @param clazz   配置数据的类型
     * @param refAttr 引用属性值
     * @return List<? extends Dictionary>
     * 获取指定分类下的所有指定引用配置
     */
    public static <T extends Dictionary> List<T> getRefDictionary(String clazz, long refAttr) {
        List<T> list = getDictionary(clazz);
        if (list.isEmpty()) {
            return list;
        }

        // 过滤
        List<T> result = new ArrayList<>(list.size());
        for (T d : list) {
            Dictionary ref = d.getRef();
            if (ref != null) {
                if (ref.getAttr() == refAttr) {
                    result.add(d);
                }
            } else if (refAttr == 0L) {
                result.add(d);
            }
        }
        return result;
    }

    /** 获取有效字典数据条目 */
    public static <T extends Dictionary> List<T> getDictionary(String clazz, long attr) {
        @SuppressWarnings("unchecked")
        List<T> dictionaryList = (List<T>) validDictionaryListMap.get(clazz);
        List<T> resultList = new ArrayList<>(dictionaryList.size());
        for (T dictionary : dictionaryList) {
            if (dictionary.getAttr() == attr) {
                resultList.add(dictionary);
                break;
            }
        }
        return resultList;
    }

    /** 获取字典数据条目 */
    @SuppressWarnings("unchecked")
    public static <T extends Dictionary> T getDictionary(Class<T> clazz, long attr) {
        List<Dictionary> dictionaryList = validDictionaryListMap.get(clazz.getName());
        if (dictionaryList != null) {
            for (Dictionary dictionary : dictionaryList) {
                if (dictionary.getAttr() == attr) {
                    return (T) dictionary;
                }
            }
        }
        return null;
    }

    /** 获取字典数据条目 */
    @SuppressWarnings("unchecked")
    public static <T extends Dictionary> T getDictionaryByVal(Class<T> clazz, String value) {
        List<Dictionary> dictionaryList = validDictionaryListMap.get(clazz.getName());
        if (dictionaryList != null) {
            for (Dictionary dictionary : dictionaryList) {
                if (dictionary.getValue().equals(value)) {
                    return (T) dictionary;
                }
            }
        }
        return null;
    }

    /** 获取字典数据条目 */
    @SuppressWarnings("unchecked")
    public static <T extends Dictionary> T getDictionaryByCnName(Class<T> clazz, String cnName) {
        List<Dictionary> dictionaryList = validDictionaryListMap.get(clazz.getName());
        if (dictionaryList != null) {
            for (Dictionary dictionary : dictionaryList) {
                if (dictionary.getCnName().equals(cnName)) {
                    return (T) dictionary;
                }
            }
        }
        return null;
    }

    /** 获取字典数据条目 */
    @SuppressWarnings("unchecked")
    public static <T extends Dictionary> T getDictionaryByEnName(Class<T> clazz, String enName) {
        List<Dictionary> dictionaryList = validDictionaryListMap.get(clazz.getName());
        if (dictionaryList != null) {
            for (Dictionary dictionary : dictionaryList) {
                if (dictionary.getEnName().equals(enName)) {
                    return (T) dictionary;
                }
            }
        }
        return null;
    }

    /**
     * @param clazz 配置数据的类型
     * @param value 配置数据的值
     * @return String
     * 获取配置数据的名称
     */
    public static String getNameByVal(Class<? extends Dictionary> clazz, String value) {
        List<Dictionary> dictionaryList = validDictionaryListMap.get(clazz.getName());
        if (dictionaryList != null) {
            for (Dictionary d : dictionaryList) {
                if (d.getValue().equals(value)) {
                    String defaultValue = DictionaryHelper.getValue(LangType.class, LangType.DEFAULT);
                    String en = DictionaryHelper.getValue(LangType.class, LangType.EN);
                    if (defaultValue.equals(en)) {
                        return d.getEnName();
                    } else {
                        return d.getCnName();
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param clazz 配置数据的类型
     * @param attr  配置数据的属性
     * @return String
     * 获取配置数据的名称
     */
    public static String getName(Class<? extends Dictionary> clazz, long attr) {
        return getName(clazz, attr, LangType.DEFAULT);
    }

    /**
     * @param clazz         配置数据的类型
     * @param attr          配置数据的属性
     * @param langTypeValue 语言类型的属性
     * @return String
     * 获取配置数据的名称
     */
    public static String getName(Class<? extends Dictionary> clazz, long attr, long langTypeValue) {
        List<Dictionary> dictionaryList = validDictionaryListMap.get(clazz.getName());
        if (dictionaryList != null) {
            for (Dictionary d : dictionaryList) {
                if (d.getAttr() == attr) {
                    // 如果该字典数据的语言类型为null或者形参语言类型为null,则返回默认的名称
                    LangType defaultLang = DictionaryHelper.getDictionary(LangType.class, LangType.DEFAULT);
                    if (langTypeValue <= 0 || d.getLangType() <= 0) {
                        return (defaultLang != null && defaultLang.getAttr() == LangType.EN) ? d.getEnName() : d.getCnName();
                    } else if (langTypeValue == LangType.CN) {
                        return d.getCnName();
                    } else if (langTypeValue == LangType.EN) {
                        return d.getEnName();
                    } else {
                        return d.getCnName();
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param clazz 配置数据的类型
     * @param attr  配置数据的属性
     * @return String
     * 获取值
     */
    public static String getValue(Class<? extends Dictionary> clazz, long attr) {
        List<Dictionary> dictionaryList = validDictionaryListMap.get(clazz.getName());
        if (dictionaryList != null) {
            for (Dictionary d : dictionaryList) {
                if (d.getAttr() == attr) {
                    return d.getValue();
                }
            }
        }
        return null;
    }

    /**
     * @param clazz 配置数据的类型
     * @param attr  配置数据的属性
     * @return boolean
     * 判断是否在class下存在attr
     */
    public static boolean existAttr(Class<? extends Dictionary> clazz, long attr) {
        List<Dictionary> dictionaryList = validDictionaryListMap.get(clazz.getName());
        if (dictionaryList != null) {
            for (Dictionary d : dictionaryList) {
                if (d.getAttr() == attr) {
                    return true;
                }
            }
        }
        return false;
    }

    // --------------------------------------辅助工具类--------------------------------------

    /** 将配置库对象封装为XML数据文件 */
    public static String parse(List<? extends Dictionary> dictionaryList) {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<dictionaryList>");
        if (dictionaryList != null && !dictionaryList.isEmpty()) {
            for (Dictionary dictionary : dictionaryList) {
                xml.append(dictionary.toXml());
            }
        }
        xml.append("</dictionaryList>");
        return xml.toString();
    }

    /** 将xml文件格式的数据封装为配置库数据列表 */
    @SuppressWarnings("unchecked")
    public static List<? extends Dictionary> parse(String xml) {
        List<Dictionary> dictionaryList = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);
            Element root = (Element) document.selectSingleNode("/dictionaryList");
            List<Element> xmlDictionaryList = root.elements();
            if (xmlDictionaryList != null && !xmlDictionaryList.isEmpty()) {
                for (Element element : xmlDictionaryList) {
                    List<Element> xmlDictionary = element.elements();
                    Dictionary dictionary = new Dictionary();
                    for (Element e : xmlDictionary) {
                        if ("clazz".equals(e.getName())) {
                            dictionary.setClazz(e.getData().toString());
                        } else if ("attr".equals(e.getName())) {
                            int attr = Integer.parseInt(e.getData().toString());
                            dictionary.setAttr(attr);
                        } else if ("value".equals(e.getName())) {
                            dictionary.setValue(e.getData().toString());
                        } else if ("enName".equals(e.getName())) {
                            dictionary.setEnName(e.getData().toString());
                        } else if ("cnName".equals(e.getName())) {
                            dictionary.setCnName(e.getData().toString());
                        } else if ("langType".equals(e.getName())) {
                            int langType = Integer.parseInt(e.getData().toString());
                            dictionary.setLangType(langType);
                        } else if ("id".equals(e.getName())) {
                            long id = parseLong(e.getData());
                            dictionary.setId(id);
                        } else if ("valid".equals(e.getName())) {
                            Object o = e.getData();
                            int valid = 0;
                            if (o != null) {
                                try {
                                    valid = Integer.parseInt(o.toString());
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                    logger.error(e1.getMessage(), e1);
                                }
                            }
                            dictionary.setValid(valid);
                        } else if ("orderBy".equals(e.getName())) {
                            Object o = e.getData();
                            int orderBy = 0;
                            if (o != null) {
                                try {
                                    orderBy = Integer.parseInt(o.toString());
                                } catch (Exception e1) {
                                    logger.error(e1.getMessage(), e1);
                                }
                            }
                            dictionary.setOrderBy(orderBy);
                        } else if ("ref".equals(e.getName())) {
                            long ref = parseLong(e.getData());
                            if (ref != 0) {
                                Dictionary r = new Dictionary();
                                r.setId(ref);
                                dictionary.setRef(r);
                            }
                        } else if ("systemType".equals(e.getName())) {
                            long ref = parseLong(e.getData());
                            if (ref != 0) {
                                Dictionary r = new Dictionary();
                                r.setId(ref);
                                dictionary.setSystemType(r);
                            }
                        } else if ("subsystem".equals(e.getName())) {
                            long ref = parseLong(e.getData());
                            if (ref != 0) {
                                Dictionary r = new Dictionary();
                                r.setId(ref);
                                dictionary.setSubsystem(r);
                            }
                        } else if ("functionClass".equals(e.getName())) {
                            long ref = parseLong(e.getData());
                            if (ref != 0) {
                                Dictionary r = new Dictionary();
                                r.setId(ref);
                                dictionary.setFunctionClass(r);
                            }
                        } else if ("operateAction".equals(e.getName())) {
                            long ref = parseLong(e.getData());
                            if (ref != 0) {
                                Dictionary r = new Dictionary();
                                r.setId(ref);
                                dictionary.setOperateAction(r);
                            }
                        }
                    }

                    // 实例化
                    dictionary = DictionaryHelper.createDictionary(dictionary.getClazz(), dictionary);
                    if (dictionary != null) {
                        dictionaryList.add(dictionary);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return dictionaryList;
    }

    private static long parseLong(Object data) {
        long id = 0;
        if (data != null) {
            return PlatformUtils.parse2Long(data.toString());
        }
        return id;
    }

    // -------------------------------------初始化-----------------------------------------

    /** 初始化名称 */
    public static void initClazzName(Class<? extends Dictionary> clazz, String name) {
        if (clazz != null) {
            clazzNameMap.put(clazz.getName(), name);
            clazzClassMap.put(clazz.getName(), clazz);
        }
    }

    /** 初始化辅助工具的字典数据 */
    public static void initDictionaryList(List<? extends Dictionary> dictionaryList) {
        allDictionaryList = null;
        if (dictionaryList != null) {
            allDictionaryList = new LinkedList<>(dictionaryList);
        } else {
            allDictionaryList = new LinkedList<>();
        }

        Map<String, LinkedList<Dictionary>> dictionaryListMap = new ConcurrentHashMap<>(allDictionaryList.size());
        Map<String, LinkedList<Dictionary>> validDictionaryListMap = new ConcurrentHashMap<>(allDictionaryList.size());
        Map<Long, Dictionary> idDictionaryMap = new ConcurrentHashMap<>(allDictionaryList.size());

        for (Dictionary dictionary : allDictionaryList) {
            // id映射
            idDictionaryMap.put(dictionary.getId(), dictionary);

            // 添加所有配置数据
            LinkedList<Dictionary> list = dictionaryListMap.get(dictionary.getClass().getName());
            if (list == null) {
                list = new LinkedList<>();
                dictionaryListMap.put(dictionary.getClass().getName(), list);
            }
            list.add(dictionary);

            // 添加所有有效配置数据
            LinkedList<Dictionary> validList = validDictionaryListMap.get(dictionary.getClass().getName());
            if (validList == null) {
                validList = new LinkedList<>();
                validDictionaryListMap.put(dictionary.getClass().getName(), validList);
            }
            if (dictionary.getValid() == CommonPlatformConstant.LENGTH_2) {
                validList.add(dictionary);
            }
        }

        // 更新缓存
        DictionaryHelper.dictionaryListMap = dictionaryListMap;
        DictionaryHelper.validDictionaryListMap = validDictionaryListMap;
        DictionaryHelper.idDictionaryMap = idDictionaryMap;
    }

    /** 客户端初始化 */
    public static void initClient(List<? extends Dictionary> dictionaryList) {
        // 初始化辅助工具的字典数据
        initDictionaryList(dictionaryList);

        // 初始化名称
        for (Dictionary dictionary : allDictionaryList) {
            Class<? extends Dictionary> clazz = dictionary.getClass();
            BeanName beanName = clazz.getAnnotation(BeanName.class);
            if (beanName != null && getClass(clazz.getName()) == null) {
                initClazzName(clazz, beanName.name());
            }
        }
    }

    /** 使用方在初始化数据或者更新数据时, 填充引用 */
    public static void fillRreference(List<? extends Dictionary> dictionaryList) {
        for (Dictionary dictionary : dictionaryList) {
            // 填充引用
            if (dictionary.getRef() != null) {
                Dictionary ref = DictionaryHelper.getDictionary(dictionary.getRef().getId());
                if (ref != null) {
                    dictionary.setRef(ref);
                }
            }
            // 填充系统类型
            if (dictionary.getSystemType() != null) {
                Dictionary systemType = DictionaryHelper.getDictionary(dictionary.getSystemType().getId());
                if (systemType != null) {
                    dictionary.setSystemType(systemType);
                }
            }
            // 填充子系统
            if (dictionary.getSubsystem() != null) {
                Dictionary subsystem = DictionaryHelper.getDictionary(dictionary.getSubsystem().getId());
                if (subsystem != null) {
                    dictionary.setSubsystem(subsystem);
                }
            }
            // 填充功能大类
            if (dictionary.getFunctionClass() != null) {
                Dictionary functionType = DictionaryHelper.getDictionary(dictionary.getFunctionClass().getId());
                if (functionType != null) {
                    dictionary.setFunctionClass(functionType);
                }
            }
            // 填充操作类型
            if (dictionary.getOperateAction() != null) {
                Dictionary operateAction = DictionaryHelper.getDictionary(dictionary.getOperateAction().getId());
                if (operateAction != null) {
                    dictionary.setOperateAction(operateAction);
                }
            }
        }
    }

    /** 使用方在初始化数据或者更新数据时, 填充引用 */
    public static void fillRreference() {
        fillRreference(DictionaryHelper.getAllDictionary());
    }

    /** 添加配置信息 */
    @SuppressWarnings("unchecked")
    public static void add(List<? extends Dictionary> dictionaryList) {
        //先删除,避免重复
        delete(dictionaryList);

        for (Dictionary dictionary : dictionaryList) {
            ((List<Dictionary>) allDictionaryList).add(dictionary);
            if (dictionary.getValid() == CommonPlatformConstant.LENGTH_2) {
                LinkedList<Dictionary> list = validDictionaryListMap.get(dictionary.getClass().getName());
                if (list == null) {
                    list = new LinkedList<>();
                    validDictionaryListMap.put(dictionary.getClass().getName(), list);
                }
                list.add(dictionary);
            }

            LinkedList<Dictionary> list = dictionaryListMap.get(dictionary.getClass().getName());
            if (list == null) {
                list = new LinkedList<>();
                dictionaryListMap.put(dictionary.getClass().getName(), list);
            }
            list.add(dictionary);

            idDictionaryMap.put(dictionary.getId(), dictionary);
        }
    }

    /** 修改配置信息 */
    public static void modify(List<? extends Dictionary> dictionaryList) {
        // 删除
        delete(dictionaryList);

        // 添加
        add(dictionaryList);
    }

    /** 删除配置信息 */
    @SuppressWarnings("unchecked")
    public static void delete(List<? extends Dictionary> dictionaryList) {
        for (Dictionary dictionary : dictionaryList) {
            remove((List<Dictionary>) allDictionaryList, dictionary);

            List<Dictionary> list = validDictionaryListMap.get(dictionary.getClass().getName());
            if (list != null) {
                remove(list, dictionary);
            }

            list = dictionaryListMap.get(dictionary.getClass().getName());
            if (list != null) {
                remove(list, dictionary);
            }

            idDictionaryMap.remove(dictionary.getId());
        }
    }

    private static void remove(List<Dictionary> list, Dictionary dictionary) {
        for (ListIterator<Dictionary> listIterator = list.listIterator(); listIterator.hasNext(); ) {
            Dictionary temp = listIterator.next();
            if (temp.getClass() == dictionary.getClass() && temp.getAttr() == dictionary.getAttr()) {
                listIterator.remove();
                //避免数据重复,所以没有使用break;语句
            }
        }
    }
}
