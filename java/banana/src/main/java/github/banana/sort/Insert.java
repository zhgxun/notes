package github.banana.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序, 以List为例, 数组比较特殊, 可直接交换
 * 时间复杂度为O(n^2), 可以看做为 O(n^2) = O(n) * O(n)
 * <p>
 * 下面为测试用例:
 * 可以看到, 数组很大时, 性能非常的低, 因为几乎要遍历玩所有的数据(排除生成序列的时间)
 * [1, 1, 2, 2, 3, 4, 5, 6, 7, 9]
 * Fri Nov 16 09:15:08 CST 2018
 * Fri Nov 16 09:15:08 CST 2018
 * 数组长度为: 100, 时消耗的时间: 1
 * Fri Nov 16 09:15:08 CST 2018
 * Fri Nov 16 09:15:08 CST 2018
 * 数组长度为: 100000, 时消耗的时间: 4775
 */
public class Insert {

    public static void main(String[] args) {

        int[] origin = RandList.getIntList(10);
        SortUtil.insert(origin);
        System.out.println(Arrays.toString(origin));

        for (int i = 100; i < 100000000; i *= 1000) {
            System.out.println(new Date());
            int[] arr = RandList.getIntList(i);
            System.out.println(new Date());
            long start = System.currentTimeMillis();
            SortUtil.insert(arr);
            long end = System.currentTimeMillis();
            System.out.println("数组长度为: " + i + ", 时消耗的时间: " + (end - start));
        }
    }
}
