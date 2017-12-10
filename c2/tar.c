//
// Created by zhgxun on 10/12/2017.
//

#include <stdio.h>
#include <stdlib.h>
#define BASE 0
#define BUTTON 65
#define TOP 90

int main(void)
{
    // 65-90
    // 用户输入的大写字母
    char letter;
    // 塔的深度
    int deep;
    int start;
    int end;
    int i, j;
    i = BASE;

    printf("Please input letter <A-Z>:\n");
    scanf("%c", &letter);
    if (letter < BUTTON || letter > TOP) {
        exit(EXIT_FAILURE);
    }
    deep = letter - BUTTON;
    while (i <= deep) {
        // 一次递增输出大写字母
        for (j = BASE; j <= i; j++) {
            printf("%c", BUTTON+j);
        }
        // 只有一个大写字母A, 直接换行
        if (j > 1) {
            j -= 2;
            for (; j >= BASE; j--) {
                printf("%c", BUTTON + j);
            }
        }
        printf("\n");

        i++;
    }
}
