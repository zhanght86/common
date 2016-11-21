package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;

import java.util.Random;

/**
 * Create at 2012-07-19
 * 随机信息处理类
 *
 * @author ketqi
 */
public abstract class RandomUtils {
    /** 符号 */
    private static final char[] SYMBOL = "!@#$*".toCharArray();
    /** 字母 */
    private static final char[] LETTER = "abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ".toCharArray();

    /**
     * @param length 生成后的字符串长度
     * @return 生成随机字符串
     */
    public static String getRandomString(int length) {
        if (length < CommonPlatformConstant.LENGTH_2) {
            throw new RuntimeException("the length of random string must greater 2");
        }
        int num = length / CommonPlatformConstant.LENGTH_5;
        if (num == 0) {
            num = 1;
        }

        String numStr = getRandomNum(num);
        String letterStr = getRandLetter(length - num);
        return shuffle(numStr + letterStr);
    }

    /**
     * @param length 生成后的字符串长度
     * @return 生成随机数字
     */
    public static String getRandomNum(int length) {
        StringBuilder generateRandStr = new StringBuilder(length);
        Random rand = new Random();
        for (int i = length; i > 0; i--) {
            generateRandStr.append(rand.nextInt(CommonPlatformConstant.LENGTH_7) + CommonPlatformConstant.LENGTH_2);
        }
        return generateRandStr.toString();
    }

    /**
     * @param length 生成后的字符串长度
     * @return 随机生成符号串
     */
    public static String getRandomSymbol(int length) {
        StringBuilder generateRandStr = new StringBuilder(length);
        Random rand = new Random();
        for (int i = length; i > 0; i--) {
            int randNum = rand.nextInt(SYMBOL.length - 1);
            generateRandStr.append(SYMBOL[randNum]);
        }
        return generateRandStr.toString();
    }

    /**
     * @param length 生成后的字符串长度
     * @return 随机生成字母串
     */
    public static String getRandLetter(int length) {
        StringBuilder generateRandStr = new StringBuilder(length);
        Random rand = new Random();
        for (int i = length; i > 0; i--) {
            int randNum = rand.nextInt(LETTER.length - 1);
            generateRandStr.append(LETTER[randNum]);
        }
        return generateRandStr.toString();
    }

    /**
     * @param length 生成后的字符串长度
     * @return 随机生成8位以上包含字母, 数字, 符号的字符串
     */
    public static String getRandom(int length) {
        int size = length;
        if (size < CommonPlatformConstant.LENGTH_8) {
            size = CommonPlatformConstant.LENGTH_8;
        }

        StringBuilder builder = new StringBuilder(size);

        // 随机数字
        int num = new Random().nextInt(size / CommonPlatformConstant.LENGTH_2);
        if (num == 0) {
            num = CommonPlatformConstant.LENGTH_2;
        }
        builder.append(getRandomNum(num));

        // 3个符号
        int symbol = new Random().nextInt(size / CommonPlatformConstant.LENGTH_2);
        if (symbol == 0) {
            symbol = CommonPlatformConstant.LENGTH_3;
        }
        builder.append(getRandomSymbol(symbol));

        // length-num-symbol个字母
        builder.append(getRandLetter(size - num - symbol));
        return shuffle(builder.toString());
    }

    /**
     * @return 随机排序字符串
     */
    private static String shuffle(String str) {
        char[] chars = str.toCharArray();
        Random rnd = new Random();

        for (int i = chars.length - 1; i > 0; i--) {
            swap(chars, i, rnd.nextInt(i));
        }

        for (int i = chars.length - 1; i > 0; i--) {
            swap(chars, i, rnd.nextInt(i));
        }

        return new String(chars);
    }

    /** Swaps the two specified elements in the specified array. */
    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
