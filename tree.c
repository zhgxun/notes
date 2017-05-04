#include <string.h>
#include <stdio.h>
#include <stdbool.h>
#include "tree.h"

/**
 * 结构记录一个项的父地址(即当前项的根节点)和自己的地址
 */
typedef struct pair
{
	// 当前项的根节点地址，没有则为NULL
	Trnode * parent;
	// 当前项的节点地址，没有则为NULL
	Trnode * child;
} Pair;

// 局部函数定义
static Trnode * MakeNode(const Item *pi);
static bool ToLeft(const Item * i1, const Item * i2);
static bool ToRight(const Item * i1, const Item * i2);
static void AddNode(Trnode * new_node, Trnode * root);
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
	return ptree->size == MAXITEMS;
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
 * 返回创建新节点的指针地址
 */
static Trnode * MakeNode(const Item *pi)
{
	Trnode * new_node;
	new_node = (Trnode *)malloc(sizeof(Trnode));
	// 保存新节点的内容，并设置左子树和右子树为空，表示该处为树的最后一个节点项
	if (new_node != NULL) {
		new_node->item = *pi;
		new_node->left = NULL;
		new_node->right = NULL;
	}

	return new_node;
}

/**
 * 添加节点到树中
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
	} else if (ToRight(&new_node->item, &root->item)) {
		// 直到找到一个右子节点为空时添加
		if (root->right == NULL) {
			root->right = new_node;
		} else {
			AddNode(new_node, root->right);
		}
	// 否则，该项内容相同，不允许添加
	} else {
		fprintf(stderr, "Location error in AddNode().\n");
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
bool InTree(const Item * pi, const Tree * ptree)
{
	return SeekItem(pi, ptree).child != NULL;
}

/**
 * 在树中查找一个项
 * 返回NULL为不存在该项
 */
static Pair SeekItem(const Item * pi, const Tree * ptree)
{
	Pair look;

	// 初始化临时记录的结构为树的根
	look.parent = NULL;
	look.child = ptree->root;

	// 树为空，返回空结构
	if (look.child == NULL) {
		return look;
	}

	// 从根节点开始，先查找每一层级的左节点，再查找右节点，循环重置look.child指向后续的项
	while (look.child != NULL) {
		// 是否是树的左子节点
		if (ToLeft(pi, &(look.child->item))) {
			// 保存当前节点作为下一层级的父节点
			look.parent = look.child;
			// 重置当前节点为下一个节点
			look.child = look.child->left;
		// 是否是树的右子节点
		} else if (ToRight(pi, &(look.child->item))) {
			look.parent = look.child;
			look.child = look.child->right;
		// 既不是左节点，也不是右节点，又不为空，则存在当前值，此时look.child是查找到的目标项
		} else {
			break;
		}
	}

	return look;
}

/**
 * 使用自定义函数作用于树的每一项
 * 遍历项比遍历链表复杂，因为每个节点都有两个分支，这种分支特性很适合使用分而治之的递归来处理。
 * 对于每一个节点，执行遍历任务的函数需要做如下工作：
 * 处理节点中的项；
 * 处理左子树；
 * 处理右子树
 */
void Traverse(const Tree * ptree, void(*pfun)(Item item))
{
	if (ptree != NULL) {
		// 从树的根开始处理
		InOrder(ptree->root, pfun);
	}
}

/**
 * 处理左子树，然后处理项，最后处理右子树
 */
static void InOrder(const Trnode * root, void(*pfun)(Item item))
{
	if (root != NULL) {
		InOrder(root->left, pfun);
		(*pfun)(root->item);
		InOrder(root->right, pfun);
	}
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

/**
 * 删除一个节点
 */
static void DeleteNode(Trnode ** ptr)
{
	Trnode * temp;
	if ((*ptr)->left == NULL) {
		temp = *ptr;
		*ptr = (*ptr)->right;
		free(temp);
	} else if ((*ptr)->right == NULL) {
		temp = *ptr;
		*ptr = (*ptr)->left;
		free(temp);
	} else {
		for (temp = (*ptr)->left; temp->right == NULL; temp = temp->right) {
			continue;
		}
		temp->right = (*ptr)->right;
		temp = *ptr;
		*ptr = (*ptr)->left;
		free(temp);
	}
}

/**
 * 清空一棵树
 */
void DeleteAll(Tree * ptree)
{
	if (ptree != NULL) {
		DeleteAllNotes(ptree->root);
	}
	ptree->root = NULL;
	ptree->size = 0;
}

/**
 * 清空所有节点
 */
static void DeleteAllNotes(Trnode * root)
{
	Trnode * pright;
	if (root != NULL) {
		pright = root->right;
		DeleteAllNotes(root->left);
		free(root);
		DeleteAllNotes(pright);
	}
}
