#include <stdio.h>
#include <stdlib.h>
#include "queue.h"

static void CopyToNode(Item item, Node * pn);
static void CopyToItem(Node * pn, Item * pi);

/**
 * 初始化队列为空
 * 把队列首项和尾项的指针设置为NULL，并把项数(items成员)设置为0
 * 这样，通过检查items的值很方便检查队列是否已满，是否为空
 */
void InitializeQueue(Queue * pq)
{
	pq->front = pq->rear = NULL;
	pq->items = 0;
}

/**
 * 队列是否已满
 */
bool QueueIsFull(const Queue * pq)
{
	return pq->items == MAXQUEUE;
}

/**
 * 队列是否为空
 */
bool QueueIsEmpty(const Queue * pq)
{
	return pq->items == 0;
}

/**
 * 队列中元素的个数
 */
int QueueItemCount(const Queue * pq)
{
	return pq->items;
}

/**
 * 把项添加到队列中
 * 1、创建一个新节点
 * 2、把项拷贝到节点中
 * 3、设置节点的next指针为NULL,表明该节点是最后一个节点
 * 4、设置当前尾节点的next指针指向新节点，把新节点链接到队列中
 * 5、把rear指针指向新节点，以便找到最后的节点
 * 6、项数加1
 * 
 * 两种特殊情况：
 * 1、如果队列为空，应该把front指针设置为指向新节点，因为如果队列中只有一个节点，那么这个节点既是首节点也是尾节点
 * 2、如果函数不能为节点分配所需的内存，则必须执行一些动作，因为大多数情况下我们使用的是小型队列，这种情况很少发生，
 * 所以如果程序运行的内存不足，我们只能通过函数终止程序
 */
bool EnQueue(Item item, Queue * pq)
{
	Node * pnew;
	if (QueueIsFull(pq)) {
		return false;
	}

	pnew = (Node *)malloc(sizeof(Node));
	if (pnew == NULL) {
		fprintf(stderr, "Unable to allocate memory\n");
		exit(1);
	}

	// 拷贝新项到节点中，并设置新项的下一个地址为NULL
	CopyToNode(item, pq);
	pnew->next = NULL;

	if (QueueIsEmpty(pq)) {
		// 为空时该新项同时位于队列首端和尾端
		pq->front = pnew;
	} else {
		// 否则链接到队列尾端
		pq->rear->next = pnew;
	}
	// 记录队列尾端的位置
	pq->rear = pnew;
	// 记录队列项数
	pq->items++;

	return true;
}

/**
 * 从队列的首端删除项
 * 1、把项拷贝到给定的变量中
 * 2、释放空出的节点使用的内存空间
 * 3、重置首端指针指向队列的下一项
 * 4、如果删除最后一项，把首端指针和尾端指针都设置为NULL
 * 5、项数减1
 */
bool DeQueue(Item * pitem, Queue * pq)
{
	// 使用临时指针存储待删除节点的位置
	// 因为指向首节点的正式指针被重置为指向下一个节点，如果没有临时指针，程序就不知道该释放那块内存
	Node * pt;

	if (QueueIsEmpty(pq)) {
		return false;
	}

	// 把队列的首端拷贝到给定的变量pitem中
	CopyToItem(pq->front, pitem);

	// 记录队列首端的地址
	pt = pq->front;

	// 重置首端指针位置指向下一项
	pq->front = pq->front->next;

	// 释放首端位置分配的内存空间
	free(pt);

	pq->items--;
	// 删除最后一项时，并未显示设置front指针指向NULL，因为已经设置front指针指向被删除节点的next指针
	if (pq->items == 0) {
		pq->rear = NULL;
	}

	return true;
}

/**
 * 清空队列
 */
void EmptyTheQueue(Queue * pq)
{
	Item dummy;
	while (!QueueIsEmpty(pq)) {
		DeQueue(&dummy, pq);
	}
}

/**
 * 把项拷贝到节点中
 */
static void CopyToNode(Item item, Node * pn)
{
	pn->item = item;
}

/**
 * 把项拷贝到给定的变量中
 */
static void CopyToItem(Node * pn, Item *pi)
{
	*pi = pn->item;
}
