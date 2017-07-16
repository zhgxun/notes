#include <stdio.h>
#include <string.h>
#define SIZE 40
#define TARGSIZE 7
#define LIMIT 5

char * s_gets(char * st, int n);

int main(void)
{
    // 保存符合条件，即是以字符q开头的字符串
    char qwords[LIMIT][TARGSIZE];
    // 存储每次输入的临时字符串
    char temp[SIZE];
    int i = 0;

    printf("Enter %d words beginning with q:\n", LIMIT);

    while (i < LIMIT && s_gets(temp, SIZE)) {
        if (temp[0] != 'q') {
            printf("%s doesn't begin with q\n", temp);
        } else {
            // 第三个参数指明拷贝的自最大字符数
            strncpy(qwords[i], temp, TARGSIZE - 1);
            // 末尾添加空字符
            qwords[i][TARGSIZE - 1] = '\0';
            i++;
        }
    }

    puts("Here are the words accepted:");
    for (i = 0; i < LIMIT; i++) {
        puts(qwords[i]);
    }

    return 0;
}

