//
// Created by zhgxun on 10/12/2017.
//
#include <stdio.h>
//#include <string.h>
//#include <limits.h>
//#define S "My name is test"

void swap(int *, int *);

int main(void)
{
    int a = 10, b = 20;
    swap(&a, &b);
    printf("a=%d, b=%d\n", a, b);
//    printf("%d\n", INT_MIN);
//    printf("%d\n", INT_MAX);
//    printf("strlen(s)=%zd, sizeof(s)=%zd\n", strlen(S), sizeof(S));
//    char name[40];
//    int age;
//    printf("Name:\n");
//    scanf("%s %d", name, &age);
//    printf("See:\n");
//    printf("strlen(name) = %zd\n", strlen(name));
//    printf("sizeof(name) = %zd\n", sizeof(name));
//
//    printf("\nage=%d\n", age);
//
//    printf("Max=%d\n", INT_MAX);
//    printf("Min=%d\n", INT_MIN);
//    age = 30;
//    printf("%p\n", &age);
//
//    int a, b;
//    a = 10;
//    b = 20;
//    temp(&a, &b);
//    printf("a=%d, b=%d\n", a, b);
//    int days[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//    int size = 9;
//    for (int i = 0; i < size; ++i) {
//        printf("%d", days[i]);
//    }
//    printf("\n");
//
//    int * p;
//    p = days;
//    for (int j = 0; j < size; ++j) {
//        printf("%d", *(p+j));
//    }
//    printf("\n");

}

void swap(int * a, int * b)
{
    int t;
    t = *a;
    *a = *b;
    *b = t;
}

