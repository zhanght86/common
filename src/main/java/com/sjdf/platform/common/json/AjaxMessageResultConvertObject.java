package com.sjdf.platform.common.json;

import java.text.DateFormat;

/** @author ketqi */
public class AjaxMessageResultConvertObject extends JsonConvertObject {
    public AjaxMessageResultConvertObject(String[] inclueProperties, String[] excludeProperties, Class<?>[] includeClasses, Class<?>[] excludeClasses, boolean simpleTypePropertyConvert, boolean gatherTypeExcluded, DateFormat dateFormat, Object convertObject) {
        super(inclueProperties, excludeProperties, includeClasses, excludeClasses, simpleTypePropertyConvert, gatherTypeExcluded, dateFormat, convertObject);
    }

    protected void convertExclude(String contextPath, int depth, Object obj, StringBuilder sb) {
        if (depth > 1) {
            super.convertExclude(sb);
        } else {
            convertNotExcludeToJson(contextPath, depth, obj, sb);
        }
    }

    @Override
    protected void convertComplexWithSimpleTypePropertyConvert(String contextPath, int depth, Object obj, StringBuilder sb) {
        if (depth > 1) {
            super.convertComplexWithSimpleTypePropertyConvert(contextPath, depth, obj, sb);
        } else {
            convertComplexToJson(contextPath, depth, obj, sb);
        }
    }

    @Override
    protected void convertGatherWithCollectionTypeExcluded(String contextPath, int depth, Object obj, StringBuilder sb) {
        if (depth > 1) {
            super.convertGatherWithCollectionTypeExcluded(contextPath, depth, obj, sb);
        } else {
            convertGather(contextPath, depth, obj, sb);
        }
    }

    @Override
    protected void convertIncludeWithNotInclude(String contextPath, int depth, Object obj, StringBuilder sb) {
        if (depth > 1) {
            super.convertIncludeWithNotInclude(contextPath, depth, obj, sb);
        } else {
            convertComplexWithIncludeToJson(contextPath, depth, obj, sb);
        }
    }
}
