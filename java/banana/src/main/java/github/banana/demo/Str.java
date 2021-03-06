package github.banana.demo;

import java.util.Arrays;

public class Str {
	public static void main(String[] args) {
		eq();
		find();
		trans();
	}
	
	/**
	 * String字符串一经定义后就不可变
	 * 
	 * 要比较两个字符串是否相等, 要使用equals()判断
	 */
	private static void eq() {
		String name1 = "Zhang";
		String name2 = "zhang";
		if (name1.equals(name2)) {
			System.out.printf("字符串%s, %s一致\n", name1, name2);
		} else {
			System.out.printf("字符串%s, %s不一致\n", name1, name2);
		}
		
		// 忽略大小小来比较字符串的内容
		if (name1.equalsIgnoreCase(name2)) {
			System.out.printf("忽略大小小后字符串%s, %s一致\n", name1, name2);
		} else {
			System.out.printf("忽略大小小后字符串%s, %s不一致\n", name1, name2);
		}
	}
	
	/**
	 * 字符串常用操作
	 */
	private static void find() {
		String s = "Hello, world";
		// 字符串是否包含子字符串
		if (s.contains("H")) {
			System.out.printf("字符串%s包含%s子字符串\n", s, "H");
		}
		
		// 子字符串在字符串中的索引位置
		int pos = s.indexOf("H");
		if (pos >= 0) {
			System.out.printf("子字符串%s在字符串%s中的索引位置%d\n", "H", s, pos);
		}
		
		// 字符串是否以子字符串开始
		if (s.startsWith("H")) {
			System.out.printf("字符串%s以子字符串%s开始\n", s, "H");
		}
		
		// 字符串以子字符串结束
		if (s.endsWith("d")) {
			System.out.printf("字符串%s以子字符串%s结束\n", s, "d");
		}
		
		// 去除字符串两边空格，没有改变字符串的内容而是返回了一个新字符串
		String m = "\n Hello, World \t\r\n";
		System.out.printf("字符串去除空格: %s\n", m.trim());
		
		// 提取子字符串
		System.out.printf("字符串%s提取2-8的子字符串%s\n", s, s.substring(2, 8));
		
		// 字符串大小写转换
		System.out.printf("字符串%s全部转为大写%s\n", s, s.toUpperCase());
		System.out.printf("字符串%s全部转为小写%s\n", s, s.toLowerCase());
		
		// 替换字符
		System.out.printf("字符串%s替换字符%c后%s\n", s, 'H', s.replace('H', 'A'));
		
		// 替换子字符串
		System.out.printf("字符串%s替换字符串%s后%s\n", s, "Hello", s.replace("Hello", "替换成一段中文"));
		
		// 正则表达式替换子串
		String n = "A,,B; C D";
		System.out.printf("正则表达式替换子串%s后%s\n", n, n.replaceAll("[\\,\\;\\s]+", ", "));
		
		// 分割字符串
		String[] p = n.split("[\\,\\;\\s]+");
		System.out.println(Arrays.toString(p));
		
		// 快速拼接字符串
		System.out.printf("用%s拼接字符串%s\n", "=====", String.join("=====", p));
	}
	
	/**
	 * String类与其它数据类型的转换
	 */
	private static void trans() {
		// 任意类型转化为字符串
		System.out.println(String.valueOf(123));
		System.out.println(String.valueOf(true));
		System.out.println(String.valueOf(new Object()));
		
		// 字符串数字转换为整数
		System.out.println(Integer.parseInt("123"));
		System.out.println(Integer.valueOf("123"));
	}
}
