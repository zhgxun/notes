package github.banana.letcode;

import java.util.Arrays;

public class MaximumProductV2 {

    public static void main(String[] args) {
        System.out.println(maximumProduct(new int[]{-1, 2, 3, 4}));
        System.out.println(maximumProductV2(new int[]{-1, 2, 3, 4}));
    }

    // 预先排序, 性能不够好
    public static int maximumProduct(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        int a = nums[length - 1] * nums[length - 2] * nums[length - 3];
        int b = nums[0] * nums[1] * nums[length - 1];
        return a > b ? a : b;
    }

    // 省去排序占用的空间, 因为排序可以是对数级别的时间复杂度
    public static int maximumProductV2(int[] nums) {
        // 只需要找到最大的三个数和最小的两个数比较乘积即可, 排序的复杂度很高, 这样避免了排序, 一次遍历即可
        // 由于数据只会在 [-1000,1000] 之间, 故最小值可以设置为-1001
        // 第一大的数
        int max1 = -1001;
        // 第二大的数
        int max2 = -1001;
        // 第三大的数
        int max3 = -1001;

        // 最小的数
        int min1 = 1001;
        // 第二小的数
        int min2 = 1001;

        int length = nums.length;
        for (int i = 0; i < length; i++) {
            // 比最大还大
            if (nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            } else if (nums[i] > max2) {
                max3 = max2;
                max2 = nums[i];
            } else if (nums[i] > max3) {
                max3 = nums[i];
            }

            if (nums[i] < min1) {
                min2 = min1;
                min1 = nums[i];
            } else if (nums[i] < min2) {
                min2 = nums[i];
            }
        }

        int a = max1 * max2 * max3;
        int b = min1 * min2 * max1;

        return a > b ? a : b;
    }
}
