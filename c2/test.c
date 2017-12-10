//
// Created by zhgxun on 10/12/2017.
//
#include <stdio.h>
#include <string.h>
#include <limits.h>

int main(void)
{
    char name[40];
    int age;
    printf("Name:\n");
    scanf("%s %d", name, &age);
    printf("See:\n");
    printf("strlen(name) = %zd\n", strlen(name));
    printf("sizeof(name) = %zd\n", sizeof(name));

    printf("\nage=%d\n", age);

    printf("Max=%d\n", INT_MAX);
    printf("Min=%d\n", INT_MIN);
}
