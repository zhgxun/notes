/**
 * 队列(queue)是具有两个特殊属性的链表。
 * 新项只能添加到队列的末尾
 * 只能从链表的开头移除项
 * 队列是一种先进先出(first in, first out)，缩写为FIFO的数据形式
 */
#ifndef _QUEUE_H_
#define _QUEUE_H_
#include <stdbool.h>

// 队列长度为10
#define MAXQUEUE 10

// 重命名一个整数类型
typedef int Item;

// 储存一个整数类型的结构，需要一个指向结构下一个元素的指针
typedef struct node
{
	Item item;
	struct node * next;
} Node;

// 定义队列结构
typedef struct queue
{
	// 指向队列首项指针
	Node * front;
	// 指向队列尾项指针
	Node * rear;
	// 队列中的项数
	int items;
} Queue;

// 初始化一个队列
void InitializeQueue(Queue *pq);

// 队列是否已满
bool QueueIsFull(const Queue * pq);

// 队列是否已空
bool QueueIsEmpty(const Queue * pq);

// 计算队列中的项数
int QueueItemCount(const Queue * pq);

// 在队列末尾添加项
bool EnQueue(Item item, Queue * pq);

// 从队列开头删除项
bool DeQueue(Item * pitem, Queue * pq);

// 清空队列
void EmptyTheQueue(Queue * pq);

#endif
