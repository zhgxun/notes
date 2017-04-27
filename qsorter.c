#include <stdio.h>
#include <stdlib.h>
#define NUM 40
// 生成一个随机数组
void fillarray(double ar[], int n);
// 显示当前数据
void showarray(const double ar[], int n);
// 自定义的比较函数，从小到大排序
int mycomp(const void * p1, const void * p2);

/**
 * 随机生成40个浮点数，从小到大排序
 */
int main(void)
{
    double vals[NUM];

    fillarray(vals, NUM);

    puts("Rand list:");
    showarray(vals, NUM);

    // 第一个参数为数组的首地址，第二参数为数据项数，第三个参数为数组中每个元素占用空间的大小，第四个参数为自定义的比较函数
    qsort(vals, NUM, sizeof(double), mycomp);

    puts("Sorted list:");
    showarray(vals, NUM);

    return 0;
}

/**
 * 随机生成一个数组
 */
void fillarray(double ar[], int n)
{
    int index;
        ar[index] = (double) rand() / ((double) rand() + 0.1);
    }
}

/**
 * 打印当前数组
 */
void showarray(const double ar[], int n)
{
    int index;
    for (index = 0; index < n; index++) {
        printf("%9.4f", ar[index]);
        // 每排6个元素
        if (index % 6 == 5) {
            putchar('\n');
        }
    }
    if (index % 6 != 0) {
        putchar('\n');
    }
}

/**
 * 按从小到大的排序
 *
 * @notice
 * 如果第一项的值大于第二项，比较函数返回正数；
 * 如果两项相同，返回0；
 * 如果第一项的值小于第二项，返回负数
 */
int mycomp(const void * p1, const void * p2)
{
    // 必须在比较函数的内部声明两个类型正确的指针，并初始化他们分别指向作为参数传入的值
    // c++要求在把void*指针赋给任何类型的指针时必须进行强制类型转化
    const double *a1 = (const double *)p1;
    const double *a2 = (const double *)p2;
    if (*a1 < *a2) {
        return -1;
    } else if (*a1 == *a2) {
        return 0;
    } else {
        return 1;
    }
}

