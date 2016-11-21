package com.sjdf.platform.common.verify;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.dictionary.bean.MatchPattern;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 2012-10-25 下午3:35:51
 * 执照类验证
 *
 * @author frank
 */
public abstract class LicenceVerify {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(LicenceVerify.class);

    // 身份证每位加权因子
    private static int[] power = {
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
     * 验证工商营业执照号码长度
     *
     * @param number 工商营业执照号码
     * @return true：通过验证;false：未通过验证。
     */
    public static boolean isRightBusinessLisenceNum(String number) {
        try {
            // 判断是否为空，或者空字符串
            if (!Tools.stringIsNotNullAndEmpty(number)) {
                return false;
            }
            // 统一社会信用代码证号码验证（三证合一后，工商营业执照号码被换成统一社会信用代码证号码）
            if (RegexCreditCode.isCreditCode(number)) {
                return true;
            }
            // 由数字或者连接符（-）组成,且只能是13位或者15位
            if (number.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.BUSINESS_NUM))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 验证组织机构代码证号码
     * 8位数字-1位数字或1个字母，如11231231-1。
     * 并根据特殊算法，使用
     *
     * @param number 需要被验证的组织机构代码证书号码
     * @return true：通过验证;false：未通过验证。
     */
    public static boolean isRightOrganizationCertificateNum(String number) {
        try {
            // 判断是否为空，或者空字符串
            if (!Tools.stringIsNotNullAndEmpty(number)) {
                return false;
            }
            // 统一社会信用代码证号码验证（三证合一后，组织机构代码证书号码被换成统一社会信用代码证号码）
            if (RegexCreditCode.isCreditCode(number)) {
                return true;
            }
            String compareNumber = number.trim();
            // 组织机构代码，正则验证 XXXXXXXX-M，其中的X,M都只能为数字（0-9），字母（a-zA-Z）
            return compareNumber.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.ORGANIZATION_CERTIFICATE_NUM));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 验证统一社会信用代码证号码
     * 18位数字或字母，如11510100009172327E。
     *
     * @param number 需要被验证的统一社会信用代码证号码
     * @return true：通过验证;false：未通过验证。
     */
    public static boolean isRightTyshxydmNum(String number) {
        try {
            // 判断是否为空，或者空字符串
            if (!Tools.stringIsNotNullAndEmpty(number)) {
                return false;
            }
            String compareNumber = number.trim();
            // 统一社会信用代码证号码，正则验证18位数字或字母
            return RegexCreditCode.isCreditCode(compareNumber);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 验证身份证号码的有效性
     *
     * @param num 需要被验证的身份证号码
     * @return true：通过验证;false：未通过验证。
     */
    public static boolean isRightIDCard(String num) {
        String number = num;
        try {
            //不为空和空字符串
            if (!Tools.stringIsNotNullAndEmpty(number)) {
                return false;
            }
            number = number.trim().toUpperCase();
            //正则验证位数，15位、18位、17位+字母
            if (!number.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.ID_CARD))) {
                return false;
            }
            //15位身份证号码，先转化为18位 ，然后按照18位身份证的验证方式验证
            if (number.matches(ConfigManager.getInstance().getValue(MatchPattern.class, MatchPattern.FIFTEEN_ID_CARD_NUM))) {
                number = convertIdcarBy15bit(number);
            }
            return validate18IdCard(number);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将15位的身份证转成18位身份证
     *
     * @param idCardNum 需要被转化的身份证号码
     * @return 转化后的18位身份证号码
     */
    private static String convertIdcarBy15bit(String idCardNum) {
        String idcard17 = null;
        try {
            // 获取出生年月日
            String birthday = idCardNum.substring(CommonPlatformConstant.LENGTH_6, CommonPlatformConstant.LENGTH_12);
            Date birthdate;
            birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            Calendar cday = Calendar.getInstance();
            cday.setTime(birthdate);
            String year = String.valueOf(cday.get(Calendar.YEAR));

            idcard17 = idCardNum.substring(0, CommonPlatformConstant.LENGTH_6) + year + idCardNum.substring(CommonPlatformConstant.LENGTH_8);

            char[] c = idcard17.toCharArray();
            String checkCode;

            int[] bit = converCharToInt(c);
            int sum17 = getPowerSum(bit);

            // 获取和值与11取模得到余数进行校验码
            checkCode = getCheckCodeBySum(sum17);
            // 获取不到校验位
            if (null == checkCode) {
                return null;
            }

            // 将前17位与第18位校验码拼接
            idcard17 += checkCode;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return idcard17;
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c 需要被转化的字符数组
     * @return 整型数组
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
                    break;
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param sum17 需要被取模属数
     * @return 校验码
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
     * @param idCardNum 需要被验证的身份证号码
     * @return true:验证通过;false:验证不通过。
     */
    private static boolean validate18IdCard(String idCardNum) {
        try {
            // 获取前17位
            String idcard17 = idCardNum.substring(0, CommonPlatformConstant.LENGTH_17);
            // 获取第18位
            String idcard18Code = idCardNum.substring(CommonPlatformConstant.LENGTH_17, CommonPlatformConstant.LENGTH_18);
            char[] c = idcard17.toCharArray();
            String checkCode;

            int[] bit = converCharToInt(c);

            int sum17 = getPowerSum(bit);

            // 将和值与11取模得到余数进行校验码判断
            checkCode = getCheckCodeBySum(sum17);
            if (null == checkCode) {
                return false;
            }
            // 将身份证的第18位与算出来的校码进行匹配，不相等就为假(区分大小写)
            return idcard18Code.equals(checkCode);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}
