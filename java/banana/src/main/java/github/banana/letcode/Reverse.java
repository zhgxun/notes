package github.banana.letcode;

public class Reverse {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE / 10);
        System.out.println(reverse(321));
    }

    /**
     * 注意充分利用取余和进行出发运算
     * 取余直接获取余数
     * 除法运算每次缩小10倍
     * 惩罚运算每次扩大10倍
     * 加上余数即可
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            // 321 % 10 = 1
            // 直接获得个位数, 百位数...
            // 取余运算, 即取出当前数字的余数
            int pop = x % 10;
            // 321 / 10 = 32
            // 做除法运算
            x /= 10;
            // 唯一麻烦的时, 可能数字一翻转, 就数字溢出了
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            // 依次扩大10倍并加上余数即可
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
