package com.sjdf.platform.pageContent.helper;

import com.sjdf.platform.common.init.InitManager;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;
import com.sjdf.platform.pageContent.vo.PageContentVo;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面内容管理器
 *
 * @author laberwu
 */
public final class PageContentManager extends InitManager {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(PageContentManager.class);
    /** 系统类型环境变量 */
    private static final String SYSTEM_TYPE = "SYSTEM_TYPE";
    /** 单例实例 */
    private static PageContentManager instance = new PageContentManager();

    /**
     * 私有构造器
     */
    private PageContentManager() {
        super();
        // 初始化，获取当前系统对应启用中的页面数据
        String xmlData = this.getData();
        // 清空当前的数据
        this.destroyData();

        // 解析xml数据，初始化缓存
        try {
            PageContentHelper.init(xmlData, false);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /** 获取实例 */
    public static PageContentManager getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getXmlData() {
        String result = null;
        String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.COMMON_PAGE_CONTENT_INIT_URL);

        //　获取系统类型
        String sysType = System.getenv(SYSTEM_TYPE);
        if (sysType == null) {
            sysType = "1";
        }
        if (StringUtils.hasText(sysType)) {
            String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
            String vertime = DateUtils.formatDateTimestamp(new Date());

            Map<String, String> postData = new HashMap<>();
            postData.put("systemType", sysType);
            postData.put("vertime", vertime);
            postData.put("vercode", MD5.md5(sysType + connpwd + vertime));

            HttpSocket httpSocket = new HttpSocket();
            httpSocket.setUrl(url);
            httpSocket.setPostData(postData);
            LOGGER.info("url:" + url);

            try {
                httpSocket.doPost();
                result = httpSocket.getResponseData();
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            LOGGER.error("获取系统类型失败，请检查启动脚本是否设置SYSTEM_TYPE，如不需要使用页面内容管理系统，请忽略");
        }

        return result;
    }

    /** 通过页面标示得到页面对象 */
    public PageContentVo getPageByMarkup(long pageMarkup, boolean isPreview) {
        return PageContentHelper.getPageByMarkup(pageMarkup, isPreview);
    }

    /**
     * 通过id得到页面对象
     *
     * @return PageContentVo 页面对象
     */
    public PageContentVo getPageById(long id, boolean isPreview) {
        return PageContentHelper.getPageById(id, isPreview);
    }

    /** 通过页面标示得到页面对象(递归所有子节点，谨慎使用) */
    public PageContentVo getDeepPageByMarkup(long pageMarkup, boolean isPreview) {
        return PageContentHelper.getDeepPageByMarkup(pageMarkup, isPreview);
    }

    /**
     * 通过id得到页面对象(递归所有子节点，谨慎使用)
     *
     * @return PageContentVo 页面对象
     */
    public PageContentVo getDeepPageById(long id, boolean isPreview) {
        return PageContentHelper.getDeepPageById(id, isPreview);
    }

    /**
     * 通过id得到子页面对象列表
     *
     * @return List<PageContentVo> 子页面对象列表
     */
    public List<PageContentVo> getSubPageListById(long id, boolean isPreview) {
        return PageContentHelper.getSubPageListById(id, isPreview);
    }

    /**
     * 通过页面标示得到子页面对象列表
     *
     * @param pageMarkup 页面标示
     * @return List<PageContentVo> 子页面对象列表
     */
    public List<PageContentVo> getSubPageListByMarkup(long pageMarkup, boolean isPreview) {
        return PageContentHelper.getSubPageListByMarkup(pageMarkup, isPreview);
    }

    /**
     * 通过id得到子页面对象列表(递归包含所有的子页面对象列表:谨慎使用)
     *
     * @return List<PageContentVo> 子页面对象列表
     */
    public List<PageContentVo> getDeepSubPageListById(long id, boolean isPreview) {
        return PageContentHelper.getDeepSubPageListById(id, isPreview);
    }

    /**
     * 通过页面标示得到子页面对象列表(递归包含所有的子页面对象列表:谨慎使用)
     *
     * @param pageMarkup 页面标示
     * @return List<PageContentVo> 子页面对象列表
     */
    public List<PageContentVo> getDeepSubPageListByMarkup(long pageMarkup, boolean isPreview) {
        return PageContentHelper.getDeepSubPageListByMarkup(pageMarkup, isPreview);
    }
}
