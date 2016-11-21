package com.sjdf.platform.check.service.impl;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.check.bean.ConditionVoBean;
import com.sjdf.platform.check.bean.DisplayInfoBean;
import com.sjdf.platform.check.service.DisplayInfoService;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.common.vo.SearchVo;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 2012-8-24 下午2:26:21
 * 【展示信息】业务接口实现类
 *
 * @author frank
 */
@Service
public class DisplayInfoServiceImpl extends BaseServiceImpl implements DisplayInfoService {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(DisplayInfoServiceImpl.class);

    @Override
    public List<DisplayInfoBean> findDisplayInfoList(SearchVo searchVo, Page page) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(DisplayInfoBean.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (searchVo != null) {
            //ID
            if (Tools.longIsNotNUllAndZero(searchVo.getDisplayInfoId())) {
                criteria.add(Restrictions.eq("id", searchVo.getDisplayInfoId()));
            }
            //信息类别
            if (Tools.longIsNotNUllAndZero(searchVo.getDisplayInfoInfoType())) {
                criteria.add(Restrictions.eq("infoType", searchVo.getDisplayInfoInfoType()));
            }
            //内容
            if (Tools.stringIsNotNullAndEmpty(searchVo.getDisplayInfoContent())) {
                criteria.add(Restrictions.like("content", "%" + searchVo.getDisplayInfoContent() + "%"));
            }

            //更新时间(开始)和结束都有
            if (Tools.stringIsNotNullAndEmpty(searchVo.getBeginOfupdateDate()) && Tools.stringIsNotNullAndEmpty(searchVo.getEndOfUpdateDate())) {
                criteria.add(Restrictions.between("updateTime", simpleDateFormat.parseObject(searchVo.getBeginOfupdateDate()), simpleDateFormat.parseObject(searchVo.getEndOfUpdateDate())));
            }
            //更新时间（开始）
            if (Tools.stringIsNotNullAndEmpty(searchVo.getBeginOfupdateDate()) && !Tools.stringIsNotNullAndEmpty(searchVo.getEndOfUpdateDate())) {
                criteria.add(Restrictions.ge("updateTime", simpleDateFormat.parseObject(searchVo.getBeginOfupdateDate())));
            }
            //更新时间（结束）
            if (!Tools.stringIsNotNullAndEmpty(searchVo.getBeginOfupdateDate()) && Tools.stringIsNotNullAndEmpty(searchVo.getEndOfUpdateDate())) {
                criteria.add(Restrictions.le("updateTime", simpleDateFormat.parseObject(searchVo.getEndOfUpdateDate())));
            }
            // 展示信息--所属类别
            if (StringUtils.hasText(searchVo.getDisplayInfoOwnerType())) {
                //以【分号空格】分裂条件【所属类别条件】
                String[] semicolonSpaceSplit = searchVo.getDisplayInfoOwnerType().split(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.SEMICOLON_SPACE));
                for (String semicolonString : semicolonSpaceSplit) {
                    //以【冒号空格】分裂条件【所属类别条件】
                    String[] colonSplit = semicolonString.split(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COLON));
                    //排除colonSplit 只有一个字符串的情况
                    String title = colonSplit[0];
                    if (colonSplit.length > 1) {
                        Criterion criterion = null;
                        //组装条件
                        String[] contents = colonSplit[1].split(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COMMA));
                        for (int i = 0, size = contents.length; i < size; i++) {
                            if (i == 0) {
                                criterion = Restrictions.like("ownerType", title + ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COLON) + contents[i], MatchMode.ANYWHERE);
                            } else {
                                criterion = Restrictions.or(Restrictions.like("ownerType", title + ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COLON) + contents[i], MatchMode.ANYWHERE), criterion);
                            }
                        }
                        criteria = criteria.add(criterion);
                    }
                }
            }
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return baseDao.listByCriteria(criteria, page, Order.desc("updateTime"));
    }

    @Override
    public String saveOrUpdateDisplayInfo(DisplayInfoBean displayInfoBean, String user) throws Exception {
        try {
            //更新【展示信息】
            if (Tools.longIsNotNUllAndZero(displayInfoBean.getId())) {
                DisplayInfoBean queryDisplayInfoBean = baseDao.get(DisplayInfoBean.class, displayInfoBean.getId());
                queryDisplayInfoBean.setUpdateUser(user);
                queryDisplayInfoBean.setInfoType(displayInfoBean.getInfoType());
                queryDisplayInfoBean.setOwnerType(displayInfoBean.getOwnerType());
                queryDisplayInfoBean.setContent(displayInfoBean.getContent());
                queryDisplayInfoBean.setUpdateTime(displayInfoBean.getUpdateTime());
                baseDao.update(queryDisplayInfoBean);
                loggerNormalToDB(String.valueOf(displayInfoBean.getId()), user + "更新ID：" + displayInfoBean.getId() + "的展示信息。", OperatorAction.MODIFY);
                //保存新的【展示信息】
            } else {
                displayInfoBean.setCreateUser(user);
                displayInfoBean.setUpdateUser(user);
                baseDao.save(displayInfoBean);
                loggerNormalToDB(String.valueOf(displayInfoBean.getId()), user + "添加ID：" + displayInfoBean.getId() + "的展示信息。", OperatorAction.ADD);
            }
            return "Status:1";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            loggerErrorToDB(user + "保存或更新展示信息报错。", OperatorAction.OTHER, e);
            return "Status:-1";
        }
    }

    /**
     * 记录正常日志
     *
     * @param resourceId     资源ID
     * @param operateContent 操作内容
     * @param operatorAction 操作动作（添加，修改，删除，其他）
     */
    private void loggerNormalToDB(String resourceId, String operateContent, long operatorAction) {
        LogBean logBean = new LogBean();
        logBean.setOperatorAction(operatorAction);
        logBean.setSubsystemType(SubsystemType.OTHER);
        logBean.setFunctionClass(FunctionClass.COMMON_FOUNTION);
        logBean.setFunctionType(FunctionType.OTHER);
        logBean.setResourceId(resourceId);
        logBean.setOperationalContent(operateContent);
        LOGGER.infoDB(logBean);
    }

    /**
     * 记录失败日志
     *
     * @param operateContent 操作内容
     * @param operatorAction 操作动作
     * @param parameterE     Error 信息
     */
    private void loggerErrorToDB(String operateContent, long operatorAction, Throwable parameterE) {
        LogBean logBean = new LogBean();
        logBean.setErrorInfo(parameterE);
        logBean.setOperatorAction(operatorAction);
        logBean.setSubsystemType(SubsystemType.OTHER);
        logBean.setFunctionClass(FunctionClass.COMMON_FOUNTION);
        logBean.setFunctionType(FunctionType.OTHER);
        logBean.setResourceId("1");
        logBean.setOperationalContent(operateContent);
        LOGGER.errorDB(logBean);
    }

    @Override
    public DisplayInfoBean getDisplayInfoById(Long id) throws Exception {
        return baseDao.get(DisplayInfoBean.class, id);
    }

    @Override
    public void deleteDisplayInfo(Long id) throws Exception {
        DisplayInfoBean displayInfoBean = baseDao.get(DisplayInfoBean.class, id);
        baseDao.delete(displayInfoBean);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<DisplayInfoBean> findDisplayInfoBeanListByCondition(DisplayInfoBean displayInfoBean) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("select * from p_display_info where 1 = 1 ");
        List conditionList = new ArrayList<>();
        if (displayInfoBean != null) {
            if (Tools.longIsNotNUllAndZero(displayInfoBean.getInfoType())) {
                hql.append(" and p_display_info.infoType = ?");
                conditionList.add(displayInfoBean.getInfoType());
            }
            if (Tools.stringIsNotNullAndEmpty(displayInfoBean.getContent())) {
                hql.append(" and p_display_info.content like ?");
                conditionList.add("%" + displayInfoBean.getContent() + "%");
            }
        }
        //封装条件
        Object[] params = new Object[conditionList.size()];
        if (params.length == 0) {
            params = null;
        } else {
            for (int i = 0; i < conditionList.size(); i++) {
                params[i] = conditionList.get(i);
            }
        }
        //根据条件查询【展示信息】List
        List<DisplayInfoBean> displayInfoBeanList = baseDao.sqlQuery(DisplayInfoBean.class, hql.toString(), params);
        return filterDisplayInfoBeanListForInterface(displayInfoBeanList, displayInfoBean.getConditionVoBeanList());
    }

    /**
     * @param displayInfoBeanList 根据基本条件查询的展示信息实体List
     * @param conditionVoBeanList 接口查询，【所属类别】条件封装实体Bean  List
     * @return 根据接口传入条件进行过滤展示信息 分两种过滤：
     * 1.有特殊标识的【展示信息】条件单独过滤
     * 2.无特殊标识的【展示信息】完全过滤
     */
    private List<DisplayInfoBean> filterDisplayInfoBeanListForInterface(List<DisplayInfoBean> displayInfoBeanList, List<ConditionVoBean> conditionVoBeanList) {
        List<DisplayInfoBean> matchedDisplayInfoBeanList = new ArrayList<>();
        //准确筛选【展示信息】
        for (DisplayInfoBean tempDisplayInfoBean : displayInfoBeanList) {
            //判断传入条件key是否是所属类别的子集
            int keyMatchCount = 0;
            int valueMatchCount = 0;
            for (ConditionVoBean conditionVoBean : conditionVoBeanList) {
                //针对特殊所属类别处理
                if (Tools.stringIsNotNullAndEmpty(conditionVoBean.getMarkValue()) && "special".equals(conditionVoBean.getMarkValue()) && tempDisplayInfoBean.getOwnerType().contains(conditionVoBean.getKey()) && tempDisplayInfoBean.getOwnerType().contains(conditionVoBean.getValue())) {
                    try {
                        String[] colonSplit = tempDisplayInfoBean.getOwnerType().split(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COLON));
                        if (colonSplit.length == CommonPlatformConstant.LENGTH_2) {
                            matchedDisplayInfoBeanList.add(tempDisplayInfoBean);
                            continue;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        LOGGER.error(e.getMessage(), e);
                    }
                }
                if (tempDisplayInfoBean.getOwnerType().contains(conditionVoBean.getKey())) {
                    if ("".equals(conditionVoBean.getValue())) {
                        valueMatchCount++;
                    } else {
                        //分号空格的方式截取【展示信息所属类别】
                        String[] semicolonSplit = tempDisplayInfoBean.getOwnerType().split(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.SEMICOLON_SPACE));
                        for (String ownerType : semicolonSplit) {
                            if (ownerType.contains(conditionVoBean.getKey())) {
                                //判断传入的具体值是否是所属类别的子集
                                if (ownerType.contains(conditionVoBean.getValue())) {
                                    valueMatchCount++;
                                }
                                break;
                            }
                        }
                    }
                    keyMatchCount++;
                }
            }
            //如果key和value分别比对的次数与传入的map的size相等，则将【展示信息】提出。
            if (keyMatchCount == conditionVoBeanList.size() && valueMatchCount == conditionVoBeanList.size()) {
                matchedDisplayInfoBeanList.add(tempDisplayInfoBean);
            }
        }
        return matchedDisplayInfoBeanList;
    }

    /**
     * @param displayInfoBean 条件【展示信息实体】
     * @return 比较后的结果，true：存在相同的【展示信息：所属类别】;false：不存在相同的【展示信息：所属类别】
     * 比较当前的【展示信息：所属类别,信息类别】是否在数据库中已存在
     */
    private boolean checkOwnerTypeUnique(DisplayInfoBean displayInfoBean) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DisplayInfoBean.class);
        if (Tools.longIsNotNUllAndZero(displayInfoBean.getInfoType()) && Tools.stringIsNotNullAndEmpty(displayInfoBean.getOwnerType())) {
            criteria.add(Restrictions.eq("infoType", displayInfoBean.getInfoType()));
            //分号空格方式分裂
            String[] splitSemicolonSpace = displayInfoBean.getOwnerType().split(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.SEMICOLON_SPACE));
            //循环
            for (String splitSemicolonSpaceStr : splitSemicolonSpace) {
                //冒号方式分裂循环个体
                String[] splitColon = splitSemicolonSpaceStr.split(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COLON));
                //取出第一个作为like语句查询
                criteria.add(Restrictions.like("ownerType", splitColon[0], MatchMode.ANYWHERE));
            }
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<DisplayInfoBean> displayInfoBeanList = baseDao.listByCriteria(criteria, Order.desc("updateTime"));
            for (DisplayInfoBean tempDisplayInfoBean : displayInfoBeanList) {
                //分号空格方式分裂DB查询后展示信息的数据
                String[] splitSemicolonSpaceOfDB = tempDisplayInfoBean.getOwnerType().split(ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.SEMICOLON_SPACE));
                //比较条件【展示信息：所属类别】与源【展示信息：所属类别】，分号空格方式分裂数组的长度是否一致
                if (splitSemicolonSpaceOfDB.length == splitSemicolonSpace.length) {
                    for (String semicolonStr : splitSemicolonSpace) {
                        if (!tempDisplayInfoBean.getOwnerType().contains(semicolonStr)) {
                            return false;
                        }
                    }
                    return !(Tools.longIsNotNUllAndZero(displayInfoBean.getId()) && Tools.compareLong(displayInfoBean.getId(), tempDisplayInfoBean.getId()));
                }
            }
            return false;
        } else {
            return false;
        }
    }
}
