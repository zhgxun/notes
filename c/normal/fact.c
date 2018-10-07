//
// Created by zhgxun on 04/12/2017.
//
#include <stdio.h>

int fact(int n);
int tail(int n, int a);

int main(void)
{
    int num = 0;
    num = fact(10);
    printf("%d\n", num);

    int b = 0;
    b = tail(10, 1);
    printf("%d\n", b);
}

/**
 * 递归求阶乘
 * @param n
 * @return
 */
int fact(int n)
{
    // 逐级递归, 最终退出时逐级相乘, 编译器无法优化
    // 必须保存上一次活跃区以维持函数调用信息
    if (n < 0) {
        return 0;
    } else if (n <= 1) {
        return 1;
    } else {
        return n * fact(n - 1);
    }
}

/**
 * 尾递归求阶乘
 * @param n
 * @param a
 * @return
 */
int tail(int n, int a)
{
    // 每次递归都将结果保存下来, 直接返回乘积, 编译器可以优化
    // 无需保存上一次活跃区直接覆盖即可
    if (n < 0) {
        return 0;
    } else if (n == 0) {
        return 1;
    } else if (n == 1) {
        return a;
    } else {
        return tail(n - 1, n * a);
    }
}

