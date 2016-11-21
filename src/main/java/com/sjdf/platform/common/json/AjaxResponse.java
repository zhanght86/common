package com.sjdf.platform.common.json;

import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.DateFormat;

/**
 * Create at 2013年8月8日 下午4:56:50
 * ajax响应信息封装
 *
 * @author KETQI
 */
public final class AjaxResponse {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AjaxResponse.class);

    private AjaxResponse() {
    }

    public static void setnAjaxText(Object obj, DateFormat format, String[] includeProperties, String[] excludeProperties, Class<?>[] includeClasses, Class<?>[] excludeClasses, boolean simpleTypePropertyConvert, boolean gatherTypeExcluded) {
        JsonConvertObject jsonConvertObject = new JsonConvertObject(includeProperties, excludeProperties, includeClasses, excludeClasses, simpleTypePropertyConvert, gatherTypeExcluded, format, obj);
        sendAjaxText(jsonConvertObject);
    }

    public static void sendAjaxText(JsonConvertObject jsonConvertObject) {
        sendText(jsonConvertObject.toJson());
    }

    private static void sendText(String responseText) {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(responseText);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                    LOGGER.error(ignore.getMessage(), ignore);
                }
            }
        }
    }
}
