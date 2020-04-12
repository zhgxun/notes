package com.github.zhgxun.learn.notes.leetcode;

/**
 * 面试题05. 替换空格
 * <p>
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 */
public class ReplaceSpace {

    public static void main(String[] args) {
        System.out.println(replaceSpace("We are happy."));
        System.out.println(replaceSpace("     "));
    }

    // 不适用系统函数
    // 需要知道可以替换多少
    // 存储目标字符串
    // 两次遍历
    // 多占用一份空间
    public static String replaceSpace(String s) {
        byte[] waitReplace = "%20".getBytes();
        byte[] bytes = s.getBytes();
        int len = bytes.length;
        // 一次遍历找到需要替换的空格数量
        int num = 0;
        for (byte aByte : bytes) {
            // 遇见空格需要替换, 但是触发扩容
            if (aByte == ' ') {
                num++;
            }
        }

        // 确认目标存储容量
        byte[] target = new byte[len - num + waitReplace.length * num];
        int i = 0;
        for (byte aByte : bytes) {
            // 遇见空格需要替换, 但是触发扩容
            if (aByte == ' ') {
                // 依次存储
                for (byte wait : waitReplace) {
                    target[i++] = wait;
                }
            } else {
                target[i++] = aByte;
            }
        }

        return new String(target);
    }
}
