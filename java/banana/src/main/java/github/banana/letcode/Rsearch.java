package github.banana.letcode;

public class Rsearch {

    public static void main(String[] args) {
        int[] nums = new int[]{8, 9, 2, 3, 4};
        System.out.println(search(nums, 9));
    }

    /**
     * 搜索旋转数组中的目标值
     *
     * @param nums   旋转数组
     * @param target 目标值
     * @return 搜索结果
     */
    public static int search(int[] nums, int target) {
        int length = nums.length;

        // 边界条件
        if (length == 0) {
            return -1;
        }
        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        // 数组被旋转的分界点
        int index = index(nums);
        System.out.println("index= " + index);

        // 目标值是最小值
        if (nums[index] == target) {
            return index;
        }
        // 最小值是第一个元素, 则数组完全升序
        if (index == 0) {
            return search(nums, target, 0, length - 1);
        }
        // 此时搜索左边还是右边, 需要留意跟数组最左边的元素比较, 而不是最小元素比较
        // 如果左半部分的最小值比目标值要大, 说明目标值只能存在较小的那一半数组中
        if (nums[0] > target) {
            return search(nums, target, index, length - 1);
        }
        // 否则就是第一个元素比目标元素要小, 则目标元素只会在更大的这部分数组中, 即左半部分数组查找
        return search(nums, target, 0, index - 1);
    }

    /**
     * 在升序数组中搜索一个目标值
     *
     * @param nums   升序数组
     * @param target 目标值
     * @param left   左界
     * @param right  右界
     * @return 搜索结果, 无时为-1
     */
    public static int search(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] > target) {
                right--;
            } else if (nums[middle] < target) {
                left++;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 找到数组中旋转的点
     *
     * @param nums 数组
     * @return 最小值的下标
     */
    public static int index(int[] nums) {
        // 8, 9, 2, 3, 4
        int left = 0;
        int right = nums.length - 1;
        // 如果数组本身已经升序
        if (nums[left] < nums[right]) {
            return 0;
        }

        while (left <= right) {
            int middle = (left + right) / 2;
            // 如果中点的值大于右边的值, 说明找到分界点
            if (nums[middle] > nums[middle + 1]) {
                return middle + 1;
            } else {
                // 此时比较麻烦的是应该往那边找, 因为局部的有序不反应整体的有序
                // 中点的值跟当前的左值比较
                // 4, 5, 6, 7, 0, 1, 2
                // 看上面的例子, 中点7此时比左值4大, 说明左边部分是升序排序的, 只能往相反的方向去找
                // 即是让左值增加到中点的后一个值
                // 否则相反
                // 官方题解似乎有点问题, 这里中点值不能进行加减
                if (nums[middle] > nums[left]) {
                    left = middle;
                } else {
                    right = middle;
                }
            }
        }
        return 0;
    }
}
