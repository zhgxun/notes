package github.banana.design.singleton;

/**
 * 单例模式, 比较常用, 也比较特殊
 * 可以参考下面几种方式, 最常见的其实还可以加上线程安全判断
 *
 * @see github.banana.demo.Singleton 饿汉模式
 * @see github.banana.demo.SingletonV2 嵌套类, 即是持有类
 * @see github.banana.demo.SingletonV3 懒汉模式
 * <p>
 * 加上局部线程锁后是线程安全的, 但是单例模式也要一些简单易用的实现, 比如
 * @see Runtime#getRuntime() 的获取方式就是饿汉模式, 并不是所说的这么严格, 必须做到学究的地步
 * 同时这也是最简单最实用的方式, 借助类的初始化, 不需要加锁即做到线程安全
 */
public class Singleton {

    private Singleton() {
    }

    private static Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}
