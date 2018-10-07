#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define MAXWORD 30
#define BUFSIZE 100
#define NKEYS 10

// c language keywords, need declaration top
struct key {
    char *word;
    int count;
} keytab[] = {
    {"auto", 0},
    {"break", 0},
    {"case", 0},
    {"char", 0},
    {"const", 0},
    {"continue", 0},
    {"default", 0},
    {"unsigned", 0},
    {"void", 0},
    {"while", 0}
};

int getWord(char *, int);
int getch(void);
void ungetch(int);
int binSearch(char *, struct key *, int);

char buf[BUFSIZE];
int bufp = 0;

/**
 * count c keywords
 */
int main()
{
    printf("Test...\n");

    int n;
    char word[MAXWORD];

    while (getWord(word, MAXWORD) != EOF) {
        if (isalpha(word[0])) {
            if ((n = binSearch(word, keytab, NKEYS)) >= 0) {
                keytab[n].count++;
            }
        }
    }

    printf("\n----------\n");

    for (n = 0; n < NKEYS; n++) {
        if (keytab[n].count > 0) {
            printf("%s --> %d\n", keytab[n].word, keytab[n].count);
        }
    }

    printf("----------\n");

    return 0;
}

/**
 * get next word or character from input
 */
int getWord(char *word, int lim)
{
    int c, getch(void);
    void ungetch(int);
    char *w = word;

    while (isspace(c = getch())) {
        ;
    }

    if (c != EOF) {
        *w++ = c;
    }

    if (!isalpha(c)) {
        *w = '\0';
        return c;
    }

    for (; --lim > 0; w++) {
        if (!isalnum(*w = getch())) {
            ungetch(*w);
            break;
        }
    }
    *w = '\0';

    return word[0];
}

/**
 * get a possibly pushed-back character
 *
 * 读入一个字符串，如果缓冲不为空，则直接读取缓冲中的字符串
 */
int getch(void)
{
    return (bufp > 0) ? buf[--bufp] : getchar();
}

/**
 * push character back on input
 *
 * 将字符串存入缓冲中
 */
void ungetch(int c)
{
    if (bufp >= BUFSIZE) {
        printf("ungetch: too many characters\n");
    } else {
        buf[bufp++] = c;
    }
}

/**
 * find word in tab[0]...tab[n-1]
 *
 * 折半查找，数组需要升序排列
 */
int binSearch(char *word, struct key tab[], int n)
{
    int cond;
    int low, high, mid;

    low = 0;
    high = n - 1;
    while (low <= high) {
        mid = (low + high) / 2;
        if ((cond = strcmp(word, tab[mid].word)) < 0) {
            high = mid - 1;
        } else if (cond > 0) {
            low = mid + 1;
        } else {
            return mid;
        }
    }

    return -1;
}

