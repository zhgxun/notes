package github.banana.sort;

/**
 * 排序工具
 */
public class SortUtil {

    public static void insert(int[] arr) {
        int length = arr.length;

        // 外层循环, 仅仅控制最低村换次数, 即遍历原始序列
        for (int i = 0; i < length; i++) {
            // 排序始终在当前元素之前进行, 循环完毕后确保当前元素是升序或者降序序列的
            // 必须将当前元素在彻底比较一遍, 才能最终确认元素该存放的位置
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] >= arr[j]) {
                    int v = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = v;
                }
            }
        }
    }
}
