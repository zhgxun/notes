package github.banana.demo;

/**
 * 这段诡异的代码
 * null不属于任何类型,可以被转换成任何类型,但是用instanceof永远返回false
 * null是任何引用类型的默认值
 * null既不是对象也不是一种类型，它仅是一种特殊的值，你可以将其赋予任何引用类型，你也可以将null转化成任何类型
 * null可以赋值给引用变量，你不能将null赋给基本类型变量
 */
public class NULL {

    public static void sayHello() {
        System.out.println("Hello World!");
    }

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        sayHello();
        ((NULL) null).sayHello();
    }
}
