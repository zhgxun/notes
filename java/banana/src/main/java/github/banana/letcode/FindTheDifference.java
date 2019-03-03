package github.banana.letcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 389. 找不同
 * <p>
 * 给定两个字符串 s 和 t, 它们只包含小写字母
 * <p>
 * 字符串 t 由字符串 s 随机重排, 然后在随机位置添加一个字母
 * <p>
 * 请找出在 t 中被添加的字母
 * <p>
 * 可使用排序直接去除法, 终极方案是异或运算
 */
public class FindTheDifference {

    public static void main(String[] args) {
        String s = "abcd";
        String t = "abcde";
        System.out.println(new FindTheDifference().findTheDifference(s, t));
    }

    // 一遍哈希法, 可能存在重复的字符, 比如 s = "a", t = "aa" 此时随机插入的字符就是 a 因此需要有一点技巧去除重复的情况
    public char findTheDifference(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        int length = s.length();

        for (int i = 0; i < length; i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }

        length = t.length();
        for (int i = 0; i < length; i++) {
            if (!map.containsKey(t.charAt(i))) {
                return t.charAt(i);
            } else {
                map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
                if (map.get(t.charAt(i)) == 0) {
                    map.remove(t.charAt(i));
                }
            }
        }

        return ' ';
    }
}
