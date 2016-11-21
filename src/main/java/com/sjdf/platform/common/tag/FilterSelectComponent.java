package com.sjdf.platform.common.tag;

import com.opensymphony.xwork2.util.ValueStack;
import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.conf.ProfileProvider;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.apache.struts2.components.ListUIBean;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Create at 2013年11月12日 上午10:40:43
 * <p/>
 * 带有过滤功能的select标签
 *
 * @author KETQI
 */
public class FilterSelectComponent extends ListUIBean {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(FilterSelectComponent.class);
    public static final String TEMPLATE = "select";
    private static final String CSS_JS;
    protected String emptyOption;
    protected String headerKey;
    protected String headerValue;
    protected String multiple;
    protected String size;

    static {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        builder.append("<link rel='stylesheet' type='text/css' href='http://").append(ProfileProvider.getCommonPlatformAccessDomain()).append("/css/sfilter.css'/>");
        builder.append("<script type='text/javascript' src='http://").append(ProfileProvider.getCommonPlatformAccessDomain()).append("/js/sfilter.js'></script>");
        CSS_JS = builder.toString();
    }

    public FilterSelectComponent(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    @Override
    public boolean end(Writer writer, String body) {
        super.end(writer, body);

        String js = "<script type='text/javascript'>$(function(){$('#" + getId() + "').sfilter();})</script>";
        try {
            writer.write(CSS_JS);
            writer.write(js);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }

        return false;
    }

    public void evaluateExtraParams() {
        super.evaluateExtraParams();

        if (emptyOption != null) {
            addParameter("emptyOption", findValue(emptyOption, Boolean.class));
        }

        if (multiple != null) {
            addParameter("multiple", findValue(multiple, Boolean.class));
        }

        if (size != null) {
            addParameter("size", findString(size));
        }

        if ((headerKey != null) && (headerValue != null)) {
            addParameter("headerKey", findString(headerKey));
            addParameter("headerValue", findString(headerValue));
        }
    }

    @StrutsTagAttribute(description = "Whether or not to add an empty (--) option after the header option", type = "Boolean", defaultValue = "false")
    public void setEmptyOption(String emptyOption) {
        this.emptyOption = emptyOption;
    }

    @StrutsTagAttribute(description = " Key for first item in list. Must not be empty! '-1' and '' is correct, '' is bad.")
    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    @StrutsTagAttribute(description = "Value expression for first item in list")
    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    @StrutsTagAttribute(description = " Creates a multiple select. The tag will pre-select multiple values" + " if the values are passed as an Array or a Collection(of appropriate types) via the value attribute. If one of the keys equals" + " one of the values in the Collection or Array it wil be selected", type = "Boolean", defaultValue = "false")
    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    @StrutsTagAttribute(description = "Size of the element box (# of elements to show)", type = "Integer")
    public void setSize(String size) {
        this.size = size;
    }
}
