package com.github.zhgxun.learn.common.converter;

import com.github.zhgxun.learn.common.bean.BaseTraceBean;
import com.github.zhgxun.learn.common.bean.LogisticsTraceBean;
import com.github.zhgxun.learn.common.bean.TraceBean;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;
import java.util.List;

@Configuration
public class MongoSelfConverter {

    // 根据类型来确定数据读取转换器
    enum DocumentReadConverter implements Converter<Document, LogisticsTraceBean> {

        INSTANCE;

        @Override
        public LogisticsTraceBean convert(Document source) {
            System.out.println("===================");
            System.out.println("Mongo LogisticsTraceBean 读转换器...");
            System.out.println(source);
            System.out.println("===================");
            return null;
        }
    }

    enum DocumentReadV2Converter implements Converter<Document, BaseTraceBean> {

        INSTANCE;

        @Override
        public BaseTraceBean convert(Document source) {
            System.out.println("===================");
            System.out.println("Mongo BaseTraceBean 读转换器...");
            System.out.println(source);
            System.out.println("===================");
            return null;
        }
    }

    // ConditionalConverter
    enum DocumentWriteConverter implements Converter<List<TraceBean>, Document>, ConditionalConverter {

        INSTANCE;

        @Override
        public Document convert(List<TraceBean> source) {
            System.out.println("===================");
            System.out.println("Mongo BaseTraceBean 写转换器...");
            System.out.println(source);
            System.out.println("===================");
            if (source == null) {
                return null;
            }

            Document object = new Document();
//            object.put("callback", source.getCallback());
//            object.put("traces", source.getTraces());
            return object;
        }

        @Override
        public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
            System.out.println("=========================");
            System.out.println("匹配目标解析器");
            System.out.println(sourceType);
            System.out.println(targetType);
            System.out.println("=========================");
            return false;
        }
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                DocumentWriteConverter.INSTANCE,
                DocumentReadConverter.INSTANCE,
                DocumentReadV2Converter.INSTANCE
        ));
    }
}
