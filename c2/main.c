//
//  存储电影基本信息用户实现部分
//

#include <stdio.h>
#include <stdlib.h>
#include "list.h"

void showMovies(Item item);
char * s_gets(char * st, int n);

int main(void)
{
    List movies;
    Item temp;

    InitializeList(&movies);
    if (ListIsFull(&movies)) {
        fprintf(stderr, "No memory available! Bye!\n");
        exit(EXIT_FAILURE);
    }

    puts("Enter first movie title:");
    while (s_gets(temp.title, SIZE) && temp.title[0] != '\0') {
        puts("Enter you rating <0-10>:");
        scanf("%d", &temp.rating);
        while (getchar() != '\n') {
        }

        if (!AddItem(temp, &movies)) {
            fprintf(stderr, "Problem allocating memory.\n");
            break;
        }

        if (ListIsFull(&movies)) {
            puts("The list is now full.");
            break;
        }

        puts("Enter next movie title (empty line to stop):");
    }

    if (ListIsEmpty(&movies)) {
        puts("No data entered!");
    } else {
        puts("Here is the movies list:");
        Traverse(&movies, showMovies);
    }

    printf("You entered %d movies.\n", ListItemCount(&movies));

    EmptyTheList(&movies);

    puts("Bye!");

    return 0;
}

/**
 * 显示电影基本信息
 * @param item
 */
void showMovies(Item item)
{
    printf("Movie: %s; Rating %d\n", item.title, item.rating);
}
