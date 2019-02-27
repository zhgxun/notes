package github.banana.letcode;

/**
 * 从数组中移除目标值, 新数组追加都数组开始处
 */
public class RemoveElement {

    public static void main(String[] args) {

    }

    public static int removeElement(int[] nums, int val) {
        int length = nums.length;
        int j = 0;
        for (int i = 0; i < length; i++) {
            // 需要保留的值往数组前面追加
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }

        return j;
    }
}
