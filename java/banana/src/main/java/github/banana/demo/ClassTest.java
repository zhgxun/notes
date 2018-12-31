package github.banana.demo;

import org.springframework.util.Assert;

public class ClassTest {

    public static void main(String[] args) {
        // JVM为每个加载的class创建对应的Class实例来保存class的所有信息
        // 获取一个class对应的Class实例后，就可以获取该class所有的信息
        // 通过Class实例获取class所有信息的方法称为反射
        // JVM总是动态加载class，可以在运行期根据条件动态加载class
        // java.lang.String
        System.out.println(String.class.getName());
        // String
        System.out.println(String.class.getSimpleName());
        // [Ljava.lang.Object;
        System.out.println(new Object[3].getClass().getName());
        // [I
        System.out.println(new int[3].getClass().getName());
        // [[[I
        System.out.println(new int[3][4][5].getClass().getName());
        //
        Assert.notNull(args, "不能为null");
        System.out.println("不能为null");
        Assert.notEmpty(args, "不能为空");
        System.out.println("不能为空");
    }
}
