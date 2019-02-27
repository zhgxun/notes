package github.banana.letcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * 检查字符串括号是否有效匹配
 */
public class IsValid {

    /**
     * 处理的字符串
     */
    private HashMap<Character, Character> mappings = new HashMap<Character, Character>() {
        {
            // 这样存储是为了闭合用, 即查询当前符号是否是闭合符号
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }
    };

    public static void main(String[] args) {

    }

    /**
     * 总体上其实也类似删除最小匹配条件, 看最终匹配结果, 匹配成功则栈为空
     *
     * @param s 原始字符串
     * @return 匹配结果
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            // 顺序获取字符串字符
            char c = s.charAt(i);

            // 当前符号属于闭合符号
            if (this.mappings.containsKey(c)) {

                // 获取栈顶元素, 为空给一个其它默认值
                char topElement = stack.empty() ? '#' : stack.pop();

                // 栈顶元素跟当前闭合符合不匹配
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                // 符号不闭合, 压入栈中
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
