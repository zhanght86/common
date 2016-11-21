package com.sjdf.platform.dictionary.cache;

import com.sjdf.platform.common.conf.ProfileMapHelper;
import com.sjdf.platform.common.conf.ProfileProvider;
import com.sjdf.platform.common.init.InitManager;
import com.sjdf.platform.common.vo.FixedVo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.LangType;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;

import java.util.*;

/**
 * 基于API调用+缓存(客户端使用)
 * Create at 2012-04-05
 *
 * @author 王正伟
 */
public final class ConfigManager extends InitManager {
    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(ConfigManager.class);
    private static ConfigManager instance = new ConfigManager();

    private ConfigManager() {
        super();

        if (SystemType.getCurrentSystemType() == SystemType.EISS_COMMON) {
            return;
        }

        String data = getData();
        destroyData();

        // 解析
        List<? extends Dictionary> dictionaryList = DictionaryHelper.parse(data);

        // 初始化
        DictionaryHelper.initClient(dictionaryList);

        // 填充引用
        DictionaryHelper.fillRreference();
    }

    /** 获取远程配置数据 */
    protected String getXmlData() {
        // 初始化cacheMap
        HttpSocket httpSocket = new HttpSocket();
        httpSocket.setUrl(ProfileProvider.getValue(ProfileMapHelper.INIT_CONFIG_MANAGER_URL));
        logger.info("url:" + ProfileProvider.getValue(ProfileMapHelper.INIT_CONFIG_MANAGER_URL));

        try {
            httpSocket.doGet();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return null;
        }
        return httpSocket.getResponseData();
    }

    public static ConfigManager getInstance() {
        return instance;
    }

    /**
     * @param clazz      配置数据的类型
     * @param systemType 系统类型
     * @return Dictionary
     * 获取clazz的指定系统类型下的所有配置信息;
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    public <T extends Dictionary> List<T> getListDictionary(String clazz, long systemType) {
        return getDictionaryList(clazz, systemType);
    }

    /**
     * @param clazz   配置数据的类型
     * @param refAttr 引用属性值
     * @return List<? extends Dictionary>
     * 获取指定分类下的所有指定引用配置;
     */
    public <T extends Dictionary> List<T> getListRefDictionary(String clazz, long refAttr) {
        return getRefDictionary(clazz, refAttr);
    }

    /**
     * @param clazz      配置数据的类型
     * @param systemType 系统类型
     * @return List<? extends Dictionary>
     * 获取clazz的指定系统类型下的所有配置信息
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    public <T extends Dictionary> List<T> getDictionaryList(String clazz, long systemType) {
        Dictionary type = getDictionary(SystemType.class, systemType);
        if (type == null) {
            return getDictionary(clazz);
        } else {
            List<T> list = getDictionary(clazz);
            for (ListIterator<? extends Dictionary> listIterator = list.listIterator(); listIterator.hasNext(); ) {
                Dictionary d = listIterator.next();
                Dictionary st = d.getSystemType();
                if (st != null && st.getId() != type.getId()) {
                    listIterator.remove();
                }
            }
            return list;
        }
    }

    /**
     * @param clazz      配置数据的类型
     * @param systemType 系统类型
     * @return List<? extends Dictionary>
     * 获取clazz的指定系统类型下的所有配置信息
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    public <T extends Dictionary> List<T> getDictionaryList(Class<T> clazz, long systemType) {
        return getDictionaryList(clazz.getName(), systemType);
    }

    /**
     * @param clazz   配置数据的类型
     * @param refAttr 引用属性值
     * @return List<? extends Dictionary>
     * 获取指定分类下的所有指定引用配置
     */
    public <T extends Dictionary> List<T> getRefDictionary(String clazz, long refAttr) {
        return DictionaryHelper.getRefDictionary(clazz, refAttr);
    }

    /**
     * @param clazz   配置数据的类型
     * @param refAttr 引用属性值
     * @return List<? extends Dictionary>
     * 获取指定分类下的所有指定引用配置
     */
    public <T extends Dictionary> List<T> getRefDictionary(Class<T> clazz, long refAttr) {
        return DictionaryHelper.getRefDictionary(clazz.getName(), refAttr);
    }

