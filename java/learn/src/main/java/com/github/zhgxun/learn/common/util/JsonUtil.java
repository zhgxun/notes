package com.github.zhgxun.learn.common.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.github.zhgxun.learn.common.bean.NotifyRequestBody;
import com.github.zhgxun.learn.common.bean.SrvRefundForm;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

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

    public static void main(String[] args) {
        String s = "{\"header\": {\"appid\":\"youpin_test\", \"project\":\"\", \"sign\":\"5CE0070B24460BD07A1B6E41C2D2954C\", \"id\":\"\"},\"body\":{\"partnerId\":65,\"orderId\":\"123,456,789,1001,2223,4561\",\"opCode\":0}}";

        // gson 解析
        JsonObject object = new JsonParser().parse(s).getAsJsonObject();
        System.out.println(object.get("body").getAsJsonObject().toString());

        // fastjson 解析 Feature.OrderedField);
        JSONObject object1 = JSONObject.parseObject(s, Feature.OrderedField);
        System.out.println(object1.get("body").toString());
    }
}
