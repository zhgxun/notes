package com.github.zhgxun.learn.common.bean;

import lombok.Data;

@Data
public class NotifyRequestBody<T> {

    private NotifyHeader header;
    private T body;

    @Data
    public class NotifyHeader {
        private String appid;
        private String project;
        private String sign;
        private String id;
    }
}
