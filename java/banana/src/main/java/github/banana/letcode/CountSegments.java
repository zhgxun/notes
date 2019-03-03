package github.banana.letcode;

/**
 * 434. 字符串中的单词数
 * <p>
 * 统计字符串中的单词个数, 这里的单词指的是连续的不是空格的字符
 * <p>
 * 请注意, 你可以假定字符串里不包括任何不可打印的字符
 */
public class CountSegments {

    public static void main(String[] args) {
        System.out.println(new CountSegments().countSegments(", , , ,        a, eaefa"));
    }

    public int countSegments(String s) {
        if (s.trim().length() == 0) {
            return 0;
        }

        String line = String.format("%s ", s.trim());
        int length = line.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (line.charAt(i) == ' ' && line.charAt(i - 1) != ' ') {
                count++;
            }
        }
        return count;
    }
}
