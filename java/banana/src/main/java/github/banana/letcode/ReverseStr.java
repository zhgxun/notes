package github.banana.letcode;

/**
 * 541. 反转字符串 II
 * <p>
 * 给定一个字符串和一个整数 k, 你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转
 * 如果剩余少于 k 个字符, 则将剩余的所有全部反转
 * 如果有小于 2k 但大于或等于 k 个字符, 则反转前 k 个字符，并将剩余的字符保持原样
 * <p>
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 */
public class ReverseStr {

    public static void main(String[] args) {
        String s = "a";
        System.out.println(new ReverseStr().reverseStr(s, 3));
    }

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        int next;
        int fast;
        for (int i = 0, j = 1; i < length; j++) {
            fast = j * 2 * k;
            if (fast > length && length - i < k) {
                next = length - 1;
            } else {
                next = i + k - 1;
            }
            // 注意下标交换的位置变化, 一个上追一个下减
            for (; i < next; i++, next--) {
                char temp = chars[i];
                chars[i] = chars[next];
                chars[next] = temp;
            }
            // 交换完毕后 i 位置前进到 fast 处
            i = fast;

        }

        return new String(chars);
    }
}
