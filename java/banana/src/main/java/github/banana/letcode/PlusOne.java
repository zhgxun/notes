package github.banana.letcode;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            // 直接末尾加1
            digits[i]++;
            // 和10取余, 小于10则为本身, 只会到10的情况, 即存在0, 存在则往前一直进位
            // 只会存在99的情况，故使用余数
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        // 将99->100的方式, 初始化一个空数组, 首位直接置为1即可
        digits = new int[digits.length + 1];
        digits[0] = 1;

        return digits;
    }
}
