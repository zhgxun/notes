#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define BUFSIZE 4096
#define SLEN 81
void append(FILE *source, FILE *dest);
char * s_gets(char *st, int n);

/**
 * 把一个文件的内容附加到另一个文件的末尾
 */
int main(void)
{
    // fd指向目标文件 fs指向源文件
    FILE *fd, *fs;
    // 附加文件的个数
    int files = 0;
    // 目标文件和源文件必须是当前目录下存在的文件
    char file_dest[SLEN];
    char file_src[SLEN];
    int ch;
    
    puts("Enter name of destination(target) file:");
    // 读取待添加存储内容文件名，比如test，将源文件名存入file_dest中
    s_gets(file_dest, SLEN);
    // 以追加方式打开文件
    if ((fd = fopen(file_dest, "a+")) == NULL) {
        fprintf(stderr, "Can't open %s\n", file_dest);
        exit(EXIT_FAILURE);
    }

    //  _IOFBF full buffering 为文件打开设置一个缓冲区
    if (setvbuf(fd, NULL, _IOFBF, BUFSIZE) != 0) {
        fputs("Can't create output buffer\n", stderr);
        exit(EXIT_FAILURE);
    }

    puts("Enter name of first source file (empty line to quit): ");
    // 待追加进来的源文件名
    while (s_gets(file_src, SLEN) && file_src[0] != '\0') {
        // 如果输入的是同一个文件，不需要文件自身进行追加操作
        if (strcmp(file_src, file_dest) == 0) {
            fputs("Can't append file to itself\n", stderr);
        // 读取源文件
        } else if ((fs = fopen(file_src, "r")) == NULL) {
            fprintf(stderr, "Can't open %s\n", file_src);
        } else {
            if (setvbuf(fs, NULL, _IOFBF, BUFSIZE) != 0) {
                fputs("Can't create input buffer\n", stderr);
                continue;
            }
        }

        // 将读取出来的源文件内容追加到目标文件中
        append(fs, fd);

        // 检查文件流是否存在错误
        if (ferror(fs) != 0) {
            fprintf(stderr, "Error in reading file %s\n", file_src);
        }
        if (ferror(fd) != 0) {
            fprintf(stderr, "Error in writing file %s\n", file_dest);
        }
        // 关闭源文件句柄
        fclose(fs);
        // 保存读取的源文件数量
        files++;

        printf("File %s appended.\n", file_src);
        puts("Next file (empty line to quit): ");
    }

    printf("Done appending. %d files appended.\n", files);

    // 移动文件指针至文件开始处，读取目标文件追加后的内容
    rewind(fd);

    printf("%s content: \n", file_dest);
    while ((ch = getc(fd)) != EOF) {
        putchar(ch);
    }
    puts("Done displaying.");
    // 关闭目标文件
    fclose(fd);

    return 0;
}

/**
 * 将源文件内容追加到目标文件中
 */
void append(FILE *source, FILE *dest)
{
    size_t bytes;
    // 静态存储区，一次性读取BUFSIZE个字符
    static char temp[BUFSIZE];
    // 返回成功读取字符的数量
    while ((bytes = fread(temp, sizeof(char), BUFSIZE, source)) > 0) {
        fwrite(temp, sizeof(char), bytes, dest);
    }
}
