package github.banana.letcode;

public class FindPeakElementV2 {

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
        System.out.println(findPeakElement(new int[]{1, 2, 3, 4, 5, 6, 7}));
        System.out.println(findPeakElement(new int[]{2, 1}));
    }

    // 线性遍历, 时间复杂度O(n), 已经很快了, 但是没有达到题目要求的对数时间复杂度
    public static int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            // 任何满足当前值大于下一个值的情形都是峰值
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    // 迭代法, 对数时间复杂度, 效率相当的好
    public static int findPeakElementV2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            // 只能确定在一段下降的序列中, 保留中点的值继续寻找
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 峰值为左边的下标对应的值
        return left;
    }
}
