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
}