    /** 获取clazz下所有效字典数据条目 */
    public <T extends Dictionary> List<T> getDictionary(String clazz) {
        return DictionaryHelper.getDictionary(clazz);
    }

    /** 获取clazz下所有效字典数据条目 */
    public <T extends Dictionary> List<T> getDictionary(Class<T> clazz) {
        return DictionaryHelper.getDictionary(clazz.getName());
    }

    /**
     * 根据指定条件获取其下所有配置
     *
     * @param clazz      配置数据的类型
     * @param systemType 系统类型
     * @param refAttr    引用属性
     * @return 配置列表
     */
    @SuppressWarnings("unchecked")
    public <T extends Dictionary> List<T> getDictionaryList(Class<T> clazz, long systemType, long refAttr) {
        List<T> list = new ArrayList<>();
        List<? extends Dictionary> tempList = getDictionary(clazz);
        for (Dictionary d : tempList) {
            if (d.getSystemType() != null && d.getSystemType().getAttr() == systemType) {
                if (refAttr == 0L) {
                    if (d.getRef() == null) {
                        list.add((T) d);
                    }
                } else {
                    if (d.getRef() != null && d.getRef().getAttr() == refAttr) {
                        list.add((T) d);
                    }
                }
            }
        }
        return list;
    }

    /** 获取clazz下所有效字典数据条目 */
    public <T extends Dictionary> Map<Long, T> getDictionaryMap(Class<T> clazz) {
        List<T> list = getDictionary(clazz);
        Map<Long, T> map = new HashMap<>();
        for (T t : list) {
            map.put(t.getAttr(), t);
        }
        return map;
    }

    /** 获取封装 为Fixed固定列表 */
    public List<FixedVo> getFixedList(Class<? extends Dictionary> clazz) {
        List<FixedVo> fixedList = new ArrayList<>();
        List<? extends Dictionary> dictionarys = getDictionary(clazz);
        for (Dictionary dictionary : dictionarys) {
            FixedVo fixedVo = new FixedVo();
            fixedVo.setKey(String.valueOf(dictionary.getAttr()));
            fixedVo.setValue(dictionary.getName());
            fixedList.add(fixedVo);
        }
        return fixedList;
    }

    /** 获取以value为key，name为value的封装 为Fixed固定列表 */
    public List<FixedVo> getFixedListByValue(Class<? extends Dictionary> clazz) {
        List<FixedVo> fixedList = new ArrayList<>();
        List<? extends Dictionary> dictionarys = getDictionary(clazz);
        for (Dictionary dictionary : dictionarys) {
            FixedVo fixedVo = new FixedVo();
            fixedVo.setKey(dictionary.getValue());
            fixedVo.setValue(dictionary.getName());
            fixedList.add(fixedVo);
        }
        return fixedList;
    }

    /** 获取以attr为key，value为value的封装 为Fixed固定列表 */
    public List<FixedVo> getValueFixedList(Class<? extends Dictionary> clazz) {
        List<FixedVo> fixedList = new ArrayList<>();
        List<? extends Dictionary> dictionarys = getDictionary(clazz);
        for (Dictionary dictionary : dictionarys) {
            FixedVo fixedVo = new FixedVo();
            fixedVo.setKey(String.valueOf(dictionary.getAttr()));
            fixedVo.setValue(dictionary.getValue());
            fixedList.add(fixedVo);
        }
        return fixedList;
    }

    /** 获取以attr为key，name为value的封装map */
    public Map<Long, String> getNameMap(Class<? extends Dictionary> clazz) {
        Map<Long, String> map = new HashMap<>();
        List<? extends Dictionary> dictionarys = getDictionary(clazz);
        for (Dictionary dictionary : dictionarys) {
            map.put(dictionary.getAttr(), dictionary.getName());
        }

        return map;
    }

