package github.banana.letcode;

/**
 * 字符串中有效匹配子串的最大长度
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses("()(()"));
    }

    public static int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        return Math.max(calc(chars, 0, 1, chars.length, '('), calc(chars, chars.length - 1, -1, -1, ')'));
    }

    private static int calc(char[] chars, int i, int flag, int end, char cTem) {
        int max = 0, sum = 0, currLen = 0, validLen = 0;
        for (; i != end; i += flag) {
            // 看当前括号匹配情况, 非0不匹配和等于0匹配
            // 这个标识很重要, stack 数据结构的时候这个就不好区分, 入栈和出栈无法判断这个是否是前序的小序列段
            sum += (chars[i] == cTem ? 1 : -1);
            currLen++;
            // 不匹配, 则有效被打破, 需要更新有效长度
            if (sum < 0) {
                max = max > validLen ? max : validLen;
                // 初始记录
                sum = 0;
                currLen = 0;
                validLen = 0;
            } else if (sum == 0) {
                // 刚好配对, 记录下当前有效长度
                validLen = currLen;
            }
        }
        return max > validLen ? max : validLen;
    }
}
