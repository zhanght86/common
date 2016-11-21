package com.sjdf.platform.common.utils;

import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * User: ketqi Date: 2013-04-26 16:00
 * 中文转拼音
 */
public abstract class PinyinManager {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(PinyinManager.class);

    /**
     * @param src 汉字字符串
     * @return String
     * 将汉字转换为全拼
     */
    public static String getPinYin(String src) {
        return getPinYin(src, false);
    }

    /**
     * @param src       汉字字符串
     * @param withSpace 是否需要包含空格
     * @return String
     * 将汉字转换为全拼
     */
    public static String getPinYin(String src, boolean withSpace) {
        return getPinYin(src, withSpace, false);
    }

    /**
     * @param src 汉字字符串
     * @return String
     * 拼音名字
     */
    public static String getName(String src) {
        return getPinYin(src, false, true);
    }

    /**
     * @param src       汉字字符串
     * @param withSpace 是否需要包含空格
     * @param isName    是否是名字
     * @return String
     * 将汉字转换为全拼
     */
    public static String getPinYin(String src, boolean withSpace, boolean isName) {
        if (src == null || "".equals(src)) {
            return "";
        }

        char[] t1 = src.toCharArray();
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder result = new StringBuilder();
        try {
            boolean hasWithSpace = false;
            for (char ch : t1) {
                // 判断能否为汉字字符
                boolean isPinyin = Character.toString(ch).matches("[\\u4E00-\\u9FA5]+");
                if (isPinyin) {
                    String[] t2 = PinyinHelper.toHanyuPinyinStringArray(ch, t3);// 将汉字的几种全拼都存到t2数组中
                    result.append(t2[0]);// 取出该汉字全拼的第一种读音并连接到字符串t4后
                } else {
                    // 如果不是汉字字符，间接取出字符并连接到字符串t4后
                    result.append(Character.toString(ch));
                }

                if (isPinyin) {
                    if (isName) {
                        if (!hasWithSpace) {
                            result.append(" ");
                            hasWithSpace = true;
                        }
                    } else if (withSpace) {
                        result.append(" ");
                    }
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return result.toString().trim();
    }
}
