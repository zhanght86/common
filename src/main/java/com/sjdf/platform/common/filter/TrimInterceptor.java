package com.sjdf.platform.common.filter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sjdf.platform.common.utils.RemoteIpAddressContext;
import com.sjdf.platform.common.utils.Tools;

import java.util.Map;
import java.util.Map.Entry;

/**
 * 替换 所有通过URL传递的参数前后的空格
 *
 * @author ketqi
 */
public class TrimInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        RemoteIpAddressContext.getInstance().set(Tools.getIpAddress());

        Map<String, Object> parameters = invocation.getInvocationContext().getParameters();
        for (Entry<String, Object> entry : parameters.entrySet()) {
            if (entry.getValue() instanceof String[]) {

                String[] valueArray = (String[]) entry.getValue();

                for (int i = 0; i < valueArray.length; i++) {
                    valueArray[i] = valueArray[i].trim();
                }

                parameters.put(entry.getKey(), valueArray);
            }
        }
        return invocation.invoke();
    }
}
