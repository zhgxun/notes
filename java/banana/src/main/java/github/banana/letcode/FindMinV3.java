package github.banana.letcode;

public class FindMinV3 {

    public static void main(String[] args) {
//        System.out.println(findMin(new int[]{1, 3, 5}));
//        System.out.println(findMin(new int[]{2, 2, 2, 0, 1}));
        System.out.println(findMin(new int[]{2, 2, 2, 1}));
    }

    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            // 左边部分升序排序, 左边界居中
            if (nums[mid] > nums[right]) {
                left = mid + 1;
                // 右边一定是升序的, 右边居中
            } else if (nums[mid] < nums[right]) {
                // 这里为啥不能减少1, 不能错过边界, 目标一定存在这一块中, 而这一块是未知的块
                right = mid;
            } else {
                // 相等时是无法判断的
                // 右边不确定的, 减少1位即可
                right = right - 1;
            }
        }

        return nums[left];
    }
}
