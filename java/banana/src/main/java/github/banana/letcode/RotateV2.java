package github.banana.letcode;

public class RotateV2 {

    public static void main(String[] args) {
        rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        rotateV2(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
    }

    // 这个常规移动效率比较低, 属于暴力解法, 时间复杂度O(n*k)如果k是n则为平方时间复杂度, 空间复杂度每次只占一个临时位置
    public static void rotate(int[] nums, int k) {
        // 可以理解为将数组后面的元素一次插入到数组的头部的位置, 数组需要移动位置
        int length = nums.length;
        for (int i = 0; i < k; i++) {
            // 将数组元素一次移动到数组的首部
            int temp = nums[length - 1];
            // 因为是旋转数组, 头部也是升序的, 故每次移动非头部的所有元素, 留出头元素即可
            for (int j = length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            // 将元素放入首位的位置即可
            nums[0] = temp;
        }
    }

    // 新数组解法, 该种解法已经比上一个解法快很多了, 但是要注意下标的处理方式, 如果实在不行, 就用新下标遍历次数也是一样的
    public static void rotateV2(int[] nums, int k) {
        // 使用一个新数组的方法, 时间复杂度为O(n), 空间复杂度也为O(n), 即新数组占用的空间
        // 我们可以用一个额外的数组来将每个元素放到正确的位置上, 也就是原本数组里下标为 i 的我们把它放到 (i+k)%数组长度 的位置
        // 数组下标这一点如果想不到, 就用原始的先拷贝k个在拷贝剩余也没多大的问题
        // 然后把新的数组拷贝到原数组中

        int length = nums.length;
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[(i + k) % length] = nums[i];
        }
        for (int i = 0; i < length; i++) {
            nums[i] = res[i];
        }
    }

    // 使用反转
    // 时间复杂度O(n), 空间复杂度O(1)
    // 这个算是目前的最优解了
    public static void rotateV3(int[] nums, int k) {
        /**
         * 原始数组                  : 1 2 3 4 5 6 7
         * 反转所有数字后             : 7 6 5 4 3 2 1
         * 反转前 k 个数字后          : 5 6 7 4 3 2 1
         * 反转后 n-k 个数字后        : 5 6 7 1 2 3 4 --> 结果
         */
        // 注意这里要取余, 因为旋转的长度超过原数组长度时会有一些特殊的变化, 其余部分是不需要旋转的
        k %= nums.length;
        // 反转原始数组
        reverse(nums, 0, nums.length - 1);
        // 反转前半部分
        reverse(nums, 0, k - 1);
        // 反转后半部分
        reverse(nums, k, nums.length - 1);
    }

    /**
     * 反转一段连续的数组
     *
     * @param nums  原始数组
     * @param start 开始反转的下标
     * @param end   结束反转的下标
     */
    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
