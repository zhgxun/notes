package github.banana.letcode;

import java.util.Stack;

/**
 * 后缀表达式求值
 */
public class EvalRPN {

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    // ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
    // 非常简单的后缀表达式计算, 队列的计算方式即可
    // 需要注意的是无需自己降字符串转为整数, 当然可以转为整数后存入栈中, 性能应该会高一点
    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String element : tokens) {
            switch (element) {
                case "+": {
                    int num1 = Integer.parseInt(stack.pop());
                    int num2 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(num1 + num2));
                    break;
                }
                case "-": {
                    int num1 = Integer.parseInt(stack.pop());
                    int num2 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(num2 - num1));
                    break;
                }
                case "*": {
                    int num1 = Integer.parseInt(stack.pop());
                    int num2 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(num2 * num1));
                    break;
                }
                case "/": {
                    int num1 = Integer.parseInt(stack.pop());
                    int num2 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(num2 / num1));
                    break;
                }
                default:
                    stack.push(element);
                    break;
            }
        }

        return Integer.parseInt(stack.pop());
    }
}
