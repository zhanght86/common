package com.sjdf.platform.common.utils;

import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * XSS过滤工具
 * Create at 2012-05-25
 *
 * @author 陈绍刚
 */
public abstract class AntiXSS {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AntiXSS.class);
    static final Pattern SCRIPT_TAG_PATTERN = Pattern.compile("<script[^>]*>.*</script[^>]*>", Pattern.CASE_INSENSITIVE);

    public static String antiXSS(String contentStr) {
        String content = contentStr;
        if (!StringUtils.isNotEmpty(content)) {
            return content;
        }
        try {
            content = URLDecoder.decode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        String old = content;
        String ret = antiXSS1(content);
        while (!ret.equals(old)) {
            old = ret;
            ret = antiXSS1(ret);
        }
        return ret;
    }

    private static String antiXSS1(String content) {
        try {
            return stripAllowScriptAccess(stripProtocol(stripCssExpression(stripAsciiAndHex(stripEvent(stripScriptTag(content))))));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    private static String stripScriptTag(String contentStr) {
        String content = contentStr;
        Matcher m = SCRIPT_TAG_PATTERN.matcher(content);
        content = m.replaceAll("");
        return content;
    }

    private static String stripEvent(String contentStr) throws Exception {
        String content = contentStr;
        String[] events = {"onmouseover", "onmouseout", "onmousedown",
                "onmouseup", "onmousemove", "onclick", "ondblclick",
                "onkeypress", "onkeydown", "onkeyup", "ondragstart",
                "onerrorupdate", "onhelp", "onreadystatechange", "onrowenter",
                "onrowexit", "onselectstart", "onload", "onunload",
                "onbeforeunload", "onblur", "onerror", "onfocus", "onresize",
                "onscroll", "oncontextmenu"};
        for (String event : events) {
            content = content.replaceAll("(<[^>]*)(" + event + ")([^>]*>)", "");
        }
        return content;
    }

    private static String stripAsciiAndHex(String content) throws Exception {
        return content.replaceAll("(<[^>]*)(&#|\\\\00)([^>]*>)", "");
    }

    private static String stripCssExpression(String contentStr) throws Exception {
        String content = contentStr;
        content = content.replaceAll("(<[^>]*style=.*)/\\*.*\\*/([^>]*>)", "");
        content = content.replaceAll("(<[^>]*style=[^>]+)(expression|javascript|vbscript|-moz-binding)([^>]*>)", "");
        content = content.replaceAll("(<style[^>]*>.*)/\\*.*\\*/(.*</style[^>]*>)", "");
        content = content.replaceAll("(<style[^>]*>[^>]+)(expression|javascript|vbscript|-moz-binding)(.*</style[^>]*>)", "");
        return content;
    }

    private static String stripProtocol(String contentStr) throws Exception {
        String content = contentStr;
        String[] protocols = {"javascript", "vbscript", "livescript", "ms-its", "mhtml", "data", "firefoxurl", "mocha"};
        for (String protocol : protocols) {
            content = content.replaceAll("(<[^>]*)" + protocol + ":([^>]*>)", "");
        }
        return content;
    }

    private static String stripAllowScriptAccess(String content) throws Exception {
        return content.replaceAll("(<[^>]*)AllowScriptAccess([^>]*>)", "");
    }
}
