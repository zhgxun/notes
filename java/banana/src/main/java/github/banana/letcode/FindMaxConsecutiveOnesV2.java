package github.banana.letcode;

public class FindMaxConsecutiveOnesV2 {

    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
    }

    // 双指针解法效率不高
    public static int findMaxConsecutiveOnes(int[] nums) {
        int length = nums.length;
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 1) {
                count++;
                // 快指针往后寻找并进行计数
                for (int j = i + 1; j < length; j++) {
                    if (nums[j] == 1) {
                        count++;
                    } else {
                        i = j;
                        break;
                    }
                }
            }
            maxCount = Math.max(maxCount, count);
            count = 0;
        }

        return maxCount;
    }

    // 一次遍历法, 充分利用连续的数字1这个条件
    public static int findMaxConsecutiveOnesV2(int[] nums) {
        int length = nums.length;
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = maxCount > count ? maxCount : count;
                count = 0;
            }
        }

        // 最后还需要比较一次, 因为全是 1,1,1,1 时执行不到非条件部分
        return maxCount > count ? maxCount : count;
    }
}
