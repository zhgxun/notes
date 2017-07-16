#include <stdio.h>
void why_me(void);

int main(void)
{
    // 当前文件名，比如predef.c
    printf("The file is %s\n", __FILE__);
    // 当前日期
    printf("The date is %s\n", __DATE__);
    // 当前时间
    printf("The time is %s\n", __TIME__);
    // 当前版本号
    printf("The version is %ld\n", __STDC_VERSION__);
    // 当前行号
    printf("The line is %d\n", __LINE__);
    // 当前函数名 main
    printf("The function is %s\n", __func__);

    why_me();

    return 0;
}

void why_me()
{
    // 当前函数名 why_me
    printf("This function is %s\n", __func__);
    // 当前行
    printf("This line is %d\n", __LINE__);
}

