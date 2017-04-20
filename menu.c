#include <stdio.h>

char get_choice(void);
char get_first(void);
int get_int(void);
void count(void);

/**
 * 菜单
 */
int main(void)
{
    int choice;
    
    while ((choice = get_choice()) != 'q') {
        switch (choice) {
            case 'a':
                printf("Buy low, sell high.\n");
                break;
            case 'b':
                putchar('\a');
                break;
            case 'c':
                count();
                break;
            default:
                printf("Program error!\n");
        }
    }
    printf("Bye.\n");

    return 0;
}

/**
 * 读取一个整数
 */
int get_int(void)
{
    int input;
    char ch;
    // scanf()函数返回成功读取的数量个数，比如输入7abc，会成功读取为7，丢弃abc
    while (scanf("%d", &input) != 1) {
        // 一直读取用户的输入，直到一行结束
        while ((ch = getchar()) != '\n') {
            putchar(ch);
        }
        printf(" is not an integer.\n");
        printf("Please enter a integer value, such as 25, -178, or 3: \n");
    }

    return input;
}

/**
 * 得到用户一个合法的输入
 */
char get_choice(void)
{
    int ch;
    printf("Enter the letter of your choice: \n");
    printf("a. advice        b. bell\n");
    printf("c. count        q. quit\n");
    ch = get_first();
    // 获取一个合法的输入
    while ((ch < 'a' || ch > 'c') && ch != 'q') {
        printf("Please respond with a, b, c or q.\n");
        ch = get_first();
    }

    return ch;
}

/**
 * 读取用户输入的第一个字符
 */
char get_first(void)
{
    int ch;
    ch = getchar();
    // 丢弃其余输入
    while (getchar() != '\n') {
        continue;
    }

    return ch;
}

/**
 * 统计输入的数字从0开始到当前值的循环打印
 */
void count(void)
{
    int n, i;
    printf("Count how far? Enter an integer: \n");
    n = get_int();
    for (i = 0; i <= n; i++) {
        printf("%d\n", i);
    }
    // 清理换行符
    while (getchar() != '\n') {
        continue;
    }
}

