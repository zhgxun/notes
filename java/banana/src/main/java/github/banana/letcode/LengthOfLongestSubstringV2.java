package github.banana.letcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstringV2 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("aab"));
        System.out.println(lengthOfLongestSubstringV2("aab"));
    }

    // 效率中等
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // 这一步比较难以理解, 果然算法都是4分理解, 3分背诵, 3分练习
            // 如果剩下的字符中重复比较分散, 会一直执行 remove 操作直到剩下的元素不重复位置
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                // 每次都统计最大长度
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    // 优化的滑动窗口
    public static int lengthOfLongestSubstringV2(String s) {
        int n = s.length(), ans = 0;
        // 存储key=元素,value=下标在的长度
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            // map中出现了重复元素, 说明该段已经是最长了
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            // 最大值为长度差
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
