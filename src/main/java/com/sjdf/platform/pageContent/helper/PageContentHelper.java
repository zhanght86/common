package com.sjdf.platform.pageContent.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.common.utils.ResultVo;
import com.sjdf.platform.dictionary.bean.OperatorAction;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.bean.WhetherState;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;
import com.sjdf.platform.pageContent.bean.ContentManageBean;
import com.sjdf.platform.pageContent.vo.PageContentVo;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 页面内容管理辅助
 *
 * @author laberwu
 */
public abstract class PageContentHelper {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(PageContentHelper.class);
    /** 页面内容信息（正式缓存） */
    private static List<PageContentVo> pageContentList = Collections.synchronizedList(new ArrayList<PageContentVo>());
    /** 页面内存信息（预览缓存） */
    private static List<PageContentVo> pageContentList4Preview = Collections.synchronizedList(new ArrayList<PageContentVo>());
    /** 添加缓存 */
    private static ConcurrentMap<Long, ContentManageBean> addCacheMap = new ConcurrentHashMap<>();
    /** 更新缓存 */
    private static ConcurrentMap<Long, ContentManageBean> updateCacheMap = new ConcurrentHashMap<>();
    /** 删除缓存 */
    private static ConcurrentMap<Long, ContentManageBean> delCacheMap = new ConcurrentHashMap<>();

