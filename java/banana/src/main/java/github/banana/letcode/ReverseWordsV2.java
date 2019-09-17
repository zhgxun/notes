package github.banana.letcode;

import java.util.Stack;

public class ReverseWordsV2 {

    public static void main(String[] args) {
        String s = "the sky is blue";
        // blue is sky the
        System.out.println(reverseWords(s));
    }

    // 使用额外的数据结构运行效率中等, 耗时10ms, 优秀的解法耗时2ms
    public static String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;

        // 借助栈来存储有效的单词
        Stack<String> stack = new Stack<>();

        // 临时保存一个单词
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // 遇见空格和遍历到末尾则已经形成一个单词
            if (chars[i] == ' ' || i == length - 1) {
                // 最后一个元素可能为空, 空不处理
                if (i == length - 1 && chars[i] != ' ') {
                    sb.append(chars[i]);
                }
                String content = sb.toString();
                if (content.length() > 0) {
                    sb = new StringBuilder();
                    stack.push(content);
                }
            } else {
                sb.append(chars[i]);
            }
        }

        // 将目标单词出栈即可
        sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop());
            sb.append(" ");
        }

        return sb.toString().trim();
    }
}
