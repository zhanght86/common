package com.sjdf.platform.message.utils;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.message.bean.SMSMessage;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: ketqi
 * Date: 2016-06-29 15:11
 */
public final class MessageExcelUtils {
    private MessageExcelUtils() {
    }

    public static List<SMSMessage> parse(File xls) {
        Workbook book;
        try {
            book = Workbook.getWorkbook(xls);
        } catch (BiffException | IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

        String content, var;
        List<SMSMessage> messageList = new ArrayList<>(CommonPlatformConstant.LENGTH_10000 * CommonPlatformConstant.LENGTH_6);
        for (Sheet sheet : book.getSheets()) {
            int rownum = sheet.getRows();// 得到总行数
            SMSMessage message;
            for (int i = 1; i < rownum; i++) {
                if ("".equals(sheet.getCell(0, i).getContents().trim())) {
                    continue;
                }
                message = new SMSMessage();
                // 第i行的第0列;手机号码
                message.setAddress(sheet.getCell(0, i).getContents().trim());
                // 第i行的第1列;短信内容
                content = sheet.getCell(1, i).getContents().trim();
                // 第i行的第2列;变量
                var = sheet.getCell(CommonPlatformConstant.LENGTH_2, i).getContents().trim();

                content = content.replace("{nickname}", var);
                message.setContent(content);
                messageList.add(message);
            }
        }
        return messageList;
    }
}
