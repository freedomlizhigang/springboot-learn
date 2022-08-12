package com.coins.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/8/2
 **/
public class JsonUtil {
    private static ObjectMapper objectMapper = SpringUtil.getBean(ObjectMapper.class);;

    // 对象转为map结构，文件到其它位置取用
    public static Map obj2map(Object obj) throws JsonProcessingException {
        String objStr = objectMapper.writeValueAsString(obj);
        return objectMapper.readValue(objStr,Map.class);
    }

    /**
     * 对象转json字符串
     */
    public static String obj2Str(Object obj) throws JsonProcessingException {
        if (Objects.isNull(obj)) {
            return null;
        }
        if (obj instanceof String) {
            return (String)obj;
        }
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * json字符串转对象
     */
    public static <T> T str2Obj(String jsonStr, Class<T> clazz) throws JsonProcessingException {
        if (Objects.isNull(jsonStr) || Objects.isNull(clazz)) {
            return null;
        }
        return clazz.equals(String.class) ? (T) jsonStr : objectMapper.readValue(jsonStr, clazz);
    }

    /**
     * json字符串转对象
     */
    public static <T> T str2Obj(String jsonStr, TypeReference<T> valueTypeRef) throws JsonProcessingException {
        if (Objects.isNull(jsonStr) || Objects.isNull(valueTypeRef)) {
            return null;
        }
        return objectMapper.readValue(jsonStr, valueTypeRef);
    }

    /**
     * 获取自定义规则 json对象（规则：objectName1:jsonString;objectName2:jsonString;）
     * @param str 字符串
     * @param objectName 获取字符串中的对象
     * @param clazz 对象类型
     * @param <T> 实体类
     * @return
     */
    public static <T> T getObject(String str, String objectName, Class<T> clazz) throws JsonProcessingException {
        // 解析获取对应json
        String s = objectName + ":";
        int startIndex = str.indexOf(s) + s.length();
        int endIndex = str.substring(startIndex).indexOf(";") + startIndex;
        String jsonStr = str.substring(startIndex, endIndex);
        // 反序列化
        return getJsonObject(jsonStr, clazz);
    }

    /**
     * 反序列化json字符串
     * @param jsonStr json字符串
     * @param clazz 反序列化class对象
     * @param <T> class对象
     */
    public static <T> T getJsonObject(String jsonStr, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(jsonStr, clazz);
    }


    /**
     * 获取自定义规则 json对象（规则：objectName1:jsonString;objectName2:jsonString;）
     * @param str 字符串
     * @param objectName 获取字符串中的对象
     * @param tClass 集合类类型
     * @param vClass 集合泛型类类型
     * @param <T> 集合类型
     * @param <V> 集合泛型类型
     */
    public static <T, V> T getObjectCollection(String str, String objectName, Class<T> tClass, Class<V> vClass) throws JsonProcessingException {
        // 解析获取对应json
        String s = objectName + ":";
        int startIndex = str.indexOf(s) + s.length();
        int endIndex = str.substring(startIndex).indexOf(";") + startIndex;
        String jsonStr = str.substring(startIndex, endIndex);
        // 反序列化
        return getJsonObjectCollection(jsonStr, tClass, vClass);
    }

    /**
     * 反序列化集合json字符串
     *
     * @param jsonStr json字符串
     * @param tClass 集合类类型
     * @param vClass 集合泛型类类型
     * @param <T> 集合类型
     * @param <V> 集合泛型类型
     */
    public static <T, V> T getJsonObjectCollection(String jsonStr, Class<T> tClass, Class<V> vClass) throws JsonProcessingException {
        return objectMapper.readValue(jsonStr, objectMapper.getTypeFactory().constructParametricType(tClass, vClass));
    }
}
