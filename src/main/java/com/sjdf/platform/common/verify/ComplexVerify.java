package com.sjdf.platform.common.verify;

import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.dictionary.bean.Keyword;
import com.sjdf.platform.dictionary.bean.MatchPattern;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

/**
 * 2012-10-29 下午3:34:10
 * 综合性验证
 *
 * @author frank
 */
public abstract class ComplexVerify {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(ComplexVerify.class);

    /**
     * 验证 负责人姓名
     * 负责人姓名不得填报“王先生”、“李小姐”、“个人”或者加带数字、特殊字符等明显不真实的姓名，
     * 中英文不得同时存在
     * 是中文时，至少2个以上
     *
     * @param reponsiblePersonName 需要被验证的【负责人姓名】
     * @return true：通过验证;false：未通过验证
     */
    public static boolean isRightReponsiblePersonName(String reponsiblePersonName) {
        try {
            //不为空和空字符串
            if (!Tools.stringIsNotNullAndEmpty(reponsiblePersonName)) {
                return false;
            }
            //限制仅为中文名或者英文名，至少2个以上
            if (!reponsiblePersonName.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.CN_OR_ZH_NAME))) {
                return false;
            }
            //负责人姓名不得填报“先生”
            if (reponsiblePersonName.contains(ConfigManager.getInstance().getValue(Keyword.class, Keyword.SIR))) {
                return false;
            }
            //负责人姓名不得填报“小姐”
            if (reponsiblePersonName.contains(ConfigManager.getInstance().getValue(Keyword.class, Keyword.MISS))) {
                return false;
            }
            //负责人姓名不得填报“个人”
            if (reponsiblePersonName.contains(ConfigManager.getInstance().getValue(Keyword.class, Keyword.PERSONAL))) {
                return false;
            }
            //负责人姓名不得填报“女士”
            if (reponsiblePersonName.contains(ConfigManager.getInstance().getValue(Keyword.class, Keyword.MADAM))) {
                return false;
            }
            return true;
        } catch (Exception th) {
            LOGGER.error(th.getMessage(), th);
            return false;
        }
    }

    /**
     * 公司名称
     */
    public static boolean isRightCompanyName(String companyName) {
        //不为空和空字符串
        if (!Tools.stringIsNotNullAndEmpty(companyName)) {
            return false;
        }
        //限制仅为中文名或者英文名，至少2个以上
        return companyName.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.COMPANY_NAME));
    }
}
