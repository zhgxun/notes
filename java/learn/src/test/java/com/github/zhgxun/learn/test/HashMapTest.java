package com.github.zhgxun.learn.test;

import java.util.HashMap;

public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "zhangsan");
        System.out.println(map.get("name"));
    }
}
