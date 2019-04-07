package github.banana.serial;

import github.banana.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
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

        System.out.println(JsonUtils.toJson(bean));
    }
}
