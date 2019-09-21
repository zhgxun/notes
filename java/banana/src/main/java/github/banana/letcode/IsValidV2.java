package github.banana.letcode;

import java.util.Stack;

public class IsValidV2 {

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("())"));
        System.out.println(isValid("(()"));
        System.out.println(isValid("(())"));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            switch (c) {
                case ')':
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                        continue;
                    }
                    break;
                case ']':
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                        continue;
                    }
                    break;
                case '}':
                    if (!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                        continue;
                    }
                    break;
            }
            stack.push(c);
        }

        return stack.isEmpty();
    }
}
