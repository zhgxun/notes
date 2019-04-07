package com.github.zhgxun.learn.mongo;

import com.github.zhgxun.learn.common.bean.LogisticsTraceBean;
import com.github.zhgxun.learn.common.bean.TraceBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckSerialTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void upsert() {
        List<TraceBean> beans = new ArrayList<>();
        beans.add(new TraceBean("北京亦庄发出", "2019-04-01"));
        beans.add(new TraceBean("北京亦庄出库", "2019-04-02"));
        beans.add(new TraceBean("北京昌平收货", "2019-04-03"));
        beans.add(new TraceBean("北京昌平签收", "2019-04-04"));

        LogisticsTraceBean bean = new LogisticsTraceBean();
        bean.setShipperCode("jd");
        bean.setOrderCode("1-2-3");
        bean.setLogisticCode("V0123");
        bean.setReason("");
        bean.setState("已签收");
        bean.setCallback("1-2-3");
        bean.setTraces(beans);

        Query query = new Query(Criteria.where("callback").is(bean.getCallback()));
        Update update = new Update();
        update.set("iskuaidaoyun", 1);
        // 映射到 MapCode
        update.set("shippercode", bean.getShipperCode());
        // ebussinessid 默认空
        update.set("ebussinessid", bean.getEbussinessId());
        // ordercode
        update.set("ordercode", bean.getOrderCode());
        // logisticcode 快递单号
        update.set("logisticcode", bean.getLogisticCode());
        // success = false
        update.set("success", false);
        // reason 默认空
        update.set("reason", "");
        // state 快递状态
        update.set("state", bean.getState());
        // callback 同 ordercode
        update.set("callback", bean.getCallback());
        // callback_time 当前 UNIX 时间戳
        update.set("callback_time", bean.getCallbackTime());
        // subscribe_time 默认0
        update.set("subscribe_time", 0);
        // traces 快递链路信息
        update.set("traces", bean.getTraces());

        System.out.println(mongoTemplate.upsert(query, update, LogisticsTraceBean.class, "kuaidibird"));
    }
}
