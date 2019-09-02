package github.banana.letcode;

public class ToLowerCaseV2 {

    public static void main(String[] args) {
        System.out.println(toLowerCase("Hello"));
    }

    public static String toLowerCase(String str) {
        int length = str.length();
        // 将字符串转为char数组来操作
        char[] chars = str.toCharArray();
        for (int i = 0; i < length; i++) {
            int num = chars[i];
            // 大写字母 65-90 A-Z
            // 小写字母 97-122 a-z
            // 两种相差32, 记住这个点
            if (num >= 65 && num < 97) {
                chars[i] = (char) (num + 32);
            }
        }
        return new String(chars);
    }
}
