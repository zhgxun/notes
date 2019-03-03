package github.banana.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 414. 第三大的数
 * <p>
 * 给定一个非空数组, 返回此数组中第三大的数
 * 如果不存在, 则返回数组中最大的数
 * 要求算法时间复杂度必须是O(n)
 * <p>
 * 注意, 要求返回第三大的数, 是指第三大且唯一出现的数
 * 存在两个值为2的数, 它们都排第二
 */
public class ThirdMax {

    public static void main(String[] args) {
        int[] nums = {2, 1, 3};
        System.out.println(new ThirdMax().thirdMax(nums));
    }

    public int thirdMax(int[] nums) {
        // 数组去重
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (!list.contains(num)) {
                list.add(num);
            }
        }

        // 构造一次 int[] 数组
        int size = list.size();
        int[] numbers = new int[size];
        int i = 0;
        for (Integer n : list) {
            numbers[i++] = n;
        }

        // 升序排序
        Arrays.sort(numbers);

        // 元素小于3个取去最大值, 否则取第三大
        int length = numbers.length;
        return length <= 2 ? numbers[length - 1] : numbers[length - 3];
    }
}
