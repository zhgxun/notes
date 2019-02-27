package github.banana.letcode;

/**
 * 判断一个数是否为回文数
 * 负数一定不是回文数
 */
public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(1221));
    }

    /**
     * 虽然慢一点但是比较简单直观一些
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        // 1. 负数不是回文数
        if (x < 0) {
            return false;
        }

        // 借助字符串数组来处理
        String s = String.valueOf(x);
        int length = s.length();

        // 2. 一个数的本身回文数也是本身
        if (length == 1) {
            return true;
        }

        // 3. 前后指针法
        // 互为回文串的数字, 对中间两边互为镜像
        /*
         *   两边互为镜像
         *       |
         * |------------
         * | 1 | 2 | 1 |
         * |------------
         *       |
         *
         *   虚拟两边互为镜像
         *         |
         * |----------------
         * | 1 | 2 | 2 | 1 |
         * |----------------
         *         |
         */
        for (int i = 0, j = length - 1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }

        return true;
    }
}
