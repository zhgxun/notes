#include <stdio.h>
#include <string.h>
#define WORDS "Test" // 定义一个原始字符串
#define SIZE 40 // 存储40个字符

int main(void)
{
    // 指针指向字符串首字符
    const char * orig = WORDS;
    char copy[SIZE] = "This is a String.";
    char * ps;

    puts(WORDS);
    puts(copy);

    // 数组名本身是指向字符数组的头指针,copy + 8即指针指向字符串第8个字符
    // 下标为7的字符是a, 函数strcpy从该处开始拷贝原始字符orig到末尾, 在允许
    // 的范围内, 函数返回目标字符串该处开始到末尾的字符
    // 超出最大范围运行时报错
    ps = strcpy(copy + 8, orig);

    // 目标字符串被改变
    puts(copy);
    // 新增加的字符串
    puts(ps);

    return 0;
}

