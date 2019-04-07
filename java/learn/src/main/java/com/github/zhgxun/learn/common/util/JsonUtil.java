package com.github.zhgxun.learn.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class JsonUtil {

    private JsonUtil() {
    }

    private static Gson GSON;

    static {
        GSON = getGB().create();
    }

    private static GsonBuilder getGB() {
        return new GsonBuilder().disableHtmlEscaping();
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Type type) {
        return (T) GSON.fromJson(json, type);
    }

    public static String toJson(Object src, Type typeOfSrc) {
        return GSON.toJson(src, typeOfSrc);
    }

    public static String toJson(Object o) {
        return GSON.toJson(o);
    }
}
