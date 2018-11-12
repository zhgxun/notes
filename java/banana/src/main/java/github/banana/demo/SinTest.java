package github.banana.demo;

/**
 * static 类加载顺序说明
 *
 * 1. 执行开始, 要寻找main()方法, 程序入口
 * 2. 既然要执行, 就要先加载类SinTest
 * 3. Base是SinTest的父类, 先加载, 故执行static静态代码段
 * 4. SinTest执行静态代码段
 * 5. new实例, 需要先调用父类的构造器
 * 6. 再调用子类的构造器
 * 7. 故输出如下
 * Base static
 * SinTest static
 * Base constructor
 * SinTest constructor
 */
public class SinTest extends Base {

    static {
        System.out.println("SinTest static");
    }

    SinTest() {
        System.out.println("SinTest constructor");
    }

    public static void main(String[] args) {
        new SinTest();
    }
}

class Base {
    static {
        System.out.println("Base static");
    }

    Base() {
        System.out.println("Base constructor");
    }
}
