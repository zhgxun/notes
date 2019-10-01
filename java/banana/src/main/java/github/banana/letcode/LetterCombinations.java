package github.banana.letcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    private static Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public static void main(String[] args) {
        System.out.println(letterCombinations(""));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> target = new ArrayList<>();
        if (digits.length() == 0) {
            return target;
        }

        gen(target, digits, "", 0);

        return target;
    }

    /**
     * 生成组合目标字符串列表
     *
     * @param target  保存目标字符串列表
     * @param digits  原始输入数字字符串
     * @param combine 组合后的字符串
     * @param depth   深度
     */
    private static void gen(List<String> target, String digits, String combine, int depth) {
        // 退出递归的条件, 相当于叶子节点时退出递归
        if (depth == digits.length()) {
            target.add(combine);
            return;
        }

        // 每次分解一个数字, 然后递归分解
        // 递归确实比较有意思
        // 还是挺抽象的
        String p = phone.get(digits.substring(depth, depth + 1));
        int length = p.length();
        for (int i = 0; i < length; i++) {
            gen(target, digits, combine + p.charAt(i), depth + 1);
        }
    }
}
