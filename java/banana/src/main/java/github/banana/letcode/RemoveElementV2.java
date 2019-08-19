package github.banana.letcode;

public class RemoveElementV2 {

    public static void main(String[] args) {

    }

    public static int removeElement(int[] nums, int val) {
        int length = nums.length;
        int i = 0;
        for (int j = 0; j < length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }

        // 最后对i进行了自加操作, 故不需要加1
        return i;
    }

    public static int removeElementV2(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return right + 1;
    }
}
