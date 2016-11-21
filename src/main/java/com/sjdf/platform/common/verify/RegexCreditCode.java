package com.sjdf.platform.common.verify;

import java.util.HashMap;
import java.util.Map;

import com.sjdf.platform.common.utils.PlatformUtils;

/**
 * Hunk: 社会信用代码证 检验 Created 2016/6/17.
 */
public class RegexCreditCode {
    private static final int LENGTH_18 = 18;
    private static Map<String, Integer> datas = null;
    private static char[] pre17s;
    private static int[] power = { 1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24,
            10, 30, 28 };
    private static char[] code = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
            'P', 'Q', 'R', 'T', 'U', 'W', 'X', 'Y' };

    /**
     * 判断是否是一个有效的社会信用代码
     * 
     * @param creditCode
     * @return
     */
    public static boolean isCreditCode(String creditCode) {
        try {
            initDatas(code.length);
            pre17(creditCode);
        } catch (Exception e) {
            return false;
        }
        if (!PlatformUtils.hasText(creditCode)) {
            return false;
        } else if (creditCode.length() < LENGTH_18) {
            return false;
        } else if (creditCode.length() > LENGTH_18) {
            return false;
        } else {
            int sum = sum(pre17s);
            int temp = sum % 31;
            try {
                return creditCode.substring(17, 18).equals(code[31 - temp] + "") ? true : false;
            } catch (Exception e) {
                return false;
            }
        }
    }

    /**
     * @param chars
     * @return
     */
    private static int sum(char[] chars) {
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            int code = datas.get(chars[i] + "");
            sum += power[i] * code;
        }
        return sum;

    }

    /**
     * 获取前17位字符
     * 
     * @param creditCode
     */
    private static void pre17(String creditCode) {
        String pre17 = creditCode.substring(0, 17);
        pre17s = pre17.toCharArray();
    }

    /**
     * 初始化数据
     * 
     * @param count
     */
    private static void initDatas(int count) {
        datas = new HashMap<>();
        for (int i = 0; i < code.length; i++) {
            datas.put(code[i] + "", i);
        }
    }

    public static void main(String[] args) {
        System.out.println(isCreditCode("91510100350611266D"));
    }
}
