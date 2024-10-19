package top.abigtree.im.robot.service.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/20
 */
@Slf4j
public class JsonUtil {
    private final static ObjectMapper NORMAL_MAPPER = new ObjectMapper();
    private final static ObjectMapper LOGGER_MAPPER = new ObjectMapper();

    static {
        NORMAL_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        LOGGER_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    /**
     * 对象转Json字符
     */
    public static String objectToJson(ObjectMapper mapper, Object o) {
        String res = StringUtils.EMPTY;
        try {
            res = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("Object:{} convert error", o);
        }
        return res;
    }

    /**
     * Json字符串转对象
     */
    public static <T> T jsonToObject(ObjectMapper mapper, String json, Class<T> clazz) {
        T res = null;
        try {
            res = mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("Json String:{} convert error, e:", json, e);
        }
        return res;
    }

    /**
     * Map 转对象
     */
    public static <T> T mapToObject(ObjectMapper mapper, Map<String, String> map, Class<T> clazz) {
        T res = null;
        try {
            res = mapper.convertValue(map, clazz);
        } catch (Exception e) {
            log.error("Map data:{} convert error, e:", map, e);
        }
        return res;
    }

    /**
     * json转对象（带范型）
     */
    public static <R, T> R jsonToObject(ObjectMapper mapper, String json, Class<R> clazz1, Class<T> clazz2) {
        R res = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(clazz1, clazz2);
            res = mapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            log.error("Json String:{} convert error, e:", json, e);
        }
        return res;
    }

    /**
     * Json转列表
     */
    public static <T> List<T> jsonToList(ObjectMapper mapper, String json, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            list = mapper.readValue(json, listType);
        } catch (JsonProcessingException e) {
            log.error("Json String:{} convert list<{}> error", json, clazz.toString());
        }
        return list;
    }

    /**
     * Json转Map
     */
    public static <K, V> Map<K, V> jsonToMap(ObjectMapper mapper, String json, Class<K> key, Class<V> value) {
        Map<K, V> map = new HashMap<>();
        try {
            MapType mapType = mapper.getTypeFactory().constructMapType(HashMap.class, key, value);
            map = mapper.readValue(json, mapType);
        } catch (JsonProcessingException e) {
            log.error("Json String:{} convert map<{}, {}> error", json, key.toString(), value.toString());
        }
        return map;
    }


    /**
     * 打印序列化
     */
    public static String toLog(Object o) {
        return objectToJson(LOGGER_MAPPER, o);
    }

    /**
     * Json字符串转对象
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        return jsonToObject(NORMAL_MAPPER, json, clazz);
    }

    public static <R, T> R jsonToObject(String json, Class<R> clazz1, Class<T> clazz2) {
        return jsonToObject(NORMAL_MAPPER, json, clazz1, clazz2);
    }

    public static <T> T mapToObject(Map<String, String> map, Class<T> clazz) {
        return mapToObject(NORMAL_MAPPER, map, clazz);
    }

    /**
     * Json字符串转列表
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        return jsonToList(NORMAL_MAPPER, json, clazz);
    }

    /**
     * Json字符串转Map
     */
    public static <K, V> Map<K, V> jsonToMap(String json, Class<K> key, Class<V> value) {
        return jsonToMap(NORMAL_MAPPER, json, key, value);
    }

}
