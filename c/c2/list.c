//
// 存储电影基本信息接口部分
//

#include <stdio.h>
#include <stdlib.h>
#include "list.h" // 一般来说，系统提供的头文件使用<>方式引入，自定义的头文件使用""方式引入，搜索方式依据编译器或者系统而定

/**
 * 拷贝一个电影基本信息到链表中
 * 内部链接函数，影藏在list.c文件中
 * 该函数是实现的一部分，而不是接口的一部分
 * @param item
 * @param node
 */
static void CopyToNode(Item item, Node * node);

/**
 * 初始化链表
 * 设置链表头指针为空
 * @param list
 */
void InitializeList(List * list)
{
    *list = NULL;
}

/**
 * 链表是否为空
 * 判断头指针即可
 * 首次调用，必须初始化链表后才能使用
 * @param list
 * @return
 */
bool ListIsEmpty(const List * list)
{
    return *list == NULL;
}

/**
 * 链表是否已满
 * 对链表而言，链表的大小取决于可用内存量
 * @param list
 * @return
 */
bool ListIsFull(const List * list)
{
    // 新定义一个指针来临时分配一块内存区域，如果分配成功，则链表有空，否则链表已满
    Node * pt;
    bool full;

    pt = (Node *)malloc(sizeof(Node));
    full = pt == NULL;
    // 需要释放临时占用的内存，留给实际的项内容来使用
    free(pt);

    return full;
}

/**
 * 返回链表中项的数量
 * @param list
 * @return
 */
unsigned int ListItemCount(const List * list)
{
    unsigned int count = 0;
    // 重新使用链表头指针，临时变量是为了避免直接操作头指针，破坏原始链表
    Node * node = *list;

    while (node != NULL) {
        ++count;
        // 指向链表的下一个节点
        // 会报不兼容的指针类型警告
        node = node->next;
    }

    return count;
}

/**
 * 添加项到链表的结尾，需要注意链表中的指针更新
 * @param item
 * @param list
 * @return
 */
bool AddItem(Item item, List * list)
{
    Node * pnew;
    Node * scan = *list;

    // 分配一块内存区域
    pnew = (Node *)malloc(sizeof(Node));
    if (pnew == NULL) {
        return false;
    }

    CopyToNode(item, pnew);

    // 链表的第一项
    if (scan == NULL) {
        *list = pnew;
    } else {
        while (scan->next != NULL) {
            // 迭代链表，找到链表的结尾，依次指向链表的下一个地址
            scan = scan->next;
        }
        // 把pnew添加到链表的末尾,scan->next是链表中最后一项，地址是NULL
        scan->next = pnew;
    }

    return true;
}

/**
 * 定义函数作用于链表中的每一个项
 * @param list
 * @param fun
 */
void Traverse(const List * list, void (*fun)(Item item))
{
    // 临时指针来遍历链表
    Node * node = *list;

    while (node != NULL) {
        // 将函数作用于链表中的每一项
        (*fun)(node->item);
        // 找到链表的下一个地址
        node = node->next;
    }
}

/**
 * 释放链表已分配的内存
 * @param list
 */
void EmptyTheList(List * list)
{
    // 临时指针
    Node * save;

    // 头指针
    while (*list != NULL) {
        // 原始链表的下一个地址指针
        save = (*list)->next;
        free(*list);
        // 重新赋值为链表的下一个地址
        *list = save;
    }
}

/**
 * 把一个项拷贝到节点中
 * @param item
 * @param node
 */
static void CopyToNode(Item item, Node * node)
{
    node->item = item;
}
