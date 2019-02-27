package github.banana.letcode;

/**
 * 给定两个有序整数数组 nums1 和 nums2, 将 nums2 合并到 nums1 中, 使得 num1 成为一个有序数组
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素
 * <p>
 * 不管升序还是降序, 从后往前排序完毕, 糟糕时候可能全部排序, 即数组整体移动, 但是只移动一次
 * 而不是每次都移动数组性能要好一些
 */
public class MergeArr {

    public static void main(String[] args) {

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 从尾部开始，向前移动
        int tail = m + n - 1;
        m--;
        n--;
        /**
         * nums1                     m                tail
         * |----------------------------------------------------
         * | x | 1 | 2 | 2 | 3 | 4 | 8 | 10 | 10 | 12 | 14 |
         * |-----------------------------------------------------
         *   0   1   2   3   4   5    6    7   8   9   10
         *
         * nums2           n
         * |------------------
         * | 1 | x | xx | xx |
         * -------------------
         *   0   1    2   3
         *
         * m = 6, n = 3, tail[10] = 14
         * m = 6, n = 2, tail[9] = 12
         * m = 5, n = 2, tail[8] = 10
         * m = 4, n = 2, tail[7] = 10
         * m = 4, n = 1, tail[6] = 8
         * m = 4, n = 0, tail[5] = 4
         * m = 3, n = 0, tail[4] = 3
         * m = 2, n = 0, tail[3] = 2
         * m = 1, n = 0, tail[2] = 2
         * m = 0, n = 0, tail[1] = 1
         * m = -1, n = 0
         *               tail[0] = 1
         * 最后剩余的直接拷贝, 其实就是前面的空洞补齐
         *
         */
        while (m >= 0 && n >= 0) {
            if (nums1[m] >= nums2[n]) {
                nums1[tail--] = nums1[m--];
            } else {
                nums1[tail--] = nums2[n--];
            }
        }
        // nums1中剩余的前n个数复制到nums1
        while (n >= 0) {
            nums1[n] = nums2[n--];
        }
    }
}
