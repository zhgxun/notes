package github.banana.letcode;

/**
 * 459. 重复的子字符串
 * <p>
 * 给定一个非空的字符串, 判断它是否可以由它的一个子串重复多次构成
 * 给定的字符串只含有小写英文字母, 并且长度不超过10000
 */
public class RepeatedSubstringPattern {

    public static void main(String[] args) {
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("abab"));
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("aba"));
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("abcabcabcabc"));
    }

    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        for (int i = 0; i < length; i++) {

            // 至少重复一次
            int times = length / (i + 1);
            if (times == 1) {
                times = 2;
            }

            // 如果重复后的长度明显超过字符串总的长度无需在处理
            if ((i + 1) * times != length) {
                continue;
            }

            // 如果当前字符串跟第一个字符串不一致一定不是重复子串
            // 借助前面的判断避开下标越界
            if (s.charAt(i + 1) != s.charAt(0)) {
                continue;
            }

            StringBuilder builder = new StringBuilder();
            String sub = s.substring(0, i + 1);
            while (times-- > 0) {
                builder.append(sub);
            }
            if (builder.toString().equals(s)) {
                return true;
            }
        }

        return false;
    }
}
