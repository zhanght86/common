package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 身份证合法性验证
 */

public class IdCardCheck {
    /**
     * 15位身份证号码
     */
    private static final int SFZ_LENGTH_FIRST = 15;
    /**
     * 18位身份证号码(Second-generation)
     */
    private static final int SFZ_LENGTH_SECOND = 18;
    /**
     * 判断字符串是否全为数字的正则表达式；
     */
    private static final String NUM_REG = "^[0-9]*$";
    /**
     * 每位加权因子
     */
    private static int power[] = {
            CommonPlatformConstant.LENGTH_7,
            CommonPlatformConstant.LENGTH_9,
            CommonPlatformConstant.LENGTH_10,
            CommonPlatformConstant.LENGTH_5,
            CommonPlatformConstant.LENGTH_8,
            CommonPlatformConstant.LENGTH_4,
            CommonPlatformConstant.LENGTH_2,
            1,
            CommonPlatformConstant.LENGTH_6,
            CommonPlatformConstant.LENGTH_3,
            CommonPlatformConstant.LENGTH_7,
            CommonPlatformConstant.LENGTH_9,
            CommonPlatformConstant.LENGTH_10,
            CommonPlatformConstant.LENGTH_5,
            CommonPlatformConstant.LENGTH_8,
            CommonPlatformConstant.LENGTH_4,
            CommonPlatformConstant.LENGTH_2};


    /**
     * 验证所有的身份证的合法性
     *
     * @param idcardNum 身份证号码
     * @return 验证通过：true;不通过 false.
     */
    public static boolean isValidatedIdCard(String idcardNum) {
        String idcard = idcardNum;
        if (IdCardCheck.isEmpty(idcard)) {
            // 如果身份证号码为空
            return false;
        } else {
            if (idcard.length() == SFZ_LENGTH_FIRST) {
                // 如果是15位身份证，将把号码升级至18位后判断
                idcard = convertIdcarBy15bit(idcard);
                if (IdCardCheck.isEmpty(idcard)) {
                    // 如果身份证号码含有字母
                    return false;
                }
            }
            // 验证18位身份证
            return isValidate18Idcard(idcard);
        }

    }

    /**
     * 将15位的身份证转成18位身份证
     *
     * @param idcard
     * @return
     */
    private static String convertIdcarBy15bit(String idcard) {
        String idcard17;
        // 非15位身份证
        if (idcard.length() != SFZ_LENGTH_FIRST) {
            return null;
        }

        if (isDigital(idcard)) {
            // 获取出生年月日
            String birthday = idcard.substring(CommonPlatformConstant.LENGTH_6, CommonPlatformConstant.LENGTH_12);
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cday = Calendar.getInstance();
            cday.setTime(birthdate);
            String year = String.valueOf(cday.get(Calendar.YEAR));

            idcard17 = idcard.substring(0, CommonPlatformConstant.LENGTH_6) + year + idcard.substring(CommonPlatformConstant.LENGTH_8);

            char c[] = idcard17.toCharArray();
            String checkCode;

            int bit[] = converCharToInt(c);
            int sum17 = getPowerSum(bit);

            // 获取和值与11取模得到余数进行校验码
            checkCode = getCheckCodeBySum(sum17);
            // 获取不到校验位
            if (null == checkCode) {
                return null;
            }

            // 将前17位与第18位校验码拼接
            idcard17 += checkCode;
        } else { // 身份证包含数字
            return null;
        }
        return idcard17;
    }

    /**
     * <p>
     * 判断18位身份证的合法性
     * </p>
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * <p>
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
     * </p>
     * <p>
     * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码；
     * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码；
     * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
     * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
     * </p>
     * <p>
     * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2
     * </p>
     * <p>
     * 2.将这17位数字和系数相乘的结果相加。
     * </p>
     * <p>
     * 3.用加出来和除以11，看余数是多少？
     * </p>
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3
     * 2。
     * <p>
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     * </p>
     *
     * @param idcard
     * @return
     */
    private static boolean isValidate18Idcard(String idcard) {
        // 非18位为假
        if (idcard.length() != SFZ_LENGTH_SECOND) {
            return false;
        }
        // 获取前17位
        String idcard17 = idcard.substring(0, CommonPlatformConstant.LENGTH_17);
        // 获取第18位
        String idcard18Code = idcard.substring(CommonPlatformConstant.LENGTH_17, CommonPlatformConstant.LENGTH_18);
        char c[];
        String checkCode;
        // 是否都为数字
        if (isDigital(idcard17)) {
            c = idcard17.toCharArray();
        } else {
            return false;
        }

        int bit[] = converCharToInt(c);
        int sum17 = getPowerSum(bit);

        // 将和值与11取模得到余数进行校验码判断
        checkCode = getCheckCodeBySum(sum17);
        if (null == checkCode) {
            return false;
        }
        // 将身份证的第18位与算出来的校码进行匹配，不相等就为假(区分大小写)
        return idcard18Code.equals(checkCode);
    }

    /**
     * 字符串是否全为数字 验证
     *
     * @param str 字符串
     * @return 通过：true,不通过：false
     */
    public static boolean isDigital(String str) {
        return !IdCardCheck.isEmpty(str) && str.matches(NUM_REG);
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c
     * @return
     * @throws NumberFormatException
     */
    private static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit
     * @return
     */
    private static int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param sum17
     * @return 校验位
     */
    private static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % CommonPlatformConstant.LENGTH_11) {
            case CommonPlatformConstant.LENGTH_10:
                checkCode = "2";
                break;
            case CommonPlatformConstant.LENGTH_9:
                checkCode = "3";
                break;
            case CommonPlatformConstant.LENGTH_8:
                checkCode = "4";
                break;
            case CommonPlatformConstant.LENGTH_7:
                checkCode = "5";
                break;
            case CommonPlatformConstant.LENGTH_6:
                checkCode = "6";
                break;
            case CommonPlatformConstant.LENGTH_5:
                checkCode = "7";
                break;
            case CommonPlatformConstant.LENGTH_4:
                checkCode = "8";
                break;
            case CommonPlatformConstant.LENGTH_3:
                checkCode = "9";
                break;
            case CommonPlatformConstant.LENGTH_2:
                checkCode = "X";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
            default:
                break;
        }
        return checkCode;
    }


    /**
     * 如果所传入字符串为NULL或长度（空格不算长度）为空，则返回true，其他：false
     *
     * @param str 判断对象
     * @return 如果所传入字符串为NULL或长度（空格不算长度）为空，则返回true，其他：false
     */
    public static boolean isEmpty(String str) {
        return !PlatformUtils.hasText(str) || "null".equalsIgnoreCase(str);
    }
}
