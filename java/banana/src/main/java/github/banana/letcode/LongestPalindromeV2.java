package github.banana.letcode;

public class LongestPalindromeV2 {

    // 中心扩展法, 互为镜像
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 每个字符都要执行2次查找
            // 因为不知道那个中心更长, 所以需要两种都进行比较
            // 中心为本身
            int len1 = expandAroundCenter(s, i, i);
            // 中心为两个元素的空隙
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            // 标记这个回文子串在原字符串中的位置
            // 长度比区间长度长
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // 统计回文长度
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
