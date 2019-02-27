package github.banana.letcode;

/**
 * 给定一个排序数组和一个目标值
 * 在数组中找到目标值
 * 并返回其索引
 * 如果目标值不存在于数组中
 * 返回它将会被按顺序插入的位置
 * <p>
 * 解决问题的思路还是遍历, 因为遍历空间或者时间无法满足要求, 才来改善该算法, 这是最基本的主线, 一开始如果不是特别确定, 不用套用高级算法
 */
public class SearchInsert {

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 6};
        System.out.println(searchInsert(a, 5));
        System.out.println(searchInsert(a, 2));
        System.out.println(searchInsert(a, 7));
        System.out.println(searchInsert(a, 0));
    }

    public static int searchInsert(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return length;
    }
}
