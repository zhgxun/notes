package github.banana.letcode;

import java.util.Collections;

public class ShortTest {

    public static void main(String[] args) {
        // s1是short类型, 1是int类型, s1 + 1 向上转型为 int 类型, 无法直接赋值给 short 类型, 需要强制类型转换
        // short s1 = 1; s1 = s1 + 1;
        // 显式赋值操作
//        short s1 = 1;
//        s1 = (short) (s1 + 1);
//
//        // 能通过编译, 隐式类型转换
//        // 计算操作, 直接转换
//        short s2 = 1;
//        s2 += 1;
//
//        System.out.println(s2);

        int a = 3;
        Integer b = 3;
        System.out.println(a == b);

        System.out.println(Math.round(11.5));
        System.out.println(Math.round(-11.5));

        // 用最有效率的方法计算2乘以8
        // 2 << 3（左移3位相当于乘以2的3次方，右移3位相当于除以2的3次方）。
        System.out.println(2 << 3);

        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        // false
        System.out.println(s1 == s2);
        // true
        System.out.println(s1 == s5);
        // false
        System.out.println(s1 == s6);
        // true
        System.out.println(s1 == s6.intern());
        // false
        System.out.println(s2 == s2.intern());
    }
}
