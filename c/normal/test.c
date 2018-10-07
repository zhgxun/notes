#include <stdio.h>
#include <math.h>

int main()
{
    printf("Test...\n");

    printf("eof = %d\n", EOF);

    printf("2 ** 5 = %f\n", pow(2, 5));

    int c;
    while ((c = getchar()) != EOF) {
        putchar(c);
    }
}

