#include <stdio.h>
#include <string.h>
#include <ctype.h>
#define LIMIT 81

// 声明时可指定类型，不必指定变量
void ToUpper(char * str);
int PunctCount(const char * str);

int main(void)
{
    char line[LIMIT];
    char * find;

    puts("Please center a line:");
    fgets(line, LIMIT, stdin);
    // fgets读取会把换行符存入，多余的字符留在缓冲区，如果存在换行符，替换为空字符
    find = strchr(line, '\n');
    if (find) {
        *find = '\0';
    }

    ToUpper(line);

    puts(line);

    printf("That line has %d punctuation characters.\n", PunctCount(line));

    return 0;
}

/**
 * 将字符全部转为大写形式
 */
void ToUpper(char * str)
{
    // 最后为空字符
    while (*str) {
        *str = toupper(*str);
        str++;
    }
}

/**
 * 统计字符串中的标点符号等数量
 */
int PunctCount(const char * str)
{
    int ct = 0;
    while (*str) {
        if (ispunct(*str)) {
            ct++;
        }
        str++;
    }

    return ct;
}

