package com.sjdf.platform.dictionary.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.annotations.Permission;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.json.AjaxSupport;
import com.sjdf.platform.common.vo.FixedVo;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import com.sjdf.platform.dictionary.service.DictionaryService;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.LoggerManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.notify.service.NotifyTrackService;
import com.sjdf.platform.rbac.helper.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 配置文件管理
 * Create at 2012-04-05
 *
 * @author 王正伟
 */
public class DictionaryAction extends BaseAction {
    private static final long serialVersionUID = -2892551177648558228L;
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(DictionaryAction.class);
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private NotifyTrackService notifyTrackService;
    /** 配置库数据的对象的class全名称 */
    private String clazz;
    /** 配置库数据的属性 */
    private int attr;
    /** 配置库数据 */
    private Dictionary dictionary;
    /** 配置库数据列表 */
    private List<Dictionary> dictionaryList;
    /** 待引用的类型列表 */
    private List<Dictionary> refDictionaryList;

    /**
     * admin/common/DictionaryAction!list.action
     *
     * @return 配置文件列表
     */
    @Permission(code = "06010101", name = "配置库列表")
    public String list() {
        dictionaryList = dictionaryService.findByPage(dictionary, page);
        return "list";
    }

    /**
     * admin/common/DictionaryAction!preAdd.action
     *
     * @return 显示添加页面和初始化数据
     */
    @Permission(code = "06010102", name = "配置库添加页面")
    public String preAdd() {
        return "post";
    }

    /**
     * admin/common/DictionaryAction!add.action
     * 添加
     *
     * @return result
     */
    @Permission(code = "06010103", name = "配置库添加", supportedCode = "010102")
    public String add() {
        Message message = dictionaryService.add(dictionary);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            dictionary = (Dictionary) message.getReturnData();

            //通知
            notifyTrackService.saveDictionary(dictionary, OperatorAction.ADD);
            saveLog(OperatorAction.ADD, LogType.INFO, dictionary.getCnName(), message.getInfo(), null);

            // 更新缓存库
            dictionaryService.updateCache();
            printWrite(SUCCESS);
        }
        return NONE;
    }

    /**
     * admin/common/DictionaryAction!edit.action
     *
     * @return 编辑
     */
    @Permission(code = "06010104", name = "配置库编辑页面")
    public String edit() {
        dictionary = dictionaryService.get(Dictionary.class, idx);
        if (dictionary != null) {
            dictionary.setClazz(dictionary.getClass().getName());
            if (dictionary.getRef() != null) {
                dictionary.setRefClazz(dictionary.getRef().getClass().getName());
                refDictionaryList = dictionaryService.getDictionaryList(dictionary.getRefClazz());
            }
        }
        return "post";
    }

    /**
     * admin/common/DictionaryAction!update.action
     *
     * @return 更新
     */
    @Permission(code = "06010105", name = "配置库更新", supportedCode = "010104")
    public String update() {
        Message message = dictionaryService.update(dictionary, true);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            dictionary = (Dictionary) message.getReturnData();

            //通知
            notifyTrackService.saveDictionary(dictionary, OperatorAction.MODIFY);
            saveLog(OperatorAction.MODIFY, LogType.INFO, dictionary.getCnName(), message.getInfo(), null);

            // 更新缓存库
            dictionaryService.updateCache();
            printWrite(SUCCESS);
        }
        return NONE;
    }

    /**
     * admin/common/DictionaryAction!del.action
     *
     * @return 删除
     */
    @Permission(code = "06010106", name = "配置库删除")
    public String del() {
        Message message = dictionaryService.del(idx);
        if (message.hasErrorMessage()) {
            printWrite(getText(message));
        } else {
            dictionary = (Dictionary) message.getReturnData();

            //通知
            notifyTrackService.saveDictionary(dictionary, OperatorAction.DELETE);
            saveLog(OperatorAction.DELETE, LogType.INFO, dictionary.getCnName(), message.getInfo(), null);

            // 更新缓存库
            dictionaryService.updateCache();
            printWrite(SUCCESS);
        }
        return NONE;
    }

    /**
     * admin/common/DictionaryAction!refresh.action
     *
     * @return 刷新缓存
     */
    @Permission(code = "06010107", name = "配置库刷新缓存")
    public String refresh() {
        dictionaryService.updateCache();
        printWrite("刷新缓存成功!");
        return NONE;
    }

    /**
     * admin/common/DictionaryAction!getDictionaryListAjax.action
     * 获取指定类型下的所有配置
     */
    public String getDictionaryListAjax() {
        List<? extends Dictionary> list = ConfigManager.getInstance().getDictionary(clazz);
        AjaxSupport.sendSuccessTextOnly(null, list, "id", "attr", "name");
        return NONE;
    }

    /**
     * admin/common/DictionaryAction!getSortedClazzName.action
     * 获取排序后的所有配置库
     */
    public String getSortedClazzName() {
        List<FixedVo> list = ConfigManager.getSortedClazzName();
        AjaxSupport.sendSuccessTextOnly(null, list, "key", "value");
        return NONE;
    }

    /** 配置库的类型与形象名称列表 */
    public Map<String, String> getClazzNameMap() {
        return DictionaryHelper.getClassNameMap();
    }

    /** 配置库的类型与形象名称列表 */
    public List<Map.Entry<String, String>> getClazzNameList() {
        return DictionaryHelper.getSortedClazzName();
    }

    /** 语言类型列表 */
    public List<LangType> getLangTypeList() {
        return DictionaryHelper.getDictionary(LangType.class);
    }

    /** 系统类型列表 */
    public List<SystemType> getSystemTypeList() {
        return DictionaryHelper.getDictionary(SystemType.class);
    }

    /** 子系统类型列表 */
    public List<SubsystemType> getSubsystemTypeList() {
        return DictionaryHelper.getDictionary(SubsystemType.class);
    }

    /** 功能大分类列表 */
    public List<FunctionClass> getFunctionTypeList() {
        return DictionaryHelper.getDictionary(FunctionClass.class);
    }

    /** 操作动作列表 */
    public List<OperatorAction> getOperatorTypeList() {
        return DictionaryHelper.getDictionary(OperatorAction.class);
    }

    private void saveLog(long operatorAction, long logType, String resourceId, String operationalContent, String errorInfo) {
        LogBean log = LoggerManager.getInstance().createLog(UserHelper.getCurrentLoginUser().getUsername(), FunctionClass.COMMON_PLATFORM, FunctionType.COMMON_PLATFORM_DICTIONARY, SubsystemType.OTHER, operatorAction, logType, resourceId, operationalContent, errorInfo, 0L);
        if (logType == LogType.INFO) {
            LOGGER.infoDB(log);
        } else {
            LOGGER.errorDB(log);
        }
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getAttr() {
        return attr;
    }

    public void setAttr(int attr) {
        this.attr = attr;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<Dictionary> getDictionaryList() {
        return dictionaryList;
    }

    public List<Dictionary> getRefDictionaryList() {
        if (refDictionaryList == null) {
            refDictionaryList = Collections.emptyList();
        }
        return refDictionaryList;
    }
}
