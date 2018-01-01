package github.banana;

public class Pow {
    public static void main(String[] args) {
        System.out.println(myPow(2.0, 10));
    }

    private static double myPow(double x, int n) {
        // long类型对待
        long N = n;
        // 如果指数小于0, 则转为正数对待
        if (N < 0) {
            // 基础变为分数
            x = 1 / x;
            // 指数变为正数
            N = -N;
        }
        // 初始值1
        double ans = 1;
        double current_product = x;
        // 让指数逐渐减少2倍
        for (long i = N; i > 0; i /= 2) {
            // 奇数时
            if ((i % 2) == 1) {
                System.out.println("奇数时结果为 " + ans + " * " + current_product);
                ans = ans * current_product;
                System.out.println("= " + ans);
            }
            // 偶数都让底数成倍相乘
            current_product = current_product * current_product;
        }
        return ans;
    }
}
