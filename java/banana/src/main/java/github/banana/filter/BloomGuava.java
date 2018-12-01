package github.banana.filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * guava 和 google-collections 包存在冲突, 需要注意
 */
public class BloomGuava {

    public static void main(String[] args) {
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 1000000, 0.01);
        for (int i = 0; i < 1000000; i++) {
            filter.put(i);
        }

        System.out.println(filter.mightContain(1) ? "数字1存在序列中" : "数字1不存在序列中");
        System.out.println(filter.mightContain(100000) ? "数字100000存在序列中" : "数字100000不存在序列中");
        System.out.println(filter.mightContain(99999999) ? "数字99999999存在序列中" : "数字99999999不存在序列中");
        System.out.println(filter.mightContain(2222) ? "数字2222存在序列中" : "数字2222不存在序列中");
        System.out.println(filter.mightContain(888822332) ? "数字888822332存在序列中" : "数字888822332不存在序列中");
        System.out.println(filter.mightContain(-3) ? "数字-3存在序列中" : "数字-3不存在序列中");
    }
}
