package github.banana.letcode;

import java.util.Stack;

public class RemoveDuplicatesV5 {

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicatesV2(s));
    }

    // 栈的方式解决, 比较容易想到, 但是效率中等因为要占用栈空间
    public static String removeDuplicates(String S) {
        char[] chars = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            // 有弹出操作不能将字符再入栈
            boolean flag = false;
            while (!stack.isEmpty() && stack.peek().equals(c)) {
                flag = true;
                stack.pop();
            }

            if (!flag) {
                stack.push(c);
            }
        }

        int length = stack.size();
        char[] chars1 = new char[length];
        while (!stack.isEmpty()) {
            chars1[--length] = stack.pop();
        }

        return new String(chars1);
    }

    // 效率较优
    // 一次遍历省去了栈本身的操作, 但是需要想到, 虽然也简单
    // 跟删除数组中重复数字类似, 预先放置目标数组, 在跟目标数组进行比较处理
    // 但需要注意处理的区别
    public static String removeDuplicatesV2(String S) {
        StringBuilder sb = new StringBuilder();
        int sbLength = 0;
        for (char character : S.toCharArray()) {
            if (sbLength != 0 && character == sb.charAt(sbLength - 1)) {
                sb.deleteCharAt(sbLength - 1);
                sbLength--;
            } else {
                sb.append(character);
                sbLength++;
            }
        }

        return sb.toString();
    }
}
