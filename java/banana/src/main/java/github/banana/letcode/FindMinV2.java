package github.banana.letcode;

public class FindMinV2 {

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2, 3}));
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(findMin(new int[]{1}));
        System.out.println(findMin(new int[]{2, 1}));
        System.out.println(findMin(new int[]{1, 2, 3}));
    }

    public static int findMin(int[] nums) {
        // 只有一个元素
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0;
        int right = nums.length - 1;

        // 原本有序
        if (nums[right] > nums[0]) {
            return nums[0];
        }

        // 被旋转过
        while (left <= right) {
            int middle = (left + right) / 2;

            // 中间值大于后一个元素
            if (nums[middle] > nums[middle + 1]) {
                return nums[middle + 1];
            }

            // 中间值小于前一个元素
            if (nums[middle] < nums[middle - 1]) {
                return nums[middle];
            }

            // 左半部分有序, 右边移动
            if (nums[middle] > nums[0]) {
                left = middle + 1;
            } else {
                // 左边移动
                right = middle - 1;
            }

        }

        return -1;
    }
}
