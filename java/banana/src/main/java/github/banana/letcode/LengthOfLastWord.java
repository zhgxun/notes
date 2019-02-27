package github.banana.letcode;

/**
 * 58. 最后一个单词的长度
 * <p>
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * <p>
 * 如果不存在最后一个单词，请返回 0
 */
public class LengthOfLastWord {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
        System.out.println(lengthOfLastWord("        "));
        System.out.println(lengthOfLastWord("a "));
        System.out.println(lengthOfLastWord(" a"));
    }

    public static int lengthOfLastWord(String s) {
        // 其实如果可以尽可能借助api来完成很多操作, 哈哈
        String s1 = s.trim();
        int length = s1.length();
        int count = 0;
        for (int i = length - 1; i >= 0; i--) {
            count++;
            if (s1.charAt(i) == ' ') {
                return count - 1;
            }
        }
        return count;
    }
}
