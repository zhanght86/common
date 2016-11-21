package com.sjdf.platform.common.json;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.Json;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.utils.clazz.ClassUtils;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.CharacterIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.*;

/**
 * Create at 2013年8月8日 下午4:59:13
 * Json转换对象,此对象封装待转换的object对象,并将其转换成一个json格式的字符串对象
 *
 * @author KETQI
 */
public class JsonConvertObject {
    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(JsonConvertObject.class);
    public static final int DEFAULT_DEPTH = 6;
    private static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(DateUtils.DATETIME_FORMAT);
    private final Set<String> excludeProperties;
    private final Class<?>[] excludeClasses;
    private final Set<String> inclueProperties;
    private final Class<?>[] includeClasses;
    private final boolean simpleTypePropertyConvert;
    private final boolean gatherTypeExcluded;
    private final Object convertObject;
    private boolean resultBeautify;
    private DateFormat dateFormat = DEFAULT_DATE_FORMAT;
    private transient String convertedResult;
    private transient Set<Class<?>> excludeClassSet = new HashSet<>();
    private transient Set<Class<?>> includeClassSet = new HashSet<>();

    public JsonConvertObject(String[] inclueProperties, String[] excludeProperties, Class<?>[] includeClasses, Class<?>[] excludeClasses, boolean simpleTypePropertyConvert, boolean gatherTypeExcluded, DateFormat dateFormat, Object convertObject) {
        this.excludeClasses = excludeClasses == null ? new Class<?>[0] : excludeClasses;
        this.includeClasses = includeClasses == null ? new Class<?>[0] : includeClasses;
        this.simpleTypePropertyConvert = simpleTypePropertyConvert;
        this.gatherTypeExcluded = gatherTypeExcluded;
        this.dateFormat = dateFormat;
        this.convertObject = convertObject;
        this.inclueProperties = (inclueProperties == null || inclueProperties.length == 0) ? Collections.<String>emptySet() : new HashSet<>(Arrays.asList(inclueProperties));
        this.excludeProperties = (excludeProperties == null || excludeProperties.length == 0) ? Collections.<String>emptySet() : new HashSet<>(Arrays.asList(excludeProperties));
    }

    public String toJson() {
        if (convertedResult == null) {
            StringBuilder sb = new StringBuilder();
            convertToJson(null, 0, convertObject, sb);
            convertedResult = sb.toString();
        }
        return convertedResult;
    }

    private void convertToJson(String contextPath, int depth, Object obj, StringBuilder sb) {
        Class<?> clazz = obj == null ? null : obj.getClass();
        if (obj == null) {
            convertNull(sb);
        } else if (containExclude(clazz)) {
            convertExclude(sb);
        } else {
            convertNotExcludeToJson(contextPath, depth, obj, sb);
        }
    }

    protected void convertNotExcludeToJson(String contextPath, int depth, Object obj, StringBuilder sb) {
        Class<?> clazz = obj.getClass();
        if (isSimpleType(clazz)) {
            convertSimple(obj, sb);
        } else if (simpleTypePropertyConvert) {
            convertComplexWithSimpleTypePropertyConvert(contextPath, depth, obj, sb);
        } else {
            convertComplexToJson(contextPath, depth, obj, sb);
        }
    }

    protected void convertComplexToJson(String contextPath, int depth, Object obj, StringBuilder sb) {
        Class<?> clazz = obj.getClass();
        if (!(includeClasses == null || includeClasses.length == 0) && !containInclude(clazz)) {
            convertIncludeWithNotInclude(contextPath, depth, obj, sb);
        } else {
            convertComplexWithIncludeToJson(contextPath, depth, obj, sb);
        }
    }

    protected void convertComplexWithIncludeToJson(String contextPath, int depth, Object obj, StringBuilder sb) {
        Class<?> clazz = obj.getClass();
        if (isGatherType(clazz)) {
            if (gatherTypeExcluded) {
                convertGatherWithCollectionTypeExcluded(contextPath, depth, obj, sb);
            } else {
                convertGather(contextPath, depth, obj, sb);
            }
        } else {
            Map<String, Object> map = objectToMap(obj);
            convertMap(contextPath, depth, map, sb);
        }
    }

    protected void convertNull(StringBuilder sb) {
        sb.append("\"\"");
    }

