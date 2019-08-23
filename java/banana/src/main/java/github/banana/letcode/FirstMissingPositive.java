package github.banana.letcode;

public class FirstMissingPositive {

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
    }

    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;

        // 桶排序的过程, 原地交换
        for (int i = 0; i < len; i++) {
            // nums[i] 必须是正数
            // nums[i] <= len 在桶的范围内
            // nums[i] 是在这个位置目前上的值
            // nums[i] - 1 是它应该在的下标
            // 它应该在的下标的值是否就是它, 如果不是那就要交换, 把它放到自己应该在的下标上去
            // 需要特别注意这里的交换次数可能超过1次
            // 比如:3,4,-1,1
            // -1,4,3,1 i=0时会交换1次,3和-1交换, 因-1小于0被排除
            // -1,1,3,4 1=1时会交换2次,4和1交换, 因为交换完毕后位置1上面的数字还是1, 也不是它应该在党位置, 还需要继续交换, 1和-1交换
            while (nums[i] > 0 && nums[i] < len && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }

        // 看第一个缺失的正数是否在数组中
        for (int i = 0; i < len; i++) {
            // 其实就是位置跟值相等
            if (nums[i] - 1 != i) {
                return i + 1;
            }
        }

        // 否则数组完整, 第一个缺失的整数刚好没有出现
        return len + 1;
    }

    /**
     * 交换两个数
     *
     * @param nums  数组
     * @param left  左边下标
     * @param right 右边下标
     */
    private static void swap(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
