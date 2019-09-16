package github.banana.letcode;

public class LongestCommonPrefixV2 {

    public static void main(String[] args) {
        String[] strings = new String[]{"fslower", "bflow", "flight"};
        longestCommonPrefix(strings);
    }

    public static String longestCommonPrefix(String[] strs) {
        // 字符串为空
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 最大公共前缀最大为最短字符串长度
        int minLength = Integer.MAX_VALUE;
        for (String s : strs) {
            minLength = Math.min(minLength, s.length());
        }

        // 二分方式
        int low = 0;
        int high = minLength;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle)) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        // 遍历方式

        System.out.println("low: " + low + ", high: " + high);
        System.out.println(strs[0].substring(0, (low + high) / 2));

        return "";
    }

    // 判断一个连续的字符是否是公共前缀
    private static boolean isCommonPrefix(String[] strList, int length) {
        String commonPrefix = strList[0].substring(0, length);
        int size = strList.length;
        for (int i = 0; i < size; i++) {
            if (!strList[i].startsWith(commonPrefix)) {
                return false;
            }
        }

        return true;
    }
}
