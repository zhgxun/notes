package github.banana;

/**
 * 单例模式
 * 懒汉模式，加载比较快，加载时不实例化类，获取比较慢，首次使用需要先实例化，但不是线程安全的
 */
public class SingletonV3 {

    // 私有化构造方法，禁止外部实例化对象
    private SingletonV3() {

    }

    // 创建实例为类的静态属性，但是不自动实例化
    private static SingletonV3 instance = null;

    // 外部通过静态属性获取类的实例
    public static SingletonV3 getInstance() {
        if (instance == null) {
            instance = new SingletonV3();
        }

        return instance;
    }
}
