package github.banana.sort;

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

    public static void quick(int[] origin, int low, int high) {
        int l = low;
        int h = high;
        int base = origin[low];
        while (l < h) {
            while (l < h && origin[h] >= base) {
                h--;
            }

            if (l < h) {
                int temp = origin[h];
                origin[h] = origin[l];
                origin[l] = temp;
                l++;
            }

            while (l < h && origin[l] <= base) {
                l++;
            }

            if (l < h) {
                int temp = origin[h];
                origin[h] = origin[l];
                origin[l] = temp;
                h--;
            }
        }
        if (l > low) quick(origin, low, l - 1);
        if (h < high) quick(origin, l + 1, high);
    }
}
