package com.github.zhgxun.learn.common.util;

import lombok.Getter;

@Getter
public class ApiX5ResponseUtil<T> {
    private Header header;
    private T body;

    private static String SUCCESS_MESSAGE = "ok";

    private ApiX5ResponseUtil() {

    }

    private ApiX5ResponseUtil(Header header, T body) {
        this.header = header;
        this.body = body;
    }

    @Getter
    static class Header {
        private int code;
        private String desc;

        Header(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

    private static <T> ApiX5ResponseUtil<T> create(Integer code, String desc, T data) {
        return new ApiX5ResponseUtil<>(new Header(code, desc), data);
    }

    public static <T> ApiX5ResponseUtil<T> success(T data) {
        return create(200, SUCCESS_MESSAGE, data);
    }

    public static <T> ApiX5ResponseUtil<T> fail(Integer code, String desc) {
        return create(code, desc, null);
    }
}
