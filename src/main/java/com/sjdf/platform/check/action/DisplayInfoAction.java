package com.sjdf.platform.check.action;

import com.sjdf.platform.check.bean.DisplayInfoBean;
import com.sjdf.platform.check.service.DisplayInfoService;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.common.vo.SearchVo;
import com.sjdf.platform.common.vo.SelectVo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.DisplayInfoType;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2012-9-3 下午4:13:32
 * 展示信息  Action，提供【展示信息】的增加，删除，修改，查询，接口调用
 *
 * @author frank
 */
public class DisplayInfoAction extends BaseAction {
    private static final long serialVersionUID = 5920447537580629123L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(DisplayInfoAction.class);

    /** 展示信息Service接口 */
    @Autowired
    private DisplayInfoService displayInfoService;
    /** 展示信息List数据 */
    private List<DisplayInfoBean> displayInfoBeanList;
    /** 展示信息实体 */
    private DisplayInfoBean displayInfoBean;
    /** 配置库数据 */
    private Dictionary dictionary;
    /** List页面，查询复选框VOList */
    private List<SelectVo> selectVoList;
    /** post页面，错误信息 */
    private String errorInfo;
    /** 搜索类 */
    private SearchVo searchVo;
    /** HttpSocket XML数据 */
    private String xml;
    /** 记录当前操作的User */
    private String user;

    /**
     * 根据条件搜索展示信息 条件包括：信息内容，信息类别，信息创建时间，信息更新时间，信息所属类别
     */
    public String displayInfoList() {
        try {
            //条件Bean
            DisplayInfoBean conditionDisplayInfoBean = new DisplayInfoBean();
            if (page != null) {
                page.setCurrentPage(1);
            }
            if (searchVo != null) {
                conditionDisplayInfoBean.setInfoType(searchVo.getDisplayInfoInfoType());//信息类别
                conditionDisplayInfoBean.setContent(searchVo.getDisplayInfoContent());//信息内容
                conditionDisplayInfoBean.setOwnerType(searchVo.getDisplayInfoOwnerType());//信息所属类别
                conditionDisplayInfoBean.setCreateTime(null);//信息创建时间
                conditionDisplayInfoBean.setUpdateTime(null);//信息更新时间
                displayInfoBean = conditionDisplayInfoBean;
            } else {
                searchVo = new SearchVo();
            }
            displayInfoBeanList = displayInfoService.findDisplayInfoList(searchVo, page);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return "list";
    }

    /**
     * 添加功能预览
     *
     * @return 跳转到添加页面
     */
    public String preAdd() {
        dictionary.setClazz(null);
        return "post";
    }

    /**
     * @return 跳转到【展示信息】列表页面
     * @throws Exception 保存或者更新【展示信息】
     */
    public String saveDisplayInfo() throws Exception {
        if (displayInfoBean.getInfoType() == 0L) {
            errorInfo = "请选择信息类别！";
            return "post";
        }
        if (!Tools.stringIsNotNullAndEmpty(displayInfoBean.getContent()) || "<p><br /></p>".equals(displayInfoBean.getContent())) {
            errorInfo = "请填写信息内容！";
            return "post";
        }
        displayInfoBean.setUpdateTime(DateUtils.getSysDateOfDate());
        String saveReturnStr = displayInfoService.saveOrUpdateDisplayInfo(displayInfoBean, user);
        if ("Status:0".equals(saveReturnStr)) {
            errorInfo = "所选所属类别已存在！不能添加，请重新选择！";
            return "post";
        }
        if ("Status:-1".equals(saveReturnStr)) {
            errorInfo = "保存失败，请联系管理员！";
            return "post";
        }
        return "flushList";
    }

    /**
     * @return 【展示信息】编辑页面
     * @throws Exception 根据【展示信息】ID 查询【展示信息】具体信息
     */
    public String getDisplayInfoById() throws Exception {
        displayInfoBean = displayInfoService.getDisplayInfoById(displayInfoBean.getId());
        return "post";
    }

    /**
     * @return 【展示信息】列表页面
     * @throws Exception 根据【展示信息】ID 删除展示信息
     */
    public String deleteDisplayInfo() throws Exception {
        displayInfoService.deleteDisplayInfo(displayInfoBean.getId());
        return "flushList";
    }

    /** 配置库的类型与形象名称列表 */
    public List<Map.Entry<String, String>> getClazzNameMap() {
        return DictionaryHelper.getSortedClazzName();
    }

    /** 展示信息类型列表 */
    @SuppressWarnings("unchecked")
    public Map<Long, String> getDisplayInfoTypeMap() {
        List<DisplayInfoType> displayInfoTypeList = DictionaryHelper.getDictionary(DisplayInfoType.class);
        Map<Long, String> displayInfoTypeMap = new HashMap<>();
        for (DisplayInfoType displayInfoType : displayInfoTypeList) {
            displayInfoTypeMap.put(displayInfoType.getAttr(), displayInfoType.getCnName());
        }
        return displayInfoTypeMap;
    }

    public DisplayInfoService getDisplayInfoService() {
        return displayInfoService;
    }

    public void setDisplayInfoService(DisplayInfoService displayInfoService) {
        this.displayInfoService = displayInfoService;
    }

    public List<DisplayInfoBean> getDisplayInfoBeanList() {
        return displayInfoBeanList;
    }

    public void setDisplayInfoBeanList(List<DisplayInfoBean> displayInfoBeanList) {
        this.displayInfoBeanList = displayInfoBeanList;
    }

    public DisplayInfoBean getDisplayInfoBean() {
        return displayInfoBean;
    }

    public void setDisplayInfoBean(DisplayInfoBean displayInfoBean) {
        this.displayInfoBean = displayInfoBean;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<SelectVo> getSelectVoList() {
        return selectVoList;
    }

    public void setSelectVoList(List<SelectVo> selectVoList) {
        this.selectVoList = selectVoList;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public SearchVo getSearchVo() {
        return searchVo;
    }

    public void setSearchVo(SearchVo searchVo) {
        this.searchVo = searchVo;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
