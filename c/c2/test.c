//
// 输出字符串
//

#include <stdio.h>
#include <string.h>

int main(void)
{
    char slide_a[] = "Hello";
    // 没有空字符结尾, 不是一个字符串
    char dont[] = {'A', 'B', 'C'};
    char slide_b[] = "World";

    puts(dont);

    return 0;
}
