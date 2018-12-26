package github.banana.demo;

import java.util.HashMap;

public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "张三");
        System.out.println(map.get("name"));
    }
}
