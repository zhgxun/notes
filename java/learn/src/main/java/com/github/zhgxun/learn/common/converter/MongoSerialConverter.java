package com.github.zhgxun.learn.common.converter;

import com.github.zhgxun.learn.common.bean.TraceBean;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;

public class MongoSerialConverter implements Converter<Document, TraceBean> {

    @Override
    public TraceBean convert(Document source) {
        System.out.println("Source: " + source.toJson());
        Document document = (Document) source.get("traces");
        System.out.println("Document: " + document.toString());
        return null;
    }
}
