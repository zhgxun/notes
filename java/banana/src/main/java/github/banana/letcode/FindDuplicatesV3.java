package github.banana.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicatesV3 {

    public static void main(String[] args) {
        System.out.println(findDuplicatesV2(new int[]{1, 1}));
        System.out.println(findDuplicatesV2(new int[]{10, 2, 5, 10, 9, 1, 1, 4, 3, 7}));
    }

    // 最简单的解法, hash解法, 但是不满足题目要求的时间和空间复杂度
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();

        int length = nums.length;

        Map<Integer, Integer> map = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 2) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    // 必须用桶排序, 占用指定的位置
    // 注意理解重复的数, 题目说了有些元素出现2次, 其它元素出现一次, 则重复的元素归类到的位置其实也是缺失的元素的位置
    // 这点非常重要
    // 可以使用系统函数排序
    // 但是效率依然不高呀
    public static List<Integer> findDuplicatesV2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }

        int length = nums.length;

        // 元素是从1开始到n之间的数字
        for (int i = 0; i < length; i++) {
            // 当前位置不是它
            // 那他应该在的位置
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        System.out.println(Arrays.toString(nums));

        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                res.add(nums[i]);
            }
        }

        return res;
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    // 使用系统函数排序比桶排序好一点但是依然不高
    public static List<Integer> findDuplicatesV3(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                res.add(nums[i]);
            }
        }

        return res;
    }

    // 采用额外数组空间来存储目标和
    // 一次遍历解决, 两次数组遍历 2 O(n) 时间复杂度, 但是空间复杂度也是 O(n) 了
    // 这个就是非常标准的以空间换取时间的做法
    public static List<Integer> findDuplicatesV4(int[] nums) {
        // 用一个新数组和来存储目标元素出现的次数
        // 其中元素为下标, 出现次数为值, 这个想法比较独特, 空间占用多了但是一次就解答完毕
        int[] sum = new int[nums.length + 1];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            sum[nums[i]] += 1;
        }
        for (int j = 0; j < sum.length; j++) {
            if (sum[j] == 2) {
                list.add(j);
            }
        }
        return list;
    }
}
