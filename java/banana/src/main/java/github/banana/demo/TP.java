package github.banana.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * 供应商类型和描述
 */
public class TP {

    public static String getName(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "携程");
        map.put("2", "国航");
        map.put("4", "东航");
        map.put("8", "南航");
        map.put("16", "携程一期H5");
        map.put("32", "必去");
        map.put("64", "携程H5抓取");
        map.put("128", "必去国际");
        map.put("256", "就旅行");
        map.put("512", "必去候补");
        map.put("1024", "去哪儿");
        map.put("2048", "携程H5阿拉丁");
        map.put("ctrip", "携程");

        return map.getOrDefault(id, "未知");
    }

    public static void main(String[] args) {

    }
}
