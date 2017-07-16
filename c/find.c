#include <stdio.h>
#define MAXLINE 1000

int getLine(char line[], int max);
int strIndex(char source[], char searchfor[]);

char pattern[] = "ould";

int main()
{
    printf("Test...\n");

    char line[MAXLINE];
    int found = 0;

    while (getLine(line, MAXLINE) > 0) {
        if (strIndex(line, pattern) >= 0) {
            printf("%s\n", line);
            found++;
        }
    }

    return found;
}

/**
 * get line into s, return length
 */
int getLine(char s[], int lim)
{
    int c, i;

    i = 0;

    while (--lim > 0 && (c = getchar()) != EOF && c != '\n') {
        s[i++] = c;
    }
    if (c == '\n') {
        s[i++] = c;
    }
    s[i] = '\0';

    return i;
}

/**
 * return index of t in s, -1 if none
 */
int strIndex(char s[], char t[])
{
    int i, j;

    for (i = 0; s[i] != '\0'; i++) {
        for (j = 0; t[j] != '\0'; j++) {
            if (s[i] == t[j]) {
                return i;
            }
        }
    }

    return -1;
}

