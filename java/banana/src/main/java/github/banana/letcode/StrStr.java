package github.banana.letcode;

public class StrStr {

    public static void main(String[] args) {
        System.out.println("hello->2 " + strStrV4("hello", "ll"));
        System.out.println("aaaaa->-1 " + strStrV4("aaaaa", "bba"));
        System.out.println("aaaaa->0 " + strStrV4("aaaaa", "aa"));
        System.out.println("a->-1 " + strStrV4("a", "aa"));
        System.out.println("a->0 " + strStrV4("a", "a"));
        System.out.println("mississippi->4 " + strStrV4("mississippi", "issip"));
        System.out.println("mississippi->-1 " + strStrV4("mississippi", "sippj"));
        System.out.println("aaa->0 " + strStrV4("aaa", "aaa"));
        System.out.println("aabaaaababaababaa->-1 " + strStrV4("aabaaaababaababaa", "bbbb"));
    }

    // 相当于稍微优化的一次遍历暴力解法
    // 效率中等, 74个单元测试运行3ms
    // 边界条件太多不是好的代码设计方式
    // 看了提交记录居然可以调用系统方法, 哈哈哈, 效率还挺高
    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        int index = -1;
        char[] haystackList = haystack.toCharArray();
        int haystackLength = haystackList.length;
        char[] needleList = needle.toCharArray();
        int needleLength = needleList.length;
        for (int i = 0; i < haystackLength; i++) {
            // 找到第一个字符
            if (haystackList[i] == needleList[0]) {
                // 记录当前开始字符串
                index = i;
                // 用临时变量记录, 不要改变遍历的步进, 有点贪心的意思了
                int temp = i;
                for (int j = 0; j < needleLength; ) {
                    // 待查找的字符串比较短, 比如在字符串 aaa 中 查找 aaaaaa
                    if (temp != 0 && temp > haystackLength - 1) {
                        return -1;
                    }
                    // 不相等可能后面会相等
                    if (haystackList[temp] != needleList[j]) {
                        // 如果是最后一个则返回-1, 否则跳过该次比较
                        if (temp == haystackLength - 1) {
                            return -1;
                        }
                        break;
                    }
                    // 尝试往下查找
                    temp++;
                    j++;
                    // 查找字符串提前完成, 比如在字符串 aaaa 中查找 aa
                    // 其实仅有此时的字符串才是匹配的
                    if (j == needleLength) {
                        return index;
                    }
                }
            }
        }

        return -1;
    }

    // 直接获取子字符串比较大, 效率中等
    // 耗时2ms
    public static int strStrV2(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        int haystackLength = haystack.length();
        int needleLength = needle.length();
        if (needleLength > haystackLength) {
            return -1;
        }

        for (int i = 0; i < haystackLength; i++) {
            // 保证能获取的下标在有效范围内即可
            int end = i + needleLength;
            if (end <= haystackLength) {
                if (haystack.substring(i, end).equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    // 改进的获取子字符串方式
    // 双指针前进法
    // 耗时1ms, 算是比较优秀的解法了
    // 但是容易被忽略不能使用系统函数
    // 感觉如果这样这个题就有点失去意义了
    public static int strStrV3(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        int haystackLength = haystack.length();
        int needleLength = needle.length();
        if (needleLength > haystackLength) {
            return -1;
        }

        int end = needleLength - 1;
        for (int i = 0; i < haystackLength && end < haystackLength; i++) {
            if (haystack.substring(i, end + 1).equals(needle)) {
                return i;
            }
            end++;
        }

        return -1;
    }

    // 解法跟上一个类似, 运行效率几乎相等, 因为都是调用两个基本一致的函数
    // 感觉这个题有点失去意义了
    // 不过能写出indexOf()效率的方式也行
    // 看了下源代码其实跟解法一类似, 只是没经过优化而已
    public static int strStrV4(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
