package com.sjdf.platform.message.task;

import com.sjdf.platform.autotask.BaseAutoTask;
import com.sjdf.platform.autotask.annotations.AutoTask;
import com.sjdf.platform.common.command.RuntimeExec;
import com.sjdf.platform.common.command.RuntimeExecResult;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.FileUtils;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.dictionary.bean.AutoTaskType;
import com.sjdf.platform.dictionary.bean.ConstGlobal;
import com.sjdf.platform.dictionary.bean.common.CommonGlobalConfig;
import com.sjdf.platform.dictionary.bean.common.MessageType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.bean.LogBackupBean;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.log.service.LogService;
import com.sjdf.platform.log.vo.LogVo;
import com.sjdf.platform.message.service.MessageService;
import org.quartz.JobExecutionContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * 消息清理自动任务
 * <pre>
 * 针对非业务类型邮件，只保留一个月。也即是我们搞一个自动任务每月执行一次
 * 该自动任务做如下工作：
 *  邮件
 *      1).将当前表的业务类型邮件，一年以前的移动到backup表
 *          业务类型邮件：验证码，相关系统的通知（续费，购买，备案）
 *
 *      2).将当前表的非业务类型邮件，一个月以前的移动到backup表
 *          非业务类型：广告，工单，监控
 *
 *      3).将backup表的2年以前的数据进行删除
 *  短信
 *      1).将当前表的短信，一年以前的移动到backup表
 *      2).将backup表的5年以前的数据进行删除
 * </pre>
 * <p/>
 * User: ketqi
 * Date: 2015-07-16 11:38
 */
@AutoTask(type = AutoTaskType.EISS_COMMON_CLEAR_MESSAGE_AUTO_SEND)
public class ClearMessageTask extends BaseAutoTask {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(ClearMessageTask.class);
    private MessageService messageService;
    private LogService logService;
    /** 业务类型邮件帐号列表 */
    private Set<String> businessUserIdList;

    @Override
    protected void executeTask(JobExecutionContext context) throws Exception {
        LOGGER.info("ClearMessageTask begin");
        //邮件短信清理
        message();

        //日志清理
        logger();
        LOGGER.info("ClearMessageTask end");
    }

    private void message() {
        LOGGER.info("ClearMessage begin");
        //邮件
        //1).将当前表的业务类型邮件，一年以前的移动到backup表;业务类型邮件：验证码，相关系统的通知（续费，购买，备案）
        try {
            messageService.transferEmail(businessUserIdList, true);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("transferEmail", e);
        }

        //2).将当前表的非业务类型邮件，一个月以前的移动到backup表;非业务类型：广告，工单，监控
        try {
            messageService.transferEmail(businessUserIdList, false);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("transferEmail", e);
        }

        //3).将backup表的2年以前的数据进行删除
        try {
            messageService.delMessageBackup(MessageType.EMAIL);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("delMessageBackup", e);
        }

        //短信
        //1).将当前表的短信，一年以前的移动到backup表
        try {
            messageService.transferSms();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("transferSms", e);
        }

        //2).将backup表的5年以前的数据进行删除
        try {
            messageService.delMessageBackup(MessageType.SMS);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("delMessageBackup", e);
        }
        LOGGER.info("ClearMessage end");
    }

    /**
     * <pre>
     * 8.1 业务后台，管理中心，控制面板，搜索当前内容，仅搜索倒推一个月的日志。
     * 8.2 仅在业务后台增加，历史搜索，倒推12个月
     * 8.3 一年（12个月）后的日志进行从数据库中进行清理。进行物理删除。
     *        每天记录一个文件，每年打个包。
     * </pre>
     */
    private void logger() {
        LOGGER.info("ClearLogger begin");
        // 1.清理当前日志表,仅保留一个月的日志
        LOGGER.info("ClearLogger 1.清理当前日志表,仅保留一个月的日志");
        LogVo vo = new LogVo();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        vo.setEndTime(calendar.getTime());
        long count = 0L;
        // 每次处理的记录数
        int records = Integer.valueOf(ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.DATA_CLEAR_RECORDS)).intValue();
        Page page = new Page();
        page.setPageSize(records);
        // 抽取数据
        List<LogBean> logList = logService.list(vo, page);
        while (logList != null && !logList.isEmpty()) {
            count += logList.size();
            for (LogBean logBean : logList) {
                logService.save(logBean.toLogBackupBean());
                logService.delete(logBean);
            }
            // 循环抽取数据
            logList = logService.list(vo, page);
        }
        LOGGER.info("ClearLogger 备份日志数量：" + count);

        // 2.清理1年前的备份日志到本地文件
        LOGGER.info("ClearLogger 2.清理1年前的备份日志到本地文件");
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        vo.setEndTime(calendar.getTime());

        //封装文件存储路径:/data/home/log/2015/09/2015-09-16.log
        String rootPath = ConfigManager.getInstance().getValue(CommonGlobalConfig.class, CommonGlobalConfig.LOG_STORE_LOCAL_FILE_PATH);
        StringBuilder builder = new StringBuilder();
        builder.append(rootPath);
        builder.append(calendar.get(Calendar.YEAR));
        // Calendar.MONTH的范围是（0~11）
        builder.append("/").append(calendar.get(Calendar.MONTH) + 1);
        builder.append("/").append(DateUtils.formatDate(calendar.getTime())).append(".log");

        // 抽取数据
        List<LogBackupBean> backupList = logService.listHistory(vo, page);

        //创建文件并写数据
        FileUtils.createFile(builder.toString());
        Path path = Paths.get(builder.toString());
        count = 0L;
        try (BufferedWriter bw = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            while (backupList != null && !backupList.isEmpty()) {
                count += backupList.size();
                for (LogBackupBean backupBean : backupList) {
                    bw.write(backupBean.toString());
                    bw.newLine();
                }
                bw.flush();
                // 删除历史数据
                logService.deleteAll(backupList);
                // 循环抽取数据
                backupList = logService.listHistory(vo, page);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("ClearLogger 删除历史数据数量：" + count);

        // 3.打包历史数据
        LOGGER.info("ClearLogger 3.打包历史数据");
        File root = new File(rootPath);
        if (!root.exists()) {
            return;
        }

        File[] yearFiles = root.listFiles();
        if (yearFiles == null) {
            return;
        }

        for (File file : yearFiles) {
            //是目录并且文件夹的名称不等于备份年限
            if (file.isDirectory() && !file.getName().equals(String.valueOf(calendar.get(Calendar.YEAR)))) {
                //tar -zcvf /data/home/log/2015.tar.gz /data/home/log/2015
                String gzPath = rootPath + file.getName() + ".tar.gz";
                StringBuilder command = new StringBuilder();
                command.append("tar -zcvf ");
                command.append(gzPath).append(" ");
                command.append(file.getAbsolutePath());

                RuntimeExecResult result = RuntimeExec.instance().exec(command.toString());
                if (result.getRet() == 0) {
                    //打包完毕后删除
                    FileUtils.delFile(file.getAbsolutePath());
                }
            }
        }
        LOGGER.info("ClearLogger end");
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public void setBusinessUserIdList(Set<String> businessUserIdList) {
        this.businessUserIdList = businessUserIdList;
    }
}
