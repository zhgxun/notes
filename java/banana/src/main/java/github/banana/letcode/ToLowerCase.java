package github.banana.letcode;

/**
 * 709. 转换成小写字母
 * <p>
 * 实现函数 ToLowerCase(), 该函数接收一个字符串参数 str, 并将该字符串中的大写字母转换成小写字母, 之后返回新的字符串
 */
public class ToLowerCase {

    public static void main(String[] args) {
        String s = "Hello";
        System.out.println(new ToLowerCase().toLowerCase(s));
    }

    public String toLowerCase(String str) {
        // 大写字母 65-90 A-Z
        // 小写字母 97-122 a-z
        char[] chars = str.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            int c = (int) chars[i];
            if (c >= 65 && c <= 90) {
                chars[i] = (char) (c + 32);
            }
        }

        return new String(chars);
    }
}
