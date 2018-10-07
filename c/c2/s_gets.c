//
// 读取一样字符串，将末尾的换行符替换为空字符，并丢弃多余的字符，使其不缓存在缓冲区
//

#include <stdio.h>

// 只保存源代码的文件，也需要引入头文件

/**
 * 读取输入数据
 * @param st
 * @param n
 * @return
 */
char * s_gets(char *st, int n)
{
    // 指针声明，通常空一格，但是使用是不空格，效果一样
    char *ret_val;
    int i = 0;

    // 从标准输入中读取指定长度的字符，如果长度范围内，会读取换行符
    ret_val = fgets(st, n, stdin);
    if (ret_val) {
        // 循环，直到遇到换行符或空字符为止
        while (st[i] != '\n' && st[i] != '\0') {
            i++;
        }

        // 此时必须是换行符或者空字符，如果是空字符，则不需要要添加，继续读取多余的字符，直到换行为止
        if (st[i] == '\n') {
            st[i] = '\0';
        } else {
            // 如果读取的长度有限，多余的输入字符会被暂存在缓冲区，被其它函数读取
            while ((getchar()) != '\n') {
            }
        }
    }

    return ret_val;
}

