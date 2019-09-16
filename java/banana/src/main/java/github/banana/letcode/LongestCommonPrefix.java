package github.banana.letcode;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strings = new String[]{"flower", "flow", "flowight"};
        System.out.println(longestCommonPrefixV2(strings));
    }

    // 二分执行效率很高
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 最大公共前缀长度
        int minLen = Integer.MAX_VALUE;
        // 寻找数组中最短的单词字符串
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int low = 1;
        // 单词最小长度, 也可能是最大公共前缀
        int high = minLen;
        // 在可能的最大公共前缀内二分查找
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle)) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private static boolean isCommonPrefix(String[] strs, int len) {
        // 任意取一个公共前缀
        String str1 = strs[0].substring(0, len);
        // 遍历全部单词看是否均包含公共前缀
        for (int i = 1; i < strs.length; i++) {
            // 只要有一个不包含则当前不是最大公共前缀, 直接退出
            if (!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }

    // 容易理解但是执行效率相比二分降低了一半
    public static String longestCommonPrefixV2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int minLength = Integer.MAX_VALUE;
        for (String s : strs) {
            minLength = Math.min(minLength, s.length());
        }

        int maxPrefix = 0;
        for (int i = 0; i <= minLength; i++) {
            maxPrefix = i;
            // 找到第一个不是公共前缀的字符则停止搜索
            if (!isCommonPrefix(strs, i)) {
                maxPrefix = i - 1;
                break;
            }
        }

        return strs[0].substring(0, maxPrefix);
    }
}
