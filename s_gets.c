#include <stdio.h>

// 只保存源代码的文件，也需要引入头文件

/**
 * 读取一样字符串，将末尾的换行符替换为空字符，并丢弃多余的字符，使其不缓存在缓冲区
 */
char * s_gets(char * st, int n)
{
    char * ret_val;
    int i = 0;
    int ch;

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
            while ((ch = getchar()) != '\n') {
                printf("正在丢弃多余的字符串%c\n", ch);
                continue;
            }
        }
    }

    return ret_val;
}
