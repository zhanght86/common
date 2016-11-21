package com.sjdf.platform.xss;

import java.util.regex.Pattern;

/**
 * Create at 2013年11月11日 下午6:22:00
 * xss 转义辅助类
 *
 * @author KETQI
 */
public abstract class EscapeUtils {

    /**
     * 转义所有输入
     *
     * @param input 输入
     * @return 输出
     */
    public static String escape(String input) {
        String result = input;
        result = StringEscapeUtils.escapeHtml4(result);
        result = StringEscapeUtils.escapeEcmaScript(result);
        result = StringEscapeUtils.escapeXml(result);
        result = StringEscapeUtils.escapeCsv(result);
        return result;
    }

    /**
     * 转义html
     *
     * @param input 输入
     * @return 输出
     */
    public static String escapeHtml(String input) {
        return StringEscapeUtils.escapeHtml4(input);
    }

    /**
     * 转义script
     *
     * @param input 输入
     * @return 输出
     */
    public static String escapeEcmaScript(String input) {
        return StringEscapeUtils.escapeEcmaScript(input);
    }

    /**
     * 转义xml
     *
     * @param input 输入
     * @return 输出
     */
    public static String escapeXml(String input) {
        return StringEscapeUtils.escapeXml(input);
    }

    /**
     * 转义csv
     *
     * @param input 输入
     * @return 输出
     */
    public static String escapeCsv(String input) {
        return StringEscapeUtils.escapeCsv(input);
    }

    public static String xssScript(String contentStr) {
        String content = contentStr;
        if (content != null) {
            content = content.replaceAll("", "");
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            content = scriptPattern.matcher(content).replaceAll("");
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            content = scriptPattern.matcher(content).replaceAll("");
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            content = scriptPattern.matcher(content).replaceAll("");
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            content = scriptPattern.matcher(content).replaceAll("");
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            content = scriptPattern.matcher(content).replaceAll("");
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            content = scriptPattern.matcher(content).replaceAll("");
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            content = scriptPattern.matcher(content).replaceAll("");
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            content = scriptPattern.matcher(content).replaceAll("");
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            content = scriptPattern.matcher(content).replaceAll("");
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            content = scriptPattern.matcher(content).replaceAll("");
        }
        return content;
    }
}
