package github.banana.letcode;

/**
 * 移除数组中相同的元素, 不关心顺序和移除后的状态
 * 相同元素保留1个即可
 * <p>
 * 关于快慢指针
 * 慢指针其实是跟踪元素的遍历顺序, 快指针可以是提前进一步或者多步, 是为了提前找到某个状态
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
    }

    public static int removeDuplicates(int[] nums) {
        int length = nums.length;
        int j = 0;
        for (int i = 1; i < length; i++) {
            if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];
            }
        }

        return j + 1;
    }
}