    /**
     * 解析xml数据
     *
     * @param xmlData xml原数据
     * @return List<PageContentVo> xml解析结果
     * @throws Exception
     */
    public static List<PageContentVo> parseXml(String xmlData) throws Exception {
        List<PageContentVo> resultList = new ArrayList<>();

        if (StringUtils.hasText(xmlData)) {
            SAXReader saxReader = new SAXReader();
            try (InputStream is = new ByteArrayInputStream(xmlData.getBytes("UTF-8"))) {
                Document document = saxReader.read(is);

                //　验证xml数据正确性
                Node node = document.selectSingleNode("/error");
                if (node != null) {
                    throw new RuntimeException(((Element) node).getData().toString());
                }

                // 解析xml
                Element root = (Element) document.selectSingleNode("/pageList"); // pageList
                @SuppressWarnings("unchecked")
                List<Element> pageElementList = root.elements();
                if (pageElementList != null && !pageElementList.isEmpty()) {
                    for (Element element : pageElementList) {
                        @SuppressWarnings("unchecked")
                        List<Element> page = element.elements();
                        if (page != null && !page.isEmpty()) {
                            PageContentVo vo = parseXmlToVo(page);
                            resultList.add(vo);
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                throw e;
            }
        }

        return resultList;
    }

    /**
     * 初始化缓存
     *
     * @param xmlData xml数据源
     * @throws Exception
     */
    public static void init(String xmlData, boolean isPreview) throws Exception {
        // 解析xml数据
        List<PageContentVo> result = parseXml(xmlData);
        // 初始化预览缓存
        if (isPreview) {
            pageContentList4Preview.clear();
            pageContentList4Preview.addAll(result);
        } else {
            pageContentList.clear();
            pageContentList.addAll(result);
        }
    }

    /**
     * 将数据转化为xml
     *
     * @param contentList 页面内容管理列表
     * @return xml
     */
    public static String toXml(List<ContentManageBean> contentList) {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<pageList>");
        if (contentList != null && !contentList.isEmpty()) {
            for (ContentManageBean content : contentList) {
                xml.append(content.toXml());
            }
        }
        xml.append("</pageList>");

        return xml.toString();
    }

    /**
     * 添加至增加缓存
     *
     * @param contentBean 页面内容管理
     */
    public static void addAddCache(ContentManageBean contentBean) {
        addCacheMap.put(contentBean.getId(), contentBean);
    }

    /**
     * 添加至更新缓存
     *
     * @param contentBean 页面内容管理
     */
    public static void addUpdateCache(ContentManageBean contentBean) {
        ContentManageBean temp = addCacheMap.get(contentBean.getId());
        // 增加缓存中存在数据，则加入增加缓存中，否则加入更新缓存中
        if (temp != null) {
            addCacheMap.put(contentBean.getId(), contentBean);
        } else {
            updateCacheMap.put(contentBean.getId(), contentBean);
        }
    }

    /**
     * 添加至删除缓存
     *
     * @param contentBean 页面内容管理
     */
    public static void addDelCache(ContentManageBean contentBean) {
        ContentManageBean temp = addCacheMap.get(contentBean.getId());
        if (temp != null) {
            addCacheMap.remove(contentBean.getId());
        }
        temp = updateCacheMap.get(contentBean.getId());
        if (temp != null) {
            updateCacheMap.remove(contentBean.getId());
        }

        delCacheMap.put(contentBean.getId(), contentBean);
    }

    /**
     * 获取推送数据，把当前的缓存数据推送
     *
     * @return String 当前推送缓存组成的缓存数据
     */
    public static String getPushData() {
        StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<pageList>");
        if (!addCacheMap.isEmpty()) {
            Set<Entry<Long, ContentManageBean>> set = addCacheMap.entrySet();
            for (Entry<Long, ContentManageBean> en : set) {
                xml.append(en.getValue().toXml(OperatorAction.ADD));
            }
        }
        if (!updateCacheMap.isEmpty()) {
            Set<Entry<Long, ContentManageBean>> set = updateCacheMap.entrySet();
            for (Entry<Long, ContentManageBean> en : set) {
                xml.append(en.getValue().toXml(OperatorAction.MODIFY));
            }
        }
        if (!delCacheMap.isEmpty()) {
            Set<Entry<Long, ContentManageBean>> set = delCacheMap.entrySet();
            for (Entry<Long, ContentManageBean> en : set) {
                xml.append(en.getValue().toXml(OperatorAction.DELETE));
            }
        }
        xml.append("</pageList>");

        return xml.toString();
    }

    /**
     * 清空要推送的缓存数据
     */
    public static void clearCacheMap() {
        addCacheMap.clear();
        updateCacheMap.clear();
        delCacheMap.clear();
    }

    /**
     * 解析用于更改缓存的xml数据
     *
     * @param data xml数据
     * @throws Exception
     */
    private static Map<Long, List<PageContentVo>> parseCacheXml(String data) throws Exception {
        List<PageContentVo> addList = new ArrayList<>();
        List<PageContentVo> updateList = new ArrayList<>();
        List<PageContentVo> delList = new ArrayList<>();
        Map<Long, List<PageContentVo>> resultMap = new HashMap<>();

        if (StringUtils.hasText(data)) {
            SAXReader saxReader = new SAXReader();
            try (InputStream is = new ByteArrayInputStream(data.getBytes("UTF-8"))) {
                Document document = saxReader.read(is);
                //　验证xml数据正确性
                Node node = document.selectSingleNode("/error");
                if (node != null) {
                    throw new RuntimeException(((Element) node).getData().toString());
                }
                // 解析xml
                Element root = (Element) document.selectSingleNode("/pageList"); // pageList
                @SuppressWarnings("unchecked")
                List<Element> pageElementList = root.elements();
                if (pageElementList != null && !pageElementList.isEmpty()) {
                    // 初始化操作动作
                    long operate = 0;
                    for (Element element : pageElementList) {
                        if ("operation".equals(element.getName())) {
                            operate = Long.parseLong(element.getData().toString());
                        } else if ("pageContent".equals(element.getName())) {
                            // 解析页面元素
                            @SuppressWarnings("unchecked")
                            List<Element> page = element.elements();
                            if (page != null && !page.isEmpty()) {
                                PageContentVo vo = parseXmlToVo(page);
                                // 根据操作动作不同分别添加至不同的集合
                                if (operate == OperatorAction.ADD) {
                                    addList.add(vo);
                                } else if (operate == OperatorAction.MODIFY) {
                                    updateList.add(vo);
                                } else if (operate == OperatorAction.DELETE) {
                                    delList.add(vo);
                                }
                            }
                            operate = 0;
                        }
                    }
                }
                // 三种操作类型数据放入map
                resultMap.put(OperatorAction.ADD, addList);
                resultMap.put(OperatorAction.MODIFY, updateList);
                resultMap.put(OperatorAction.DELETE, delList);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                throw e;
            }
        }

        return resultMap;
    }

    /**
     * 解析xml数据为pageVo对象
     *
     * @param page 元素
     * @return PageContentVo pageVo对象
     */
    private static PageContentVo parseXmlToVo(List<Element> page) {
        PageContentVo vo = new PageContentVo();
        for (Element el : page) {
            if ("id".equals(el.getName())) {
                vo.setId(Long.parseLong(el.getData().toString()));
            } else if ("menuName".equals(el.getName())) {
                vo.setMenuName(el.getData().toString());
            } else if ("pageMarkup".equals(el.getName())) {
                vo.setPageMarkup(Long.parseLong(el.getData().toString()));
            } else if ("parentId".equals(el.getName())) {
                vo.setParentId(Long.parseLong(el.getData().toString()));
            } else if ("linkUrl".equals(el.getName())) {
                vo.setLinkUrl(el.getData().toString());
            } else if ("displaySort".equals(el.getName())) {
                vo.setDisplaySort(Long.parseLong(el.getData().toString()));
            } else if ("whetherDisplay".equals(el.getName())) {
                vo.setWhetherDisplay(Long.parseLong(el.getData().toString()));
            } else if ("whetherNewWindow".equals(el.getName())) {
                vo.setWhetherNewWindow(Long.parseLong(el.getData().toString()));
            } else if ("showImageUrl".equals(el.getName())) {
                vo.setShowImageUrl(el.getData().toString());
            } else if ("remark".equals(el.getName())) {
                vo.setRemark(el.getData().toString());
            } else if ("realRemark".equals(el.getName())) {
                vo.setRealRemark(el.getData().toString());
            }
        }
        return vo;
    }

    /**
     * 通过页面标示得到页面对象
     *
     * @param pageMarkup 页面标识
     */
    public static PageContentVo getPageByMarkup(long pageMarkup, boolean isPreview) {
        PageContentVo result = null;
        if (isPreview) {
            if (pageContentList4Preview != null) {
                for (PageContentVo vo : pageContentList4Preview) {
                    if (vo.getPageMarkup() == pageMarkup) {
                        result = getPageById(vo.getId(), true);
                    }
                }
            }
        } else {
            if (pageContentList != null) {
                for (PageContentVo vo : pageContentList) {
                    if (vo.getPageMarkup() == pageMarkup) {
                        result = getPageById(vo.getId(), false);
                    }
                }
            }
        }

        return result;
    }

    /**
     * 通过页面标示得到页面对象
     *
     * @param pageMarkup 页面标识
     */
    public static PageContentVo getDeepPageByMarkup(long pageMarkup, boolean isPreview) {
        PageContentVo result = null;
        if (isPreview) {
            if (pageContentList4Preview != null) {
                for (PageContentVo vo : pageContentList4Preview) {
                    if (vo.getPageMarkup() == pageMarkup) {
                        result = getDeepPageById(vo.getId(), true);
                    }
                }
            }
        } else {
            if (pageContentList != null) {
                for (PageContentVo vo : pageContentList) {
                    if (vo.getPageMarkup() == pageMarkup) {
                        result = getDeepPageById(vo.getId(), false);
                    }
                }
            }
        }

        return result;
    }

    /**
     * 通过id得到页面对象
     *
     * @param id 主键
     * @return PageContentVo 页面对象
     */
    public static PageContentVo getPageById(long id, boolean isPreview) {
        if (isPreview) {
            if (pageContentList4Preview != null) {
                for (PageContentVo vo : pageContentList4Preview) {
                    if (vo.getId() == id && vo.getWhetherDisplay() == WhetherState.YES) {
                        // 获取子节点
                        List<PageContentVo> subList = getSubPageListById(id, true);
                        vo.setSubList(subList);
                        return vo;
                    }
                }
            }
        } else {
            if (pageContentList != null) {
                for (PageContentVo vo : pageContentList) {
                    if (vo.getId() == id && vo.getWhetherDisplay() == WhetherState.YES) {
                        // 获取子节点
                        List<PageContentVo> subList = getSubPageListById(id, false);
                        vo.setSubList(subList);
                        return vo;
                    }
                }
            }
        }

        return null;
    }

    /**
     * 通过id得到页面对象(递归调用，包含所有子节点，谨慎使用)
     *
     * @param id 主键
     * @return PageContentVo 页面对象
     */
    public static PageContentVo getDeepPageById(long id, boolean isPreview) {
        if (isPreview) {
            if (pageContentList4Preview != null) {
                for (PageContentVo vo : pageContentList4Preview) {
                    if (vo.getId() == id) {
                        // 获取子节点
                        List<PageContentVo> subList = getDeepSubPageListById(id, true);
                        vo.setSubList(subList);
                        return vo;
                    }
                }
            }
        } else {
            if (pageContentList != null) {
                for (PageContentVo vo : pageContentList) {
                    if (vo.getId() == id) {
                        // 获取子节点
                        List<PageContentVo> subList = getDeepSubPageListById(id, false);
                        vo.setSubList(subList);
                        return vo;
                    }
                }
            }
        }

        return null;
    }

    /**
     * 通过id得到子页面对象列表
     *
     * @param id 组件
     * @return List<PageContentVo> 子页面对象列表
     */
    public static List<PageContentVo> getSubPageListById(long id, boolean isPreview) {
        List<PageContentVo> resultList = new ArrayList<>();
        if (isPreview) {
            if (pageContentList4Preview != null) {
                for (PageContentVo vo : pageContentList4Preview) {
                    if (vo.getParentId() == id && vo.getWhetherDisplay() == WhetherState.YES) {
                        resultList.add(vo);
                    }
                }
            }
        } else {
            if (pageContentList != null) {
                for (PageContentVo vo : pageContentList) {
                    if (vo.getParentId() == id && vo.getWhetherDisplay() == WhetherState.YES) {
                        resultList.add(vo);
                    }
                }
            }
        }
        // 排序
        Collections.sort(resultList, PageContentVo.COMPARATOR);

        return resultList;
    }

    /**
     * 通过id得到子页面对象列表(递归包含所有的子页面对象列表)
     *
     * @param id 主键
     * @return List<PageContentVo> 子页面对象列表
     */
    public static List<PageContentVo> getDeepSubPageListById(long id, boolean isPreview) {
        List<PageContentVo> resultList = new ArrayList<>();
        if (isPreview) {
            if (pageContentList4Preview != null) {
                for (PageContentVo vo : pageContentList4Preview) {
                    if (vo.getParentId() == id && vo.getWhetherDisplay() == WhetherState.YES) {
                        List<PageContentVo> tmpList = getSubPageListById(vo.getId(), true);
                        // 递归查询
                        if (tmpList != null) {
                            tmpList = getDeepSubPageListById(vo.getId(), true);
                            vo.setSubList(tmpList);
                        }
                        resultList.add(vo);
                    }
                }
            }
        } else {
            if (pageContentList != null) {
                for (PageContentVo vo : pageContentList) {
                    if (vo.getParentId() == id && vo.getWhetherDisplay() == WhetherState.YES) {
                        List<PageContentVo> tmpList = getSubPageListById(vo.getId(), false);
                        // 递归查询
                        if (tmpList != null) {
                            tmpList = getDeepSubPageListById(vo.getId(), false);
                            vo.setSubList(tmpList);
                        }
                        resultList.add(vo);
                    }
                }
            }
        }
        // 排序
        Collections.sort(resultList, PageContentVo.COMPARATOR);

        return resultList;
    }

    /**
     * 通过页面标示得到子页面对象列表
     *
     * @param pageMarkup 页面标示
     * @return List<PageContentVo> 子页面对象列表
     */
    public static List<PageContentVo> getSubPageListByMarkup(long pageMarkup, boolean isPreview) {
        List<PageContentVo> resultList = new ArrayList<>();
        PageContentVo pageVo = getPageByMarkup(pageMarkup, isPreview);
        if (pageVo != null) {
            resultList = getSubPageListById(pageVo.getId(), isPreview);
        }

        return resultList;
    }

    /**
     * 通过页面标示得到子页面对象列表(递归包含所有的子页面对象列表)
     *
     * @param pageMarkup 页面标识
     * @param isPreview  是否预览
     * @return 列表
     */
    public static List<PageContentVo> getDeepSubPageListByMarkup(long pageMarkup, boolean isPreview) {
        List<PageContentVo> resultList = new ArrayList<>();
        PageContentVo pageVo = getPageByMarkup(pageMarkup, isPreview);
        if (pageVo != null) {
            resultList = getDeepSubPageListById(pageVo.getId(), isPreview);
        }
        return resultList;
    }

    /**
     * 添加缓存数据
     *
     * @param list 类表
     */
    public static void addList(List<PageContentVo> list, boolean isPreview) {
        if (!list.isEmpty()) {
            //　清除重复数据
            delList(list, isPreview);

            // 添加预览缓存数据
            if (isPreview) {
                if (pageContentList4Preview != null) {
                    pageContentList4Preview.addAll(list);
                }
            } else if (pageContentList != null) {
                pageContentList.addAll(list);
            }
        }
    }

    /**
     * 删除缓存数据
     *
     * @param list 列表
     */
    public static void delList(List<PageContentVo> list, boolean isPreview) {
        if (!list.isEmpty()) {
            // 删除预览缓存数据
            if (isPreview) {
                if (pageContentList4Preview != null) {
                    for (PageContentVo vo : list) {
                        removeVo(vo, pageContentList4Preview);
                    }
                }
            } else if (pageContentList != null) {
                for (PageContentVo vo : list) {
                    removeVo(vo, pageContentList);
                }
            }
        }
    }

    /**
     * 根据元素删除list中的数据
     *
     * @param vo               页面内容对象
     * @param pageContentList2 页面内容对象列表
     */
    private static void removeVo(PageContentVo vo, List<PageContentVo> pageContentList2) {
        for (ListIterator<PageContentVo> it = pageContentList2.listIterator(); it.hasNext(); ) {
            PageContentVo tempVo = it.next();
            if (vo.getId() == tempVo.getId()) {
                it.remove();
            }
        }
    }

    /**
     * 更改缓存数据
     *
     * @param list 页面内容对象
     */
    public static void updateList(List<PageContentVo> list, boolean isPreview) {
        addList(list, isPreview);
    }

    /**
     * 刷新缓存
     *
     * @param data 数据
     * @throws Exception
     */
    public static void flushContent(String data, boolean isPreview) throws Exception {
        Map<Long, List<PageContentVo>> map = PageContentHelper.parseCacheXml(data);
        if (map.get(OperatorAction.ADD) != null) {
            PageContentHelper.addList(map.get(OperatorAction.ADD), isPreview);
        }
        if (map.get(OperatorAction.MODIFY) != null) {
            PageContentHelper.updateList(map.get(OperatorAction.MODIFY), isPreview);
        }
        if (map.get(OperatorAction.DELETE) != null) {
            PageContentHelper.delList(map.get(OperatorAction.DELETE), isPreview);
        }
    }

    /**
     * 更新预览缓存
     *
     * @param data 页面更新数据
     * @throws Exception
     */
    public static void flushPreviewContent(String data) throws Exception {
        // 拷贝正式缓存一份至预览缓存
        copyPreview();
        // 更新预览缓存
        flushContent(data, true);
    }

    /**
     * 　拷贝正式缓存一份至预览缓存
     */
    private static void copyPreview() {
        // 情况预览缓存
        pageContentList4Preview.clear();
        // 拷贝
        pageContentList4Preview.addAll(pageContentList);
    }

    /**
     * 根据数据更新对应缓存
     *
     * @param data      数据
     * @param isPreview 是否预览缓存
     * @throws Exception
     */
    public static void flushVersion(String data, boolean isPreview) throws Exception {
        init(data, isPreview);
    }

    /** 销毁预览缓存 */
    public static void flushDestroyPreview() {
        pageContentList4Preview.clear();
    }

    /** 推送预览内容 */
    public static ResultVo pushPreviewContent(String url) {
        return pushContent(url);

    }

    /** 推送页面缓存内容 */
    public static ResultVo pushContent(String url) {
        // 获取更改的缓存数据
        String xml = getPushData();
        return pushData(url, xml);
    }

    /**
     * 推送清空预览缓存数据
     *
     * @param url 地址
     */
    public static ResultVo pushDestroyPreview(String url) {
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vertime = DateUtils.formatDateTimestamp(new Date());

        Map<String, String> postData = new HashMap<>();
        postData.put("vertime", vertime);
        postData.put("vercode", MD5.md5(connpwd + vertime));

        HttpSocket socket = new HttpSocket();

        socket.setUrl(url);
        socket.setPostData(postData);

        try {
            socket.doPost();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        String result = socket.getResponseData();

        return ResultVo.parse(result);
    }

    /**
     * 推送版本数据
     *
     * @param url     地址
     * @param xmlData xml
     */
    public static ResultVo pushVersion(String url, String xmlData) {
        return pushData(url, xmlData);
    }

    /**
     * 推送数据
     *
     * @param url     推送地址
     * @param xmlData 推送数据
     * @return ResultVo 结果对象
     */
    public static ResultVo pushData(String url, String xmlData) {
        String result = null;
        try (InputStream is = new ByteArrayInputStream(xmlData.getBytes())) {
            HttpSocket socket = new HttpSocket();
            try {
                socket.setUrl(url);
                socket.addAttachment("file", "test", "text/plain", is);
                socket.doPostMultipart();

            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            result = socket.getResponseData();
            LOGGER.info("推送返回数据：" + result);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return ResultVo.parse(result);
    }
}
