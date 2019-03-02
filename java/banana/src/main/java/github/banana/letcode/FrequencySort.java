package github.banana.letcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 451. 根据字符出现频率排序
 * <p>
 * 给定一个字符串, 请将字符串里的字符按照出现的频率降序排列
 */
public class FrequencySort {

    public static void main(String[] args) {
        System.out.println(new FrequencySort().frequencySort("tree"));
        System.out.println(new FrequencySort().frequencySort("cccaaa"));
    }

    public String frequencySort(String s) {
        // 实现思路比较简单, 就是要知道每个字符出现的频次, 麻烦在频次排序上, 这个比较啰嗦
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }

        StringBuilder builder = new StringBuilder();

        // 注意这个排序, 自己实现比较啰嗦, 借助api来实现
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        list.forEach(a -> {
            for (int i = 0; i < a.getValue(); i++) {
                builder.append(a.getKey());
            }
        });

        return builder.toString();
    }
}
