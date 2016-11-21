package com.sjdf.platform.dictionary.bean.eiss.user;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

/**
 * User: ketqi
 * Date: 2015-10-22 10:24
 */
@Entity
@DiscriminatorValue("MEMBER_CONST_CONFIG")
@BeanName(name = "会员常量配置")
public class MemberConstConfig extends Dictionary {
    private static final long serialVersionUID = -1110377046031293821L;

    @BeanAttrInfo(value = "2015-10-21", cnName = "会员搜索开始时间")
    public static final long MEMBER_SEARCH_BEGIN_TIME = 1;

    /** 以超管编号并以逗号分隔,不允许重复出现 */
    @BeanAttrInfo(value = "75863485,38800465,32175182", cnName = "会员流转循环规则")
    public static final long MEMBER_TRANSFER_RULE = 2;

    @BeanAttrInfo(value = "", cnName = "会员黑名单")
    public static final long MEMBER_REGISTER_BLACK_LIST = 3;

    /** 获取下一个超管会员Id */
    public static long getNextAdminUserId(long adminUserId) {
        List<Long> adminUserIdList = PlatformUtils.parse2LongList(ConfigManager.getInstance().getValue(MemberConstConfig.class, MemberConstConfig.MEMBER_TRANSFER_RULE));
        if (adminUserIdList.isEmpty()) {
            return 0L;
        }

        int index = adminUserIdList.indexOf(adminUserId);

        //不存在或者最后一个则返回第一个
        if (index == -1 || index == adminUserIdList.size() - 1) {
            return adminUserIdList.get(0);
        }

        return adminUserIdList.get(index + 1);
    }

    /**
     * 判断指定名称是否在黑名单
     *
     * @param userName 名称
     * @return bool
     */
    public static boolean existMemberRegisterBlacklist(String userName) {
        String value = ConfigManager.getInstance().getValue(MemberConstConfig.class, MEMBER_REGISTER_BLACK_LIST);
        return PlatformUtils.hasText(value) && PlatformUtils.parse2StrList(value).contains(userName);
    }
}