    protected void convertExclude(StringBuilder sb) {
        sb.append("\"\"");
    }

    private void convertSimple(Object obj, StringBuilder sb) {
        Class<?> clazz = obj.getClass();
        if (isNumberType(clazz)) {
            convertNumber(obj, sb);
        } else if (isDateType(clazz)) {
            convertDate(obj, sb);
        } else if (isStringType(clazz)) {
            convertString(obj, sb);
        } else if (isEnumType(clazz)) {
            convertEnum(obj, sb);
        }
    }

    protected void convertNumber(Object obj, StringBuilder sb) {
        if (obj instanceof Character) {
            sb.append("'").append(obj).append("'");
        } else {
            sb.append(obj);
        }
    }

    protected void convertDate(Object obj, StringBuilder sb) {
        sb.append('"').append(dateFormat.format(obj)).append('"');
    }

    private void convertString(Object obj, StringBuilder sb) {
        sb.append('"');
        CharacterIterator it = new StringCharacterIterator(obj.toString());

        for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
            if (c == '"') {
                sb.append("\\\"");
            } else if (c == '\\') {
                sb.append("\\\\");
            } else if (c == '/') {
                sb.append("\\/");
            } else if (c == '\b') {
                sb.append("\\b");
            } else if (c == '\f') {
                sb.append("\\f");
            } else if (c == '\n') {
                sb.append("\\n");
            } else if (c == '\r') {
                sb.append("\\r");
            } else if (c == '\t') {
                sb.append("\\t");
            } else if (Character.isISOControl(c)) {
                sb.append(PlatformUtils.unicodeEscaped(c));
            } else {
                sb.append(c);
            }
        }
        sb.append('"');
    }

    protected void convertEnum(Object obj, StringBuilder sb) {
        convertString(obj.toString(), sb);
    }

    protected void convertComplexWithSimpleTypePropertyConvert(String contextPath, int depth, Object obj, StringBuilder sb) {
        if (depth > 0) {
            sb.append("\"\"");
        } else {
            convertComplexToJson(contextPath, depth, obj, sb);
        }
    }

    protected void convertIncludeWithNotInclude(String contextPath, int depth, Object obj, StringBuilder sb) {
        sb.append("\"\"");
    }

    protected void convertGatherWithCollectionTypeExcluded(String contextPath, int depth, Object obj, StringBuilder sb) {
        sb.append("\"\"");
    }

    protected void convertGather(String contextPath, int depth, Object obj, StringBuilder sb) {
        Class<?> clazz = obj.getClass();
        if (isCollectionType(clazz)) {
            convertCollection(contextPath, depth, obj, sb);
        } else if (isArrayType(clazz)) {
            convertArray(contextPath, depth, obj, sb);
        } else if (isMapType(clazz)) {
            convertMap(contextPath, depth, obj, sb);
        }
    }

    private void convertCollection(String contextPath, int depth, Object obj, StringBuilder sb) {
        sb.append("[");
        for (Iterator<?> iterator = ((Collection<?>) obj).iterator(); iterator.hasNext(); ) {
            convertToJson(contextPath, depth, iterator.next(), sb);
            if (iterator.hasNext()) {
                sb.append(",");
                if (resultBeautify) {
                    sb.append("\n");
                }
            }
        }
        sb.append("]");
    }

    protected void convertArray(String contextPath, int depth, Object obj, StringBuilder sb) {
        sb.append("[");
        int max = java.lang.reflect.Array.getLength(obj);
        for (int i = 0; i < max; i++) {
            if (i > 0) {
                sb.append(",");
            }
            convertToJson(contextPath, depth, java.lang.reflect.Array.get(obj, i), sb);
        }
        sb.append("]");
    }

    protected void convertMap(String contextPath, int depth, Object obj, StringBuilder sb) {
        // key过滤以include为准还是exclude为准
        boolean judgeIncludeProperty = !inclueProperties.isEmpty();
        if (resultBeautify && sb.length() > 0 && sb.lastIndexOf(",") != -1) {
            sb.insert(sb.lastIndexOf(",") + 1, "\n");
        }
        sb.append("{");
        boolean shouldAddComma = false;
        for (Map.Entry<?, ?> e : ((Map<?, ?>) obj).entrySet()) {
            if (!(e.getKey() instanceof String)) {
                continue;
            }
            String key = (String) e.getKey();
            String path = add(contextPath, key);
            if (judgeIncludeProperty && !containInclude(path)) {
                continue;
            } else if (containExclude(path)) {
                continue;
            }

            if (shouldAddComma) {
                sb.append(",");
            }
            if (resultBeautify && sb.length() > 0 && sb.charAt(sb.length() - 1) == ',' && sb.charAt(sb.length() - CommonPlatformConstant.LENGTH_2) == '}') {
                sb.insert(sb.length(), "\n");
            }

            sb.append("\"").append(e.getKey()).append("\"").append(":");

            if (depth <= DEFAULT_DEPTH) {
                convertToJson(path, depth + 1, e.getValue(), sb);
            } else {
                sb.append("undefined");
            }
            shouldAddComma = true;
        }

        sb.append("}");
    }

    private static boolean isGatherType(Class<?> clazz) {
        return isCollectionType(clazz) || isArrayType(clazz) || isMapType(clazz);
    }

    private static boolean isCollectionType(Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }

    private static boolean isArrayType(Class<?> clazz) {
        return clazz.isArray();
    }

    private static boolean isMapType(Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }

    private static boolean isSimpleType(Class<?> clazz) {
        return isNumberType(clazz) || isDateType(clazz) || isStringType(clazz) || isEnumType(clazz);
    }

    private static boolean isDateType(Class<?> clazz) {
        return Date.class.isAssignableFrom(clazz);
    }

    private static boolean isEnumType(Class<?> clazz) {
        return Enum.class.isAssignableFrom(clazz);
    }

    private static boolean isStringType(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz) || StringBuffer.class.isAssignableFrom(clazz) || StringBuilder.class.isAssignableFrom(clazz);
    }

    private static boolean isNumberType(Class<?> clazz) {
        return clazz.isPrimitive() || Number.class.isAssignableFrom(clazz) || Boolean.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz);
    }

    private static String add(String a, String b) {
        if (a == null) {
            return b;
        }
        StringBuilder s = new StringBuilder(a);
        s.append(".").append(b);
        if (s.charAt(0) == '.') {
            s.deleteCharAt(0);
        }
        return s.toString();
    }

    private boolean containExclude(Class<?> c) {
        return containClass(c, excludeClasses, excludeClassSet);
    }

    private static boolean containClass(Class<?> c, Class<?>[] classes, Set<Class<?>> cacheClassSet) {
        if (cacheClassSet.contains(c)) {
            return true;
        }
        for (Class<?> cl : classes) {
            if (c.isAssignableFrom(cl)) {
                cacheClassSet.add(c);
                return true;
            }
        }
        return false;
    }

    private boolean containInclude(Class<?> c) {
        return containClass(c, includeClasses, includeClassSet);
    }

    private boolean containExclude(String s) {
        return excludeProperties.contains(s);
    }

    private boolean containInclude(String s) {
        for (String st : inclueProperties) {
            if (st.startsWith(s)) {
                return true;
            }
        }
        return false;
    }

    private static Map<String, Object> objectToMap(Object obj) {
        if (obj instanceof Class<?>) {
            return Collections.emptyMap();
        }
        Map<String, Object> map = new HashMap<>();
        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(ClassUtils.findRealClass(obj)).getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String name = propertyDescriptor.getName();
                if ("class".equals(name)) {
                    continue;
                }
                Method method = propertyDescriptor.getReadMethod();
                if (method == null) {
                    continue;
                }
                Json json = method.getAnnotation(Json.class);
                DateFormat format = DEFAULT_DATE_FORMAT;
                if (json != null) {
                    if (json.excluded()) {
                        continue;
                    }
                    if (PlatformUtils.hasText(json.format())) {
                        format = new SimpleDateFormat(json.format());
                    }
                    if (PlatformUtils.hasText(json.name())) {
                        name = json.name();
                    }
                }
                Object result = method.invoke(obj);
                if (result != null && Date.class.isAssignableFrom(propertyDescriptor.getPropertyType())) {
                    map.put(name, format.format(result));
                } else {
                    map.put(name, result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("objectToMap转换失败:" + obj, e);
        }

        return map;
    }
}
