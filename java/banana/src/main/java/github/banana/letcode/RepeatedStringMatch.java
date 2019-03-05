package github.banana.letcode;

/**
 * 686. 重复叠加字符串匹配
 * <p>
 * 给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数, 使得字符串B成为叠加后的字符串A的子串, 如果不存在则返回 -1
 * <p>
 * 举个例子, A = "abcd", B = "cdabcdab"
 * <p>
 * 答案为 3, 因为 A 重复叠加三遍后为 “abcdabcdabcd”, 此时 B 是其子串; A 重复叠加两遍后为"abcdabcd", B 并不是其子串
 * <p>
 * A 与 B 字符串的长度在1和10000区间范围内
 */
public class RepeatedStringMatch {

    public static void main(String[] args) {
        String a = "abcd";
        String b = "cdabcdab";
        System.out.println(new RepeatedStringMatch().repeatedStringMatch(a, b));
    }

    // 暴力解法
    public int repeatedStringMatch1(String A, String B) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; ; i++) {
            builder.append(A);
            String dest = builder.toString();
            if (dest.lastIndexOf(B) != -1) {
                return i + 1;
            }
            // 如果字符超过限制则退出不再寻找
            if (dest.length() > 10000) {
                return -1;
            }
        }
    }

    // 长度换算解法
    // 分析得知如果刚开始A长度较小则将其拼接超过B的长度
    // 如果此时还不符合条件, 可能是末尾字符, 在拼接一次还不匹配就真的不匹配了
    public int repeatedStringMatch(String A, String B) {
        StringBuilder str = new StringBuilder(A);
        int lenA = str.length();
        int lenStr = lenA;
        int count = 1;
        int lenB = B.length();
        while (lenStr < lenB) {
            str.append(A);
            lenStr += lenA;
            count++;
        }
        String resA = str.toString();
        if (!resA.contains(B)) {
            resA = resA + A;
            count++;
            if (!resA.contains(B)) {
                return -1;
            }
        }
        return count;
    }
}
