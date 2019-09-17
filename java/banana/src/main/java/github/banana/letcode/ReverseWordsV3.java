package github.banana.letcode;

public class ReverseWordsV3 {

    public static void main(String[] args) {
        String source = "Let's take LeetCode contest";
        // s'teL ekat edoCteeL tsetnoc
        // 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格
        System.out.println(reverseWords(source));
    }

    // 效率不是最好的, 30个单元测是运行13ms
    public static String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        // 慢指针
        for (int i = 0; i < length; ) {
            // 快指针找单词分界
            int j = 0;
            for (; ; ) {
                // 到达句子末尾刚好够最后一个单词
                if (i + j >= length) {
                    break;
                }
                // 正常遇见空格形成一个单词
                if (chars[i + j] == ' ') {
                    break;
                }
                j++;
            }
            swap(chars, i, i + j - 1);
            i += (j + 1);
        }
        return new String(chars);
    }

    private static void swap(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;

            start++;
            end--;
        }
    }
}
