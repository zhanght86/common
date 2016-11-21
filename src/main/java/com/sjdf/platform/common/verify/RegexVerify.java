package com.sjdf.platform.common.verify;

import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.MatchPattern;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import java.util.HashSet;
import java.util.Set;

/**
 * 2012-10-26 下午2:31:25
 * 常用正则表达式 验证
 *
 * @author frank
 */
public abstract class RegexVerify {
    /**
     * 检查 email输入是否正确
     *
     * @param value  需要被检查的email字符串
     * @param length 定义传入的email字符串的最大长度
     * @return true：通过验证;false：未通过验证
     */
    public static boolean checkEmail(String value, int length) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.E_MAIL)) && value.length() <= length;
    }

    /**
     * 检查 email输入是否正确
     *
     * @param value 需要被检查的email字符串
     * @return true：通过验证;false：未通过验证
     */
    public static boolean checkEmail(String value) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.E_MAIL));
    }

    /**
     * 检查办公电话（座机号码）输入是否正确 正确格式 012-87654321、0123-87654321、0123－7654321
     *
     * @param value 需要被检查的办公电话（座机号码）字符串
     * @return true：通过验证;false：未通过验证 true：通过验证;false：未通过验证
     */
    public static boolean checkTel(String value) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.LANDLINE_NUMBER));
    }

    /**
     * 检查手机号码输入 是否正确
     *
     * @param value 需要被检查的手机号码
     * @return true：通过验证;false：未通过验证 true：通过验证;false：未通过验证
     */
    public static boolean checkMobile(String value) {
        return PlatformUtils.hasText(value) &&
                (value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.MOBILE_PHONE_NUMBER_FOR_HONGKONG)) ||
                        value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.MOBILE_PHONE_NUMBER)));
    }

    /**
     * 检查中文名输 入是否正确
     *
     * @param value  需要被验证的中文
     * @param length 定义传入的email字符串的最大长度
     * @return true：通过验证;false：未通过验证 true：通过验证;false：未通过验证
     */
    public static boolean checkChineseName(String value, int length) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.CHINESE_NAME)) && value.length() <= length;
    }

    /**
     * 检查中文名输 入是否正确
     *
     * @param value 需要被验证的中文
     * @return true：通过验证;false：未通过验证 true：通过验证;false：未通过验证
     */
    public static boolean checkChineseName(String value) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.CHINESE_NAME));
    }

    /**
     * 检查输 入是否包含中文信息
     *
     * @param value 需要被验证的输入
     * @return true：包含中文信息;false：不包含中文信息
     */
    public static boolean isContainsChinese(String value) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.IS_CONTAINS_CHINESE));
    }

    /**
     * 检查英文名输 入是否正确
     *
     * @param value  需要被验证的英文
     * @param length 定义传入的字符串的最大长度
     * @return true：通过验证;false：未通过验证 true：通过验证;false：未通过验证
     */
    public static boolean checkEnglishName(String value, int length) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.ENGLISH_NAME)) && value.length() <= length;
    }

    /**
     * 检查英文名输 入是否正确
     *
     * @param value 需要被验证的英文
     * @return true：通过验证;false：未通过验证 true：通过验证;false：未通过验证
     */
    public static boolean checkEnglishName(String value) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.ENGLISH_NAME));
    }

    /**
     * 检查英文名输 入是否正确（允许出现多个大写字母：YaoMing）
     *
     * @param value 需要被验证的英文
     * @return true：通过验证;false：未通过验证 true：通过验证;false：未通过验证
     */
    public static boolean checkEnglishName2(String value, int length) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.ENGLISH_NAME2)) && value.length() <= length;
    }

    /**
     * 检查字符串 中首尾空行或空格
     *
     * @param value 需要被检查的字符串
     * @return true：通过验证;false：未通过验证
     */
    public static boolean checkBlank(String value) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.BLANK_BEGIN_END));
    }

    /**
     * 检查字符串中是否含有HTML标签
     *
     * @param value 需要被检查的字符串
     * @return true：有HTML标签;false：没有HTML标签
     */
    public static boolean checkHtmlTag(String value) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.HTML_TAGS));
    }

    /**
     * 检查URL是 否合法
     *
     * @param url 需要被检查的URL
     * @return true：通过验证;false：未通过验证
     */
    public static boolean checkURL(String url) {
        return PlatformUtils.hasText(url) && url.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.URL));
    }

    /**
     * 检查IP是否 合法
     *
     * @param ip 需要被验证的IP
     * @return true：通过验证;false：未通过验证
     */
    public static boolean checkIP(String ip) {
        return PlatformUtils.hasText(ip) && ip.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.IP));
    }

    /**
     * 检查ID是否 合法，开头必须是大小写字母，其他位可以有大小写字符、数字、下划线
     *
     * @param id 需要被检查的ID
     * @return true：通过验证;false：未通过验证
     */
    public static boolean checkID(String id) {
        return PlatformUtils.hasText(id) && id.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.ID));
    }

    /**
     * 检查QQ是否 合法，必须是数字，且首位不能为0，最长15位
     *
     * @param qq 需要被检查的QQ
     * @return true：通过验证;false：未通过验证
     */
    public static boolean checkQQ(String qq) {
        return PlatformUtils.hasText(qq) && qq.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.QQ));
    }

    /**
     * 检查邮编是否 合法
     *
     * @param postCode 需要被验证的邮政编码
     * @return true：通过验证;false：未通过验证
     */
    public static boolean checkPostCode(String postCode) {
        return PlatformUtils.hasText(postCode) && postCode.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.POST_CODE));
    }

    /**
     * 检查身份证是 否合法,15位或18位
     *
     * @param idCardNum 需要被验证的身份证号码
     * @return true：通过验证;false：未通过验证
     */
    public static boolean checkIDCard(String idCardNum) {
        return PlatformUtils.hasText(idCardNum) && idCardNum.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.ID_CARD));
    }

    /**
     * 检查输入是否 超出规定长度
     *
     * @param value  需要被检查的字符串
     * @param length 定义需要验证的长度
     * @return true：通过验证;false：未通过验证
     */
    public static boolean checkLength(String value, int length) {
        return ((value == null || "".equals(value.trim())) ? 0 : value.length()) <= length;
    }

    /**
     * 验证字符串是否为null或""
     *
     * @param value 需要被验证的字符串
     * @return true：通过验证;false：未通过验证
     */
    public static boolean checkNull(String value) {
        return value == null || "".equals(value.trim());
    }

    /**
     * 验证是否为数字
     */
    public static boolean isNumeric(String str) {
        if (!PlatformUtils.hasText(str)) {
            return false;
        } else {
            for (int i = str.length(); --i >= 0; ) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 验证通信地址：请勿用符号表示，如1-2-3等，需用文字叙述方式填写
     *
     * @param address 通信地址
     * @return boolean 是否符号要求
     */
    public static boolean checkLetterAddress(String address) {
        return PlatformUtils.hasText(address) && address.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.LETTET_ADDRESS));
    }

    /**
     * 验证是否包含特殊字符
     *
     * @param checkValue 被验证字符串
     * @return String 第一个不符合
     */
    public static String checkSpecialCharacter(String checkValue) {
        Set<String> set = new HashSet<>();
        String regEx = ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.SPECIAL_CHARACTER);
        for (int i = 0, size = regEx.length(); i < size; i++) {
            char ch = regEx.charAt(i);
            if (checkValue.indexOf(ch) >= 0) {
                set.add(ch + "");
            }
        }

        StringBuilder info = new StringBuilder();
        for (String str : set) {
            info.append(str);
        }

        return info.toString();
    }

    /**
     * 验证密码：6-20位必须包含数字和字母，允许包含下划线的字符串
     *
     * @param password 密码
     * @return boolean 是否符号要求
     */
    public static boolean checkPassword(String password) {
        return PlatformUtils.hasText(password) && password.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.PASSWORD));
    }

    /**
     * 验证单位名称：只能是数字，字母，中文及.·()（）《》 、
     *
     * @param value 单位名称
     * @return boolean 是否符号要求
     */
    public static boolean checkRegDwmc(String value) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.REG_DWMC));
    }

    /**
     * 验证英文字符：只能是字母
     *
     * @param value 待验证字符
     * @return boolean 是否符号要求
     */
    public static boolean checkCharacter(String value) {
        return PlatformUtils.hasText(value) && value.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.CHARACTER));
    }
}
