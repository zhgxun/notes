#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define LEN 40

int main(int argc, char * argv[])
{
    FILE *in, *out;
    int ch;
    char name[LEN];
    int count = 0;

    if (argc < 2) {
        fprintf(stderr, "Usage: %s filename\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    if ((in = fopen(argv[1], "r")) == NULL) {
        fprintf(stderr,"");
    }
}
