package github.banana.letcode;

import java.util.Arrays;

public class MajorityElement {

    // 由于众数至少占了n/2部分, 故排序后的这个位置一定是众数的位置
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // 投票解法
    public int majorityElementV2(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            // 投票计数为0则说明前面的众数和非众数数量一样多, 但由于众数比非众数多, 故最终留下来的一定是众数
            if (count == 0) {
                // 将当前元素作为众数
                candidate = num;
            }
            // 相等则众数累计和增加, 否则降低累计和
            count += (num == candidate) ? 1 : -1;
        }

        // 最后剩下的一定是众数
        return candidate;
    }
}
