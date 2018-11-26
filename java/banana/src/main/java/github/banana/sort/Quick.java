package github.banana.sort;

import java.util.Arrays;

/**
 * 快速排序, 一种比较优秀的排序方案
 * <p>
 * 快速排序的原理：选择一个关键值作为基准值。
 * 比基准值小的都在左边序列（一般是无序的），比基准值大的都在右边（一般是无序的）。
 * 一般选择序列的第一个元素。
 * <p>
 * 一次循环：从后往前比较，用基准值和最后一个值比较，如果比基准值小的交换位置，如果没有继续比较下一个，直到找到第一个比基准值小的值才交换。
 * 找到这个值之后，又从前往后开始比较，如果有比基准值大的，交换位置，如果没有继续比较下一个，直到找到第一个比基准值大的值才交换。
 * 直到从前往后的比较索引>从后往前比较的索引，结束第一次循环，此时，对于基准值来说，左右两边就是有序的了。
 * <p>
 * 接着分别比较左右两边的序列，重复上述的循环。
 * <p>
 * 记住一点, 使用基准值所在的索引进行交换
 */
public class Quick {

    public static void main(String[] args) {
        int[] test = new int[]{3, 2, 8, 6, 1, 9, 2, 4};
//        int[] origin = RandList.getIntList(10);
//        long start = System.currentTimeMillis();
        SortUtil.quick(test, 0, test.length - 1);

        System.out.println(Arrays.toString(test));
        System.exit(0);
//
//        long end = System.currentTimeMillis();
//        System.out.println("消耗的时间: " + (end - start));
    }
}
