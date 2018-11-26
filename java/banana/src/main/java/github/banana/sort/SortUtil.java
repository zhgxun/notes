package github.banana.sort;

import java.util.Arrays;

/**
 * 排序工具
 */
public class SortUtil {

    public static void insert(int[] arr) {
        int length = arr.length;

        // 外层循环, 仅仅控制最低循环次数, 即遍历原始序列
        for (int i = 0; i < length; i++) {
            // 排序始终在当前元素之前进行, 循环完毕后确保当前元素是升序或者降序序列的
            // 必须将当前元素在彻底比较一遍, 才能最终确认元素该存放的位置
            for (int j = i; j > 0; j--) {
                // 由于升序排序的优点, 已经排序的序列中的相对最后一个值为最大值, 故大于等于该值的数不需要再排序
                // 说明当前值比相对前面的值都要大, 无需再进行比较, 避免非必要的遍历开销
                if (arr[j] >= arr[j - 1]) {
                    break;
                }

                // 否则需要一直比较下去
                if (arr[j - 1] >= arr[j]) {
                    int v = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = v;
                }
            }
        }
    }

    public static void select(int[] origin) {
        int length = origin.length;
        // 外层循环
        for (int i = 0; i < length; i++) {
            // 在未排序序列中找到最小的值，并记录最小值的下标
            int pos = i;
            // 每次从后续未排序序列中选择一个最小值的下标
            for (int j = i + 1; j < length; j++) {
                if (origin[j] < origin[pos]) {
                    pos = j;
                }
            }

            // 将最小值填充到当前位置
            if (i != pos) {
                int v = origin[i];
                origin[i] = origin[pos];
                origin[pos] = v;
            }
        }
    }

    public static void bubble(int[] origin) {
        int length = origin.length;

        for (int i = 0; i < length; i++) {
            // 每次比较后, 序列逐渐减小
            for (int j = 0; j < length - i - 1; j++) {
                // 每次从剩余的选择中选择最大值
                if (origin[j] > origin[j + 1]) {
                    int v = origin[j];
                    origin[j] = origin[j + 1];
                    origin[j + 1] = v;
                }
            }
        }
    }

    /**
     * 快速排序
     *
     * @param origin 待排序数组
     * @param low    低位, 初始值初始0
     * @param high   高位, 初始值为末尾下标
     */
    public static void quick(int[] origin, int low, int high) {
        int start = low;
        int end = high;
        // 基数值, 即是每次处理的首元素, 一般也直接使用首元素
        int base = origin[low];
        // 低位小于高位时处理, 找寻一次, 直接低位和高位相遇为止
        while (start < end) {
            // 从高位往低位降, 找到比基数小的元素, 记录当前
            // 降找时依然不能不能低位低位标识
            // 找到一个比基数小的数时记录该小数的下标, 同时退出循环
            while (start < end && origin[end] >= base) {
                // 找到一个比基数小的数值的下标
                end--;
            }

            // 如果这两个下标有效, 则交换两个数值
            if (start < end) {
                int temp = origin[end];
                origin[end] = origin[start];
                origin[start] = temp;
                start++;
            }

            // 起始下标依然在降序范围内, 继续寻找
            // 升序方式找到一个比基数大的数
            while (start < end && origin[start] <= base) {
                // 记录下标
                start++;
            }

            // 交换
            if (start < end) {
                int temp = origin[end];
                origin[end] = origin[start];
                origin[start] = temp;
                end--;
            }
        }

        System.out.println(Arrays.toString(origin));

        // 低位还有数
        if (start > low) {
            quick(origin, low, start - 1);
        }

        // 高位还有数
        if (end < high) {
            quick(origin, start + 1, high);
        }
    }
}
