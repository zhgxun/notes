package github.banana.letcode;

public class FindLengthOfLCISV2 {

    public static void main(String[] args) {
        System.out.println(findLengthOfLCIS(new int[]{1, 3, 5, 6, 7}));
        System.out.println(findLengthOfLCIS(new int[]{2, 2, 2, 2, 2, 2}));
    }

    public static int findLengthOfLCIS(int[] nums) {
        int length = nums.length;
        // 记录历史统计的最大连续次数
        int maxCount = 0;
        // 当前累计的连续递增次数
        int count = 0;
        for (int i = 0; i < length; i++) {
            // 每个值都需要统计, 不符合条件时排除
            count++;
            // 不满足时进行历史计数统计
            if (i > 0 && nums[i] <= nums[i - 1]) {
                // 当前值小于等于上一个元素的值, 说明当前值已经不满足计数统计, 需要去除当前的计数
                count--;
                maxCount = maxCount > count ? maxCount : count;
                // 当前计数变更为1
                count = 1;
            }
        }
        // 连续数组需要在这里处理, 比如 [1,3,5,7]
        return maxCount > count ? maxCount : count;
    }
}
