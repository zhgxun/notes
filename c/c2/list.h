//
// ADT 电影基本信息头文件
//

#ifndef C2_LIST_H
#define C2_LIST_H
#include <stdbool.h>
#define SIZE 45

/**
 * 定义一个存储电影基本信息的结构体
 */
struct film {
    char title[SIZE];
    int rating;
};

// 重命名代表电影基本信息结构体为Item
typedef struct film Item;

/**
 * 定义一个保存电影基本信息和下一个项地址的结构体，并重命名为Node
 */
typedef struct node {
    Item item;
    struct note * next; // 指向该Node的下一项地址
} Node;

// 为了管理链表，定义一个List为指向链表开始处的指针
typedef Node * List;

/**
 * 初始化一个链表
 * *list 指向链表的头指针
 * 链表初始化为空
 */
void InitializeList(List * list);

/**
 * 确认链表是否为空
 * @return boolean 如果链表为空，返回true，否则返回false
 */
bool ListIsEmpty(const List * list);

/**
 * 链表是否已满
 * @return boolean
 */
bool ListIsFull(const List * list);

/**
 * 链表中的项数
 * @return unsigned int
 */
unsigned int ListItemCount(const List * list);

/**
 * 在链表的末尾添加项
 * item是一个待添加至链表的项，plist执行一个已初始化的链表
 * @return boolean
 */
bool AddItem(Item item, List * plist);

/**
 * 把函数作用于链表中的每一项
 */
void Traverse(const List * plist, void (*fun)(Item item));

/**
 * 释放已分配的内存
 */
void EmptyTheList(List * list);

#endif //C2_LIST_H
