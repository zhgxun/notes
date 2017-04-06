#include <stdio.h>
#define MAXLINE 100 

int getLine(char s[], int limit);
void copy(char to[], char from[]);

/**
 * get max length line
 */
int main()
{
    printf("Test...\n");
    int len;
    int max;
    char line[MAXLINE];
    char longest[MAXLINE];

    max = 0;
    // if len > 0 lead to die loop
    while ((len = getLine(line, MAXLINE)) > 1) {
        if (len > max) {
            max = len;    
        }    
        copy(longest, line);
    }

    if (max > 0) {
        printf("%s\n", longest);    
    }

    return 0;
}

/**
 * read max line
 */
int getLine(char s[], int limit)
{
    int c, i;

    for (i = 0; i < limit - 1 && (c = getchar()) != EOF && c != '\n'; ++i) {
        s[i] = c;
    }
    if (c == '\n') {
        s[i] = c;
        ++i;
    }
    s[i] = '\0';

    return i;
}

/**
 * copy char from -> to
 */
void copy(char to[], char from[])
{
    int i = 0;
    while ((to[i] = from[i]) != '\0') {
        ++i;    
    }    
}

