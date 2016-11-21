package com.sjdf.platform.common.command;

import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 收集外部命令执行结果的线程
 * User: ketqi
 * Date: 2015-09-17 10:29
 */
public final class RuntimeThread extends Thread {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(RuntimeThread.class);
    /** 获取RuntimeExec执行进程的输出流 */
    private InputStream is;
    /** 设置读取的字符编码 */
    private String character = "UTF-8";
    /** 是否是错误输出流 */
    private boolean error;
    /** 输出结果 */
    private RuntimeExecResult result;

    public RuntimeThread(InputStream is, boolean error, RuntimeExecResult result) {
        this(is, null, error, result);
    }

    public RuntimeThread(InputStream is, String character, boolean error, RuntimeExecResult result) {
        this.is = is;
        this.error = error;
        this.result = result;
        if (PlatformUtils.hasText(character)) {
            this.character = character;
        }
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, character))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (error) {
                    result.appendError(line);
                } else {
                    result.append(line);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
