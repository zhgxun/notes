package github.banana.letcode;

import java.util.List;
import java.util.Stack;

public class CalculateV2 {

    public static void main(String[] args) {
//        System.out.println(calculate("3+2*2"));
//        System.out.println(calculate(" 3/2 "));
//        System.out.println(calculate(" 3+5 / 2 "));
//        System.out.println(calculate("100000000/1/2/3/4/5/6/7/8/9/10"));
//        System.out.println(calculate("2*3+4"));
        System.out.println(calculate("1*2-3/4+5*6-7*8+9/10"));
    }

    // 跟上一题类似没有括号处理
    // 同样处理过多效率不够高
    public static int calculate(String s) {
        // 数字栈
        Stack<Integer> numStack = new Stack<>();
        // 存放基本运算符栈, 包括做括号
        Stack<String> operateStack = new Stack<>();
        List<String> tokens = Calculate.expressionToTokens(s);
        System.out.println(tokens);
        // [(, 1, +, (, 4, +, 5, +, 2, ), -, 3, ), +, (, 6, +, 8, )]
        for (String token : tokens) {
            switch (token) {
                case "+":
                case "-":
                case "*":
                case "/":
                    // 优先级处理
                    if (!operateStack.isEmpty()) {
                        // 同级运算符直接进行计算
                        if (token.equals("+") || token.equals("-")) {
                            while (!operateStack.isEmpty()) {
                                numStack.push(Calculate.sum(operateStack.pop(), numStack.pop(), numStack.pop()));
                            }
                        }
                        while ((token.equals("*") || token.equals("/")) && !operateStack.isEmpty() && (operateStack.peek().equals("*") || operateStack.peek().equals("/"))) {
                            numStack.push(Calculate.sum(operateStack.pop(), numStack.pop(), numStack.pop()));
                        }
                    }

                    // 当前运算符入栈
                    operateStack.push(token);
                    break;
                default:
                    // 数字直接入数字栈
                    numStack.push(Integer.parseInt(token));
            }
        }

        // 最后还剩余的无先后顺序直接计算即可
        while (!operateStack.isEmpty()) {
            numStack.push(Calculate.sum(operateStack.pop(), numStack.pop(), numStack.pop()));
        }

        return numStack.peek();
    }

    // 这个效率明显就达到优秀解法
    public static int calculateV2(String s) {
        // 保留目标求和数字, 比较巧妙
        Stack<Integer> integers = new Stack<>();
        char op = '+';
        int number = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            // 数字处理, 多位数
            if (Character.isDigit(c)) {
                // 上一个数字扩大10倍加当前数字即为最新数字
                number = number * 10 + (c - '0');
            }

            // 遇到运算符
            if ((i == length - 1) || c == '+' || c == '-' || c == '*' || c == '/') {
                if (op == '+') {
                    // 加直接入栈
                    integers.push(number);
                } else if (op == '-') {
                    // 减相反数入栈
                    integers.push(number * (-1));
                } else if (op == '*') {
                    // 乘直接计算结果
                    int temp = integers.pop() * number;
                    integers.push(temp);
                } else if (op == '/') {
                    // 除也直接计算结果
                    int temp = integers.pop() / number;
                    integers.push(temp);
                }
                number = 0;
                op = c;
            }
        }

        // 最后求和即可
        int sum = 0;
        while (!integers.empty()) {
            sum += integers.pop();
        }

        return sum;
    }
}
