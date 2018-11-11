package github.banana.demo;

/**
 * 注意等号比较的是引用, 即内存中的地址
 */
public class IntegerTest {

    public static void main(String[] args) {
        // Integer 默认缓存 -127 ~ 128 小型整数值
        Integer a = 100;
        Integer b = 100;
        if (a == b) {
            System.out.println("相等");
        } else {
            System.out.println("不相等");
        }

        // 自动拆箱时重新 new 实例
        Integer c = 1000;
        Integer d = 1000;
        if (c == d) {
            System.out.println("也相等");
        } else {
            System.out.println("不相等");
        }
        // equals比较的是值
        if (c.equals(d)) {
            System.out.println("equals相等");
        } else {
            System.out.println("equals也不相等");
        }

        // Integer 赋值时会将 -127 ~ 128 之间的数据缓存起来，这之间的数据直接从缓存中读取 cache[k] = new Integer(i)

        // 不必要的装箱操作
        Integer e = new Integer(10);
        Integer f = new Integer(10);
        if (e == f) {
            System.out.println("相等");
        } else {
            System.out.println("不相等");
        }

        // 注意拆箱, 最终比较的是值
        Integer g = 500;
        int h = 500;
        if (g == h) {
            System.out.println("相等");
        } else {
            System.out.println("不相等");
        }
    }
}
