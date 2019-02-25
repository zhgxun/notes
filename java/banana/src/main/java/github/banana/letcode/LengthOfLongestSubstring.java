package github.banana.letcode;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * <p>
 * 给定一个字符串, 请你找出其中不含有重复字符的 最长子串 的长度
 * <p>
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @link https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/
 */
@Slf4j
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "abbbbbbbba";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring2(s));
        System.out.println(lengthOfLongestSubstring3(s));
        System.out.println(lengthOfLongestSubstring4(s));
    }

    /**
     * a. 暴力法
     * <p>
     * 逐个检查所有的子字符串, 看它是否不含有重复的字符
     * 为了枚举给定字符串的所有子字符串, 我们需要枚举它们开始和结束的索引
     * 要检查一个字符串是否有重复字符, 我们可以使用集合
     * 我们遍历字符串中的所有字符, 并将它们逐个放入 set 中
     * 在放置一个字符之前, 我们检查该集合是否已经包含它
     * 如果包含, 我们会返回 false, 循环结束后, 我们返回 true
     *
     * @param s 原始字符串
     * @return 无重复最长子字符串长度
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // 暴力枚举字符串所有子字符串
                if (unique(s, i, j)) {
                    // 保存当前最大不重复子字符串
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }

    /**
     * 检查一段字符串是否重复
     *
     * @param s     字符串
     * @param start 开始位置
     * @param end   结束位置
     * @return 重复返回 false, 不重复返回 true
     */
    public static boolean unique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    /**
     * b. 滑动窗口
     * <p>
     * 在暴力法中, 我们会反复检查一个子字符串是否含有有重复的字符, 但这是没有必要的
     * 如果从索引 i 到 j - 1 之间的子字符串 s{ij} 已经被检查为没有重复字符, 我们只需要检查 s[j] 对应的字符是否已经存在于子字符串 s{ij} 中
     * <p>
     * 要检查一个字符是否已经在子字符串中, 我们可以检查整个子字符串, 这将产生一个复杂度为 O(n^2) 的算法, 但我们可以做得更好
     * <p>
     * 通过使用 HashSet 作为滑动窗口, 我们可以用 O(1) 的时间来完成对字符是否在当前的子字符串中的检查
     * <p>
     * 滑动窗口是数组/字符串问题中常用的抽象概念, 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合, 即 [i, j) [左闭, 右开)
     * 而滑动窗口是可以将两个边界向某一方向“滑动”的窗口, 例如, 我们将 [i, j) 向右滑动 1 个元素, 则它将变为 [i + 1, j + 1) [左闭，右开)
     * <p>
     * 回到我们的问题, 我们使用 HashSet 将字符存储在当前窗口 [i, j) (最初 j = i）中,
     * 然后我们向右侧滑动索引 j, 如果它不在 HashSet 中, 我们会继续滑动 j, 直到 s[j] 已经存在于 HashSet 中
     * 此时, 我们找到的没有重复字符的最长子字符串将会以索引 i 开头, 如果我们对所有的 i 这样做, 就可以得到答案
     *
     * @param s 原始字符串
     * @return 无重复最长子字符串长度
     */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            log.info("---------------");
            log.info("初始set: {}", set);
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
            log.info("结果set: {}", set);
        }
        return ans;
    }

    /**
     * c. 优化的滑动窗口
     * <p>
     * 事实上, 它可以被进一步优化为仅需要 n 个步骤
     * 我们可以定义字符到索引的映射, 而不是使用集合来判断一个字符是否存在, 当我们找到重复的字符时, 我们可以立即跳过该窗口
     * <p>
     * 也就是说, 如果 s[j] 在 [i, j) 范围内有与 j 重复的字符, 我们不需要逐渐增加 i
     * 我们可以直接跳过 [i, j] 范围内的所有元素, 并将 i 变为 j + 1
     *
     * @param s 原始字符串
     * @return 无重复最长子字符串长度
     */
    public static int lengthOfLongestSubstring3(String s) {
        /*
         * |----------------------------
         * | a | b | b | b | b | b | a |
         * |----------------------------
         */
        /*
         * i = j = 0
         * |--------------------------------
         * | a | b | c | a | b | c | b | b |
         * |--------------------------------
         *   0   1   2   3   4   5   6   7
         */
        // 其实无重复子字符串的可以是很多中组合
        // 只需要记录已遍历移动中曾经记录的最大长度即可
        // 而最大长度其实是遇见相同字符串的时候就比较一次即可
        // j 是遍历数组用, 只会遍历一次
        // i 是距离当前时刻相同字母上一次出现的位置
        // i 相当于是一个不记录比较历史的临时变量
        // 保证 i 的更新机制即可, 即出现相同元素就需要更新 i
        int n = s.length(), ans = 0, i = 0;
        // 记录当前元素出现的最后位置
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                // 获取当前字母出现的最大位置
                // 因为重复出现时, 位置会被更新为当前最大位置
                i = Math.max(map.get(s.charAt(j)), i);
            }
            // i 为该字母当前出现的最大位置, 首次出现则为0
            // j - i + 1 记为当前无重复字符串的最大长度
            ans = Math.max(ans, j - i + 1);
            /*
             * 记录字母在数组中的位置
             * 未重复之前
             * a = 1
             * b = 2
             * c = 3
             *
             * 重复之后
             * a = 4
             * b = 5
             * c = 6
             */
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public static int lengthOfLongestSubstring4(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128];
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}
