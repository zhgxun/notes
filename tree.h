/**
 * 二叉查找树头文件
 * 二叉树中的每个节点都包含两个子节点，左节点和右节点，其顺序按照如下规定确定：
 * 左节点的项在父节点的项前面，右节点的项在父节点的项后面。这种关系存在于每个有子节点的节点中。
 * 进一步而言，所有可以追溯其祖先回到一个父节点的左节点的项，都在该父节点项的前面，所有以一个父节点的右节点为祖先的项，都在该父节点项的后面。
 *
 * 二叉查找树中的每个节点是其后代节点的根，该节点与其后代节点构成了一个子树(subtree)。
 *
 * 二叉查找树在链式结构中结合了二分查找的效率。但是，这样编程的代价是构建一个二叉树比创建一个链表更复杂。
 *
 * 实现二叉查找树最直接的方法是通过指针动态分配链接节点。
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
 * 定义一个树的节点结构体，包含树的内容和树的左子节点指向的指针和右子节点指向的指针
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
