package github.banana.demo;

/**
 * static关键字还有一个比较关键的作用就是 用来形成静态代码块以优化程序性能。
 * static块可以置于类中的任何地方，类中可以有多个static块。
 * 在类初次被加载的时候，会按照static块的顺序来执行每个static块，并且只会执行一次。
 */
public class SinTextV3 {

    static {
        System.out.println("test static 1");
    }

    public static void main(String[] args) {

    }

    static {
        System.out.println("test static 2");
    }
}
