package github.banana.letcode;

/**
 * 最长回文字符串
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println("abba: " + longestPalindrome("abba"));
        System.out.println("a: " + longestPalindrome("a"));
        System.out.println("aba: " + longestPalindrome("abacdfgdcaba"));

        // a = 97
        // b = 98
        // c = 99
        // a = 65
        String s = "abc";
        System.out.println((int) s.charAt(0));
        System.out.println((int) " ".charAt(0));
    }

    /**
     * 回文可以从中点往两边扩散开, 是一个相同的镜像
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        // 记录当前最长回文串的开始坐标和结束坐标
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 奇数长度回文串
            /*
             * abad
             * 奇数长度回文串, 中点其实就是当前数组下标即可切分
             */
            int len1 = expandAroundCenter(s, i, i);
            // 偶数长度回文串
            // abba
            // 如法用下标来直接切分, 需要模拟一个中间点来切分
            int len2 = expandAroundCenter(s, i, i + 1);
            // 截止当前最长回文串的长度
            int len = Math.max(len1, len2);
            // 回文串更长
            if (len > end - start) {
                /*
                 * 1.
                 * |----------------
                 * | a | b | a | d |
                 * |----------------
                 *   0   1   2   3
                 *
                 * 2.
                 * |------------------------
                 * | f | a | b | b | a | d |
                 * |------------------------
                 *   0   1   2   3   4   5
                 *
                 */
                // 偶数回文串和奇数回文串比较特别, 需要注意这个玩意
                // 可以看到回文串的位置更新
                // 对于1
                // 回文串为 aba 长度为3, 此时下标为值 b 的位置, 下标为1, 即是 i = 1, len = 3
                // start = i - (len - 1) / 2 = 1 - (3 - 1) / 2 = 1 - 1 = 0
                // end = i + len / 2 = 1 + 3 / 2 = 1 + 1 = 2
                // 注意计算为舍去法取整
                //
                // 对于2
                // 回文串为 abba 长度为4, 此时下标其实为2, 中点为模拟的 2 和 3 之间, 即下标从 2,3 往两边扩散即可, 即 i = 2, len = 4
                // start = i - (len - 1) / 2 = 2 - (4 - 1) / 2 = 2 - 1 = 1
                // end = i + len / 2 = 2 + 4 / 2 = 4
                // 需要找出下标的计算公式, 这个可能是比较难的部分
                //
                // 更新回文串开始坐标
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
