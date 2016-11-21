package com.sjdf.platform.common.command;

import com.sjdf.platform.CommonPlatformConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 执行外部命令结果
 * User: ketqi
 * Date: 2015-09-17 10:25
 */
public final class RuntimeExecResult {
    /** shell执行错误代码 */
    private int ret;
    /** 输出字符串 */
    private StringBuilder outStr = new StringBuilder(CommonPlatformConstant.LENGTH_512);
    /** 输出错误字符串 */
    private StringBuilder outErr = new StringBuilder(CommonPlatformConstant.LENGTH_512);
    /** 保存进程的输入流信息 */
    private List<String> stdoutList = new ArrayList<>();
    /** 保存进程的错误流信息 */
    private List<String> erroroutList = new ArrayList<>();

    public void append(String line) {
        outStr.append(line);
        stdoutList.add(line);
    }

    public void appendError(String line) {
        outErr.append(line);
        erroroutList.add(line);
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getOutStr() {
        return outStr.toString();
    }

    public String getOutErr() {
        return outErr.toString();
    }

    public List<String> getStdoutList() {
        return stdoutList;
    }

    public List<String> getErroroutList() {
        return erroroutList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RuntimeExecResult{");
        sb.append("ret=").append(ret);
        sb.append(", outStr='").append(outStr).append('\'');
        sb.append(", outErr='").append(outErr).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
