package github.banana.letcode;

public class DominantIndex {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 6, 1, 0};
        System.out.println(dominantIndex(nums));
        int[] nums1 = new int[]{1, 2, 3, 4};
        System.out.println(dominantIndex(nums1));
    }

    // 2次遍历效率已经足够高
    // 250个测试用例耗时1ms
    public static int dominantIndex(int[] nums) {
        int length = nums.length;
        // 单个元素比较特殊, 符合条件
        if (length == 1) {
            return 0;
        }

        int maxIndex = 0;
        // 一次遍历的时间复杂度是O(n)
        // 快速排序的时间复杂度 O(n log n) 一般都会比一次遍历大
        for (int i = 1; i < length; i++) {
            // 找最大值
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // 看最大值是否是所有其它值的两倍大
        for (int i = 0; i < length; i++) {
            if (i == maxIndex) {
                continue;
            }
            if (nums[maxIndex] < nums[i] * 2) {
                return -1;
            }
        }

        return maxIndex;
    }
}
