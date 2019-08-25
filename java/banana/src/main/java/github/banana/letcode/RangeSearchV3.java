package github.banana.letcode;

public class RangeSearchV3 {

    public static void main(String[] args) {
//        System.out.println(search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
//        System.out.println(search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
//        System.out.println(search(new int[]{2, 5, 0, 0, 0, 1, 2}, 3));
        System.out.println(search(new int[]{1, 3}, 3));
        System.out.println(search(new int[]{3, 1}, 1));
        System.out.println(search(new int[]{3, 1}, 3));
    }

    // 二分查找找旋转点在找目标元素的做法会超时, 故不符合算法要求的复杂度
    public static boolean search(int[] nums, int target) {
        int index = index(nums);
        if (index < 0) {
            return false;
        }

        // 查看中点的元素值跟目标值, 看应该在那部分数组中搜索
        // 刚好就是最小值
        if (nums[index] == target) {
            return true;
        }

        // 如果完全升序搜索整个数组
        if (index == 0) {
            return search(nums, 0, nums.length - 1, target) >= 0;
        }
        // 大于等于第一个值说明在较大的这部分中
        if (target >= nums[0]) {
            return search(nums, 0, index - 1, target) >= 0;
        }
        // 否则在较小的这部分中
        return search(nums, index, nums.length - 1, target) >= 0;
    }

    /**
     * 在指定范围内搜索目标值
     *
     * @param nums   升序排序数组
     * @param left   左边界
     * @param right  有边界
     * @param target 目标值
     * @return 目标值下标
     */
    private static int search(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }

        return -1;
    }

    /**
     * 寻找旋转排序数组的旋转点
     *
     * @param nums 旋转的排序数组
     * @return 旋转点
     */
    private static int index(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // 两个元素
        if (right == 1) {
            return nums[0] > nums[1] ? 1 : 0;
        }

        while (left <= right) {
            int middle = (left + right) / 2;
            // 需要注意的一点是用中点的值和left的值进行比较, 否则会找不到旋转点, 即不知道往哪个方向试探
            if (nums[left] >= nums[middle]) {
                // 如果不存在重复元素则找到, 但是存在重复元素则还不知道边界在哪里, 需要继续找
                if (middle == 0 || nums[middle - 1] > nums[middle]) {
                    return middle;
                } else {
                    // 注意此时不能降低中点, 否则无法找到
                    right = middle;
                }
            } else {
                if (nums[left] < nums[middle]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }

    // 中间元素与左边界比较
    public static boolean searchV2(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return false;
        }

        int left = 0;
        int right = len - 1;

        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] > nums[left]) {
                // 左边严格有序
                // 目标在左边这部分有序数组中
                if (nums[left] <= target && target <= nums[mid]) {
                    // 不累加
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[left]) {
                // 右边这部分严格有序
                // 目标刚好在右边这部分数组中
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                // 重点和左值相等
                // 是目标元素
                if (nums[left] == target) {
                    return true;
                } else {
                    // 左值增加1个位置
                    left = left + 1;
                }
            }

        }

        return nums[left] == target;
    }
}
