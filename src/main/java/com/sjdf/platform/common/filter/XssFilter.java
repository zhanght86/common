package com.sjdf.platform.common.filter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sjdf.platform.common.utils.Tools;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Create at 2012-10-24
 * 使用Servlet Filter来防止Xss漏洞和SQL注入
 *
 * @author ketqi
 */
public class XssFilter extends MethodFilterInterceptor {
    private static final long serialVersionUID = -6427639508741888020L;

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        //设置请求和响应的编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //自定义响应头中的Server
        response.setHeader("Server", "cws");

        Map<String, Object> parameters = invocation.getInvocationContext().getParameters();
        for (Entry<String, Object> entry : parameters.entrySet()) {
            if (entry.getValue() instanceof String[]) {
                String[] valueArray = (String[]) entry.getValue();
                for (int i = 0; i < valueArray.length; i++) {
                    valueArray[i] = xssEncode(valueArray[i]);
                }
                parameters.put(entry.getKey(), valueArray);
            }
        }
        return invocation.invoke();
    }

    /** 将容易引起xss漏洞的半角字符直接替换成全角字符 */
    private static String xssEncode(String s) {
        return Tools.xssEncode(s);
    }
}