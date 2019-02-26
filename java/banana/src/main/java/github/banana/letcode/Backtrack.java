package github.banana.letcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * <p>
 * 只有在我们知道序列仍然保持有效时才添加 '(' or ')'，而不是每次添加
 * 我们可以通过跟踪到目前为止放置的左括号和右括号的数目来做到这一点
 * 如果我们还剩一个位置, 我们可以开始放一个左括号
 * 如果它不超过左括号的数量, 我们可以放一个右括号
 */
public class Backtrack {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(1).toString());
        System.out.println(generateParenthesis(2).toString());
        System.out.println(generateParenthesis(3).toString());
    }

    /**
     * 生成有效括号的数量
     *
     * @param n 括号数量
     * @return 结果
     */
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        // "" 当前括号还未开始生成为空
        // 当前左括号, 右括号都为0
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    /**
     * 放置左右括号
     *
     * @param ans   每一种符合条件的括号组合
     * @param cur   当前拼接的括号字符串
     * @param open  当前左括号的位置
     * @param close 当前右括号的位置
     * @param max   最多允许的括号数量
     */
    private static void backtrack(List<String> ans, String cur, int open, int close, int max) {
        // 最多生成的括号数量, 即2倍
        // 生成了就是一队有效的组合
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        //
        // "", 0, 0, 2
        // "(", 1, 0, 2
        // "((", 2, 0, 2
        // "(()", 2, 1, 2
        // "(())", 2, 2, 2

        //
        // "(", 1, 0, 2
        // "()", 1, 1, 2
        // "()(", 2, 1, 2
        // "()()", 2, 2, 2

        // 左括号最多可以放置需要的括号数
        if (open < max) {
            // 依次往后放置左括号
            // cur + "(" 可以生成一个左括号
            // open + 1 下一个左括号待生成
            // close 当前允许生成的右括号数量
            backtrack(ans, cur + "(", open + 1, close, max);
        }
        // 右括号最多也只能放置对应的左括号数量
        // 即是有左括号才会有右括号
        if (close < open) {
            // cur + ")" 当前生成一个右括号
            // open 当前已经有的左括号
            // 生成下一个右括号
            backtrack(ans, cur + ")", open, close + 1, max);
        }
    }
}
