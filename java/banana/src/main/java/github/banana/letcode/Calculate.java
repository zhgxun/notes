package github.banana.letcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 基本计算器
 */
public class Calculate {

    public static void main(String[] args) {
//        System.out.println(calculate("1 + 1"));
//        System.out.println(calculate(" 2-1 + 2   "));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    // (1+(4+5+2)-3)+(6+8)
    // 只存在加减和小括号运算符
    // 非负整数不需考虑小数
    // 难点在于括号的优先级怎么处理, 这里计算还是不容易考虑的
    // 遇见运算符时先尝试进行已有运算符栈的计算, 巧妙的解决括号内计算的问题
    // 同时遇到的括号再入运算符栈即可
    // 但是这个解法性能普通
    public static int calculate(String s) {
        // 数字栈
        Stack<Integer> numStack = new Stack<>();
        // 存放基本运算符栈, 包括做括号
        Stack<String> operateStack = new Stack<>();
        List<String> tokens = expressionToTokens(s);
        // [(, 1, +, (, 4, +, 5, +, 2, ), -, 3, ), +, (, 6, +, 8, )]
        for (String token : tokens) {
            switch (token) {
                case "(":
                    // 左括号直接入运算符栈
                    operateStack.push(token);
                    break;
                case "+":
                case "-":
                case ")":
                    // 如果运算符栈顶是则现尝试进行加减运算符则进行计算
                    if (operateStack.size() > 0 && (operateStack.peek().equals("+") || operateStack.peek().equals("-"))) {
                        numStack.push(sum(operateStack.pop(), numStack.pop(), numStack.pop()));
                    }
                    if (token.equals(")")) {
                        // 如果运算符栈顶是左括号则消除
                        if (operateStack.peek().equals("(")) {
                            operateStack.pop();
                        }
                    } else {
                        // 否则将当前运算符入栈
                        operateStack.push(token);
                    }
                    break;
                default:
                    // 数字直接入数字栈
                    numStack.push(Integer.parseInt(token));
            }
        }

        // 最后还剩余的无先后顺序直接计算即可
        while (!operateStack.isEmpty()) {
            numStack.push(sum(operateStack.pop(), numStack.pop(), numStack.pop()));
        }

        return numStack.peek();
    }

    // 加减求值
    public static int sum(String operation, int num1, int num2) {
        System.out.println(operation + ", " + num1 + ", " + num2);
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num2 - num1;
            case "*":
                return num1 * num2;
            case "/":
                return num2 / num1;
            default:
                throw new RuntimeException("Sum is error");
        }
    }

    // 将表达式转为数组形式
    public static List<String> expressionToTokens(String expression) {
        List<String> tokens = new ArrayList<>();
        int length = expression.length();
        StringBuilder keep = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);
            switch (c) {
                case ' ':
                    // 空格不处理
                    break;
                case '+':
                case '-':
                case '(':
                case ')':
                case '*':
                case '/':
                    // 如果是符号则直接存入
                    tokens.add(c + "");
                    break;
                default:
                    // 数字需要连续的位数
                    // 如果下一位是符号则存取
                    keep.append(c);

                    // 最后一个位置无需处理
                    if (i + 1 >= length) {
                        tokens.add(keep.toString());
                        keep = new StringBuilder();
                    } else {
                        int next = expression.charAt(i + 1) - 48;
                        // 提前检测下一位不是一个合适的数字, 则存入
                        if (next < 0 || next > 9) {
                            tokens.add(keep.toString());
                            keep = new StringBuilder();
                        }
                    }
            }
        }

        return tokens;
    }
}
