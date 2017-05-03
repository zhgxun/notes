#include <string.h>
#include <stdio.h>
#include <stdbool.h>
#include "tree.h"

/**
 * 结构记录一个项的父地址和自己的地址
 */
typedef struct pair
{
	Trnode * parent;
	Trnode * child;	
} Pair;

// 局部函数定义
static Trnode * MakeNode(const Item *pi);
static bool ToLeft(const Item * i1, const Item * i2);
static bool ToRight(const Item * i1, const Item * i2);
static void AddNode(Trnode & new_node, Trnode * root);
static void InOrder(const Trnode * root, void(*pfun)(Item item));
static Pair SeekItem(const Item * pi, const Tree * ptree);
static void DeleteNode(Trnode ** ptr);
static void DeleteAllNotes(Trnode * ptr);

/**
 * 初始化树
 */
void InitializeTree(Tree * ptree)
{
	ptree->root = NULL;
	ptree->size = 0;
}

/**
 * 树是否为空
 */
bool TreeIsEmpty(const Tree * ptree)
{
	return ptree->root == NULL;
}

/**
 * 树是否已满
 */
bool TreeIsFull(const Tree * ptree)
{
	return ptree->size == MAXITERMS;
}

/**
 * 树的项数
 */
int TreeItemCount(const Tree * ptree)
{
	return ptree->size;
}

/**
 * 往树中添加一个项
 */
bool AddItem(const Item * pi, Tree * ptree)
{
	Trnode * new_node;

	// 树是否已满
	if (TreeIsFull(ptree)) {
		fprintf(stderr, "Tree is full.\n");
		return false;
	}

	// 待添加的项是否已经存在
	if (SeekItem(pi, ptree).child != NULL) {
		fprintf(stderr, "Attempted to add duplicate item.\n");
		return false;
	}

	// 创建一个新节点
	new_node = MakeNode(pi);
	if (new_node == NULL) {
		fprintf(stderr, "Couldn't create node.\n");
		return false;
	}

	// 成功创建新节点，统计树的项数
	ptree->size++;

	// 新项是树的根
	if (ptree->root == NULL) {
		ptree->root = new_node;
	// 否则，树不为空，把新项添加到树中
	} else {
		AddNode(new_node, ptree->root);
	}

	return true;
}

/**
 * 创建一个新节点
 */
static Trnode * MakeNode(const Item *pi)
{
	Trnode * new_node;
	new_node = (Trnode *)malloc(sizeof(Trnode));
	// 保存新节点的内容，并设置左子树和右子树为空，表示该处为树的最后一个项
	if (new_node != NULL) {
		new_node->item = *pi;
		new_node->left = NULL;
		new_node->right = NULL;
	}

	return new_node;
}

/**
 * 添加节点到
 */
static void AddNode(Trnode * new_node, Trnode * root)
{
	// 先查找是否是添加到左子节点
	if (ToLeft(&new_node->item, &root->item)) {
		// 直到找到一个左子节点为空时添加
		if (root->left == NULL) {
			root->left = new_node;
		} else {
			AddNode(new_node, root->left);
		}
	// 再查找是否是添加到右子节点
	} else if (ToRight(*new_node->item, &root->item)) {
		// 直到找到一个右子节点为空时添加
		if (root->right == NULL) {
			root->right = new_node;
		} else {
			AddNode(new_node, root->right);
		}
	// 否则，该项内容相同，不允许添加
	} else {
		fprintf(stderr, "Location error in AddNode()\n");
		exit(1);
	}
}

/**
 * 比较两个项是否在左边
 */
static bool ToLeft(const Item * i1, const Item * i2)
{
	int comp;
	// 第一个项在第二个项的前面，返回负数，相等返回0
	if ((comp = strcmp(i1->petname, i2->petname)) < 0) {
		return true;
	} else if (comp == 0 && strcmp(i1->petkind, i2->petkind) < 0) {
		return true;
	} else {
		return false;
	}
}

/**
 * 比较两个项是否在右边
 */
static bool ToRight(const Item * i1, const Item * i2)
{
	int comp;
	// 第一个项在第二个项的后面，返回正数，相等返回0
	if ((comp = strcmp(i1->petname, i2->petname)) > 0) {
		return true;
	} else if (comp == 0 && strcmp(i1->petkind, i2->petkind) > 0) {
		return true;
	} else {
		return false;
	}
}

/**
 * 查看一个项是否在树中
 */
bool InOrder(const Item * pi, const Tree * ptree)
{
	return SeekItem(pi, ptree).child != NULL;
}

/**
 * 在树中查找一个项
 */
static Pair SeekItem(const Item * pi, const Tree * ptree)
{
	// 初始化临时记录的结构为树的根
	Pair look;

	look.parent = NULL;
	look.child = ptree->root;

	// 树为空，返回根
	if (look.child == NULL) {
		return look;
	}

	while (look.child != NULL) {
		// 是否是树的左子节点
		if (ToLeft(pi, &(look.child->item))) {
			look.parent = look.child;
			look.child = look.child->left;
		// 是否是树的右子节点
		} else if (ToRight(pi, &(look.child->item))) {
			look.parent = look.child;
			look.child = look.child->right;
		// 存在，此时look.child是查找到的目标项
		} else {
			break;
		}
	}

	return look;
}

/**
 * 删除树中的一个项
 */
bool DeleteItem(const Item * pi, Tree * ptree)
{
	Pair look;
	look = SeekItem(pi, ptree);

	// 项不存在树中
	if (look.child == NULL) {
		return false;
	}

	// 当前为树的根，删除根
	if (look.parent == NULL) {
		DeleteNode(&ptree->root);
	} else if (look.parent->left == look.child) {
		DeleteNode(&look.parent->left);
	} else {
		DeleteNode(&look.parent->right);
	}

	ptree->size--;

	return true;
}




































