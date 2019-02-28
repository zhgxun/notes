package github.banana.letcode;

/**
 * 给定一个包含 n + 1 个整数的数组 nums
 * 其数字都在 1 到 n 之间(包括 1 和 n)
 * 可知至少存在一个重复的整数
 * 假设只有一个重复的整数, 找出这个重复的数
 * <p>
 * 不能更改原数组(假设数组是只读的)
 * 只能使用额外的 O(1) 的空间
 * 时间复杂度小于 O(n2)
 * 数组中只有一个重复的数字, 但它可能不止重复出现一次
 * <p>
 * 题给了我们 n+1 个数, 所有的数都在 [1, n] 区域内
 * 首先让我们证明必定会有一个重复数,
 * 这不禁让我想起了小学华罗庚奥数中的抽屉原理(又叫鸽巢原理), 即如果有十个苹果放到九个抽屉里, 如果苹果全在抽屉里
 * 则至少有一个抽屉里有两个苹果, 这里就不证明了
 * <p>
 * 题目要求我们不能改变原数组, 即不能给原数组排序, 又不能用多余空间
 * 那么哈希表神马的也就不用考虑了, 又说时间小于O(n2), 也就不能用brute force的方法
 * 那我们也就只能考虑用二分搜索法了, 我们在区间 [1, n] 中搜索, 首先求出中点mid, 然后遍历整个数组
 * 统计所有小于等于 mid 的数的个数, 如果个数小于等于mid, 则说明重复值在 [mid+1, n] 之间
 * 反之重复值应在 [1, mid-1] 之间, 然后依次类推, 直到搜索完成, 此时的 low 就是我们要求的重复值
 */
public class FindDuplicate {

    public static void main(String[] args) {

    }

    /**
     * 换检测的解法, 比较烧脑
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (slow == fast) {
                fast = 0;
                while (nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }
    }

    public int findDuplicate1(int[] nums) {
        /**
         *
         *  left             right
         * |--------------------
         * | 1 | 3 | 4 | 2 | 2 |
         * |--------------------
         *   0   1   2   3   4
         *
         * mid = 0 + ( 5 - 0 ) / 2 = 2
         *
         * 遍历小于等于4的个数 = 4
         *
         * mid = 3 + ( 5 - 3 ) / 2 = 4
         *
         * 遍历小于等于2的个数 = 3
         *
         * 直接找到下标4
         */
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2, cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}
