package github.banana.letcode;

public class MyAtoi {

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("   -42"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
    }

    // 最简单直接想到的解决方案
    // 去除开头的空格, 怎么保证空格是开头
    // 判断第一个是否为符号
    // 遇见的是数字怎么计算
    // 否则遇见的不是数字怎么退出
    // 这样写代码边界条件比较多, 需要特别留意
    public static int myAtoi(String str) {
        // ASCII 重要的对应关系
        // 空格是32
        // 0 48
        // a 97
        // A 65
        char[] chars = str.toCharArray();
        int length = chars.length;
        boolean haveSign = false;
        int sign = 1;
        boolean haveNum = false;
        // 保存计算结果
        long res = 0;
        for (int i = 0; i < length; i++) {
            // 去除开始的空格, 后面的空格不能在计算
            if (chars[i] == ' ') {
                if (!haveNum && !haveSign) {
                    continue;
                }
                // 空格出现在中间直接退出
                break;
            }
            if (chars[i] == '+' || chars[i] == '-') {
                if (!haveNum && !haveSign) {
                    haveSign = true;
                    if (chars[i] == '-') {
                        sign = -1;
                    }
                    continue;
                }
                // 出现多次正负号直接退出
                break;
            }
            if (chars[i] - 48 >= 0 && chars[i] - 48 <= 9) {
                haveNum = true;
                // 此时是一个有效的数字, 应该计算保存起来, 考虑十进制的情况
                res = res * 10 + chars[i] - 48;
                // 需要考虑溢出的取舍, 超过最大时返回整形最大值, 否则返回整形最小值
                if (sign > 0 && res > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (sign < 0 && res > Integer.MAX_VALUE) {
                    return Integer.MIN_VALUE;
                }

                // 计算完继续进行下面的处理
                continue;
            }

            // 出现非数字直接退出
            break;
        }

        return (int) res * sign;
    }
}
