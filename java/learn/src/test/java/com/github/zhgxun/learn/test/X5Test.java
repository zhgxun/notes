package com.github.zhgxun.learn.test;

import com.github.zhgxun.learn.common.util.JsonUtil;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

public class X5Test {

    public static void main(String[] args) {
        String s = "{\"header\":{\"code\":500,\"desc\":\"签名非法\"},\"body\":{\"name\":\"田七\", \"age\":18}}";
        Response<Param> paramResponse = JsonUtil.fromJson(s, new TypeToken<Response<Param>>() {
        }.getType());
        // Response(header=Header(code=500, desc=签名非法), body=Param(name=田七, age=18))
        System.out.println(paramResponse);

        String s1 = "{\"header\":{\"code\":500,\"desc\":\"签名非法\"}}";
        Response<Param> paramResponse1 = JsonUtil.fromJson(s1, new TypeToken<Response<Param>>() {
        }.getType());
        // Response(header=Header(code=500, desc=签名非法), body=null)
        System.out.println(paramResponse1);
    }
}

@Data
class Response<T> {
    private Header header;
    private T body;
}

@Data
class Header {
    private Integer code;
    private String desc;
}

@Data
class Param {
    private String name;
    private int age;
}
