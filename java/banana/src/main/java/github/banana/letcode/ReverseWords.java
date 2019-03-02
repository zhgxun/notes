package github.banana.letcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 557. 反转字符串中的单词 III
 * <p>
 * 给定一个字符串, 你需要反转字符串中每个单词的字符顺序, 同时仍保留空格和单词的初始顺序
 * <p>
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc"
 */
@Slf4j
public class ReverseWords {

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        /**
         * |---------------------------------------------------------------------------------------------------------
         * | L | e | t | ' | s |  | t | a | k | e |  | L | e | e | t | C | o | d | e |  | c | o | n | t | e | s | t |
         * |---------------------------------------------------------------------------------------------------------
         *   0   1   2   3   4   5  6   7   8   9  10 11  12  13  14  15  16  17  18  19  20 21  22  23  24  25  26
         *                       ^                  ^                                  ^
         */
        System.out.println(new ReverseWords().reverseWords(s));
    }

    public String reverseWords(String s) {
        // 对字符串进行空格补齐
        char[] chars = String.format("%s ", s.trim()).toCharArray();
        int length = chars.length;
        int next;
        for (int i = 0, j = 0; j < length; j++) {
            // 慢指针 i 不动, 让快指针 j 往后查找空格, 找到空格则为一个单词
            // 则反转单词, 需要注意临界条件最后一个单词没有空格, 或者对原始字符串补齐一个空格来解决
            if (chars[j] == ' ') {
                next = j - 1;
                for (; i < next; i++, next--) {
                    char temp = chars[i];
                    chars[i] = chars[next];
                    chars[next] = temp;
                }
                i = j + 1;
            }
        }

        return new String(chars).trim();
    }
}
