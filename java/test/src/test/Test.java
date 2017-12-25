package test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 测试用例
 * 
 * @author zhgxun
 *
 */
public class Test {

	/**
	 * 测试用例
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(sum(10));
		root(4.0, 15, 6);
		str();
		arr();
		ins();
		pri();
		check();
		bmi(57, 1.71);
		loop();
		sort();

		// 命令行参数是String[]数组, 是由JVM接收用户输入并传递给main的, 包含空格的命令行参数需要用""括起来
		System.out.println("Numbers is args: " + args.length);
		for (String arg : args) {
			if (arg.equals("-version")) {
				System.out.println("version: 1.0");
			} else {
				System.out.println(arg);
			}
		}
	}

	/**
	 * 求前N个自然数的和, 根据公式计算
	 * 
	 * @param n
	 * @return
	 */
	private static long sum(int n) {
		long sum;
		sum = (1 + n) * n / 2;
		return sum;
	}

	/**
	 * 一元二次方程的根 只含有一个未知数（一元），并且未知数项的最高次数是2（二次）的整式方程叫做一元二次方程 标准形式为：ax²+bx+c=0（a≠0）。
	 * 
	 * 求根公式为：x=[-b±√(b²-4ac)]/2a
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	private static void root(double a, double b, double c) {
		if (a != 0 && (b * b - 4 * a * c) >= 0) {
			System.out.println((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));
			System.out.println((-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a));
		} else {
			System.out.println("Error");
		}
	}

	/**
	 * 字符串不是一种基本的类型, 是引用类型
	 */
	private static void str() {
		// 定义两个字符串
		String s = "Hello";
		String t = s;
		System.out.println(s);
		System.out.println(t);

		// 重新赋值其中一个字符串, 引用指向, 并不改变已存在引用变量
		s = "World";
		System.out.println(s);
		System.out.println(t);
	}

	/**
	 * 声明数组的方式
	 */
	private static void arr() {
		// 声明一个数组, 需要使用new关键字
		int[] ns = new int[3];
		ns[0] = 10;
		ns[1] = 11;
		ns[2] = 12;
		// 计算数组的长度
		System.out.println("数组长度: " + ns.length);

		// 声明数组也可以声明时初始化, 不需要指定下标, 由编译器自动计算
		int[] m = new int[] { 2, 3, 4, 5, 6 };
		System.out.println("数组长度: " + m.length);

		// 声明数组初始化还可以省略类型
		int[] n = { 100, 200, 300, 500 };
		System.out.println("数组长度: " + n.length);

		// 数组是引用类型, 但数组元素可以是值类型, 比如int[], 也可以是引用类型, 比如String[]
		int[] p = { 20, 30, 40 };
		System.out.println(p[0]);

		// 把数组赋值给p2
		int[] p2 = p;

		// 即是数组声明后重新指向了新位置
		p = new int[] { 10 };
		System.out.println(p[0]);

		// 查看p2的值不会被更改
		System.out.println("p2[0] = " + p2[0]);

		// 但是字符数组元素属于引用类型
		String[] names = { "zhangsan", "lisi", "wangwu" };
		System.out.println("names[0] = " + names[0]);

		// 将字符串数组赋值给names2
		String[] names2 = names;
		System.out.println("names2[0] = " + names2[0]);

		// 更改原始字符串names[0]的值
		names[0] = "maliu";
		// 字符串是引用类型, 改变后相关引用也被更改
		System.out.println("names[0] = " + names[0]);
		System.out.println("names2[0] = " + names[0]);
	}

	/**
	 * 测试输入输出
	 */
	private static void ins() {
		// 实例化输入类
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input your name:");
		String name = scanner.nextLine();
		System.out.println("and input your age:");
		int age = scanner.nextInt();
		System.out.println("Your name is " + name + ", and your age is " + age);
	}

	/**
	 * 格式化输出
	 */
	private static void pri() {
		// %d输出整数
		int m = 10;
		System.out.printf("%d\n", m);

		// %f输出浮点数
		final float PI = 3.1415926f;
		System.out.printf("%f\n", PI);
		System.out.printf("%4f\n", PI);
		System.out.printf("%10.4f\n", PI);

		// %s输出浮点数
		String name = "China";
		System.out.printf("%s\n", name);
	}

	/**
	 * 浮点数比较大小
	 */
	private static void check() {
		// 浮点数==等于判断不靠谱, 可以使用绝对值的方式来判断
		float a = 3.14f;
		float b = 3.141f;
		if (a > b) {
			System.out.println("Max: a");
		} else {
			System.out.println("Max: b");
		}

		// 使用绝对值
		if (Math.abs(a - b) > 0.001) {
			System.out.println("Max: a");
		} else {
			System.out.println("Max: b");
		}

		String s = "Hi";
		if (s != null && s.equals("Hi")) {
			System.out.println(s);
		} else {
			System.out.println("end");
		}
	}

	/**
	 * 计算体质指数BMI=体重(kg)除以身高(m)的平方
	 * 
	 * @param d
	 * @param e
	 */
	private static void bmi(double d, double e) {
		double result = d / (e * e);
		if (result > 32) {
			System.out.printf("BMI=%2f, 非常肥胖：高于32\n", result);
		} else if (result > 28) {
			System.out.printf("BMI=%2f, 肥胖：28-32\n", result);
		} else if (result > 25) {
			System.out.printf("BMI=%2f, 过重：25-28\n", result);
		} else if (result > 18.5) {
			System.out.printf("BMI=%2f, 正常：18.5-2\n", result);
		} else {
			System.out.printf("BMI=%2f, 过轻：低于18.5\n", result);
		}
	}

	/**
	 * for each 可以遍历数组和可迭代的数据类型, 比如List, Map
	 */
	private static void loop() {
		// 但是无法指定遍历顺序和数组索引
		String[] names = { "张三", "李四", "王二", "马六", "田七" };
		for (String name : names) {
			System.out.println(name);
		}
		// 直接打印数组的变量, 得到的是数组在JVM中的引用地址
		System.out.println(names);

		// Array.toString可以快速得到数组的内容
		System.out.println(Arrays.toString(names));

		Arrays.sort(names);
		System.out.println(Arrays.toString(names));

		// 打印多维数组用Arrays.deepToString()
		int[][] n = { { 1, 2, 4 }, { 2, 5, 7 }, { 5, 4, 1 } };
		System.out.println(Arrays.toString(n));
		System.out.println(Arrays.deepToString(n));
	}

	/**
	 * 冒泡排序算法
	 */
	private static void sort() {
		int[] m = { 9, 8, 4, 3, 7, 5, 2 };
		for (int i = 0; i < m.length; i++) {
			for (int j = i + 1; j < m.length; j++) {
				// 比较两个数的值并进行交换
				if (m[i] > m[j]) {
					int t;
					t = m[i];
					m[i] = m[j];
					m[j] = t;
				}
			}
		}
		System.out.println(Arrays.toString(m));
	}

}
