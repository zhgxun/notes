package github.banana.letcode;

public class MergeArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 数组1的末尾位置, 注意题目m和n是元素数量, m 不是数组1的最终长度
        int p1 = m - 1;
        // 数组2的末尾位置
        int p2 = n - 1;
        // 合并后的最终末尾位置
        int p = m + n - 1;

        // 往数组1末尾处拷贝元素
        while (p1 >= 0 && p2 >= 0) {
            // 末尾最终存储的数据
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }
        // 数组2剩余元素一次性拷贝
        // 有剩余则说明数组2比较长, 数组1都经填充完毕了, 就把剩下的元素全部拷贝到数组1中
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
}
