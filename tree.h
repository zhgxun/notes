/**
 * 二叉查找树头文件
 */
#ifndef _TREE_H_
#define _TREE_H_
#include <stdbool.h>

// 宠物名称和种属长度
#define SLEN 20

/**
 * 定义一个宠物结构，包含宠物名称和所属种类
 */
typedef struct item
{
	char petname[SLEN];
	char petkind[SLEN];
} Item;

// 树的最大项数
#define MAXITEMS 10

/**
 * 定义一个树的内容结构体，包含树的内容和树的左子节点指向的指针和右子节点指向的指针
 */
typedef struct trnode
{
	Item item;
	struct trnode * left;
	struct $trnode * right;	
} Trnode;

/**
 * 定义树，记录树的根地址和树的项数
 */
typedef struct tree
{
	Trnode * root;
	int size;
} Tree;

/**
 * 初始化树
 */
void InitializeTree(Tree * ptree);

/**
 * 树是否为空
 */
bool TreeIsEmpty(const Tree * ptree);

/**
 * 树是否已满
 */
bool TreeIsFull(const Tree * ptree);

/**
 * 树的项数
 */
int TreeItemCount(const Tree * ptree);

/**
 * 往树中添加一个项
 */
bool AddItem(const Item * pi, Tree * ptree);

/**
 * 在树中查找一个项
 */
bool InTree(const Item * pi, const Tree * ptree);

/**
 * 从树中删除一个项
 */
bool DeleteItem(const Item * pi, Tree * ptree);

/**
 * 作用于树中的每一项
 */
void Traverse(const Tree * ptree, void(*pfun)(Item item));

/**
 * 删除树中的所有项
 */
void DeleteAll(Tree * ptree);

#endif
