package github.banana.demo;

public class Catch {

	/**
	 * 异常匹配的顺序很重要, 子类必须在前面, 否则会被父类捕获到不再继续捕获
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int n = Integer.parseInt("4");
			int m = 100 / n;
			System.out.printf("n = %d, m = %d\n", n, m);
		} catch (NumberFormatException | ArithmeticException e) { // 格式错误和除数为0
			System.out.println("Bad input");
			System.out.println(e);
			// 打印方法调用栈
			e.printStackTrace();
		} finally { // 可选, 确保有误异常发生都能执行
			System.out.println("Exit");
		}
	}

}
