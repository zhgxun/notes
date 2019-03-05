package github.banana.letcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 599. 两个列表的最小索引总和
 * <p>
 * 假设Andy和Doris想在晚餐时选择一家餐厅, 并且他们都有一个表示最喜爱餐厅的列表, 每个餐厅的名字用字符串表示
 * <p>
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅
 * 如果答案不止一个, 则输出所有答案并且不考虑顺序, 你可以假设总是存在一个答案
 */
public class FindRestaurant {

    public static void main(String[] args) {
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> target = new HashMap<>();
        int a = list1.length;
        for (int i = 0; i < a; i++) {
            map.put(list1[i], i);
        }

        int b = list2.length;
        // 最小值默认为两个数组长度或者 Integer.MAX_VALUE
        int min = a + b;
        for (int i = 0; i < b; i++) {
            if (map.containsKey(list2[i])) {
                // 存入最小下标和, 最终取最小值
                Integer j = map.get(list2[i]);
                if (min > i + j) {
                    min = i + j;
                }
                target.put(list2[i], i + j);
            }
        }

        // 遍历获取最小值
        for (Map.Entry<String, Integer> entry : target.entrySet()) {
            if (entry.getValue() == min) {
                result.add(entry.getKey());
            }
        }

        return result.toArray(new String[result.size()]);
    }
}
