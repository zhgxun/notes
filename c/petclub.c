#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "tree.h"

char menu(void);
void addpet(Tree * pt);
void droppet(Tree * pt);
void showpets(const Tree * pt);
void findpet(const Tree * pt);
void printitem(Item item);
void uppercase(char * st);
char * s_gets(char * st, int n);

int main(void)
{
	Tree pets;
	char choice;

	InitializeTree(&pets);

	while ((choice = menu()) != 'q') {
		switch (choice) {
			case 'a':
				addpet(&pets);
				break;
			case 'l':
				showpets(&pets);
				break;
			case 'f':
				findpet(&pets);
				break;
			case 'n':
				printf("%d pets in clud.\n", TreeItemCount(&pets));
				break;
			case 'd':
				droppet(&pets);
				break;
			default:
				puts("Switching error");
		}
	}

	DeleteAll(&pets);

	puts("Bye.");

	return 0;
}

/**
 * 选择菜单
 */
char menu(void)
{
	int ch;

	puts("NerFville Pet Club Membership Program");
	puts("Enter the letter corresponding to your choice");
	puts("a) add a pet        l) show list of pets");
	puts("n) number of pets   f) find pets");
	puts("d) delete a pet     q) quit");

	while ((ch = getchar()) != EOF) {
		while (getchar() != '\n') {
			continue;
		}

		// 转化为小写比较
		ch = tolower(ch);
		if (strchr("alnfdq", ch) == NULL) {
			puts("Please enter an a, l, n, f, d or q");
		} else {
			break;
		}
	}

	// 使程序结束
	if (ch == EOF) {
		ch = 'q';
	}

	return ch;
}

/**
 * 添加一个宠物
 */
void addpet(Tree * pt)
{
	Item temp;

	if (TreeIsFull(pt)) {
		puts("No room in the clud!");
	} else {
		puts("Please enter name of pet:");
		s_gets(temp.petname, SLEN);
		puts("Please enter pet kind:");
		s_gets(temp.petkind, SLEN);

		// 宠物名称均转化为大写
		uppercase(temp.petname);
		uppercase(temp.petkind);

		AddItem(&temp, pt);
	}
}

/**
 * 展示所有宠物
 */
void showpets(const Tree * pt)
{
	if (TreeIsEmpty(pt)) {
		puts("No entries");
	} else {
		Traverse(pt, printitem);
	}
}

/**
 * 显示一个宠物的名称和种属
 */
void printitem(Item item)
{
	printf("Pet: %-19s    Kind:    %-19s\n", item.petname, item.petkind);
}

/**
 * 根据宠物名称和种属查找是否是一个已经存在的成员
 */
void findpet(const Tree * pt)
{
	Item temp;

	if (TreeIsEmpty(pt)) {
		puts("No entries");
		return;
	}

	puts("Please enter name of pet you wish to find:");
	s_gets(temp.petname, SLEN);
	puts("Please enter pet kind:");
	s_gets(temp.petkind, SLEN);

	uppercase(temp.petname);
	uppercase(temp.petkind);

	printf("%s the %s ", temp.petname, temp.petkind);
	if (InTree(&temp, pt)) {
		printf("is a member.\n");
	} else {
		printf("is not a member.\n");
	}
}

/**
 * 删除一个宠物
 */
void droppet(Tree * pt)
{
	Item temp;

	if (TreeIsEmpty(pt)) {
		puts("No entries");
		return;
	}

	puts("Please enter name of pet you wish to find:");
	s_gets(temp.petname, SLEN);
	puts("Please enter pet kind:");
	s_gets(temp.petkind, SLEN);

	uppercase(temp.petname);
	uppercase(temp.petkind);

	printf("%s the %s ", temp.petname, temp.petkind);
	if (DeleteItem(&temp, pt)) {
		printf("is dropped from the clud.\n");
	} else {
		printf("is not a member.\n");
	}
}

/**
 * 将字符转为大写
 */
void uppercase(char * str)
{
	while (*str) {
		*str = toupper(*str);
		str++;
	}
}
