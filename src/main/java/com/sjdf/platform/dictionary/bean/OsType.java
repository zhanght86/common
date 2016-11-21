package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 操作系统类别
 * Create at 2012-05-22
 *
 * @author 王正伟
 */
@Entity
@DiscriminatorValue("OS_TYPE")
@BeanName(name = "操作系统类别")
public class OsType extends Dictionary {
    private static final long serialVersionUID = -4275549955295467017L;

    /** windows */
    @BeanAttrInfo(cnName = "windows")
    public static final long WINDOWS = 1;
    /** linux */
    @BeanAttrInfo(cnName = "linux")
    public static final long LINUX = 2;
    /** windows 2003 */
    @BeanAttrInfo(refClass = OsType.class, refAttr = WINDOWS, cnName = "windows 2003")
    public static final long WINDOW_2003 = 2003;
    /** windows 2008 */
    @BeanAttrInfo(refClass = OsType.class, refAttr = WINDOWS, cnName = "windows 2008")
    public static final long WINDOW_2008 = 2008;

    public static long osType() {
        //操作系统名称
        String osName = System.getProperties().getProperty("os.name");
        if (PlatformUtils.hasText(osName)) {
            if (osName.contains("2003")) {
                return WINDOW_2003;
            } else if (osName.contains("2008")) {
                return WINDOW_2008;
            } else if (osName.toLowerCase().contains("linux")) {
                return LINUX;
            }
        }
        return 0L;
    }
}
