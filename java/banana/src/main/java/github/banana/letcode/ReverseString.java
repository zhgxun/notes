package github.banana.letcode;

/**
 * 344. 反转字符串
 * <p>
 * 编写一个函数, 其作用是将输入的字符串反转过来
 * 输入字符串以字符数组 char[] 的形式给出
 */
public class ReverseString {

    public static void main(String[] args) {
        char[] c = {'h', 'e', 'l', 'l'};
        new ReverseString().reverseString(c);
        System.out.println(c);
    }

    public void reverseString(char[] s) {
        int length = s.length;
        for (int i = 0, j = length - 1; i <= j; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }
}
