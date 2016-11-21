package com.sjdf.platform.common.command;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;

/**
 * 执行外部命令
 * User: ketqi
 * Date: 2015-09-17 10:20
 */
public final class RuntimeExec {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(RuntimeExec.class);
    private static final RuntimeExec INSTANCE = new RuntimeExec();
    private static final Semaphore SEMAPHORE = new Semaphore(CommonPlatformConstant.LENGTH_2);

    private RuntimeExec() {
    }

    public static RuntimeExec instance() {
        return INSTANCE;
    }

    public RuntimeExecResult exec(String command) {
        return exec(command, null, null);
    }

    public RuntimeExecResult exec(String command, String[] envp) {
        return exec(command, envp, null);
    }

    public RuntimeExecResult exec(String command, String[] envp, File dir) {
        if (!PlatformUtils.hasText(command)) {
            throw new IllegalArgumentException("Empty command");
        }

        StringTokenizer st = new StringTokenizer(command);
        String[] cmdarray = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++) {
            cmdarray[i] = st.nextToken();
        }
        return exec(cmdarray, envp, dir);
    }

    public RuntimeExecResult exec(String[] cmdarray) {
        return exec(cmdarray, null, null);
    }

    public RuntimeExecResult exec(String[] cmdarray, String[] envp) {
        return exec(cmdarray, envp, null);
    }

    public RuntimeExecResult exec(String[] cmdarray, String[] envp, File dir) {
        if (cmdarray == null || cmdarray.length == 0) {
            throw new IllegalArgumentException("Empty command");
        }

        LOGGER.info(String.format("the command [%s] started.", Arrays.toString(cmdarray)));
        RuntimeExecResult result = new RuntimeExecResult();
        try {
            SEMAPHORE.acquire();

            Process process = Runtime.getRuntime().exec(cmdarray, envp, dir);
            // 创建2个线程，分别读取输入流缓冲区和错误流缓冲区
            RuntimeThread stdoutThread = new RuntimeThread(process.getInputStream(), false, result);
            RuntimeThread erroroutThread = new RuntimeThread(process.getErrorStream(), true, result);
            //启动线程读取缓冲区数据
            stdoutThread.start();
            erroroutThread.start();

            stdoutThread.join();
            erroroutThread.join();

            process.waitFor();

            // 返回子进程的出口值。
            result.setRet(process.exitValue());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            SEMAPHORE.release();
            LOGGER.info(String.format("the command [%s] end. result [%s]", Arrays.toString(cmdarray), result.toString()));
        }
        return result;
    }
}
