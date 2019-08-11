package github.banana.letcode;

/**
 * 寻找两个有序数组的中位数
 * <p>
 * 在统计中，中位数被用来：
 * <p>
 * 将一个集合划分为两个长度相等的子集，其中一个子集中的元素总是大于另一个子集中的元素。
 * <p>
 * 首先，让我们在任一位置 i 将 A 划分成两个部分：
 * <p>
 * <pre>
 *       left_A             |        right_A
 * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
 * </pre>
 * 由于 A 中有 m 个元素， 所以我们有 m+1 种划分的方法（i=0∼m）。
 * <p>
 * 我们知道：len(left_A)=i,len(right_A)=m−i.
 * <p>
 * 注意：当 i = 0 时，left_A 为空集， 而当 i = m 时, right_A 为空集。
 * <p>
 * 采用同样的方式，我们在任一位置 j 将 B 划分成两个部分：
 * <pre>
 *       left_B             |        right_B
 * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
 * 将 left_A 和 left_B 放入一个集合，并将 right_A 和 right_B 放入另一个集合。 再把这两个新的集合分别命名为 left_part 和 right_part：
 * </pre>
 * <pre>
 *       left_part          |        right_part
 * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
 * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
 * <pre>
 * 如果我们可以确认：
 * 1. len(left_part) = len(right_part)
 * 2. max(left_part) ≤ min(right_part)
 * 那么，我们已经将 {A,B} 中的所有元素划分为相同长度的两个部分，且其中一部分中的元素总是大于另一部分中的元素。那么：
 * median= (max(left_part) + min(right_part)) / 2
 * <p>
 * <p>
 * 要确保这两个条件，我们只需要保证：
 * 1. i+j=m−i+n−j（或：m - i + n - j + 1）
 * 如果 n≥m，只需要使 i = 0~m, j=(m+n+1)/2 - i
 * 2. B[j−1]≤A[i] 以及 A[i−1] ≤ B[j]
 * <p>
 * 在目标对象[0, m] 中使用二叉搜索j，使得B[j−1]≤A[i] 以及 A[i−1] ≤ B[j]，j=(m+n+1)/2 - i
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        System.out.println(findMedianSortedArraysStudy(nums1, nums2));
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArraysStudy(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;

        // 确保左边数组长度比右边大, 否则交换
        if (m > n) {
            // 交换数组
            int[] temp = A;
            A = B;
            B = temp;

            // 交换长度
            int tmp = m;
            m = n;
            n = tmp;
        }

        // 在 [0, m] 中二分搜索一个位置j即可
        int iMin = 0, iMax = m;
        // 根据推论, 这部分是固定的, 需要根据当前的j动态变化即可
        int halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            // i用于切分A
            int i = (iMin + iMax) / 2;
            // j用于切分B
            int j = halfLen - i;

            /*
             * <pre>
             *       left_part          |        right_part
             * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
             * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
             * </pre>
             */

            // 集合B左边部分的最大值大于集合A右边部分的最小值
            // i太小需要往后移动
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1;
            } else if (i > iMin && A[i - 1] > B[j]) {
                // 集合A左边的最大值比集合B右边的最小值大, 说明i太大了
                iMax = i - 1; // i is too big
            } else {
                // 说明此时i是正要找的值
                int maxLeft;
                // i=0则集合A已经到最左边
                // 左边最大值为集合B的最大值
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    // j=0说明集合B已经到达最左边
                    // 左边最大值为集合A的最大值
                    maxLeft = A[i - 1];
                } else {
                    // 否则集合AB均不为空, 取两个最大值中的最大值
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }

                // m + n 和2取余为1则为奇数, 中位数刚好为左边较大值
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                // m + n 为偶数, 中位数为左边较大值和右边较小值的的平均值
                int minRight;
                // i=m 则集合A到头, 取B的最小值
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    // j=n则集合B到头, 取A的最小值
                    minRight = A[i];
                } else {
                    // 否则取两个的最大值
                    minRight = Math.min(B[j], A[i]);
                }

                // 左边最大值和右边最小值的平均数为中位数
                return (maxLeft + minRight) / 2.0;
            }
        }

        // 不存在中位数
        return 0.0;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 数组长度
        int m = nums1.length;
        int n = nums2.length;

        // 必须保证 n >= m, 这个条件是个注意点
        if (m > n) {
            // 交换数组
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;

            // 交换长度, 也可以直接再次获取值
            int len = n;
            n = m;
            m = len;
        }

        int min = 0, max = m, half = (m + n + 1) / 2;
        while (min <= max) {
            // 确定i和j切点
            int i = (min + max) / 2;
            int j = half - i;

            /*
             * 用i将集合nums1且分开, 用j将集合nums2且分开
             * <pre>
             *                       left_part           |       right_part
             *     nums1[0], nums1[1], ..., nums1[i - 1] | nums1[i], ..., nums[m - i]
             *     nums2[0], nums2[1], ..., nums2[j - 1] | nums2[j], ..., nums[n - j]
             * </pre>
             * 在[0, m] 中搜索j, 使得 nums1[i - 1] <= nums2[j], nums2[j - 1] <= nums2[i]
             * 并且 i + j = m - i + n - j + 1, 即 j = (m + n + 1) / 2 -i
             *
             * 满足该条件则为两个有序数组的中位数
             */
            if (i > min && nums1[i - 1] > nums2[j]) {
                // 向右滑动, 使值变小, 即最大值变为当前位置
                max = i - 1;
            } else if (i < max && nums2[j - 1] > nums1[i]) {
                // 向左滑动, 使值变大, 即最小值变为当前位置
                min = i + 1;
            } else {
                // 找到中位数位置
                // 该处存在临界条件
                // 找到左边最大值
                int maxLeft;
                if (i == 0) {
                    // 集合nums1已经到最左边, 此时nums1已经没有了左边部分, 只考虑nums2的左边部分
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    // 集合nums2已经到达最左边, 集合 nums2 已经没有了左边, 只考虑 nums1 的左边部分
                    maxLeft = nums1[i - 1];
                } else {
                    // 两个集合均有左边, 取左边较大值
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }

                // 如果元素个数是奇数, 则此时左边最大为中位数
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                // 元素个数为偶数, 则需要计算左右两边平均数
                int minRight;
                if (i == m) {
                    // nums1已经没有了右边部分, 取nums2的右边最小即可
                    minRight = nums2[j];
                } else if (j == n) {
                    // nums2已经没有了右边部分, 取nums1的右边最小即可
                    minRight = nums1[i];
                } else {
                    // 取右边部分的较小值
                    minRight = Math.min(nums1[i], nums2[j]);
                }

                // 注意该处需要返回浮点数
                return (maxLeft + minRight) / 2.0;
            }
        }

        return 0.0;
    }
}
