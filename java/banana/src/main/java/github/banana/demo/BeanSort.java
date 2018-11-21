package github.banana.demo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BeanSort {

    public static void main(String[] args) {
        List<Bean> beans = new ArrayList<>();
        beans.add(new Bean("1111", "test1", "数据1"));
        beans.add(new Bean("test1", "test2", "数据2"));
        beans.add(new Bean("test2", "test3", "数据3"));
        beans.add(new Bean("test5", "test6", "数据4"));
        beans.add(new Bean("test6", "test7", "数据5"));
        beans.add(new Bean("test3", "test4", "数据6"));
        beans.add(new Bean("test4", "test5", "数据7"));

        Map<String, Bean> map = new TreeMap<>();
        for (Bean b : beans) {
            map.put(b.getTwo(), b);
        }

        for (Map.Entry<String, Bean> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

@Data
class Bean {
    private String one;
    private String two;
    private String desc;

    Bean(String one, String two, String desc) {
        this.one = one;
        this.two = two;
        this.desc = desc;
    }
}
