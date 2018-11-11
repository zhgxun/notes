package github.banana.demo;

/**
 * 单例模式
 * 饿汉模式类加载时创建类，加载比较慢，获取比较快，同时饿汉模式是线程安全的
 */
public class Singleton {

    // 私有化构造方法，防止外部创建类
    private Singleton() {

    }

    // 私有化类为静态属性，便于外部访问
    private static Singleton instance = new Singleton();

    // 外部获取类的实例
    public static Singleton getInstance() {
        return instance;
    }
}
