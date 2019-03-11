package github.banana.letcode;

import java.util.Arrays;

/**
 * 645. 错误的集合
 * <p>
 * 集合 S 包含从1到 n 的整数
 * 不幸的是, 因为数据错误, 导致集合里面某一个元素复制了成了集合里面的另外一个元素的值, 导致集合丢失了一个整数并且有一个元素重复
 * <p>
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果
 * 你的任务是首先寻找到重复出现的整数, 再找到丢失的整数, 将它们以数组的形式返回
 * <p>
 * 给定数组的长度范围是 [2, 10000]
 * 给定的数组是无序的
 */
public class FindErrorNums {

    public static void main(String[] args) {
        int[] nums = {2, 2};
        System.out.println(Arrays.toString(new FindErrorNums().findErrorNums(nums)));
        int[] nums1 = {1, 2, 2, 4};
        System.out.println(Arrays.toString(new FindErrorNums().findErrorNums(nums1)));
        int[] nums2 = {3, 2, 3, 4, 6, 5};
        System.out.println(Arrays.toString(new FindErrorNums().findErrorNums(nums2)));
    }

    public int[] findErrorNums(int[] nums) {
        // 思路就是找到重复的元素, 采用求和公式来找差
        int[] res = new int[2];
        if (nums == null || nums.length < 2) {
            return res;
        }
        boolean[] isIn = new boolean[nums.length + 1];
        // 求和公式 sum = (n+1)*n/2 (n为个数)
        int sum = (1 + nums.length) * nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            // 重复的元素
            if (isIn[nums[i]]) {
                res[0] = nums[i];
                // 重复的元素不记录进求和的部分
            } else {
                isIn[nums[i]] = true;
                // 求和的方式找到缺失的数字
                sum -= nums[i];
            }
        }
        res[1] = sum;
        return res;
    }
}