    public Map<String, Long> getNameValueMap(Class<? extends Dictionary> clazz) {
        Map<String, Long> map = new HashMap<>();
        List<? extends Dictionary> dictionarys = getDictionary(clazz);
        for (Dictionary dictionary : dictionarys) {
            map.put(dictionary.getCnName(), dictionary.getAttr());
        }
        return map;
    }

    /** 获取clazz下指定属性的指定字典数据条目, 如果attr为空则返回null */
    public <T extends Dictionary> T getDictionary(Class<T> clazz, long attr) {
        return DictionaryHelper.getDictionary(clazz, attr);
    }

    /** 获取字典数据条目 */
    public <T extends Dictionary> T getDictionaryByVal(Class<T> clazz, String value) {
        return DictionaryHelper.getDictionaryByVal(clazz, value);
    }

    /** 获取字典数据条目 */
    public <T extends Dictionary> T getDictionaryByCnName(Class<T> clazz, String cnName) {
        return DictionaryHelper.getDictionaryByCnName(clazz, cnName);
    }

    /** 获取字典数据条目 */
    public <T extends Dictionary> T getDictionaryByEnName(Class<T> clazz, String enName) {
        return DictionaryHelper.getDictionaryByEnName(clazz, enName);
    }

    /**
     * @return List<Dictionary>
     * 获取所有有效配置数据
     */
    public List<Dictionary> getAllDictionary() {
        return DictionaryHelper.getDictionary();
    }

    /**
     * @param clazz 配置数据的类型
     * @param value 配置数据的值
     * @return String
     * 获取配置数据的名称
     */
    public String getNameByVal(Class<? extends Dictionary> clazz, String value) {
        return DictionaryHelper.getNameByVal(clazz, value);
    }

    /**
     * @param clazz 配置数据的类型
     * @param attr  配置数据的属性
     * @return String
     * 获取配置数据的名称
     */
    public String getName(Class<? extends Dictionary> clazz, long attr) {
        return DictionaryHelper.getName(clazz, attr, LangType.DEFAULT);
    }

    /**
     * @param clazz 配置数据的类型
     * @param attr  配置数据的属性
     * @return String
     * 获取配置数据的名称
     */
    public String getCnName(Class<? extends Dictionary> clazz, long attr) {
        return DictionaryHelper.getName(clazz, attr, LangType.CN);
    }

    /**
     * @param clazz 配置数据的类型
     * @param attr  配置数据的属性
     * @return String
     * 获取配置数据的名称
     */
    public String getEnName(Class<? extends Dictionary> clazz, long attr) {
        return DictionaryHelper.getName(clazz, attr, LangType.EN);
    }

    /**
     * @param clazz 配置数据的类型
     * @param attr  配置数据的属性
     * @return boolean
     * 判断是否在class下存在attr
     */
    public boolean existAttr(Class<? extends Dictionary> clazz, long attr) {
        return DictionaryHelper.existAttr(clazz, attr);
    }

    /**
     * @param clazz 配置数据的类型
     * @param attr  配置数据的属性
     * @return clazz或者attr任何一个为空则返回null
     * 获取值
     */
    public String getValue(Class<? extends Dictionary> clazz, long attr) {
        return DictionaryHelper.getValue(clazz, attr);
    }

    /** 将配置库对象封装为XML数据文件 */
    public static String parse(List<? extends Dictionary> dictionaryList) {
        return DictionaryHelper.parse(dictionaryList);
    }

    /** 将xml文件格式的数据封装为配置库数据列表 */
    public static List<? extends Dictionary> parse(String xml) {
        return DictionaryHelper.parse(xml);
    }

    /** 获取所有形象性名称 */
    public static Map<String, String> getClassNameMap() {
        return DictionaryHelper.getClassNameMap();
    }

    /** 获取所有形象性名称 */
    public static List<FixedVo> getSortedClazzName() {
        List<Map.Entry<String, String>> list = DictionaryHelper.getSortedClazzName();
        List<FixedVo> voList = new ArrayList<>(list.size());
        for (Map.Entry<String, String> e : list) {
            FixedVo vo = new FixedVo();
            vo.setKey(e.getKey());
            vo.setValue(e.getValue());
            voList.add(vo);
        }
        return voList;
    }
}
