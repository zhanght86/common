package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-010-24
 *
 * @author 丁龑
 * @category 序列号
 */

@Entity
@DiscriminatorValue("SERIALIZATIONNUMBER")
@BeanName(name = "序列号")
public class SerializationNumber extends Dictionary {
    private static final long serialVersionUID = 4371388273516687958L;
    /** 1 */
    @BeanAttrInfo(value = "1", cnName = "1")
    public static final long ONE = 1;
    /** 2 */
    @BeanAttrInfo(value = "2", cnName = "2")
    public static final long TWO = 2;
    /** 3 */
    @BeanAttrInfo(value = "3", cnName = "3")
    public static final long THREE = 3;
    /** 4 */
    @BeanAttrInfo(value = "4", cnName = "4")
    public static final long FOUR = 4;
    /** 5 */
    @BeanAttrInfo(value = "5", cnName = "5")
    public static final long FIVE = 5;
    /** 6 */
    @BeanAttrInfo(value = "6", cnName = "6")
    public static final long SIX = 6;
    /** 7 */
    @BeanAttrInfo(value = "7", cnName = "7")
    public static final long SEVEN = 7;
    /** 8 */
    @BeanAttrInfo(value = "8", cnName = "8")
    public static final long EIGHT = 8;
    /** 9 */
    @BeanAttrInfo(value = "9", cnName = "9")
    public static final long NINE = 9;
    /** 10 */
    @BeanAttrInfo(value = "10", cnName = "10")
    public static final long TEN = 10;
    /** 11 */
    @BeanAttrInfo(value = "11", cnName = "11")
    public static final long ELEVEN = 11;
    /** 12 */
    @BeanAttrInfo(value = "12", cnName = "12")
    public static final long TWELVE = 12;
    /** 13 */
    @BeanAttrInfo(value = "13", cnName = "13")
    public static final long THIRTEEN = 13;
    /** 14 */
    @BeanAttrInfo(value = "14", cnName = "14")
    public static final long FOURTEEN = 14;
    /** 15 */
    @BeanAttrInfo(value = "15", cnName = "15")
    public static final long FIFTEEN = 15;
    /** 16 */
    @BeanAttrInfo(value = "16", cnName = "16")
    public static final long SIXTEEN = 16;
    /** 17 */
    @BeanAttrInfo(value = "17", cnName = "17")
    public static final long SEVENTEEN = 17;
    /** 18 */
    @BeanAttrInfo(value = "18", cnName = "18")
    public static final long EIGHTEEN = 18;
    /** 19 */
    @BeanAttrInfo(value = "19", cnName = "19")
    public static final long NINETEEN = 19;
    /** 20 */
    @BeanAttrInfo(value = "20", cnName = "20")
    public static final long TWENTY = 20;
}
