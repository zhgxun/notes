#include <stdio.h>

int main(int argc, char * argv[])
{
    int count;
    printf("The command line has %d arguments:\n", argc - 1);
    
    //for (count = 1; count < argc; count++) {
    //    printf("%d: %s\n", count, argv[count]);
    //}
    //printf("\n");
    //

    // 第一个为命令名称:w
    //
    argv++;
    while (*argv) {
        printf("%d: %s\n", count, *argv);
        argv++;
    }

    return 0;
}
