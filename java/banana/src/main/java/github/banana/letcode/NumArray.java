package github.banana.letcode;

public class NumArray {

    private int[] nums;
    private int length;

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        System.out.println(new NumArray(nums).sumRange(0, 2));
        System.out.println(new NumArray(nums).sumRange(2, 5));
        System.out.println(new NumArray(nums).sumRange(0, 5));
    }

    public NumArray(int[] nums) {
        this.nums = nums;
        this.length = nums.length;
    }

    public int sumRange(int i, int j) {
        int sum = nums[i];
        if (j >= length) {
            j = length - 1;
        }
        for (int k = i + 1; k <= j; k++) {
            sum += nums[k];
        }

        return sum;
    }
}
