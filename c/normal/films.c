#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TSIZE 45

/**
 * 存储电影信息的结构体
 */
struct film {
    char title[TSIZE];
    int rating;
    struct film * next;
};

char s_gets(char * st, int n);

/**
 * 电影信息
 */
int main(void)
{
    // 指向电信信息的头指针
    struct film * head = NULL;
    // 保存当前电影信息和上一条电影信息
    struct film * prev, * current;
    // 临时字符数组保存电影标题
    char input[TSIZE];

    puts("Enter first movie title:");
    while (s_gets(input, TSIZE) && input[0] != '\0') {
        // 为每一个电影信息分配内存
        current = (struct film *) malloc(sizeof(struct film));

        // 指定头指针
        if (head == NULL) {
            head = current;
        } else {
            prev->next = current;
        }

        // 将当前电影信息指向下一部电影的指针设置为NULL
        current->next = NULL;

        // 存储电影名称
        strcpy(current->title, input);

        puts("Enter you rating <0-10>:");
        scanf("%d", &current->rating);
        // 过滤多余字符
        while (getchar() != '\n') {
            continue;
        }

        puts("Enter next movie title (empty line to stop):");
        // 将当前电影存储为上一部电影
        prev = current;
    }

    if (head == NULL) {
        puts("No data entered.");
    } else {
        puts("Here is the movies list:");
        // 打印电影信息，不能直接使用头指针，避免链表头指针被破坏
        current = head;
        while (current != NULL) {
            printf("Movie: %s, Rating %d;\n", current->title, current->rating);
            current = current->next;
        }

        // 释放内存
        // 在许多环境中，程序结束都会自动释放malloc()分配的内存。但是，最好还是成对调用malloc()和free()
        // 因此，程序在清理内存时为每个已分配的结构都调用free()函数
        current = head;
        while (current != NULL) {
            current = head;
            head = current->next;
            free(current);
        }
    }

    puts("Bye!");

    return 0;
}

