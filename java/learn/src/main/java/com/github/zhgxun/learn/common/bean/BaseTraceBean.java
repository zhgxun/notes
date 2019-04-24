package com.github.zhgxun.learn.common.bean;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class BaseTraceBean {

    private String callback;
    private List<TraceBean> traces;
}
